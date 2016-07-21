<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户资料管理管理</title>
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
		<li class="active"><a href="${ctx}/dlj/comClientBasicInfo/">客户资料管理列表</a></li>
		<shiro:hasPermission name="dlj:comClientBasicInfo:edit"><li><a href="${ctx}/dlj/comClientBasicInfo/form">客户资料管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="comClientBasicInfo" action="${ctx}/dlj/comClientBasicInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>地区：</label>
				<sys:treeselect id="area" name="area.id" value="${comClientBasicInfo.area.id}" labelName="area.name" labelValue="${comClientBasicInfo.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="clientName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>档案编号：</label>
				<form:input path="archiveNo" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>身份证：</label>
				<form:input path="cardId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>地区</th>
				<th>客户姓名</th>
				<th>客户档案编号</th>
				<th>身份证</th>
				<th>用电地址</th>
				<th>更新日期</th>
				<shiro:hasPermission name="dlj:comClientBasicInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comClientBasicInfo">
			<tr>
				<td><a href="${ctx}/dlj/comClientBasicInfo/form?id=${comClientBasicInfo.id}">
					${comClientBasicInfo.area.name}
				</a></td>
				<td>
					${comClientBasicInfo.clientName}
				</td>
				<td>
					${comClientBasicInfo.archiveNo}
				</td>
				<td>
					${comClientBasicInfo.cardId}
				</td>
				<td>
					${comClientBasicInfo.address}
				</td>
				<td>
					<fmt:formatDate value="${comClientBasicInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="dlj:comClientBasicInfo:edit">
					<td>
    				<a href="${ctx}/dlj/comClientBasicInfo/form?id=${comClientBasicInfo.id}">修改</a>
    				<a href="${ctx}/dlj/comClientBasicInfo/view?id=${comClientBasicInfo.id}">查看</a>
					<a href="${ctx}/dlj/comClientBasicInfo/delete?id=${comClientBasicInfo.id}" onclick="return confirmx('确认要删除该客户资料管理吗？', this.href)">删除</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>