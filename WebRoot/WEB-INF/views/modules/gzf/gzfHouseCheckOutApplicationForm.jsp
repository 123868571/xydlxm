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
							<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-edit"></i><span class="whiteFont">退房申请</span></div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN CONTNET-->
							<div class="tabbable tabbable-custom tabbable-full-width">
								<div class="tab-pane profile-classic row-fluid">
									<form:form id="inputForm" modelAttribute="gzfHousePerson" action="" method="post" class="form-horizontal">
										<ul class="unstyled span6 ">
										<form:hidden path="id"/>								
											<li>
												<span class="pad padWidth pull-left">退房日期：</span>
												<div class="controls">
														<span class="pad"><fmt:formatDate value="${sysdate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
												</div>
											</li>
											<li>
												<span class="pad padWidth pull-left">缴费标准：</span>
												<div class="controls">
														<span class="pad">${gzfHousePerson.gzfHouseholdInfo.gzfPaymentStandard.name}</span>
												</div>
											</li>
											<li>
												<span class="pad padWidth pull-left">房租费：</span>
												<div class="controls">
													<span class="pad padFont">${houseRentFee}&nbsp;&nbsp;元</span>
												</div>
											</li>
											<li>
												<span class="pad padWidth pull-left">物业费：</span>
												<div class="controls">
													<span class="pad padFont">${manageFee}&nbsp;&nbsp;元</span>
												</div>
											</li>
											<li>
												<span class="pad padWidth pull-left">能耗费：</span>
												<div class="controls">
													<span class="pad padFont">${consumFee}&nbsp;&nbsp;元</span>
												</div>
											</li>
											<li>
												<span class="pad padWidth pull-left">预存费：</span>
												<div class="controls">
													<span class="pad padFont">${freeFee}&nbsp;&nbsp;元</span>
												</div>
											</li>
											<li>
												<span class="pad padWidth">水表数：</span>
												<form:input path="water" placeholder="" class="m-wrap small" style="margin: 0px;font-size:20px;"/>
												<span class="pad">吨</span>
											</li>
											<li>
												<span class="pad padWidth">电表数：</span>
												<form:input path="elec" placeholder="" class="m-wrap small" style="margin: 0px;font-size:20px;"/>
												<span class="pad">度</span>
											</li>
											<li>
												<span class="pad padWidth">天然气表数：</span>
												<form:input path="gas" placeholder="" class="m-wrap small" style="margin: 0px;font-size:20px;"/>
												<span class="pad">m<sup>3</sup></span>
											</li>
											<li>
												<span class="pad padWidth pull-left">房屋设备：</span>
												<div class="controls">
													<label class="">
														<input type="radio" name="device" value="0" checked/>
														<span id="login-name">设备完好</span>
													</label>
													<label class="">
														<input type="radio" name="device" value="1" />
														<span id="elec-sign">设备有损坏</span>
													</label>
												</div>
												<div class="controls">
													<form:textarea path="deviceRemarks" placeholder="请输入设备情况的相关信息描述" class="large m-wrap  margin-top-10" rows="4"/>
												</div>
											</li>
											<li>
												<span class="pad padWidth">设备损坏扣费：</span>
												<form:input path="destroy" class="m-wrap small" style="margin: 0px;font-size:20px;"/>
												<span class="pad">元</span>
											</li>
											<li>
												<span class="pad padWidth pull-left">申请人：</span>
												<div class="controls pull-left">
													<label class="radio line">
														<input type="radio" name="optionsRadios2" value="option1" />
														<form:input path="apply" placeholder="请输入申请人姓名 " class="m-wrap small "/>
														<span class="help-inline "></span>
													</label>
													<label class="radio line">
														<input type="radio" name="optionsRadios2" value="option1" />
														
													</label>
												</div>
											</li>
											<div class="clearfix"></div>
											<li>
											<span class="pad padWidth"></span>
											<shiro:hasPermission name="gzf:gzfHouseInfo:edit">
											<button id="btnSubmit" class="btn blue margin-r-20 " type="submit"><i class="icon-ok"></i>保存</button></shiro:hasPermission> 
												<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
										</li>
										</ul>
									</form:form>
									<div class="span6">
										<div class="item">
											<a class="fancybox-button" data-rel="fancybox-button" title="合同" href="media/image/house-ht.jpg">
												<div align="center" class="zoom">
													<img src="${ctxStatic}/media/image/house-ht2.jpg" width="231px" alt="Photo" />
													<div class="zoom-icon"></div>
												</div>
											</a>
											<div class="details">点击浏览合同</div>
										</div>
										<div align="center">
											<button onclick="" type="submit" class="btn green"><i class="icon-print"></i> 打印申请表</button>
										</div>
									</div>
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

	<%-- <form:form id="inputForm" modelAttribute="gzfHousePerson" action="" method="post" class="form-horizontal">
			
			<div class="control-group">
			<label class="control-label">退房日期：</label>
			<div class="controls">
				<form:input path="gzfHousePerson。" htmlEscape="false" maxlength="11" class="input-xlarge required digits" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">物业费：</label>
			<div class="controls">
				<form:input path="gzfHouseholdInfo.gzfPaymentStandard.managementFee" htmlEscape="false" maxlength="11" class="input-xlarge required digits" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">能耗费：</label>
			<div class="controls">
				<form:input path="gzfHouseholdInfo.gzfPaymentStandard.consumption" htmlEscape="false" maxlength="11" class="input-xlarge required digits" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
			
			
			<div class="form-actions">
			<shiro:hasPermission name="gzf:gzfHouseInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
		
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">更新时间：</label>
			<div class="controls">
			<fmt:formatDate value="${gzfHousePerson.gzfHouseInfo.updateDate}" pattern="yyyy-MM-dd"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">房租类型：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseInfo.houseType}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房租地址：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseInfo.gzfPalaces.gzfVillage.name}
			${gzfHousePerson.gzfHouseInfo.gzfPalaces.name}
			${gzfHousePerson.gzfHouseInfo.buildNum}号
			${gzfHousePerson.gzfHouseInfo.unit}单元
			${gzfHousePerson.gzfHouseInfo.room}房间
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">套内面积：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseInfo.innerArea}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用面积：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseInfo.useArea}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房间属性：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseInfo.gzfHouseProperty.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseInfo.remarks}
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.sex}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年龄：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.age}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.phone}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.cardid}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学历：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.education}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作单位：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.company}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">家庭住址：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.province}
			${gzfHousePerson.gzfHouseholdInfo.city}
			${gzfHousePerson.gzfHouseholdInfo.area}
			${gzfHousePerson.gzfHouseholdInfo.address}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">籍贯：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.nativeProvince}
			${gzfHousePerson.gzfHouseholdInfo.nativeCity}
			${gzfHousePerson.gzfHouseholdInfo.nativeArea}
			${gzfHousePerson.gzfHouseholdInfo.nativeAddress}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">紧急联系人：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.emergencyContact}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">紧急联系电话：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.emergencyPhone}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.remarks}
			</div>
		</div> --%>
		
	
</body>
</html>