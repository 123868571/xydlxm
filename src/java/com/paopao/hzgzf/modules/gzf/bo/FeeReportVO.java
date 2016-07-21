package com.paopao.hzgzf.modules.gzf.bo;

import java.util.List;
import java.util.Map;

/**
 * Created by FastLane on 2016-04-17.
 */
public class FeeReportVO extends ReportVO {
    public List<ReportBO> getReportBOList() {
        return reportBOList;
    }

    public void setReportBOList(List<ReportBO> reportBOList) {
        this.reportBOList = reportBOList;
    }

    private List<ReportBO> reportBOList;
}
