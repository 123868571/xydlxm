<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调房管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.form-wizard .step .desc {
			display:inline-block;
			color:#555;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#setupForm1").click(function(){
					var allName=$("#allName").val(); 
					$.post("${ctx}/gzf/gzfHouseChange/setup2Form?allName="+allName,
					   function(data){
							var village = data.gzfHousePerson.gzfHouseInfo.villageName +' '+ data.gzfHousePerson.gzfHouseInfo.palacesName
							+data.gzfHousePerson.gzfHouseInfo.buildNum +'号'+data.gzfHousePerson.gzfHouseInfo.unit+'单元'
							+data.gzfHousePerson.gzfHouseInfo.room+'房间';
							$("#gzfVillage1").html(village);
							$("#innerArea1").html(data.gzfHousePerson.gzfHouseInfo.innerArea);
							$("#useArea1").html(data.gzfHousePerson.gzfHouseInfo.useArea);
							$("#remarks1").html(data.gzfHousePerson.gzfHouseInfo.remarks);
							$("#name1").html(data.gzfHousePerson.gzfHouseholdInfo.name);
							$("#age1").html(data.gzfHousePerson.gzfHouseholdInfo.age);
							
							$("#phone1").html(data.gzfHousePerson.gzfHouseholdInfo.phone);
							$("#cardid1").html(data.gzfHousePerson.gzfHouseholdInfo.cardid);
							$("#nativeAddress1").html(data.gzfHousePerson.gzfHouseholdInfo.nativeProvince + data.gzfHousePerson.gzfHouseholdInfo.nativeCity
									+data.gzfHousePerson.gzfHouseholdInfo.nativeArea+data.gzfHousePerson.gzfHouseholdInfo.nativeAddress);
							$("#company1").html(data.gzfHousePerson.gzfHouseholdInfo.company);
							$("#address1").html(data.gzfHousePerson.gzfHouseholdInfo.province + data.gzfHousePerson.gzfHouseholdInfo.city
									+data.gzfHousePerson.gzfHouseholdInfo.area+data.gzfHousePerson.gzfHouseholdInfo.address);
							$("#emergencyContact1").html(data.gzfHousePerson.gzfHouseholdInfo.emergencyContact);
							$("#emergencyPhone1").html(data.gzfHousePerson.gzfHouseholdInfo.emergencyPhone);
							$("#housePerson1").val(data.gzfHousePerson.id);
					   }
					);
			  });  
			$("#setupForm2").click(function(){
				var allName=$("#allName2").val(); 
				$.post("${ctx}/gzf/gzfHouseChange/setup2Form?allName="+allName,
				   function(data){
						var village = data.gzfHousePerson.gzfHouseInfo.villageName +' '+ data.gzfHousePerson.gzfHouseInfo.palacesName
						+data.gzfHousePerson.gzfHouseInfo.buildNum +'号'+data.gzfHousePerson.gzfHouseInfo.unit+'单元'
						+data.gzfHousePerson.gzfHouseInfo.room+'房间';
						$("#gzfVillage2").html(village);
						$("#innerArea2").html(data.gzfHousePerson.gzfHouseInfo.innerArea);
						$("#useArea2").html(data.gzfHousePerson.gzfHouseInfo.useArea);
						$("#remarks2").html(data.gzfHousePerson.gzfHouseInfo.remarks);
						$("#name2").html(data.gzfHousePerson.gzfHouseholdInfo.name);
						$("#age2").html(data.gzfHousePerson.gzfHouseholdInfo.age);
						
						$("#phone2").html(data.gzfHousePerson.gzfHouseholdInfo.phone);
						$("#cardid2").html(data.gzfHousePerson.gzfHouseholdInfo.cardid);
						$("#nativeAddress2").html(data.gzfHousePerson.gzfHouseholdInfo.nativeProvince + data.gzfHousePerson.gzfHouseholdInfo.nativeCity
								+data.gzfHousePerson.gzfHouseholdInfo.nativeArea+data.gzfHousePerson.gzfHouseholdInfo.nativeAddress);
						$("#company2").html(data.gzfHousePerson.gzfHouseholdInfo.company);
						$("#address2").html(data.gzfHousePerson.gzfHouseholdInfo.province + data.gzfHousePerson.gzfHouseholdInfo.city
								+data.gzfHousePerson.gzfHouseholdInfo.area+data.gzfHousePerson.gzfHouseholdInfo.address);
						$("#emergencyContact2").html(data.gzfHousePerson.gzfHouseholdInfo.emergencyContact);
						$("#emergencyPhone2").html(data.gzfHousePerson.gzfHouseholdInfo.emergencyPhone);
						$("#housePerson2").val(data.gzfHousePerson.id);
				   }
				);
		  });  
		});
	</script>
</head>
<body>
	<div class="row-fluid">
		<div class="span12 no-padding no-margin">
				<div class="form-wizard">
					<div class="navbar steps">
						<div class="navbar-inner">
							<ul class="row-fluid nav nav-pills">
								<li class="span3 active">
									<a href="${ctx}/gzf/gzfHouseChange/setup1" data-toggle="tab" class="step">
										<span class="number">1</span>
										<span class="desc"><i class="icon-ok"></i>上传同意书</span>   
									</a>
								</li>
								<li class="span3 active">
									<a href="${ctx}/gzf/gzfHouseChange/setup2" data-toggle="tab" class="step">
										<span class="number">2</span>
										<span class="desc"><i class="icon-ok"></i>选择调房双方</span>   
									</a>
								</li>
								<li class="span3">
									<a href="${ctx}/gzf/gzfHouseChange/setup3" data-toggle="tab" class="step">
										<span class="number">3</span>
										<span class="desc"><i class="icon-ok"></i>确认调房信息</span>   
									</a>
								</li>
								<li class="span3">
									<a href="${ctx}/gzf/gzfHouseChange/setup4" data-toggle="tab" class="step">
										<span class="number">4</span>
										<span class="desc"><i class="icon-ok"></i>打印调房协议完成调房</span>   
									</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="tab-content">
						<div class="tab-pane active" id="tab2">
							<div class="row-fluid">
								<div class="span6">
									<div class="portlet box blue">
										<div style="border-bottom: 1px solid rgb(245, 245, 245);" class="portlet-title">
											<div style="color: rgb(77, 144, 254);" class="caption"><i class="icon-edit"></i><span class="whiteFont">调房甲方</span></div>
											<div class="tools">
												<a href="javascript:;" class="collapse"></a>
											</div>
										</div>
										<div class="portlet-body form">
											<!-- BEGIN CONTNET-->
											<div class="row-fluid">
												<div class="span12">
													<div class="pull-left margin-r-10">
														<input name="allName" id="allName" type="text" class="m-wrap large" placeholder="请输入姓名、电话查询">
													</div>
													<button id="setupForm1" type="button" class="btn blue"><i class="icon-search"></i> 搜索</button>	
													<div class="portlet">
														<div class="portlet-body">
															<div class="tabbable tabbable-custom tabbable-full-width">
																<div class="tab-pane profile-classic row-fluid">
																	<ul class="unstyled span12 ">
																		<li class="no-padding no-margin">
																			<span class="pad">房租地址:</span>
																			<div class="pad">
																				<div id="gzfVillage1"></div>
																			</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">套内面积:</span>
																			<div class="pad">
																			<div id="innerArea1"></div>
																			</div>
																			<span class="pad">m<sup>2</sup></span>
																		</li>
									
																		<li class="no-padding no-margin">
																			<span class="pad">使用面积:</span>
																			<div class="pad">
																				<div id="useArea1"></div>
																			</div>
																			<span class="pad">m<sup>2</sup></span>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">备注信息:</span>
																			<div class="pad">
																			<div id="remarks1"></div>
																			</div>
																		</li>
																	</ul>
																</div>
															</div>
															<div class="tabbable tabbable-custom tabbable-full-width">
																<div class="tab-pane profile-classic row-fluid">
																	<ul class="unstyled span12 ">
																		<li class="no-padding no-margin">
																			<span class="pad">姓名:</span>
																			<div class="pad">
																			<div id="name1"></div>
																			</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">年龄:</span>
																			<div class="pad">
																			<div id="age1"></div></div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">电话:</span>
																			<div class="pad">
																			<div id="phone1"></div></div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">身份证号:</span>
																			<div class="pad">
																			<div id="cardid1"></div></div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">籍贯:</span>
																			<div class="pad">
																				<div id="nativeAddress1"></div>
																			</div>
																		</li>
									
																		<li class="no-padding no-margin">
																			<span class="pad">工作单位:</span>
																			<div class="pad">
																			<div id="company1"></div></div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">家庭住址:</span>
																			<div class="pad">
																				<div id="address1"></div>
																			</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">紧急联系人:</span>
																			<div class="pad">
																			<div id="emergencyContact1"></div></div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">紧急联系人电话:</span>
																			<div class="pad">
																			<div id="emergencyPhone1"></div></div>
																		</li>
																	</ul>
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
								<div class="span6">
									<div class="portlet box blue">
										<div style="border-bottom: 1px solid rgb(245, 245, 245);" class="portlet-title">
											<div style="color: rgb(77, 144, 254);" class="caption"><i class="icon-edit"></i><span class="whiteFont">调房乙方</span></div>
											<div class="tools">
												<a href="javascript:;" class="collapse"></a>
											</div>
										</div>
										<div class="portlet-body form">
											<!-- BEGIN CONTNET-->
											<div class="row-fluid">
												<div class="span12">
													<div class="pull-left margin-r-10">
														<input name="allName2" id="allName2" type="text" class="m-wrap large" placeholder="请输入姓名、电话查询">
													</div>
													<button id="setupForm2" type="button" class="btn blue"><i class="icon-search"></i> 搜索</button>	
													<div class="portlet">
														<div class="portlet-body">
															<div class="tabbable tabbable-custom tabbable-full-width">
																<div class="tab-pane profile-classic row-fluid">
																	<ul class="unstyled span12 ">
																		<li class="no-padding no-margin">
																			<span class="pad">房租地址:</span>
																			<div class="pad">
																				<div id="gzfVillage2"></div>
																			</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">套内面积:</span>
																			<div class="pad">
																			<div id="innerArea2"></div>
																			</div>
																			<span class="pad">m<sup>2</sup></span>
																		</li>
									
																		<li class="no-padding no-margin">
																			<span class="pad">使用面积:</span>
																			<div class="pad">
																				<div id="useArea2"></div>
																			</div>
																			<span class="pad">m<sup>2</sup></span>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">备注信息:</span>
																			<div class="pad">
																			<div id="remarks2"></div>
																			</div>
																		</li>
																	</ul>
																</div>
															</div>
															<div class="tabbable tabbable-custom tabbable-full-width">
																<div class="tab-pane profile-classic row-fluid">
																	<ul class="unstyled span12 ">
																		<li class="no-padding no-margin">
																			<span class="pad">姓名:</span>
																			<div class="pad">
																			<div id="name2"></div>
																			</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">年龄:</span>
																			<div class="pad">
																			<div id="age2"></div></div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">电话:</span>
																			<div class="pad">
																			<div id="phone2"></div></div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">身份证号:</span>
																			<div class="pad">
																			<div id="cardid2"></div></div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">籍贯:</span>
																			<div class="pad">
																				<div id="nativeAddress2"></div>
																			</div>
																		</li>
									
																		<li class="no-padding no-margin">
																			<span class="pad">工作单位:</span>
																			<div class="pad">
																			<div id="company2"></div></div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">家庭住址:</span>
																			<div class="pad">
																				<div id="address2"></div>
																			</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">紧急联系人:</span>
																			<div class="pad">
																			<div id="emergencyContact2"></div></div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">紧急联系人电话:</span>
																			<div class="pad">
																			<div id="emergencyPhone2"></div></div>
																		</li>
																	</ul>
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
							<form:form id="inputForm" modelAttribute="gzfHousePerson" action="${ctx}/gzf/gzfHouseChange/setup3" method="post">
								<input name="housePerson1" id="housePerson1" type="hidden" />
								<input name="housePerson2" id="housePerson2" type="hidden" />
								<div class="form-actions clearfix">
									<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" >确认调房<i class="m-icon-swapright m-icon-white"></i></button>
								</div>	
							</form:form>
						</div>
					</div>
				</div>
			</div>
	</div>	
</body>
</html>