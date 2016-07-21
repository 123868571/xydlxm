package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfInvoice;
import com.paopao.hzgzf.modules.pay.entity.GzfInvoiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfInvoiceMapper extends CrudDao<GzfInvoice>{
    int countByExample(GzfInvoiceExample example);

    int deleteByExample(GzfInvoiceExample example);

    int deleteByPrimaryKey(String id);

    int insert(GzfInvoice record);

    int insertSelective(GzfInvoice record);

    List<GzfInvoice> selectByExample(GzfInvoiceExample example);

    GzfInvoice selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GzfInvoice record, @Param("example") GzfInvoiceExample example);

    int updateByExample(@Param("record") GzfInvoice record, @Param("example") GzfInvoiceExample example);

    int updateByPrimaryKeySelective(GzfInvoice record);

    int updateByPrimaryKey(GzfInvoice record);
}