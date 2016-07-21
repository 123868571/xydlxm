/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.service;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.datacontract.schemas._2004._07.communityserversync.Renter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tempuri.AddRenter;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.utils.StringUtils;
import com.paopao.hzgzf.modules.gzf.dao.GzfHousePersonDao;
import com.paopao.hzgzf.modules.gzf.dao.GzfHouseholdInfoDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseholdInfo;
import com.paopao.hzgzf.modules.gzf.webservice.DataServiceStub;
import com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRenter_FaultExceptionFault_FaultMessage;

/**
 * 人员信息Service
 * @author Hongjun
 * @version 2016-01-02
 */
@Service
@Transactional(readOnly = true)
public class GzfHouseholdInfoService extends CrudService<GzfHouseholdInfoDao, GzfHouseholdInfo> {

    @Autowired
    private GzfHouseholdInfoDao gzfHouseholdInfoDao;
    @Autowired
    private GzfHousePersonDao   gzfHousePersonDao;

    public GzfHouseholdInfo get(String id) {
        return super.get(id);
    }

    public List<GzfHouseholdInfo> findList(GzfHouseholdInfo gzfHouseholdInfo) {
        return super.findList(gzfHouseholdInfo);
    }

    public Page<GzfHouseholdInfo> findPageList(Page<GzfHouseholdInfo> page,
                                               GzfHouseholdInfo gzfHouseholdInfo) {
        gzfHouseholdInfo.setPage(page);
        page.setList(dao.findHouseholdList(gzfHouseholdInfo));
        return page;
    }

    public Page<GzfHouseholdInfo> findPage(Page<GzfHouseholdInfo> page,
                                           GzfHouseholdInfo gzfHouseholdInfo) {
        return super.findPage(page, gzfHouseholdInfo);
    }

    public int countHousehold(String personal) {
        return dao.countHousehold(personal);
    }

    @Transactional(readOnly = false)
    public void save(GzfHouseholdInfo gzfHouseholdInfo) {
        boolean flag = false;
        if (StringUtils.isEmpty(gzfHouseholdInfo.getId())) {
            flag = true;
        }
        super.save(gzfHouseholdInfo);
        if (flag) {
            try {
                gzfHouseholdInfo = dao.get(gzfHouseholdInfo);
                //增加租客信息
                AddRenter addRenter22 = new AddRenter();
                DataServiceStub dataServiceStub = null;
                try {
                    dataServiceStub = new DataServiceStub();
                } catch (AxisFault e1) {
                    logger.error("", e1);
                }
                Renter renter = new Renter();
                renter.setCode(gzfHouseholdInfo.getCode());
                renter.setName(gzfHouseholdInfo.getName());
                renter.setIdCardNumber(gzfHouseholdInfo.getCardid());
                renter.setPhoneNumber(gzfHouseholdInfo.getPhone());
                renter.setMaxCardCount(Integer.valueOf(gzfHouseholdInfo.getCardnum()));
                addRenter22.setRenter(renter);
                dataServiceStub.addRenter(addRenter22);
            } catch (RemoteException | IDataService_AddRenter_FaultExceptionFault_FaultMessage e) {
                logger.error("", e);
                gzfHouseholdInfo.setSync(1);
                dao.update(gzfHouseholdInfo);
            }
        }
    }

    @Transactional(readOnly = false)
    public void delete(GzfHouseholdInfo gzfHouseholdInfo) {
        super.delete(gzfHouseholdInfo);
    }

    public GzfHouseholdInfo getByEntity(GzfHouseholdInfo gzfHouseholdInfo) {
        // TODO Auto-generated method stub
        return gzfHouseholdInfoDao.getByEntity(gzfHouseholdInfo);
    }

    public GzfHouseholdInfo getByCardId(String cardid) {
        return dao.getByCardId(cardid);
    }

    public List<GzfHouseholdInfo> getByNameAndPhone(GzfHouseholdInfo gzfHouseholdInfo) {
        return dao.getByNameAndPhone(gzfHouseholdInfo);
    }

}