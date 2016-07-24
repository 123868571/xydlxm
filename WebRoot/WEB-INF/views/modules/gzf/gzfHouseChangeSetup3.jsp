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
</head>
<body>
	<div class="row-fluid">
		<div class="span12 no-padding no-margin">
			<form:form id="inputForm" modelAttribute="gzfHousePerson" action="${ctx}/gzf/gzfHouseChange/setup4" method="post" class="form-horizontal">
				<form:hidden path="housePerson1"/>
				<form:hidden path="housePerson2"/>
				<div class="form-wizard">
					<div class="navbar steps">
						<div class="navbar-inner">
							<ul class="row-fluid nav nav-pills">
								<li class="span3 active">
									<a href="${ctx}/gzf/gzfHouseChange/setup1" data-toggle="tab" class="step active">
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
								<li class="span3 active">
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
						<div class="tab-pane active" id="tab3">
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
													<div class="portlet">
														<div class="portlet-body">
															<div class="tabbable tabbable-custom tabbable-full-width">
																<div class="tab-pane profile-classic row-fluid">
																	<ul class="unstyled span12 ">
																		<li class="no-padding no-margin">
																			<span class="pad">房租类型:</span>
																			<div class="pad">${fns:getDictLabel(gzfHousePerson2.gzfHouseInfo.houseType, 'house_type', '')}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">房租地址:</span>
																			<div class="pad">
																				${gzfHousePerson2.gzfHouseInfo.gzfPalaces.gzfVillage.name}
																				${gzfHousePerson2.gzfHouseInfo.gzfPalaces.name}
																				${gzfHousePerson2.gzfHouseInfo.buildNum}号
																				${gzfHousePerson2.gzfHouseInfo.unit}单元
																				${gzfHousePerson2.gzfHouseInfo.room}房间
																			</div>
																		</li>
									
																		<li class="no-padding no-margin">
																			<span class="pad">套内面积:</span>
																			<div class="pad">${gzfHousePerson2.gzfHouseInfo.innerArea}</div>
																			<span class="pad">m<sup>2</sup></span>
																		</li>
									
																		<li class="no-padding no-margin">
																			<span class="pad">使用面积:</span>
																			<div class="pad">${gzfHousePerson2.gzfHouseInfo.useArea}</div>
																			<span class="pad">m<sup>2</sup></span>
																		</li>
									
																		<li class="no-padding no-margin">
																			<span class="pad">房屋属性:</span>
																			<div class="pad">${gzfHousePerson2.gzfHouseInfo.gzfHouseProperty.name}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">备注信息:</span>
																			<div class="pad">${gzfHousePerson2.gzfHouseInfo.remarks}</div>
																		</li>
																</ul>
															</div>
															</div>
															<div class="tabbable tabbable-custom tabbable-full-width">
																<div class="tab-pane profile-classic row-fluid">
																	<ul class="unstyled span12 ">
																		<li class="no-padding no-margin">
																			<span class="pad">姓名:</span>
																			<div class="pad">${gzfHousePerson.gzfHouseholdInfo.name}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">性别:</span>
																			<div class="pad">${fns:getDictLabel(gzfHousePerson.gzfHouseholdInfo.sex, 'sex', '')}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">年龄:</span>
																			<div class="pad">${gzfHousePerson.gzfHouseholdInfo.age}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">电话:</span>
																			<div class="pad">${gzfHousePerson.gzfHouseholdInfo.phone}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">身份证号:</span>
																			<div class="pad">${gzfHousePerson.gzfHouseholdInfo.cardid}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">学历:</span>
																			<div class="pad">${fns:getDictLabel(gzfHousePerson.gzfHouseholdInfo.education, 'education', '')}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">籍贯:</span>
																			<div class="pad">
																				${gzfHousePerson.gzfHouseholdInfo.nativeProvince}
																				${gzfHousePerson.gzfHouseholdInfo.nativeCity}
																				${gzfHousePerson.gzfHouseholdInfo.nativeArea}
																				${gzfHousePerson.gzfHouseholdInfo.nativeAddress}
																			</div>
																		</li>
									
																		<li class="no-padding no-margin">
																			<span class="pad">工作单位:</span>
																			<div class="pad">${gzfHousePerson.gzfHouseholdInfo.company}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">家庭住址:</span>
																			<div class="pad">
																				${gzfHousePerson.gzfHouseholdInfo.province}
																				${gzfHousePerson.gzfHouseholdInfo.city}
																				${gzfHousePerson.gzfHouseholdInfo.area}
																				${gzfHousePerson.gzfHouseholdInfo.address}
																			</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">紧急联系人:</span>
																			<div class="pad">${gzfHousePerson.gzfHouseholdInfo.emergencyContact}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">紧急联系人电话:</span>
																			<div class="pad">${gzfHousePerson.gzfHouseholdInfo.emergencyPhone}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">备注信息:</span>
																			<div class="pad">${gzfHousePerson.gzfHouseholdInfo.remarks}</div>
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
													<div class="portlet">
														<div class="portlet-body">
															<div class="tabbable tabbable-custom tabbable-full-width">
																<div class="tab-pane profile-classic row-fluid">
																	<ul class="unstyled span12 ">
																		<li class="no-padding no-margin">
																			<span class="pad">房租类型:</span>
																			<div class="pad">${fns:getDictLabel(gzfHousePerson.gzfHouseInfo.houseType, 'house_type', '')}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">房租地址:</span>
																			<div class="pad">
																				${gzfHousePerson.gzfHouseInfo.gzfPalaces.gzfVillage.name}
																				${gzfHousePerson.gzfHouseInfo.gzfPalaces.name}
																				${gzfHousePerson.gzfHouseInfo.buildNum}号
																				${gzfHousePerson.gzfHouseInfo.unit}单元
																				${gzfHousePerson.gzfHouseInfo.room}房间
																			</div>
																		</li>
									
																		<li class="no-padding no-margin">
																			<span class="pad">套内面积:</span>
																			<div class="pad">${gzfHousePerson.gzfHouseInfo.innerArea}</div>
																			<span class="pad">m<sup>2</sup></span>
																		</li>
									
																		<li class="no-padding no-margin">
																			<span class="pad">使用面积:</span>
																			<div class="pad">${gzfHousePerson.gzfHouseInfo.useArea}</div>
																			<span class="pad">m<sup>2</sup></span>
																		</li>
									
																		<li class="no-padding no-margin">
																			<span class="pad">房屋属性:</span>
																			<div class="pad">${gzfHousePerson.gzfHouseInfo.gzfHouseProperty.name}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">备注信息:</span>
																			<div class="pad">${gzfHousePerson.gzfHouseInfo.remarks}</div>
																		</li>
																	</ul>
																</div>
															</div>
															<div class="tabbable tabbable-custom tabbable-full-width">
																<div class="tab-pane profile-classic row-fluid">
																	<ul class="unstyled span12 ">
																		<li class="no-padding no-margin">
																			<span class="pad">姓名:</span>
																			<div class="pad">${gzfHousePerson2.gzfHouseholdInfo.name}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">性别:</span>
																			<div class="pad">${fns:getDictLabel(gzfHousePerson2.gzfHouseholdInfo.sex, 'sex', '')}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">年龄:</span>
																			<div class="pad">${gzfHousePerson2.gzfHouseholdInfo.age}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">电话:</span>
																			<div class="pad">${gzfHousePerson2.gzfHouseholdInfo.phone}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">身份证号:</span>
																			<div class="pad">${gzfHousePerson2.gzfHouseholdInfo.cardid}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">学历:</span>
																			<div class="pad">${fns:getDictLabel(gzfHousePerson2.gzfHouseholdInfo.education, 'education', '')}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">籍贯:</span>
																			<div class="pad">
																				${gzfHousePerson2.gzfHouseholdInfo.nativeProvince}
																				${gzfHousePerson2.gzfHouseholdInfo.nativeCity}
																				${gzfHousePerson2.gzfHouseholdInfo.nativeArea}
																				${gzfHousePerson2.gzfHouseholdInfo.nativeAddress}
																			</div>
																		</li>
									
																		<li class="no-padding no-margin">
																			<span class="pad">工作单位:</span>
																			<div class="pad">${gzfHousePerson2.gzfHouseholdInfo.company}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">家庭住址:</span>
																			<div class="pad">
																				${gzfHousePerson2.gzfHouseholdInfo.province}
																				${gzfHousePerson2.gzfHouseholdInfo.city}
																				${gzfHousePerson2.gzfHouseholdInfo.area}
																				${gzfHousePerson2.gzfHouseholdInfo.address}
																			</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">紧急联系人:</span>
																			<div class="pad">${gzfHousePerson2.gzfHouseholdInfo.emergencyContact}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">紧急联系人电话:</span>
																			<div class="pad">${gzfHousePerson2.gzfHouseholdInfo.emergencyPhone}</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">备注信息:</span>
																			<div class="pad">${gzfHousePerson2.gzfHouseholdInfo.remarks}</div>
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
							<div class="form-actions clearfix">
								<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" >确认信息并提交<i class="m-icon-swapright m-icon-white"></i></button>
							</div>	
						</div>
					</div>
				</div>
				</form:form>
			</div>
	</div>	
</body>
</html>