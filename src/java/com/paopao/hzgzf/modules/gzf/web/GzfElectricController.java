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
import com.paopao.hzgzf.modules.gzf.entity.GzfElectric;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.service.GzfElectricService;
import com.paopao.hzgzf.modules.gzf.service.GzfHouseInfoService;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.pay.service.DealBillService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 电表录入Controller
 * @author Hongjun
 * @version 2016-01-18
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfElectric")
public class GzfElectricController extends BaseController {

    @Autowired
    private GzfElectricService    gzfElectricService;
    @Autowired
    private GzfHousePersonService gzfHousePersonService;
    @Autowired
//    private GzfAcctItemService    gzfAcctItemService;
    private DealBillService dealBillService;
    
    @Autowired
    private GzfHouseInfoService gzfHouseInfoService;
    
    TcpHolder tcpHolder = new TcpHolder();

    @ModelAttribute
    public GzfElectric get(@RequestParam(required = false) String id) {
        GzfElectric entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfElectricService.get(id);
        }
        if (entity == null) {
            entity = new GzfElectric();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfElectric:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfHousePerson gzfHousePerson, HttpServletRequest request, GzfElectric elec,
                       HttpServletResponse response, Model model) {
        gzfHousePerson.setNewSelect(elec.getSearchName());
        Page<GzfHousePerson> page = gzfHousePersonService.findPage(new Page<GzfHousePerson>(
            request, response), gzfHousePerson);
        List<GzfHousePerson> list = page.getList();
        if (!list.isEmpty()) {
            for (GzfHousePerson gp : list) {
                List<GzfElectric> ms = gzfElectricService.findList(new GzfElectric());
                if (!ms.isEmpty()) {
                    for (GzfElectric gzfWaterMeter : ms) {
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
        return "modules/gzf/gzfElectricList";
    }

    @RequiresPermissions("gzf:gzfElectric:view")
    @RequestMapping(value = "form")
    public String form(GzfElectric gzfElectric, Model model) {
        List<GzfElectric> gs = gzfElectricService.getByHouseId(gzfElectric.getGzfHouseInfoId());
        GzfElectric g = gs.get(0);
        g.setGzfHouseholdInfoId(gzfElectric.getGzfHouseholdInfoId());
        // if (!gs.isEmpty())
        model.addAttribute("gzfElectric", g);
        model.addAttribute("sysdate", new Date());
        return "modules/gzf/gzfElectricForm";
    }

    @RequiresPermissions("gzf:gzfElectric:view")
    @RequestMapping(value = "history")
    public String history(GzfElectric gzfElectric, HttpServletRequest request,
                          HttpServletResponse response, Model model) {
        Page<GzfElectric> page = gzfElectricService.findPage(new Page<GzfElectric>(request,
            response), gzfElectric);
        model.addAttribute("page", page);
        return "modules/gzf/gzfElectricListHistory";
    }

    @RequiresPermissions("gzf:gzfElectric:edit")
    @RequestMapping(value = "save")
    public String save(GzfElectric gzfElectric, Model model, RedirectAttributes redirectAttributes)
                                                                                                   throws Exception {
        if (!beanValidator(model, gzfElectric)) {
            return form(gzfElectric, model);
        }
        gzfElectric.setId(null);
        gzfElectric.setGzfHouseholdInfoId(gzfElectric.getGzfHouseholdInfoId());

        List<GzfElectric> gs = gzfElectricService.getByHouseId(gzfElectric.getGzfHouseInfoId());
        GzfElectric g = gs.get(0);
        g.setGzfHouseholdInfoId(gzfElectric.getGzfHouseholdInfoId());
        if (!gs.isEmpty() && g.getNum() != null) {
//            gzfAcctItemService.generateElectricAcctItem(g, gzfElectric);
        	dealBillService.generateElectricAcctItemAndWriteOff(g, gzfElectric);
        } else {
//            gzfAcctItemService.generateElectricAcctItem(null, gzfElectric);
        	dealBillService.generateElectricAcctItemAndWriteOff(null, gzfElectric);
        }
        gzfElectricService.save(gzfElectric);
        addMessage(redirectAttributes, "保存电表录入成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfElectric/?repage";
    }

    @RequiresPermissions("gzf:gzfElectric:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfElectric gzfElectric, RedirectAttributes redirectAttributes) {
        gzfElectricService.delete(gzfElectric);
        addMessage(redirectAttributes, "删除电表录入成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfElectric/?repage";
    }
    
    @RequiresPermissions("gzf:gzfElectric:view")
    @RequestMapping(value = "look")
    public String look(GzfElectric gzfElectric, Model model) {
        List<GzfElectric> gs = gzfElectricService.getByHouseId(gzfElectric.getGzfHouseInfoId());
        GzfElectric g = gs.get(0);
        g.setGzfHouseholdInfoId(gzfElectric.getGzfHouseholdInfoId());
        // if (!gs.isEmpty())
        GzfHouseInfo info = gzfHouseInfoService.get(gzfElectric
                .getGzfHouseInfoId());
        model.addAttribute("gzfElectric", g);
        model.addAttribute("sysdate", new Date());
        model.addAttribute("ElectricDegree", -2);
        
        if(info == null || info.getElec() == null || info.getElecIp() == null){
            return "modules/gzf/gzfElectricLook";
        }
        getElecNumer(info.getElec(), info.getElecIp(), model);
        
        return "modules/gzf/gzfElectricLook";
    }
    
    private void getElecNumer(String etNum,String etIp,final Model model) {
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
                        model.addAttribute("ElectricDegree", waterDegree);
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
        }finally {
            //
        }

    }

}