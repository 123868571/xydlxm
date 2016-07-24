<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>ftp服务器配置管理</title>
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
		<li class="active"><a href="${ctx}/ftp/comFtp/">ftp服务器配置列表</a></li>
		<shiro:hasPermission name="ftp:comFtp:edit"><li><a href="${ctx}/ftp/comFtp/form">ftp服务器配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="comFtp" action="${ctx}/ftp/comFtp/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>ftp编码：</label>
				<form:input path="ftpCode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ftp编码</th>
				<th>主机IP地址</th>
				<th>用户</th>
				<th>密码</th>
				<th>更新日期</th>
				<shiro:hasPermission name="ftp:comFtp:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comFtp">
			<tr>
				<td><a href="${ctx}/ftp/comFtp/form?id=${comFtp.id}">
					${comFtp.ftpCode}
				</a></td>
				<td>
					${comFtp.hostIp}
				</td>
				<td>
					${comFtp.user}
				</td>
				<td>
					${comFtp.password}
				</td>
				<td>
					<fmt:formatDate value="${comFtp.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ftp:comFtp:edit"><td>
    				<a href="${ctx}/ftp/comFtp/form?id=${comFtp.id}">修改</a>
					<a href="${ctx}/ftp/comFtp/delete?id=${comFtp.id}" onclick="return confirmx('确认要删除该ftp服务器配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>