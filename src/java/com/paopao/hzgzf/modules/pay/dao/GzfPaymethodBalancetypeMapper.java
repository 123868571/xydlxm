package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfPaymethodBalancetype;
import com.paopao.hzgzf.modules.pay.entity.GzfPaymethodBalancetypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfPaymethodBalancetypeMapper extends CrudDao<GzfPaymethodBalancetype>{
    int countByExample(GzfPaymethodBalancetypeExample example);

    int deleteByExample(GzfPaymethodBalancetypeExample example);

    int deleteByPrimaryKey(String payMethod);

    int insert(GzfPaymethodBalancetype record);

    int insertSelective(GzfPaymethodBalancetype record);

    List<GzfPaymethodBalancetype> selectByExample(GzfPaymethodBalancetypeExample example);

    GzfPaymethodBalancetype selectByPrimaryKey(String payMethod);

    int updateByExampleSelective(@Param("record") GzfPaymethodBalancetype record, @Param("example") GzfPaymethodBalancetypeExample example);

    int updateByExample(@Param("record") GzfPaymethodBalancetype record, @Param("example") GzfPaymethodBalancetypeExample example);

    int updateByPrimaryKeySelective(GzfPaymethodBalancetype record);

    int updateByPrimaryKey(GzfPaymethodBalancetype record);
}