/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.common.utils.print.DocConverter;
import com.paopao.hzgzf.common.utils.print.WordUtil;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfPaymentStandard;
import com.paopao.hzgzf.modules.gzf.service.GzfHousePersonService;
import com.paopao.hzgzf.modules.gzf.service.GzfPaymentStandardService;

/**
 * 
 * 
 * @author TU
 * @version $Id: GzfDocPreviewController.java, v 0.1 2016-4-2 上午11:44:49 TU Exp $
 */
@Controller
@RequestMapping(value = "static/flexpaper")
public class GzfDocPreviewController extends BaseController {

    @Autowired
    private GzfHousePersonService     gzfHousePersonService;
    @Autowired
    private GzfPaymentStandardService gzfPaymentStandardService;

    @ModelAttribute
    public GzfHousePerson get(@RequestParam(required = false) String id) {
        GzfHousePerson entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = gzfHousePersonService.get(id);
        }
        if (entity == null) {
            entity = new GzfHousePerson();
        }
        return entity;
    }

    @RequestMapping(value = "preview")
    public String list(GzfHousePerson gzfHousePerson, HttpServletRequest request,
                       HttpServletResponse response, Model model) {

        GzfHouseholdInfo gzfHouseholdInfo = gzfHousePerson.getGzfHouseholdInfo();
        if (gzfHouseholdInfo != null) {
            if (gzfHouseholdInfo.getGzfPaymentStandardId() != null
                && !"".equals(gzfHouseholdInfo.getGzfPaymentStandardId())) {
                GzfPaymentStandard gzfps = gzfPaymentStandardService.get(gzfHouseholdInfo
                    .getGzfPaymentStandardId());
                gzfHouseholdInfo.setGzfPaymentStandard(gzfps);
            }
            gzfHousePerson.setGzfHouseholdInfo(gzfHouseholdInfo);
        }

        //1.先读取template模板数据，poi处理
        String template = request.getParameter("template");
        WordUtil a = new WordUtil();
        String temppath = request.getSession().getServletContext()
            .getRealPath("/template/" + template + ".docx");
        String path = request.getSession().getServletContext()
            .getRealPath("/output/" + template + System.currentTimeMillis() + ".docx");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("htNo", "HT-" + gzfHousePerson.getCreateDate().getTime() / 1000);
        map.put("name", gzfHousePerson.getGzfHouseholdInfo().getName());
        map.put("cardid", gzfHousePerson.getGzfHouseholdInfo().getCardid());
        map.put("address", gzfHousePerson.getGzfHouseholdInfo().getAddress());
        map.put("company", gzfHousePerson.getGzfHouseholdInfo().getCompany());
        map.put("phone", gzfHousePerson.getGzfHouseholdInfo().getPhone());

        StringBuilder sb = new StringBuilder();
        sb.append(gzfHousePerson.getGzfHouseInfo().getGzfPalaces().getGzfVillage().getName());
        sb.append(gzfHousePerson.getGzfHouseInfo().getGzfPalaces().getName());
        sb.append(gzfHousePerson.getGzfHouseInfo().getBuildNum()).append("号");
        sb.append(gzfHousePerson.getGzfHouseInfo().getUnit()).append("单元");
        sb.append(gzfHousePerson.getGzfHouseInfo().getRoom()).append("房间");

        map.put("houseAddress", sb.toString());
        map.put("useArea", gzfHousePerson.getGzfHouseInfo().getUseArea());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(gzfHousePerson.getStartDate());
        map.put("beginYear", calendar.get(Calendar.YEAR));
        map.put("beginMonth", calendar.get(Calendar.MONTH) + 1);
        map.put("beginDay", calendar.get(Calendar.DAY_OF_MONTH));

        calendar.setTime(gzfHousePerson.getEndDate());
        map.put("endYear", calendar.get(Calendar.YEAR));
        map.put("endMonth", calendar.get(Calendar.MONTH) + 1);
        map.put("endDay", calendar.get(Calendar.DAY_OF_MONTH));
        map.put("rentUnitPrice", gzfHousePerson.getGzfHouseholdInfo().getGzfPaymentStandard()
            .getRentUnitPrice());
        map.put("totalPrice", gzfHousePerson.getGzfHouseholdInfo().getGzfPaymentStandard()
            .getRentUnitPrice()
                              * gzfHousePerson.getGzfHouseInfo().getUseArea());

        try {
            XWPFDocument doc = a.generateWordFromTemplate(temppath, path, map);
            OutputStream out = new FileOutputStream(path);
            doc.write(out);
            out.close();
        } catch (InvalidFormatException | IOException e) {
            logger.error("读取模板文件错误:", e);
        }
        //2.输出处理的文件预览
        //调用转换类DocConverter,并将需要转换的文件传递给该类的构造方法
        DocConverter d = new DocConverter(path);
        //调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;
        d.conver();
        //调用getswfPath()方法，打印转换后的swf文件路径
        //生成swf相对路径，以便传递给flexpaper播放器
        String swfpath = d.getswfPath().substring(d.getswfPath().lastIndexOf("/"));
        //将相对路径放入sessio中保存
        model.addAttribute("swfpath", swfpath);
        return "modules/gzf/gzfDocPreView";
    }
}