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
			<form novalidate="novalidate" action="#" class="form-horizontal" id="submit_form">
				<div class="form-wizard">
					<div class="navbar steps">
						<div class="navbar-inner">
							<ul class="row-fluid nav nav-pills">
								<li class="span3 active">
									<a href="#tab1" data-toggle="tab" class="step active">
										<span class="number">1</span>
										<span class="desc"><i class="icon-ok"></i>上传同意书</span>   
									</a>
								</li>
								<li class="span3">
									<a href="#tab2" data-toggle="tab" class="step active">
										<span class="number">2</span>
										<span class="desc"><i class="icon-ok"></i>选择调房双方</span>   
									</a>
								</li>
								<li class="span3">
									<a href="#tab3" data-toggle="tab" class="step">
										<span class="number">3</span>
										<span class="desc"><i class="icon-ok"></i>确认调房信息</span>   
									</a>
								</li>
								<li class="span3">
									<a href="#tab4" data-toggle="tab" class="step">
										<span class="number">4</span>
										<span class="desc"><i class="icon-ok"></i>打印调房协议完成调房</span>   
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
											<div style="color: rgb(77, 144, 254);" class="caption"><i class="icon-edit"></i><span class="whiteFont">上传同意单</span></div>
											<div class="tools">
												<a href="javascript:;" class="collapse"></a>
											</div>
										</div>
										<div class="portlet-body form">
											<div class="row-fluid">
												<div class="span12">
													<div class="control-group">
													    <label class="control-label">同意单:</label>
														<div class="controls">
															<img src="" width="300px"/>	
															<div class="margin-t-20">
																<input type="text" placeholder="" class="m-wrap large" />
																<a class="btn btn-file">浏览</a>
																<a class="btn blue">开始上传</a>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-actions clearfix">
								<a href="javascript:;" class="btn blue button-next">
								确认 <i class="m-icon-swapright m-icon-white"></i>
								</a>
							</div>	
						</div>
						<div class="tab-pane" id="tab2">
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
														<input type="text" class="m-wrap large" placeholder="请输入姓名、电话查询">
													</div>
													<button type="button" class="btn blue"><i class="icon-search"></i> 搜索</button>	
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
																			<div class="pad">蒋村花园兴达苑3号6单909</div>
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
														<input type="text" class="m-wrap large" placeholder="请输入姓名、电话查询">
													</div>
													<button type="button" class="btn blue"><i class="icon-search"></i> 搜索</button>	
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
																			<span class="pad">房屋租地址:</span>
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
											</div>
											<!-- END FORM-->
										</div>
									</div>
								</div>
							</div>
							<div class="form-actions clearfix">
								<a href="javascript:;" class="btn blue button-next">
								确认调房 <i class="m-icon-swapright m-icon-white"></i>
								</a>
							</div>	
						</div>
						<div class="tab-pane" id="tab3">
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
																			<span class="pad">房屋类型:</span>
																			<div class="pad">租赁房</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">房屋地址:</span>
																			<div class="pad"><strong>漓江山水花园如意苑15号2单309</strong></div>
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
																			<span class="pad">房屋类型:</span>
																			<div class="pad">租赁房</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">房屋地址:</span>
																			<div class="pad"><strong>蒋村花园兴达苑3号6单909</strong></div>
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
						<div class="tab-pane" id="tab4">
							<div class="row-fluid">
								<div class="span6">
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
												<div class="span12">
													<div class="portlet">
														<div class="span12 pull-right">
															<div class="item">
																<a class="fancybox-button" data-rel="fancybox-button" title="合同" href="media/image/house-ht.jpg">
																	<div align="center" class="zoom">
																		<img src="media/image/house-ht2.jpg" width="231px" alt="Photo" />
																		<div class="zoom-icon"></div>
																	</div>
																</a>
																<div class="details">
																	点击浏览合同
																</div>
															</div>
															<div align="center">
																<button onclick="" type="submit" class="btn green"><i class="icon-print"></i> 打印调房协议</button>
															</div>
														</div>
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
											</div>
											<!-- END FORM-->
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="portlet box blue">
										<div style="border-bottom: 1px solid rgb(245, 245, 245);" class="portlet-title">
											<div style="color: rgb(77, 144, 254);" class="caption"><i class="icon-edit"></i><span class="whiteFont">调房乙方协议</span></div>
											<div class="tools">
												<a href="javascript:;" class="collapse"></a>
											</div>
										</div>
										<div class="portlet-body form">
											<!-- BEGIN CONTNET-->
											<div class="row-fluid">
												<div class="span12">
													<div class="portlet">
														<div class="span12 pull-right">
															<div class="item">
																<a class="fancybox-button" data-rel="fancybox-button" title="合同" href="media/image/house-ht.jpg">
																	<div align="center" class="zoom">
																		<img src="media/image/house-ht2.jpg" width="231px" alt="Photo" />
																		<div class="zoom-icon"></div>
																	</div>
																</a>
																<div class="details">
																	点击浏览合同
																</div>
															</div>
															<div align="center">
																<button onclick="" type="submit" class="btn green"><i class="icon-print"></i> 打印调房协议</button>
															</div>
														</div>
														<div class="portlet-body">
															<div class="tabbable tabbable-custom tabbable-full-width">
																<div class="tab-pane profile-classic row-fluid">
																	<ul class="unstyled span12 ">
																		<li class="no-padding no-margin">
																			<span class="pad">房屋类型:</span>
																			<div class="pad">租赁房</div>
																		</li>
																		<li class="no-padding no-margin">
																			<span class="pad">房屋租地址:</span>
																			<div class="pad">蒋村花园兴达苑3号6单909</div>
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
											</div>
											<!-- END FORM-->
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