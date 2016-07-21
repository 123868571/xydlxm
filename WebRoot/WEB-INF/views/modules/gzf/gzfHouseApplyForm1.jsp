<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>人员申请</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		//有数据时的初始化操作start===
		var formContent = '${dto.bean.formContent}';
		var members = '${members}';
		if (formContent != "") {
			var obj = JSON.parse(formContent);
			$('input:radio[name=companyRadio][value=' + obj.haveCompany + ']').attr('checked', true);
			if (obj.otherSituation != "") {
				var otherSituations = obj.otherSituation.split(",");
				otherSituations.forEach(function(e, i) {
					$('#formContent\\.otherSituation' + e).attr('checked', 'checked');
				});
			}

			if (members != '') {
				memberObjs = JSON.parse(members);
				if (memberObjs.length > 0) {
					memberObjs.forEach(function(e, i) {
						if (e.memberRelation == 1) {
							return;
						}

						var tr = $('#otherMember_' + e.id);
						var s1 = tr.find('.memberMaritalStatus');
						s1.val(e.memberMaritalStatus);
						s1.trigger('change');
						var s2 = tr.find('.memberRelation');
						s2.val(e.memberRelation);
						s2.trigger('change');
					});
				}

			}
		}
		//==初始化操作end==
		var isUndefined = function(obj) {
			if (typeof obj === 'undefined') {
				return true;
			}
			return false;
		}
		//$('input:radio[name=companyRadio]')
		//确认
		$(".ok").click(function() {
			// 申请人
			var member = {};
			member.id = $('#memberId').val();
			member.serial = $('#serial').val();
			member.applyType = $('input:radio[name=bean\\.applyType]:checked').val();
			member.applyMajorName = $('#applyMajorName').val();
			member.applyMajorPhone = $('#applyMajorPhone').val();
			member.applyMajorIdcard = $('#applyMajorIdcard').val();
			member.applyMajorMaritalStatus = $('input:radio[name=bean\\.applyMajorMaritalStatus]:checked').val();
			member.applyMajorIncome = $('#applyMajorIncome').val();
			member.applyMajorPhotoFront = $('#photoFront').val();
			member.applyMajorPhotoBack = $('#photoBack').val();
			member.applyMajorAreaId = $('#areaId').val();
			member.applyMajorCommunity = $('#applyMajorCommunity').val();

			if (isUndefined(member.applyMajorMaritalStatus)) {
				member.applyMajorMaritalStatus = -1;
			}
			if (isUndefined(member.applyType)) {
				member.applyType = -1;
			}

			//申请表详情
			var formContent = {};
			formContent.selfHouseAddr = $('#selfHouseAddr').val();
			formContent.selfHouseOwner = $('#selfHouseOwner').val();
			formContent.rentHouseAddr = $('#rentHouseAddr').val();
			formContent.rentHouseOwner = $('#rentHouseOwner').val();
			formContent.sensusStatus = $('input:radio[name=formContent\\.sensusStatus]:checked').val();
			formContent.haveCompany = $('input:radio[name=companyRadio]:checked').val();
			formContent.noWorkingStatus = $('input:radio[name=formContent\\.noWorkingStatus]:checked').val();
			formContent.companyName = $('#companyName').val();
			formContent.workingStatus = $('input:radio[name=formContent\\.workingStatus]:checked').val();
			formContent.hardshipType = $('input:radio[name=formContent\\.hardshipType]:checked').val();
			formContent.hardshipCard = $('#hardshipCard').val();
			formContent.hardshipCardExpired = $('#hardshipCardExpired').val();

			var valArr = new Array;
			$("input:checkbox[name=formContent\\.otherSituation]:checked").each(function(i) {
				valArr[i] = $(this).val();
			});
			var vals = valArr.join(',');
			formContent.otherSituation = vals;

			if (isUndefined(formContent.hardshipType)) {
				formContent.hardshipType = -1;
			}
			if (isUndefined(formContent.haveCompany)) {
				formContent.haveCompany = -1;
			}
			if (isUndefined(formContent.noWorkingStatus)) {
				formContent.noWorkingStatus = -1;
			}
			if (isUndefined(formContent.sensusStatus)) {
				formContent.sensusStatus = -1;
			}
			if (isUndefined(formContent.workingStatus)) {
				formContent.workingStatus = -1;
			}

			var formContentJson = JSON.stringify(formContent);
			member.formContent = formContentJson;

			//家庭成员
			var members = new Array;
			$('.membertr').each(function() {
				var _this = $(this);
				var member = {};
				member.id = _this.data('id');
				member.memberName = _this.find('.memberName').val();
				if (member.memberName == '') {
					return;
				}

				member.memberIdcard = _this.find('.memberIdcard').val();
				member.memberPhone = _this.find('.memberPhone').val();
				member.memberMaritalStatus = _this.find('select.memberMaritalStatus').val();
				if (isUndefined(member.memberMaritalStatus)) {
					member.memberMaritalStatus = _this.find('input.memberMaritalStatus').val();
				}
				member.memberCompany = _this.find('.memberCompany').val();
				member.memberIncome = _this.find('.memberIncome').val();
				member.memberRelation = _this.find('select.memberRelation').val();
				if (isUndefined(member.memberRelation)) {
					member.memberRelation = _this.find('input.memberRelation').val();
				}

				members.push(member);
			});

			var membersJson = JSON.stringify(members);
			member.familyMembers = membersJson;

			member.formType = 1;
			$.ajax({
				url : "${ctx}/gzf/gzfHouseApplyForm/save",
				data : member,
				async : true
			}).done(function(data) {
				if ("success" == data) {
					alert("添加成功");
					location.reload([ true ]);
				} else if ("false" == data) {
					alert("添加失败");
				} else {
					alert(data);
					//location.reload([ true ]);
				}
			});
		});
	});
</script>
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
label.control-label{
	width:100px !important;
}
.form-horizontal .controls {
    margin-left: 120px !important;
}
</style>
</head>
<body>
	<sys:message content="${message}" />
	<div class="row-fluid">
		<div class="span12 no-padding no-margin">
			<form novalidate="novalidate" action="#" class="form-horizontal"
				id="submit_form">
				<div class="form-wizard">
					<div class="navbar steps hide">
						<div class="navbar-inner">
							<ul class="row-fluid nav nav-pills">
								<li class="span3 active"><a href="#tab1" data-toggle="tab"
									class="step active"> <span class="number">1</span> <span
										class="desc"><i class="icon-ok"></i>公租房申请表Ⅰ</span>
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
																<th colspan="7"><h3>公 共 租 赁 住 房 申 请 表Ⅰ</h3> <br />编号:
																	${serial} <br />（城镇中等偏低以下收入住房困难家庭申请使用）</th>
															</thead>
															<tbody>
																<tr>
																	<td width="15%">申请人信息</td>
																	<td width="10%">姓名</td>
																	<td width="15%"><input type="text"
																		id="applyMajorName" value="${dto.bean.applyMajorName}"><input
																		type="hidden" value="${dto.bean.id}" id="memberId">
																		<input type="hidden" value="${serial}" id="serial"></td>
																	<td width="15%">身份证号码</td>
																	<td width="15%"><input type="text"
																		id="applyMajorIdcard" maxlength="18"
																		value="${dto.bean.applyMajorIdcard}"></td>
																	<td width="15%">联系电话</td>
																	<td width="15%"><input type="text"
																		id="applyMajorPhone" maxlangth="11"
																		value="${dto.bean.applyMajorPhone}"></td>
																</tr>
																<tr>
																	<td></td>
																	<td>婚姻状况</td>
																	<td colspan="3"><form:radiobuttons
																			path="dto.bean.applyMajorMaritalStatus"
																			items="${fns:getDictList('marital_status')}"
																			itemLabel="label" itemValue="value"
																			htmlEscape="false" class="required" /></td>
																	<td>年收入(元)</td>
																	<td><input type="text" id="applyMajorIncome"
																		value="${dto.bean.applyMajorIncome}"></td>
																</tr>
																<tr>
																	<td></td>
																	<td>居住情况</td>
																	<td colspan="3">
																		<table width="100%">
																			<tr>
																				<td width="20%">自有房屋地址:</td>
																				<td width="80%"><input type="text"
																					style="width: 356px" id="selfHouseAddr"
																					value="${dto.formContent.selfHouseAddr}"></td>
																			</tr>
																		</table>

																	</td>
																	<td>房屋产权人</td>
																	<td><input type="text" id="selfHouseOwner"
																		value="${dto.formContent.selfHouseOwner}"></td>
																</tr>
																<tr>
																	<td></td>
																	<td></td>
																	<td colspan="3">
																		<table width="100%">
																			<tr>
																				<td width="20%">借住房屋地址:</td>
																				<td width="80%"><input type="text"
																					id="rentHouseAddr" style="width: 356px"
																					value="${dto.formContent.rentHouseAddr}"></td>
																			</tr>
																		</table>

																	</td>
																	<td>房屋所有人</td>
																	<td><input type="text" id="rentHouseOwner"
																		value="${dto.formContent.rentHouseOwner}"></td>
																</tr>
																<tr>
																	<td></td>
																	<td>户籍性质</td>
																	<td colspan="5" style="text-align: center"><form:radiobuttons
																			path="dto.formContent.sensusStatus"
																			items="${fns:getDictList('apply_form_sensus_status')}"
																			itemLabel="label" itemValue="value"
																			htmlEscape="false" class="required" /></td>
																</tr>
																<tr>
																	<td></td>
																	<td>工作单位</td>
																	<td><input type="radio" name="companyRadio"
																		value="1">无</input></td>
																	<td>职业状况<br />（选一项）
																	</td>
																	<td colspan="3" style="text-align: center"><form:radiobuttons
																			path="dto.formContent.noWorkingStatus"
																			items="${fns:getDictList('apply_form_nowork_status')}"
																			itemLabel="label" itemValue="value"
																			htmlEscape="false" class="required" /></td>
																	</td>
																</tr>
																<tr>
																	<td></td>
																	<td></td>
																	<td><input type="radio" name="companyRadio"
																		value="2">有</input></td>
																	<td>单位全称</td>
																	<td colspan="2"><input type="text"
																		id="companyName"
																		value="${dto.formContent.companyName}" /></td>
																	<td style="text-align: center"><form:radiobuttons
																			path="dto.formContent.workingStatus"
																			items="${fns:getDictList('apply_form_working_status')}"
																			itemLabel="label" itemValue="value"
																			htmlEscape="false" class="required" /></td>
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
																				<c:set var="haveMemberMate" value="0"></c:set>
																				<c:forEach items="${dto.familyMembers}" var="member">
																					<c:if test="${member.memberRelation==1}">
																						<tr id="memberMate_${member.id}" class="membertr"
																							data-id="${member.id}">
																							<td class="bi-right"><input type="text"
																								class="w80 memberName"
																								value="${member.memberName }"><input
																								type="hidden" class="memberRelation" value="1">
																								<input type="hidden" class="memberMaritalStatus"
																								value="1"></td>
																							<td class="bi-right"><input type="text"
																								class="w80 memberPhone"
																								value="${member.memberPhone }"></td>
																							<td class="bi-right"><input type="text"
																								class="w80 memberIdcard" maxlangth="18"
																								value="${member.memberIdcard }"></td>
																							<td class="bi-right"><input type="text"
																								class="w80 memberCompany"
																								value="${member.memberCompany }"></td>
																							<td><input type="text"
																								class="w80 memberIncome"
																								value="${member.memberIncome }"></td>
																						</tr>
																						<c:set var="haveMemberMate" value="1"></c:set>
																					</c:if>
																				</c:forEach>
																				<c:if test="${haveMemberMate!=1}">
																					<tr class="membertr" data-id="">
																						<td class="bi-right"><input type="text"
																							class="w80 memberName"><input
																							type="hidden" class="memberRelation" value="1">
																							<input type="hidden" class="memberMaritalStatus"
																							value="1"></td>
																						<td class="bi-right"><input type="text"
																							class="w80 memberPhone"></td>
																						<td class="bi-right"><input type="text"
																							maxlangth="18" class="w80 memberIdcard"></td>
																						<td class="bi-right"><input type="text"
																							class="w80 memberCompany"></td>
																						<td><input type="text"
																							class="w80 memberIncome"></td>
																					</tr>
																				</c:if>
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
																				<c:set var="emptyMembersSize" value="2"></c:set>
																				<c:forEach items="${dto.familyMembers}" var="member">
																					<c:if test="${member.memberRelation != 1}">
																						<tr id="otherMember_${member.id}" class="membertr"
																							data-id="${member.id}">
																							<td class="bi-right bi-bottom"><input
																								type="text" class="w80 memberName"
																								value="${member.memberName}"> <input
																								type="hidden" class="memberPhone"></td>
																							<td class="bi-right bi-bottom"><select
																								class="memberRelation">
																									<option value="-1">全部</option>
																									<c:forEach
																										items="${fns:getDictList('family_member_relation')}"
																										var="relation">
																										<option value="${relation.value }">${relation.label }</option>
																									</c:forEach>
																							</select></td>
																							<td class="bi-right bi-bottom"><select
																								class="memberMaritalStatus">
																									<option value="-1">全部</option>
																									<c:forEach
																										items="${fns:getDictList('marital_status')}"
																										var="relation">
																										<option value="${relation.value }">${relation.label }</option>
																									</c:forEach>
																							</select></td>
																							<td class="bi-right bi-bottom"><input
																								type="text" class="w80 memberIdcard"
																								maxlangth="18" value="${member.memberIdcard}"></td>
																							<td class="bi-right bi-bottom"><input
																								type="text" class="w80 memberCompany"
																								value="${member.memberCompany}"></td>
																							<td class="bi-bottom"><input type="text"
																								class="w80 memberIncome"
																								value="${member.memberIncome}"></td>
																						</tr>
																						<c:set var="emptyMembersSize"
																							value="${emptyMembersSize-1}"></c:set>
																					</c:if>
																				</c:forEach>
																				<c:forEach begin="1" end="${emptyMembersSize}"
																					step="1">
																					<tr class="membertr" data-id="">
																						<td class="bi-right bi-bottom"><input
																							type="text" class="w80 memberName"><input
																							type="hidden" class="memberPhone"></td>
																						<td class="bi-right bi-bottom"><select
																							class="memberRelation">
																								<option value="-1">全部</option>
																								<c:forEach
																									items="${fns:getDictList('family_member_relation')}"
																									var="relation">
																									<option value="${relation.value }">${relation.label }</option>
																								</c:forEach>
																						</select></td>
																						<td class="bi-right bi-bottom"><select
																							class="memberMaritalStatus">
																								<option value="-1">全部</option>
																								<c:forEach
																									items="${fns:getDictList('marital_status')}"
																									var="relation">
																									<option value="${relation.value }">${relation.label }</option>
																								</c:forEach>
																						</select></td>
																						<td class="bi-right bi-bottom"><input
																							maxlangth="18" type="text"
																							class="w80 memberIdcard"></td>
																						<td class="bi-right bi-bottom"><input
																							type="text" class="w80 memberCompany"></td>
																						<td class="bi-bottom"><input type="text"
																							class="w80 memberIncome"></td>
																					</tr>
																				</c:forEach>
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
																					<td width="20%" class="bi-right bi-bottom"><form:radiobuttons
																							path="dto.formContent.hardshipType"
																							items="${fns:getDictList('apply_form_hardship_type')}"
																							itemLabel="label" itemValue="value"
																							htmlEscape="false" class="required" /></td>
																					<td width="10%" class="bi-right bi-bottom">证号</td>
																					<td width="20%" class="bi-right bi-bottom"><input
																						type="text" id="hardshipCard"
																						value="${dto.formContent.hardshipCard}" /></td>
																					<td width="10%" class="bi-right bi-bottom">有效截止日期</td>
																					<td width="20%" class="bi-bottom"><input
																						id="hardshipCardExpired" type="text"
																						readonly="readonly" maxlength="20"
																						value="${dto.formContent.hardshipCardExpired}"
																						class="input-xlarge Wdate required"
																						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /></td>
																				</tr>
																				<tr>
																					<td class="bi-right">其他特殊情况</td>
																					<td colspan="5"><form:checkboxes
																							path="dto.formContent.otherSituation"
																							items="${fns:getDictList('apply_form_hardship_othersituation')}"
																							itemLabel="label" itemValue="value" /></td>
																				</tr>
																			</tbody>
																		</table>
																	</td>
																</tr>
															</tbody>
														</table>
													</div>
													<div>
														<ul>
															<li>申请属性: <form:radiobuttons
																	path="dto.bean.applyType"
																	items="${fns:getDictList('apply_form_apply_type')}"
																	itemLabel="label" itemValue="value" htmlEscape="false"
																	class="required" /></li>
															<li>所属: 地区<sys:treeselect id="area"
																	name="area.id" value="${dto.applyMajorArea.id}"
																	labelName="area.name"
																	labelValue="${dto.applyMajorArea.name}" title="区域"
																	url="/sys/area/treeData" cssClass="required" /> <input
																type="text" id="applyMajorCommunity"
																value="${dto.bean.applyMajorCommunity}" placeholder="街道/社区"/>
															</li>
															<li>
																<div class="control-group">
																	<table>
																		<tr>
																			<td><label class="control-label">身份证正面:</label>
																				<div class="controls">
																					<form:hidden id="photoFront"
																						path="dto.bean.applyMajorPhotoFront"
																						htmlEscape="false" maxlength="255"
																						class="input-xlarge" />
																					<sys:ckfinder input="photoFront" type="images"
																						uploadPath="/photo" selectMultiple="false"
																						maxWidth="100" maxHeight="100" />
																				</div></td>
																			<td><label class="control-label">身份证背面:</label>
																				<div class="controls">
																					<form:hidden id="photoBack"
																						path="dto.bean.applyMajorPhotoBack"
																						htmlEscape="false" maxlength="255"
																						class="input-xlarge" />
																					<sys:ckfinder input="photoBack" type="images"
																						uploadPath="/photo" selectMultiple="false"
																						maxWidth="100" maxHeight="100" />
																				</div></td>
																		</tr>
																	</table>
																</div>
															</li>
														</ul>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-actions clearfix">
								<a href="javascript:;" class="btn blue button-next ok"> 确认 <i
									class="m-icon-swapright m-icon-white"></i>
								</a>
							</div>
						</div>
						<!-- tab1 end -->
					</div>
			</form>
		</div>
	</div>
</body>
</html>