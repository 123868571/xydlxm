<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>水表管理</title>
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
		<li class="active"><a href="${ctx}/gzf/gzfMailList/">水表列表</a></li>
		<shiro:hasPermission name="gzf:gzfMailList:edit"><li><a href="${ctx}/gzf/gzfMailList/form">水表添加</a></li></shiro:hasPermission>
	</ul> --%>
	<form:form id="searchForm" modelAttribute="gzfMailList" action="${ctx}/gzf/gzfMailList/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目小区：</label>
			<form:select path="gzfVillageId" class="input-medium">
					<form:options items="${gzfVillage}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>负责人：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><button type="submit" class="btn  blue margin-r-20 "><i class="icon-search"></i> 搜索</button></li>
			<li>
			<div class="portlet-title pull-right" style="position:absolute;right:5px;">
				
				<a href="${ctx}/gzf/gzfMailList/form" id="add" class="add-district  btn blue" style="color:#fff;"><i class="icon-plus"></i> 新增</a>
				<!-- <button id="modify" class="btn blue"><i class="icon-wrench"></i> 修改</button> -->
				<!-- <a  id="del" class="btn blue"><i class="icon-trash"></i> 删除</a> -->
				<!-- <button id="exportExcel" class="btn green imp-excel"><i class="icon-share"></i> 导出Excel</button> -->
			</div>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">项目小区</th>
				<th style="text-align:center;">设备名称</th>
				<th style="text-align:center;">设备厂家</th>
				<th style="text-align:center;">负责人</th>
				<th style="text-align:center;">联系电话</th>
				<th style="text-align:center;">备注</th>
				<shiro:hasPermission name="gzf:gzfMailList:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfMailList">
			<tr>
				<td style="text-align:center;"><a href="${ctx}/gzf/gzfMailList/form?id=${gzfMailList.id}">
					${gzfMailList.gzfVillage.name}
				</a></td>
				<td style="text-align:center;">
					${gzfMailList.mailName}
				</td>
				<td style="text-align:center;">
					${gzfMailList.manufactor}
				</td>
				<td style="text-align:center;">
					${gzfMailList.name}
				</td>
				<td style="text-align:center;">
					${gzfMailList.phone}
				</td>
				<td style="text-align:center;">
					${gzfMailList.remarks}
				</td>
				<shiro:hasPermission name="gzf:gzfMailList:edit"><td style="text-align:center;">
    				<a href="${ctx}/gzf/gzfMailList/form?id=${gzfMailList.id}">修改</a>
					<a href="${ctx}/gzf/gzfMailList/delete?id=${gzfMailList.id}" onclick="return confirmx('确认要删除该水表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>