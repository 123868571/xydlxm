<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>天然气表录入管理</title>
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
			<li><a href="${ctx}/gzf/gzfWaterMeter/">水表录入</a></li>
			<li><a href="${ctx}/gzf/gzfElectric/">电表录入</a></li>
			<li class="active"><a href="${ctx}/gzf/gzfNaturalGas/">天然气录入</a></li>
		</ul>
			
		<form:form id="searchForm" modelAttribute="gzfNaturalGas" action="${ctx}/gzf/gzfNaturalGas/" method="post" class="form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			
			<div class="tab-pane" id="tab_1_2" style="margin-top:15px;">
				<div id="houseWholelist" class="row-fluid">
					<div class="span12">
						<!--BEGIN CONTENT-->
						<div class="row-fluid">
							<div class="span12">
								<div class="pull-left">
										<form:input path="searchName" htmlEscape="false" maxlength="64" class="m-wrap medium"  placeholder="请输入房间号、住户姓名、手机号查询"/>
										<button type="submit" id="btnSubmit" class="btn blue"><i class="icon-search"></i> 查询</button>
									</div>
								<div class="portlet-title pull-right">
<%-- 									<a href="${ctx}/gzf/gzfNaturalGas/form" id="add" class="inputpower btn blue" style="color:#fff;"><i class="icon-plus"></i>录入天然气数</a>
 --%>									<!-- <a href="#" class="btn blue"><i class="icon-wrench"></i> 修改</a>
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
										<th style="text-align:center;">天然气数(m<sup>3</sup>)</th>
										<shiro:hasPermission name="gzf:gzfNaturalGas:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
									</tr>
								</thead>
								<tbody>
										<c:forEach items="${page.list}" var="gzfNaturalGas">
											<tr class="odd gradeX">
												<!-- <td><input type="checkbox" class="checkboxes" value="1" /></td> -->
												<td style="text-align:center;">
													${gzfNaturalGas.gzfHouseInfo.gzfPalaces.gzfVillage.name}
													${gzfNaturalGas.gzfHouseInfo.gzfPalaces.name}
													${gzfNaturalGas.gzfHouseInfo.buildNum}号
													${gzfNaturalGas.gzfHouseInfo.unit}单
													${gzfNaturalGas.gzfHouseInfo.room}
												</td>
												<td style="text-align:center;">
													${gzfNaturalGas.gzfHouseholdInfo.name}
												</td>
												<td style="text-align:center;">
													${gzfNaturalGas.gzfHouseholdInfo.phone}
												</td>
												<td style="text-align:center;">
													${gzfNaturalGas.num}
												</td>
												<shiro:hasPermission name="gzf:gzfNaturalGas:edit"><td style="text-align:center;">
													<a href="${ctx}/gzf/gzfNaturalGas/form?gzfHouseInfoId=${gzfNaturalGas.gzfHouseInfo.id}&gzfHouseholdInfoId=${gzfNaturalGas.gzfHouseholdInfo.id}">录入天然气表数 |</a>
													<a href="${ctx}/gzf/gzfNaturalGas/history?gzfHouseInfoId=${gzfNaturalGas.gzfHouseInfo.id}">历史天然气表数 |</a>
								    				 <a href="${ctx}/gzf/gzfNaturalGas/look?gzfHouseInfoId=${gzfNaturalGas.gzfHouseInfo.id}">查看当前示数</a>
													<%--<a href="${ctx}/gzf/gzfNaturalGas/delete?id=${gzfNaturalGas.id}" onclick="return confirmx('确认要删除该天然气表录入吗？', this.href)">删除</a> --%>
												</td></shiro:hasPermission>
											</tr>
										</c:forEach>
								</tbody>
							</table>
							<div class="pagination">${page}</div>
						</div>
						<!--END CONTENT-->
					</div>
				</div>
			</div>
			
		</form:form>
	</div>
</body>
</html>