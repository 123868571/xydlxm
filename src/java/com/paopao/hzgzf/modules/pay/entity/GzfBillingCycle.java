package com.paopao.hzgzf.modules.pay.entity;

import java.util.Date;

import com.paopao.hzgzf.common.persistence.DataEntity;
/**
 * 帐单周期
 * @author songyh
 *
 */
public class GzfBillingCycle extends DataEntity<GzfBillingCycle>{
	private static final long serialVersionUID = 1L;

	private Integer biilingCycleId;//帐单周期，如201601

    private Date beginDate;

    private Date endDate;

    private Integer lastBillingCycleId;//上个帐单周期，如201512

    private Date stateDate;

    private Integer nextBillingCycleId;//下个帐单周期，如201602
    
    public GzfBillingCycle(){
    	super();
    }
    
    public GzfBillingCycle(Integer biilingCycleId){
    	super(biilingCycleId.toString());
    }
    
    public Integer getBiilingCycleId() {
        return biilingCycleId;
    }

    public void setBiilingCycleId(Integer biilingCycleId) {
        this.biilingCycleId = biilingCycleId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getLastBillingCycleId() {
        return lastBillingCycleId;
    }

    public void setLastBillingCycleId(Integer lastBillingCycleId) {
        this.lastBillingCycleId = lastBillingCycleId;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public Integer getNextBillingCycleId() {
        return nextBillingCycleId;
    }

    public void setNextBillingCycleId(Integer nextBillingCycleId) {
        this.nextBillingCycleId = nextBillingCycleId;
    }
}