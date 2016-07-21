package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalance;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfAccountBalanceMapper extends CrudDao<GzfAccountBalance>{
    int countByExample(GzfAccountBalanceExample example);

    int deleteByExample(GzfAccountBalanceExample example);

    int deleteByPrimaryKey(String id);

    int insert(GzfAccountBalance record);

    int insertSelective(GzfAccountBalance record);

    List<GzfAccountBalance> selectByExample(GzfAccountBalanceExample example);

    GzfAccountBalance selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GzfAccountBalance record, @Param("example") GzfAccountBalanceExample example);

    int updateByExample(@Param("record") GzfAccountBalance record, @Param("example") GzfAccountBalanceExample example);

    int updateByPrimaryKeySelective(GzfAccountBalance record);

    int updateByPrimaryKey(GzfAccountBalance record);
    
    List<GzfAccountBalance> getBalancesByAccountId(String accountId);
    
    List<GzfAccountBalance> getGroupedBalanceByAccountId(String accountId);
    
    GzfAccountBalance getSumBalanceByAcctIdAndSpecId(GzfAccountBalance record);
    
    GzfAccountBalance getSumBalanceByAcctIdAndAcctItemTypeId(GzfAccountBalance record);
}