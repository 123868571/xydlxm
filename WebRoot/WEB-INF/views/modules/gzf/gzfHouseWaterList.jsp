<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
	<title>房屋水电</title>
	<meta name="decorator" content="default"/>
	<!-- <script type="text/javascript">
		$(document).ready(function() {
			function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
	        	return false;
        	}
	</script> -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#waterBtn").click(function(){
				var url = "${ctx}/gzf/gzfHouseWater/dealWaterMeterAutoReadRecord";
				postJson(url, null,function(state, json){
					if (state == "success") {
						$.jBox.tip(json.message, 'success');
					}
				});
			});
			
			$("#elecBtn").click(function(){
				var url = "${ctx}/gzf/gzfHouseWater/dealElecMeterAutoReadRecord";
				postJson(url, null,function(state, json){
					if (state == "success") {
						$.jBox.tip(json.message, 'success');
					}
				});
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>

	<style type="text/css">
		a:link {color: #000;text-decoration:none;}
		a:visited {color: #000}
		a:hover {color: #000;text-decoration:underline;}
		a:active {color: #000}
	</style>
</head>
<body>
	
	<div id="infomanage-1-add">
	
	<form:form id="searchForm" modelAttribute="gzfHouseInfo" action="${ctx}/gzf/gzfHouseWater/" method="post" class="breadcrumb form-search" style="background:#fff">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="row-fluid">
			<div class="span12">
				<div class="dropd margin-r-20  flo-left">
					<label class="flo-left">房屋状态：</label>
					<form:select path="houseStat" class="input-medium">
						<form:option value="" label="全部"/>
						<form:options items="${fns:getDictList('house_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				<div class="pull-left">
					<form:input type="text" path="houseAll" htmlEscape="false" class="m-wrap medium" placeholder="请输入房间号"/>
					<button type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button>
				</div>
				<div class="pull-right">
					<button type="button" id="waterBtn" class="btn blue">生成水帐单</button>
					<button type="button" id="elecBtn" class="btn blue">生成电帐单</button>
				</div>
			</div>
		</div>
			
			
		<%-- <ul class="ul-form">
			<li>
				<form:input path="houseAll" htmlEscape="false" class="input-medium" placeholder="请输入房间号,住户姓名，手机号查询"/>
			</li>
			<li><label>房屋类型：</label>
				<form:select path="houseType" class="input-medium">
					<form:options items="${fns:getDictList('house_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>房屋状态：</label>
				<form:select path="houseStat" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('house_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" style="margin-left:30px;" type="submit"/><i class="icon-search"></i>搜索</li>
			<li class="btns"><button id="btnSubmit" type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button></li>
			 <li>
           <div class="portlet-title pull-right" style="position:absolute;right:5px;">
				<a href="${ctx}/gzf/gzfHouseInfo/form" id="add" class="add-district btn blue" style="color:#fff;"><i class="icon-plus"></i> 新增</a>
				<!-- <button id="modify" class="btn blue"><i class="icon-wrench"></i> 修改</button> -->
				<a id="del" class="btn blue" style="color:#fff;"><i class="icon-trash"></i> 删除</a>
				<!-- <button id="exportExcel" class="btn green imp-excel"><i class="icon-share"></i> 导出Excel</button> -->
			</div>
			</li>
			<li class="clearfix"></li>
		</ul> --%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">苑名称</th>
				<th style="text-align:center;">楼栋</th>
				<th style="text-align:center;">单元</th>
				<th style="text-align:center;">房间号</th>
				<th style="text-align:center;">房屋状态</th>
				<th style="text-align:center;">水表号</th>
				<th style="text-align:center;">水表IP</th>
				<th style="text-align:center;">水表状态</th>
				<th style="text-align:center;">电表号</th>
				<th style="text-align:center;">电表IP</th>
				<th style="text-align:center;">电表状态</th>
				<shiro:hasPermission name="gzf:gzfHouseWater:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfHouseInfo">
			<tr>
				<td style="text-align:center;"><a href="${ctx}/gzf/gzfHouseWater/form?id=${gzfHouseInfo.id}">
					${gzfHouseInfo.gzfPalaces.name}
				</a></td>
				<td style="text-align:center;">
					${gzfHouseInfo.buildNum}
				</td>
				<td style="text-align:center;">
					${gzfHouseInfo.unit}
				</td>
				<td style="text-align:center;">
					${gzfHouseInfo.room}
				</td>
				<td style="text-align:center;">
					${fns:getDictLabel(gzfHouseInfo.houseStat, 'house_status', '')}
				</td>
				<td style="text-align:center;">
					${gzfHouseInfo.water}
				</td>
				<td style="text-align:center;">
					${gzfHouseInfo.waterIp}
				</td>
				<td style="text-align:center;">
					<c:choose>
						<c:when test="${gzfHouseInfo.waterStatus == 0 || empty gzfHouseInfo.waterStatus}">
							开启
						</c:when>
						<c:otherwise>
							关闭
						</c:otherwise>
					</c:choose>
				</td>
				<td style="text-align:center;">
					${gzfHouseInfo.elec}
				</td>
				<td style="text-align:center;">
					${gzfHouseInfo.elecIp}
				</td>
				<td style="text-align:center;">
					<c:choose>
						<c:when test="${gzfHouseInfo.elecStatus == 0 || empty gzfHouseInfo.elecStatus}">
							开启
						</c:when>
						<c:otherwise>
							关闭
						</c:otherwise>
					</c:choose>
				</td>
				<shiro:hasPermission name="gzf:gzfHouseWater:edit"><td style="text-align:center;">
				<a href="${ctx}/gzf/gzfHouseWater/form?id=${gzfHouseInfo.id}">
					配置|
				</a>
				<a href="${ctx}/gzf/gzfHouseWater/delete?id=${gzfHouseInfo.id}" onclick="return confirmx('确认要删除该额度限制吗？', this.href)">
					删除 |
				</a>
				<a href="${ctx}/gzf/gzfHouseWater/ctrol?id=${gzfHouseInfo.id}">
					开关指令
				</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>