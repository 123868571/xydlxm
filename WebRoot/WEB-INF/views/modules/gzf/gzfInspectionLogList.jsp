<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡查日志管理</title>
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

		<div class="row-fluid ">
			<div class="span12" id="deviceAddlist" >
				<div class="portlet-body">
					<form:form id="searchForm" modelAttribute="gzfInspectionLog" action="${ctx}/gzf/gzfInspectionLog/" method="post" class="form-search">
						<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
						<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
							<div class="dropd pull-left margin-r-10">
								<label class="pull-left">巡查日期：</label>
								<div class="controls pull-left">
									<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
										<input name="time" type="text" maxlength="20" class="m-wrap m-ctrl-medium date-picker Wdate"
										value="<fmt:formatDate value="${gzfInspectionLog.time}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width: 100px;"/>
										<span class="add-on"><i class="icon-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="pull-left">
								<form:input path="inspectionName" htmlEscape="false" placeholder="请输入巡查人名称查询" maxlength="64" class="input-medium"/>
								<button type="button" class="btn blue" style="margin-left:-4px;"><i class="icon-search"></i> 搜索</button>
							</div>
							<div class="portlet-title pull-right">
								<a href="${ctx}/gzf/gzfInspectionLog/form" id="add" class="add-district  btn blue" style="color:#fff;"><i class="icon-plus"></i> 新增</a>
							</div>
							</form:form>
							<sys:message content="${message}"/>
							<table id="contentTable" class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th>巡查日期</th>
										<th>小区名</th>
										<th>巡查单位</th>
										<th>巡查人</th>
										<th>项目维护负责人</th>
										<shiro:hasPermission name="gzf:gzfInspectionLog:edit"><th>操作</th></shiro:hasPermission>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${page.list}" var="gzfInspectionLog">
									<tr>
										<td><a href="${ctx}/gzf/gzfInspectionLog/form?id=${gzfInspectionLog.id}">
											<fmt:formatDate value="${gzfInspectionLog.time}" pattern="yyyy-MM-dd"/>
										</a></td>
										<td>
											${gzfInspectionLog.gzfVillage.name}
										</td>
										<td>
											${gzfInspectionLog.unit}
										</td>
										<td>
											${gzfInspectionLog.inspectionName}
										</td>
										<td>
											${gzfInspectionLog.name}
										</td>
										<shiro:hasPermission name="gzf:gzfInspectionLog:edit"><td>
						    				<a href="${ctx}/gzf/gzfInspectionLog/form?id=${gzfInspectionLog.id}">修改</a>
											<a href="${ctx}/gzf/gzfInspectionLog/delete?id=${gzfInspectionLog.id}" onclick="return confirmx('确认要删除该巡查日志吗？', this.href)">删除</a>
										</td></shiro:hasPermission>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<div class="pagination">${page}</div>
						</div>
					</div>
				</div>
		
		
	
</body>
</html>