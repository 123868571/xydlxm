package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalance;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalanceExample;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalanceHis;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfAccountBalanceHisMapper extends CrudDao<GzfAccountBalanceHis>{
    int countByExample(GzfAccountBalanceExample example);

    int deleteByExample(GzfAccountBalanceExample example);

    int deleteByPrimaryKey(String id);

    int insert(GzfAccountBalanceHis record);

    int insertSelective(GzfAccountBalanceHis record);

    List<GzfAccountBalance> selectByExample(GzfAccountBalanceExample example);

    GzfAccountBalance selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GzfAccountBalanceHis record, @Param("example") GzfAccountBalanceExample example);

    int updateByExample(@Param("record") GzfAccountBalanceHis record, @Param("example") GzfAccountBalanceExample example);

    int updateByPrimaryKeySelective(GzfAccountBalanceHis record);

    int updateByPrimaryKey(GzfAccountBalanceHis record);
    
    List<GzfAccountBalance> getBalancesByAccountId(String accountId);
    
    List<GzfAccountBalance> getGroupedBalanceByAccountId(String accountId);
}