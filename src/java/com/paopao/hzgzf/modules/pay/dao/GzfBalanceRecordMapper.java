package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfBalanceRecord;
import com.paopao.hzgzf.modules.pay.entity.GzfBalanceRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfBalanceRecordMapper extends CrudDao<GzfBalanceRecord>{
    int countByExample(GzfBalanceRecordExample example);

    int deleteByExample(GzfBalanceRecordExample example);

    int deleteByPrimaryKey(String operId);

    int insert(GzfBalanceRecord record);

    int insertSelective(GzfBalanceRecord record);

    List<GzfBalanceRecord> selectByExample(GzfBalanceRecordExample example);

    GzfBalanceRecord selectByPrimaryKey(String operId);

    int updateByExampleSelective(@Param("record") GzfBalanceRecord record, @Param("example") GzfBalanceRecordExample example);

    int updateByExample(@Param("record") GzfBalanceRecord record, @Param("example") GzfBalanceRecordExample example);

    int updateByPrimaryKeySelective(GzfBalanceRecord record);

    int updateByPrimaryKey(GzfBalanceRecord record);
}