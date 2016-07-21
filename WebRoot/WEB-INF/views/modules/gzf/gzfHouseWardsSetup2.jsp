<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人员信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			if($("#gzfVillage").val() != null && $("#gzfVillage").val() != ""){
				var gzfVillageId = $("#gzfVillage").val();
				$.ajax({url : "${ctx}/gzf/gzfHousePerson/findName",data : {gzfVillageId:gzfVillageId},async : true}).done(function(data){
					var obj = eval(data);
					jQuery("#gzfPalacesId").empty();
				   for(var key in obj){
				   		$("#gzfPalacesId").append('<option value="' + obj[key].id + '">' + obj[key].name + '</option>');
				   }
				});
			}
			
			if(($("#gzfVillage").val() != null && $("#gzfVillage").val() != "") && ($("#gzfPalacesId").val() != null && $("#gzfPalacesId").val() != "")){
				var gzfPalacesId = $("#gzfPalacesId").val();
				$.ajax({url : "${ctx}/gzf/gzfHousePerson/findPalace",data : {gzfPalacesId:gzfPalacesId},async : true}).done(function(data){
					var obj = eval(data);
					jQuery("#houseInfo").empty();
				   for(var key in obj){
				   		$("#houseInfo").append('<option value="' + obj[key].id + '">' + obj[key].buildNum + '号楼' + obj[key].unit + '单元' + obj[key].room + '房间' + '</option>');
				   }
				});
			}
			
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
	<!-- BEGIN PAGE CONTENT-->
	<div class="row-fluid ">
		<div class="span12">
			<div class="portlet-body">
				<div id="householdWhole" class="row-fluid">
					<div class="span12">
						<!--BEGIN CONTENT-->
							<div class="form-wizard">
								<div class="navbar steps">
									<div class="navbar-inner">
										<ul class="row-fluid nav nav-pills">
											<li class="span4 active">
												<a href="#tab1" data-toggle="tab" class="step active">
													<span class="number">1</span>
													<span class="desc" style="color:#555"><i class="icon-ok"></i>选择换房人</span>   
												</a>
											</li>
											<li class="span4 active">
												<a href="#tab2" data-toggle="tab" class="step">
													<span class="number">2</span>
													<span class="desc" style="color:#555"><i class="icon-ok"></i>选择房源确认换房</span>   
												</a>
											</li>
											<li class="span4">
												<a href="#tab3" data-toggle="tab" class="step">
													<span class="number">3</span>
													<span class="desc" style="color:#555"><i class="icon-ok"></i>打印换房协议完成换房</span>   
												</a>
											</li>
										</ul>
									</div>
								</div>
								<div class="tab-content">
									<div class="tab-pane active" id="tab2">
										<form:form id="inputForm"  modelAttribute="gzfHousePerson" action="${ctx}/gzf/gzfHouseWardsController/setup3" method="post" class="form-horizontal">
										<form:hidden path="housePerson1"/>
										<form:hidden path="gzfHouseInfoId"/>
										<div class="row-fluid">
											<div class="span12">
												<div class="portlet box blue">
													<div style="border-bottom: 1px solid rgb(245, 245, 245);" class="portlet-title">
														<div style="color: rgb(77, 144, 254);" class="caption"><i class="icon-edit"></i><span class="whiteFont">选择换房房源</span></div>
														<div class="tools">
															<a href="javascript:;" class="collapse"></a>
														</div>
													</div>
													<div class="portlet-body form">
														<!-- BEGIN CONTNET-->
														<div class="row-fluid">
															<div class="span12">
																<div class="portlet">
																	<div class="portlet-body">
																		<div class="control-group">
																			<label class="control-label">上传同意书:</label>
																			<div class="controls">
																				<form:hidden id="nameImage" path="remarks" htmlEscape="false" maxlength="255" class="input-xlarge" />
																				<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="300" maxHeight="300"/>
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">房屋信息:</label>
																			<div class="controls">
																				<form:select id="gzfVillage" path="" class="input-medium" style="margin-right:20px;">
																					<form:option value="" label=""/>
																					<form:options items="${gzfVillage}" itemLabel="name" itemValue="id" htmlEscape="false"/>
																				</form:select><span style="margin-right:20px;"></span>
																				<form:select id="gzfPalacesId" path="" class="input-medium required">
																					<form:option value="" label="---请选择苑---" selected="true"/>
																				</form:select><span style="margin-right:20px;"></span>
																				<form:select id="houseInfo" path="" class="input-large required">
																					<form:option value="" label="---请选择房间---" selected="true"/>
																				</form:select><span style="margin-right:20px;"></span>
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">合同日期:</label>
																			<div class="controls">
																				<div class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
																					<input name="startDate" type="text" readonly="readonly" maxlength="20" class="m-wrap m-ctrl-medium date-picker input-medium Wdate required"
																						value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd"/>"
																						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/><span class="add-on"><i class="icon-calendar"></i></span>
																				</div>-
																				<div class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
																					<input name="endDate" type="text" readonly="readonly" maxlength="20" class="m-wrap m-ctrl-medium date-picker input-medium Wdate required"
																						value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>"
																						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/><span class="add-on"><i class="icon-calendar"></i></span>
																				</div>
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">房租生效日期:</label>
																			<div class="controls">
																				<div class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
																					<input name="effectiveDate" type="text" readonly="readonly" maxlength="20" class="m-wrap m-ctrl-medium date-picker input-medium Wdate required"
																						value="<fmt:formatDate value="${effectiveDate}" pattern="yyyy-MM-dd"/>"
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
																	</div>
																</div>	
															</div>
														</div>
														<!-- END FORM-->
													</div>
												</div>
											</div>
										</div>
										<div class="form-actions clearfix">
											<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" >确认信息并提交 <i class="m-icon-swapright m-icon-white"></i></button>
										</div>	
									</form:form>
									</div>
								</div>
							</div>
						<!--END CONTENT-->
					</div>
					<div class="space10 visible-phone"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>