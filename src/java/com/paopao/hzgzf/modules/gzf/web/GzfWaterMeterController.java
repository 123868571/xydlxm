/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cc.tson.bean.Request;
import com.cc.tson.bean.Response;
import com.cc.tson.tcp.ResultCallBack;
import com.cc.tson.tcp.TcpHolder;
import com.cc.tson.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import org.apache.ibatis.thread.*;
import org.apache.mina.core.RuntimeIoException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfWaterMeter;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfWaterMeterService;
import com.paopao.hzgzf.modules.pay.service.DealBillService;
import com.paopao.hzgzf.modules.pay.service.GzfAcctItemService;

/**
 * 水表Controller
 * @author Hongjun
 * @version 2016-01-17
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfWaterMeter")
public class GzfWaterMeterController extends BaseController {

    @Autowired
    private GzfWaterMeterService  gzfWaterMeterService;
    @Autowired
    private GzfHousePersonService gzfHousePersonService;
    @Autowired
//    private GzfAcctItemService    gzfAcctItemService;
    private DealBillService dealBillService;

    @Autowired
    private GzfHouseInfoService gzfHouseInfoService;

    TcpHolder tcpHolder = new TcpHolder();

    @ModelAttribute
    public GzfWaterMeter get(@RequestParam(required = false) String id) {
        GzfWaterMeter entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfWaterMeterService.get(id);
        }
        if (entity == null) {
            entity = new GzfWaterMeter();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfWaterMeter:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfHousePerson gzfHousePerson, HttpServletRequest request,
                       HttpServletResponse response, Model model, GzfWaterMeter water) {
        gzfHousePerson.setNewSelect(water.getSearchName());
        Page<GzfHousePerson> page = gzfHousePersonService.findPage(new Page<GzfHousePerson>(
            request, response), gzfHousePerson);
        List<GzfHousePerson> list = page.getList();
        if (!list.isEmpty()) {
            for (GzfHousePerson gp : list) {
                GzfWaterMeter w = new GzfWaterMeter();
                w.setGzfHouseholdInfoId(gp.getGzfHouseholdInfoId());
                List<GzfWaterMeter> ms = gzfWaterMeterService.findList(w);
                if (!ms.isEmpty()) {
                    for (GzfWaterMeter gzfWaterMeter : ms) {
                        if (gzfWaterMeter.getGzfHouseInfoId() != null)
                            if (gzfWaterMeter.getGzfHouseInfoId().equals(gp.getGzfHouseInfoId())) {
                                gp.setNum(gzfWaterMeter.getNum());
                                break;
                            }
                    }
                }
            }
        }
        model.addAttribute("page", page);
        return "modules/gzf/gzfWaterMeterList";
    }

    @RequiresPermissions("gzf:gzfWaterMeter:view")
    @RequestMapping(value = "history")
    public String history(GzfWaterMeter gzfWaterMeter, HttpServletRequest request,
                          HttpServletResponse response, Model model) {
        Page<GzfWaterMeter> page = gzfWaterMeterService.findPage(new Page<GzfWaterMeter>(request,
            response), gzfWaterMeter);
        model.addAttribute("page", page);
        return "modules/gzf/gzfWaterMeterListHistory";
    }

    @RequiresPermissions("gzf:gzfWaterMeter:view")
    @RequestMapping(value = "form")
    public String form(GzfWaterMeter gzfWaterMeter, Model model) {
        List<GzfWaterMeter> gs = gzfWaterMeterService.getByHouseId(gzfWaterMeter
            .getGzfHouseInfoId());
        GzfWaterMeter g = gs.get(0);
        g.setGzfHouseholdInfoId(gzfWaterMeter.getGzfHouseholdInfoId());
        model.addAttribute("gzfWaterMeter", g);
        model.addAttribute("sysdate", new Date());
        return "modules/gzf/gzfWaterMeterForm";
    }

    @RequiresPermissions("gzf:gzfWaterMeter:edit")
    @RequestMapping(value = "save")
    public String save(GzfWaterMeter gzfWaterMeter,Model model,
                       RedirectAttributes redirectAttributes) throws Exception {
        if (!beanValidator(model, gzfWaterMeter)) {
            return form(gzfWaterMeter, model);
        }
        gzfWaterMeter.setId(null);
        gzfWaterMeter.setGzfHouseholdInfoId(gzfWaterMeter.getGzfHouseholdInfoId());

        List<GzfWaterMeter> gs = gzfWaterMeterService.getByHouseId(gzfWaterMeter
            .getGzfHouseInfoId());
        GzfWaterMeter g = gs.get(0);
        g.setGzfHouseholdInfoId(gzfWaterMeter.getGzfHouseholdInfoId());
        if (!gs.isEmpty() && g.getNum() != null) {
//            gzfAcctItemService.generateWaterAcctItem(g, gzfWaterMeter);
        	dealBillService.generateWaterAcctItemAndWriteOff(g, gzfWaterMeter);
        } else {
//            gzfAcctItemService.generateWaterAcctItem(null, gzfWaterMeter);
        	dealBillService.generateWaterAcctItemAndWriteOff(null, gzfWaterMeter);
        }
        gzfWaterMeterService.save(gzfWaterMeter);

        addMessage(redirectAttributes, "保存水表成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfWaterMeter/?repage";
    }

    @RequiresPermissions("gzf:gzfWaterMeter:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfWaterMeter gzfWaterMeter, RedirectAttributes redirectAttributes) {
        gzfWaterMeterService.delete(gzfWaterMeter);
        addMessage(redirectAttributes, "删除水表成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfWaterMeter/?repage";
    }
    
    @RequiresPermissions("gzf:gzfWaterMeter:view")
    @RequestMapping(value = "look")
    public String look(GzfWaterMeter gzfWaterMeter,final Model model) {
        List<GzfWaterMeter> gs = gzfWaterMeterService.getByHouseId(gzfWaterMeter
            .getGzfHouseInfoId());
        GzfHouseInfo info = gzfHouseInfoService.get(gzfWaterMeter
                .getGzfHouseInfoId());
        GzfWaterMeter g = gs.get(0);
        g.setGzfHouseholdInfoId(gzfWaterMeter.getGzfHouseholdInfoId());
        model.addAttribute("gzfWaterMeter", g);
        model.addAttribute("sysdate", new Date());
        model.addAttribute("waterDegree", "-1");
        if(info == null || info.getWater() == null || info.getWaterIp() == null){
            return "modules/gzf/gzfWaterMeterLook";
        }
        getWaterNumer(info.getWater(), info.getWaterIp(), model);

        return "modules/gzf/gzfWaterMeterLook";
    }

    private void getWaterNumer(String etNum,String etIp,final Model model) {
        tcpHolder.start();
        try {
            Request t = new Request();
            t.setEt_type(2);//1表示电表2表示水表
            t.setEt_num(etNum);
            t.setEt_cmd(1);//1：总量、2：表示时间，3：时钟,4开，5关
            t.setEt_ip(etIp);
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            tcpHolder.setResponseCallBack(new ResultCallBack() {
                public void executorResult(Response response) {
                    try {
                        System.out.println("result json :result json :" + JsonUtil.toJson(response));
                        String waterDegree = "0.0";
                        if (response != null && response.getEt_num() != null) {
                            waterDegree = String.valueOf(response.getEt_context());
                        }
                        model.addAttribute("waterDegree", waterDegree);
                        countDownLatch.countDown();
                    } catch (JsonProcessingException ignore) {
                    }finally {
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
            }finally {
                tcpHolder.stop();
            }
        }catch(RuntimeIoException e){
            throw new RuntimeException("tcp服务器连接异常，请打开服务器");
        }finally {
            //
        }

    }

}