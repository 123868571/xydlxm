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
								
									<div class="input-append">
										<form:input path="gzfHouseholdInfoId" htmlEscape="false" maxlength="64" class="m-wrap" placeholder="请输入住户姓名"/>
										<button type="button" class="btn blue"><i class="icon-search"></i> </button>
									</div>
									<div class="input-append">
										<form:input path="gzfHouseholdInfoId" htmlEscape="false" maxlength="64" class="m-wrap" placeholder="请输入房号"/>
										<button type="button" class="btn blue"><i class="icon-search"></i> </button>
									</div>
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
										<th style="width:8px;">
											<input type="checkbox" class="group-checkable" data-set="" />
										</th>
										<th>房屋地址</th>
										<th>住户姓名</th>
										<th>手机号</th>
										<th>天然气数(m<sup>3</sup>)</th>
										<shiro:hasPermission name="gzf:gzfNaturalGas:edit"><th>操作</th></shiro:hasPermission>
									</tr>
								</thead>
								<tbody>
										<c:forEach items="${page.list}" var="gzfNaturalGas">
											<tr class="odd gradeX">
												<td><input type="checkbox" class="checkboxes" value="1" /></td>
												<td>
													${gzfNaturalGas.gzfHousePerson.gzfHouseInfo.gzfPalaces.gzfVillage.name}
													${gzfNaturalGas.gzfHousePerson.gzfHouseInfo.gzfPalaces.name}
													${gzfNaturalGas.gzfHousePerson.gzfHouseInfo.buildNum}号
													${gzfNaturalGas.gzfHousePerson.gzfHouseInfo.unit}单
													${gzfNaturalGas.gzfHousePerson.gzfHouseInfo.room}
												</td>
												<td>
													${gzfNaturalGas.gzfHousePerson.gzfHouseholdInfo.name}
												</td>
												<td>
													${gzfNaturalGas.gzfHousePerson.gzfHouseholdInfo.phone}
												</td>
												<td>
													${gzfNaturalGas.num}
												</td>
												<shiro:hasPermission name="gzf:gzfNaturalGas:edit"><td>
													<a href="${ctx}/gzf/gzfNaturalGas/form?gzfHouseInfoId=${gzfNaturalGas.gzfHousePerson.gzfHouseInfo.id}">录入天然气表数</a>
								    				<%-- <a href="${ctx}/gzf/gzfNaturalGas/form?id=${gzfNaturalGas.id}">修改</a>
													<a href="${ctx}/gzf/gzfNaturalGas/delete?id=${gzfNaturalGas.id}" onclick="return confirmx('确认要删除该天然气表录入吗？', this.href)">删除</a> --%>
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