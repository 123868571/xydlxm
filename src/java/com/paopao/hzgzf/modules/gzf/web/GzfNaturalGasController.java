/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.paopao.hzgzf.modules.gzf.entity.GzfNaturalGas;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfNaturalGasService;
import com.paopao.hzgzf.modules.pay.service.DealBillService;

/**
 * 天然气表录入Controller
 * @author Hongjun
 * @version 2016-01-18
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfNaturalGas")
public class GzfNaturalGasController extends BaseController {

    @Autowired
    private GzfNaturalGasService  gzfNaturalGasService;
    @Autowired
    private GzfHousePersonService gzfHousePersonService;
    @Autowired
//    private GzfAcctItemService    gzfAcctItemService;
    private DealBillService dealBillService;

    @ModelAttribute
    public GzfNaturalGas get(@RequestParam(required = false) String id) {
        GzfNaturalGas entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfNaturalGasService.get(id);
        }
        if (entity == null) {
            entity = new GzfNaturalGas();
        }
        return entity;
    }

    @RequiresPermissions("gzf:gzfNaturalGas:view")
    @RequestMapping(value = { "list", "" })
    public String list(GzfHousePerson gzfHousePerson, HttpServletRequest request,
                       GzfNaturalGas gas, HttpServletResponse response, Model model) {
        gzfHousePerson.setNewSelect(gas.getSearchName());
        Page<GzfHousePerson> page = gzfHousePersonService.findPage(new Page<GzfHousePerson>(
            request, response), gzfHousePerson);
        List<GzfHousePerson> list = page.getList();
        if (!list.isEmpty()) {
            for (GzfHousePerson gp : list) {
                List<GzfNaturalGas> ms = gzfNaturalGasService.findList(new GzfNaturalGas());
                if (!ms.isEmpty()) {
                    for (GzfNaturalGas gzfWaterMeter : ms) {
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
        return "modules/gzf/gzfNaturalGasList";
    }

    @RequiresPermissions("gzf:gzfNaturalGas:view")
    @RequestMapping(value = "form")
    public String form(GzfNaturalGas gzfNaturalGas, Model model) {
        List<GzfNaturalGas> gs = gzfNaturalGasService.getByHouseId(gzfNaturalGas
            .getGzfHouseInfoId());
        GzfNaturalGas g = gs.get(0);
        g.setGzfHouseholdInfoId(gzfNaturalGas.getGzfHouseholdInfoId());
        if (!gs.isEmpty())
            model.addAttribute("gzfNaturalGas", g);
        model.addAttribute("sysdate", new Date());
        return "modules/gzf/gzfNaturalGasForm";
    }

    @RequiresPermissions("gzf:gzfNaturalGas:view")
    @RequestMapping(value = "history")
    public String history(GzfNaturalGas gzfNaturalGas, HttpServletRequest request,
                          HttpServletResponse response, Model model) {
        Page<GzfNaturalGas> page = gzfNaturalGasService.findPage(new Page<GzfNaturalGas>(request,
            response), gzfNaturalGas);
        model.addAttribute("page", page);
        return "modules/gzf/gzfNaturalGasListHistory";
    }

    @RequiresPermissions("gzf:gzfNaturalGas:edit")
    @RequestMapping(value = "save")
    public String save(GzfNaturalGas gzfNaturalGas, Model model,
                       RedirectAttributes redirectAttributes) throws Exception {
        if (!beanValidator(model, gzfNaturalGas)) {
            return form(gzfNaturalGas, model);
        }
        gzfNaturalGas.setId(null);
        gzfNaturalGas.setGzfHouseholdInfoId(gzfNaturalGas.getGzfHouseholdInfoId());

        List<GzfNaturalGas> gs = gzfNaturalGasService.getByHouseId(gzfNaturalGas
            .getGzfHouseInfoId());
        GzfNaturalGas g = gs.get(0);
        g.setGzfHouseholdInfoId(gzfNaturalGas.getGzfHouseholdInfoId());
        if (!gs.isEmpty() && g.getNum() != null) {
//            gzfAcctItemService.generateNatureGasAcctItem(g, gzfNaturalGas);
        	dealBillService.generateNatureGasAcctItemAndWriteOff(g, gzfNaturalGas);
        } else {
//            gzfAcctItemService.generateNatureGasAcctItem(null, gzfNaturalGas);
        	dealBillService.generateNatureGasAcctItemAndWriteOff(null, gzfNaturalGas);
        }
        gzfNaturalGasService.save(gzfNaturalGas);

        addMessage(redirectAttributes, "保存天然气表录入成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfNaturalGas/?repage";
    }

    @RequiresPermissions("gzf:gzfNaturalGas:edit")
    @RequestMapping(value = "delete")
    public String delete(GzfNaturalGas gzfNaturalGas, RedirectAttributes redirectAttributes) {
        gzfNaturalGasService.delete(gzfNaturalGas);
        addMessage(redirectAttributes, "删除天然气表录入成功");
        return "redirect:" + Global.getAdminPath() + "/gzf/gzfNaturalGas/?repage";
    }
    
    @RequiresPermissions("gzf:gzfNaturalGas:view")
    @RequestMapping(value = "look")
    public String look(GzfNaturalGas gzfNaturalGas, Model model) {
        List<GzfNaturalGas> gs = gzfNaturalGasService.getByHouseId(gzfNaturalGas
            .getGzfHouseInfoId());
        GzfNaturalGas g = gs.get(0);
        g.setGzfHouseholdInfoId(gzfNaturalGas.getGzfHouseholdInfoId());
        if (!gs.isEmpty())
            model.addAttribute("gzfNaturalGas", g);
        model.addAttribute("sysdate", new Date());
        return "modules/gzf/gzfNaturalGasLook";
    }

}