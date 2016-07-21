<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工单分配管理</title>
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
        
        function checkOrder(wotype){
        	if(wotype=='1'){
        		alert("改工单已完成,不允许打印!");
        		return false;
        	}
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dlj/comWorkorder/">工单分配列表</a></li>
		<shiro:hasPermission name="dlj:comWorkorder:edit"><li><a href="${ctx}/dlj/comWorkorder/form">工单分配添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="comWorkorder" action="${ctx}/dlj/comWorkorder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="code" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工单编号</th>
				<th>工作人员</th>
				<th>绑定GPS</th>
				<th>派单日期</th>
				<th>工单状态</th>
				<shiro:hasPermission name="dlj:comWorkorder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comWorkorder">
			<tr>
				<td><a href="${ctx}/dlj/comWorkorder/form?id=${comWorkorder.id}">
					${comWorkorder.code}
				</a></td>
				<td>
					${comWorkorder.staffids}
				</td>
				<td>
					${comWorkorder.gpsids}
				</td>
				<td>
					<fmt:formatDate value="${comWorkorder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${comWorkorder.wotype}
				</td>

				<shiro:hasPermission name="dlj:comWorkorder:edit"><td>
    				<a href="${ctx}/dlj/comWorkorder/form?id=${comWorkorder.id}">修改</a>
					<a href="${ctx}/dlj/comWorkorder/delete?id=${comWorkorder.id}" onclick="return confirmx('确认要删除该工单分配吗？', this.href)">删除</a>
					<a href="${ctx}/dlj/comWorkorder/print?id=${comWorkorder.id}" onclick="return checkOrder(${comWorkorder.wotype})" target="_blank">打印工单</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>