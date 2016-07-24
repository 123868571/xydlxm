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

	<div id="transferManage">
		<div class="row-fluid">
			<div class="span12">
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-edit"></i><span class="whiteFont">签订协议</span></div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN CONTNET-->
							<div class="tabbable tabbable-custom tabbable-full-width">
								<div class="tab-pane profile-classic row-fluid">
									<ul id="bindInfo" class="unstyled span6 ">
										<li>
											<span class="pad padWidth">房租:</span>
											<div class="pad padFont">${gzfHousePerson.gzfHouseholdInfo.gzfPaymentStandard.rentUnitPrice}</div>
											<span class="pad">元/月</span>
										</li>
										<li>
											<span class="pad padWidth">押金:</span>
											<div class="pad padFont">${gzfHousePerson.gzfHouseholdInfo.gzfPaymentStandard.deposit}</div>
											<span class="pad">元</span>
										</li>
										<li>
											<span class="pad padWidth">房卡个数:</span>
											<div class="pad padFont">${gzfHousePerson.gzfHouseholdInfo.cardnum}</div>
											<span class="pad">个</span>
										</li>
										<li>
											<span class="pad padWidth pull-left">合同日期：</span>
											<div class="controls">
												<span class="pad">
													<fmt:formatDate value="${gzfHousePerson.startDate}" pattern="yyyy-MM-dd"/>至
													<fmt:formatDate value="${gzfHousePerson.endDate}" pattern="yyyy-MM-dd"/>
												</span>
											</div>
										</li>
										<li>
											<span class="pad padWidth pull-left">房租生效日期：</span>
											<div class="control-group">
												<span class="pad"><fmt:formatDate value="${gzfHousePerson.effectiveDate}" pattern="yyyy-MM-dd"/></span>
											</div>
										</li>
										<li>
											<span class="pad padWidth pull-left">更新时间：</span>
											<div class="control-group">
												<span class="pad">
													<fmt:formatDate value="${gzfHousePerson.gzfHouseInfo.updateDate}" pattern="yyyy-MM-dd"/>
												</span>
											</div>
										</li>
										<li>
											<span class="pad padWidth"></span>
											<%-- <shiro:hasPermission name="gzf:gzfHouseInfo:edit">
											<button id="btnSubmit" class="btn blue margin-r-20 " type="submit"><i class="icon-ok"></i>保存</button></shiro:hasPermission> --%>
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
										</li>
									</ul>
									<div class="span6 pull-right">
										<div class="item">
											<a class="fancybox-button" data-rel="fancybox-button" title="合同" href="../../../static/flexpaper/preview?id=${gzfHousePerson.id}&template=zhoushan" target="_blank">
												<div align="center" class="zoom">
													<img src="${ctxStatic }/media/image/house-ht.jpg" width="231px" alt="Photo" />
													<div class="zoom-icon"></div>
												</div>
											</a>
											<div class="details">
												点击浏览合同
											</div>
										</div>
										<div align="center">
											<button onclick="" type="submit" class="btn green"><i class="icon-print"></i> 打印合同</button>
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
											<span class="pad">房屋缩略图:</span>
											<div class="pad">
											<img class="thumbnail" src="${gzfHousePerson.gzfHouseInfo.photo}" alt="房屋缩略图"></img></div>
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