<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	   String EtCellVersion = "CODEBASE='"
			+ request.getContextPath()
			+ "/WEB-INF/tlds/EtCell.CAB#version=3,8,7,408'";
%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<html>
<head>
<title>打印页面</title>
<script>
function init(){
	//var str = "诶必塞第衣爱抚即"; 
	//var str1 = str.subCHStr(1, 5); 
	//var str2 = str.subCHString(4, 6); 
	//alert(str1 + " == " + str2);
	printMoreInvoiceNew();//新发票
}

function printMoreInvoiceNew(){
	var number='${comWorkorder.id}';
	var operator='${comWorkorder.code}';
	var strContent='${comWorkorder.code}';
	var strWarning='${comWorkorder.code}';
	//var invoiceDate=g_GetSysDate();
	//var content1=cutstr(strContent,0,64);
	//var content2=cutstr(strContent,31,200);
	var content1=strContent.subCHString(0,64);
	var content2=strContent.subCHString(64,200);
	
	var warning1=strWarning.subCHString(0,64);
	var warning2=strWarning.subCHString(64,128);
	var warning3=strWarning.subCHString(128,200);
	
	var createDate='${comWorkorder.createDate}';
	var array=createDate.split("-");
	var nums = [ ];
	for(var i=0;i<array.length;i++){
		nums.push(array[i]);
	}
	alert("00000  "+nums[0]);
	alert("11111  "+nums[1]);
	
    var invoiceYear ='${comWorkorder.code}';
    var invoiceMonth = '${comWorkorder.code}';
    var invoiceDay = '${comWorkorder.code}';
    var invoiceHour = '${comWorkorder.code}';
    var invoiceMin = '${comWorkorder.code}';
    
    var startYear ='${comWorkorder.code}';
    var startMonth = '${comWorkorder.code}';
    var startDay = '${comWorkorder.code}';
    var startHour = '${comWorkorder.code}';
    var startMin = '${comWorkorder.code}';
    var endHour = '${comWorkorder.code}';
    var endMin = '${comWorkorder.code}';
    
    var worker='${comWorkorder.code}';

    var EtCell = document.all.item("EtCell");
    //EtCell.SetPrintPaperSize(500, 1271, 2000);
    EtCell.FileName = "<%=request.getContextPath()%>/template/workorder.eT";
    EtCell.SetAliasCell("name", "张湾");//开票日期：年月日
	EtCell.SetAliasCell("number", number);//行业分类
    EtCell.SetAliasCell("operator",operator);//手机号码
    EtCell.SetAliasCell("year",invoiceYear);//客户名称
    EtCell.SetAliasCell("month", invoiceMonth);//费用项
    EtCell.SetAliasCell("day",invoiceDay);//实收人民币（大写）
    EtCell.SetAliasCell("hour", invoiceHour);//实收小写
    EtCell.SetAliasCell("minute",invoiceMin);//受理编号
    EtCell.SetAliasCell("worker", worker);//收款员
        //别名待确定
    EtCell.SetAliasCell("content1", content1);// 收款单位
    EtCell.SetAliasCell("content2", content2);// 收款单位
    EtCell.SetAliasCell("startY", startYear);// 收款单位
    EtCell.SetAliasCell("startM", startMonth);// 收款单位
    EtCell.SetAliasCell("startD", startDay);// 收款单位
    EtCell.SetAliasCell("startH", startHour);//实收小写
    EtCell.SetAliasCell("startMin",startMin);//受理编号
    EtCell.SetAliasCell("endH", endHour);//实收小写
    EtCell.SetAliasCell("endMin",endMin);//受理编号
    EtCell.SetAliasCell("warning1", warning1);// 收款单位
    EtCell.SetAliasCell("warning2", warning2);// 收款单位
    EtCell.SetAliasCell("warning3", warning3);// 收款单位
}

function print()
{
        var EtCell = document.all.item("EtCell");
        EtCell.print(false);
        alert("工单打印成功!");
        document.getElementById("btnPrtBill").disabled = true;
        window.returnValue = "true";
}

function cancelAction()
{
    window.returnValue = "true";
    window.close();
}


function cutstr(str,num,len)  
{  
   var str_length = 0;  
   var str_len = 0;  
   str_cut = new String();  
   str_len = str.length;  
   for(var i = num;i<str_len;i++)  
     {  
        a = str.charAt(i);  
        str_length++;  
        if(escape(a).length > 4)  
        {  
         //中文字符的长度经编码之后大于4  
         str_length++;  
         }  
         str_cut = str_cut.concat(a);  
         if(str_length>=len)  
         {
         //str_cut = str_cut.concat("...");  
         return str_cut;  
         }  
    }  
    //如果给定字符串小于指定长度，则返回源字符串；  
    if(str_length<len){  
    	if(num<(str_len*2)){
    		return str_cut;
    	}else{
    		return  str;  
    	}
     
    }  
}


String.prototype.strLen = function() {
	var len = 0;
	for (var i = 0; i < this.length; i++) {
	if (this.charCodeAt(i) > 255 || this.charCodeAt(i) < 0) len += 2; else len ++;
}
return len;
}
//将字符串拆成字符，并存到数组中
String.prototype.strToChars = function(){
	var chars = new Array();
	for (var i = 0; i < this.length; i++){
		chars[i] = [this.substr(i, 1), this.isCHS(i)];
	}
	String.prototype.charsArray = chars;
	return chars;
}
//判断某个字符是否是汉字
String.prototype.isCHS = function(i){
	if (this.charCodeAt(i) > 255 || this.charCodeAt(i) < 0) 
		return true;
	else
		return false;
}
//截取字符串（从start字节到end字节）
String.prototype.subCHString = function(start, end){
	var len = 0;
	var str = "";
	this.strToChars();
	for (var i = 0; i < this.length; i++) {
		if(this.charsArray[i][1])
			len += 2;
		else
			len++;
		if (end < len)
			return str;
		else if (start < len)
			str += this.charsArray[i][0];
	}
	return str;
}
//截取字符串（从start字节截取length个字节）
String.prototype.subCHStr = function(start, length){
	return this.subCHString(start, start + length);
}
</script>
 </head>
  
  <body onload="init()">
    	<object classid="clsid:20423C49-2276-43D5-BC6D-53829C41AEAD"
			id="EtCell" width="800pt" height="600pt" <%=EtCellVersion%>
			style="display:block">
			<param name="_Version" value="196611">
			<param name="_ExtentX" value="10848">
			<param name="_ExtentY" value="7091">
			<param name="_StockProps" value="0">
			<param name="FileName"
				value="<%=request.getContextPath()%>/template/workorder.eT">
			<param name="Script" value>
			<param name="CanSizeRow" value="-1">
			<param name="CanSizeCol" value="-1">
			<param name="Ruler" value="0">
			<param name="Rows" value="30">
			<param name="Cols" value="30">
			<param name="NotScrollRows" value="0">
			<param name="NotScrollCols" value="0">
			<param name="PrintEtCellBackColor" value="0">
			<param name="PrintCellBackColor" value="-1">
			<param name="FixedCellSelect" value="0">
			<param name="MultiSelect" value="0">
			<param name="PrintHeaderText" value>
			<param name="PrintFooterText" value>
			<param name="PrintHeaderLine" value="0">
			<param name="PrintFooterLine" value="0">
			<param name="PrintPageNo" value="0">
			<param name="PrintDirectH" value="0">
			<param name="PrintFullPage" value="0">
			<param name="ConnectionString" value>
			<param name="DatabaseTableName" value>
			<param name="DatabaseActive" value="0">
			<param name="Border3D" value="0">
			<param name="RunStartScript" value="-1">
			<param name="ReadOnly" value="true">
			<param name="DefaultLibFileName" value>
			<param name="FocusCellShape" value="0">
		</object>

        <table align="center">
            <tr>
                <td>
                	<button id="btnPrtBill" onclick="print()" style="width:90px">打印工单</button>
					<button id="btnCancel" onclick="cancelAction()" style="width:90px">取消</button>
                </td>
            </tr>
        </table>
  </body>
</html>
