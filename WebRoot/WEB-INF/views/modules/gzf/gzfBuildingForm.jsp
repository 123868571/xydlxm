<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>楼栋管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			$("#gzfPalacesId").change(function(){
					var p1=$(this).children('option:selected').val();//这就是selected的值 
					$.post("${ctx}/gzf/gzfPalaces/getGzfPalaces?id="+p1,
					   function(data){
							var minBuildNum = $("#minBuildNum");
							minBuildNum.empty();
							for(var i=0;i<data.maxBuildNum;i++) {
								var option = $("<option>").text(i+1).val(i+1)
								minBuildNum.append(option);
							}
							var maxBuildNum = $("#maxBuildNum");
							maxBuildNum.empty();
							for(var i=0;i<data.maxBuildNum;i++) {
								var option = $("<option>").text(i+1).val(i+1)
								maxBuildNum.append(option);
							}
							
							var minUnitNum = $("#minUnitNum");
							minUnitNum.empty();
							for(var i=0;i<data.maxUnitNum;i++) {
								var option = $("<option>").text(i+1).val(i+1)
								minUnitNum.append(option);
							}
							var maxUnitNum = $("#maxUnitNum");
							maxUnitNum.empty();
							for(var i=0;i<data.maxUnitNum;i++) {
								var option = $("<option>").text(i+1).val(i+1)
								maxUnitNum.append(option);
							}
							
							var minFloorNum = $("#minFloorNum");
							minFloorNum.empty();
							for(var i=0;i<data.maxFloorNum;i++) {
								var option = $("<option>").text(i+1).val(i+1)
								minFloorNum.append(option);
							}
							var maxFloorNum = $("#maxFloorNum");
							maxFloorNum.empty();
							for(var i=0;i<data.maxFloorNum;i++) {
								var option = $("<option>").text(i+1).val(i+1)
								maxFloorNum.append(option);
							}
							
							var minRoomNum = $("#minRoomNum");
							minRoomNum.empty();
							for(var i=0;i<data.maxRoomNum;i++) {
								var option = $("<option>").text(i+1).val(i+1)
								minRoomNum.append(option);
							}
							var maxRoomNum = $("#maxRoomNum");
							maxRoomNum.empty();
							for(var i=0;i<data.maxRoomNum;i++) {
								var option = $("<option>").text(i+1).val(i+1)
								maxRoomNum.append(option);
							}
					   }
					);
			  });  
		});
	</script>
	<style type="text/css">
	a:link {color: #000;text-decoration:none;}
	a:visited {color: #000}
	a:hover {color: #000;text-decoration:underline;}
	a:active {color: #000}
	select {
	  display:block;
	  float:right;
	}
	.bolang {
	  float:left;
	}
	</style>
</head>
<body>
	<!-- BEGIN PAGE HEADER-->

	<%--<div class="row-fluid" style="margin-top:140px;">--%>

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
	<%--<a id="" href="${ctx}/gzf/gzfBuilding">楼栋管理</a>--%>
	<%--<i class="icon-angle-right"></i>--%>
	<%--</li>--%>
	<%--<li>--%>
	<%--<a id="" href="#">楼栋添加</a>--%>
	<%--</li>--%>
	<%--<li class="pull-right posa-back">--%>
	<%--<a href="${ctx}/gzf/gzfBuilding">--%>
	<%--<button class="btn btn-primary" id=""><i class="icon-arrow-left" style="color:#fff;"></i> 返回</button>--%>
	<%--</a>--%>
	<%--</li>--%>
	<%--</ul>--%>
	<%--<!-- END PAGE TITLE & BREADCRUMB-->--%>

	<%--</div>--%>

	<%--</div>--%>

	<!-- END PAGE HEADER-->

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzf/gzfBuilding/form?id=${gzfBuilding.id}">楼栋<shiro:hasPermission name="gzf:gzfBuilding:edit">${not empty gzfBuilding.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzf:gzfBuilding:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gzfBuilding" action="${ctx}/gzf/gzfBuilding/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<%--2016-01-07修改为按行增加--%>
		<!-- <a href="" id="add" class="add-district btn blue pull-right" style="color:#fff;margin-bottom:10px;"><i class="icon-plus"></i> 新增</a> -->

		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
		<th style="width:150px">小区名称</th>
		<th style="text-align:center;">苑名称</th>
		<th style="text-align:center;">楼栋数</th>
		<th style="text-align:center;">单元数</th>
		<th style="text-align:center;">楼层数</th>
		<th style="text-align:center;">房间数</th>
		<%-- <shiro:hasPermission name="gzf:gzfHouseInfo:edit"><th style="text-align:center;">操作</th></shiro:hasPermission> --%>
		</tr>
		</thead>
		<tbody>

			<tr>
			<td style="width:150px">
		<form:select path="gzfVillageId" class="input-large">
			<form:option value="" label=""/>
			<form:options items="${gzfVillageList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
		</form:select></td>
			<td style="text-align:center;">
		<form:select id="gzfPalacesId" path="gzfPalacesId" class="input-large">
			<form:option value="" label=""/>
			<form:options items="${gzfPalacesList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
		</form:select>
			</td>
			<td style="text-align:center;">
		<form:select id="minBuildNum" path="minBuildNum" style="width:60px;" class="m-wrap span4">
			<form:options items="${minBuildList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
		</form:select><div class="bolang" style="padding:5px 2px;">~</div>
		<form:select id="maxBuildNum" path="maxBuildNum" style="width:60px;" class="m-wrap span4">
			<form:options items="${maxBuildList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
		</form:select>
			</td>
			<td style="text-align:center;">
		<form:select id="minUnitNum" path="minUnitNum" style="width:60px;" class="m-wrap span4">
			<form:options items="${minUnitList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
		</form:select><div class="bolang" style="padding:5px 2px;">~</div>
		<form:select id="maxUnitNum" path="maxUnitNum" style="width:60px;" class="m-wrap span4">
			<form:options items="${maxUnitList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
		</form:select>
			</td>
			<td style="text-align:center;">
		<form:select id="minFloorNum" path="minFloorNum" style="width:60px;" class="m-wrap span4">
			<form:options items="${minFloorList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
		</form:select><div class="bolang" style="padding:5px 2px;">~</div>
		<form:select id="maxFloorNum" path="maxFloorNum" style="width:60px;" class="m-wrap span4">
			<form:options items="${maxFloorList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
		</form:select>
			</td>
			<td style="text-align:center;">
		<form:select id="minRoomNum" path="minRoomNum" style="width:60px;" class="m-wrap span4">
			<form:options items="${minRoomList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
		</form:select><div class="bolang" style="padding:5px 2px;">~</div>
		<form:select id="maxRoomNum" path="maxRoomNum" style="width:60px;" class="m-wrap span4">
			<form:options items="${maxRoomList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
		</form:select>
			</td>

			<!-- <td style="text-align:center;"> -->
		<%--<a href="" id="add" class="add-district btn btn-primary" style="color:#fff;"><i class="icon-plus"></i> 新增</a>--%>
		<%--<a href="" id="add" class="add-district btn btn-primary" style="color:#fff;"><i class="icon-trash"></i> 删除</a>--%>

		<%--<a href="">新增</a>--%>
				<!-- <a href="" onclick="return confirmx('确认要删除该房屋信息吗？', this.href)">删除</a> -->
				<!-- </td> -->
			</tr>

		</tbody>
		</table>
		<%--2016-01-07修改为按行增加 END--%>

		<%--<div class="control-group">--%>
			<%--<label class="control-label">小区名称：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:select path="gzfVillageId" class="input-xlarge ">--%>
					<%--<form:option value="" label=""/>--%>
					<%--<form:options items="${gzfVillageList}" itemLabel="name" itemValue="id" htmlEscape="false"/>--%>
				<%--</form:select>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">苑名称：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:select id="gzfPalacesId" path="gzfPalacesId" class="input-xlarge ">--%>
					<%--<form:option value="" label=""/>--%>
					<%--<form:options items="${gzfPalacesList}" itemLabel="name" itemValue="id" htmlEscape="false"/>--%>
				<%--</form:select>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">楼栋数：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:select id="minBuildNum" path="minBuildNum" style="width:60px;" class="m-wrap span4">--%>
				<%--</form:select>--%>
				<%--<form:select id="maxBuildNum" path="maxBuildNum" style="width:60px;" class="m-wrap span4">--%>
				<%--</form:select>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">单元数：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:select id="minUnitNum" path="minUnitNum" style="width:60px;" class="m-wrap span4">--%>
				<%--</form:select>--%>
				<%--<form:select id="maxUnitNum" path="maxUnitNum" style="width:60px;" class="m-wrap span4">--%>
				<%--</form:select>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">楼层数：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:select id="minFloorNum" path="minFloorNum" style="width:60px;" class="m-wrap span4">--%>
				<%--</form:select>--%>
				<%--<form:select id="maxFloorNum" path="maxFloorNum" style="width:60px;" class="m-wrap span4">--%>
				<%--</form:select>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">房间数：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:select id="minRoomNum" path="minRoomNum" style="width:60px;" class="m-wrap span4">--%>
				<%--</form:select>--%>
				<%--<form:select id="maxRoomNum" path="maxRoomNum" style="width:60px;" class="m-wrap span4">--%>
				<%--</form:select>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="gzf:gzfBuilding:edit">
			<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 保存</button></shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>