<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>GPS管理</title>
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
		<li class="active"><a href="${ctx}/dlj/comGps/">GPS列表</a></li>
		<shiro:hasPermission name="dlj:comGps:edit"><li><a href="${ctx}/dlj/comGps/form">GPS添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="comGps" action="${ctx}/dlj/comGps/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>设备名称：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>设备类型：</label>
				<form:select path="gpstype">
					<form:options items="${fns:getDictList('gpstype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设备名称</th>
				<th>设别KEY</th>
				<th>车牌号码</th>
				<th>启用日期</th>
				<shiro:hasPermission name="dlj:comGps:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comGps">
			<tr>
				<td><a href="${ctx}/dlj/comGps/form?id=${comGps.id}">
					${comGps.name}
				</a></td>
				<td>
					${comGps.gpskey}
				</td>
				<td>
					${comGps.carnum}
				</td>
				<td>
					<fmt:formatDate value="${comGps.opendate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="dlj:comGps:edit"><td>
    				<a href="${ctx}/dlj/comGps/form?id=${comGps.id}">修改</a>
					<a href="${ctx}/dlj/comGps/delete?id=${comGps.id}" onclick="return confirmx('确认要删除该保存GPS吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>