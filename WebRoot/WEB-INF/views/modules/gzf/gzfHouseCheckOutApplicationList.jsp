<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>房屋信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		
		});
		
		//复选框事件
		//全选、取消全选的事件
		function selectAll(){
			if ($("#SelectAll").attr("checked")) {
				$(":checkbox").attr("checked", true);
			} else {
				$(":checkbox").attr("checked", false);
			}
		}
		//子复选框的事件
		function setSelectAll(){
			//当没有选中某个子复选框时，SelectAll取消选中
			if (!$("#subcheck").checked) {
				$("#SelectAll").attr("checked", false);
			}
			var chsub = $("input[type='checkbox'][id='subcheck']").length; //获取subcheck的个数
			var checkedsub = $("input[type='checkbox'][id='subcheck']:checked").length; //获取选中的subcheck的个数
			if (checkedsub == chsub) {
				$("#SelectAll").attr("checked", true);
			}
		}
		
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
	<form:form id="searchForm" modelAttribute="gzfHousePerson" action="${ctx}/gzf/gzfHouseCheckOutApplication/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<form:input path="newSelect" htmlEscape="false" class="input-medium" placeholder="请输入房间号,住户姓名，手机号查询"/>
			</li>
			<li class="btns"><button id="btnSubmit" type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button></li>
			 <li>
          
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">房屋地址</th>
				<th style="text-align:center;">套内面积(m²)</th>
				<th style="text-align:center;">使用面积(m²)</th>
				<th style="text-align:center;">住户姓名</th>
				<th style="text-align:center;">电话</th>
				<th style="text-align:center;">身份证</th>
				<th style="text-align:center;">房卡数</th>
				<th style="text-align:center;">更新时间</th>
				<shiro:hasPermission name="gzf:gzfHouseCheckOutApplication:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfHousePerson">
			<tr>
				<td style="text-align:center;">
					${gzfHousePerson.gzfHouseInfo.gzfPalaces.gzfVillage.name}
					${gzfHousePerson.gzfHouseInfo.gzfPalaces.name}
					${gzfHousePerson.gzfHouseInfo.buildNum}号
					${gzfHousePerson.gzfHouseInfo.unit}单
					${gzfHousePerson.gzfHouseInfo.room}
				</td>
				<td style="text-align:center;">
					${gzfHousePerson.gzfHouseInfo.innerArea}
				</td>
				<td style="text-align:center;">
					${gzfHousePerson.gzfHouseInfo.useArea}
				</td>
				<td style="text-align:center;">
					${gzfHousePerson.gzfHouseholdInfo.name}
				</td>
				<td style="text-align:center;">
					${gzfHousePerson.gzfHouseholdInfo.phone}
				</td>
				<td style="text-align:center;">
					${gzfHousePerson.gzfHouseholdInfo.cardid}
				</td>
				<td style="text-align:center;">
					${gzfHousePerson.gzfHouseholdInfo.cardnum}
				</td>
				<td style="text-align:center;">
					<fmt:formatDate value="${gzfHousePerson.updateDate}" pattern="yyyy-MM-dd"/>
				</td>
				
				<shiro:hasPermission name="gzf:gzfHouseCheckOutApplication:edit"><td style="text-align:center;">
				<a href="${ctx}/gzf/gzfHouseCheckOutApplication/form?id=${gzfHousePerson.id}">申请退房</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>