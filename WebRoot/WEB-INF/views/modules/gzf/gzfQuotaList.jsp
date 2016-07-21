<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>额度限制管理</title>
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
		<li class="active"><a href="${ctx}/gzf/gzfQuota/">额度限制列表</a></li>
		<shiro:hasPermission name="gzf:gzfQuota:edit"><li><a href="${ctx}/gzf/gzfQuota/form">额度限制添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gzfQuota" action="${ctx}/gzf/gzfQuota/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>房租最大月：</label>
				<form:input path="maxRent" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">小区名称</th>
				<th style="text-align:center;">苑名称</th>
				<th style="text-align:center;">房租（月）</th>
				<th style="text-align:center;">水费（元）</th>
				<th style="text-align:center;">电费（元）</th>
				<th style="text-align:center;">更新时间</th>
				<th style="text-align:center;">备注信息</th>
				<shiro:hasPermission name="gzf:gzfQuota:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfQuota">
			<tr>
				<td style="text-align:center;"><a href="${ctx}/gzf/gzfQuota/form?id=${gzfQuota.id}">
					${gzfQuota.gzfVillage.name}
				</a>
				</td>
				<td style="text-align:center;">
					${gzfQuota.gzfPalaces.name}
				</td>
				<td style="text-align:center;">
					${gzfQuota.maxRent}
				</td>
				<td style="text-align:center;">
					${gzfQuota.maxWater}
				</td>
				<td style="text-align:center;">
					${gzfQuota.maxElec}
				</td>
				<td style="text-align:center;">
					<fmt:formatDate value="${gzfQuota.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="text-align:center;">
					${gzfQuota.remarks}
				</td>
				<shiro:hasPermission name="gzf:gzfQuota:edit"><td style="text-align:center;">
    				<a href="${ctx}/gzf/gzfQuota/form?id=${gzfQuota.id}">修改</a>
					<a href="${ctx}/gzf/gzfQuota/delete?id=${gzfQuota.id}" onclick="return confirmx('确认要删除该额度限制吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>