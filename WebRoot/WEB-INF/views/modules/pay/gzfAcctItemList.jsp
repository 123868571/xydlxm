<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>查询帐单</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>抄表日期</th>
				<th>表数（吨/度）</th>
				<th>应缴金额（元）</th>
				<th>实缴金额（元）</th>
				<th>待缴金额（元）</th>
				<th>缴费状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="acctItem">
			<tr>
				<td>
					<fmt:formatDate value="${acctItem.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${acctItem.remarks}
				</td>
				<td>
					<fmt:formatNumber value="${acctItem.receAmount/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber>
				</td>
				<td>
					<fmt:formatNumber value="${acctItem.factAmount/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber>
				</td>
				<td>
					<fmt:formatNumber value="${(acctItem.receAmount-acctItem.factAmount)/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber>
				</td>
				<td>
					未结清
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<li class="btns"><input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/></li>
</body>
</html>