/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 缴费标准Entity
 * @author Hongjun
 * @version 2016-01-02
 */
public class GzfPaymentStandard extends DataEntity<GzfPaymentStandard> {

    private static final long serialVersionUID = 1L;
    private String            name;                 // 名称
    private Double            rentUnitPrice;        // 房屋单价
    private Double            managementFee;        // 物业费
    private Double            consumption;          // 能耗费
    private Double            waterFee;             // 水费
    private Double            electricityFee;       // 电费
    private Double            naturalgasFee;        // 天然气费
    private Double            deposit;              // 押金

    public GzfPaymentStandard() {
        super();
    }

    public GzfPaymentStandard(String id) {
        super(id);
    }

    @Length(min = 1, max = 100, message = "名称长度必须介于 1 和 100 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "房屋单价不能为空")
    public Double getRentUnitPrice() {
        return rentUnitPrice;
    }

    public void setRentUnitPrice(Double rentUnitPrice) {
        this.rentUnitPrice = rentUnitPrice;
    }

    @NotNull(message = "物业费不能为空")
    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    @NotNull(message = "能耗费不能为空")
    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    @NotNull(message = "水费不能为空")
    public Double getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(Double waterFee) {
        this.waterFee = waterFee;
    }

    @NotNull(message = "电费不能为空")
    public Double getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(Double electricityFee) {
        this.electricityFee = electricityFee;
    }

    @NotNull(message = "天然气费不能为空")
    public Double getNaturalgasFee() {
        return naturalgasFee;
    }

    public void setNaturalgasFee(Double naturalgasFee) {
        this.naturalgasFee = naturalgasFee;
    }

    @NotNull(message = "押金不能为空")
    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return name;
    }

}