<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>房屋信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
	
	<form:form id="searchForm" modelAttribute="gzfHouseInfo" action="${ctx}/gzf/gzfHouseDistribution/setup2" method="post" class="breadcrumb form-search" style="background:#fff">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="row-fluid">
			<div class="span12">
				<div class="pull-left">
					<form:input type="text" path="room" htmlEscape="false" class="m-wrap medium" placeholder="请输入房间号查询"/>
					<button type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button>
				</div>

				<div class="portlet-title pull-right">
					<a href="${ctx}/gzf/gzfHouseDistribution/assign" id="add" class="add-district btn blue" style="color:#fff;"><i class="icon-plus"></i> 分配房屋</a>
				</div>
			</div>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">低收人群</th>
				<th style="text-align:center;">新大学生</th>
				<th style="text-align:center;">流动人口</th>
			</tr>
			<tr>
				<td style="text-align:center;">${apply1 }</td>
				<td style="text-align:center;">${apply2 }</td>
				<td style="text-align:center;">${apply3 }</td>
			</tr>
		</thead>
	</table>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;"><input type="checkbox" id="SelectAll"  value="全选" onclick="selectAll();"/></th>
				<th style="text-align:center;">房屋坐落位置</th>
				<!-- <th style="text-align:center;">楼栋</th>
				<th style="text-align:center;">单元</th>
				<th style="text-align:center;">房间号</th> -->
				<th style="text-align:center;">套内面积</th>
				<th style="text-align:center;">人员属性</th>
				<%-- <shiro:hasPermission name="gzf:gzfHouseInfo:edit"><th style="text-align:center;">操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfHouseInfo">
			<tr>
				<td style="text-align:center;"><input type="checkbox" id="subcheck"  value="${gzfHouseInfo.id}" onclick="setSelectAll();"/></td> 
				<td style="text-align:center;"><a href="${ctx}/gzf/gzfHouseInfo/form?id=${gzfHouseInfo.id}">
					
					<%-- ${gzfHouseInfo.gzfPalaces.name}**
					${gzfHouseInfo.gzfPalaces.gzfVillageId} 
					--
					${gzfHouseInfo.gzfPalaces.name} 
					${gzfHouseInfo.buildNum}幢
					${gzfHouseInfo.unit} 单元
					${gzfHouseInfo.room} 室 --%>
					${gzfHouseInfo.gzfPalaces.gzfVillage.name}**
						${gzfHouseInfo.gzfPalaces.name}
						${gzfHouseInfo.buildNum}幢
						${gzfHouseInfo.unit}单元
						${gzfHouseInfo.room}室
				</a></td>
				<%-- <td style="text-align:center;">
					${gzfHouseInfo.buildNum}
				</td>
				<td style="text-align:center;">
					${gzfHouseInfo.unit}
				</td>
				<td style="text-align:center;">
					${gzfHouseInfo.room}
				</td> --%>
				<td style="text-align:center;">
					${gzfHouseInfo.innerArea}
				</td>
				<td style="text-align:center;">
					${fns:getDictLabel(gzfHouseInfo.personal, 'apply_form_type', '')}
				</td>
				<%-- <td style="text-align:center;">
				<a href="${ctx}/gzf/gzfHouseDistribution/next2?id=${gzfHouseInfo.id}"><b>下次分房</b></a>
				</td> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>