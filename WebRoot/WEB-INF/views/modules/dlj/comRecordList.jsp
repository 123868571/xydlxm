<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通用变更记录管理</title>
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
		<li class="active"><a href="${ctx}/dlj/comRecord/">通用变更记录列表</a></li>
		<shiro:hasPermission name="dlj:comRecord:edit"><li><a href="${ctx}/dlj/comRecord/form">通用变更记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="comRecord" action="${ctx}/dlj/comRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>业务类型：</label>
				<form:select path="referType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('referType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>修改的字段名：</label>
				<form:input path="changeColumn" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>业务类型</th>
				<th>修改的字段名</th>
				<th>修改前内容</th>
				<th>修改后内容</th>
				<th>是否是附件</th>
				<th>修改类型</th>
				<th>更新日期</th>
				<shiro:hasPermission name="dlj:comRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comRecord">
			<tr>
				<td><a href="${ctx}/dlj/comRecord/form?id=${comRecord.id}">
					${fns:getDictLabel(comRecord.referType, 'referType', '')}
				</a></td>
				<td>
					${comRecord.changeColumn}
				</td>
				<td>
					${comRecord.befValue}
				</td>
				<td>
					${comRecord.aftValue}
				</td>
				<td>
					${comRecord.isAttach}
				</td>
				<td>
					${comRecord.modifyType}
				</td>
				<td>
					<fmt:formatDate value="${comRecord.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="dlj:comRecord:edit"><td>
    				<a href="${ctx}/dlj/comRecord/form?id=${comRecord.id}">修改</a>
					<a href="${ctx}/dlj/comRecord/delete?id=${comRecord.id}" onclick="return confirmx('确认要删除该通用变更记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>