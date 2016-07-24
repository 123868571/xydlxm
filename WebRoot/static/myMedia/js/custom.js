var myDatePicker = function(){
    $("input.date-picker").daterangepicker({
          singleDatePicker: true,
          showDropdowns: true,
          locale: {
            format: 'YYYY-MM-DD',
            separator: ' - ',
            applyLabel: '确认',
            cancelLabel: '取消',
            fromLabel: 'From',
            toLabel: 'To',
            customRangeLabel: 'Custom',
            daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            firstDay: 1
          }
    });
};

var initReportYear = function(){
    var curYear = new Date().getFullYear();
    var period = 15;
    var start = curYear - period;
    var end = curYear + period;
    for (var i = start; i < end; i++){
        var option = "";
        if (i == curYear) {
            option  = "<option selected='selected' value='" + i + "'>" + i + "年</option>";
        }else {
            option  = "<option value='" + i + "'>" + i + "年</option>";
        }
        $(".report-year").append(option);
    }
};

var initReportPalaces = function(url){
    $("#gzf-villages-select").change(function(){
          var villageId = $("#gzf-villages-select").val();
          $.ajax({
                url : url,
                data : {villageId:villageId},
                async : true
          }).done(function(data){
              var obj = eval(data);
              jQuery("#gzf-palaces-select").empty();
              $("#gzf-palaces-select").append('<option value="0">所有苑</option>');
              for(var key in obj){
                  $("#gzf-palaces-select").append('<option value="' + obj[key].id + '">' + obj[key].name + '</option>');
              }
          });
    });
    $("#gzf-palaces-select").change(function(){
        var gzfPalacesId = $("#gzf-palaces-select").val();
    });
};

var page = function(n,s){
    $("#pageNo").val(n);
    $("#pageSize").val(s);
    $("#searchForm").submit();
    return false;
};

$(document).ready(function(){
    // 初始化
    myDatePicker();

    initReportYear();

    // 时间控件
    $(".add-on").on("click", function(){
        console.log($(this).prev("input.date-picker"));
    });

    // 导入按钮
    $("#btnImport").click(function(){
        $.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
            bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
    });


});
