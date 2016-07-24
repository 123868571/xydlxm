<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>苑管理</title>
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
		});
	</script>
	<style type="text/css">
	a:link {color: #000;text-decoration:none;}
	a:visited {color: #000}
	a:hover {color: #000;text-decoration:underline;}
	a:active {color: #000}
	</style>
</head>
<body>
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
	<%--<a id="" href="${ctx}/gzf/gzfPalaces?gzfVillageId=${gzfVillage.id}">苑管理</a>--%>
	<%--<i class="icon-angle-right"></i>--%>
	<%--</li>--%>
	<%--<li>--%>
	<%--<a id="" href="#">苑添加</a>--%>
	<%--</li>--%>
	<%--<li class="pull-right posa-back">--%>
	<%--<a href="${ctx}/gzf/gzfPalaces?gzfVillageId=${gzfVillage.id}">--%>
	<%--<button class="btn btn-primary" id=""><i class="icon-arrow-left" style="color:#fff;"></i> 返回</button>--%>
	<%--</a>--%>
	<%--</li>--%>
	<%--</ul>--%>
	<%--<!-- END PAGE TITLE & BREADCRUMB-->--%>

	<%--</div>--%>

	<%--</div>--%>


	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzf/gzfPalaces/form?id=${gzfPalaces.id}">苑<shiro:hasPermission name="gzf:gzfPalaces:edit">${not empty gzfPalaces.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzf:gzfPalaces:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gzfPalaces" action="${ctx}/gzf/gzfPalaces/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="gzfVillageId"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">小区名称:</label>
			<div class="controls">
				<label class="lbl">${gzfPalaces.gzfVillage.name}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大楼栋数：</label>
			<div class="controls">
				<form:input path="maxBuildNum" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大单元数：</label>
			<div class="controls">
				<form:input path="maxUnitNum" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大楼层数：</label>
			<div class="controls">
				<form:input path="maxFloorNum" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大房间数：</label>
			<div class="controls">
				<form:input path="maxRoomNum" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="gzf:gzfPalaces:edit"><button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 保存</button></shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>