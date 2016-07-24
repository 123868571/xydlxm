<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>申请结果</title>
<meta name="decorator" content="defaultV3" />
<script type="text/javascript">
	$(document).ready(function() {

		//$("#name").focus();
		$("#inputForm").validate({
			submitHandler : function(form) {
				loading('正在提交，请稍等...');
				form.submit();
			},
			errorContainer : "#messageBox",
			errorPlacement : function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});

	});
</script>
<style type="text/css">
a:link {
	color: #000;
	text-decoration: none;
}

a:visited {
	color: #000
}

a:hover {
	color: #000;
	text-decoration: underline;
}

a:active {
	color: #000
}

.nav-tabs>li,i {
	font-size:14px !important;
}
.blue{
	color: rgb(77, 144, 254);
}
</style>
</head>
<body>


	<ul class="nav nav-tabs">
		<li class="active"><i class="blue">申请人：</i>${dto.bean.applyMajorName} |
			<i class="blue">身份证：</i>${dto.bean.applyMajorIdcard} | <i class="blue">联系电话：</i>${dto.bean.applyMajorPhone} |
			<i class="blue">申请类型：</i>${fns:getDictLabel(dto.bean.formType, 'apply_form_type', '未知')}
			</td>
		</li>
	</ul>
	<br />

	<div id="houseApplyFormInformation" class="row-fluid">
		<div class="space12">
			<c:forEach items="${fns:getDictList(dto.dictCheckName)}" var="apc"
				varStatus="status">
				<c:if test="${status.index%2==0 }">
					<div class="span6" style="margin-left: 0">
				</c:if>
				<c:if test="${status.index%2!=0 }">
					<div class="span6">
				</c:if>
				<div class="portlet">
					<div class="portlet-title"
						style="border-bottom: #f5f5f5 1px solid;">
						<div class="caption" style="color: rgb(77, 144, 254);">
							<i class="icon-list"></i>${apc.label }审核情况
						</div>
					</div>
					<div class="portlet-body">
						<div class="tabbable tabbable-custom tabbable-full-width">
							<div class="tab-pane profile-classic row-fluid">
								<ul class="unstyled span12 ">
									<c:set var="checkstatus"
										value="${dto.checkStatusMap[apc.value] }"></c:set>
									<c:set var="checkverify"
										value="${dto.checkVerifyMap[apc.value] }"></c:set>
									<li class="no-padding no-margin"><span class="pad">审核状态:</span>
										<div class="pad">
											<c:if test="${checkstatus==0}">未审核</c:if>
											<c:if test="${checkstatus==1}">已审核</c:if>
										</div></li>
									<li class="no-padding no-margin"><span class="pad">审核结果:</span>
										<div class="pad">
											<c:if test="${checkstatus==1}">
												<c:if test="${checkverify==1}">
													已同意
												</c:if>
												<c:if test="${checkverify==0}">
													已拒绝
												</c:if>
											</c:if>
										</div></li>
									<li class="no-padding no-margin"><span class="pad">审核时间:</span>
										<div class="pad">
											${dto.checkContentMap[apc.value].updateTime }</div></li>
									<li class="no-padding no-margin"><span class="pad">审核原因:</span>
										<div class="pad">${dto.checkContentMap[apc.value].result }</div></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
		</div>
		</c:forEach>

		<div class="row-fluid">
			<div class="space12">
				<div class="margin-l-40">
					<input id="btnCancel" class="btn" type="button" value="返 回"
						onclick="history.go(-1)" />
				</div>
			</div>
		</div>
	</div>
	</div>

	<%-- <form:form id="inputForm"  modelAttribute="gzfHousePerson" action="${ctx}/gzf/gzfHouseholdInfo/" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
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
		
		<div class="form-actions">
			<shiro:hasPermission name="gzf:gzfHousePerson:edit">
			<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" ><i class="icon-ok"></i> 确定</button></shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form> --%>

	<script>
		$(document).ready(function() {
			$("#new_btn1").click(function() {
				$("#old_yuan").hide();
				$("#new_input").show();
			});
		});
	</script>
	<script>
		$(document).ready(function() {
			$("#new_btn2").click(function() {
				$("#old_lou").hide();
				$("#new_input2").show();
			});
		});
	</script>
</body>
</html>