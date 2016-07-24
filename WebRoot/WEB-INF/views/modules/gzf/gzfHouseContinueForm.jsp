<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>签到协议</title>
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
	<form:form id="inputForm"  modelAttribute="gzfHousePerson" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div id="transferManage">
			<div class="row-fluid">
				<div class="span12">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="icon-edit"></i><span class="whiteFont">续租管理</span></div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN CONTNET-->
							<div class="tabbable tabbable-custom tabbable-full-width">
								<div class="tab-pane profile-classic row-fluid">
									<ul id="leaseInfo" class="unstyled span6 ">
										<li>
											<span class="pad padWidth">房租：</span>
											<div class="pad padFont">${gzfHousePerson.gzfHouseholdInfo.gzfPaymentStandard.rentUnitPrice}</div>
											<span class="pad">元/月</span>
										</li>
										<li>
											<span class="pad padWidth">押金：</span>
											<div class="pad padFont">${gzfHousePerson.gzfHouseholdInfo.gzfPaymentStandard.deposit}</div>
											<span class="pad">元</span>
										</li>
										<li>
											<span class="pad padWidth">房卡张数：</span>
											<div class="pad padFont">${gzfHousePerson.gzfHouseholdInfo.cardnum}</div>
											<span class="pad">张</span>
										</li>
										<form:form id="inputForm"  modelAttribute="gzfHousePerson" action="${ctx}/gzf/gzfHousePerson/save" method="post" class="form-horizontal">
											<input type="hidden" name="id" value="${gzfHousePerson.id}">
											<li>
												<span class="pad padWidth pull-left">合同日期：</span>
												<div class="control-group" style="margin: 0px;">
													<div class="controls">
														<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
															<input style="width:100px;" name="startDate" type="text" readonly="readonly" maxlength="20"  class="m-wrap m-ctrl-medium date-picker" size="16" type="text" 
															value="<fmt:formatDate value="${gzfHousePerson.startDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/><span class="add-on"><i class="icon-calendar"></i></span>
														</div>-
														<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
															<input style="width:100px;" name="endDate" type="text" readonly="readonly" maxlength="20"  class="m-wrap m-ctrl-medium date-picker" size="16" type="text" 
															value="<fmt:formatDate value="${gzfHousePerson.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/><span class="add-on"><i class="icon-calendar"></i></span>
														</div>
													</div>
												</div>
											</li>
											<li>
												<span class="pad padWidth"></span>
												<button id="btnSubmit" class="btn blue margin-r-20 "><i class="icon-ok"></i>修改</button>
												<button type="button" class="btn">取消</button>
											</li>
										</form:form>
									</ul>
									<!-- 点击浏览合同 -->
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
											<button onclick="" type="submit" class="btn green"><i class="icon-print"></i> 打印续租协议</button>
										</div>
									</div>
								</div>
							</div>
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<!-- 房屋信息 -->
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
											<div class="pad">
											${fns:getDictLabel(gzfHousePerson.gzfHouseInfo.houseType, 'house_type', '')}
											</div>
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
				<!-- 住户信息 -->
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
											<div class="pad">
											${fns:getDictLabel(gzfHousePerson.gzfHouseholdInfo.sex, 'sex', '')}
											</div>
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
											<div class="pad">
											${fns:getDictLabel(gzfHousePerson.gzfHouseholdInfo.education, 'education', '')}
											</div>
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
	</form:form>
	
 	<%-- <form:form id="inputForm"  modelAttribute="gzfHousePerson" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		
		<div class="control-group">
			<label class="control-label">房租：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.gzfPaymentStandard.rentUnitPrice}
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">押金：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.gzfPaymentStandard.deposit}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房卡个数：</label>
			<div class="controls">
			${gzfHousePerson.gzfHouseholdInfo.cardnum}
			</div>
		</div>
	 	<div class="control-group">
			<label class="control-label">合同日期：</label>
			<div class="controls">
			<fmt:formatDate value="${gzfHousePerson.startDate}" pattern="yyyy-MM-dd"/>-
			<fmt:formatDate value="${gzfHousePerson.endDate}" pattern="yyyy-MM-dd"/>
			</div>
		</div>
		
		
		
		
		
		
		 <form:form id="inputForm"  modelAttribute="gzfHousePerson" action="${ctx}/gzf/gzfHousePerson/save" method="post" class="form-horizontal">

			<input type="hidden" name="id" value="${gzfHousePerson.id}">
			<div class="control-group">
				<label class="control-label">合同日期：</label>
				<div class="controls">
					<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
						value="<fmt:formatDate value="${gzfHousePerson.startDate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					-<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
						value="<fmt:formatDate value="${gzfHousePerson.endDate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</div> 
				<div class="controls">
				<button id="btnSubmit" type="submit" class="btn blue">修改</button>
				</div>
			</div>
		</form:form>
		
		
		<div class="control-group">
			<label class="control-label">房租生效日期：</label>
			<div class="controls">
			<fmt:formatDate value="${gzfHousePerson.effectiveDate}" pattern="yyyy-MM-dd"/>
			</div>
		</div>
		
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