<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>人员信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/jsAddresss/jsAddress.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
				$('#IDcard').blur(function() {
					$('#age').val(IdCard($('#IDcard').val(),3));
				})
				function IdCard(UUserCard, num) {
					if (num == 1) {
						//获取出生日期
						birth = UUserCard.substring(6, 10) + "-"
								+ UUserCard.substring(10, 12) + "-"
								+ UUserCard.substring(12, 14);
						return birth;
					}
					if (num == 2) {
						//获取性别
						if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) {
							//男
							return "男";
						} else {
							//女
							return "女";
						}
					}
					if (num == 3) {
						//获取年龄
						var myDate = new Date();
						var month = myDate.getMonth() + 1;
						var day = myDate.getDate();
						var age = myDate.getFullYear()
								- UUserCard.substring(6, 10) - 1;
						if (UUserCard.substring(10, 12) < month
								|| UUserCard.substring(10, 12) == month
								&& UUserCard.substring(12, 14) <= day) {
							age++;
						}
						return age;
					}
				}
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
</style>
<script type="text/javascript"
	src="${ctxStatic}/myMedia/js/formValidation.js"></script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/gzf/gzfHouseholdInfo/form?id=${gzfHouseholdInfo.id}">人员信息<shiro:hasPermission
					name="gzf:gzfHouseholdInfo:edit">${not empty gzfHouseholdInfo.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="gzf:gzfHouseholdInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="gzfHouseholdInfo"
		action="${ctx}/gzf/gzfHouseholdInfo/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:radiobuttons path="sex" items="${fns:getDictList('sex')}"
					itemLabel="label" itemValue="value" htmlEscape="false"
					class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作单位：</label>
			<div class="controls">
				<form:input path="company" htmlEscape="false" maxlength="225"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位所属行业：</label>
			<div class="controls">
				<form:select path="industry" class="input-xlarge required"
					style="width:285px;">
					<form:options items="${fns:getDictList('industry')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参加工作时间：</label>
			<div class="controls">
				<input name="workTime" type="text" readonly="readonly"
					maxlength="20" class="input-xlarge Wdate required"
					value="<fmt:formatDate value="${gzfHouseholdInfo.workTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div style="visibility: hidden">
			<label class="control-label">国籍：</label>
			<div class="controls">
				<select id="coutry1" name="coutry1" class="input-xlarge required"
					style="width: 285px;"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">省：</label>
			<div class="controls">
				<select id="province" name="province" class="input-xlarge required"
					style="width: 285px;"></select> <span class="help-inline"><font
					color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市：</label>
			<div class="controls">
				<select id="city" name="city" class="input-xlarge required"
					style="width: 285px;"></select> <span class="help-inline"><font
					color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区：</label>
			<div class="controls">
				<select id="area" name="area" class="input-xlarge required"
					style="width: 285px;"></select> <span class="help-inline"><font
					color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详细地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="255"
					class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<script type="text/javascript">
			addressInit('coutry1', 'province', 'city', 'area');
		</script>

		<div class="control-group">
			<label class="control-label">学历：</label>
			<div class="controls">
				<form:select path="education" class="input-xlarge required"
					style="width:285px;">
					<form:options items="${fns:getDictList('education')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作性质：</label>
			<div class="controls">
				<form:select path="jobCategory" class="input-xlarge required"
					style="width:285px;">
					<form:options items="${fns:getDictList('job_category')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">户口属性：</label>
			<div class="controls">
				<form:select path="household" class="input-xlarge required"
					style="width:285px;">
					<form:options items="${fns:getDictList('household')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收入范围：</label>
			<div class="controls">
				<form:select path="incomeRange" class="input-xlarge required"
					style="width:285px;">
					<form:options items="${fns:getDictList('income_range')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="32"
					class="input-xlarge  required phone " name="phoneNumberChina" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号：</label>
			<div class="controls">
				<form:input path="cardid" htmlEscape="false" maxlength="64"
					class="input-xlarge required card " name="IDcard" id="IDcard"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年龄：</label>
			<div class="controls">
				<form:input path="age" htmlEscape="false" maxlength="11" id="age"
					class="input-xlarge" disabled="disabled" />
			</div>
		</div>
		<div style="visibility: hidden">
			<label class="control-label">国籍：</label>
			<div class="controls">
				<select id="coutry" name="coutry" class="input-xlarge required"
					style="width: 285px;"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">籍贯省：</label>
			<div class="controls">
				<select id="nativeProvince" name="nativeProvince"
					class="input-xlarge required" style="width: 285px;"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">籍贯市：</label>
			<div class="controls">
				<select id="nativeCity" name="nativeCity"
					class="input-xlarge required" style="width: 285px;"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">籍贯区：</label>
			<div class="controls">
				<select id="nativeArea" name="nativeArea"
					class="input-xlarge required" style="width: 285px;"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">籍贯地址：</label>
			<div class="controls">
				<form:input path="nativeAddress" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
			</div>
		</div>

		<script type="text/javascript">
			addressInit('coutry', 'nativeProvince', 'nativeCity', 'nativeArea');
		</script>
		<div class="control-group">
			<label class="control-label">紧急联系人：</label>
			<div class="controls">
				<form:input path="emergencyContact" htmlEscape="false"
					maxlength="64" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">紧急联系电话：</label>
			<div class="controls">
				<form:input path="emergencyPhone" htmlEscape="false" maxlength="64"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">房卡个数：</label>
			<div class="controls">
				<form:input path="cardnum" htmlEscape="false" maxlength="11"
					class="input-xlarge required digits" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">婚姻状况：</label>
			<div class="controls">
				<form:radiobuttons path="maritalStatus"
					items="${fns:getDictList('marital_status')}" itemLabel="label"
					itemValue="value" htmlEscape="false" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">政治面貌：</label>
			<div class="controls">
				<form:select path="politicalStatus" class="input-xlarge required"
					style="width:285px;">
					<form:options items="${fns:getDictList('political_status')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" style="display: none">
			<label class="control-label">人员特性：</label>
			<div class="controls">
				<form:select path="personal" class="input-xlarge required"
					style="width:285px;">
					<form:options items="${fns:getDictList('apply_people_property')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缴费标准：</label>
			<div class="controls">
				<form:select path="gzfPaymentStandardId" class="input-xlarge"
					style="width:285px;">
					<form:options items="${gzfPaymentStandardList}" itemLabel="name"
						itemValue="id" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="gzf:gzfHouseholdInfo:edit">
				<button id="btnSubmit" class="btn blue margin-r-20 " type="submit">
					<i class="icon-ok"></i> 保存
				</button>
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#inputForm').formValidation({
				message : 'This value is not valid',
				icon : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {

					phoneNumberChina : {
						validators : {
							stringLength : {
								min : 11,
								max : 11,
								message : '手机号码为11位'
							},
							regexp : {
								regexp : /^[0-9]+$/,
								message : '只能是数字'
							}
						}
					},
					IDcard : {
						validators : {
							stringLength : {
								min : 18,
								max : 18,
								message : '身份证号码为18位'
							},
							regexp : {
								regexp : /^[0-9xX]+$/,
								message : '只能是数字和字母'
							}
						}
					},

				}
			});
		});
	</script>

</body>
</html>