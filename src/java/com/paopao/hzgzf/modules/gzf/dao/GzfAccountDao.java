package com.paopao.hzgzf.modules.gzf.dao;

import com.paopao.hzgzf.common.persistence.CrudDao;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.modules.pay.entity.GzfAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by FastLane on 2016-03-19.
 */
public interface GzfAccountDao extends CrudDao<GzfAccount> {

   List<GzfAccount> findAllList(@Param("gzfAccount") GzfAccount gzfAccount, @Param("page") Page page, @Param("DEL_FLAG_NORMAL") Integer flag);

}
