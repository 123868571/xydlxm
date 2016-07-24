<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<%@ page import="com.paopao.hzgzf.modules.pay.common.PaymentConst" %>
<html>
<head>
	<title>房租管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		$(document).ready(function() {
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			
			$("searchForm").submit();
		});
	</script>

	<style type="text/css">
	a:link {color: #000;text-decoration:none;}
	a:visited {color: #000}
	a:hover {color: #000;text-decoration:underline;}
	a:active {color: #000}
	</style>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/pay/gzfPayment/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/pay/gzfPayment/import/template">下载模板</a>
		</form>
	</div>
	<form:form id="searchForm" modelAttribute="gzfAccount" action="${ctx}/pay/gzfAccount/payHouseRentBatch" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<form:input path="phoneNo" htmlEscape="false" maxlength="100" class="input-medium" placeholder="请输入手机号查询" style="padding:6px;"/>
				<button type="submit" class="btn blue" style="margin-left:-5px;"><i class="icon-search"></i> 搜索</button>
			</li>
			<li class="btns" style="float:right"><button id="btnImport" type="button" class="btn green"><i class="icon-reply"></i>导入Excel</button></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">房屋地址</th>
				<th style="text-align:center;">住房姓名</th>
				<th style="text-align:center;">住房电话</th>
				<th style="text-align:center;">帐户余额（元）</th>
				<th style="text-align:center;">房租到期日期</th>
				<shiro:hasPermission name="pay:gzfBankCard:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfAccount">
			<tr>
				<td style="text-align:center;">
					<c:choose>
						<c:when test="${gzfAccount.housePerson.gzfHouseInfo.gzfPalaces.name !=null}">
							${gzfAccount.housePerson.gzfHouseInfo.gzfPalaces.name}${gzfAccount.housePerson.gzfHouseInfo.buildNum}楼${gzfAccount.housePerson.gzfHouseInfo.unit}单元${gzfAccount.housePerson.gzfHouseInfo.room}号
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</td>
				<td style="text-align:center;">
					${gzfAccount.accountName}
				</td >
				<td style="text-align:center;">
					${gzfAccount.phoneNo}
				</td>
				<td style="text-align:center;">
					<fmt:formatNumber value="${gzfAccount.fee/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber>
				</td>
				<td style="text-align:center;">
					<fmt:formatDate value="${gzfAccount.updateDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="pay:gzfPayment:view"><td style="text-align:center;">
				<c:choose>
					<c:when test="${gzfAccount.id != null}">
						<a href="${ctx}/pay/gzfPayment/list?accountId=${gzfAccount.id}&spePaymentId=<%=PaymentConst.SPECIAL_PAYMENT.HOUSE_RENT%>">缴费历史</a>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
