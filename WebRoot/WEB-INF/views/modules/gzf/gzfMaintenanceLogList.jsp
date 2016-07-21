<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>维修日志管理</title>
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
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzf/gzfMaintenanceLog/">维修日志列表</a></li>
		<shiro:hasPermission name="gzf:gzfMaintenanceLog:edit"><li><a href="${ctx}/gzf/gzfMaintenanceLog/form">维修日志添加</a></li></shiro:hasPermission>
	</ul> --%>
	<form:form id="searchForm" modelAttribute="gzfMaintenanceLog" action="${ctx}/gzf/gzfMaintenanceLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>操作时间：</label>
				<input name="time" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${gzfMaintenanceLog.time}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>故障类别：</label>
				<form:select id="type" path="type" class="input-xlarge" style="width:285px;">
					<form:option value="" label="全部"/>
					<form:options items="${typeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn blue" type="submit" value="查询"/></li>
			<li>
			<div class="portlet-title pull-right" style="position:absolute;right:5px;">
				
				<a href="${ctx}/gzf/gzfMaintenanceLog/form?id=${gzfMaintenanceLog.id}" id="add" class="add-district  btn blue" style="color:#fff;"><i class="icon-plus"></i> 新增</a>
				<!-- <button id="modify" class="btn blue"><i class="icon-wrench"></i> 修改</button> -->
<!-- 				<a  id="del" class="btn blue"><i class="icon-trash"></i> 删除</a>
 -->				<!-- <button id="exportExcel" class="btn green imp-excel"><i class="icon-share"></i> 导出Excel</button> -->
			</div>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>操作时间</th>
				<th>故障类别</th>
				<th>故障原因及解决方法</th>
				<th>操作人</th>
				<shiro:hasPermission name="gzf:gzfMaintenanceLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfMaintenanceLog">
			<tr>
				<td><a href="${ctx}/gzf/gzfMaintenanceLog/form?id=${gzfMaintenanceLog.id}">
					<fmt:formatDate value="${gzfMaintenanceLog.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${gzfMaintenanceLog.gzfFaultCategory.name}
				</td>
				<td>
					${gzfMaintenanceLog.content}
				</td>
				<td>
					${gzfMaintenanceLog.name}
				</td>
				<shiro:hasPermission name="gzf:gzfMaintenanceLog:edit"><td>
    				<a href="${ctx}/gzf/gzfMaintenanceLog/form?id=${gzfMaintenanceLog.id}">修改</a>
					<a href="${ctx}/gzf/gzfMaintenanceLog/delete?id=${gzfMaintenanceLog.id}" onclick="return confirmx('确认要删除该维修日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>