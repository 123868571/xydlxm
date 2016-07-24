<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>银行卡绑定信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
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
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pay/accountBank/">房屋信息列表</a></li>
		<shiro:hasPermission name="pay:gzfBankCard:edit"><li><a href="${ctx}/pay/accountBank/add">房屋信息添加</a></li></shiro:hasPermission>
	</ul> --%>
	<form:form id="searchForm" modelAttribute="accountBank" action="${ctx}/pay/accountBank/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<form:input path="phoneNo" htmlEscape="false" maxlength="100" class="input-medium" placeholder="请输入手机号查询" style="padding:6px;"/>
			</li>
			<li class="btns"><button id="btnSubmit" type="submit" class="btn blue"><i class="icon-search"></i>搜索</button></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">姓名</th>
				<th style="text-align:center;">电话</th>
				<th style="text-align:center;">银行卡号</th>
				<th style="text-align:center;">房屋地址</th>
				<th style="text-align:center;">绑定种类</th>
				<shiro:hasPermission name="pay:gzfBankCard:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="accountBank">
			<tr>
				<td style="text-align:center;">
					${accountBank.accountName}
				</td>
				<td style="text-align:center;">
					${accountBank.phoneNo}
				</td>
				<td style="text-align:center;">
					${accountBank.cardNo}
				</td>
				<td style="text-align:center;">
					<c:choose>
						<c:when test="${accountBank.housePerson.gzfHouseInfo.gzfPalaces.name !=null}">
						${accountBank.housePerson.gzfHouseInfo.gzfPalaces.name}苑${accountBank.housePerson.gzfHouseInfo.buildNum}楼${accountBank.housePerson.gzfHouseInfo.unit}单元${accountBank.housePerson.gzfHouseInfo.room}号
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</td>
				<td style="text-align:center;">
					${fns:getDictLabels(accountBank.acctItemType, 'remind_type', '')}
				</td>
				<shiro:hasPermission name="pay:gzfBankCard:edit"><td style="text-align:center;">
				<a href="${ctx}/pay/accountBank/form?oper=add&id=${accountBank.id}">新增银行卡</a>
				<c:choose>
					<c:when test="${accountBank.cardNo != null}">
						|<a href="${ctx}/pay/accountBank/form?oper=modify&id=${accountBank.id}&bankId=${accountBank.bankId}&bankName=${accountBank.bankName}&cardNo=${accountBank.cardNo}&acctItemType=${accountBank.acctItemType}">修改绑定</a>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>