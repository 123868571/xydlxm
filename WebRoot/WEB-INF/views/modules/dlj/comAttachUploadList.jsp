<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通用附件上传管理</title>
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
		<li class="active"><a href="${ctx}/dlj/comAttachUpload/">通用附件上传列表</a></li>
		<shiro:hasPermission name="dlj:comAttachUpload:edit"><li><a href="${ctx}/dlj/comAttachUpload/form">通用附件上传添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="comAttachUpload" action="${ctx}/dlj/comAttachUpload/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>业务类型：</label>
				<form:select path="referType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('referType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>文件名：</label>
				<form:input path="fileName" htmlEscape="false" maxlength="200" class="input-medium"/>
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
				<th>上传路径</th>
				<th>文件名</th>
				<th>文件大小</th>
				<th>更新日期</th>
				<shiro:hasPermission name="dlj:comAttachUpload:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comAttachUpload">
			<tr>
				<td><a href="${ctx}/dlj/comAttachUpload/form?id=${comAttachUpload.id}">
					${fns:getDictLabel(comAttachUpload.referType, 'referType', '')}
				</a></td>
				<td>
					${comAttachUpload.uploadPath}
				</td>
				<td>
					${comAttachUpload.fileName}
				</td>
				<td>
					${comAttachUpload.fileSize}
				</td>
				<td>
					<fmt:formatDate value="${comAttachUpload.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="dlj:comAttachUpload:edit"><td>
    				<a href="${ctx}/dlj/comAttachUpload/form?id=${comAttachUpload.id}">修改</a>
					<a href="${ctx}/dlj/comAttachUpload/delete?id=${comAttachUpload.id}" onclick="return confirmx('确认要删除该通用附件上传吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>