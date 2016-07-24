<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通知发布管理</title>
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
		<li class="active"><a href="${ctx}/gzf/gzfNotice/">通知发布列表</a></li>
		<shiro:hasPermission name="gzf:gzfNotice:edit"><li><a href="${ctx}/gzf/gzfNotice/form">通知发布添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gzfNotice" action="${ctx}/gzf/gzfNotice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>通知标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">通知标题</th>
				<th style="text-align:center;">小区名称</th>
				<th style="text-align:center;">苑名称</th>
				<th style="text-align:center;">更新时间</th>
				<th style="text-align:center;">备注信息</th>
				<shiro:hasPermission name="gzf:gzfNotice:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfNotice">
			<tr>
				<td style="text-align:center;"><a href="${ctx}/gzf/gzfNotice/form?id=${gzfNotice.id}">
					${gzfNotice.title}
				</a></td>
				<td style="text-align:center;">
					${gzfNotice.gzfVillage.name}
				</td>
				<td style="text-align:center;">
					${gzfNotice.gzfPalaces.name}
				</td>
				<td style="text-align:center;">
					<fmt:formatDate value="${gzfNotice.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="text-align:center;">
					${gzfNotice.remarks}
				</td>
				<shiro:hasPermission name="gzf:gzfNotice:edit"><td style="text-align:center;">
    				<a href="${ctx}/gzf/gzfNotice/form?id=${gzfNotice.id}">修改</a>
					<a href="${ctx}/gzf/gzfNotice/delete?id=${gzfNotice.id}" onclick="return confirmx('确认要删除该通知发布吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>