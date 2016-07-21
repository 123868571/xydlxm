package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfSpecialPayment;
import com.paopao.hzgzf.modules.pay.entity.GzfSpecialPaymentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfSpecialPaymentMapper extends CrudDao<GzfSpecialPayment>{
    int countByExample(GzfSpecialPaymentExample example);

    int deleteByExample(GzfSpecialPaymentExample example);

    int deleteByPrimaryKey(Integer spePaymentId);

    int insert(GzfSpecialPayment record);

    int insertSelective(GzfSpecialPayment record);

    List<GzfSpecialPayment> selectByExample(GzfSpecialPaymentExample example);

    GzfSpecialPayment selectByPrimaryKey(Integer spePaymentId);

    int updateByExampleSelective(@Param("record") GzfSpecialPayment record, @Param("example") GzfSpecialPaymentExample example);

    int updateByExample(@Param("record") GzfSpecialPayment record, @Param("example") GzfSpecialPaymentExample example);

    int updateByPrimaryKeySelective(GzfSpecialPayment record);

    int updateByPrimaryKey(GzfSpecialPayment record);
}