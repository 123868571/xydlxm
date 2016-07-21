package com.paopao.hzgzf.modules.sys.utils;

import com.paopao.hzgzf.common.utils.SpringContextHolder;
import com.paopao.hzgzf.modules.sys.dao.AreaDao;
import com.paopao.hzgzf.modules.sys.entity.Area;

import java.util.List;

/**
 * Created by FastLane on 2016-01-12.
 */
public class AreaUtils {

    private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);

    public static List<Area> getAreaListByPids(String pid) {
        return areaDao.findAreaListByPid(pid, 0);
    }

}
