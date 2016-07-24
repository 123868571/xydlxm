$(document).ready(function(){
	 if ($("#rent_standard").val().length<=0)
	 {
	 	$("#rent_standard").parent().hide();
	 }
	 
	 if ($("#operation_log").val().length<=0)
	 {
	 	$("#operation_log").parent().hide();
	 }
})
//信息中心
var info =  ["信息中心", "基础信息", "房屋信息", "人员信息", "房源审核", "小区管理"];
var httpinfo = new Array();
    httpinfo[1] = "basic_infomation.html";
    httpinfo[2] = "house_infomation.html";
    httpinfo[3] = "household_infomation.html";
    httpinfo[4] = "housing_audit.html";
    httpinfo[5] = "district_manage.html";
    //房屋管理
var house =  ["房屋管理","交房管理","退房管理","续租管理","换房管理"];
var httphouse = new Array();
    httphouse[1] = "binding_house.html";
    httphouse[2] = "checkout_house.html";
    httphouse[3] = "lease_house.html";
    httphouse[4] = "change_house.html";
 //缴费管理
var pay =  ["缴费管理", "", "房租缴费", "物业缴费", "水电缴费"];
var httppay = new Array();
    httppay[1] = "rent_standard.html";
    httppay[2] = "rent_payment.html";
    httppay[3] = "property_payment.html";
    httppay[4] = "other_payment.html";
    //物业管理
var property =  ["物业管理", "提醒设置", "设备录入", "设备管理", "报修管理", "维修管理"];
var httpro = new Array();
    httpro[1] = "daily_manage.html";
    httpro[2] = "equipment_input.html";
    httpro[3] = "equipment_manage.html";
    httpro[4] = "repair_manage.html";
    httpro[5] = "maintenance_manage.html";
    //数据报表
var data =  ["数据报表", "房源报表", "人员报表", "缴费报表", "维修报表"];
var httpdata = new Array();
    httpdata[1] = "housing_report.html";
    httpdata[2] = "staff_report.html";
    httpdata[3] = "payment_report.html";
    httpdata[4] = "repair_report.html";
    //策略管理
var strategy =  ["策略管理", "层级名称", "交费标准"];
var httpstra = new Array();
    httpstra[1] = "level_name.html";
    httpstra[2] = "payment_standard.html";
    //权限管理
var auth =  ["权限管理", "权限设置", ""];
var httpauth = new Array();
    httpauth[1] = "permission_set.html";
    httpauth[2] = "operation_log.html";
    //其它管理
var other =  ["其它管理", "日志查询", "忘记密码"];
var httpother = new Array();
    httpother[1] = "log_query.html";
    httpother[2] = "modify_psw.html";

    document.getElementById("home").innerHTML="概况总览";
    document.getElementById("home").href="index.html";
    //信息中心ID
    document.getElementById("infotitle").innerHTML=info[0];
    document.getElementById("basic_info").innerHTML=info[1];
    document.getElementById("basic_info").href=httpinfo[1];
    document.getElementById("house_info").innerHTML=info[2];
    document.getElementById("house_info").href=httpinfo[2];
    document.getElementById("househode_info").innerHTML=info[3];
    document.getElementById("househode_info").href=httpinfo[3];
    document.getElementById("house_audit").innerHTML=info[4];
    document.getElementById("house_audit").href=httpinfo[4];
     document.getElementById("district_manage").innerHTML=info[5];
    document.getElementById("district_manage").href=httpinfo[5];   
    
    //房屋管理ID
    document.getElementById("house_manage").innerHTML=house[0];
    document.getElementById("binding_house").innerHTML=house[1];
    document.getElementById("binding_house").href=httphouse[1]; 
    document.getElementById("checkout_house").innerHTML=house[2];
    document.getElementById("checkout_house").href=httphouse[2];
    document.getElementById("lease_house").innerHTML=house[3];
    document.getElementById("lease_house").href=httphouse[3];
    document.getElementById("change_house").innerHTML=house[4];
    document.getElementById("change_house").href=httphouse[4];
    //缴费管理ID
    document.getElementById("payment_manage").innerHTML=pay[0];
    document.getElementById("rent_standard").innerHTML=pay[1];
    document.getElementById("rent_standard").href=httppay[1]; 
    document.getElementById("rent_payment").innerHTML=pay[2];
    document.getElementById("rent_payment").href=httppay[2];
    document.getElementById("property_payment").innerHTML=pay[3];
    document.getElementById("property_payment").href=httppay[3];
    document.getElementById("other_payment").innerHTML=pay[4];
    document.getElementById("other_payment").href=httppay[4];
    //物业管理ID
    document.getElementById("property_manage").innerHTML=property[0];
    document.getElementById("daily_manage").innerHTML=property[1];
    document.getElementById("daily_manage").href=httpro[1]; 
    document.getElementById("equipment_input").innerHTML=property[2];
    document.getElementById("equipment_input").href=httpro[2];
    document.getElementById("equipment_manage").innerHTML=property[3];
    document.getElementById("equipment_manage").href=httpro[3];
    document.getElementById("repair_manage").innerHTML=property[4];
    document.getElementById("repair_manage").href=httpro[4];
    document.getElementById("maintenance_manage").innerHTML=property[5];
    document.getElementById("maintenance_manage").href=httpro[5];
    //数据报表ID
    document.getElementById("data_report").innerHTML=data[0];
    document.getElementById("housing_report").innerHTML=data[1];
    document.getElementById("housing_report").href=httpdata[1]; 
    document.getElementById("staff_report").innerHTML=data[2];
    document.getElementById("staff_report").href=httpdata[2];
    document.getElementById("payment_report").innerHTML=data[3];
    document.getElementById("payment_report").href=httpdata[3];
    document.getElementById("repair_report").innerHTML=data[4];
    document.getElementById("repair_report").href=httpdata[4];
    //策略管理ID
    document.getElementById("policy_manage").innerHTML=strategy[0];
    document.getElementById("level_name").innerHTML=strategy[1];
    document.getElementById("level_name").href=httpstra[1]; 
    document.getElementById("payment_standard").innerHTML=strategy[2];
    document.getElementById("payment_standard").href=httpstra[2];
    //权限管理ID
    document.getElementById("authority_manage").innerHTML=auth[0];
    document.getElementById("permission_set").innerHTML=auth[1];
    document.getElementById("permission_set").href=httpauth[1]; 
    document.getElementById("operation_log").innerHTML=auth[2];
    document.getElementById("operation_log").href=httpauth[2];
    //其它管理ID
    document.getElementById("other_manage").innerHTML=other[0];
    document.getElementById("log_query").innerHTML=other[1];
    document.getElementById("log_query").href=httpother[1];
    document.getElementById("modify_psw").innerHTML=other[2];
    document.getElementById("modify_psw").href=httpother[2];
