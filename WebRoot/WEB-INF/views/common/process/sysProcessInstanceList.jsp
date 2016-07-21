<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流程实例管理</title>
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
		<li class="active"><a href="${ctx}/process/sysProcessInstance/">流程实例列表</a></li>
		<shiro:hasPermission name="process:sysProcessInstance:edit"><li><a href="${ctx}/process/sysProcessInstance/form">流程实例添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysProcessInstance" action="${ctx}/process/sysProcessInstance/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>流程类型，预设字段：</label>
				<form:select path="processType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('process_instance_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>流程类型，预设字段</th>
				<th>起始时间</th>
				<th>结束时间</th>
				<th>流程实例状态</th>
				<th>update_date</th>
				<shiro:hasPermission name="process:sysProcessInstance:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysProcessInstance">
			<tr>
				<td><a href="${ctx}/process/sysProcessInstance/form?id=${sysProcessInstance.id}">
					${fns:getDictLabel(sysProcessInstance.processType, 'process_instance_type', '')}
				</a></td>
				<td>
					<fmt:formatDate value="${sysProcessInstance.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sysProcessInstance.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysProcessInstance.status}
				</td>
				<td>
					<fmt:formatDate value="${sysProcessInstance.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="process:sysProcessInstance:edit"><td>
    				<a href="${ctx}/process/sysProcessInstance/form?id=${sysProcessInstance.id}">修改</a>
					<a href="${ctx}/process/sysProcessInstance/delete?id=${sysProcessInstance.id}" onclick="return confirmx('确认要删除该流程实例吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>