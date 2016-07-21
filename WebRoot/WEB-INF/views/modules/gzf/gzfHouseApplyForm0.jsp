<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>调房管理</title>
<meta name="decorator" content="default" />
<style type="text/css">
.form-wizard .step .desc {
	display: inline-block;
	color: #555;
}

table {
	text-align: center;
}

.bi-right {
	border-right: solid 1px #000;
}

.bi-bottom {
	border-bottom: solid 1px #000;
}

.w80 {
	width: 80%;
}
</style>
</head>
<body>
	<div class="row-fluid">
		<c:if test="${houseHoldInfo != null}">
			<div class="span12 no-padding no-margin">
				<form novalidate="novalidate" action="#" class="form-horizontal"
					id="submit_form">
					<div class="form-wizard">
						<div class="navbar steps">
							<div class="navbar-inner">
								<ul class="row-fluid nav nav-pills">
									<li class="span3 active"><a href="#tab1" data-toggle="tab"
										class="step active"> <span class="number">1</span> <span
											class="desc"><i class="icon-ok"></i>公租房申请表Ⅰ</span>
									</a></li>
									<li class="span3"><a href="#tab2" data-toggle="tab"
										class="step active"> <span class="number">2</span> <span
											class="desc"><i class="icon-ok"></i>公租房申请表Ⅱ</span>
									</a></li>
									<li class="span3"><a href="#tab3" data-toggle="tab"
										class="step"> <span class="number">3</span> <span
											class="desc"><i class="icon-ok"></i>公租房申请表Ⅲ</span>
									</a></li>
								</ul>
							</div>
						</div>
						<div class="tab-content">
							<!-- tab1 start -->
							<div class="tab-pane active" id="tab1">
								<div class="row-fluid">
									<div class="span12">
										<div class="portlet box blue">
											<div style="border-bottom: 1px solid rgb(245, 245, 245);"
												class="portlet-title">
												<div style="color: rgb(77, 144, 254);" class="caption">
													<i class="icon-edit1"></i><span class="whiteFont">城镇中等偏低以下收入住房困难家庭申请使用</span>
												</div>
											</div>
											<div class="portlet-body form">
												<div class="row-fluid">
													<div class="span12">
														<div class="control-group">
															<table border="1" style="width: 100%">
																<thead>
																	<th colspan="7"><h3>公 共 租 赁 住 房 申 请 表Ⅰ</h3> <br />（城镇中等偏低以下收入住房困难家庭申请使用）</th>
																</thead>
																<tbody>
																	<tr>
																		<td width="15%">申请人信息</td>
																		<td width="10%">姓名</td>
																		<td width="15%"><input type="text" readonly=true
																			value="${houseHoldInfo.name}"></td>
																		<td width="15%">身份证号码</td>
																		<td width="15%"><input type="text" readonly=true
																			value="${houseHoldInfo.cardid}"></td>
																		<td width="15%">联系电话</td>
																		<td width="15%"><input type="text" readonly=true
																			value="${houseHoldInfo.phone}"></td>
																	</tr>
																	<tr>
																		<td></td>
																		<td>婚姻状况</td>
																		<td colspan="3"><form:radiobuttons
																				path="houseHoldInfo.maritalStatus"
																				items="${fns:getDictList('marital_status')}"
																				itemLabel="label" itemValue="value"
																				htmlEscape="false" class="required" /></td>
																		<td>年收入(元)</td>
																		<td><input type="text"></td>
																	</tr>
																	<tr>
																		<td></td>
																		<td>居住情况</td>
																		<td colspan="3">
																			<table width="100%">
																				<tr>
																					<td width="20%">自有房屋地址:</td>
																					<td width="80%"><input type="text"
																						style="width: 356px"></td>
																				</tr>
																			</table>

																		</td>
																		<td>房屋产权人</td>
																		<td><input type="text"></td>
																	</tr>
																	<tr>
																		<td></td>
																		<td></td>
																		<td colspan="3">
																			<table width="100%">
																				<tr>
																					<td width="20%">借住房屋地址:</td>
																					<td width="80%"><input type="text"
																						style="width: 356px"></td>
																				</tr>
																			</table>

																		</td>
																		<td>房屋所有人</td>
																		<td><input type="text"></td>
																	</tr>
																	<tr>
																		<td></td>
																		<td>户籍性质</td>
																		<td colspan="5" style="text-align: left"><input
																			type="radio" name="census" style="margin-left: 80px">农业</input>
																			<input type="radio" name="census"
																			style="margin-left: 80px">非农业</input></td>
																	</tr>
																	<tr>
																		<td></td>
																		<td>工作单位</td>
																		<td><input type="radio" name="companyRadio">无</input></td>
																		<td>职业状况<br />（选一项）
																		</td>
																		<td colspan="3" style="text-align: center"><input
																			type="radio" name="noWorkRadio">失业</input> <input
																			type="radio" name="noWorkRadio"
																			style="margin-left: 80px">待业</input></td>
																	</tr>
																	<tr>
																		<td></td>
																		<td></td>
																		<td><input type="radio" name="companyRadio">有</input></td>
																		<td>单位全称</td>
																		<td colspan="2"><input type="text" /></td>
																		<td style="text-align: center"><input
																			type="radio" name="workRadio">工作</input> <input
																			type="radio" name="workRadio"
																			style="margin-left: 40px">退休</input></td>
																	</tr>
																	<tr>
																		<td colspan="7">
																			<table width="100%" height="100%">
																				<thead>
																					<th class="bi-right bi-bottom" width="20%">配偶</th>
																					<th class="bi-right bi-bottom" width="20%">联系电话</th>
																					<th class="bi-right bi-bottom" width="20%">身份证号码</th>
																					<th class="bi-right bi-bottom" width="20%">工作单位</th>
																					<th class="bi-bottom">年收入(元)</th>
																				</thead>
																				<tbody>
																					<tr>
																						<td class="bi-right"><input type="text"
																							class="w80"></td>
																						<td class="bi-right"><input type="text"
																							class="w80"></td>
																						<td class="bi-right"><input type="text"
																							class="w80"></td>
																						<td class="bi-right"><input type="text"
																							class="w80"></td>
																						<td><input type="text" class="w80"></td>
																					</tr>
																				</tbody>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="7">
																			<table width="100%" height="100%">
																				<thead>
																					<th class="bi-right bi-bottom" width="20%">申请家庭<br />成员姓名
																					</th>
																					<th class="bi-right bi-bottom" width="10%">与申请人关系</th>
																					<th class="bi-right bi-bottom" width="10%">婚姻状况</th>
																					<th class="bi-right bi-bottom" width="20%">身份证号码</th>
																					<th class="bi-right bi-bottom" width="20%">工作单位</th>
																					<th class="bi-bottom">年收入(元)</th>
																				</thead>
																				<tbody>
																					<tr>
																						<td class="bi-right bi-bottom"><input
																							type="text" class="w80"></td>
																						<td class="bi-right bi-bottom"><input
																							type="text" class="w80"></td>
																						<td class="bi-right bi-bottom"><input
																							type="text" class="w80"></td>
																						<td class="bi-right bi-bottom"><input
																							type="text" class="w80"></td>
																						<td class="bi-right bi-bottom"><input
																							type="text" class="w80"></td>
																						<td class="bi-bottom"><input type="text"
																							class="w80"></td>
																					</tr>
																					<tr>
																						<td class="bi-right"><input type="text"
																							class="w80"></td>
																						<td class="bi-right"><input type="text"
																							class="w80"></td>
																						<td class="bi-right"><input type="text"
																							class="w80"></td>
																						<td class="bi-right"><input type="text"
																							class="w80"></td>
																						<td class="bi-right"><input type="text"
																							class="w80"></td>
																						<td><input type="text" class="w80"></td>
																					</tr>
																				</tbody>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="7">
																			<table width="100%" height="100%">
																				<tbody>
																					<tr>
																						<td width="10%" class="bi-right bi-bottom">困难证类型</td>
																						<td width="20%" class="bi-right bi-bottom"><input
																							type="radio" name="kunnanRadio">低保</input><input
																							type="radio" name="kunnanRadio"
																							style="margin-left: 40px">特困</input></td>
																						<td width="10%" class="bi-right bi-bottom">证号</td>
																						<td width="20%" class="bi-right bi-bottom"><input
																							type="text" /></td>
																						<td width="10%" class="bi-right bi-bottom">有效截止日期</td>
																						<td width="20%" class="bi-bottom"><input
																							type="text" readonly="readonly" maxlength="20"
																							class="input-xlarge Wdate required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></td>
																					</tr>
																					<tr>
																						<td class="bi-right">其他特殊情况</td>
																						<td colspan="5"><input type="checkbox"
																							name="otherbox">申请夫妻一方60周岁（含）以上</input><input
																							type="checkbox" name="otherbox"
																							style="margin-left: 40px">重点优抚对象</input><input
																							type="checkbox" name="otherbox"
																							style="margin-left: 40px">市级以上劳动模范家庭</input><input
																							type="checkbox" name="otherbox"
																							style="margin-left: 40px">见义勇为伤残人员</input><input
																							type="checkbox" name="otherbox"
																							style="margin-left: 40px">重大疾病家庭</input></td>
																					</tr>
																				</tbody>
																			</table>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-actions clearfix">
									<a href="javascript:;" class="btn blue button-next"> 确认 <i
										class="m-icon-swapright m-icon-white"></i>
									</a>
								</div>
							</div>
							<!-- tab1 end -->
							<!-- tab2 start -->
							<div class="tab-pane" id="tab2">
								<div class="row-fluid">
									<div class="span6">
										<div class="portlet box blue">
											<div style="border-bottom: 1px solid rgb(245, 245, 245);"
												class="portlet-title">
												<div style="color: rgb(77, 144, 254);" class="caption">
													<i class="icon-edit"></i><span class="whiteFont">调房甲方</span>
												</div>
												<div class="tools">
													<a href="javascript:;" class="collapse"></a>
												</div>
											</div>
											<div class="portlet-body form">
												<!-- BEGIN CONTNET-->
												<div class="row-fluid">
													<div class="span12">
														<div class="pull-left margin-r-10">
															<input type="text" class="m-wrap large"
																placeholder="请输入姓名、电话查询">
														</div>
														<button type="button" class="btn blue">
															<i class="icon-search"></i> 搜索
														</button>
														<div class="portlet">
															<div class="portlet-body">
																<div
																	class="tabbable tabbable-custom tabbable-full-width">
																	<div class="tab-pane profile-classic row-fluid">
																		<ul class="unstyled span12 ">
																			<li class="no-padding no-margin"><span
																				class="pad">房屋类型:</span>
																				<div class="pad">租赁房</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">房屋地址:</span>
																				<div class="pad">蒋村花园兴达苑3号6单909</div></li>

																			<li class="no-padding no-margin"><span
																				class="pad">套内面积:</span>
																				<div class="pad">80</div> <span class="pad">m<sup>2</sup></span>
																			</li>

																			<li class="no-padding no-margin"><span
																				class="pad">使用面积:</span>
																				<div class="pad">60</div> <span class="pad">m<sup>2</sup></span>
																			</li>

																			<li class="no-padding no-margin"><span
																				class="pad">房屋属性:</span>
																				<div class="pad">简装</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">备注信息:</span>
																				<div class="pad"></div></li>
																		</ul>
																	</div>
																</div>
																<div
																	class="tabbable tabbable-custom tabbable-full-width">
																	<div class="tab-pane profile-classic row-fluid">
																		<ul class="unstyled span12 ">
																			<li class="no-padding no-margin"><span
																				class="pad">姓名:</span>
																				<div class="pad">赵雪儿</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">年龄:</span>
																				<div class="pad">34</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">电话:</span>
																				<div class="pad">15678609835</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">身份证号:</span>
																				<div class="pad">480988902989093452</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">学历:</span>
																				<div class="pad">大专</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">籍贯:</span>
																				<div class="pad">浙江杭州新都庄园广安苑1号4单1245</div></li>

																			<li class="no-padding no-margin"><span
																				class="pad">单位:</span>
																				<div class="pad">杭州易和网络技术有限公司</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">家庭住址:</span>
																				<div class="pad">新都庄园广安苑1号4单1245</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">紧急联系人:</span>
																				<div class="pad">王米</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">紧急联系人电话:</span>
																				<div class="pad">15690879043</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">备注信息:</span>
																				<div class="pad"></div></li>
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
											<div style="border-bottom: 1px solid rgb(245, 245, 245);"
												class="portlet-title">
												<div style="color: rgb(77, 144, 254);" class="caption">
													<i class="icon-edit"></i><span class="whiteFont">调房乙方</span>
												</div>
												<div class="tools">
													<a href="javascript:;" class="collapse"></a>
												</div>
											</div>
											<div class="portlet-body form">
												<!-- BEGIN CONTNET-->
												<div class="row-fluid">
													<div class="span12">
														<div class="pull-left margin-r-10">
															<input type="text" class="m-wrap large"
																placeholder="请输入姓名、电话查询">
														</div>
														<button type="button" class="btn blue">
															<i class="icon-search"></i> 搜索
														</button>
														<div class="portlet">
															<div class="portlet-body">
																<div
																	class="tabbable tabbable-custom tabbable-full-width">
																	<div class="tab-pane profile-classic row-fluid">
																		<ul class="unstyled span12 ">
																			<li class="no-padding no-margin"><span
																				class="pad">房屋类型:</span>
																				<div class="pad">租赁房</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">房屋租地址:</span>
																				<div class="pad">漓江山水花园如意苑15号2单309</div></li>

																			<li class="no-padding no-margin"><span
																				class="pad">套内面积:</span>
																				<div class="pad">80</div> <span class="pad">m<sup>2</sup></span>
																			</li>

																			<li class="no-padding no-margin"><span
																				class="pad">使用面积:</span>
																				<div class="pad">60</div> <span class="pad">m<sup>2</sup></span>
																			</li>
																			<li class="no-padding no-margin"><span
																				class="pad">房屋属性:</span>
																				<div class="pad">简装</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">备注信息:</span>
																				<div class="pad"></div></li>
																		</ul>
																	</div>
																</div>
																<div
																	class="tabbable tabbable-custom tabbable-full-width">
																	<div class="tab-pane profile-classic row-fluid">
																		<ul class="unstyled span12 ">
																			<li class="no-padding no-margin"><span
																				class="pad">姓名:</span>
																				<div class="pad">王丽</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">年龄:</span>
																				<div class="pad">30</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">电话:</span>
																				<div class="pad">13567854692</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">身份证号:</span>
																				<div class="pad">467899875623546784</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">学历:</span>
																				<div class="pad">大专</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">籍贯:</span>
																				<div class="pad">浙江杭州东方城市花园红枫苑1号3单2309</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">单位:</span>
																				<div class="pad">杭州易和网络技术有限公司</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">家庭住址:</span>
																				<div class="pad">新都庄园广安苑1号4单1245</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">紧急联系人:</span>
																				<div class="pad">李明</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">紧急联系人电话:</span>
																				<div class="pad">13658936846</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">备注信息:</span>
																				<div class="pad"></div></li>
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
									<a href="javascript:;" class="btn blue button-next"> 确认调房 <i
										class="m-icon-swapright m-icon-white"></i>
									</a>
								</div>
							</div>
							<!-- tab2 end -->
							<!-- tab3 start -->
							<div class="tab-pane" id="tab3">
								<div class="row-fluid">
									<div class="span6">
										<div class="portlet box blue">
											<div style="border-bottom: 1px solid rgb(245, 245, 245);"
												class="portlet-title">
												<div style="color: rgb(77, 144, 254);" class="caption">
													<i class="icon-edit"></i><span class="whiteFont">调房甲方</span>
												</div>
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
																<div
																	class="tabbable tabbable-custom tabbable-full-width">
																	<div class="tab-pane profile-classic row-fluid">
																		<ul class="unstyled span12 ">
																			<li class="no-padding no-margin"><span
																				class="pad">房屋类型:</span>
																				<div class="pad">租赁房</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">房屋地址:</span>
																				<div class="pad">
																					<strong>漓江山水花园如意苑15号2单309</strong>
																				</div></li>

																			<li class="no-padding no-margin"><span
																				class="pad">套内面积:</span>
																				<div class="pad">80</div> <span class="pad">m<sup>2</sup></span>
																			</li>

																			<li class="no-padding no-margin"><span
																				class="pad">使用面积:</span>
																				<div class="pad">60</div> <span class="pad">m<sup>2</sup></span>
																			</li>

																			<li class="no-padding no-margin"><span
																				class="pad">房屋属性:</span>
																				<div class="pad">简装</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">备注信息:</span>
																				<div class="pad"></div></li>
																		</ul>
																	</div>
																</div>
																<div
																	class="tabbable tabbable-custom tabbable-full-width">
																	<div class="tab-pane profile-classic row-fluid">
																		<ul class="unstyled span12 ">
																			<li class="no-padding no-margin"><span
																				class="pad">姓名:</span>
																				<div class="pad">赵雪儿</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">年龄:</span>
																				<div class="pad">34</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">电话:</span>
																				<div class="pad">15678609835</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">身份证号:</span>
																				<div class="pad">480988902989093452</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">学历:</span>
																				<div class="pad">大专</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">籍贯:</span>
																				<div class="pad">浙江杭州新都庄园广安苑1号4单1245</div></li>

																			<li class="no-padding no-margin"><span
																				class="pad">单位:</span>
																				<div class="pad">杭州易和网络技术有限公司</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">家庭住址:</span>
																				<div class="pad">新都庄园广安苑1号4单1245</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">紧急联系人:</span>
																				<div class="pad">王米</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">紧急联系人电话:</span>
																				<div class="pad">15690879043</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">备注信息:</span>
																				<div class="pad"></div></li>
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
											<div style="border-bottom: 1px solid rgb(245, 245, 245);"
												class="portlet-title">
												<div style="color: rgb(77, 144, 254);" class="caption">
													<i class="icon-edit"></i><span class="whiteFont">调房乙方</span>
												</div>
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
																<div
																	class="tabbable tabbable-custom tabbable-full-width">
																	<div class="tab-pane profile-classic row-fluid">
																		<ul class="unstyled span12 ">
																			<li class="no-padding no-margin"><span
																				class="pad">房屋类型:</span>
																				<div class="pad">租赁房</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">房屋地址:</span>
																				<div class="pad">
																					<strong>蒋村花园兴达苑3号6单909</strong>
																				</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">套内面积:</span>
																				<div class="pad">80</div> <span class="pad">m<sup>2</sup></span>
																			</li>
																			<li class="no-padding no-margin"><span
																				class="pad">使用面积:</span>
																				<div class="pad">60</div> <span class="pad">m<sup>2</sup></span>
																			</li>
																			<li class="no-padding no-margin"><span
																				class="pad">房屋属性:</span>
																				<div class="pad">简装</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">备注信息:</span>
																				<div class="pad"></div></li>
																		</ul>
																	</div>
																</div>
																<div
																	class="tabbable tabbable-custom tabbable-full-width">
																	<div class="tab-pane profile-classic row-fluid">
																		<ul class="unstyled span12 ">
																			<li class="no-padding no-margin"><span
																				class="pad">姓名:</span>
																				<div class="pad">王丽</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">年龄:</span>
																				<div class="pad">30</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">电话:</span>
																				<div class="pad">13567854692</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">身份证号:</span>
																				<div class="pad">467899875623546784</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">学历:</span>
																				<div class="pad">大专</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">籍贯:</span>
																				<div class="pad">浙江杭州东方城市花园红枫苑1号3单2309</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">单位:</span>
																				<div class="pad">杭州易和网络技术有限公司</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">家庭住址:</span>
																				<div class="pad">新都庄园广安苑1号4单1245</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">紧急联系人:</span>
																				<div class="pad">李明</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">紧急联系人电话:</span>
																				<div class="pad">13658936846</div></li>
																			<li class="no-padding no-margin"><span
																				class="pad">备注信息:</span>
																				<div class="pad"></div></li>
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
							<!-- tab3 end -->
						</div>
				</form>
			</div>
		</c:if>
		<c:if test="${houseHoldInfo == null}">
			数据获取错误!
		</c:if>
	</div>
</body>
</html>