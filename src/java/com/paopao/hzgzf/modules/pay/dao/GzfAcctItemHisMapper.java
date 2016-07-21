package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItemHis;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItemHisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfAcctItemHisMapper extends CrudDao<GzfAcctItemHis>{
    int countByExample(GzfAcctItemHisExample example);

    int deleteByExample(GzfAcctItemHisExample example);

    int deleteByPrimaryKey(String id);

    int insert(GzfAcctItemHis record);

    int insertSelective(GzfAcctItemHis record);

    List<GzfAcctItemHis> selectByExample(GzfAcctItemHisExample example);

    GzfAcctItemHis selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GzfAcctItemHis record, @Param("example") GzfAcctItemHisExample example);

    int updateByExample(@Param("record") GzfAcctItemHis record, @Param("example") GzfAcctItemHisExample example);

    int updateByPrimaryKeySelective(GzfAcctItemHis record);

    int updateByPrimaryKey(GzfAcctItemHis record);
}