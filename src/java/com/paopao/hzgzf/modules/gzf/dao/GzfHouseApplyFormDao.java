/**  
 * Project Name:hzgzf  
 * File Name:GzfHouseApplyFormDao.java  
 * Package Name:com.paopao.hzgzf.modules.gzf.dao  
 * Date:2016年4月11日下午4:50:07  
 * Copyright (c) 2016, yuliqian@19lou.com All Rights Reserved.  
 *  
 */

package com.paopao.hzgzf.modules.gzf.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.gzf.entity.GzfHouseApplyForm;

/**
 * ClassName: GzfHouseApplyFormDao <br/>
 * Function: gzf_house_apply_form mapper. <br/>
 * date: 2016年4月11日 下午4:50:07 <br/>
 * 
 * @author yuliqian
 * @version
 * @since JDK 1.6
 */
@MyBatisDao
public interface GzfHouseApplyFormDao extends CrudDao<GzfHouseApplyForm> {
    /**
     * 
     * 更新审核. <br/>
     * 
     * @author yuliqian
     * @param entity
     * @return
     * @since JDK 1.6
     */
    public int updateCheck(GzfHouseApplyForm entity);
}
