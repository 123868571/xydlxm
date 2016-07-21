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
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzf/gzfHousePerson/form">小区<shiro:hasPermission name="gzf:gzfHousePerson:edit">${not empty gzfHousePerson.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gzf:gzfHousePerson:edit">查看</shiro:lacksPermission></a></li>
	</ul> --%><br/>
	
	<!-- BEGIN AUDIT INFORMATION -->
	<div id="auditInfo" class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">	
				<div class="portlet-title">
					<div class="auditTitle caption"><i class="icon-edit"></i><span class="whiteFont">审核信息</span></div>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>		
				</div>
				<div class="portlet-body row-fluid">
					<div class="span6">
						<form:form id="inputForm1" modelAttribute="gzfHousePerson" action="${ctx}/gzf/gzfHousePersonAudit/save" method="post" class="form-horizontal">
							<input id="isReview" name="isReview" type="hidden" value="2"/>
							<form:hidden path="id"/>
								<div class="tabbable tabbable-custom tabbable-full-width">
									<div class="tab-pane profile-classic row-fluid">
										<ul class="unstyled span12 ">
											<li class="no-padding no-margin">
												<span class="pad">审核日期:</span>
												<div class="pad">
													<%-- <c:if test="${gzfHousePerson.review eq '1'}">
														${gzfHousePerson.updateDate}
													</c:if> --%>
													<fmt:formatDate value="${gzfHousePerson.updateDate}" pattern="yyyy-MM-dd"/>
												</div>
											</li>
											<li class="no-padding no-margin">
												<span class="pad">审核状态:</span>
												<div class="pad">审核驳回</div>
											</li>
		
											<li class="no-padding no-margin">
												<span class="pad">审核人:</span>
												<div class="pad">${audit}</div>
											</li>
		
											<li class="no-padding no-margin">
												<span class="pad">备注信息:</span>
												<form:textarea path="auditRemarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
											</li>
											
											<li>
											<span class="pad padWidth"></span>
											<shiro:hasPermission name="gzf:gzfHouseInfo:edit">
											<button id="btnSubmit" class="btn blue margin-r-20 " type="submit"><i class="icon-ok"></i>保存</button></shiro:hasPermission>
											<input id="btnCancel" class="btn margin-r-20" type="button" value="返 回" onclick="history.go(-1)"/>
										</li>
											
										</ul>
									</div>
								</div>
							</form:form>
						</div>
					<div class="span6">
						<div class="item">
							<a class="fancybox-button" data-rel="fancybox-button" title="合同" href="media/image/house-ht.jpg">
								<div align="center" class="zoom">
									<img src="${ctxStatic}/media/image/house-ht2.jpg" width="210px" alt="Photo" />
									<div class="zoom-icon"></div>
								</div>
							</a>
							<div class="details">点击浏览合同</div>
						</div>
						<div align="center">
							<button class="btn green" id="printList" ><i class="icon-print"></i> <span class="list">打印单据</span></button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="span12">
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
		<div class="row-fluid">
				<div class="space12">
					<div class="margin-l-40">
						<%-- <shiro:hasPermission name="gzf:gzfHousePerson:edit">
							<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 确定</button></shiro:hasPermission> --%>
							<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- END AUDIT INFORMATION -->
	
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