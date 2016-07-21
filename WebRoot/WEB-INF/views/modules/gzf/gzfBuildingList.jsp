<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>楼栋管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		//删除 
	    $("#del").click(function(){ 
	        var valArr = new Array; 
	        $("input[type='checkbox'][id='subcheck']:checked").each(function(i){ 
	            valArr[i] = $(this).val(); 
	        }); 
	        var vals = valArr.join(','); 
	        $.ajax({url : "${ctx}/gzf/gzfBuilding/moredelete",data : {valArr:vals},async : true}).done(function(data){
				if("success"==data){
					/* $("#message").text("删除成功"); */
					alert("删除成功");
				}else if("false" == data){
					/* $("#message").text("请勾选需要删除项"); */
					alert("请勾选需要删除项");
				}else{
					/* $("#message").text("部分删除失败"); */
					alert("部分删除失败"); 
				}
			});
	    }); 
		//修改
	    $("#modify").click(function(){
	    	var checkedsub = $("input[type='checkbox'][id='subcheck']:checked").length; //获取选中的subcheck的个数
	    	if(checkedsub > 1 || checkedsub == 0){
	    		return alertx('请选择一条信息修改');
	    	}
	        var valArr = new Array; 
	        $("input[type='checkbox'][id='subcheck']:checked").each(function(i){ 
	            valArr[i] = $(this).val(); 
	        }); 
	        var vals = valArr.join(','); 
	        location.href = "gzf/gzfVillage/form?id="+vals;
	    }); 
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

	<%--<div class="row-fluid" style="margin-top:70px;">--%>

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
	<%--<a id="guide_infotitle" href="${ctx}/gzf/gzfVillage">信息中心</a>--%>
	<%--<i class="icon-angle-right"></i>--%>
	<%--</li>--%>
	<%--<li>--%>
	<%--<a id="guide_district_manage" href="${ctx}/gzf/gzfVillage">小区管理</a>--%>
	<%--<i class="icon-angle-right"></i>--%>

	<%--</li>--%>
	<%--<li>--%>
	<%--<a id="" href="${ctx}/gzf/gzfVillage">基础信息管理</a>--%>
	<%--<i class="icon-angle-right"></i>--%>
	<%--</li>--%>
	<%--<li>--%>
	<%--<a id="" href="#">楼栋管理</a>--%>
	<%--</li>--%>
	<%--<li class="pull-right posa-back">--%>
	<%--<a href="${ctx}/gzf/gzfVillage">--%>
	<%--<button class="btn btn-primary" id=""><i class="icon-arrow-left" style="color:#fff;"></i> 返回</button>--%>
	<%--</a>--%>
	<%--</li>--%>
	<%--</ul>--%>
	<%--<!-- END PAGE TITLE & BREADCRUMB-->--%>

	<%--</div>--%>

	<%--</div>--%>

	<!-- END PAGE HEADER-->

	<ul class="nav nav-tabs">
		<li><a href="${ctx}/gzf/gzfPalaces?gzfVillageId=${gzfVillageId}">苑管理</a></li>
		<li class="active"><a href="${ctx}/gzf/gzfBuilding?gzfVillageId=${gzfVillageId}">楼栋管理</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="gzfBuilding" action="${ctx}/gzf/gzfBuilding/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="gzfVillageId"/>
		<ul class="ul-form"  style="position:relative;">
			<li>
				<form:input path="gzfVillage.name" htmlEscape="false" maxlength="100" class="input-medium" placeholder="请输入名称查询"/>
			</li>
			<li class="btns"><button type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button></li>
			<li>
			<div class="portlet-title pull-right" style="position:absolute;right:5px;">
				<a href="${ctx}/gzf/gzfBuilding/form?gzfVillageId=${gzfVillageId}" id="add" class="add-district btn blue" style="color:#fff;"><i class="icon-plus"></i> 新增</a>
				<!-- <button id="modify" class="btn blue"><i class="icon-wrench"></i> 修改</button> -->

				<!-- <a id="del" class="btn btn-primary" style="color:#fff;"><i class="icon-trash"></i> 删除</a> -->

				<!-- <button id="exportExcel" class="btn green imp-excel"><i class="icon-share"></i> 导出Excel</button> -->
			</div>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th style="text-align:center;"><input type="checkbox" id="SelectAll"  value="全选" onclick="selectAll();"/></th> -->
				<th style="text-align:center;">小区名称</th>
				<th style="text-align:center;">苑名称</th>
				<th style="text-align:center;">已选楼栋区间</th>
				<th style="text-align:center;">已选单元区间</th>
				<th style="text-align:center;">已选楼层区间</th>
				<th style="text-align:center;">已选房间区间</th>
				<shiro:hasPermission name="gzf:gzfBuilding:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfBuilding">
			<tr>
				<%-- <td style="text-align:center;"><input type="checkbox" id="subcheck"  value="${gzfVillage.id}" onclick="setSelectAll();"/></td> --%>
				<td style="text-align:center;"><a href="${ctx}/gzf/gzfBuilding/form?id=${gzfBuilding.id}">
					${gzfBuilding.gzfVillage.name}
				</a></td>
				<td style="text-align:center;">
					${gzfBuilding.gzfPalaces.name}
				</td>
				<td style="text-align:center;">
					${gzfBuilding.minBuildNum} ~ ${gzfBuilding.maxBuildNum}
				</td>
				<td style="text-align:center;">
					${gzfBuilding.minUnitNum} ~ ${gzfBuilding.maxUnitNum}
				</td>
				<td style="text-align:center;">
					${gzfBuilding.minFloorNum} ~ ${gzfBuilding.maxFloorNum}
				</td>
				<td style="text-align:center;">
					${gzfBuilding.minRoomNum} ~ ${gzfBuilding.maxRoomNum}
				</td>
				 <shiro:hasPermission name="gzf:gzfBuilding:edit"><td style="text-align:center;">
    				<a href="${ctx}/gzf/gzfBuilding/form?id=${gzfBuilding.id}">修改</a>
					<a href="${ctx}/gzf/gzfBuilding/delete?id=${gzfBuilding.id}" onclick="return confirmx('确认要删除该楼栋吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>