	<%@ page contentType="text/html;charset=UTF-8" %>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<%@ page import="com.paopao.hzgzf.modules.pay.common.PaymentConst" %>
	<html>
	<head>
	<title>一次性结清</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
	</head>
		<body>
		<div id="payInfo" class="portlet-body">
			<div class="row-fluid">
				<div class="span12">
					<form:form id="searchForm" modelAttribute="gzfAccount" action="${ctx}/pay/gzfAccount/queryEveryTypeFee" method="post" class="form-search">
						<div class="input-append">
							<label class="flo-left">手机号码：</label>
							<form:input path="phoneNo" htmlEscape="false" maxlength="100" class="input-medium" placeholder="请输入手机号查询" style="padding:6px;"/>
						</div>
						<div class="input-append">
							<label class="flo-left">身份证号：</label>
							<form:input path="cardid" htmlEscape="false" maxlength="100" class="input-medium" placeholder="请输入身份证号查询" style="padding:6px;"/>
						</div>
						<div class="input-append">
							<button type="submit" class="btn blue"><i class="icon-search"></i>搜索</button>
						</div>
					</form:form>
					<sys:message content="${message}"/>
					<div class="row-fluid">
						<div class="span12">
							<div class="portlet box blue">
								<div class="portlet-title">
									<div class="caption"><i class="icon-edit"></i><span class="whiteFont">余额信息</span></div>
										<div class="tools">
											<a href="javascript:;" class="collapse"></a>
										</div>
									</div>

									<div class="portlet-body form">
										<form:form id="houseForm" style="margin:0" modelAttribute="gzfPayment" action="${ctx}/pay/gzfPayment/settlementOneOff" method="post" class="form-horizontal">
										<form:hidden path="accountId" id="accountId"/>
										<form:hidden path="phoneNo"/>
										<form:hidden path="custId"/>
										<div class="tabbable tabbable-custom tabbable-full-width">
											<div class="tab-pane profile-classic row-fluid">
												<ul class="unstyled span6 ">
													<li class="no-padding no-margin">
														<span class="pad padWidth">房租类型:</span>
														<div class="pad">${fns:getDictLabel(housePerson.gzfHouseInfo.houseType,"house_type","")}</div>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">房屋地址:</span>
														<div class="pad">
														<c:choose>
														<c:when test="${housePerson.gzfHouseInfo.gzfPalaces.name !=null}">
														${housePerson.gzfHouseInfo.gzfPalaces.name}${housePerson.gzfHouseInfo.buildNum}楼${housePerson.gzfHouseInfo.unit}单元${housePerson.gzfHouseInfo.room}号
														</c:when>
														<c:otherwise></c:otherwise>
														</c:choose>
														</div>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">房租费(元):</span>
														<div class="pad">${houseRentFee}</div>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">物业费(元):</span>
														<div class="pad">${manageFee}</div>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">能耗费(元):</span>
														<div class="pad">${consumption}</div>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">水费(元):</span>
														<div class="pad">${waterFee}</div>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">电费(元):</span>
														<div class="pad">${electricityFee}</div>
													</li>

													<li class="no-padding no-margin">
														<span class="pad padWidth">燃气费(元):</span>
														<div class="pad">${naturalgasFee}</div>
													</li>
													
													<li class="no-padding no-margin">
														<span class="pad padWidth">设备维修费(元):</span>
														<div class="pad">${repairFee}</div>
													</li>
													
													<li class="no-padding no-margin">
														<span class="pad padWidth">自由费用(元):</span>
														<div class="pad">${freeFee}</div>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">押金(元):</span>
														<div class="pad">${deposite}</div>
													</li>
												</ul>
												<ul class="unstyled span6 ">
													<li class="no-padding no-margin">
														<span class="pad padWidth">总费用(元):</span>
														<div class="pad">${total}</div>
													</li>
													<li>
														<span class="pad padWidth"></span>
														<shiro:hasPermission name="pay:gzfPayment:settlement">
														<button id="payBtn" type="submit" class="btn red"><i class="icon-yen"></i>一次性结清</button>
														</shiro:hasPermission>
													</li>
												</ul>
											</div>
										</div>
										</form:form>
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>