<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>房屋属性管理</title>
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
		<li class="active"><a href="${ctx}/gzf/gzfHouseProperty/">房屋属性列表</a></li>
		<shiro:hasPermission name="gzf:gzfHouseProperty:edit"><li><a href="${ctx}/gzf/gzfHouseProperty/form">房屋属性添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gzfHouseProperty" action="${ctx}/gzf/gzfHouseProperty/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
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
				<th>名称</th>
				<shiro:hasPermission name="gzf:gzfHouseProperty:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfHouseProperty">
			<tr>
				<td><a href="${ctx}/gzf/gzfHouseProperty/form?id=${gzfHouseProperty.id}">
					${gzfHouseProperty.name}
				</a></td>
				<shiro:hasPermission name="gzf:gzfHouseProperty:edit"><td>
    				<a href="${ctx}/gzf/gzfHouseProperty/form?id=${gzfHouseProperty.id}">修改</a>
					<a href="${ctx}/gzf/gzfHouseProperty/delete?id=${gzfHouseProperty.id}" onclick="return confirmx('确认要删除该房屋属性吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>