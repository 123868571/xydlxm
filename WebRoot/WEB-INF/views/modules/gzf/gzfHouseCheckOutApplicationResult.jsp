<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物业管理</title>
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
	.form-horizontal .controls {margin-left:0}
	</style>
</head>
<body>


	<div id="transferManage">
		<form:form id="inputForm" modelAttribute="gzfHousePerson" action="${ctx}/gzf/gzfHouseCheckOutApplication/checkout" method="post" class="form-horizontal">
			<sys:message content="${message}"/>
			<div class="row-fluid">
				<div class="span12">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-edit"></i><span class="whiteFont">退房申请提示</span></div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN CONTNET-->
							<div class="tabbable tabbable-custom tabbable-full-width">
								<div class="tab-pane profile-classic row-fluid">
									<form:form id="inputForm" modelAttribute="gzfHousePerson" action="" method="post" class="form-horizontal">
										<form:hidden path="id"/>	
											<ul class="unstyled span6 ">							
												<div class="control-group">
													<label class="control-label">退房日期：</label>
													<div class="controls">
														<span class="pad"><fmt:formatDate value="${gzfHousePerson.checkoutDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">物业费：</label>
													<div class="controls">
														<span class="pad">${management}</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">房租费：</label>
													<div class="controls">
														<span class="pad">${houseRent}</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">押金：</label>
													<div class="controls">
														<span class="pad">${deposite}</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">水费：</label>
													<div class="controls">
														<span class="pad">${waterFee}</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">电费：</label>
													<div class="controls">
														<span class="pad">${electricityFee}</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">天燃气费：</label>
													<div class="controls">
														<span class="pad">${naturalgasFee}</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">房屋设备：</label>
													<div class="controls">
														<c:if test="${gzfHousePerson.device ==0}">
															<label class="">
																<span id="login-name">设备完好</span>
															</label>
														</c:if>
														<c:if test="${gzfHousePerson.device ==1}">
															<label class="">
																<span id="elec-sign">设备有损坏</span>
															</label>
														</c:if>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">描述：</label>
													<div class="controls">
														<span class="pad">${gzfHousePerson.deviceRemarks}</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">设备损坏扣费：</label>
													<div class="controls">
														<span class="pad">${gzfHousePerson.destroy}</span>
													</div>
												</div>
												<span class="pad padWidth"></span>
													<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
										</ul>
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
		<sys:message content="${message}"/>	
		<div class="row-fluid">
			<div class="span6">
				<div class="portlet">
					<div class="portlet-title bottom-line">
						<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-edit"></i>房屋信息</div>
					</div>
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
					</div>
				</div>
			</div>
			<div class="span6">
				<div class="portlet">
					<div class="portlet-title bottom-line">
						<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-list"></i>住户信息</div>
					</div>
					<div class="portlet-body">
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
	</div>
</body>
</html>