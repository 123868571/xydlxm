<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流程类型管理</title>
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
		<li class="active"><a href="${ctx}/process/sysProcessType/">流程类型列表</a></li>
		<shiro:hasPermission name="process:sysProcessType:edit"><li><a href="${ctx}/process/sysProcessType/form">流程类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysProcessType" action="${ctx}/process/sysProcessType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型编号：</label>
				<form:input path="processTypeCode" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>类型名称：</label>
				<form:input path="processTypeName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>流程类型编号</th>
				<th>流程类型名称</th>
				<th>查看URL链接</th>
				<th>更新日期</th>
				<shiro:hasPermission name="process:sysProcessType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysProcessType">
			<tr>
				<td><a href="${ctx}/process/sysProcessType/form?id=${sysProcessType.id}">
					${sysProcessType.processTypeCode}
				</a></td>
				<td>
					${sysProcessType.processTypeName}
				</td>
				<td>
					${sysProcessType.viewUrl}
				</td>
				<td>
					<fmt:formatDate value="${sysProcessType.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="process:sysProcessType:edit"><td>
    				<a href="${ctx}/process/sysProcessType/form?id=${sysProcessType.id}">修改</a>
					<a href="${ctx}/process/sysProcessType/delete?id=${sysProcessType.id}" onclick="return confirmx('确认要删除该流程类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>