package com.paopao.hzgzf.modules.gzf.web;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.EhCacheUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.bo.*;
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
import com.paopao.hzgzf.modules.sys.entity.*;
import com.paopao.hzgzf.modules.sys.service.HonorWallService;
import com.paopao.hzgzf.modules.sys.service.OfficeService;
import com.paopao.hzgzf.modules.sys.utils.AreaUtils;
import com.paopao.hzgzf.modules.sys.utils.DictUtils;
import com.paopao.hzgzf.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by FastLane on 2016-01-10.
 * <p/>
 * 系统后台首页
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/main")
public class GzfMainController extends BaseController {

    @Autowired
    GzfHouseInfoService gzfHouseInfoService;

    @Autowired
    OfficeService officeService;

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

    @Autowired
    HonorWallService honorWallService;


    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = UserUtils.getUser();

        //首页显示当前操作人的区域信息
        Area areaList = user.getOffice().getArea();

        model.addAttribute("areaList", areaList);

        //首页显示系统中的荣誉墙，只显示5个
        List<HonorWall> honorwallList = honorWallService.findAll();

        model.addAttribute("honorwallList", honorwallList);

        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        String flushCache = request.getParameter("flushCache");
        boolean isFlushCache = (flushCache != null && Integer.parseInt(flushCache) == 1) ? true : false;

        // basic report
        List<MainBaseReportBO> mainBaseReportBOs;
        Object object = EhCacheUtils.get(ReportDictConstant.DICT_CACHE_REPORT,
                ReportDictConstant.DICT_CACHE_KEY_MAIN_BASE_REPORT);

        if (object == null || isFlushCache) {
            mainBaseReportBOs =
                    mainBaseReport(villageId, palacesId, startDate, endDate);
            EhCacheUtils.put(ReportDictConstant.DICT_CACHE_REPORT,
                    ReportDictConstant.DICT_CACHE_KEY_MAIN_BASE_REPORT, mainBaseReportBOs);
        } else {
            mainBaseReportBOs = (List<MainBaseReportBO>) object;
        }
        model.addAttribute("mainBaseReport", mainBaseReportBOs);

        // Rent
        ReportBO rentReport;
        object = EhCacheUtils.get(ReportDictConstant.DICT_CACHE_REPORT,
                ReportDictConstant.DICT_CACHE_KEY_RENT_REPORT);
        if (object == null || isFlushCache) {
            rentReport = getRentReport(villageId, palacesId, startDate, endDate);
            EhCacheUtils.put(ReportDictConstant.DICT_CACHE_REPORT,
                    ReportDictConstant.DICT_CACHE_KEY_RENT_REPORT, rentReport);
        } else {
            rentReport = (ReportBO) object;
        }
        model.addAttribute("rentReport", rentReport);

        // Property
        ReportBO propertyReport;
        object = EhCacheUtils.get(ReportDictConstant.DICT_CACHE_REPORT,
                ReportDictConstant.DICT_CACHE_KEY_PROPERTY_REPORT);
        if (object == null || isFlushCache) {
            propertyReport = getPropertyReport(villageId, palacesId, startDate, endDate);
            EhCacheUtils.put(ReportDictConstant.DICT_CACHE_REPORT,
                    ReportDictConstant.DICT_CACHE_KEY_PROPERTY_REPORT, propertyReport);
        } else {
            propertyReport = (ReportBO) object;
        }
        model.addAttribute("propertyReport", propertyReport);

        // Water&Electricity
        ReportBO waterAndElecReport;
        object = EhCacheUtils.get(ReportDictConstant.DICT_CACHE_REPORT,
                ReportDictConstant.DICT_CACHE_KEY_WATER_ELECTIC_REPORT);
        if (object == null || isFlushCache) {
            waterAndElecReport = getWaterAndElecReport(villageId, palacesId, startDate, endDate);
            EhCacheUtils.put(ReportDictConstant.DICT_CACHE_REPORT,
                    ReportDictConstant.DICT_CACHE_KEY_WATER_ELECTIC_REPORT, waterAndElecReport);
        } else {
            waterAndElecReport = (ReportBO) object;
        }
        model.addAttribute("waterAndElecReport", waterAndElecReport);

        // alert
        Office office = officeService.get(user.getOffice());
        boolean showAlert = false;
        if (Integer.valueOf(office.getGrade()) >= 3) {
            showAlert = true;
        }
        model.addAttribute("showAlert", showAlert);

        return "modules/gzf/main";
    }

    @RequestMapping("areaList")
    @ResponseBody
    public List<Area> getAreaListJsonByPid(String pid) {
        List<Area> areaList = AreaUtils.getAreaListByPids(pid);
        return areaList;
    }

    public List<MainBaseReportBO> mainBaseReport(String villageId, String palacesId,
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
        List<MainBaseReportBO> result = new ArrayList<>();

        String houseKey = "home";
        String houseTitle = "总房屋数";
        String houseValue = "";
        String houseClassName = "blue";
        String houseUrl = "/gzf/gzfReport/house";
        MainBaseReportBO houseReport = new MainBaseReportBO(houseKey, houseTitle, houseValue,
                houseClassName, houseUrl);
        List<GzfHouseInfo> houseList =
                gzfHouseInfoService.findAllList(null, startDate, endDate, palacesIds, null, null);
        if (houseList.size() == 0) {
            houseList.add(null);
        }
        houseReport.setValue("" + houseList.size());
        result.add(houseReport);

        String personKey = "group";
        String personTitle = "入住人员1";
        String personValue = "";
        String personClassName = "green";
        String personUrl = "/gzf/gzfReport/person";
        MainBaseReportBO personReport = new MainBaseReportBO(personKey, personTitle, personValue,
                personClassName, personUrl);
        GzfHousePerson gzfHousePerson = new GzfHousePerson();
        gzfHousePerson.setPalacesIds(palacesIds);
        if (startDate != null) {
            try {
                Date startTime = DateFormat.getDateInstance().parse(startDate);
                gzfHousePerson.setStartTime(startTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        if (endDate != null) {
            try {
                Date endTime = DateFormat.getDateInstance().parse(endDate);
                gzfHousePerson.setStartTime(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        List<GzfHousePerson> personList = housePersonService.findList(gzfHousePerson);
        personReport.setValue("" + personList.size());
        result.add(personReport);

        int totalAccount = 0;
        int totalNotFeeAccount = 0;
        Page<GzfAccount> pageAccount = new Page<>();
        List<FeeReportVO> reportVOs = feeReport(palacesId, villageId, startDate,
                endDate,
                pageAccount,
                PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT, "");
        Iterator<FeeReportVO> iterator = reportVOs.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            ReportVO reportVO = iterator.next();
            String[] data = reportVO.getData();
            for (int j = 0; j < data.length; j++) {
                if (i == 0) {
                    totalAccount += Integer.parseInt(data[j]);
                } else if (i == 1) {
                    totalNotFeeAccount += Integer.parseInt(data[j]);
                }
            }
            i++;
        }
        String houseFeeRateKey = "jpy";
        String houseFeeRateTitle = "房租缴费率1";
        String houseFeeRateValue;
        if (totalAccount == 0) {
            houseFeeRateValue = "0.00";
        } else {
            houseFeeRateValue = String.format("%.2f",
                    (float) ((totalAccount - totalNotFeeAccount) * 100 / totalAccount)) + "%";
        }
        String houseFeeRateClassName = "purple";
        String houseFeeUrl = "/gzf/gzfReport/fee";
        MainBaseReportBO houseFeeRateReport = new MainBaseReportBO(houseFeeRateKey,
                houseFeeRateTitle, houseFeeRateValue, houseFeeRateClassName, houseFeeUrl);
        result.add(houseFeeRateReport);

        String houseRateKey = "bar-chart";
        String houseRateTitle = "房屋出租率";
        String houseRateValue = String.format("%.1f",
                (float) (personList.size() * 100 / houseList.size()))
                + "%";
        String houseRateClassName = "yellow";
        String houseRateUrl = "/gzf/gzfReport/house";
        MainBaseReportBO houseRateReport = new MainBaseReportBO(houseRateKey, houseRateTitle,
                houseRateValue, houseRateClassName, houseRateUrl);
        result.add(houseRateReport);

        return result;
    }

    @RequestMapping("personReport")
    @ResponseBody
    public List<ReportBO> getPersonReport() {
        List<ReportBO> reportBOs = new ArrayList<>();
        List<Dict> dicts = DictUtils.getDictList(ReportDictConstant.DICT_TYPE_EDUCATION);
        ReportBO reportBO = null;
        Iterator iterator = dicts.iterator();
        while (iterator.hasNext()) {
            Dict dict = (Dict) iterator.next();
            reportBO = new ReportBO();
            reportBO.setKey(dict.getValue());
            reportBO.setTitle(dict.getLabel());
            reportBO.setValue("" + Math.floor(Math.random() * 100));
            reportBO.setRate(Float.valueOf(String.format("%.2f", (float) Math.random() * 100)));
            reportBOs.add(reportBO);
        }
        return reportBOs;
    }

    @RequestMapping("feeReport")
    @ResponseBody
    public List<ReportBO> getFeeReport(HttpServletRequest request, HttpServletResponse response) {
        List<ReportBO> reportBOs = new ArrayList<>();
        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        reportBOs.add(getRentReport(villageId, palacesId, startDate, endDate));
        reportBOs.add(getPropertyReport(villageId, palacesId, startDate, endDate));
        reportBOs.add(getWaterAndElecReport(villageId, palacesId, startDate, endDate));
        return reportBOs;
    }

    public ReportBO getRentReport(String villageId, String palacesId,
                                  String startDate, String endDate) {
        ReportBO reportBOs = new ReportBO("房租缴费", "rent", "89");
        int totalAccount = 0;
        int totalNotFeeAccount = 0;
        Page<GzfAccount> pageAccount = new Page<>();
        List<FeeReportVO> reportVOs = feeReport(palacesId, villageId, startDate,
                endDate,
                pageAccount,
                PaymentConst.ACCT_ITEM_TYPE.HOUSE_RENT, "");
        Iterator<FeeReportVO> iterator = reportVOs.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            ReportVO reportVO = iterator.next();
            String[] data = reportVO.getData();
            for (int j = 0; j < data.length; j++) {
                if (i == 0) {
                    totalAccount += Integer.parseInt(data[j]);
                } else if (i == 1) {
                    totalNotFeeAccount += Integer.parseInt(data[j]);
                }
            }
            i++;
        }
        String houseFeeRateValue;
        if (totalAccount == 0) {
            houseFeeRateValue = "0.00";
        } else {
            houseFeeRateValue = String.format("%.2f",
                    (float) ((totalAccount - totalNotFeeAccount) * 100 / totalAccount));
        }
        reportBOs.setValue(houseFeeRateValue);
        return reportBOs;
    }

    public ReportBO getPropertyReport(String villageId, String palacesId,
                                      String startDate, String endDate) {
        ReportBO reportBOs = new ReportBO("物业缴费", "rent", "80");
        int totalAccount = 0;
        int totalNotFeeAccount = 0;
        Page<GzfAccount> pageAccount = new Page<>();
        List<FeeReportVO> reportVOs = feeReport(palacesId, villageId, startDate,
                endDate,
                pageAccount,
                PaymentConst.ACCT_ITEM_TYPE.MANAGEMENT, "");
        Iterator<FeeReportVO> iterator = reportVOs.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            ReportVO reportVO = iterator.next();
            String[] data = reportVO.getData();
            for (int j = 0; j < data.length; j++) {
                if (i == 0) {
                    totalAccount += Integer.parseInt(data[j]);
                } else if (i == 1) {
                    totalNotFeeAccount += Integer.parseInt(data[j]);
                }
            }
            i++;
        }
        String houseFeeRateValue;
        if (totalAccount == 0) {
            houseFeeRateValue = "0.00";
        } else {
            houseFeeRateValue = String.format("%.2f",
                    (float) ((totalAccount - totalNotFeeAccount) * 100 / totalAccount));
        }
        reportBOs.setValue(houseFeeRateValue);
        return reportBOs;
    }

    public ReportBO getWaterAndElecReport(String villageId, String palacesId,
                                          String startDate, String endDate) {
        ReportBO reportBOs = new ReportBO("水电缴费", "rent", "99");
        int totalAccount = 0;
        int totalNotFeeAccount = 0;
        Page<GzfAccount> pageAccount = new Page<>();
        List<FeeReportVO> reportVOs = feeReport(palacesId, villageId, startDate,
                endDate,
                pageAccount,
                PaymentConst.ACCT_ITEM_TYPE.WATER, "");
        Iterator<FeeReportVO> iterator = reportVOs.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            ReportVO reportVO = iterator.next();
            String[] data = reportVO.getData();
            for (int j = 0; j < data.length; j++) {
                if (i == 0) {
                    totalAccount += Integer.parseInt(data[j]);
                } else if (i == 1) {
                    totalNotFeeAccount += Integer.parseInt(data[j]);
                }
            }
            i++;
        }
        String houseFeeRateValue;
        if (totalAccount == 0) {
            houseFeeRateValue = "0.00";
        } else {
            houseFeeRateValue = String.format("%.2f",
                    (float) ((totalAccount - totalNotFeeAccount) * 100 / totalAccount));
        }
        reportBOs.setValue(houseFeeRateValue);
        return reportBOs;
    }

    public List<Area> getAreaListByPid(String pid) {
        List<Area> areaList = AreaUtils.getAreaListByPids(pid);
        return areaList;
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
