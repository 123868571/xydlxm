<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>缴费标准管理</title>
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzf/gzfPaymentStandard/">缴费标准列表</a></li>
		<shiro:hasPermission name="gzf:gzfPaymentStandard:edit"><li><a href="${ctx}/gzf/gzfPaymentStandard/form">缴费标准添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gzfPaymentStandard" action="${ctx}/gzf/gzfPaymentStandard/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标准名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn blue" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标准名称</th>
				<th>房屋单价(元/m<sup>2</sup>)</th>
				<th>物业费(元/m<sup>2</sup>)</th>
				<th>能耗费(元/m<sup>2</sup>)</th>
				<th>水费(元/吨)</th>
				<th>电费(元/度)</th>
				<th>天然气费(元/m<sup>3</sup>)</th>
				<th>押金</th>
				<shiro:hasPermission name="gzf:gzfPaymentStandard:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfPaymentStandard">
			<tr>
				<td><a href="${ctx}/gzf/gzfPaymentStandard/form?id=${gzfPaymentStandard.id}">
					${gzfPaymentStandard.name}
				</a></td>
				<td>
					${gzfPaymentStandard.rentUnitPrice}
				</td>
				<td>
					${gzfPaymentStandard.managementFee}
				</td>
				<td>
					${gzfPaymentStandard.consumption}
				</td>
				<td>
					${gzfPaymentStandard.waterFee}
				</td>
				<td>
					${gzfPaymentStandard.electricityFee}
				</td>
				<td>
					${gzfPaymentStandard.naturalgasFee}
				</td>
				<td>
					${gzfPaymentStandard.deposit}
				</td>
				<shiro:hasPermission name="gzf:gzfPaymentStandard:edit"><td>
    				<a href="${ctx}/gzf/gzfPaymentStandard/form?id=${gzfPaymentStandard.id}">修改</a>
					<a href="${ctx}/gzf/gzfPaymentStandard/delete?id=${gzfPaymentStandard.id}" onclick="return confirmx('确认要删除该缴费标准吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>