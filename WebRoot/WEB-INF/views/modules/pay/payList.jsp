<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>缴费历史</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			//setFirstAndLastMonthDayAndSec("beginTime", "endTime", false);
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	       	return false;
        }
	</script>
	<style type="text/css">
	a:link {color: #000;text-decoration:none;}
	a:visited {color: #000}
	a:hover {color: #000;text-decoration:underline;}
	a:active {color: #000}
	</style>
</head>
<body>
	<form:form id="searchForm" modelAttribute="gzfPayment" action="${ctx}/pay/gzfPayment/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="accountId"/>
		<form:hidden path="spePaymentId"/>
		<ul class="ul-form">
			<li>
				<form:input path="beginTime" id="beginTime" htmlEscape="false" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyy-MM-dd 00:00:00'})" value=""/>
				至
				<form:input path="endTime" id="endTime" htmlEscape="false" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyy-MM-dd 00:00:00'})" value=""/>
			</li>
			<li class="btns"><button type="submit" class="btn blue"><i class="icon-search"></i>搜索</button></li>
			<li class="btns"><input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>开始日期</th>
				<th>结束日期</th>
				<th>金额（元）</th>
				<th>缴费方式</th>
				<shiro:hasPermission name="pay:gzfPayment:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfPayment">
			<tr>
				<td>
					<fmt:formatDate value="${gzfPayment.effectDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${gzfPayment.expireDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatNumber value="${gzfPayment.amount/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber>
				</td>
				<td>
					${fns:getDictLabel(gzfPayment.payMethod, 'paymethod', '')}
				</td>
				<%-- <shiro:hasPermission name="pay:gzfPayment:detail"> --%>
				<td>
    				<a href="${ctx}/pay/gzfPayment/detail?id=${gzfPayment.id}">查看详情</a>
   				</td>
				<%-- </shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
 </body>
</html>
