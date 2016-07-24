<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>小区管理</title>
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzf/gzfVillage/form?id=${gzfVillage.id}">小区<shiro:hasPermission name="gzf:gzfVillage:edit">${not empty gzfVillage.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzf:gzfVillage:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm"  modelAttribute="gzfVillage" action="${ctx}/gzf/gzfVillage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属区域:</label>
			<div class="controls">
                <sys:treeselect id="area" name="area.id" value="${gzfVillage.area.id}" labelName="area.name" labelValue="${gzfVillage.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下级层级：</label>
			<div class="controls">
				<%--<form:input path="nextLevel" htmlEscape="false" maxlength="64" class="input-xlarge required" placeholder="苑或楼栋,默认苑，(如不是苑请输入名称)"/>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>

			<div style="border:#f5f5f5 1px solid;width:280px;height:80px; padding-top:10px;padding-left:5px;padding-bottom:5px ;">
				<div style="position:relative;width:100%;height:40px;padding:0;">
		           <div class="pull-left" style="margin-top:5px;" ><input id="checkPalaces" name="checkPalaces" type="checkbox" value="1" checked="checked"/> <span id="old_yuan">苑</span></div>
		           <div id="new_input" style="display: none;width:240px;"><input id="palaces" name="palaces" type="text" placeholder="苑"/></div>
		           <div style="position:absolute;top:5px; right:8px;">
		           <a href="javascript:void(0);"><i  class="icon-pencil" id="new_btn1" style=""></i></a>
		</div>
		        </div>

		         <div style="position:relative;width:100%;height:40px;padding:0;">
		            <div class="pull-left" style="margin-top: 5px;margin-left:30px;"><span id="old_lou">楼栋</span></div>
		            <div id="new_input2" style="display: none;margin-left:30px;"><input id="building" name="building" type="text" placeholder="楼栋" style="height:20px!important;"/></div>

		            <div class="pull-right" style="position:absolute; top: 5px; right: 8px">
		            <a href="javascript:void(0);"><i  class="icon-pencil" id="new_btn2" style=""></i></a>
		            </div>

		        </div>

		    </div>


		</div>
		</div>
		<div class="control-group">
			<label class="control-label">建筑年代：</label>
			<div class="controls">
				<input name="buildYear" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate required"
					value="<fmt:formatDate value="${gzfVillage.buildYear}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属单位：</label>
			<div class="controls">
				<form:input path="belongCompany" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物业单位：</label>
			<div class="controls">
				<form:input path="propertyCompany" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">施工单位：</label>
			<div class="controls">
				<form:input path="constructCompany" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">坐落位置：</label>
			<div class="controls">
				<form:input path="location" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">土地等级：</label>
			<div class="controls">
				<form:input path="landGrade" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地号：</label>
			<div class="controls">
				<form:input path="landNo" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产权证号：</label>
			<div class="controls">
				<form:input path="rightNumber" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产权单位：</label>
			<div class="controls">
				<form:input path="rightCompany" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可配总租户数：</label>
			<div class="controls">
				<form:input path="totalTenant" htmlEscape="false" maxlength="11" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions" style="background:#fff;border-top:none;">
			<shiro:hasPermission name="gzf:gzfVillage:edit">
			<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 保存</button>
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

	<script>
	$(document).ready(function(){
	$("#new_btn1").click(function(){
	$("#old_yuan").hide();
	$("#new_input").show();
	});
	});
	</script>
	<script>
	$(document).ready(function(){
	$("#new_btn2").click(function(){
	$("#old_lou").hide();
	$("#new_input2").show();
	});
	});
	</script>
</body>
</html>