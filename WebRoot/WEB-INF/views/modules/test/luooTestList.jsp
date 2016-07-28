<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>test管理</title>
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
		<li class="active"><a href="${ctx}/test/luooTest/">test列表</a></li>
		<shiro:hasPermission name="test:luooTest:edit"><li><a href="${ctx}/test/luooTest/form">test添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="luooTest" action="${ctx}/test/luooTest/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>name：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>name</th>
				<shiro:hasPermission name="test:luooTest:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="luooTest">
			<tr>
				<td><a href="${ctx}/test/luooTest/form?id=${luooTest.id}">
					${luooTest.name}
				</a></td>
				<shiro:hasPermission name="test:luooTest:edit"><td>
    				<a href="${ctx}/test/luooTest/form?id=${luooTest.id}">修改</a>
					<a href="${ctx}/test/luooTest/delete?id=${luooTest.id}" onclick="return confirmx('确认要删除该test吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>