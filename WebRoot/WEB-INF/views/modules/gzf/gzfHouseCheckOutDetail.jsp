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
									<li class="no-padding no-margin">
										<span class="pad">水费单据号:</span>
										<div class="pad">${gzfHousePerson.waterOrder}</div>
									</li>
									<li class="no-padding no-margin">
										<span class="pad">电费单据号:</span>
										<div class="pad">${gzfHousePerson.elecOrder}</div>
									</li>
									<li class="no-padding no-margin">
										<span class="pad">天然气费单据号:</span>
										<div class="pad">${gzfHousePerson.gasOrder}</div>
									</li>
									
									<li class="no-padding no-margin">
										<span class="pad">水费凭证:</span>
										<div class="pad">
										<img class="thumbnail" src="${gzfHousePerson.waterPhoto}" alt="凭证"></img></div>
									</li>
									<li class="no-padding no-margin">
										<span class="pad">电费凭证:</span>
										<div class="pad">
										<img class="thumbnail" src="${gzfHousePerson.elecPhoto}" alt="凭证"></img></div>
									</li>
									<li class="no-padding no-margin">
										<span class="pad">天然气费凭证:</span>
										<div class="pad">
										<img class="thumbnail" src="${gzfHousePerson.gasPhoto}" alt="凭证"></img></div>
									</li>
								</ul>
								<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
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