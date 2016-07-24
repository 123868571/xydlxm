<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>换房管理</title>
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
			<form novalidate="novalidate" action="#" class="form-horizontal" id="submit_form">
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
								<li class="span4 active">
									<a href="#tab3" data-toggle="tab" class="step">
										<span class="number">3</span>
										<span class="desc" style="color:#555"><i class="icon-ok"></i>打印换房协议完成换房</span>   
									</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="tab-content">
								<div class="tab-pane active" id="tab3">
										<div class="row-fluid">
											<div class="span12">
												<div class="portlet box blue">
													<div style="border-bottom: 1px solid rgb(245, 245, 245);" class="portlet-title">
														<div style="color: rgb(77, 144, 254);" class="caption"><i class="icon-edit"></i><span class="whiteFont">调房甲方协议</span></div>
														<div class="tools">
															<a href="javascript:;" class="collapse"></a>
														</div>
													</div>
													<div class="portlet-body form">
														<!-- BEGIN CONTNET-->
														<div class="row-fluid">
															<div class="span6">
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
															<div class="span6 pull-right">
																<div class="item">
																	<a class="fancybox-button" data-rel="fancybox-button" title="合同" href="media/image/house-ht.jpg">
																		<div align="center" class="zoom">
																			<img src="${ctxStatic}/media/image/house-ht2.jpg" width="231px" alt="Photo" />
																			<div class="zoom-icon"></div>
																		</div>
																	</a>
																	<div class="details">
																		点击浏览合同
																	</div>
																</div>
																<div align="center">
																	<button onclick="" type="submit" class="btn green"><i class="icon-print"></i> 打印换房协议</button>
																</div>
															</div>
														</div>
														<!-- END FORM-->
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
						</div>
				</form>
			</div>
	</div>	
</body>
</html>