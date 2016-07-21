<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报修管理管理</title>
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
	<form:form id="searchForm" modelAttribute="gzfRepairManagement" action="${ctx}/gzf/gzfRepairManagement/" method="post" class="form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>维修状态：</label>
				<form:select path="maintenanceId" class="input-xlarge required" style="width:80px;">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('repair')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>报修项目：</label>
				<form:select path="repairType" class="input-xlarge" style="width:80px;">
					<form:option value="" label="全部"/>
					<form:options items="${repairTypeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>报修时间：</label>
				<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
				<input name="startDate" type="text"" maxlength="20" class="m-wrap m-ctrl-medium date-picker Wdate"
					value="<fmt:formatDate value="${gzfRepairManagement.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width: 100px;"/>
					<span class="add-on"><i class="icon-calendar"></i></span>
					</div>&nbsp;-&nbsp;
					<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
					<input name="endDate" type="text" maxlength="20" class="m-wrap m-ctrl-medium date-picker Wdate"
					value="<fmt:formatDate value="${gzfRepairManagement.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width: 100px;"/>
					<span class="add-on"><i class="icon-calendar"></i></span>
					</div>
			<li style="float:right"><label></label>
				<form:input path="allSelect" htmlEscape="false" maxlength="64" class="input-medium" placeholder="请输入房间号、手机号查询" style="padding:5px 4px"/>
				<button id="btnSubmit" type="submit" class="btn blue" style="margin-left:-4px;"><i class="icon-search"></i>搜索</button>
				
				<a href="${ctx}/gzf/gzfRepairManagement/form?id=${gzfRepairManagement.id}" id="add" class="add-district  btn blue" style="color:#fff;"><i class="icon-plus"></i> 新增</a>
			</li>
			<li class="clearfix"></li>
		</ul>
		
			<%-- <div class="portlet-title pull-right"  style="margin:10px 0;">
				
				<a href="${ctx}/gzf/gzfRepairManagement/form?id=${gzfRepairManagement.id}" id="add" class="add-district  btn blue" style="color:#fff;"><i class="icon-plus"></i> 新增</a>
				<!-- <button id="modify" class="btn blue"><i class="icon-wrench"></i> 修改</button> -->
<!-- 				<a  id="del" class="btn blue"><i class="icon-trash"></i> 删除</a>
 -->				<!-- <button id="exportExcel" class="btn green imp-excel"><i class="icon-share"></i> 导出Excel</button> -->
			</div>
			<div class="clearfix"></div> --%>
			
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">报修单号</th>
				<th style="text-align:center;">报修项目</th>
				<th style="text-align:center;">报修时间</th>
				<th style="text-align:center;">房屋地址</th>
				<th style="text-align:center;">报修人</th>
				<th style="text-align:center;">报修人电话</th>
				<th style="text-align:center;">故障现象</th>
				<th style="text-align:center;">维修状态</th>
				<shiro:hasPermission name="gzf:gzfRepairManagement:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfRepairManagement">
			<tr>
				<td style="text-align:center;"><a href="${ctx}/gzf/gzfRepairManagement/form?id=${gzfRepairManagement.id}">
					${gzfRepairManagement.repairNum}
				</a></td>
				<td style="text-align:center;">
					${gzfRepairManagement.gzfRepairProject.name}
				</td>
				<td style="text-align:center;">
					<fmt:formatDate value="${gzfRepairManagement.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="text-align:center;">
					${gzfRepairManagement.gzfHouseInfo.gzfPalaces.gzfVillage.name}
					${gzfRepairManagement.gzfHouseInfo.gzfPalaces.name}
					${gzfRepairManagement.gzfHouseInfo.buildNum}幢
					${gzfRepairManagement.gzfHouseInfo.unit}单元
					${gzfRepairManagement.gzfHouseInfo.room}室
				</td>
				<td style="text-align:center;">
					${gzfRepairManagement.name}
				</td>
				<td style="text-align:center;">
					${gzfRepairManagement.phone}
				</td>
				<td style="text-align:center;">
					${gzfRepairManagement.content}
				</td>
				<td style="text-align:center;">
					${fns:getDictLabel(gzfRepairManagement.maintenanceId, 'repair', '报修')}
				</td>
				<shiro:hasPermission name="gzf:gzfRepairManagement:edit"><td style="text-align:center;">
    				<a href="${ctx}/gzf/gzfRepairManagement/info?id=${gzfRepairManagement.id}">查看详情</a>
					<%-- <a href="${ctx}/gzf/gzfRepairManagement/delete?id=${gzfRepairManagement.id}" onclick="return confirmx('确认要删除该报修管理吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>