<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流程步骤管理</title>
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
		<li class="active"><a href="${ctx}/process/sysProcessStepConf/">流程步骤列表</a></li>
		<shiro:hasPermission name="process:sysProcessStepConf:edit"><li><a href="${ctx}/process/sysProcessStepConf/form">流程步骤添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="sysProcessStepConf" action="${ctx}/process/sysProcessStepConf/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="row-fluid">
			<div class="span12">
				<div class="dropd margin-r-20 flo-left">
					<label>步骤名称：</label>
					<form:input path="stepName" htmlEscape="false" maxlength="255" class="input-medium" placeholder="请输入步骤名称，如‘领导审核’"/>
				</div>
				<div class="dropd margin-r-20 flo-left">
					<label class="flo-left">流程类型：</label>
					<form:select path="processType" class="input-medium">
						<form:option value="" label="请选择"/>
						<form:options items="${fns:getDictList('process_instance_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				<%--<div class="dropd margin-r-20 flo-left">
					<label class="flo-left">创建日期：</label>
					<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
						value="<fmt:formatDate value="${sysProcessStepConf.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
					<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
						value="<fmt:formatDate value="${sysProcessStepConf.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				</div>
				<div class="dropd margin-r-20 flo-left">
					<label>处理角色：</label>
					<form:select path="rollId" class="input-medium">
						<form:option value="" label=""/>
						<form:options items="${roles}" itemLabel="name" itemValue="id" htmlEscape="false"/>
					</form:select>
				</div>
				--%><div class="portlet-title pull-right">
					<button type="submit" id="btnSubmit" class="btn blue"><i class="icon-search"></i> 查询</button>
				</div>
			<%--<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		--%></div>
		</div>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>流程类型</th>
				<th>步骤</th>
				<th>步骤名称</th>
				<th>处理人的角色</th>
				<th>备注</th>
				<th>创建人</th>
				<th>创建日期</th>
				<th>更新人</th>
				<th>更新日期</th>
				<shiro:hasPermission name="process:sysProcessStepConf:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysProcessStepConf">
			<tr>
				<td><a href="${ctx}/process/sysProcessStepConf/form?id=${sysProcessStepConf.id}">
					${fns:getDictLabel(sysProcessStepConf.processType, 'process_instance_type', '')}
				</a></td>
				<td>
					${sysProcessStepConf.step}
				</td>
				<td>
					${sysProcessStepConf.stepName}
				</td>
				<td>
					${sysProcessStepConf.rollId}
				</td>
				<td>
					${sysProcessStepConf.remark}
				</td>
				<td>
					${sysProcessStepConf.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${sysProcessStepConf.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysProcessStepConf.updateBy.id}
				</td>
				<td>
					<fmt:formatDate value="${sysProcessStepConf.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="process:sysProcessStepConf:edit"><td>
    				<a href="${ctx}/process/sysProcessStepConf/form?id=${sysProcessStepConf.id}">修改</a>
					<a href="${ctx}/process/sysProcessStepConf/delete?id=${sysProcessStepConf.id}" onclick="return confirmx('确认要删除该流程步骤吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>