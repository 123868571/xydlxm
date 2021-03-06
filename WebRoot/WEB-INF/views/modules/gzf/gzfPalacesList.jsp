<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>苑管理</title>
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
	        $.ajax({url : "${ctx}/gzf/gzfPalaces/moredelete",data : {valArr:vals},async : true}).done(function(data){
				if("success"==data){
					/* $("#message").text("删除成功"); */
					alert("删除成功");
					location.reload([true]);
				}else if("false" == data){
					/* $("#message").text("请勾选需要删除项"); */
					alert("请勾选需要删除项");
				}else{
					/* $("#message").text("部分删除失败"); */
					alert("部分删除失败"); 
					location.reload([true]);
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

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzf/gzfPalaces?gzfVillageId=${gzfVillageId}">苑管理</a></li>
		<li><a href="${ctx}/gzf/gzfBuilding?gzfVillageId=${gzfVillageId}">楼栋管理</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="gzfPalaces" action="${ctx}/gzf/gzfPalaces/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="gzfVillageId"/>
		<ul class="ul-form"  style="position:relative;">
			<li>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium" placeholder="请输入名称查询"/>
			</li>
			<li class="btns"><button type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button></li>
			<li>
			<div class="portlet-title pull-right" style="position:absolute;right:5px;">
				<a href="${ctx}/gzf/gzfPalaces/form?gzfVillageId=${gzfVillageId}" id="add" class="add-district btn blue" style="color:#fff;"><i class="icon-plus"></i> 新增</a>
				<!-- <button id="modify" class="btn blue"><i class="icon-wrench"></i> 修改</button> -->
				<!-- <a id="del" class="btn blue" style="color:#fff;"><i class="icon-trash"></i> 删除</a> -->
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
				<th style="text-align:center;">最大楼栋数</th>
				<th style="text-align:center;">最大单元数</th>
				<th style="text-align:center;">最大楼层数</th>
				<th style="text-align:center;">最大房间数</th>
				<shiro:hasPermission name="gzf:gzfPalaces:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfPalaces">
			<tr>

				<%-- <td><input type="checkbox" id="subcheck"  value="${gzfPalaces.id}" onclick="setSelectAll();"/></td> --%>
				<td style="text-align:center;">
					${gzfPalaces.gzfVillage.name}
				</td>
				<td style="text-align:center;"><a href="${ctx}/gzf/gzfPalaces/form?id=${gzfPalaces.id}">
					${gzfPalaces.name}
				</a></td>
				<td style="text-align:center;">
					${gzfPalaces.maxBuildNum}
				</td>
				<td style="text-align:center;">
					${gzfPalaces.maxUnitNum}
				</td>
				<td style="text-align:center;">
					${gzfPalaces.maxFloorNum}
				</td>
				<td style="text-align:center;">
					${gzfPalaces.maxRoomNum}
				</td>
				<shiro:hasPermission name="gzf:gzfPalaces:edit"><td style="text-align:center;">
    				<a href="${ctx}/gzf/gzfPalaces/form?id=${gzfPalaces.id}">修改</a>
					<a href="${ctx}/gzf/gzfPalaces/delete?id=${gzfPalaces.id}" onclick="return confirmx('确认要删除该苑吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>