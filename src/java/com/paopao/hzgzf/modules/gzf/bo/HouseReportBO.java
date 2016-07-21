package com.paopao.hzgzf.modules.gzf.bo;

import java.util.List;
import java.util.Map;

/**
 * Created by FastLane on 2016-02-28.
 */
public class HouseReportBO extends ReportBO {

    public Map<String, List<ReportBO>> getSubReports() {
        return subReports;
    }

    public void setSubReports(
      Map<String, List<ReportBO>> subReports) {
        this.subReports = subReports;
    }

    private Map<String,List<ReportBO>> subReports;
}
