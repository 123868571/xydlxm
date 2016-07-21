package com.paopao.hzgzf.modules.gzf.web;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.common.utils.excel.ExportExcel;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.bo.FeeReportVO;
import com.paopao.hzgzf.modules.gzf.bo.ReportBO;
import com.paopao.hzgzf.modules.gzf.bo.ReportVO;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;
import com.paopao.hzgzf.modules.gzf.entity.GzfPalaces;
import com.paopao.hzgzf.modules.gzf.entity.GzfVillage;
import com.paopao.hzgzf.modules.gzf.service.*;
import com.paopao.hzgzf.modules.pay.common.PaymentConst;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalance;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItem;
import com.paopao.hzgzf.modules.pay.service.GzfAccountBalanceService;
import com.paopao.hzgzf.modules.pay.service.GzfAccountService;
import com.paopao.hzgzf.modules.pay.service.GzfAcctItemService;
import com.paopao.hzgzf.modules.sys.entity.User;
import com.paopao.hzgzf.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by FastLane on 2016-03-18.
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfReport")
public class GzfFeeReportController extends BaseController {

    @Autowired
    GzfAccountService accountService;

    @Autowired
    GzfPalacesService gzfPalacesService;

    @Autowired
    GzfHousePersonService housePersonService;

    @Autowired
    GzfHouseInfoService houseInfoService;

    @Autowired
    GzfPalacesService placesService;

    @Autowired
    GzfAccountBalanceService balanceService;

    @Autowired
    GzfVillageService gzfVillageService;

    @Autowired
    GzfAcctItemService gzfAcctItemService;

    /**
     * 缴费报表页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/fee")
    public String fee(GzfAccount gzfAccount, HttpServletRequest request,
      HttpServletResponse response, Model model) {
        User user = UserUtils.getUser();
        List<GzfVillage> gzfVillageList = gzfVillageService.findAllList(null);
        model.addAttribute("gzfVillageList", gzfVillageList);
        return "modules/gzf/gzfRportFee";
    }


    /**
     * 缴费报表导出excel
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public String feeExport(HttpServletRequest request,
      HttpServletResponse response, RedirectAttributes redirectAttributes) {
        Page<GzfAccount> pageAccount = new Page<>(request, response, -1);
        GzfAccount gzfAccount = new GzfAccount();
        GzfHousePerson gzfHousePerson = new GzfHousePerson();
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String type = request.getParameter("type");
        String redirectUrl = "redirect:" + adminPath + "/gzf/gzfReport/fee?repage";
        String title = "";

        int pamentType = Integer.parseInt(type);

        switch (pamentType) {
            case PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT:
                title = "房租";
                redirectUrl = "redirect:" + adminPath + "/gzf/gzfReport/fee/houseDetail?repage";
                break;
            case PaymentConst.ACCT_ITEM_TYPE.WATER:
                title = "水费";
                redirectUrl = "redirect:" + adminPath + "/gzf/gzfReport/fee/houseDetail?repage";
                break;
            case PaymentConst.ACCT_ITEM_TYPE.ELECTRICITY:
                title = "电费";
                redirectUrl = "redirect:" + adminPath + "/gzf/gzfReport/fee/houseDetail?repage";
                break;
            case PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT:
                title = "物业费";
                redirectUrl = "redirect:" + adminPath + "/gzf/gzfReport/fee/houseDetail?repage";
                break;
            default:
                break;
        }

        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");

        // 获取所有苑
        List<String> palacesIds = getPalacesListFromParam(villageId, palacesId, startDate, endDate);
        gzfHousePerson.setPalacesIds(palacesIds);
        gzfAccount.setPalacesIdList(palacesIds);

        //        pageAccount.setPageSize(1000);
        Page<GzfAccount> page =
          fee2(gzfAccount, pageAccount, startDate, endDate, pamentType);

        try {
            String fileName = title + "缴费数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            new ExportExcel(title + "缴费数据", GzfAccount.class).setDataList(page.getList())
              .write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出缴费报表失败！失败信息：" + e.getMessage());
        }
        return redirectUrl;
    }

    /**
     * 缴费报表页面 - 房租
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/fee/houseDetail")
    public String feeHouse(HttpServletRequest request,
      HttpServletResponse response, Model model) {
        User user = UserUtils.getUser();
        List<GzfVillage> gzfVillageList = gzfVillageService.findAllList(null);
        model.addAttribute("gzfVillageList", gzfVillageList);
        Page<GzfAccount> pageAccount = new Page<>(request, response);
        //        pageAccount.setPageSize(4);
        //        Page<GzfAccount> page = accountService.findPage(pageAccount, gzfAccount);
        GzfAccount gzfAccount = new GzfAccount();
        //        List<String> accountIdList = new ArrayList<>();
        GzfHousePerson gzfHousePerson = new GzfHousePerson();
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");

        // 获取所有苑
        List<String> palacesIds = getPalacesListFromParam(villageId, palacesId, startDate, endDate);
        gzfHousePerson.setPalacesIds(palacesIds);

        // 所有租户
        //        List<GzfHousePerson> gzfHousePersonList = housePersonService.findList(gzfHousePerson);
        //        Iterator iterator = gzfHousePersonList.iterator();
        //        while (iterator.hasNext()) {
        //            GzfHousePerson gzfHousePerson1 = (GzfHousePerson) iterator.next();
        //            if (gzfHousePerson1 != null && gzfHousePerson1.getAccountId() != null
        //              && !gzfHousePerson1.getAccountId().equals("")) {
        //                accountIdList.add(gzfHousePerson1.getAccountId());
        //            }
        //        }
        // 所有租户的账号
        //        gzfAccount.setAccountIdList(accountIdList);
        gzfAccount.setPalacesIdList(palacesIds);

        Page<GzfAccount> page =
          fee2(gzfAccount, pageAccount, startDate, endDate,
            PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT);

        model.addAttribute("page", page);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("villageId", villageId);
        model.addAttribute("palacesId", palacesId);

        return "modules/gzf/gzfRportHouseFee";
    }

    /**
     * 缴费报表页面 - 水费
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/fee/waterDetail")
    public String feeWater(HttpServletRequest request,
      HttpServletResponse response, Model model) {
        User user = UserUtils.getUser();
        List<GzfVillage> gzfVillageList = gzfVillageService.findAllList(null);
        model.addAttribute("gzfVillageList", gzfVillageList);
        Page<GzfAccount> pageAccount = new Page<>(request, response);
        //        Page<GzfAccount> page = accountService.findPage(pageAccount, gzfAccount);
        GzfAccount gzfAccount = new GzfAccount();
        //        List<String> accountIdList = new ArrayList<>();
        GzfHousePerson gzfHousePerson = new GzfHousePerson();
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");

        // 获取所有苑
        List<String> palacesIds = getPalacesListFromParam(villageId, palacesId, startDate, endDate);
        gzfHousePerson.setPalacesIds(palacesIds);

        gzfAccount.setPalacesIdList(palacesIds);

        Page<GzfAccount> page =
          fee2(gzfAccount, pageAccount, startDate, endDate, PaymentConst.ACCT_ITEM_TYPE.WATER);

        model.addAttribute("page", page);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("villageId", villageId);
        model.addAttribute("palacesId", palacesId);

        return "modules/gzf/gzfRportWaterFee";
    }


    /**
     * 缴费报表页面 - 电费
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/fee/electricDetail")
    public String feeElectric(HttpServletRequest request,
      HttpServletResponse response, Model model) {
        User user = UserUtils.getUser();
        List<GzfVillage> gzfVillageList = gzfVillageService.findAllList(null);
        model.addAttribute("gzfVillageList", gzfVillageList);
        Page<GzfAccount> pageAccount = new Page<>(request, response);
        //        Page<GzfAccount> page = accountService.findPage(pageAccount, gzfAccount);
        GzfAccount gzfAccount = new GzfAccount();
        //        List<String> accountIdList = new ArrayList<>();
        GzfHousePerson gzfHousePerson = new GzfHousePerson();
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");

        if (endDate != null) {
            endDate += " 23:59:59";
        }

        // 获取所有苑
        List<String> palacesIds = getPalacesListFromParam(villageId, palacesId, startDate, endDate);
        gzfHousePerson.setPalacesIds(palacesIds);

        gzfAccount.setPalacesIdList(palacesIds);

        Page<GzfAccount> page =
          fee2(gzfAccount, pageAccount, startDate, endDate,
            PaymentConst.ACCT_ITEM_TYPE.ELECTRICITY);

        model.addAttribute("page", page);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("villageId", villageId);
        model.addAttribute("palacesId", palacesId);

        return "modules/gzf/gzfRportElectricFee";
    }

    /**
     * 缴费报表页面 - 物业费
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/fee/propertyDetail")
    public String feeProperty(HttpServletRequest request,
      HttpServletResponse response, Model model) {
        User user = UserUtils.getUser();
        List<GzfVillage> gzfVillageList = gzfVillageService.findAllList(null);
        model.addAttribute("gzfVillageList", gzfVillageList);
        Page<GzfAccount> pageAccount = new Page<>(request, response);
        //        Page<GzfAccount> page = accountService.findPage(pageAccount, gzfAccount);
        GzfAccount gzfAccount = new GzfAccount();
        //        List<String> accountIdList = new ArrayList<>();
        GzfHousePerson gzfHousePerson = new GzfHousePerson();
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");

        if (endDate != null) {
            endDate += " 23:59:59";
        }

        // 获取所有苑
        List<String> palacesIds = getPalacesListFromParam(villageId, palacesId, startDate, endDate);
        gzfHousePerson.setPalacesIds(palacesIds);

        gzfAccount.setPalacesIdList(palacesIds);

        Page<GzfAccount> page =
          fee2(gzfAccount, pageAccount, startDate, endDate,
            PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT);

        model.addAttribute("page", page);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("villageId", villageId);
        model.addAttribute("palacesId", palacesId);

        return "modules/gzf/gzfRportPropertyFee";
    }

    /**
     * 缴费报表 -- 总览
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/fee/total")
    @ResponseBody
    public List<FeeReportVO> feeTotal(HttpServletRequest request, HttpServletResponse response,
      Model model) {
        User user = UserUtils.getUser();

        Page<GzfAccount> pageAccount = new Page<>(request, response);

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");

        String title = "";

        return feeReport(palacesId, villageId, startDate, endDate, pageAccount,
          0, title);
    }


    /**
     * 房租缴费总览（报表)
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/fee/rent")
    @ResponseBody
    public List<FeeReportVO> feeHouseRent(HttpServletRequest request, HttpServletResponse response,
      Model model) {
        User user = UserUtils.getUser();

        Page<GzfAccount> pageAccount = new Page<>(request, response);

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");

        String title = "房租";

        return feeReport(palacesId, villageId, startDate, endDate, pageAccount,
          PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT, title);
    }

    /**
     * 水费缴费总览（报表)
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/fee/water")
    @ResponseBody
    public List<FeeReportVO> feeHouseWater(HttpServletRequest request, HttpServletResponse response,
      Model model) {
        User user = UserUtils.getUser();

        Page<GzfAccount> pageAccount = new Page<>(request, response);

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");

        String title = "水费";

        return feeReport(palacesId, villageId, startDate, endDate, pageAccount,
          PaymentConst.ACCT_ITEM_TYPE.WATER, title);
    }

    /**
     * 水费缴费总览（报表)
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/fee/electric")
    @ResponseBody
    public List<FeeReportVO> feeHouseElectric(HttpServletRequest request,
      HttpServletResponse response,
      Model model) {
        User user = UserUtils.getUser();

        Page<GzfAccount> pageAccount = new Page<>(request, response);

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");

        String title = "电费";

        return feeReport(palacesId, villageId, startDate, endDate, pageAccount,
          PaymentConst.ACCT_ITEM_TYPE.ELECTRICITY, title);
    }

    /**
     * 物业缴费总览（报表)
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/fee/property")
    @ResponseBody
    public List<FeeReportVO> feeHouseProperty(HttpServletRequest request,
      HttpServletResponse response,
      Model model) {
        User user = UserUtils.getUser();

        Page<GzfAccount> pageAccount = new Page<>(request, response);

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");

        String title = "物业费";

        return feeReport(palacesId, villageId, startDate, endDate, pageAccount,
          PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT, title);
    }

    private List<String> getPalacesListFromParam(String villageId, String palacesId,
      String startDate, String endDate) {
        List<String> palacesIds = new ArrayList<>();
        if (palacesId != null && !palacesId.equals("0")) {
            palacesIds.add(palacesId);
        } else {
            if (villageId != null && !villageId.equals("0")) {
                GzfPalaces gzfPalaces = new GzfPalaces();
                gzfPalaces.setGzfVillageId(villageId);
                List<GzfPalaces> gzfPalacesList = gzfPalacesService.findList(gzfPalaces);
                if (gzfPalacesList.size() > 0) {
                    for (GzfPalaces gzfPalaces1 : gzfPalacesList) {
                        String palacesTmpId = gzfPalaces1.getId();
                        if (!palacesTmpId.equals("")) {
                            palacesIds.add(palacesTmpId);
                        }
                    }
                }
            }
        }
        if (palacesIds.size() < 1) {
            palacesIds = null;
        }
        return palacesIds;
    }

    /**
     * 根据类型获取缴费数据
     *
     * @param palacesId
     * @param villageId
     * @param startDate
     * @param endDate
     * @param pageAccount
     * @param type
     * @return
     */
    public List<FeeReportVO> feeReport(String palacesId, String villageId, String startDate,
      String endDate, Page<GzfAccount> pageAccount, int type, String title) {
        List<FeeReportVO> result = new ArrayList<>();
        List<GzfVillage> gzfVillageList = gzfVillageService.findAllList(null);

        List<String> palacesIds = new ArrayList<>();
        List<String> villageIds = new ArrayList<>();

        Map<String, List<GzfAccount>> totalAccountMap = new HashMap<>();
        Map<String, List<GzfAccount>> rentAccountMap = new HashMap<>();

        int extendDay = 15;
        String key0 = "day_" + extendDay; //  宽限期
        String title0 = "宽限期内未缴费";

        String title1 = "宽限期外未缴费";

        ReportBO reportBO = null;
        List<ReportBO> reportBOs = new ArrayList<>();
        List<GzfAccount> extendFeeAccount = new ArrayList<>();
        List<GzfAccount> beyondExtendFeeAccount = new ArrayList<>();

        int byFlag = 0;
        if (palacesId != null && !palacesId.equals("0") && (villageId == null || villageId
          .equals("0"))) {
            // 按照苑来统计（指定苑）
            byFlag = 1;
            palacesIds.add(palacesId);
        } else {
            if (villageId != null && !villageId.equals("0")) {
                // 按照小区来统计（所有苑）
                byFlag = 2;
                villageIds.add(villageId);
                List<GzfPalaces> gzfPalacesList = getGzfPalacesByConditions(villageId);
                List<String> gzfPalacesIdListTmp = new ArrayList<>();
                for (int i = 0; i < gzfPalacesList.size(); i++) {
                    palacesIds = new ArrayList<>();
                    reportBO = new ReportBO();
                    String palacesIdTmp = gzfPalacesList.get(i).getId();
                    palacesIds.add(palacesIdTmp);
                    gzfPalacesIdListTmp.add(palacesIdTmp);
                    GzfAccount gzfAccount = new GzfAccount();
                    gzfAccount.setPalacesIdList(palacesIds);
                    Page<GzfAccount> gzfAccountPage =
                      fee2(gzfAccount, pageAccount, startDate, endDate, type);
                    List<GzfAccount> gzfAccountList = gzfAccountPage.getList();
                    List<GzfAccount> rentAccountList = new ArrayList<>();
                    Iterator iterator = gzfAccountList.iterator();
                    while (iterator.hasNext()) {
                        GzfAccount gzfAccount1 = (GzfAccount) iterator.next();
                        if (gzfAccount1.isOweMoney()) {
                            rentAccountList.add(gzfAccount1);
                        }
                    }
                    totalAccountMap.put(palacesIdTmp, gzfAccountList);
                    rentAccountMap.put(palacesIdTmp, rentAccountList);
                }
            } else {
                // 按照所有小区（所有小区）
                if (gzfVillageList != null) {
                    Map<String, List<GzfPalaces>> map = new HashMap<>();
                    for (int i = 0; i < gzfVillageList.size(); i++) {
                        String villId = gzfVillageList.get(i).getId();
                        List<String> gzfPalacesIdListTmp = new ArrayList<>();
                        villageIds.add(villId);
                        List<GzfAccount> gzfAccountList = new ArrayList<>();
                        List<GzfAccount> rentAccountList = new ArrayList<>();

                        List<GzfPalaces> gzfPalacesListTmp = getGzfPalacesByConditions(villId);
                        for (int j = 0; j < gzfPalacesListTmp.size(); j++) {
                            gzfPalacesIdListTmp.add(gzfPalacesListTmp.get(j).getId());
                        }
                        List<GzfHouseInfo> gzfHouseInfos = new ArrayList<>();
                        List<GzfHousePerson> gzfHousePersons = new ArrayList<>();
                        if (gzfPalacesIdListTmp.size() > 0) {
                            GzfAccount gzfAccount = new GzfAccount();
                            gzfAccount.setPalacesIdList(gzfPalacesIdListTmp);
                            Page<GzfAccount> gzfAccountPage =
                              fee2(gzfAccount, pageAccount, startDate, endDate, type);
                            gzfAccountList = gzfAccountPage.getList();
                            Iterator iterator = gzfAccountList.iterator();
                            while (iterator.hasNext()) {
                                GzfAccount gzfAccount1 = (GzfAccount) iterator.next();
                                if (gzfAccount1.isOweMoney()) {
                                    rentAccountList.add(gzfAccount1);
                                }
                            }
                        }
                        totalAccountMap.put(villId, gzfAccountList);
                        rentAccountMap.put(villId, rentAccountList);
                    }
                }
            }
        }

        Map<String, List<ReportBO>> reportsMap = new HashMap<>();

        FeeReportVO reportVO = null;

        if (byFlag == 0) {
            reportVO = new FeeReportVO();
            reportVO.setTitle(title + "缴费报表（按小区统计-点击查看详情）");
            reportVO.setName("缴费账号总数");
            String[] xAis = new String[totalAccountMap.size()];
            String[] data = new String[totalAccountMap.size()];
            Iterator iter = totalAccountMap.entrySet().iterator();
            int i = 0;
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String villId = (String) entry.getKey();
                String villName = gzfVillageService.get(villId).getName();
                List<GzfAccount> gzfAccountList = (List<GzfAccount>) entry.getValue();
                xAis[i] = villName;
                data[i] = "" + gzfAccountList.size();
                i++;
            }
            reportVO.setData(data);
            reportVO.setxAxis(xAis);
            result.add(reportVO);

            reportVO = new FeeReportVO();
            reportVO.setName("未缴费总数");
            data = new String[rentAccountMap.size()];
            iter = rentAccountMap.entrySet().iterator();
            i = 0;
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                List<GzfAccount> gzfAccountList = (List<GzfAccount>) entry.getValue();
                data[i] = "" + gzfAccountList.size();
                i++;
                for (GzfAccount gzfAccount1 : gzfAccountList) {
                    // 判断是否在宽限期内缴费
                    if (gzfAccount1.getOverDay() <= extendDay) {
                        extendFeeAccount.add(gzfAccount1);
                    } else if (gzfAccount1.getOverDay() > extendDay) {
                        beyondExtendFeeAccount.add(gzfAccount1);
                    } else {

                    }
                }
            }
            reportBO = new ReportBO();
            reportBO.setTitle(title0);
            reportBO.setValue(extendFeeAccount.size() + "");
            reportBOs.add(reportBO);

            reportBO = new ReportBO();
            reportBO.setTitle(title1);
            reportBO.setValue(beyondExtendFeeAccount.size() + "");

            reportBOs.add(reportBO);
            reportVO.setData(data);
            reportVO.setReportBOList(reportBOs);
            result.add(reportVO);

        } else if (byFlag == 2) {
            reportVO = new FeeReportVO();
            reportVO.setTitle(title + "缴费报表（按苑统计-点击查看详情）");
            reportVO.setName("缴费账号总数");
            String[] xAis = new String[totalAccountMap.size()];
            String[] data = new String[totalAccountMap.size()];
            Iterator iter = totalAccountMap.entrySet().iterator();
            int i = 0;
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String palaceId = (String) entry.getKey();
                String palaceName = gzfPalacesService.get(palaceId).getName();
                List<GzfAccount> gzfAccountList = (List<GzfAccount>) entry.getValue();
                xAis[i] = palaceName;
                data[i] = "" + gzfAccountList.size();
                i++;
            }
            reportVO.setData(data);
            reportVO.setxAxis(xAis);
            result.add(reportVO);

            reportVO = new FeeReportVO();
            reportVO.setName("未缴费总数");
            data = new String[rentAccountMap.size()];
            iter = rentAccountMap.entrySet().iterator();
            i = 0;
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                List<GzfAccount> gzfAccountList = (List<GzfAccount>) entry.getValue();
                data[i] = "" + gzfAccountList.size();
                i++;
                for (GzfAccount gzfAccount1 : gzfAccountList) {
                    // 判断是否在宽限期内缴费
                    if (gzfAccount1.getOverDay() <= extendDay) {
                        extendFeeAccount.add(gzfAccount1);
                    } else if (gzfAccount1.getOverDay() > extendDay) {
                        beyondExtendFeeAccount.add(gzfAccount1);
                    } else {

                    }
                }
            }
            reportBO = new ReportBO();
            reportBO.setTitle(title0);
            reportBO.setValue(extendFeeAccount.size() + "");
            reportBOs.add(reportBO);

            reportBO = new ReportBO();
            reportBO.setTitle(title1);
            reportBO.setValue(beyondExtendFeeAccount.size() + "");
            reportBOs.add(reportBO);

            reportVO.setData(data);
            reportVO.setReportBOList(reportBOs);
            result.add(reportVO);
        }
        return result;
    }

    /**
     * 根据条件获取苑数据
     *
     * @param villageId
     * @return
     */
    public List<GzfPalaces> getGzfPalacesByConditions(String villageId) {
        GzfPalaces gzfPalaces = new GzfPalaces();
        gzfPalaces.setGzfVillageId(villageId);
        return gzfPalacesService.findList(gzfPalaces);
    }

    /**
     * 缴费账号列表
     *
     * @param gzfAccount
     * @param pageAccount
     * @param type
     * @return
     */
    public Page<GzfAccount> fee(GzfAccount gzfAccount,
      Page<GzfAccount> pageAccount, int type) {
        Page<GzfAccount> page = accountService.findAllList(gzfAccount, pageAccount);
        if (page.getList() != null && page.getList().size() > 0) {
            GzfAccountBalance balanceTemp = new GzfAccountBalance();
            List<GzfAccount> accountList = page.getList();
            for (int i = 0; i < accountList.size(); i++) {
                GzfAccount account = accountList.get(i);
                if (account != null) {
                    GzfHousePerson housePerson = getHousePerson(account);
                    if (housePerson == null) {
                        account.setUpdateDate(null);
                        continue;
                    }
                    Date feeExpireDate = balanceService.getFeeExpireDate(housePerson,
                      type);
                    long now = System.currentTimeMillis();
                    boolean isOwnMoney = now > feeExpireDate.getTime() ? true : false;
                    Integer overDay = 0;
                    if (isOwnMoney) {
                        overDay = (int) Math
                          .ceil((double) (now - feeExpireDate.getTime()) / 1000 / 3600 / 24);
                    }
                    account.setHousePerson(housePerson);
                    account.setUpdateDate(feeExpireDate);
                    account.setOweMoney(isOwnMoney);
                    balanceTemp.setAccountId(account.getId());
                    account.setOverDay(overDay);
                    balanceTemp.setSpePaymentId(PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT);
                    int total = 0;
                    List<GzfAccountBalance> balances = balanceService.findList(balanceTemp);
                    if (balances != null && balances.size() > 0) {
                        for (int j = 0; j < balances.size(); j++) {
                            total += balances.get(j).getBalance();
                        }
                    }
                    account.setFee((double) total);
                }
            }
        }
        return page;

    }

    /**
     * 缴费账号列表 -- 根据账单查询
     *
     * @param gzfAccount
     * @param pageAccount
     * @param startDate
     * @param endDate
     * @param type
     * @return
     */
    public Page<GzfAccount> fee2(GzfAccount gzfAccount,
      Page<GzfAccount> pageAccount, String startDate, String endDate, int type) {
        Page<GzfAccount> page = accountService.findAllList(gzfAccount, pageAccount);
        Date startTime = null;
        Date endTime = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (startDate != null) {
            startDate += " 00:00:00";
            try {
                startTime = simpleDateFormat.parse(
                  startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (endDate != null) {
            endDate += " 23:59:59";
            try {
                endTime = simpleDateFormat.parse(
                  endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (page.getList() != null && page.getList().size() > 0) {
            GzfAccountBalance balanceTemp = new GzfAccountBalance();
            List<GzfAccount> accountList = page.getList();
            GzfAcctItem gzfAcctItem;
            for (int i = 0; i < accountList.size(); i++) {
                GzfAccount account = accountList.get(i);
                gzfAcctItem = new GzfAcctItem();
                if (account != null) {
                    GzfHousePerson housePerson = getHousePerson(account);
                    if (housePerson == null) {
                        account.setUpdateDate(null);
                        continue;
                    }
                    gzfAcctItem.setAccountId(account.getId());
                    gzfAcctItem.setAcctItemTypeId(type);
                    gzfAcctItem.setStatisStartDate(startTime);
                    gzfAcctItem.setStatisEndDate(endTime);
                    Date feeExpireDate = balanceService.getFeeExpireDate(housePerson,
                      type);
                    List<GzfAcctItem> gzfAcctItemList = gzfAcctItemService.customGet(gzfAcctItem);
                    int gzfAcctItemListSize = gzfAcctItemList.size();
                    long now = System.currentTimeMillis();
                    boolean isOwnMoney = now > feeExpireDate.getTime() ? true : false;
                    isOwnMoney = gzfAcctItemListSize > 0 ? true : false;
                    Integer overDay = 0;
                    if (isOwnMoney) {
                        overDay = (int) Math
                          .ceil((double) (now - feeExpireDate.getTime()) / 1000 / 3600 / 24);
                        overDay = (int) Math
                          .ceil(
                            (double) (now - gzfAcctItemList.get(0).getCreateDate().getTime()) / 1000
                              / 3600 / 24);
                    }
                    account.setHousePerson(housePerson);
                    account.setUpdateDate(feeExpireDate);
                    account.setOweMoney(isOwnMoney);
                    balanceTemp.setAccountId(account.getId());
                    account.setOverDay(overDay);
                    balanceTemp.setSpePaymentId(type);
                    int total = 0;
                    List<GzfAccountBalance> balances = balanceService.findList(balanceTemp);
                    if (balances != null && balances.size() > 0) {
                        for (int j = 0; j < balances.size(); j++) {
                            total += balances.get(j).getBalance();
                        }
                    }
                    if (isOwnMoney) {
                        GzfAcctItem lastGzfAccItem = gzfAcctItemList.get(gzfAcctItemListSize - 1);
                        account
                          .setFee(lastGzfAccItem.getReceAmount() - lastGzfAccItem.getFactAmount());
                        account.setUpdateDate(lastGzfAccItem.getEndDate());
                    } else {
                        account.setFee(0d);
                    }
                }
            }
        }
        return page;

    }

    public GzfHousePerson getHousePerson(GzfAccount account) {
        GzfHousePerson housePerson = housePersonService.get(account.getCustId());
        if (housePerson == null) {
            return null;
        }
        //住房信息
        GzfHouseInfo houseInfo = houseInfoService.get(housePerson.getGzfHouseInfoId());
        if (houseInfo != null) {
            houseInfo.setGzfPalaces(placesService.get(houseInfo.getGzfPalacesId()));
        }
        housePerson.setGzfHouseInfo(houseInfo);
        return housePerson;
    }
}
