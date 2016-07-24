function postJson(url, datas , callback){  
	$.ajax({
        url: url,
        type: "get",
        data : datas ,
        cache: false,
        //dataType: "json",
        contentType:'text/plain; charset=utf-8',
        success: function(json) {
        	//var json2 = jQuery.parseJSON(json);
            /* if(json.data && json.data.returnCode && json.data.returnMessage && json.data.returnCode!='0'){
                window.location.href = "business?service=page/PageError500";
            }else if (json.data && json.data.returnCode && json.data.returnMessage && json.data.returnCode=='530') {
                window.location.href = "/";
            }else{ */
                //符合Wade框架
                callback("success", json);
            //}
        },
        error: function(e) {
        	alert("出错了！！");
        }
    });
}  


//获取当前月的第一天和当天以及时分秒
function setFirstAndLastMonthDayAndSec(startInput, endInput , flag ){
	var curDate = new Date();
	//var fdate = curDate.formatDD( "yyyy-MM-01");
	//var ldate = curDate.formatDD( "yyyy-MM-DD");
	var fdate = curDate.formatDD( "yyyy-MM-01 00:00:00");
	var ldate = curDate.formatDD( "yyyy-MM-DD HH:mm:ss");
	if(flag){
		//fdate = curDate.formatDD( "yyyy-01-01");
		fdate = curDate.formatDD( "yyyy-01-01 00:00:00");
	}
	$("#"+startInput).attr("value",fdate);
	$("#"+endInput).attr("value",ldate);
	$("#"+startInput).val(fdate).attr("value",fdate);
	$("#"+endInput).val(ldate).attr("value",ldate);
	return fdate+","+ldate;
}

Date.prototype.formatDD = function( formatStr){
	var date = this;
	var str = formatStr;
	str=str.replace(/yyyy|YYYY/,date.getFullYear());
	str=str.replace(/yy|YY/,(date.getYear() % 100)>9?(date.getYear() % 100).toString():"0" + (date.getYear() % 100));
	str=str.replace(/MM/,date.getMonth()>8?(date.getMonth()+1).toString():"0" + (date.getMonth()+1));
	str=str.replace(/M/g,date.getMonth()+1);
	str=str.replace(/dd|DD/,date.getDate()>9?date.getDate().toString():"0" + date.getDate());
	str=str.replace(/d|D/g,date.getDate());
	str=str.replace(/hh|HH/,date.getHours()>9?date.getHours().toString():"0" + date.getHours());
	str=str.replace(/h|H/g,date.getHours());
	str=str.replace(/mm/,date.getMinutes()>9?date.getMinutes().toString():"0" + date.getMinutes());
	str=str.replace(/m/g,date.getMinutes());
	str=str.replace(/ss|SS/,date.getSeconds()>9?date.getSeconds().toString():"0" + date.getSeconds());
	str=str.replace(/s|S/g,date.getSeconds());
	return str;
}

//获取两个日期间的月数
function getMonths(begDateStr, endDateStr){
	var date1 = begDateStr.split("-");
    var date2 = endDateStr.split("-");
    var year1 = parseInt(date1[0]);
    var month1 = parseInt(date1[1]); 
    var year2 = parseInt(date2[0]); 
    var month2 = parseInt(date2[1]);
    var day1 = parseInt(date1[2]);
    var day2 = parseInt(date2[2]);
    var months = (year2 - year1) * 12 + (month2-month1);
    if(day2 < day1){
    	months = months-1;
    }
    return months;   
}

//获取两个日期间的天数(只计算天)
function getDays(begDateStr, endDateStr){
	var date1 = begDateStr.split("-");
    var date2 = endDateStr.split("-");
    var day1 = parseInt(date1[2]);
    var day2 = parseInt(date2[2]);
    var days = day2-day1;
    if(day2 < day1){//如2015-05-21和2015-7-10
    	var months = getMonths(begDateStr, endDateStr);
    	var tempDate = addMonth(begDateStr, months);
    	var maxDay = getMaxDay(tempDate);
    	days = day2 + (maxDay-day1);
    }
    return days;   
}

function getMaxDay(dateStr){
	var date1 = dateStr.split("-");
	var date = new Date(date1[0],date1[1],0);
	return date.getDate();
}

function addMonth(dateStr, amount){
	var arr = dateStr.split("-");
	var date = new Date(parseInt(arr[0]), parseInt(arr[1])-1, parseInt(arr[2]));
	date.setMonth(parseInt(date.getMonth()) + parseInt(amount));
	return date.formatDD("yyyy-MM-DD");
}	


$(document.body).ready(function(){
	
	jQuery('body').on('click', '.portlet .tools .collapse, .portlet .tools .expand', function (e) {
        e.preventDefault();
            var el = jQuery(this).closest(".portlet").children(".portlet-body");
            if (jQuery(this).hasClass("collapse")) {
                jQuery(this).removeClass("collapse").addClass("expand");
                el.slideUp(200);
            } else {
                jQuery(this).removeClass("expand").addClass("collapse");
                el.slideDown(200);
            }
    });
	
})