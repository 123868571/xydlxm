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

	<!-- BEGIN PAGE HEADER-->

	<%--<div class="row-fluid">--%>
	<%--<div class="span12">--%>
	<%--<!-- BEGIN PAGE TITLE & BREADCRUMB-->--%>
	<%--<h6></h6>--%>
	<%--<ul class="breadcrumb pos-rel">--%>
	<%--<li>--%>
	<%--<i class="icon-list" style="font-size:14px;"></i>--%>
	<%--<a id="home" href="#"></a>--%>
	<%--<i class="icon-angle-right"></i>--%>
	<%--</li>--%>
	<%--<li>--%>
	<%--<a id="guide_infotitle" href="#">信息中心</a>--%>
	<%--<i class="icon-angle-right"></i>--%>
	<%--</li>--%>
	<%--<li>--%>
	<%--<a id="guide_district_manage" href="">房屋信息</a>--%>
	<%--<i class="icon-angle-right"></i>--%>
	<%--</li>--%>

	<%--<li>--%>
	<%--<a id="" href="#">房屋信息列表</a>--%>
	<%--</li>--%>
	<%--<li class="pull-right posa-back">--%>
	<%--<button class="btn btn-primary" id=""><i class="icon-arrow-left" style="color:#fff;"></i> 返回</button>--%>
	<%--</li>--%>
	<%--</ul>--%>
	<%--<!-- END PAGE TITLE & BREADCRUMB-->--%>

	<%--</div>--%>

	<%--</div>--%>

	
	<form:form id="searchForm" modelAttribute="gzfHousePerson" action="${ctx}/gzf/gzfHouseContinue/" method="post" class="form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="input-append">
				<form:input path="newSelect" htmlEscape="false" class="m-wrap" placeholder="请输入房间号,住户姓名，手机号查询"/>
				<button id="btnSubmit" class="btn blue margin-r-20"><i class="icon-search"></i> 搜索 </button>
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
				<th style="text-align:center;">更新时间</th>
				<shiro:hasPermission name="gzf:gzfHouseContinue:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
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
				<td style="text-align:center;" >
					<fmt:formatDate value="${gzfHousePerson.updateDate}" pattern="yyyy-MM-dd"/>
				</td>
				
				<shiro:hasPermission name="gzf:gzfHouseContinue:edit"><td style="text-align:center;">
				<a href="${ctx}/gzf/gzfHouseContinue/form?id=${gzfHousePerson.id}">续租</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>