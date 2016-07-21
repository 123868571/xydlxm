package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItemType;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItemTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfAcctItemTypeMapper extends CrudDao<GzfAcctItemType>{
    int countByExample(GzfAcctItemTypeExample example);

    int deleteByExample(GzfAcctItemTypeExample example);

    int deleteByPrimaryKey(Integer acctItemTypeId);

    int insert(GzfAcctItemType record);

    int insertSelective(GzfAcctItemType record);

    List<GzfAcctItemType> selectByExample(GzfAcctItemTypeExample example);

    GzfAcctItemType selectByPrimaryKey(Integer acctItemTypeId);

    int updateByExampleSelective(@Param("record") GzfAcctItemType record, @Param("example") GzfAcctItemTypeExample example);

    int updateByExample(@Param("record") GzfAcctItemType record, @Param("example") GzfAcctItemTypeExample example);

    int updateByPrimaryKeySelective(GzfAcctItemType record);

    int updateByPrimaryKey(GzfAcctItemType record);
}