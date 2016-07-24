<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人员信息管理</title>
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
	<form:form id="searchForm" modelAttribute="gzfHouseholdInfo" action="${ctx}/gzf/gzfHouseDistribution/" method="post" class=" form-search" style="background:#fff;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="row-fluid">
			<div class="span12">
				<div class="pull-left">
					<form:input path="allName" htmlEscape="false" maxlength="64" class="input-medium" placeholder="请输入人名、手机号查询"/>
					<button type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button>
				</div>
				<div class="portlet-title pull-right">
					<a href="${ctx}/gzf/gzfHouseDistribution/setup2" id="add" class="add-district btn blue" style="color:#fff;"><i class="icon-plus"></i> 选择房源</a>
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
				<th style="text-align:center;">姓名</th>
				<!-- <th style="text-align:center;">性别</th>
				<th style="text-align:center;">工作单位</th>
				<th style="text-align:center;">学历</th>
				<th style="text-align:center;">联系电话</th> -->
				<th style="text-align:center;">身份证号</th>
				<th style="text-align:center;">人员属性</th>
				<!-- <th style="text-align:center;">操作</th> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfHouseholdInfo">
			<tr>
			<td style="text-align:center;"><input type="checkbox" id="subcheck"  value="${gzfHouseInfo.id}" onclick="setSelectAll();"/></td> 
				<td style="text-align:center;">
					${gzfHouseholdInfo.name}
				</td>
				<%-- <td style="text-align:center;">
					${fns:getDictLabel(gzfHouseholdInfo.sex, 'sex', '')}
				</td>
				<td style="text-align:center;">
					${gzfHouseholdInfo.company}
				</td>
				<td style="text-align:center;">
					${fns:getDictLabel(gzfHouseholdInfo.education, 'education', '')}
				</td>
				<td style="text-align:center;">
					${gzfHouseholdInfo.phone}
				</td> --%>
				<td style="text-align:center;">
					${gzfHouseholdInfo.cardid}
				</td>
				<td style="text-align:center;">
					${fns:getDictLabel(gzfHouseholdInfo.personal, 'apply_form_type', '')}
				</td>
				<%-- <td style="text-align:center;">
				<a href="${ctx}/gzf/gzfHouseDistribution/next1?id=${gzfHouseholdInfo.id}"><b>下次分房</b></a>
				</td> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>