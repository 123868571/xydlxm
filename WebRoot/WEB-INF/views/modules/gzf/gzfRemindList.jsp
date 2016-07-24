<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提醒管理</title>
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
	
	<form:form id="searchForm" modelAttribute="gzfRemind" action="${ctx}/gzf/gzfRemind/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label></label>
				<form:input path="allSelect" htmlEscape="false" maxlength="64" class="input-medium" placeholder="请输入姓名、电话查询"/>
			</li>
			<li><label>没交费项目：</label>
			<form:select path="remindtype" class="input-medium">
					<form:options items="${fns:getDictList('remind_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn blue" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">提醒对象</th>
				<th style="text-align:center;">房屋地址</th>
				<th style="text-align:center;">没交费项目</th>
				<th style="text-align:center;">提醒时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfRemind">
			<tr>
				<td style="text-align:center;"><a href="${ctx}/gzf/gzfRemind/form?id=${gzfRemind.id}">
					${gzfRemind.gzfHouseholdInfoId}
				</a></td>
				<td style="text-align:center;">
					${gzfRemind.gzfHousInfo.gzfPalaces.gzfVillage.name}
					${gzfRemind.gzfHousInfo.gzfPalaces.name}
					${gzfRemind.gzfHousInfo.buildNum}
					${gzfRemind.gzfHousInfo.unit}
					${gzfRemind.gzfHousInfo.room}
				</td>
				<td style="text-align:center;">
					${gzfRemind.remindtype}
				</td>
				<td style="text-align:center;">
					<fmt:formatDate value="${gzfRemind.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>