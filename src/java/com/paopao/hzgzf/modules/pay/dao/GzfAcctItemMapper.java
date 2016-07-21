package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItem;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfAcctItemMapper extends CrudDao<GzfAcctItem>{
    int countByExample(GzfAcctItemExample example);

    int deleteByExample(GzfAcctItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(GzfAcctItem record);

    int insertSelective(GzfAcctItem record);

    List<GzfAcctItem> selectByExample(GzfAcctItemExample example);

    GzfAcctItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GzfAcctItem record, @Param("example") GzfAcctItemExample example);

    int updateByExample(@Param("record") GzfAcctItem record, @Param("example") GzfAcctItemExample example);

    int updateByPrimaryKeySelective(GzfAcctItem record);

    int updateByPrimaryKey(GzfAcctItem record);
    
    List<GzfAcctItem> getSumMoney(GzfAcctItem record);

    List<GzfAcctItem> selectByGzfAcctItemEntity(GzfAcctItem entity);
}
