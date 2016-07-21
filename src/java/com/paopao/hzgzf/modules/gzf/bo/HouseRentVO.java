package com.paopao.hzgzf.modules.gzf.bo;

import com.paopao.hzgzf.modules.gzf.entity.GzfHouseInfo;
import com.paopao.hzgzf.modules.gzf.entity.GzfHousePerson;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by FastLane on 2016-04-16.
 */
public class HouseRentVO {

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public List<List<GzfHousePerson>> getGzfHousePersonListList() {
        return gzfHousePersonListList;
    }

    public void setGzfHousePersonListList(
      List<List<GzfHousePerson>> gzfHousePersonListList) {
        this.gzfHousePersonListList = gzfHousePersonListList;
    }

    private String uniqueKey;

    private List<List<GzfHousePerson>> gzfHousePersonListList;

    public List<List<GzfHouseInfo>> getGzfHouseInfoListList() {
        return gzfHouseInfoListList;
    }

    public void setGzfHouseInfoListList(
      List<List<GzfHouseInfo>> gzfHouseInfoListList) {
        this.gzfHouseInfoListList = gzfHouseInfoListList;
    }

    private List<List<GzfHouseInfo>> gzfHouseInfoListList;

}
