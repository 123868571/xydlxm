package com.paopao.hzgzf.modules.gzf.bo;

import java.util.List;

/**
 * Created by FastLane on 2016-02-21.
 */
public class ProjectRepairReportBo extends ReportBO {
    public List<ReportBO> getSubReports() {
        return subReports;
    }

    public void setSubReports(List<ReportBO> subReports) {
        this.subReports = subReports;
    }

    private List<ReportBO> subReports;
}
