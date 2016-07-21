package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfBalanceType;
import com.paopao.hzgzf.modules.pay.entity.GzfBalanceTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfBalanceTypeMapper extends CrudDao<GzfBalanceType>{
    int countByExample(GzfBalanceTypeExample example);

    int deleteByExample(GzfBalanceTypeExample example);

    int deleteByPrimaryKey(Integer balanceTypeId);

    int insert(GzfBalanceType record);

    int insertSelective(GzfBalanceType record);

    List<GzfBalanceType> selectByExample(GzfBalanceTypeExample example);

    GzfBalanceType selectByPrimaryKey(Integer balanceTypeId);

    int updateByExampleSelective(@Param("record") GzfBalanceType record, @Param("example") GzfBalanceTypeExample example);

    int updateByExample(@Param("record") GzfBalanceType record, @Param("example") GzfBalanceTypeExample example);

    int updateByPrimaryKeySelective(GzfBalanceType record);

    int updateByPrimaryKey(GzfBalanceType record);
}