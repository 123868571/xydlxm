package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfBillRecord;
import com.paopao.hzgzf.modules.pay.entity.GzfBillRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfBillRecordMapper extends CrudDao<GzfBillRecord>{
    int countByExample(GzfBillRecordExample example);

    int deleteByExample(GzfBillRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(GzfBillRecord record);

    int insertSelective(GzfBillRecord record);

    List<GzfBillRecord> selectByExample(GzfBillRecordExample example);

    GzfBillRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GzfBillRecord record, @Param("example") GzfBillRecordExample example);

    int updateByExample(@Param("record") GzfBillRecord record, @Param("example") GzfBillRecordExample example);

    int updateByPrimaryKeySelective(GzfBillRecord record);

    int updateByPrimaryKey(GzfBillRecord record);
}