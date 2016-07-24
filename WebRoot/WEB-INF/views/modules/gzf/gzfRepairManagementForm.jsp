<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>报修管理管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//$("#name").focus();
						$("#inputForm").validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});

						if ($("#gzfVillage").val() != null
								&& $("#gzfVillage").val() != "") {
							var gzfVillageId = $("#gzfVillage").val();
							$.ajax({
								url : "${ctx}/gzf/gzfHousePerson/findName",
								data : {
									gzfVillageId : gzfVillageId
								},
								async : true
							}).done(
									function(data) {
										var obj = eval(data);
										jQuery("#gzfPalacesId").empty();
										for ( var key in obj) {
											$("#gzfPalacesId").append(
													'<option value="' + obj[key].id + '">'
															+ obj[key].name
															+ '</option>');
										}
									});
						}

						if (($("#gzfVillage").val() != null && $("#gzfVillage")
								.val() != "")
								&& ($("#gzfPalacesId").val() != null && $(
										"#gzfPalacesId").val() != "")) {
							var gzfPalacesId = $("#gzfPalacesId").val();
							$.ajax({
								url : "${ctx}/gzf/gzfHousePerson/findPalace",
								data : {
									gzfPalacesId : gzfPalacesId
								},
								async : true
							}).done(
									function(data) {
										var obj = eval(data);
										jQuery("#houseInfo").empty();
										$("#houseInfo").append('<option value="">无</option>');
										for ( var key in obj) {
											$("#houseInfo").append(
													'<option value="' + obj[key].id + '">'
															+ obj[key].buildNum
															+ '号楼'
															+ obj[key].unit
															+ '单元'
															+ obj[key].room
															+ '房间'
															+ '</option>');
										}
									});
						}

						$("#gzfVillage")
								.change(
										function() {
											var gzfVillageId = $("#gzfVillage")
													.val();
											$
													.ajax(
															{
																url : "${ctx}/gzf/gzfHousePerson/findName",
																data : {
																	gzfVillageId : gzfVillageId
																},
																async : true
															})
													.done(
															function(data) {
																var obj = eval(data);
																jQuery(
																		"#gzfPalacesId")
																		.empty();
																for ( var key in obj) {
																	$(
																			"#gzfPalacesId")
																			.append(
																					'<option value="' + obj[key].id + '">'
																							+ obj[key].name
																							+ '</option>');
																}
																$(
																		'#gzfPalacesId')
																		.trigger(
																				'change');
															});

										});
						$("#gzfPalacesId")
								.on(
										'change',
										function() {
											var gzfPalacesId = $(
													"#gzfPalacesId").val();
											$
													.ajax(
															{
																url : "${ctx}/gzf/gzfHousePerson/findPalace",
																data : {
																	gzfPalacesId : gzfPalacesId
																},
																async : true
															})
													.done(
															function(data) {
																var obj = eval(data);
																jQuery(
																		"#houseInfo")
																		.empty();
																$("#houseInfo").append('<option value="">无</option>');
																for ( var key in obj) {
																	$(
																			"#houseInfo")
																			.append(
																					'<option value="' + obj[key].id + '">'
																							+ obj[key].buildNum
																							+ '号楼'
																							+ obj[key].unit
																							+ '单元'
																							+ obj[key].room
																							+ '房间'
																							+ '</option>');
																}
																$('#houseInfo')
																		.trigger(
																				'change');
															});

										});

						$("#houseInfo")
								.change(
										function() {
											var houseInfo = $("#houseInfo")
													.val();
											$
													.ajax(
															{
																url : "${ctx}/gzf/gzfHousePerson/findHouse",
																data : {
																	id : houseInfo
																},
																async : true
															})
													.done(
															function(data) {
																var obj = eval(data);
																for ( var key in obj) {
																	$(
																			"#gzfHouseholdInfoName")
																			.val(
																					obj[key].gzfHousePerson.gzfHouseholdInfo.name);
																	$(
																			"#gzfHouseholdInfoPhone")
																			.val(
																					obj[key].gzfHousePerson.gzfHouseholdInfo.phone);
																	$(
																			"#houseType")
																			.val(
																					obj[key].houseType);
																	$(
																			"#houseBuild")
																			.val(
																					$(
																							"#gzfVillage")
																							.val()
																							+ $(
																									"#gzfPalacesId")
																									.val()
																							+ $(
																									"#houseInfo")
																									.val());
																	$(
																			"#innerArea")
																			.val(
																					obj[key].innerArea);
																	$(
																			"#useArea")
																			.val(
																					obj[key].useArea);
																	$(
																			"#houseProperty")
																			.val(
																					obj[key].gzfHousePropertyId);
																	$(
																			"#houseRemark")
																			.val(
																					obj[key].remarks);
																	$(
																			"#gzfHouseInfoId")
																			.val(
																					obj[key].id);
																	
																	$("#gzfHouseholdInfoId")
																	.val(
																			obj[key].gzfHousePerson.gzfHouseholdInfo.id);
																	

																}
															});

										});
					});
</script>
</head>
<body>
	<div id="addRepairitem" class="row-fluid">
		<div class="span12">
			<div class="portlet">
				<div class="portlet-title bottom-line"
					style="border-bottom: #f5f5f5 1px solid;">
					<div class="caption" style="color: rgb(77, 144, 254);">
						<i class="icon-edit"></i>新增报修
					</div>
				</div>
				<div class="portlet-body">
					<div class="span9">
						<form:form id="inputForm" modelAttribute="gzfRepairManagement"
							action="${ctx}/gzf/gzfRepairManagement/save" method="post"
							class="form-horizontal">
							<form:hidden path="id" />
							<form:hidden path="repairNum" />
							<sys:message content="${message}" />
							<div class="control-group ">
								<label class="control-label ">报修单号</label>
								<div class="controls ">
									<span class="pad ">${gzfRepairManagement.repairNum}</span>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">报修时间</label>
								<div class="controls ">
									<div class="input-append date date-picker"
										data-date="2015-11-15" data-date-format="yyyy-mm-dd"
										data-date-viewmode="years">
										<input name="time" type="text" readonly="readonly"
											maxlength="20"
											class="m-wrap m-ctrl-medium date-picker Wdate required"
											value="<fmt:formatDate value="${gzfRepairManagement.time}" pattern="yyyy-MM-dd HH:mm:ss"/>"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"
											style="width: 245px" /> <span class="add-on"><i
											class="icon-calendar"></i></span>
											<span class="help-inline"><font color="red">*</font> </span>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label ">报修项目</label>
								<div class="controls">
										<form:select path="repairType" class="input-xlarge"
											style="width:285px;">
											<form:options items="${repairTypeList}" itemLabel="name"
												itemValue="id" htmlEscape="false" />
										</form:select>
								</div>
							</div>
							<form:input path="gzfHouseholdInfoId" type="hidden" />
							<form:input path="gzfHouseInfoId" type="hidden" />
							<div class="portlet-body">
								<div class="control-group">
									<label class="control-label">房屋信息:</label>
									<div class="controls">
										<form:select id="gzfVillage" path="" class="input-medium"
											style="margin-right:20px;">
											<form:option value="" label="" />
											<form:options items="${gzfVillage}" itemLabel="name"
												itemValue="id" htmlEscape="false" />
										</form:select>
										<form:select id="gzfPalacesId" path=""
											class="input-medium required">
											<form:option value="" label="---请选择楼栋---" selected="true" />
										</form:select>
										<span style="margin-right: 20px;"></span>
										<form:select id="houseInfo" path=""
											class="input-medium required">
											<form:option value="" label="---请选择单元---" selected="true" />
										</form:select>
										<span style="margin-right: 20px;"></span><form:input path="repairRoom"
											htmlEscape="false" class="m-wrap large " placeholder="房间为无，手动输入地址"/> 
									</div>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">户主姓名</label>
								<div class="controls ">
									<span class="pad "> <span class="pad ">
											${gzfRepairManagement.gzfHouseholdInfo.name } </span>
											<input class="input-xlarge " id="gzfHouseholdInfoName" name="gzfHouseholdInfoName" disabled="disabled">
									</span> 
									<div id="gzfHouseholdInfoName"></div>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">户住电话</label>
								<div class="controls ">
									<span class="pad "> 
											${gzfRepairManagement.gzfHouseholdInfo.phone }
											<input class="input-xlarge " id="gzfHouseholdInfoPhone" name="gzfHouseholdInfoPhone" disabled="disabled">
									</span>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">报修人</label>
								<div class="controls ">
									<%-- <label class="radio line"> <input type="radio"
										name="optionsRadios2" value="option1" /> <form:input
											path="name" htmlEscape="false" maxlength="64"
											class="input-xlarge required" placeholder="请输入报修人姓名 " /> <span
										class="help-inline "></span>
									</label> --%>
									
									<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
									<span class="help-inline"><font color="red">*</font> </span>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">报修人电话</label>
								<div class="controls ">
									<span class="pad "> <form:input path="phone"
											htmlEscape="false" maxlength="11" class="input-xlarge required phone" />
									</span> <span class="help-inline "><font color="red">*</font></span>
								</div>
							</div>
							<div class="control-group ">
								<label class="control-label ">故障现象</label>
								<div class="control-group ">
									<div class="controls ">
										<span class="pad"> <form:textarea path="content"
												htmlEscape="false" rows="4" maxlength="64"
												class="input-xxlarge " />
										</span>
									</div>
								</div>
							</div>
							<div class="form-actions save-cancle ">
								<shiro:hasPermission name="gzf:gzfRepairManagement:edit">
									<button id="btnSubmit" class="btn blue margin-r-20 "
										type="submit">
										<i class="icon-ok"></i> 保存
									</button>
								</shiro:hasPermission>
								<input id="btnCancel" class="btn" type="button" value="取消"
									onclick="history.go(-1)" />
							</div>
						</form:form>
					</div>
					<%-- <div class="span6">
						<div class="item">
							<a class="fancybox-button" data-rel="fancybox-button" title="合同"
								href="${ctxStatic}/media/image/property-bill.jpg">
								<div align="center" class="zoom">
									<img src="media/image/property-bill.jpg" width="360px"
										alt="Photo" />
									<div class="zoom-icon"></div>
								</div>
							</a>
							<div class="details">点击浏览合同</div>
						</div>
						<div align="center">
							<button onclick="" type="submit" class="btn green">
								<i class="icon-print"></i> 打印报修单
							</button>
						</div>
					</div> --%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>