<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>荣誉墙管理</title>
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
		<li class="active"><a href="${ctx}/sys/honorWall/">荣誉墙列表</a></li>
		<shiro:hasPermission name="sys:honorWall:edit"><li><a href="${ctx}/sys/honorWall/form">荣誉墙添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="honorWall" action="${ctx}/sys/honorWall/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
                <th>简介</th>
				<th>备注信息</th>
				<th>排序</th>
                <th>更新时间</th>
				<shiro:hasPermission name="sys:honorWall:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="honorWall">
			<tr>
				<td><a href="${ctx}/sys/honorWall/form?id=${honorWall.id}">
					${honorWall.name}
				</a></td>
                <td>
                    ${honorWall.introduction}
                </td>
				<td>
					${honorWall.remarks}
				</td>
				<td>
                    ${honorWall.sort}
				</td>
                <td>
                    <fmt:formatDate value="${honorWall.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
				<shiro:hasPermission name="sys:honorWall:edit"><td>
    				<a href="${ctx}/sys/honorWall/form?id=${honorWall.id}">修改</a>
					<a href="${ctx}/sys/honorWall/delete?id=${honorWall.id}" onclick="return confirmx('确认要删除该荣誉墙吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>