package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctTransfer;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctTransferExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfAcctTransferMapper extends CrudDao<GzfAcctTransfer>{
    int countByExample(GzfAcctTransferExample example);

    int deleteByExample(GzfAcctTransferExample example);

    int deleteByPrimaryKey(String id);

    int insert(GzfAcctTransfer record);

    int insertSelective(GzfAcctTransfer record);

    List<GzfAcctTransfer> selectByExample(GzfAcctTransferExample example);

    GzfAcctTransfer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GzfAcctTransfer record, @Param("example") GzfAcctTransferExample example);

    int updateByExample(@Param("record") GzfAcctTransfer record, @Param("example") GzfAcctTransferExample example);

    int updateByPrimaryKeySelective(GzfAcctTransfer record);

    int updateByPrimaryKey(GzfAcctTransfer record);
}