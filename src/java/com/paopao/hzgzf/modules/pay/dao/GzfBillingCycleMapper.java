package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfBillingCycle;
import com.paopao.hzgzf.modules.pay.entity.GzfBillingCycleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfBillingCycleMapper extends CrudDao<GzfBillingCycle>{
    int countByExample(GzfBillingCycleExample example);

    int deleteByExample(GzfBillingCycleExample example);

    int deleteByPrimaryKey(Integer biilingCycleId);

    int insert(GzfBillingCycle record);

    int insertSelective(GzfBillingCycle record);

    List<GzfBillingCycle> selectByExample(GzfBillingCycleExample example);

    GzfBillingCycle selectByPrimaryKey(Integer biilingCycleId);

    int updateByExampleSelective(@Param("record") GzfBillingCycle record, @Param("example") GzfBillingCycleExample example);

    int updateByExample(@Param("record") GzfBillingCycle record, @Param("example") GzfBillingCycleExample example);

    int updateByPrimaryKeySelective(GzfBillingCycle record);

    int updateByPrimaryKey(GzfBillingCycle record);
}