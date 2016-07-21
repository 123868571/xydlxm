/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.gzf.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paopao.hzgzf.common.persistence.DataEntity;
import com.paopao.hzgzf.common.utils.excel.annotation.ExcelField;

/**
 * 人员信息Entity
 * @author Hongjun
 * @version 2016-01-02
 */
public class GzfHouseholdInfo extends DataEntity<GzfHouseholdInfo> {

    private static final long serialVersionUID = 1L;
    private String            name;                 // 姓名
    private Integer           sex              = 1; // 性别
    private String            company;              // 工作单位
    private Integer           industry;             // 单位所属行业
    private Date              workTime;             // 参加工作时间
    private String            province;             // 省
    private String            city;                 // 市
    private String            area;                 // 区
    private String            address;              // 详细地址
    private Integer           education;            // 学历
    private Integer           jobCategory;          // 工作性质
    private Integer           household;            // 户口属性
    private Integer           incomeRange;          // 收入范围
    private String            phone;                // 联系电话
    private String            cardid;               // 身份证号
    private String            age;                  // 年龄
    private String            nativeProvince;       // 籍贯省
    private String            nativeCity;           // 籍贯市
    private String            nativeArea;           // 籍贯区
    private String            nativeAddress;        // 籍贯地址
    private String            emergencyContact;     // 紧急联系人
    private String            emergencyPhone;       // 紧急联系电话
    private String            cardnum;              // 房卡个数
    private Integer           maritalStatus    = 1; // 婚姻状况
    private Integer           politicalStatus;      // 政治面貌
    private String            gzfPaymentStandardId; // 缴费标准

    private GzfHouseInfo      gzfHouseInfo;

    private GzfHousePerson    gzfHousePerson;

    private String            allSelect;

    private Date              startTime;

    private Date              endTime;

    private int               code;                 //cs端对应

    private String            personFlag;
    private int               sync;
    private Integer           nextStep;              //下次分房
    private Integer           personal;              //人员属性

    public Integer getNextStep() {
        return nextStep;
    }

    public void setNextStep(Integer nextStep) {
        this.nextStep = nextStep;
    }

    public Integer getPersonal() {
        return personal;
    }

    public void setPersonal(Integer personal) {
        this.personal = personal;
    }

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public String getPersonFlag() {
        return personFlag;
    }

    public void setPersonFlag(String personFlag) {
        this.personFlag = personFlag;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAllSelect() {
        return allSelect;
    }

    public void setAllSelect(String allSelect) {
        this.allSelect = allSelect;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    private String             allName;

    private GzfPaymentStandard paymentStandard;

    public String getAllName() {
        return allName;
    }

    public void setAllName(String allName) {
        this.allName = allName;
    }

    public GzfPaymentStandard getPaymentStandard() {
        return paymentStandard;
    }

    public void setPaymentStandard(GzfPaymentStandard paymentStandard) {
        this.paymentStandard = paymentStandard;
    }

    public GzfHousePerson getGzfHousePerson() {

        return gzfHousePerson;
    }

    public void setGzfHousePerson(GzfHousePerson gzfHousePerson) {
        this.gzfHousePerson = gzfHousePerson;
    }

    public GzfHouseInfo getGzfHouseInfo() {
        return gzfHouseInfo;
    }

    public void setGzfHouseInfo(GzfHouseInfo gzfHouseInfo) {
        this.gzfHouseInfo = gzfHouseInfo;
    }

    private GzfPaymentStandard gzfPaymentStandard;

    @ExcelField(title = "缴费标准", align = 2, sort = 24)
    public GzfPaymentStandard getGzfPaymentStandard() {
        return gzfPaymentStandard;
    }

    public void setGzfPaymentStandard(GzfPaymentStandard gzfPaymentStandard) {
        this.gzfPaymentStandard = gzfPaymentStandard;
    }

    public GzfHouseholdInfo() {
        super();
    }

    public GzfHouseholdInfo(String id) {
        super(id);
    }

    @Length(min = 1, max = 64, message = "姓名长度必须介于 1 和 64 之间")
    @ExcelField(title = "姓名", align = 2, sort = 1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "性别不能为空")
    @ExcelField(title = "性别", align = 2, sort = 2, dictType = "sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Length(min = 1, max = 225, message = "工作单位长度必须介于 1 和 225 之间")
    @ExcelField(title = "工作单位", align = 2, sort = 3)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @NotNull(message = "单位所属行业不能为空")
    @ExcelField(title = "单位所属行业", align = 2, sort = 30, dictType = "industry")
    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "参加工作时间不能为空")
    @ExcelField(title = "参加工作时间", align = 2, sort = 31)
    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    @Length(min = 1, max = 64, message = "省长度必须介于 1 和 64 之间")
    @ExcelField(title = "省", align = 2, sort = 4)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Length(min = 1, max = 64, message = "市长度必须介于 1 和 64 之间")
    @ExcelField(title = "市", align = 2, sort = 5)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Length(min = 1, max = 64, message = "区长度必须介于 1 和 64 之间")
    @ExcelField(title = "区", align = 2, sort = 6)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Length(min = 1, max = 255, message = "详细地址长度必须介于 1 和 255 之间")
    @ExcelField(title = "详细地址", align = 2, sort = 7)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotNull(message = "学历不能为空")
    @ExcelField(title = "学历", align = 2, sort = 8, dictType = "education")
    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    @NotNull(message = "工作性质不能为空")
    @ExcelField(title = "工作性质", align = 2, sort = 9, dictType = "job_category")
    public Integer getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(Integer jobCategory) {
        this.jobCategory = jobCategory;
    }

    @NotNull(message = "户口属性不能为空")
    @ExcelField(title = "户口属性", align = 2, sort = 10, dictType = "household")
    public Integer getHousehold() {
        return household;
    }

    public void setHousehold(Integer household) {
        this.household = household;
    }

    @NotNull(message = "收入范围不能为空")
    @ExcelField(title = "收入范围", align = 2, sort = 11, dictType = "income_range")
    public Integer getIncomeRange() {
        return incomeRange;
    }

    public void setIncomeRange(Integer incomeRange) {
        this.incomeRange = incomeRange;
    }

    @Length(min = 1, max = 32, message = "联系电话长度必须介于 1 和 32 之间")
    @ExcelField(title = "联系电话", align = 2, sort = 12)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Length(min = 1, max = 64, message = "身份证号长度必须介于 1 和 64 之间")
    @ExcelField(title = "身份证号", align = 2, sort = 13)
    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    @NotNull(message = "年龄不能为空")
    @ExcelField(title = "年龄", align = 2, sort = 14)
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Length(min = 0, max = 64, message = "籍贯省长度必须介于 0 和 64 之间")
    @ExcelField(title = "籍贯省", align = 2, sort = 15)
    public String getNativeProvince() {
        return nativeProvince;
    }

    public void setNativeProvince(String nativeProvince) {
        this.nativeProvince = nativeProvince;
    }

    @Length(min = 0, max = 64, message = "籍贯市长度必须介于 0 和 64 之间")
    @ExcelField(title = "籍贯市", align = 2, sort = 16)
    public String getNativeCity() {
        return nativeCity;
    }

    public void setNativeCity(String nativeCity) {
        this.nativeCity = nativeCity;
    }

    @Length(min = 0, max = 64, message = "籍贯区长度必须介于 0 和 64 之间")
    @ExcelField(title = "籍贯区", align = 2, sort = 17)
    public String getNativeArea() {
        return nativeArea;
    }

    public void setNativeArea(String nativeArea) {
        this.nativeArea = nativeArea;
    }

    @Length(min = 0, max = 255, message = "籍贯地址长度必须介于 0 和 255 之间")
    @ExcelField(title = "籍贯地址", align = 2, sort = 18)
    public String getNativeAddress() {
        return nativeAddress;
    }

    public void setNativeAddress(String nativeAddress) {
        this.nativeAddress = nativeAddress;
    }

    @Length(min = 0, max = 64, message = "紧急联系人长度必须介于 0 和 64 之间")
    @ExcelField(title = "紧急联系人", align = 2, sort = 19)
    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    @Length(min = 0, max = 64, message = "紧急联系电话长度必须介于 0 和 64 之间")
    @ExcelField(title = "紧急联系电话", align = 2, sort = 20)
    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    @NotNull(message = "房卡个数不能为空")
    @ExcelField(title = "房卡个数", align = 2, sort = 21)
    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    @NotNull(message = "婚姻状况不能为空")
    @ExcelField(title = "婚姻状况", align = 2, sort = 22, dictType = "marital_status")
    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @NotNull(message = "政治面貌不能为空")
    @ExcelField(title = "政治面貌", align = 2, sort = 23, dictType = "political_status")
    public Integer getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(Integer politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    @Length(min = 1, max = 64, message = "缴费标准长度必须介于 1 和 64 之间")
    public String getGzfPaymentStandardId() {
        return gzfPaymentStandardId;
    }

    public void setGzfPaymentStandardId(String gzfPaymentStandardId) {
        this.gzfPaymentStandardId = gzfPaymentStandardId;
    }

}