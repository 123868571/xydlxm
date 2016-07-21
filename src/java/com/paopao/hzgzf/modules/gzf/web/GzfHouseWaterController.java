/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import com.cc.tson.bean.Request;
import com.cc.tson.bean.Response;
import com.cc.tson.tcp.ResultCallBack;
import com.cc.tson.tcp.TcpHolder;
import com.cc.tson.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.*;
import com.paopao.hzgzf.modules.gzf.service.*;
import org.apache.mina.core.RuntimeIoException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * 房屋信息Controller
 * 
 * @author Hongjun
 * @version 2016-01-02
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfHouseWater")
public class GzfHouseWaterController extends BaseController {

	@Autowired
	private GzfHouseInfoService gzfHouseInfoService;

	@Autowired
	private GzfPalacesService gzfPalacesService;

	@Autowired
	private GzfHousePropertyService gzfHousePropertyService;

	@Autowired
	private GzfHousePersonService gzfHousePersonService;

	@Autowired
	private GzfHouseholdInfoService gzfHouseholdInfoService;
	
	//@Autowired
	//DealAutoMeterReadRecord dealAutoMeterReadRecord;

	TcpHolder tcpHolder = new TcpHolder();

	@ModelAttribute
	public GzfHouseInfo get(@RequestParam(required = false) String id) {
		GzfHouseInfo entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = gzfHouseInfoService.get(id);
		}
		if (entity == null) {
			entity = new GzfHouseInfo();
		}
		return entity;
	}

	@RequiresPermissions("gzf:gzfHouseWater:view")
	@RequestMapping(value = { "list", "" })
	public String list(GzfHouseInfo gzfHouseInfo, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String houseAll = gzfHouseInfo.getHouseAll();
		Pattern pattern = Pattern.compile("([1-9]-)?([1-9]-)?\\d+");
		if (!StringUtils.isEmpty(houseAll) && pattern.matcher(houseAll).matches()) {
			String[] rooms = StringUtils.split(houseAll, "-");
			if (rooms.length == 3) {
				gzfHouseInfo.setBuildNum(Integer.valueOf(rooms[0]));
				gzfHouseInfo.setUnit(Integer.valueOf(rooms[1]));
				gzfHouseInfo.setRoom(rooms[2]);
			} else if (rooms.length == 2) {
				gzfHouseInfo.setUnit(Integer.valueOf(rooms[0]));
				gzfHouseInfo.setRoom(rooms[1]);
			} else if (rooms.length == 1) {
				gzfHouseInfo.setRoom(rooms[0]);
			}
			gzfHouseInfo.setHouseAll(null);
		}
		Page<GzfHouseInfo> page = gzfHouseInfoService.findPage(new Page<GzfHouseInfo>(request, response), gzfHouseInfo);
		List<GzfHouseInfo> list = page.getList();
		List<GzfHouseInfo> resultList = new ArrayList<GzfHouseInfo>();
		if (!list.isEmpty()) {
			for (GzfHouseInfo gzfhi : list) {
				GzfHousePerson gzfp = new GzfHousePerson();
				gzfp.setGzfHouseInfoId(gzfhi.getId());
				gzfp.setBind("1");
				GzfHousePerson gzfp1 = gzfHousePersonService.query1(gzfp);
				if (gzfp1 != null) {
					GzfHouseholdInfo gzfhhi = gzfHouseholdInfoService.get(gzfp1.getGzfHouseholdInfoId());
					if (gzfhhi != null) {
						gzfp1.setGzfHouseholdInfo(gzfhhi);
					}
					gzfhi.setGzfHousePerson(gzfp1);
				}
				resultList.add(gzfhi);
			}
		}
		page.setList(resultList);
		model.addAttribute("page", page);
		return "modules/gzf/gzfHouseWaterList";
	}

	@RequiresPermissions("gzf:gzfHouseWater:view")
	@RequestMapping(value = "form")
	public String form(GzfHouseInfo gzfHouseInfo, Model model) {
		model.addAttribute("gzfHouseInfo", gzfHouseInfo);
		model.addAttribute("gzfPalacesList", gzfPalacesService.findList(new GzfPalaces()));
		model.addAttribute("gzfHousePropertyList", gzfHousePropertyService.findList(new GzfHouseProperty()));
		return "modules/gzf/gzfHouseWaterForm";
	}

	@RequiresPermissions("gzf:gzfHouseWater:edit")
	@RequestMapping(value = "save")
	public String save(GzfHouseInfo gzfHouseInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzfHouseInfo)) {
			return form(gzfHouseInfo, model);
		}
		try {
			gzfHouseInfoService.save(gzfHouseInfo, false);
		} catch (DuplicateKeyException e) {
			logger.error("已经初始化过房屋信息", e);
			addMessage(redirectAttributes, "已经初始化过房屋信息");
			return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseWater/?repage";
		}
		addMessage(redirectAttributes, "保存房屋信息成功");
		return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseWater/?repage";
	}

	@RequiresPermissions("gzf:gzfHouseWater:edit")
	@RequestMapping(value = "delete")
	public String delete(GzfHouseInfo gzfHouseInfo, RedirectAttributes redirectAttributes) {
		gzfHouseInfoService.delete(gzfHouseInfo);
		addMessage(redirectAttributes, "删除房屋信息成功");
		return "redirect:" + Global.getAdminPath() + "/gzf/gzfHouseWater/?repage";
	}

	@RequiresPermissions("gzf:gzfHouseWater:view")
	@RequestMapping(value = "ctrol")
	public String ctrol(GzfHouseInfo gzfHouseInfo, Model model) {
		GzfHousePerson ghp = new GzfHousePerson();
		ghp.setGzfHouseInfoId(gzfHouseInfo.getId());
		GzfHousePerson personis = gzfHousePersonService.query1(ghp);
		/*personis.getGzfHouseholdInfo().getName();
		sysout*/
		gzfHouseInfo.setGzfHousePerson(personis);
		return "modules/gzf/gzfHouseWaterctrol";
	}

	@RequiresPermissions("gzf:gzfHouseWater:view")
	@RequestMapping(value = "changeStatus")
	public String changeStatus(GzfHouseInfo gzfHouseInfo, HttpServletRequest request, Model model) {
		if (gzfHouseInfo.getElecStatus() == null) {
			gzfHouseInfo.setElecStatus(0);
		}
		if (gzfHouseInfo.getWaterStatus() == null) {
			gzfHouseInfo.setWaterStatus(0);
		}

		String strNdem = gzfHouseInfo.getGzfPalaces().getGzfVillage().getName();
		String option = String.valueOf(request.getParameter("type"));
		String etNum = null;
		String etIp = null;
		int etType = 1;
		int etCmd = -1;
		switch (option) {
		case "e":
			if (gzfHouseInfo == null || StringUtils.isEmpty(gzfHouseInfo.getElec())
					|| StringUtils.isEmpty(gzfHouseInfo.getElecIp())) {
				return "modules/gzf/gzfHouseWaterctrol";
			}
			if (gzfHouseInfo.getElecStatus() == null || gzfHouseInfo.getElecStatus() == 0) {
				etCmd = 5;
			} else {
				etCmd = 4;
			}
			etNum = gzfHouseInfo.getElec();
			etIp = gzfHouseInfo.getElecIp();
			break;
		case "w":
			if (gzfHouseInfo == null || StringUtils.isEmpty(gzfHouseInfo.getWater())
					|| StringUtils.isEmpty(gzfHouseInfo.getWaterIp())) {
				return "modules/gzf/gzfHouseWaterctrol";
			}
			if (gzfHouseInfo.getWaterStatus() == null || gzfHouseInfo.getWaterStatus() == 0) {
				etCmd = 5;
			} else {
				etCmd = 4;
			}

			etType = 2;
			etNum = gzfHouseInfo.getWater();
			etIp = gzfHouseInfo.getWaterIp();
			break;
		default:
			return "modules/gzf/gzfHouseWaterctrol";
		}
		// changeStatus(etNum, etIp, etType, etCmd, model);
		model.addAttribute("changeStatus", true);
		if (model.containsAttribute("changeStatus")) {
			// 更新数据库
			if (etType == 2) {
				gzfHouseInfo.setWaterStatus(~gzfHouseInfo.getWaterStatus() & 0X00000001);
			} else if (etType == 1) {
				gzfHouseInfo.setElecStatus(~gzfHouseInfo.getElecStatus() & 0X00000001);
			}
			gzfHouseInfoService.updateInfoAndStatus(gzfHouseInfo);
		}
		return "modules/gzf/gzfHouseWaterctrol";
	}

	private void changeStatus(String etNum, String etIp, int etType, int cmd, final Model model) {
		tcpHolder.start();
		try {
			Request t = new Request();
			t.setEt_type(etType);// 1表示电表2表示水表
			t.setEt_num(etNum);
			t.setEt_cmd(cmd);// 1：总量、2：表示时间，3：时钟,4开，5关
			t.setEt_ip(etIp);
			final CountDownLatch countDownLatch = new CountDownLatch(1);
			tcpHolder.setResponseCallBack(new ResultCallBack() {
				public void executorResult(Response response) {
					try {
						System.out.println("result json :result json :" + JsonUtil.toJson(response));
						if ("1".equals(response.getEt_context())) {
							model.addAttribute("changeStatus", true);
						}
						countDownLatch.countDown();
					} catch (JsonProcessingException ignore) {
					} finally {
						tcpHolder.stop();
					}
				}
			});
			try {
				tcpHolder.write(JsonUtil.toJson(t));
				countDownLatch.await(60, TimeUnit.SECONDS);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				tcpHolder.stop();
			}
		} catch (RuntimeIoException e) {
			throw new RuntimeException("tcp服务器连接异常，请打开服务器");
		} finally {
			//
		}
	}
	
//	@RequiresPermissions("gzf:gzfHouseWater:edit")
//	@RequestMapping(value="dealWaterMeterAutoReadRecord")
//	public @ResponseBody Map<String, String> dealWaterMeterAutoReadRecord(){
//		dealAutoMeterReadRecord.dealWaterMeterAutoReadRecord();
//		Map map = new HashMap();
//		map.put("message", "操作成功！");
//		return map;
//	}
//	
//	@RequiresPermissions("gzf:gzfHouseWater:edit")
//	@RequestMapping(value="dealElecMeterAutoReadRecord")
//	public @ResponseBody Map<String, String> dealElecMeterAutoReadRecord(){
//		dealAutoMeterReadRecord.dealElecMeterAutoReadRecord();
//		Map map = new HashMap();
//		map.put("message", "操作成功！");
//		return map;
//	}
}