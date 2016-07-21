package com.paopao.hzgzf.modules.pay.service;

import com.paopao.hzgzf.common.utils.EhCacheUtils;
import com.paopao.hzgzf.modules.gzf.bo.MainBaseReportBO;
import com.paopao.hzgzf.modules.gzf.bo.ReportDictConstant;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报表相关定时任务
 * <p/>
 * Created by FastLane on 2016-05-07.
 */
@Component @Lazy(false)
public class GzfReportTask {

    @Scheduled(cron = "0 0 2 1 * ?")
    public void updateCachedReportInMainPage() {

        // basic report
        List<MainBaseReportBO> mainBaseReportBOs;
        EhCacheUtils.remove(ReportDictConstant.DICT_CACHE_REPORT,
          ReportDictConstant.DICT_CACHE_KEY_MAIN_BASE_REPORT);

        // Rent
        EhCacheUtils.remove(ReportDictConstant.DICT_CACHE_REPORT,
          ReportDictConstant.DICT_CACHE_KEY_RENT_REPORT);

        // Property
        EhCacheUtils.remove(ReportDictConstant.DICT_CACHE_REPORT,
          ReportDictConstant.DICT_CACHE_KEY_PROPERTY_REPORT);

        // Water&Electricity
        EhCacheUtils.remove(ReportDictConstant.DICT_CACHE_REPORT,
          ReportDictConstant.DICT_CACHE_KEY_WATER_ELECTIC_REPORT);

    }

}
