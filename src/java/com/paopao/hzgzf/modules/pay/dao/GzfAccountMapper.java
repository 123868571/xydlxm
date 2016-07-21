package com.paopao.hzgzf.modules.pay.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.persistence.annotation.MyBatisDao;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import com.paopao.hzgzf.modules.pay.entity.GzfAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface GzfAccountMapper extends CrudDao<GzfAccount>{
    int countByExample(GzfAccountExample example);

    int deleteByExample(GzfAccountExample example);

    int deleteByPrimaryKey(String id);

    int insert(GzfAccount record);

    int insertSelective(GzfAccount record);

    List<GzfAccount> selectByExample(GzfAccountExample example);

    GzfAccount selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GzfAccount record, @Param("example") GzfAccountExample example);

    int updateByExample(@Param("record") GzfAccount record, @Param("example") GzfAccountExample example);

    int updateByPrimaryKeySelective(GzfAccount record);

    int updateByPrimaryKey(GzfAccount record);

    List<GzfAccount> findAllList(@Param("gzfAccount") GzfAccount gzfAccount, @Param("page")
            Page page, @Param("DEL_FLAG_NORMAL") Integer flag);
    
    List<GzfAccount> findListJoin(GzfAccount entity);
}
