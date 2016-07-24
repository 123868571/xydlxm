<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡查设备详情管理</title>
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
		<li class="active"><a href="${ctx}/gzf/gzfInspectionDetail/">巡查设备详情列表</a></li>
		<li><a href="${ctx}/gzf/gzfInspectionDetail/form">巡查设备详情添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="gzfInspectionDetail" action="${ctx}/gzf/gzfInspectionDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>巡查类目：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>巡查类别</th>
				<th>巡查类目</th>
				<shiro:hasPermission name="gzf:gzfInspectionDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfInspectionDetail">
			<tr>
				<td><a href="${ctx}/gzf/gzfInspectionDetail/form?id=${gzfInspectionDetail.id}">
					${gzfInspectionDetail.gzfInspectionCategory.name}
				</a></td>
				<td>
					${gzfInspectionDetail.name}
				</td>
				<td>
    				<a href="${ctx}/gzf/gzfInspectionDetail/form?id=${gzfInspectionDetail.id}">修改</a>
					<a href="${ctx}/gzf/gzfInspectionDetail/delete?id=${gzfInspectionDetail.id}" onclick="return confirmx('确认要删除该巡查设备详情吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>