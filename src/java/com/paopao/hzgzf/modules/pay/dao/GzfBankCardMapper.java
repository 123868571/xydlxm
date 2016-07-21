package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfBankCard;
import com.paopao.hzgzf.modules.pay.entity.GzfBankCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfBankCardMapper extends CrudDao<GzfBankCard>{
    int countByExample(GzfBankCardExample example);

    int deleteByExample(GzfBankCardExample example);

    int deleteByPrimaryKey(String id);

    int insert(GzfBankCard record);

    int insertSelective(GzfBankCard record);

    List<GzfBankCard> selectByExample(GzfBankCardExample example);

    GzfBankCard selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GzfBankCard record, @Param("example") GzfBankCardExample example);

    int updateByExample(@Param("record") GzfBankCard record, @Param("example") GzfBankCardExample example);

    int updateByPrimaryKeySelective(GzfBankCard record);

    int updateByPrimaryKey(GzfBankCard record);
    
    void deleteByAccountAndCardNo(GzfBankCard record);
}