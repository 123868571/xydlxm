package com.paopao.hzgzf.modules.gzf.web;

import com.paopao.hzgzf.common.utils.CacheUtils;
import com.paopao.hzgzf.common.utils.DateUtils;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.gzf.bo.*;
import com.paopao.hzgzf.modules.gzf.entity.*;
import com.paopao.hzgzf.modules.gzf.service.*;
import com.paopao.hzgzf.modules.sys.entity.Dict;
import com.paopao.hzgzf.modules.sys.entity.User;
import com.paopao.hzgzf.modules.sys.utils.DictUtils;
import com.paopao.hzgzf.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Created by FastLane on 2016-01-07.
 */
@Controller
@RequestMapping(value = "${adminPath}/gzf/gzfReport")
public class GzfReportController extends BaseController {

    @Autowired
    GzfHousePersonService housePersonService;

    @Autowired
    GzfVillageService gzfVillageService;

    @Autowired
    GzfHouseInfoService gzfHouseInfoService;

    @Autowired
    GzfRepairProjectService gzfRepairProjectService;

    @Autowired
    GzfPalacesService gzfPalacesService;

    @Autowired
    GzfBuildingService gzfBuildingService;

    @Autowired
    GzfRepairManagementService gzfRepairManagementService;

    /**
     * 房源报表页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/house")
    public String house(HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = UserUtils.getUser();
        List<GzfVillage> gzfVillageList = gzfVillageService.findAllList(null);
        model.addAttribute("gzfVillageList", gzfVillageList);
        return "modules/gzf/gzfRportHouse";
    }

    /**
     * 房源报表之房屋按年出租&退租报表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("houseReport/building")
    @ResponseBody
    public List getHouseReportBuild(
      HttpServletRequest request,
      HttpServletResponse response) {
        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");
        String year = request.getParameter("year");
        List<ReportBO> reportBOs = new ArrayList<>();
        List<String> palacesIds = new ArrayList<>();
        List<String> villageIds = new ArrayList<>();
        GzfBuilding gzfBuilding = new GzfBuilding();
        List<GzfVillage> gzfVillageList = gzfVillageService.findAllList(null);

        return houseReportByYear(villageId, palacesId, year, gzfVillageList);
    }

    @RequestMapping("houseReport/house")
    @ResponseBody
    public List<ReportVO> getHouseReport(HttpServletRequest request,
      HttpServletResponse response) {
        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        if (endDate != null) {
            endDate += " 23:59:59";
        }
        if (startDate != null){
            startDate += " 00:00:00";
        }
        List<GzfVillage> gzfVillageList = gzfVillageService.findAllList(null);
        return houseReport(villageId, palacesId, startDate, endDate, gzfVillageList);
    }

    /**
     * 房源报表
     *
     * @param villageId
     * @param palacesId
     * @param startDate
     * @param endDate
     * @return
     */
    public List<ReportVO> houseReport(String villageId, String palacesId,
      String startDate, String endDate, List<GzfVillage> gzfVillageList) {

        List<String> palacesIds = new ArrayList<>();
        List<String> villageIds = new ArrayList<>();
        GzfBuilding gzfBuilding = new GzfBuilding();
        Map<String, List<GzfHouseInfo>> villageGzfHouseMap = new HashMap<>();
        Map<String, List<GzfHouseInfo>> palaceGzfHouseMap = new HashMap<>();

        Map<String, List<GzfHouseInfo>> totalHouseMap = new HashMap<>();
        Map<String, List<GzfHousePerson>> rentHouseMap = new HashMap<>();

        int byFlag = 0;
        if (palacesId != null && !palacesId.equals("0") && (villageId == null || villageId
          .equals("0"))) {
            // 按照苑来统计（指定苑）
            byFlag = 1;
            palacesIds.add(palacesId);
            gzfBuilding.setGzfPalaceList(palacesIds);
        } else {
            if (villageId != null && !villageId.equals("0")) {
                // 按照小区来统计（所有苑）
                byFlag = 2;
                villageIds.add(villageId);
                List<GzfPalaces> gzfPalacesList = getGzfPalacesByConditions(villageId);
                List<String> gzfPalacesIdListTmp = new ArrayList<>();
                for (int i = 0; i < gzfPalacesList.size(); i++) {
                    String palacesIdTmp = gzfPalacesList.get(i).getId();
                    gzfPalacesIdListTmp.add(palacesIdTmp);
                    List<GzfHouseInfo> gzfHouseInfos =
                      getGzfHouseInfoByConditions(null, gzfPalacesIdListTmp, startDate, endDate,
                        byFlag);
                    List<GzfHousePerson> gzfHousePersons =
                      getGzfHousePersonByConditions(gzfPalacesIdListTmp, startDate, endDate, 0);
                    totalHouseMap.put(palacesIdTmp, gzfHouseInfos);
                    rentHouseMap.put(palacesIdTmp, gzfHousePersons);
                }
                gzfBuilding.setGzfVillages(villageIds);
            } else {
                // 按照所有小区（所有小区）
                if (gzfVillageList != null) {
                    Map<String, List<GzfPalaces>> map = new HashMap<>();
                    for (int i = 0; i < gzfVillageList.size(); i++) {
                        String villId = gzfVillageList.get(i).getId();
                        List<String> gzfPalacesIdListTmp = new ArrayList<>();
                        villageIds.add(villId);
                        List<GzfPalaces> gzfPalacesListTmp = getGzfPalacesByConditions(villId);
                        for (int j = 0; j < gzfPalacesListTmp.size(); j++) {
                            gzfPalacesIdListTmp.add(gzfPalacesListTmp.get(j).getId());
                        }
                        List<GzfHouseInfo> gzfHouseInfos = new ArrayList<>();
                        List<GzfHousePerson> gzfHousePersons = new ArrayList<>();
                        if (gzfPalacesIdListTmp.size() > 0) {
                            gzfHouseInfos =
                              getGzfHouseInfoByConditions(null, gzfPalacesIdListTmp, startDate,
                                endDate,
                                byFlag);
                            gzfHousePersons = getGzfHousePersonByConditions(
                              gzfPalacesIdListTmp, startDate, endDate, 0);
                        }
                        totalHouseMap.put(villId, gzfHouseInfos);
                        rentHouseMap.put(villId, gzfHousePersons);
                    }
                }
                palacesIds = null;
            }
        }
        List<ReportVO> result = new ArrayList<>();
        Map<String, List<ReportBO>> reportsMap = new HashMap<>();
        String totalKey = "total";
        String rentedKey = "rent";

        String houseKey = "home";
        String houseTitle = "总房屋数";
        String houseValue = "";
        String houseClassName = "blue";
        String houseUrl = "/gzf/gzfReport/house";
        List<GzfHouseInfo> houseList = null;
        ReportVO reportVO = null;
        if (byFlag == 0) {
            reportVO = new ReportVO();
            reportVO.setTitle("房源报表（按小区统计）");
            reportVO.setName("房屋总数");
            String[] xAis = new String[totalHouseMap.size()];
            String[] data = new String[totalHouseMap.size()];
            Iterator iter = totalHouseMap.entrySet().iterator();
            int i = 0;
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String villId = (String) entry.getKey();
                String villName = gzfVillageService.get(villId).getName();
                List<GzfHouseInfo> gzfHouseInfos = (List<GzfHouseInfo>) entry.getValue();
                xAis[i] = villName;
                data[i] = "" + gzfHouseInfos.size();
                i++;
            }
            reportVO.setData(data);
            reportVO.setxAxis(xAis);
            result.add(reportVO);

            reportVO = new ReportVO();
            reportVO.setName("已出租房屋总数");
            data = new String[rentHouseMap.size()];
            iter = rentHouseMap.entrySet().iterator();
            i = 0;
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                List<GzfHousePerson> gzfHousePersons = (List<GzfHousePerson>) entry.getValue();
                data[i] = "" + gzfHousePersons.size();
                i++;
            }
            reportVO.setData(data);
            result.add(reportVO);

        } else if (byFlag == 2) {
            reportVO = new ReportVO();
            reportVO.setTitle("房源报表（按苑统计）");
            reportVO.setName("房屋总数");
            String[] xAis = new String[totalHouseMap.size()];
            String[] data = new String[totalHouseMap.size()];
            Iterator iter = totalHouseMap.entrySet().iterator();
            int i = 0;
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String palaceId = (String) entry.getKey();
                String palaceName = gzfPalacesService.get(palaceId).getName();
                List<GzfHouseInfo> gzfHouseInfos = (List<GzfHouseInfo>) entry.getValue();
                xAis[i] = palaceName;
                data[i] = "" + gzfHouseInfos.size();
                i++;
            }
            reportVO.setData(data);
            reportVO.setxAxis(xAis);
            result.add(reportVO);

            reportVO = new ReportVO();
            reportVO.setName("已出租房屋总数");
            data = new String[rentHouseMap.size()];
            iter = rentHouseMap.entrySet().iterator();
            i = 0;
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                List<GzfHousePerson> gzfHousePersons = (List<GzfHousePerson>) entry.getValue();
                data[i] = "" + gzfHousePersons.size();
                i++;
            }
            reportVO.setData(data);
            result.add(reportVO);
        }

        return result;
    }

    /**
     * 房源报表（按照年份）
     *
     * @param villageId
     * @param palacesId
     * @param year
     * @param gzfVillageList
     * @return
     */
    public List<List> houseReportByYear(String villageId,
      String palacesId,
      String year, List<GzfVillage> gzfVillageList) {

        List<String> palacesIds = new ArrayList<>();
        List<String> villageIds = new ArrayList<>();
        GzfBuilding gzfBuilding = new GzfBuilding();
        Map<String, List<GzfHouseInfo>> villageGzfHouseMap = new HashMap<>();
        Map<String, List<GzfHouseInfo>> palaceGzfHouseMap = new HashMap<>();


        List<List> result = new ArrayList<>();
        Map<String, List<ReportBO>> reportsMap = new HashMap<>();
        String totalKey = "total";
        String rentedKey = "rent";

        String houseKey = "home";
        String houseTitle = "总房屋数";
        String houseValue = "";
        String houseClassName = "blue";
        String houseUrl = "/gzf/gzfReport/house";
        List<GzfHouseInfo> houseList = null;
        ReportVO reportVO = null;

        int byFlag = 0;
        if (palacesId != null && !palacesId.equals("0") && (villageId == null || villageId
          .equals("0"))) {
            // 按照苑来统计（指定苑）
            byFlag = 1;
        } else {
            if (villageId != null && !villageId.equals("0")) {
                // 按照小区来统计（所有苑）
                byFlag = 2;
            }
        }

        List list =
          getHouseTotalRentMapListByYear(byFlag, villageId, palacesId, year, gzfVillageList);

        List<Map<String, List<List<GzfHouseInfo>>>> houseTotalMapList =
          (List<Map<String, List<List<GzfHouseInfo>>>>) list.get(0);
        List<Map<String, List<List<GzfHousePerson>>>> houseRentTotalMapList =
          (List<Map<String, List<List<GzfHousePerson>>>>) list.get(1);
        List<Map<String, List<List<GzfHousePerson>>>> houseENdRentTotalMapList =
          (List<Map<String, List<List<GzfHousePerson>>>>) list.get(2);


//        List<HouseRentVO> houseTotalMapListR =
//          (List<HouseRentVO>) list.get(0);
//        List<HouseRentVO> houseRentTotalMapListR =
//          (List<HouseRentVO>) list.get(1);
//        List<HouseRentVO> houseENdRentTotalMapListR =
//          (List<HouseRentVO>) list.get(2);


        String[] xAis = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
        String[] data = new String[12];

        if (byFlag == 0) {
            for (int i = 0; i < houseTotalMapList.size(); i++) {
                List listTmp = new ArrayList();

                Map<String, List<List<GzfHouseInfo>>> houseTotalMap =
                  houseTotalMapList.get(i);
                Map<String, List<List<GzfHousePerson>>> houseRentMap =
                  houseRentTotalMapList.get(i);
                Map<String, List<List<GzfHousePerson>>> houseEndRentMap =
                  houseENdRentTotalMapList.get(i);

//                HouseRentVO houseTotalMapR = houseTotalMapListR.get(i);
//                HouseRentVO houseRentMapR = houseRentTotalMapListR.get(i);
//                HouseRentVO houseEndRentMapR = houseENdRentTotalMapListR.get(i);

                Iterator iter = houseRentMap.entrySet().iterator();

                reportVO = new ReportVO();
                reportVO.setName("出租房屋总数");
                reportVO.setTitle("房源报表（按小区统计）");
                data = new String[12];
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    List<List<GzfHousePerson>> gzfHousePersons =
                      (List<List<GzfHousePerson>>) entry.getValue();
                    String villageIdTmp = (String) entry.getKey();
                    reportVO.setTitle(gzfVillageService.get(villageIdTmp).getName());
                    for (int k = 0; k < gzfHousePersons.size(); k++) {
                        List<GzfHousePerson> gzfHouseInfos = gzfHousePersons.get(k);
                        data[k] = "" + gzfHouseInfos.size();
                    }
                }
                reportVO.setData(data);
                reportVO.setxAxis(xAis);
                listTmp.add(reportVO);

                reportVO = new ReportVO();
                reportVO.setName("退租房屋总数");
                reportVO.setTitle("房源报表（按小区统计）");
                data = new String[12];
                iter = houseEndRentMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    List<List<GzfHousePerson>> gzfHousePersons =
                      (List<List<GzfHousePerson>>) entry.getValue();
                    for (int k = 0; k < gzfHousePersons.size(); k++) {
                        List<GzfHousePerson> gzfHouseInfos = gzfHousePersons.get(k);
                        data[k] = "" + gzfHouseInfos.size();
                    }
                }
                reportVO.setData(data);
                listTmp.add(reportVO);

                data = new String[12];
                reportVO = new ReportVO();
                reportVO.setName("房屋总数");
                iter = houseTotalMap.entrySet().iterator();

                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    List<List<GzfHouseInfo>> gzfHouseInfoList =
                      (List<List<GzfHouseInfo>>) entry.getValue();
                    String villageIdTmp = (String) entry.getKey();
                    reportVO.setTitle(gzfVillageService.get(villageIdTmp).getName());
                    for (int k = 0; k < gzfHouseInfoList.size(); k++) {
                        List<GzfHouseInfo> gzfHouseInfos = gzfHouseInfoList.get(k);
                        data[k] = "" + gzfHouseInfos.size();
                    }
                }
                reportVO.setData(data);
                reportVO.setxAxis(xAis);
                listTmp.add(reportVO);

                result.add(listTmp);
            }
        } else if (byFlag == 2) {
            for (int i = 0; i < houseTotalMapList.size(); i++) {
                List listTmp = new ArrayList();
                data = new String[12];
                reportVO = new ReportVO();
                reportVO.setName("房屋总数");

                Map<String, List<List<GzfHouseInfo>>> houseTotalMap =
                  houseTotalMapList.get(i);
                Map<String, List<List<GzfHousePerson>>> houseRentMap =
                  houseRentTotalMapList.get(i);
                Map<String, List<List<GzfHousePerson>>> houseEndRentMap =
                  houseENdRentTotalMapList.get(i);

                Iterator iter = houseTotalMap.entrySet().iterator();

                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    List<List<GzfHouseInfo>> gzfHouseInfoList =
                      (List<List<GzfHouseInfo>>) entry.getValue();
                    String palaceIdTmp = (String) entry.getKey();
                    reportVO.setTitle(gzfPalacesService.get(palaceIdTmp).getName());
                    for (int k = 0; k < gzfHouseInfoList.size(); k++) {
                        List<GzfHouseInfo> gzfHouseInfos = gzfHouseInfoList.get(k);
                        data[k] = "" + gzfHouseInfos.size();
                    }
                }
                reportVO.setData(data);
                reportVO.setxAxis(xAis);
                listTmp.add(reportVO);

                reportVO = new ReportVO();
                reportVO.setName("已出租房屋总数");
                reportVO.setTitle("房源报表（按苑统计）");
                data = new String[12];
                iter = houseRentMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    List<List<GzfHousePerson>> gzfHousePersons =
                      (List<List<GzfHousePerson>>) entry.getValue();
                    for (int k = 0; k < gzfHousePersons.size(); k++) {
                        List<GzfHousePerson> gzfHouseInfos = gzfHousePersons.get(k);
                        data[k] = "" + gzfHouseInfos.size();
                    }
                }
                reportVO.setData(data);
                listTmp.add(reportVO);

                reportVO = new ReportVO();
                reportVO.setName("退租房屋总数");
                reportVO.setTitle("房源报表（按小区统计）");
                data = new String[12];
                iter = houseEndRentMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    List<List<GzfHousePerson>> gzfHousePersons =
                      (List<List<GzfHousePerson>>) entry.getValue();
                    for (int k = 0; k < gzfHousePersons.size(); k++) {
                        List<GzfHousePerson> gzfHouseInfos = gzfHousePersons.get(k);
                        data[k] = "" + gzfHouseInfos.size();
                    }
                }
                reportVO.setData(data);
                listTmp.add(reportVO);

                result.add(listTmp);
            }

        }

        return result;
    }



    protected List getHouseTotalRentMapListByYear(Integer byFlag,
      String villageId,
      String palacesId, String year, List<GzfVillage> gzfVillageList) {
        List list = new ArrayList();

        List<Map<String, List<List<GzfHouseInfo>>>> houseTotalMapList = new ArrayList();
        List<HouseRentVO> houseTotalMapListR = new ArrayList();
        Map<String, List<List<GzfHouseInfo>>> totalHouseMap = new HashMap<>();
        HouseRentVO totalHouseMapR = new HouseRentVO();

        List<Map<String, List<List<GzfHousePerson>>>> houseRentMapList = new ArrayList();
        List<Map<String, List<List<GzfHousePerson>>>> houseEndRentMapList = new ArrayList();

        List<HouseRentVO> houseRentMapListR = new ArrayList<>();
        List<HouseRentVO> houseEndRentMapListR = new ArrayList<>();

        Map<String, List<List<GzfHousePerson>>> rentHouseMap = new HashMap<>();
        Map<String, List<List<GzfHousePerson>>> endRentHouseMap = new HashMap<>();

        HouseRentVO rentHouseMapR = new HouseRentVO();
        HouseRentVO endRentHouseMapR = new HouseRentVO();

        String startDate = null;
        String endDate = null;
        String month = "";
        String monthNext = "";
        List<GzfHouseInfo> gzfHouseInfos = null;
        List<GzfHousePerson> gzfHousePersons = null;

        List villageList = new ArrayList();

        if (byFlag == 2) {
            // 按照小区来统计（所有苑）
            List<GzfPalaces> gzfPalacesList = getGzfPalacesByConditions(villageId);
            List<String> gzfPalacesIdListTmp = new ArrayList<>();
            for (int i = 0; i < gzfPalacesList.size(); i++) {
                String palacesIdTmp = gzfPalacesList.get(i).getId();
                gzfPalacesIdListTmp.add(palacesIdTmp);
                List<List<GzfHouseInfo>> gzfHouseInfoList = new ArrayList<>();
                List<List<GzfHousePerson>> gzfHousePersonList = new ArrayList<>();
                List<List<GzfHousePerson>> gzfHousePersonList1 = new ArrayList<>();

                List<Map<String, Integer>> tmpList = new ArrayList<>();

                for (int y = 1; y <= 12; y++) {
                    gzfHouseInfos = new ArrayList<>();
                    totalHouseMap = new HashMap<>();

                    gzfHousePersons = new ArrayList<>();
                    rentHouseMap = new HashMap<>();
                    endRentHouseMap = new HashMap<>();

                    totalHouseMapR = new HouseRentVO();
                    rentHouseMapR = new HouseRentVO();
                    endRentHouseMapR = new HouseRentVO();

                    if (y < 10) {
                        month = "0" + y;
                    } else {
                        month = y + "";
                    }
                    if ((y + 1) < 10) {
                        monthNext = "0" + (y + 1);
                    } else {
                        monthNext = (y + 1) + "";
                    }
                    startDate = year + "-" + month + "-01 00:00:00";
                    if (y == 12) {
                        endDate = year + "-12-31 23:59:59";
                    } else {
                        endDate = year + "-" + monthNext + "-01 00:00:00";
                    }
                    gzfHouseInfos =
                      getGzfHouseInfoByConditions(null, gzfPalacesIdListTmp, startDate, endDate,
                        byFlag);
                    gzfHouseInfoList.add(gzfHouseInfos);

                    gzfHousePersons =
                      getGzfHousePersonByConditions(gzfPalacesIdListTmp, startDate, endDate, 0);
                    gzfHousePersonList.add(gzfHousePersons);

                    gzfHousePersonList1.add(
                      getGzfHousePersonByConditions(gzfPalacesIdListTmp, startDate, endDate, 1));

                }

                totalHouseMap.put(palacesIdTmp, gzfHouseInfoList);
                houseTotalMapList.add(totalHouseMap);

                rentHouseMap.put(palacesIdTmp, gzfHousePersonList);
                rentHouseMapR.setUniqueKey(palacesIdTmp);
                rentHouseMapR.setGzfHousePersonListList(gzfHousePersonList);
                houseRentMapList.add(rentHouseMap);
                houseRentMapListR.add(rentHouseMapR);

                endRentHouseMap.put(palacesIdTmp, gzfHousePersonList1);
                endRentHouseMapR.setUniqueKey(palacesIdTmp);
                endRentHouseMapR.setGzfHousePersonListList(gzfHousePersonList1);

                houseEndRentMapList.add(endRentHouseMap);
                houseEndRentMapListR.add(endRentHouseMapR);

            }
        } else if (byFlag == 0) {
            // 按照所有小区（所有小区）
            if (gzfVillageList != null) {
                Map<String, List<GzfPalaces>> map = new HashMap<>();
                for (int i = 0; i < gzfVillageList.size(); i++) {
                    String villId = gzfVillageList.get(i).getId();
                    List<String> gzfPalacesIdListTmp = new ArrayList<>();
                    List<GzfPalaces> gzfPalacesListTmp = getGzfPalacesByConditions(villId);
                    List<List<GzfHouseInfo>> gzfHouseInfoList = new ArrayList<>();
                    List<List<GzfHousePerson>> gzfHousePersonList = new ArrayList<>();
                    List<List<GzfHousePerson>> gzfHousePersonList1 = new ArrayList<>();
                    for (int j = 0; j < gzfPalacesListTmp.size(); j++) {
                        gzfPalacesIdListTmp.add(gzfPalacesListTmp.get(j).getId());
                    }
                    for (int y = 1; y <= 12; y++) {
                        totalHouseMap = new HashMap<>();
                        rentHouseMap = new HashMap<>();
                        endRentHouseMap = new HashMap<>();
                        gzfHouseInfos = new ArrayList<>();


                        totalHouseMapR = new HouseRentVO();
                        rentHouseMapR = new HouseRentVO();
                        endRentHouseMapR = new HouseRentVO();

                        if (y < 10) {
                            month = "0" + y;
                        } else {
                            month = y + "";
                        }
                        if ((y + 1) < 10) {
                            monthNext = "0" + (y + 1);
                        } else {
                            monthNext = (y + 1) + "";
                        }
                        startDate = year + "-" + month + "-01 00:00:00";
                        if (y == 12) {
                            endDate = year + "-12-31 23:59:59";
                        } else {
                            endDate = year + "-" + monthNext + "-01 00:00:00";
                        }
                        if (gzfPalacesIdListTmp.size() > 0) {
                            gzfHouseInfos =
                              getGzfHouseInfoByConditions(null, gzfPalacesIdListTmp, startDate,
                                endDate,
                                byFlag);
                        }
                        gzfHouseInfoList.add(gzfHouseInfos);

                        if (gzfPalacesIdListTmp.size() > 0) {
                            gzfHousePersons = getGzfHousePersonByConditions(
                              gzfPalacesIdListTmp, startDate, endDate, 0);
                        }
                        gzfHousePersonList.add(gzfHousePersons);

                        gzfHousePersonList1.add(getGzfHousePersonByConditions(gzfPalacesIdListTmp,
                          startDate, endDate, 1));
                    }
                    totalHouseMap.put(villId, gzfHouseInfoList);
                    totalHouseMapR.setUniqueKey(villId);
                    totalHouseMapR.setGzfHouseInfoListList(gzfHouseInfoList);
                    houseTotalMapList.add(totalHouseMap);
                    houseTotalMapListR.add(totalHouseMapR);

                    rentHouseMap.put(villId, gzfHousePersonList);
                    rentHouseMapR.setUniqueKey(villId);
                    rentHouseMapR.setGzfHousePersonListList(gzfHousePersonList);
                    houseRentMapList.add(rentHouseMap);
                    houseRentMapListR.add(rentHouseMapR);

                    endRentHouseMap.put(villId, gzfHousePersonList1);
                    endRentHouseMapR.setUniqueKey(villId);
                    endRentHouseMapR.setGzfHousePersonListList(gzfHousePersonList1);
                    houseEndRentMapList.add(endRentHouseMap);
                    houseEndRentMapListR.add(endRentHouseMapR);
                }
            }
        }
        list.add(houseTotalMapList);
        list.add(houseRentMapList);
        list.add(houseEndRentMapList);


//        list.add(houseTotalMapListR);
//        list.add(houseRentMapListR);
//        list.add(houseEndRentMapListR);
        return list;
    }

    protected List<Map<String, List<List<GzfHousePerson>>>> getHouseRentMapListByYear(
      Integer byFlag,
      String villageId,
      String palacesId, String year, List<GzfVillage> gzfVillageList) {
        List<Map<String, List<List<GzfHousePerson>>>> houseRentMapList = new ArrayList();
        Map<String, List<List<GzfHousePerson>>> rentHouseMap = new HashMap<>();
        String startDate = null;
        String endDate = null;
        String month = "";
        String monthNext = "";
        List<GzfHouseInfo> gzfHouseInfos = null;
        List<GzfHousePerson> gzfHousePersons = null;
        if (byFlag == 2) {
            // 按照小区来统计（所有苑）
            List<GzfPalaces> gzfPalacesList = getGzfPalacesByConditions(villageId);
            List<String> gzfPalacesIdListTmp = new ArrayList<>();
            for (int i = 0; i < gzfPalacesList.size(); i++) {
                String palacesIdTmp = gzfPalacesList.get(i).getId();
                gzfPalacesIdListTmp.add(palacesIdTmp);
                List<List<GzfHousePerson>> gzfHousePersonList = new ArrayList<>();
                for (int y = 1; y <= 12; y++) {
                    gzfHousePersons = new ArrayList<>();
                    rentHouseMap = new HashMap<>();
                    if (y < 10) {
                        month = "0" + y;
                    } else {
                        month = y + "";
                    }
                    if ((y + 1) < 10) {
                        monthNext = "0" + (y + 1);
                    } else {
                        monthNext = (y + 1) + "";
                    }
                    startDate = year + "-" + month + "-01 00:00:00";
                    if (y == 12) {
                        endDate = year + "-12-31 23:59:59";
                    } else {
                        endDate = year + "-" + monthNext + "-01 00:00:00";
                    }
                    gzfHousePersons =
                      getGzfHousePersonByConditions(gzfPalacesIdListTmp, startDate, endDate, 0);
                    gzfHousePersonList.add(gzfHousePersons);
                }
                rentHouseMap.put(palacesIdTmp, gzfHousePersonList);
                houseRentMapList.add(rentHouseMap);
            }
        } else if (byFlag == 0) {
            // 按照所有小区（所有小区）
            if (gzfVillageList != null) {
                Map<String, List<GzfPalaces>> map = new HashMap<>();
                for (int i = 0; i < gzfVillageList.size(); i++) {
                    String villId = gzfVillageList.get(i).getId();
                    List<String> gzfPalacesIdListTmp = new ArrayList<>();
                    List<List<GzfHousePerson>> gzfHousePersonList = new ArrayList<>();
                    List<GzfPalaces> gzfPalacesListTmp = getGzfPalacesByConditions(villId);
                    for (int j = 0; j < gzfPalacesListTmp.size(); j++) {
                        gzfPalacesIdListTmp.add(gzfPalacesListTmp.get(j).getId());
                    }
                    for (int y = 1; y <= 12; y++) {
                        gzfHousePersons = new ArrayList<>();
                        rentHouseMap = new HashMap<>();
                        if (y < 10) {
                            month = "0" + y;
                        } else {
                            month = y + "";
                        }
                        if ((y + 1) < 10) {
                            monthNext = "0" + (y + 1);
                        } else {
                            monthNext = (y + 1) + "";
                        }
                        startDate = year + "-" + month + "-01";
                        if (y == 12) {
                            endDate = year + "-12-31 23:59:59";
                        } else {
                            endDate = year + "-" + monthNext + "-01 00:00:00";
                        }
                        if (gzfPalacesIdListTmp.size() > 0) {
                            gzfHousePersons = getGzfHousePersonByConditions(
                              gzfPalacesIdListTmp, startDate, endDate, 0);
                        }
                        gzfHousePersonList.add(gzfHousePersons);
                    }
                    rentHouseMap.put(villId, gzfHousePersonList);
                    houseRentMapList.add(rentHouseMap);
                }
            }
        }
        return houseRentMapList;
    }

    /**
     * 根据条件获取苑数据
     *
     * @param villageId
     * @return
     */
    protected List<GzfPalaces> getGzfPalacesByConditions(String villageId) {
        GzfPalaces gzfPalaces = new GzfPalaces();
        gzfPalaces.setGzfVillageId(villageId);
        return gzfPalacesService.findList(gzfPalaces);
    }

    /**
     * 获取房屋列表
     *
     * @param villageList
     * @param palacesIds
     * @param startDate
     * @param endDate
     * @param byFlag
     * @return
     */
    protected List<GzfHouseInfo> getGzfHouseInfoByConditions(List<String> villageList,
      List<String> palacesIds, String startDate, String endDate, Integer byFlag) {
        switch (byFlag) {
            case 0:
                // all village
            case 1:
                // all palaces one village
            case 2:
                // one palace
                return gzfHouseInfoService
                  .findAllList(null, null, null, palacesIds, null, null);
            default:
                return null;
        }

    }

    /**
     * 获取已出租房屋&人员信息列表
     *
     * @param palacesIds
     * @param startDate
     * @param endDate
     * @param flag
     * @return
     */
    protected List<GzfHousePerson> getGzfHousePersonByConditions(List<String> palacesIds,
      String startDate, String endDate, int flag) {
        GzfHousePerson gzfHousePerson = new GzfHousePerson();
        gzfHousePerson.setPalacesIds(palacesIds);
        // flag = 0 出租数
        if (flag == 0 && startDate != null) {
            try {
                Date startTime = DateFormat.getDateInstance().parse(startDate);
                gzfHousePerson.setStartTime(startTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        if (flag == 0 && endDate != null) {
            try {
                Date endTime = DateFormat.getDateInstance().parse(endDate);
                gzfHousePerson.setStartRentEndDate(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        // flag = 1 退租（合同结束)
        if (flag == 1 && startDate != null) {
            try {
                Date startTime = DateFormat.getDateTimeInstance().parse(startDate);
                gzfHousePerson.setEndRentDate(startTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        if (flag == 1 && endDate != null) {
            try {
                Date endTime = DateFormat.getDateTimeInstance().parse(endDate);
                gzfHousePerson.setEndRentEndDate(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return housePersonService.findList(gzfHousePerson);
    }


    /**
     * 人员报表页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/person")
    public String person(HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = UserUtils.getUser();
        List<GzfVillage> gzfVillageList = gzfVillageService.findAllList(null);
        model.addAttribute("gzfVillageList", gzfVillageList);
        return "modules/gzf/gzfRportStaff";
    }

    /**
     * 人员报表之学位报表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("personReport/degree")
    @ResponseBody
    public List<ReportBO> getPersonReportDegree(HttpServletRequest request,
      HttpServletResponse response) {
        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<ReportBO> reportBOs = new ArrayList<>();
        List<Dict> dicts = DictUtils.getDictList(ReportDictConstant.DICT_TYPE_EDUCATION);
        Set<String> degreeDict = new HashSet<>();
        GzfHousePerson gzfHousePerson = new GzfHousePerson();
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
        gzfHousePerson.setPalacesIds(palacesIds);
        if (startDate != null) {
            try {
                gzfHousePerson.setStartTime(DateFormat.getDateInstance().parse(startDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (endDate != null) {
            endDate += " 23:59:59";
            try {
                gzfHousePerson.setEndTime(DateFormat.getDateInstance().parse(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<GzfHousePerson> allPersonList = housePersonService.findList(gzfHousePerson);
        int total = allPersonList.size();
        List<GzfHousePerson> personList = null;
        ReportBO reportBO = null;
        Iterator iterator = dicts.iterator();
        while (iterator.hasNext()) {
            Dict dict = (Dict) iterator.next();
            degreeDict.add(dict.getLabel());
            gzfHousePerson.setEducation(Integer.valueOf(dict.getValue()));
            personList = housePersonService.findList(gzfHousePerson);
            int curSize = personList.size();
            reportBO = new ReportBO();
            reportBO.setKey(dict.getValue());
            reportBO.setTitle(dict.getLabel());
            reportBO.setValue("" + curSize);
            reportBO.setRate(Float.valueOf(String.format("%.2f", ((float) curSize / total) * 100)));
            reportBOs.add(reportBO);
        }

        return reportBOs;
    }

    /**
     * 人员报表之年龄报表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("personReport/age")
    @ResponseBody
    public List<ReportBO> getPersonReportAge(HttpServletRequest request,
      HttpServletResponse response) {
        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<ReportBO> reportBOs = new ArrayList<>();
        Map<String, Integer> ageMap = new HashMap<>();
        String key1 = "0-17岁";
        String key2 = "18-23岁";
        String key3 = "24-30岁";
        String key4 = "31-35岁";
        String key5 = "35岁以上";

        ageMap.put(key1, 0);
        ageMap.put(key2, 0);
        ageMap.put(key3, 0);
        ageMap.put(key4, 0);
        ageMap.put(key5, 0);

        GzfHousePerson gzfHousePerson = new GzfHousePerson();
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
        gzfHousePerson.setPalacesIds(palacesIds);
        if (startDate != null) {
            try {
                gzfHousePerson.setStartTime(DateFormat.getDateInstance().parse(startDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (endDate != null) {
            endDate += " 23:59:59";
            try {
                gzfHousePerson.setEndTime(DateFormat.getDateInstance().parse(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<GzfHousePerson> allPersonList = housePersonService.findList(gzfHousePerson);
        int first = 17;
        int second = 24;
        int third = 30;
        int forth = 35;
        Iterator iterator = allPersonList.iterator();
        while (iterator.hasNext()) {
            GzfHousePerson person = (GzfHousePerson) iterator.next();
            Integer age = Integer.parseInt(person.getGzfHouseholdInfo().getAge());
            if (age == null) {
                age = 0;
            }
            if (age < first) {
                Integer oldValue = ageMap.get(key1);
                ageMap.put(key1, ++oldValue);
            } else if (age >= first && age < second) {
                Integer oldValue = ageMap.get(key2);
                ageMap.put(key2, ++oldValue);
            } else if (age >= second && age < third) {
                Integer oldValue = ageMap.get(key3);
                ageMap.put(key3, ++oldValue);
            } else if (age >= third && age < forth) {
                Integer oldValue = ageMap.get(key4);
                ageMap.put(key4, ++oldValue);
            } else {
                Integer oldValue = ageMap.get(key5);
                ageMap.put(key5, ++oldValue);
            }
        }
        ReportBO reportBO = new ReportBO();
        reportBO.setTitle(key1);
        reportBO.setValue("" + ageMap.get(key1));
        reportBOs.add(reportBO);

        reportBO = new ReportBO();
        reportBO.setTitle(key2);
        reportBO.setValue("" + ageMap.get(key2));
        reportBOs.add(reportBO);

        reportBO = new ReportBO();
        reportBO.setTitle(key3);
        reportBO.setValue("" + ageMap.get(key3));
        reportBOs.add(reportBO);

        reportBO = new ReportBO();
        reportBO.setTitle(key4);
        reportBO.setValue("" + ageMap.get(key4));
        reportBOs.add(reportBO);

        reportBO = new ReportBO();
        reportBO.setTitle(key5);
        reportBO.setValue("" + ageMap.get(key5));
        reportBOs.add(reportBO);

        return reportBOs;
    }

    /**
     * 人员报表之性别报表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("personReport/sex")
    @ResponseBody
    public List<ReportBO> getPersonReportSex(HttpServletRequest request,
      HttpServletResponse response) {
        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<ReportBO> reportBOs = new ArrayList<>();
        List<Dict> dicts = DictUtils.getDictList(ReportDictConstant.DICT_TYPE_SEX);
        Set<String> degreeDict = new HashSet<>();
        GzfHousePerson gzfHousePerson = new GzfHousePerson();
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
        gzfHousePerson.setPalacesIds(palacesIds);
        if (startDate != null) {
            try {
                gzfHousePerson.setStartTime(DateFormat.getDateInstance().parse(startDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (endDate != null) {
            endDate += " 23:59:59";
            try {
                gzfHousePerson.setEndTime(DateFormat.getDateInstance().parse(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<GzfHousePerson> allPersonList = housePersonService.findList(gzfHousePerson);
        int total = allPersonList.size();
        List<GzfHousePerson> personList = null;
        ReportBO reportBO = null;
        Iterator iterator = dicts.iterator();
        while (iterator.hasNext()) {
            Dict dict = (Dict) iterator.next();
            degreeDict.add(dict.getLabel());
            gzfHousePerson.setSex(Integer.valueOf(dict.getValue()));
            personList = housePersonService.findList(gzfHousePerson);
            int curSize = personList.size();
            reportBO = new ReportBO();
            reportBO.setKey(dict.getValue());
            reportBO.setTitle(dict.getLabel());
            reportBO.setValue("" + curSize);
            reportBO.setRate(Float.valueOf(String.format("%.2f", ((float) curSize / total) * 100)));
            reportBOs.add(reportBO);
        }

        return reportBOs;
    }

    /**
     * 人员报表之工作性质报表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("personReport/jobCategory")
    @ResponseBody
    public List<ReportBO> getPersonReportJobCategory(HttpServletRequest request,
      HttpServletResponse response) {
        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<ReportBO> reportBOs = new ArrayList<>();
        List<Dict> dicts = DictUtils.getDictList(ReportDictConstant.DICT_TYPE_JOB_CATEGORY);
        Set<String> degreeDict = new HashSet<>();
        GzfHousePerson gzfHousePerson = new GzfHousePerson();
        List<GzfHousePerson> personList = null;
        ReportBO reportBO = null;
        Iterator iterator = dicts.iterator();
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
        gzfHousePerson.setPalacesIds(palacesIds);
        if (startDate != null) {
            try {
                gzfHousePerson.setStartTime(DateFormat.getDateInstance().parse(startDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (endDate != null) {
            endDate += " 23:59:59";
            try {
                gzfHousePerson.setEndTime(DateFormat.getDateInstance().parse(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<GzfHousePerson> allPersonList = housePersonService.findList(gzfHousePerson);
        int total = allPersonList.size();
        while (iterator.hasNext()) {
            Dict dict = (Dict) iterator.next();
            degreeDict.add(dict.getLabel());
            gzfHousePerson.setEducation(Integer.valueOf(dict.getValue()));
            personList = housePersonService.findList(gzfHousePerson);
            int curSize = personList.size();
            reportBO = new ReportBO();
            reportBO.setKey(dict.getValue());
            reportBO.setTitle(dict.getLabel());
            reportBO.setValue("" + curSize);
            reportBO.setRate(Float.valueOf(String.format("%.2f", ((float) curSize / total) * 100)));
            reportBOs.add(reportBO);
        }

        return reportBOs;
    }



    /**
     * 维修报表页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/maintainace")
    public String maintainace(HttpServletRequest request, HttpServletResponse response,
      Model model) {
        User user = UserUtils.getUser();
        List<GzfVillage> gzfVillageList = gzfVillageService.findAllList(null);
        model.addAttribute("gzfVillageList", gzfVillageList);
        return "modules/gzf/gzfRportMaintainace";
    }

    /**
     * 异步获取设备报表数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("maintainaceReport/project")
    @ResponseBody
    public List<ReportBO> getMaintainaceReportProject(HttpServletRequest request,
      HttpServletResponse response) {
        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<ReportBO> reportBOs = new ArrayList<>();
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
        if (endDate != null) {
            endDate += " 23:59:59";
        }
        List<GzfHouseInfo> houseInfoList =
          gzfHouseInfoService.findAllList(null, startDate, endDate, palacesIds, null, null);
        List<GzfRepairProject> repairProjectList = gzfRepairProjectService.findAllList(null);
        Map<String, Integer> elevatorMap = new HashMap<>();
        String elevatorKey;
        GzfHouseInfo gzfHouseInfo;

        int cardNum = 0;
        int elevatorNum = 0;
        for (int i = 0; i < houseInfoList.size(); i++) {
            gzfHouseInfo = houseInfoList.get(i);
            cardNum += gzfHouseInfo.getCardNum();
            elevatorKey =
              gzfHouseInfo.getGzfPalacesId() + "_" + gzfHouseInfo.getBuildNum() + "_" + gzfHouseInfo
                .getUnit();
            if (!elevatorMap.containsKey(elevatorKey)) {
                elevatorMap.put(elevatorKey, 1);
                elevatorNum++;
            } else {
                continue;
            }
        }

        int houseNumber = houseInfoList.size();
        Iterator iterator = repairProjectList.iterator();
        ReportBO reportBO = null;
        while (iterator.hasNext()) {
            GzfRepairProject repairProject = (GzfRepairProject) iterator.next();
            reportBO = new ReportBO();
            reportBO.setTitle(repairProject.getName());
            if (repairProject.getName().equals("无线模块")) {
                reportBO.setValue("" + houseNumber * 2);
            } else if (repairProject.getName().equals("门卡")) {
                reportBO.setValue("" + cardNum);
            } else if (repairProject.getName().equals("电梯")) {
                reportBO.setValue("" + elevatorNum);
            } else {
                reportBO.setValue("" + houseNumber * 1);
            }
            reportBOs.add(reportBO);
        }
        return reportBOs;
    }

    /**
     * 异步获取设备报修报表数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("maintainaceReport/projectMaintainace")
    @ResponseBody
    public List<ReportBO> getMaintainaceReportProjectMaintainace(HttpServletRequest request,
      HttpServletResponse response) {
        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String projectId = request.getParameter("projectId");
        List<ReportBO> reportBOs = new ArrayList<>();
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
        if (endDate != null) {
            endDate += " 23:59:59";
        }
        List<GzfHouseInfo> houseInfoList = gzfHouseInfoService.findAllList(null, startDate, endDate,
          palacesIds, null, null);
        List<GzfRepairProject> repairProjectList = null;
        if (projectId.equals("0")) {
            // 所有设备
            repairProjectList = gzfRepairProjectService.findAllList(null);
        } else {
            // 指定设备
            GzfRepairProject gzfRepairProject = new GzfRepairProject(projectId);
            repairProjectList = gzfRepairProjectService.findList(gzfRepairProject);
        }
        int houseNumber = houseInfoList.size();
        Iterator iterator = repairProjectList.iterator();
        ReportBO reportBO;
        Map<String, Integer> elevatorMap = new HashMap<>();
        GzfHouseInfo gzfHouseInfo;
        String elevatorKey;

        int cardNum = 0;
        int elevatorNum = 0;
        for (int i = 0; i < houseInfoList.size(); i++) {
            gzfHouseInfo = houseInfoList.get(i);
            cardNum += gzfHouseInfo.getCardNum();
            elevatorKey =
              gzfHouseInfo.getGzfPalacesId() + "_" + gzfHouseInfo.getBuildNum() + "_" + gzfHouseInfo
                .getUnit();
            if (!elevatorMap.containsKey(elevatorKey)) {
                elevatorMap.put(elevatorKey, 1);
                elevatorNum++;
            } else {
                continue;
            }
        }

        while (iterator.hasNext()) {
            GzfRepairProject repairProject = (GzfRepairProject) iterator.next();
            String tmpProjectId = repairProject.getId();
            int repairNum =
              getGzfRepairManagementByConditions(palacesIds, startDate, endDate, tmpProjectId)
                .size();
            int totalNum = houseNumber * 1;
            reportBO = new ReportBO();
            reportBO.setTitle(repairProject.getName());

            if (repairProject.getName().equals("无线模块")) {
                totalNum = houseNumber * 2;
                repairNum = repairNum * 2;
            } else if (repairProject.getName().equals("门卡")) {
                totalNum = cardNum;
            } else if (repairProject.getName().equals("电梯")) {
                totalNum = elevatorNum;
            } else {

            }

            repairNum = repairNum > totalNum ? totalNum : repairNum;
            float rate = (float) repairNum / totalNum * 100;
            reportBO.setValue("" + totalNum);
            reportBO.setRate(rate);
            reportBOs.add(reportBO);
        }
        return reportBOs;
    }

    /**
     * 根据条件返回报修的项目列表
     *
     * @param palacesIds
     * @param startDate
     * @param endDate
     * @param projectId
     * @return
     */
    private List<GzfRepairManagement> getGzfRepairManagementByConditions(List<String> palacesIds,
      String startDate, String endDate, String projectId) {
        GzfRepairManagement gzfRepairManagement = new GzfRepairManagement();
        if (palacesIds != null) {
            gzfRepairManagement.setPalacesIds(palacesIds);
        }
        if (!startDate.equals("")) {
            try {
                gzfRepairManagement.setStartDate(DateFormat.getDateInstance().parse(startDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!endDate.equals("")) {
            try {
                gzfRepairManagement.setEndDate(DateFormat.getDateInstance().parse(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!projectId.equals("0")) {
            List<String> projectIds = new ArrayList<>();
            projectIds.add(projectId);
            //String repairType = gzfRepairProjectService.get(projectId).getName();
            //gzfRepairManagement.setRepairTypeStr(repairType);
            gzfRepairManagement.setProjectIds(projectIds);
        }
        return gzfRepairManagementService.findListWithProject(gzfRepairManagement);
    }


    /**
     * 设备维修率
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("maintainaceReport/projectRepair")
    @ResponseBody
    public List<ReportBO> getMaintainaceReportProjectRepair(HttpServletRequest request,
      HttpServletResponse response) {
        String villageId = request.getParameter("villageId");
        String palacesId = request.getParameter("palacesId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<ReportBO> reportBOs = new ArrayList<>();
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
        if (endDate != null) {
            endDate += " 23:59:59";
        }
        List<GzfHouseInfo> houseInfoList = gzfHouseInfoService.findAllList(null, startDate, endDate,
          palacesIds, null, null);
        List<GzfRepairProject> repairProjectList = gzfRepairProjectService.findAllList(null);

        int houseNumber = houseInfoList.size();
        Iterator iterator = repairProjectList.iterator();
        ProjectRepairReportBo reportBO = null;
        // 每个设备总量
        int totalNum = 0;
        // 每个设备报修总量
        int repairManagementNum = 0;
        // 每个设备的报修率= 报修总量/设备总量
        float rate = 0f;
        // 所有设备报修总数量 所有设备报修总量 = 每个设备报修数量之和
        int totalRepairManagementNum = 0;
        //每个设备ID（临时）
        String tmpProjectId = "";
        // 设备报修条目
        List<GzfRepairManagement> repairManagementList = null;
        Map<String, Integer> projectRepairMap = null;
        List<ReportBO> subReportBOs = null;
        while (iterator.hasNext()) {
            subReportBOs = new ArrayList<>();
            GzfRepairProject repairProject = (GzfRepairProject) iterator.next();
            tmpProjectId = repairProject.getId();
            repairManagementList = getGzfRepairManagementByConditions(palacesIds, startDate,
              endDate, tmpProjectId);
            repairManagementNum = repairManagementList.size();
            totalRepairManagementNum += repairManagementNum;
            totalNum = houseNumber * 1;
            projectRepairMap = projectRepairMap(repairManagementList);
            if (!projectRepairMap.isEmpty()) {
                subReportBOs = changeMap2ReportBoList(projectRepairMap);
            }
            reportBO = new ProjectRepairReportBo();
            if (repairProject.getName().equals("无线模块")) {
                totalNum = houseNumber * 2;
                repairManagementNum = repairManagementNum * 2;
            }
            repairManagementNum = repairManagementNum > totalNum ? totalNum : repairManagementNum;
            rate = (float) repairManagementNum / totalNum * 100;
            //            reportBO.setValue("" + totalNum);
            //            reportBO.setRate(rate);
            //            reportBOs.add(reportBO);
            reportBO.setKey(repairProject.getId());
            reportBO.setTitle(repairProject.getName());
            reportBO.setValue("" + repairManagementNum);
            reportBO.setRate(
              Float.valueOf(String.format("%.2f", ((float) repairManagementNum / totalNum) * 100)));
            reportBO.setSubReports(subReportBOs);
            reportBOs.add(reportBO);
        }
        return reportBOs;
    }

    private Map<String, Integer> projectRepairMap(
      List<GzfRepairManagement> gzfRepairManagementList) {
        Map<String, Integer> projectRepairMap = new HashMap<>();
        String keyTotal = "totalNum";
        String keyNotRepair = "notRepair";
        String keyRepairing = "repairing";
        String keyFirstTimeRepairing = "repairingF";
        String keySecondTimeRepairing = "repairingS";
        String keyThirdTimeRepairing = "repairingT";
        String keyForthTimeRepairing = "repairingFo";
        int totalNum = gzfRepairManagementList.size();
        int notRepairNum = 0;
        int repairingNum = 0;
        int repairingFirstTimeNum = 0;
        int repairingSecondTimeNum = 0;
        int repairingThirdTimeNum = 0;
        int repairingForthTimeNum = 0;
        int firstTime = 3;
        int secondTime = 7;
        int thirdTime = 15;
        long repairTimeMinusCreateTime = 0;
        int repairDay = 0;
        if (gzfRepairManagementList.size() > 0) {
            GzfRepairManagement gzfRepairManagement = null;
            Iterator iterator = gzfRepairManagementList.iterator();
            while (iterator.hasNext()) {
                gzfRepairManagement = (GzfRepairManagement) iterator.next();
                if (gzfRepairManagement.getMaintenanceId() == null) {
                    notRepairNum++;
                } else {
                    if (gzfRepairManagement.getRepairTime() == null
                      || gzfRepairManagement.getTime() == null) {
                        repairTimeMinusCreateTime = 0l;
                    } else {
                        repairTimeMinusCreateTime =
                          gzfRepairManagement.getRepairTime().getTime() - gzfRepairManagement
                            .getTime().getTime();
                    }
                    repairDay = (int) Math.floor(repairTimeMinusCreateTime / (24 * 3600 * 1000));
                    if (repairDay <= firstTime) {
                        repairingFirstTimeNum++;
                    } else if (repairDay > firstTime && repairDay <= secondTime) {
                        repairingSecondTimeNum++;
                    } else if (repairDay > secondTime && repairDay <= thirdTime) {
                        repairingSecondTimeNum++;
                    } else {
                        repairingForthTimeNum++;
                    }
                    repairingNum++;
                }
            }
        }
        projectRepairMap.put(keyTotal, totalNum);
        projectRepairMap.put(keyNotRepair, notRepairNum);
        projectRepairMap.put(keyRepairing, repairingNum);
        projectRepairMap.put(keyFirstTimeRepairing, repairingFirstTimeNum);
        projectRepairMap.put(keySecondTimeRepairing, repairingSecondTimeNum);
        projectRepairMap.put(keyThirdTimeRepairing, repairingThirdTimeNum);
        projectRepairMap.put(keyForthTimeRepairing, repairingForthTimeNum);
        return projectRepairMap;
    }

    private List<ReportBO> changeMap2ReportBoList(Map<String, Integer> projectRepairMap) {
        List<ReportBO> reportBOList = new ArrayList<>();
        ReportBO reportBO = null;
        String keyTotal = "totalNum";
        String keyNotRepair = "notRepair";
        String keyFirstTimeRepairing = "repairingF";
        String keySecondTimeRepairing = "repairingS";
        String keyThirdTimeRepairing = "repairingT";
        String keyForthTimeRepairing = "repairingFo";
        int firstTime = 3;
        int secondTime = 7;
        int thirdTime = 15;
        int repairingFirstTimeNum = projectRepairMap.get(keyFirstTimeRepairing);
        int repairingSecondTimeNum = projectRepairMap.get(keySecondTimeRepairing);
        int repairingThirdTimeNum = projectRepairMap.get(keyThirdTimeRepairing);
        // 超过期限和未维修的放在一起？
        int repairingForthTimeNum =
          projectRepairMap.get(keyForthTimeRepairing) + projectRepairMap.get(keyNotRepair);
        int totalNum = projectRepairMap.get(keyTotal);
        if (totalNum == 0) {
            totalNum = 1;
        }
        reportBO = new ReportBO();
        reportBO.setTitle(firstTime + "天内");
        reportBO.setValue("" + repairingFirstTimeNum);
        reportBO.setRate(
          Float.valueOf(String.format("%.2f", ((float) repairingFirstTimeNum / totalNum) * 100)));
        reportBOList.add(reportBO);

        reportBO = new ReportBO();
        reportBO.setTitle(firstTime + "天至" + secondTime + "天内");
        reportBO.setValue("" + repairingSecondTimeNum);
        reportBO.setRate(
          Float.valueOf(String.format("%.2f", ((float) repairingSecondTimeNum / totalNum) * 100)));
        reportBOList.add(reportBO);

        reportBO = new ReportBO();
        reportBO.setTitle(secondTime + "天至" + thirdTime + "天内");
        reportBO.setValue("" + repairingThirdTimeNum);
        reportBO.setRate(
          Float.valueOf(String.format("%.2f", ((float) repairingThirdTimeNum / totalNum) * 100)));
        reportBOList.add(reportBO);

        reportBO = new ReportBO();
        reportBO.setTitle("超过" + thirdTime + "天");
        reportBO.setValue("" + repairingForthTimeNum);
        reportBO.setRate(
          Float.valueOf(String.format("%.2f", ((float) repairingForthTimeNum / totalNum) * 100)));
        reportBOList.add(reportBO);
        return reportBOList;
    }

}
