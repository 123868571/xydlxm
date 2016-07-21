package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfPayment;
import com.paopao.hzgzf.modules.pay.entity.GzfPaymentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfPaymentMapper extends CrudDao<GzfPayment>{
    int countByExample(GzfPaymentExample example);

    int deleteByExample(GzfPaymentExample example);

    int deleteByPrimaryKey(String id);

    int insert(GzfPayment record);

    int insertSelective(GzfPayment record);

    List<GzfPayment> selectByExample(GzfPaymentExample example);

    GzfPayment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GzfPayment record, @Param("example") GzfPaymentExample example);

    int updateByExample(@Param("record") GzfPayment record, @Param("example") GzfPaymentExample example);

    int updateByPrimaryKeySelective(GzfPayment record);

    int updateByPrimaryKey(GzfPayment record);
    
    List<GzfPayment> getPaymentByAccountId(String accountId);
}