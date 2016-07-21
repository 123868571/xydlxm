/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.util.List;

import org.datacontract.schemas._2004._07.communityserversync.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tempuri.AddRoom;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.modules.gzf.dao.GzfHouseInfoDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.webservice.DataServiceStub;

/**
 * 房屋信息Service
 *
 * @author Hongjun
 * @version 2016-01-02
 */
@Service
@Transactional(readOnly = true)
public class GzfHouseInfoService extends CrudService<GzfHouseInfoDao, GzfHouseInfo> {

    @Autowired
    private GzfHouseInfoDao houseInfoDao;

    public GzfHouseInfo get(String id) {
        return super.get(id);
    }

    public GzfHouseInfo getByHouseInfo(GzfHouseInfo gzfHouseInfo) {
        return dao.getByHouseInfo(gzfHouseInfo);
    }

    public List<GzfHouseInfo> findList(GzfHouseInfo gzfHouseInfo) {
        return super.findList(gzfHouseInfo);
    }

    public Page<GzfHouseInfo> findPage(Page<GzfHouseInfo> page, GzfHouseInfo gzfHouseInfo) {
        return super.findPage(page, gzfHouseInfo);
    }

    public Page<GzfHouseInfo> findPageList(Page<GzfHouseInfo> page, GzfHouseInfo gzfHouseInfo) {
        gzfHouseInfo.setPage(page);
        page.setList(dao.findHouseList(gzfHouseInfo));
        return page;
    }

    public int countHouse(String personal) {
        return dao.countHouse(personal);
    }

    @Transactional(readOnly = false)
    public void save(GzfHouseInfo houseInfo, boolean isSync) {
        if (!StringUtils.isEmpty(houseInfo.getId())) {
            isSync = false;
        }
        super.save(houseInfo);
        if (isSync) {
            try {
                GzfHouseInfo gzfHouseInfo = dao.get(houseInfo);
                DataServiceStub dataServiceStub = new DataServiceStub();
                AddRoom addRoom12 = new AddRoom();
                Room room = new Room();
                room.setCode(gzfHouseInfo.getCode());
                StringBuilder s = new StringBuilder();
                s.append(gzfHouseInfo.getGzfPalaces().getGzfVillage().getName());
                s.append(gzfHouseInfo.getGzfPalaces().getName());
                s.append(gzfHouseInfo.getBuildNum()).append("-");
                s.append(gzfHouseInfo.getUnit()).append("-");
                s.append(gzfHouseInfo.getRoom());
                room.setLocation(s.toString());
                room.setNumber(gzfHouseInfo.getRoom());
                room.setBuilding(gzfHouseInfo.getBuildNum().toString());
                room.setUnit(gzfHouseInfo.getUnit().toString());
                room.setFloor(StringUtils.substring(gzfHouseInfo.getRoom(), 2));
                room.setArea(gzfHouseInfo.getUseArea().floatValue());
                addRoom12.setRoom(room);
                dataServiceStub.addRoom(addRoom12);
            } catch (Exception e) {
                logger.error("", e);
                houseInfo.setSync(1);//同步失败
                dao.update(houseInfo);
            }
        }
    }

    @Transactional(readOnly = false)
    public void delete(GzfHouseInfo gzfHouseInfo) {
        super.delete(gzfHouseInfo);
    }

    public List<GzfHouseInfo> findAllList(Page<GzfHouseInfo> page, String startVillageDate,
                                          String endVillageDate, List<String> palacesIds,
                                          String startDate, String endDate) {
        return houseInfoDao.findAllList(page, startVillageDate, endVillageDate, palacesIds, 0,
            startDate, endDate);
    }

    @Transactional(readOnly = false)
    public void updateInfoAndStatus(GzfHouseInfo gzfHouseInfo) {
        dao.updateInfoAndStatus(gzfHouseInfo);
    }

}
