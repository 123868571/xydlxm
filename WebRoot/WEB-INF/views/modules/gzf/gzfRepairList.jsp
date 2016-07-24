<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>维修历史管理</title>
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
		<li class="active"><a href="${ctx}/gzf/gzfRepair/">维修历史列表</a></li>
		<shiro:hasPermission name="gzf:gzfRepair:edit"><li><a href="${ctx}/gzf/gzfRepair/form">维修历史添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gzfRepair" action="${ctx}/gzf/gzfRepair/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>维修状态：</label>
				<form:input path="repairType" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn blue" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>维修状态</th>
				<th>维修人</th>
				<th>维修人电话</th>
				<th>维修项目</th>
				<th>维修数量</th>
				<th>维修内容</th>
				<th>备注</th>
				<th>维修时间</th>
				<th>创建者</th>
				<th>更新时间</th>
				<shiro:hasPermission name="gzf:gzfRepair:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfRepair">
			<tr>
				<td><a href="${ctx}/gzf/gzfRepair/form?id=${gzfRepair.id}">
					${gzfRepair.repairType}
				</a></td>
				<td>
					${gzfRepair.repairBy}
				</td>
				<td>
					${gzfRepair.phone}
				</td>
				<td>
					${gzfRepair.repairProject}
				</td>
				<td>
					${gzfRepair.repairNum}
				</td>
				<td>
					${gzfRepair.content}
				</td>
				<td>
					${gzfRepair.remarks}
				</td>
				<td>
					<fmt:formatDate value="${gzfRepair.repairDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${gzfRepair.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${gzfRepair.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="gzf:gzfRepair:edit"><td>
    				<a href="${ctx}/gzf/gzfRepair/form?id=${gzfRepair.id}">修改</a>
					<a href="${ctx}/gzf/gzfRepair/delete?id=${gzfRepair.id}" onclick="return confirmx('确认要删除该维修历史吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>