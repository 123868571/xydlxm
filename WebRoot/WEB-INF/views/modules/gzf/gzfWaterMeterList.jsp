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
 	<div class="tabbable-custom">
		<ul id="tabNavi" class="nav nav-tabs" style="border-bottom:1px solid #ddd">
			<li class="active"><a href="${ctx}/gzf/gzfWaterMeter/">水表录入</a></li>
			<li><a href="${ctx}/gzf/gzfElectric/">电表录入</a></li>
			<li><a href="${ctx}/gzf/gzfNaturalGas/">天然气录入</a></li>
		</ul>
			
		<form:form id="searchForm" modelAttribute="gzfWaterMeter" action="${ctx}/gzf/gzfWaterMeter/" method="post" class="form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			
			<div class="tab-pane active" id="tab_1_1" style="margin-top:15px;">
				<div id="houseWholelist" class="row-fluid">
					<div class="span12">
						<!--BEGIN CONTENT-->
						<div id="infomanage-1-add">
							<div class="row-fluid">
								<div class="span12">
									<div class="pull-left">
										<form:input path="searchName" htmlEscape="false" maxlength="64" class="m-wrap medium"  placeholder="请输入房间号、住户姓名、手机号查询"/>
										<button type="submit" id="btnSubmit" class="btn blue"><i class="icon-search"></i> 查询</button>
									</div>
									<div class="portlet-title pull-right">
<%-- 										<a href="${ctx}/gzf/gzfWaterMeter/form" id="add" class="inputwater btn blue" style="color:#fff;"><i class="icon-plus"></i>录入水表数</a>
 --%>										<!-- <a href="#" class="btn blue"><i class="icon-wrench"></i> 修改</a>
										<a href="#" class="btn blue"><i class="icon-trash"></i> 删除</a>
										<a href="#" class="btn green imp-excel"><i class="icon-share"></i> 导出Excel</a> -->
									</div>
								</div>
							</div>
							<div id="info_manage_1">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>																	
											<!-- <th style="width:8px;">
												<input type="checkbox" class="group-checkable" data-set="" />
											</th> -->
											<th style="text-align:center;">房屋地址</th>
											<th style="text-align:center;">住户姓名</th>
											<th style="text-align:center;">手机号</th>
											<th style="text-align:center;">水表数(吨)</th>
											<shiro:hasPermission name="gzf:gzfWaterMeter:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${page.list}" var="gzfWaterMeter">
											<tr class="odd gradeX">
												<!-- <td><input type="checkbox" class="checkboxes" value="1" /></td> -->
												<td style="text-align:center;">
													${gzfWaterMeter.gzfHouseInfo.gzfPalaces.gzfVillage.name}
													${gzfWaterMeter.gzfHouseInfo.gzfPalaces.name}
													${gzfWaterMeter.gzfHouseInfo.buildNum}号
													${gzfWaterMeter.gzfHouseInfo.unit}单
													${gzfWaterMeter.gzfHouseInfo.room}
												</td>
												<td style="text-align:center;">
													${gzfWaterMeter.gzfHouseholdInfo.name}
												</td>
												<td style="text-align:center;">
													${gzfWaterMeter.gzfHouseholdInfo.phone}
												</td>
												<td style="text-align:center;">
													${gzfWaterMeter.num}
												</td>
												<shiro:hasPermission name="gzf:gzfWaterMeter:edit"><td style="text-align:center;">
													<a href="${ctx}/gzf/gzfWaterMeter/form?gzfHouseInfoId=${gzfWaterMeter.gzfHouseInfo.id}&gzfHouseholdInfoId=${gzfWaterMeter.gzfHouseholdInfo.id}">录入水表数 |</a>
													<a href="${ctx}/gzf/gzfWaterMeter/history?gzfHouseInfoId=${gzfWaterMeter.gzfHouseInfo.id}">历史水表 |</a>
								    				 <a href="${ctx}/gzf/gzfWaterMeter/look?gzfHouseInfoId=${gzfWaterMeter.gzfHouseInfo.id}">查看当前示数</a>
													<%--<a href="${ctx}/gzf/gzfWaterMeter/delete?id=${gzfWaterMeter.id}" onclick="return confirmx('确认要删除该水表吗？', this.href)">删除</a> --%>
												</td></shiro:hasPermission>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="pagination">${page}</div>
							</div>
						</div>
						<!--END CONTENT-->
					</div>
				</div>
			</div>	
		</form:form>
	</div>

	<%--  <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzf/gzfWaterMeter/">水表录入列表</a></li>
		<li><a href="${ctx}/gzf/gzfElectric/">电表录入列表</a></li>
		<li><a href="${ctx}/gzf/gzfNaturalGas/">天然气表录入列表</a></li>
		<shiro:hasPermission name="gzf:gzfWaterMeter:edit"><li><a href="${ctx}/gzf/gzfWaterMeter/form">水表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gzfWaterMeter" action="${ctx}/gzf/gzfWaterMeter/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>住户：</label>
				<form:input path="gzfHouseInfoId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn blue" type="submit" value="搜索"/></li>
			<li>
			<div class="portlet-title pull-right" style="position:absolute;right:5px;">
				<a href="${ctx}/gzf/gzfWaterMeter/form" id="add" class="add-district btn btn-primary" style="color:#fff;"><i class="icon-plus"></i>录入水表数</a>
				<!-- <button id="modify" class="btn blue"><i class="icon-wrench"></i> 修改</button> -->
				<!-- <!-- <a  id="del" class="btn btn-primary" style="color:#fff;"><i class="icon-trash"></i> 删除</a> -->
				<!-- <button id="exportExcel" class="btn green imp-excel"><i class="icon-share"></i> 导出Excel</button> -->
			</div>
			</li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
    	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>房屋地址</th>
				<th>住户姓名</th>
				<th>手机号</th>
				<th>水表数(吨)</th>
				<shiro:hasPermission name="gzf:gzfWaterMeter:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfWaterMeter">
			<tr>
				<td>
					${gzfWaterMeter.gzfHousePerson.gzfHouseInfo.gzfPalaces.gzfVillage.name}
					${gzfWaterMeter.gzfHousePerson.gzfHouseInfo.gzfPalaces.name}
					${gzfWaterMeter.gzfHousePerson.gzfHouseInfo.buildNum}号
					${gzfWaterMeter.gzfHousePerson.gzfHouseInfo.unit}单
					${gzfWaterMeter.gzfHousePerson.gzfHouseInfo.room}
				</td>
				<td>
					${gzfWaterMeter.gzfHousePerson.gzfHouseholdInfo.name}
				</td>
				<td>
					${gzfWaterMeter.gzfHousePerson.gzfHouseholdInfo.phone}
				</td>
				<td>
					${gzfWaterMeter.num}
				</td>
				<shiro:hasPermission name="gzf:gzfWaterMeter:edit"><td>
					<a href="${ctx}/gzf/gzfWaterMeter/form?gzfHouseInfoId=${gzfWaterMeter.gzfHousePerson.gzfHouseInfo.id}">录入水表数</a>
    				<a href="${ctx}/gzf/gzfWaterMeter/form?id=${gzfWaterMeter.id}">修改</a>
					<a href="${ctx}/gzf/gzfWaterMeter/delete?id=${gzfWaterMeter.id}" onclick="return confirmx('确认要删除该水表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>  --%>
</body>
</html>