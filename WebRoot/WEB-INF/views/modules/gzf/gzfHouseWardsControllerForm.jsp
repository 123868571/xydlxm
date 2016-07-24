<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人员信息</title>
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
			

			$("#tab_2016_03_29_001").click(function(){
			    

				  parent.iFrameHeight();
			/* ceshi */
			
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
	<!-- BEGIN PAGE HEADER-->

	<%--<div class="row-fluid">--%>
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
	<%--<a id="guide_infotitle" href="#">信息中心</a>--%>
	<%--<i class="icon-angle-right"></i>--%>
	<%--</li>--%>
	<%--<li>--%>
	<%--<a id="guide_district_manage" href="">人员信息</a>--%>
	<%--<i class="icon-angle-right"></i>--%>
	<%--</li>--%>
	<%--<li>--%>
	<%--<a id="" href="">查看详情</a>--%>
	<%--</li>--%>
	<%--<li class="pull-right posa-back">--%>
	<%--<button class="btn btn-primary" id="" onclick="history.go(-1)"><i class="icon-arrow-left" style="color:#fff;"></i> 返回</button>--%>
	<%--</li>--%>
	<%--</ul>--%>
	<%--<!-- END PAGE TITLE & BREADCRUMB-->--%>

	<%--</div>--%>

	<%--</div>--%>

	<!-- END PAGE HEADER-->

	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzf/gzfHousePerson/form">小区<shiro:hasPermission name="gzf:gzfHousePerson:edit">${not empty gzfHousePerson.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzf:gzfHousePerson:edit">查看</shiro:lacksPermission></a></li>
	</ul> --%><br/>
	
	<!-- BEGIN PAGE CONTENT-->
	<div class="row-fluid ">
		<div class="span12">
			<div class="portlet-body">
				<div id="householdWhole" class="row-fluid">
					<div class="span12">
						<!--BEGIN CONTENT-->
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
											<li class="span4">
												<a href="#tab2" id="tab_2016_03_29_001"  data-toggle="tab" class="step">
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
									<div class="tab-pane active" id="tab1">
										<div class="row-fluid">
											<div class="span12">
												<div class="portlet box blue">
													<div style="border-bottom: 1px solid rgb(245, 245, 245);" class="portlet-title">
														<div style="color: rgb(77, 144, 254);" class="caption"><i class="icon-edit"></i><span class="whiteFont">选择换房人</span></div>
														<div class="tools">
															<a href="javascript:;" class="collapse"></a>
														</div>
													</div>
													<div class="portlet-body form">
														<!-- BEGIN CONTNET-->
														<div class="row-fluid">
																<div class="span12">
																	<form:form id="searchForm" modelAttribute="gzfHouseholdInfo" action="${ctx}/gzf/gzfHouseWardsController/form" method="post" class="breadcrumb form-search">
																		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
																		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
																		<div class="pull-left margin-r-10">
																			<form:input type="text" path="name" htmlEscape="false" class="m-wrap large" placeholder="请输入姓名、电话查询"/>
																		</div>
																		<button id="btnSubmit" type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button>
																	</form:form>
																	<div class="portlet">
																		<div class="portlet-body">
																			<div class="tabbable tabbable-custom tabbable-full-width">
																				<div class="tab-pane profile-classic row-fluid">
																					<ul class="unstyled span12 ">
																						<li class="no-padding no-margin">
																							<span class="pad">房屋类型:</span>
																							<div class="pad">${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.houseType}</div>
																						</li>
																						<li class="no-padding no-margin">
																							<span class="pad">房屋地址:</span>
																							<div class="pad">
																								${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.gzfPalaces.gzfVillage.name}
																								${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.gzfPalaces.name}
																								${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.buildNum}号
																								${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.unit}单元
																								${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.room}房间
																							</div>
																						</li>
		
																						<li class="no-padding no-margin">
																							<span class="pad">套内面积:</span>
																							<div class="pad">${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.innerArea}</div>
																							<span class="pad">m<sup>2</sup></span>
																						</li>
		
																						<li class="no-padding no-margin">
																							<span class="pad">使用面积:</span>
																							<div class="pad">${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.useArea}</div>
																							<span class="pad">m<sup>2</sup></span>
																						</li>
		
																						<li class="no-padding no-margin">
																							<span class="pad">房屋属性:</span>
																							<div class="pad">${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.gzfHouseProperty.name}</div>
																						</li>
																						<li class="no-padding no-margin">
																							<span class="pad">备注信息:</span>
																							<div class="pad">${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.remarks}</div>
																						</li>
																					</ul>
																				</div>
																			</div>
																			<div class="tabbable tabbable-custom tabbable-full-width">
																				<div class="tab-pane profile-classic row-fluid">
																					<ul class="unstyled span12 ">
																						<li class="no-padding no-margin">
																							<span class="pad">姓名:</span>
																							<div class="pad">${gzfHouseholdInfo.name}</div>
																						</li>
																						<li class="no-padding no-margin">
																							<span class="pad">性别:</span>
																							<div class="pad">${gzfHouseholdInfo.sex}</div>
																						</li>
																						<li class="no-padding no-margin">
																							<span class="pad">年龄:</span>
																							<div class="pad">${gzfHouseholdInfo.age}</div>
																						</li>
																						<li class="no-padding no-margin">
																							<span class="pad">电话:</span>
																							<div class="pad">${gzfHouseholdInfo.phone}</div>
																						</li>
																						<li class="no-padding no-margin">
																							<span class="pad">身份证号:</span>
																							<div class="pad">${gzfHouseholdInfo.cardid}</div>
																						</li>
																						<li class="no-padding no-margin">
																							<span class="pad">学历:</span>
																							<div class="pad">${gzfHouseholdInfo.education}</div>
																						</li>
																						<li class="no-padding no-margin">
																							<span class="pad">籍贯:</span>
																							<div class="pad">
																								${gzfHouseholdInfo.nativeProvince}
																								${gzfHouseholdInfo.nativeCity}
																								${gzfHouseholdInfo.nativeArea}
																								${gzfHouseholdInfo.nativeAddress}
																							</div>
																						</li>
		
																						<li class="no-padding no-margin">
																							<span class="pad">工作单位:</span>
																							<div class="pad">
																								${gzfHouseholdInfo.company}
																							</div>
																						</li>
																						<li class="no-padding no-margin">
																							<span class="pad">家庭住址:</span>
																							<div class="pad">
																								${gzfHouseholdInfo.province}
																								${gzfHouseholdInfo.city}
																								${gzfHouseholdInfo.area}
																								${gzfHouseholdInfo.address}
																							</div>
																						</li>
																						<li class="no-padding no-margin">
																							<span class="pad">紧急联系人:</span>
																							<div class="pad">${gzfHouseholdInfo.emergencyContact}</div>
																						</li>
																						<li class="no-padding no-margin">
																							<span class="pad">紧急联系人电话:</span>
																							<div class="pad">${gzfHouseholdInfo.emergencyPhone}</div>
																						</li>
																						<li class="no-padding no-margin">
																							<span class="pad">备注信息:</span>
																							<div class="pad">${gzfHouseholdInfo.remarks}</div>
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
										<form:form id="inputForm" action="${ctx}/gzf/gzfHouseWardsController/form1?gzfHouseholdInfoId=${gzfHouseholdInfo.id}" method="post" class="form-horizontal">
											<div class="form-actions">
												<shiro:hasPermission name="gzf:gzfHousePerson:edit"><input id="btnSubmit" class="btn blue" type="submit" value="确定换房"/>&nbsp;</shiro:hasPermission>
											</div>
										</form:form> 
									</div>
									<div class="tab-pane" id="tab2">
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
																		    <label class="control-label">上传同意单:</label>
																			<div class="controls">
																			<img src="media/image/property-bill.jpg" width="300px"/>	
																			<div class="margin-t-20">
																			<input type="text" placeholder="" class="m-wrap large" />
																			<a class="btn btn-file">浏览</a>
																			<a class="btn blue">开始上传</a>
																			</div>
																			</div>	
																		</div>
																		<div class="control-group">
																			<label class="control-label">房屋信息:</label>
																			<div class="controls">
																				<select style="margin:0 20px 20px 0;">
																					<option>漓江水花园</option>
																					<option>新都庄园</option>
																					<option>锋尚名居</option>
																				</select>
																				<select style="margin:0 20px 20px 0;">
																					<option>如意苑</option>
																					<option>兴达苑</option>
																					<option>广安苑</option>
																				</select>
																				<br />
																				<h6></h6>
																				<select style="width: 60px;">
																					<option>1</option>
																					<option>2</option>
																					<option>3</option>
																				</select>
																				<span>号楼</span>
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
																				<span>房间</span>
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">合同日期:</label>
																			<div class="controls">
																				<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
																					<input style="width:100px;" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" value="2015-11-15" /><span class="add-on"><i class="icon-calendar"></i></span>
																				</div>-
																				<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
																					<input style="width:100px;" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" value="2015-11-15" /><span class="add-on"><i class="icon-calendar"></i></span>
																				</div>
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">房租生效日期:</label>
																			<div class="controls">
																				<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
																					<input style="width:100px;" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" value="2015-11-15" /><span class="add-on"><i class="icon-calendar"></i></span>
																				</div>
																			</div>
																		</div>
																		<div class="control-group no-padding no-margin">
			
																			<label class="pad control-label">房租类型:</label>
																			<div class="controls">
																				<div class="pad">租赁房</div>
																			</div>
																		</div>
																		<div class="control-group no-padding no-margin">
																			<label class="pad control-label">房租地址:</label>
																			<div class="controls">
																				<div class="pad">漓江山水花园如意苑15号2单309</div>
																			</div>
																		</div>
																		<div class="control-group no-padding no-margin">
																			<label class="pad control-label">套内面积:</label>
																			<div class="controls">
																				<div class="pad">60</div>
																				<span class="pad">m<sup>2</sup></span>
																			</div>
																		</div>
																		<div class="control-group no-padding no-margin">
																			<label class="pad control-label">使用面积:</label>
																			<div class="controls">
																				<div class="pad">80</div>
																				<span class="pad">m<sup>2</sup></span>
																			</div>
																		</div>
																		<div class="control-group no-padding no-margin">
																			<label class="pad control-label">房屋属性:</label>
																			<div class="controls">
																				<div class="pad">简装</div>
																			</div>
																		</div>
																		<div class="control-group no-padding no-margin">
																			<label class="pad control-label">房屋备注:</label>
																			<div class="controls">
																				<div class="pad">房屋备注信息</div>
																			</div>
																		</div>
																		<div class="control-group">
																				<label class="control-label">备注信息:</label>
																				<div class="controls">
																					<textarea class="large m-wrap" rows="3"></textarea>
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
											<a href="javascript:;" class="btn blue button-next">
											确认信息并提交 <i class="m-icon-swapright m-icon-white"></i>
											</a>
										</div>	
									</div>
									<div class="tab-pane" id="tab3">
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
																						<span class="pad">房屋类型:</span>
																						<div class="pad">租赁房</div>
																					</li>
																					<li class="no-padding no-margin">
																						<span class="pad">房屋地址:</span>
																						<div class="pad">漓江山水花园如意苑15号2单309</div>
																					</li>
	
																					<li class="no-padding no-margin">
																						<span class="pad">套内面积:</span>
																						<div class="pad">80</div>
																						<span class="pad">m<sup>2</sup></span>
																					</li>
	
																					<li class="no-padding no-margin">
																						<span class="pad">使用面积:</span>
																						<div class="pad">60</div>
																						<span class="pad">m<sup>2</sup></span>
																					</li>
	
																					<li class="no-padding no-margin">
																						<span class="pad">房屋属性:</span>
																						<div class="pad">简装</div>
																					</li>
																					<li class="no-padding no-margin">
																						<span class="pad">备注信息:</span>
																						<div class="pad"></div>
																					</li>
																				</ul>
																			</div>
																		</div>
	
																		<div class="tabbable tabbable-custom tabbable-full-width">
																			<div class="tab-pane profile-classic row-fluid">
																				<ul class="unstyled span12 ">
																					<li class="no-padding no-margin">
																						<span class="pad">姓名:</span>
																						<div class="pad">赵雪儿</div>
																					</li>
																					<li class="no-padding no-margin">
																						<span class="pad">年龄:</span>
																						<div class="pad">34</div>
																					</li>
																					<li class="no-padding no-margin">
																						<span class="pad">电话:</span>
																						<div class="pad">15678609835</div>
																					</li>
																					<li class="no-padding no-margin">
																						<span class="pad">身份证号:</span>
																						<div class="pad">480988902989093452</div>
																					</li>
																					<li class="no-padding no-margin">
																						<span class="pad">学历:</span>
																						<div class="pad">大专</div>
																					</li>
																					<li class="no-padding no-margin">
																						<span class="pad">籍贯:</span>
																						<div class="pad">浙江杭州新都庄园广安苑1号4单1245</div>
																					</li>
	
																					<li class="no-padding no-margin">
																						<span class="pad">单位:</span>
																						<div class="pad">杭州易和网络技术有限公司</div>
																					</li>
																					<li class="no-padding no-margin">
																						<span class="pad">家庭住址:</span>
																						<div class="pad">新都庄园广安苑1号4单1245</div>
																					</li>
																					<li class="no-padding no-margin">
																						<span class="pad">紧急联系人:</span>
																						<div class="pad">王米</div>
																					</li>
																					<li class="no-padding no-margin">
																						<span class="pad">紧急联系人电话:</span>
																						<div class="pad">15690879043</div>
																					</li>
																					<li class="no-padding no-margin">
																						<span class="pad">备注信息:</span>
																						<div class="pad"></div>
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
						<!--END CONTENT-->
					</div>
					<div class="space10 visible-phone"></div>
				</div>
				<div id="householdInformation" class="row-fluid hide">
					<div class="space12">
						<div class="span6">
							<div class="portlet">
								<div class="portlet-title" style="border-bottom: #f5f5f5 1px solid;">
									<div class="caption"><i class="icon-list"></i>住户信息</div>
								</div>
								<div class="portlet-body">
									<div class="tabbable tabbable-custom tabbable-full-width">
										<div class="tab-pane profile-classic row-fluid">
											<ul class="unstyled span12 ">
												<li class="no-padding no-margin">
													<span class="pad">姓名:</span>
													<div class="pad">王丽</div>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">年龄:</span>
													<div class="pad">30</div>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">电话:</span>
													<div class="pad">13567854692</div>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">身份证号:</span>
													<div class="pad">467899875623546784</div>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">学历:</span>
													<div class="pad">大专</div>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">籍贯:</span>
													<div class="pad">浙江杭州东方城市花园红枫苑1号3单2309</div>
												</li>

												<li class="no-padding no-margin">
													<span class="pad">单位:</span>
													<div class="pad">杭州易和网络技术有限公司</div>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">家庭住址:</span>
													<div class="pad">新都庄园广安苑1号4单1245</div>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">紧急联系人:</span>
													<div class="pad">李明</div>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">紧急联系人电话:</span>
													<div class="pad">13658936846</div>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">备注信息:</span>
													<div class="pad"></div>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="span6">
							<div class="portlet">
								<div class="portlet-title" style="border-bottom: #f5f5f5 1px solid;">
									<div class="caption"><i class="icon-edit"></i>房屋信息</div>
								</div>
								<div class="portlet-body">
									<div class="tabbable tabbable-custom tabbable-full-width">
										<div class="tab-pane profile-classic row-fluid">
											<ul class="unstyled span12 ">
												<li class="no-padding no-margin">
													<span class="pad">房租类型:</span>
													<div class="pad">租赁房</div>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">房租地址:</span>
													<div class="pad">漓江山水花园如意苑15号2单309</div>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">套内面积:</span>
													<div class="pad">80</div>
													<span class="pad">m<sup>2</sup></span>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">使用面积:</span>
													<div class="pad">60</div>
													<span class="pad">m<sup>2</sup></span>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">房屋属性:</span>
													<div class="pad">简装</div>
												</li>
												<li class="no-padding no-margin">
													<span class="pad">备注信息:</span>
													<div class="pad"></div>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="space12">
								<div class="margin-l-40">
									<button id="save" class="btn blue margin-r-20 "><i class="icon-ok"></i>确定</button>
									<button type="button" class="btn">取消</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="householdHistory" class="row-fluid hide">
					<div class="span12">
						<div class="input-append">
							<input type="text" class="m-wrap" placeholder="请输入设备名称查询">
							<button type="button" class="btn blue"><i class="icon-search"></i> </button>
						</div>
						<div class="controls pull-left margin-r-20">
							<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
								<input style="width:100px;" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" value="2015-11-15" /><span class="add-on"><i class="icon-calendar"></i></span>
							</div>-
							<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
								<input style="width:100px;" class="m-wrap m-ctrl-medium date-picker" size="16" type="text" value="2015-11-15" /><span class="add-on"><i class="icon-calendar"></i></span>
							</div>
						</div>
						<div class="portlet box blue">
							<div class="portlet-title">
								<div class="caption"><i class="icon-list"></i><span class="whiteFont">"刘一飞[4109287623986753]"租房详情</span></div>
							</div>
							<div class="portlet-body">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>序号</th>
											<th>小区信息</th>
											<th>使用年限</th>
											<th>开始时间</th>
											<th>截止时间</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody>
										<tr class="odd gradeX">
											<td>01</td>
											<td>漓江山水花园如意苑15号2单309</td>
											<td>3</td>
											<td>1983-08-01</td>
											<td>1984-08-01</td>
											<td></td>
										</tr>
										<tr class="odd gradeX">
											<td>02</td>
											<td>东方城市花园红枫苑1号3单2309</td>
											<td>3</td>
											<td>1983-08-01</td>
											<td>1984-08-01</td>
											<td></td>
										</tr>
										<tr class="odd gradeX">
											<td>03</td>
											<td>和名邸小区广安苑9号6单509</td>
											<td>3</td>
											<td>1983-08-01</td>
											<td>1984-08-01</td>
											<td></td>
										</tr>
									</tbody>
								</table>
								<div class="left">显示<span id="">1</span>-<span id="">3</span>，总共：<span id="">3</span> 行</div>
								<!-- BEGIN GALLERY MANAGER PAGINATION  分页-->
								<div class="row-fluid">
									<div class="span12">
										<div align="center" class="pagination">
											<ul>
												<li><a href="#">«</a></li>
												<li><a href="#">1</a></li>
												<li><a href="#">2</a></li>
												<li><a href="#">3</a></li>
												<li><a href="#">4</a></li>
												<li><a href="#">5</a></li>
												<li><a href="#">»</a></li>
											</ul>
										</div>
									</div>
								</div>
								<!-- END GALLERY MANAGER PAGINATION-->
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END INLINE TABS PORTLET-->
		</div>
	</div>
	<!-- END PAGE CONTENT-->
	
<%-- 	<form:form id="searchForm" modelAttribute="gzfHouseholdInfo" action="${ctx}/gzf/gzfHouseWardsController/form" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<form:input path="name" htmlEscape="false" class="input-medium required" placeholder="请输入人名"/>
			</li>
			<li>
				<form:input path="phone" htmlEscape="false" class="input-medium required" placeholder="请输入手机号"/>
			</li>
			<li class="btns"><button id="btnSubmit" type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button></li>
			 <li>
          
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	
		<sys:message content="${message}"/>	
		
		<div class="control-group">
			<label class="control-label">更新时间：</label>
			<div class="controls">
			<fmt:formatDate value="${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.updateDate}" pattern="yyyy-MM-dd"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">房租类型：</label>
			<div class="controls">
			${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.houseType}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房租地址：</label>
			<div class="controls">
			${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.gzfPalaces.gzfVillage.name}
			${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.gzfPalaces.name}
			${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.buildNum}号
			${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.unit}单元
			${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.room}房间
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">套内面积：</label>
			<div class="controls">
			${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.innerArea}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用面积：</label>
			<div class="controls">
			${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.useArea}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房间属性：</label>
			<div class="controls">
			${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.gzfHouseProperty.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
			${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.remarks}
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
			${gzfHouseholdInfo.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
			${gzfHouseholdInfo.sex}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年龄：</label>
			<div class="controls">
			${gzfHouseholdInfo.age}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
			${gzfHouseholdInfo.phone}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
			${gzfHouseholdInfo.cardid}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学历：</label>
			<div class="controls">
			${gzfHouseholdInfo.education}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作单位：</label>
			<div class="controls">
			${gzfHouseholdInfo.company}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">家庭住址：</label>
			<div class="controls">
			${gzfHouseholdInfo.province}
			${gzfHouseholdInfo.city}
			${gzfHouseholdInfo.area}
			${gzfHouseholdInfo.address}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">籍贯：</label>
			<div class="controls">
			${gzfHouseholdInfo.nativeProvince}
			${gzfHouseholdInfo.nativeCity}
			${gzfHouseholdInfo.nativeArea}
			${gzfHouseholdInfo.nativeAddress}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">紧急联系人：</label>
			<div class="controls">
			${gzfHouseholdInfo.emergencyContact}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">紧急联系电话：</label>
			<div class="controls">
			${gzfHouseholdInfo.emergencyPhone}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
			${gzfHouseholdInfo.remarks}
			</div>
		</div>
		
		
		
		<form:form id="inputForm" action="${ctx}/gzf/gzfHouseWardsController/form1?gzfHouseholdInfoId=${gzfHouseholdInfo.id}" method="post" class="form-horizontal">
		<div class="form-actions">
			<shiro:hasPermission name="gzf:gzfHousePerson:edit"><input id="btnSubmit" class="btn blue" type="submit" value="确定换房"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>  --%>

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