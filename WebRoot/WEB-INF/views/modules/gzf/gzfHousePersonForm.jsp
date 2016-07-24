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
			

			
			$("#gzfVillage").change(function(){
				var gzfVillageId = $("#gzfVillage").val();
				$.ajax({url : "${ctx}/gzf/gzfHousePerson/findName",data : {gzfVillageId:gzfVillageId},async : true}).done(function(data){
					var obj = eval(data);
					jQuery("#gzfPalacesId").empty();
					jQuery("#houseInfo").empty();
				   for(var key in obj){
				   		$("#gzfPalacesId").append('<option value="' + obj[key].id + '">' + obj[key].name + '</option>');
				   }
					$('#gzfPalacesId').trigger('change');
				});
																			
				}); 
			$("#gzfPalacesId").on('change',function(){
				jQuery("#houseInfo").empty();
				var gzfPalacesId = $("#gzfPalacesId").val();
				$.ajax({url : "${ctx}/gzf/gzfHousePerson/findPalace",data : {gzfPalacesId:gzfPalacesId},async : true}).done(function(data){
					var obj = eval(data);
				   $("#houseInfo").append('<option value="">' + '---请选择房间---' + '</option>');
				   for(var key in obj){
				   		$("#houseInfo").append('<option value="' + obj[key].id + '">' + obj[key].buildNum + '号楼' + obj[key].unit + '单元' + obj[key].room + '房间' + '</option>');
				   }
				   $('#houseInfo').trigger('change');
				});
																			
				});
			 $("#houseInfo").change(function(){
				 var gzfPalacesId = $("#gzfPalacesId").val();
					$.ajax({url : "${ctx}/gzf/gzfHousePerson/removeRoom",data : {gzfPalacesId:gzfPalacesId},async : true}).done(function(data){
						var obj = eval(data);
					    for(var key in obj){
						   $("#houseInfo option[value="+ obj[key].id +"]").remove();
						}
					});
				var houseInfo = $("#houseInfo").val();
				$.ajax({url : "${ctx}/gzf/gzfHousePerson/findHouse",data : {id:houseInfo},async : true}).done(function(data){
					var obj = eval(data);
				   for(var key in obj){
					   $("#houseType").val(obj[key].houseTypeStr);
					   $("#houseBuild").val($("#gzfVillage").val()+$("#gzfPalacesId").val()+$("#houseInfo").val());
					   $("#innerArea").val(obj[key].innerArea);
					   $("#useArea").val(obj[key].useArea);
					   $("#houseProperty").val(obj[key].houseProperty);
					   $("#houseRemark").val(obj[key].remarks);
					   $("#gzfHouseInfoId").val(obj[key].id);
				   }
				}); 
																			
				});
			if($("#gzfVillage").val() != null && $("#gzfVillage").val() != ""){
				var gzfVillageId = $("#gzfVillage").val();
				var data = $.ajax({url : "${ctx}/gzf/gzfHousePerson/findName",data : {gzfVillageId:gzfVillageId},async : false}).responseText;
				var obj = eval(data);
				jQuery("#gzfPalacesId").empty();
				for(var key in obj){
					$("#gzfPalacesId").append('<option value="' + obj[key].id + '">' + obj[key].name + '</option>');
				}
				$('#gzfPalacesId').trigger('change');
			}

			if(($("#gzfVillage").val() != null && $("#gzfVillage").val() != "") && ($("#gzfPalacesId").val() != null && $("#gzfPalacesId").val() != "")){
				var gzfPalacesId = $("#gzfPalacesId").val();
				var data = $.ajax({url : "${ctx}/gzf/gzfHousePerson/findPalace",data : {gzfPalacesId:gzfPalacesId},async : false}).responseText;
				var obj = eval(data);
				jQuery("#houseInfo").empty();
				for(var key in obj){
					$("#houseInfo").append('<option value="' + obj[key].id + '">' + obj[key].buildNum + '号楼' + obj[key].unit + '单元' + obj[key].room + '房间' + '</option>');
				}
				$('#houseInfo').trigger('change');
			}
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

	<!--BEGIN BIND HOUSE-->
	 <div id="bindList" class="row-fluid">
		<div class="span12">
			<div class="portlet">
				<div class="portlet-title bottom-line" style="border-bottom: #f5f5f5 1px solid;">
					<div  class="bindText caption" style="color: rgb(77, 144, 254);"><i class="icon-edit"></i>绑定房源</div>
				</div>
				<form:form id="inputForm"  modelAttribute="gzfHousePerson" action="${ctx}/gzf/gzfHousePerson/save" method="post" class="form-horizontal">
					<form:hidden path="id"/>
					<sys:message content="${message}"/>	
					<input id="gzfHouseInfoId" name="gzfHouseInfoId" type="hidden" value=""/>
					<form:input path="gzfHouseholdInfoId" type="hidden"/>	
					<div class="portlet-body">
						<div class="control-group">
							<label class="control-label">房屋信息:</label>
							<div class="controls">
								<form:select id="gzfVillage" path="" class="input-medium" style="margin-right:20px;">
					
					<%-- <form:options  items="${gzfVillage}" itemLabel="name" itemValue="id" htmlEscape="false"/> --%>
					<%-- <form:option  value="" label="${gzfHousePerson.gzfHouseInfo.gzfPalaces.gzfVillage.name}"/> --%>
					<form:option  value="" label=""/>
					<c:forEach items="${gzfVillage}" var="item">
					     <c:choose>
					   		<c:when test="${gzfHousePerson.gzfHouseInfo.gzfPalaces.gzfVillage.name == item.name}"> 
					   			<form:option  value="${item.id}" label="${item.name}" selected="true" />
					   		</c:when>
					   		<c:otherwise> 
					    		<form:option  value="${item.id}" label="${item.name}" />
					   		</c:otherwise>
						</c:choose>
						
					</c:forEach>
					
				</form:select><span style="margin-right:20px;"></span>
				<form:select id="gzfPalacesId" path="" class="input-medium required">
					<form:option value="" label="---请选择苑---" selected="true"/>
				</form:select><span style="margin-right:20px;"></span>
				<form:select id="houseInfo" path="" class="input-large required">
					<form:option value="" label="---请选择房间---" selected="true"/>
				</form:select><span style="margin-right:20px;"></span>
			<%-- 	<form:select id="" path="" class="input-mini required">
					<form:option value="" label="---请选择房间号---" selected="true"/>
				</form:select><span>房间</span> --%>
								<%-- <form:select id="gzfVillage" path="" class="input-medium" style="margin-right:20px;">
									<form:option value="" label="---请选择小区---"/>
									 <form:options items="${gzfVillage}" itemLabel="name" itemValue="id" htmlEscape="false"/>
								</form:select>
								<form:select id="gzfPalacesId" path="" class="input-medium required" style="margin-right:20px;">
									<form:option value="" label="---请选择苑---" selected="true"/>
									<form:options items="" itemLabel="" itemValue="" htmlEscape="false"/>
								</form:select>
								<form:select id="houseInfo" style="width: 60px;" path="" class="input-medium required" style="margin-right:20px;">
									<form:option value="" label="---请选择楼层---"/>
									<form:options items="" itemLabel="" itemValue="" htmlEscape="false"/>
								</form:select><span>号楼</span>
								<select style="width: 60px;">
									<option>10</option>
									<option>21</option>
									<option>34</option>
								</select>
								<span>单元</span>
								<select style="width: 80px;">
									<option>2536</option>
									<option>2689</option>
									<option>3809</option>
								</select>
								<span>房间</span> --%>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">合同日期:</label>
							<div class="controls">
								<div class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
									<input name="startDate" type="text" readonly="readonly" maxlength="20" class="m-wrap m-ctrl-medium date-picker input-medium Wdate required"
										value="<fmt:formatDate value="${gzfHousePerson.startDate}" pattern="yyyy-MM-dd"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/><span class="add-on"><i class="icon-calendar"></i></span>
								</div>-
								<div class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
									<input name="endDate" type="text" readonly="readonly" maxlength="20" class="m-wrap m-ctrl-medium date-picker input-medium Wdate required"
										value="<fmt:formatDate value="${gzfHousePerson.endDate}" pattern="yyyy-MM-dd"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/><span class="add-on"><i class="icon-calendar"></i></span>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">房租生效日期:</label>
							<div class="controls">
								<div class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
									<input name="effectiveDate" type="text" readonly="readonly" maxlength="20" class="m-wrap m-ctrl-medium date-picker input-medium Wdate required"
										value="<fmt:formatDate value="${gzfHousePerson.effectiveDate}" pattern="yyyy-MM-dd"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/><span class="add-on"><i class="icon-calendar"></i></span>
								</div>
							</div>
						</div>
						<div class="control-group no-padding no-margin">
							<label class="pad control-label">房租类型:</label>
							<div class="controls">
								<div class="pad">
									<form:input id="houseType" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
								</div>
							</div>
						</div>
						<%-- <div class="control-group no-padding no-margin">
							<label class="pad control-label">房租地址:</label>
							<div class="controls">
								<div class="pad">
									<form:input id="houseBuild" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
								</div>
							</div>
						</div> --%>
						<div class="control-group no-padding no-margin">
							<label class="pad control-label">套内面积:</label>
							<div class="controls">
								<div class="pad">
									<form:input id="innerArea" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
								</div>
								<span class="pad">m<sup>2</sup></span>
							</div>
						</div>
						<div class="control-group no-padding no-margin">
							<label class="pad control-label">使用面积:</label>
							<div class="controls">
								<div class="pad">
									<form:input id="useArea" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
								</div>
								<span class="pad">m<sup>2</sup></span>
							</div>
						</div>
						<div class="control-group no-padding no-margin">
							<label class="pad control-label">房屋属性:</label>
							<div class="controls">
								<div class="pad">
									<form:input id="houseProperty" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
								</div>
							</div>
						</div>
						<div class="control-group no-padding no-margin">
							<label class="pad control-label">房屋备注:</label>
							<div class="controls">
								<div class="pad">
									<form:input id="houseRemark" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">备注信息:</label>
							<div class="controls">
								<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
							</div>
						</div>
						<div class="form-actions save-cancle ">
							<shiro:hasPermission name="gzf:gzfHousePerson:edit">
								<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 保存</button></shiro:hasPermission>
								<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>  
	<!--END BIND HOUSE-->
	
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzf/gzfHousePerson/form">小区<shiro:hasPermission name="gzf:gzfHousePerson:edit">${not empty gzfHousePerson.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzf:gzfHousePerson:edit">查看</shiro:lacksPermission></a></li>
	</ul> --%><br/>
	
	<%-- <form:form id="inputForm"  modelAttribute="gzfHousePerson" action="${ctx}/gzf/gzfHousePerson/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<input id="gzfHouseInfoId" name="gzfHouseInfoId" type="hidden" value=""/>
		<form:input path="gzfHouseholdInfoId" type="hidden"/>	
		
		<div class="control-group">
			<label class="control-label">建筑年代：</label>
			<div class="controls">
				<input name="" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate required"
					value="<fmt:formatDate value="${gzfVillage.buildYear}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
			
		<div class="control-group">
			<label class="control-label">房屋信息：</label>
			<div class="controls">
			<form:select id="gzfVillage" path="" class="input-medium">
					<form:option value="" label="---请选择小区---"/>
					 <form:options items="${gzfVillage}" itemLabel="name" itemValue="id" htmlEscape="false"/> 
				</form:select>
				<form:select id="gzfPalacesId" path="" class="input-medium required">
					<form:option value="" label="---请选择苑---" selected="true"/>
					<form:options items="" itemLabel="" itemValue="" htmlEscape="false"/>
				</form:select>
				
				<div>
					<form:select id="houseInfo" path="" class="input-medium required">
					<form:option value="" label="---请选择楼层---"/>
					<form:options items="" itemLabel="" itemValue="" htmlEscape="false"/>
				</form:select>
				</div>
			</div>
			
		</div>
		<div class="control-group">
			<label class="control-label">合同日期：</label>
			<div class="controls">
				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				-<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div> 
		</div>
		<div class="control-group">
			<label class="control-label">房租生效日期：</label>
			<div class="controls">
				-<input name="effectiveDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${effectiveDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房租类型：</label>
			<div class="controls">
				<form:input id="houseType" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房租地址：</label>
			<div class="controls">
				<form:input id="houseBuild" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">套内面积：</label>
			<div class="controls">
				<form:input id="innerArea" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用面积：</label>
			<div class="controls">
				<form:input id="useArea" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房屋属性：</label>
			<div class="controls">
				<form:input id="houseProperty" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房屋备注：</label>
			<div class="controls">
				<form:input id="houseRemark" path="" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="gzf:gzfHousePerson:edit">
		<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 绑定</button></shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
 --%>
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