package com.paopao.hzgzf.modules.pay.entity;

import com.paopao.hzgzf.common.persistence.DataEntity;
/**
 * 付费方式与余额类型映射
 * @author songyh
 *
 */
public class GzfPaymethodBalancetype extends DataEntity<GzfPaymethodBalancetype>{
	private static final long serialVersionUID = 1L;

	private String payMethod;//1.现金缴费 2.银行代收

    private String channelType;//操作渠道（APP：手机终端  OFFICE：营业窗口）

    private Integer balanceTypeId;

    public GzfPaymethodBalancetype(){
    	super();
    }
    
    public GzfPaymethodBalancetype(String id){
    	super(id);
    }
    
    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod == null ? null : payMethod.trim();
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType == null ? null : channelType.trim();
    }

    public Integer getBalanceTypeId() {
        return balanceTypeId;
    }

    public void setBalanceTypeId(Integer balanceTypeId) {
        this.balanceTypeId = balanceTypeId;
    }

}