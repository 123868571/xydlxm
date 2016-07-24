	<%@ page contentType="text/html;charset=UTF-8" %>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<%@ page import="com.paopao.hzgzf.modules.pay.common.PaymentConst" %>
	<html>
	<head>
	<title>天然气费管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#houseForm").validate({
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
			
			$("#search").click(function(){
				var url = "${ctx}/pay/gzfPayment/listJson";
				var beginTime =$("#beginTime").val();
				var endTime = $("#endTime").val();
				var pageSize = $("#pageSize").val()
				var pageNo = $("#pageNo").val();
				var spePaymentId = "<%=PaymentConst.SPECIAL_PAYMENT.NATURALGAS%>";
				
				var datas = "accountId=${gzfAccount.id}&spePaymentId="+spePaymentId+"&beginTime="
					+beginTime+"&endTime="+endTime + "&pageSize="+pageSize + "&pageNo="+ pageNo;
				
				postJson(url, datas,function(state, json){
					if (state == "success") {
						var list = json.list;
						var init_html=$("#T_tabletpl").html();
						var template = Handlebars.compile(init_html);
						var rs_html=template(list);
						$("#resText").html(rs_html);
						$("#pagination").html(json);
						$("#pageSize").val(json.pageSize);
						$("#pageNo").val(json.pageNo);
					}else{
						//alert("出错了！！:"+data.msg);
					}
				}); 
				
			});
			
			setFirstAndLastMonthDayAndSec("beginTime", "endTime", false);
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#search").click();
			return false;
		}
		
		function pay(url, datas){
			var accountId = $("#accountId").val();
			if(accountId ==null || accountId==""){
				alert("请先查询住户信息!");
				return false;
			}
			$("#houseForm").attr("action","${ctx}/pay/gzfPayment/save");
			$("#houseForm").submit();
		}
		
		function dealBankInfo(radio){
			if(radio=="1"){
				$("#bankInfo").hide();
			}else{
				$("#bankInfo").show();
			}
		}
	</script>
	</head>
		<body>
		<div id="payInfo" class="portlet-body">
			<div class="row-fluid">
				<div class="span12">
					<form:form id="searchForm" modelAttribute="gzfAccount" action="${ctx}/pay/gzfAccount/getNaturalgasFeeHomePageInfo" method="post" class="form-search">
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
					<!-- BEGIN RENT PAYMENT -->
					<div class="row-fluid">
						<div class="span12">
							<div class="portlet box blue">
								<div class="portlet-title">
									<div class="caption"><i class="icon-edit"></i><span class="whiteFont">天然气缴费</span></div>
										<div class="tools">
											<a href="javascript:;" class="collapse"></a>
										</div>
									</div>

									<div class="portlet-body form">
										<form:form id="houseForm" style="margin:0" modelAttribute="gzfPayment" action="${ctx}/pay/gzfPayment/getNaturalgasFeeHomePageInfo" method="post" class="form-horizontal">
										<form:hidden path="id"/>
										<form:hidden path="accountId" id="accountId"/>
										<form:hidden path="spePaymentId"/>
										<form:hidden path="amount"/>
										<form:hidden path="phoneNo"/>
										<form:hidden path="custId"/>
										<!-- BEGIN CONTNET-->
										<div class="tabbable tabbable-custom tabbable-full-width">
											<div class="tab-pane profile-classic row-fluid">
												<ul class="unstyled span6 ">
													<li class="no-padding no-margin">
														<span class="pad padWidth">房租类型:</span>
														<div class="pad">${fns:getDictLabel(householdInfo.gzfHouseInfo.houseType,"house_type","")}</div>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">房租地址:</span>
														<div class="pad">
														<c:choose>
														<c:when test="${householdInfo.gzfHouseInfo.gzfPalaces.name !=null}">
														${householdInfo.gzfHouseInfo.gzfPalaces.name}苑${householdInfo.gzfHouseInfo.buildNum}楼${householdInfo.gzfHouseInfo.unit}单元${householdInfo.gzfHouseInfo.room}号
														</c:when>
														<c:otherwise></c:otherwise>
														</c:choose>
														</div>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">套内面积:</span>
														<div class="pad">${householdInfo.gzfHouseInfo.innerArea}</div>
														<span class="pad">m<sup>2</sup></span>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">使用面积:</span>
														<div class="pad">${householdInfo.gzfHouseInfo.useArea}</div>
														<span class="pad">m<sup>2</sup></span>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">气单价:</span>
														<div class="pad">${householdInfo.paymentStandard.naturalgasFee}</div>
														<span class="pad">元/度</span>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">气表数(度):</span>
														<div class="pad">${gzfAccount.remarks}</div>
														<span class="pad">(未缴始-末)</span>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">气费金额:</span>
														<div class="pad">
														<c:choose>
														<c:when test="${gzfAccount.fee != null}">${gzfAccount.fee}</c:when>
														<c:otherwise></c:otherwise>
														</c:choose>
														</div>
														<span class="pad">元</span>
														<c:choose>
														<c:when test="${gzfAccount.fee != null}">
															<a href="${ctx}/pay/gzfAccount/listAcctItem?accountId=${gzfAccount.id}&acctItemTypeId=<%=PaymentConst.ACCT_ITEM_TYPE.NATURALGAS%>">查看帐单</a>
														</c:when>
														<c:otherwise></c:otherwise>
														</c:choose>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">住户姓名:</span>
														<div class="pad">${gzfAccount.accountName}</div>
													</li>

													<li class="no-padding no-margin">
														<span class="pad padWidth">电话:</span>
														<div class="pad">${gzfAccount.phoneNo}</div>
													</li>

													<li class="no-padding no-margin">
														<span class="pad padWidth">身份证:</span>
														<div class="pad">${householdInfo.cardid}</div>
													</li>


													<li class="no-padding no-margin">
														<span class="pad padWidth">合同有效期:</span>
														<div class="pad">
														<c:choose>
														<c:when test="${gzfAccount.housePerson.startDate != null}">
														<fmt:formatDate value="${gzfAccount.housePerson.startDate}" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${gzfAccount.housePerson.endDate}" pattern="yyyy-MM-dd"/>
														</c:when>
														<c:otherwise></c:otherwise>
														</c:choose>
														</div>
													</li>

												</ul>
												<ul class="unstyled span6 ">
													<li class="no-padding no-margin">
														<span class="pad padWidth">缴费方式:</span>
														<div id="cashHold" class="pad padFont">
															<label  class="">
																<form:radiobutton path="payMethod" name="optionsRadios1" value="1" onclick="dealBankInfo(1)"/>现金
															</label>
			
															<label class="">
																<form:radiobutton path="payMethod" name="optionsRadios1" value="2" onclick="dealBankInfo(2)"/>银行代扣
															</label>
														</div>
													</li>
													<li class="hide" id="bankInfo">
														<div class="clearfix" style="padding:8px 0; border-bottom:solid 1px #f5f5f5">
															<span class="pad padWidth pull-left">缴费单据号：</span>
															<form:input path="certificateCode" placeholder="请输入缴费单的单据号码" class="m-wrap medium pull-left margin-r-10"/>
															<span class="pad"></span>
														</div>
														<div class="clearfix" style="padding:8px 0;">
															<span class="pad padWidth pull-left">上传缴费单：</span>
															<div class="control-group" style="border:none">
																<div class="controls"  style="position:relative;">
																	<form:hidden id="certificateImage" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
																	<sys:ckfinder input="certificateImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
																</div>
															</div>
														</div>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">需缴金额:</span>
														<div class="pad padFont">
														${gzfPayment.amountYuan}
														</div>
														<span class="pad">元</span>
													</li>
													<li class="no-padding no-margin">
														<span class="pad padWidth">实缴金额:</span>
														<div class="pad padFont">
														<form:input path="amount" htmlEscape="false" maxlength="10" class="input-medium required number" style="padding:6px;"/>
														</div>
														<span class="pad">元</span>
													</li>
													<li>
														<span class="pad padWidth"></span>
														<shiro:hasPermission name="pay:gzfPayment:payDeposite">
															<button id="rentPayment" type="button" class="btn red" onclick="pay()"><i class="icon-yen"></i>缴费</button>
														</shiro:hasPermission>
													</li>
												</ul>
											</div>
										</div>
										<!-- END FORM-->
										</form:form>
									</div>

								</div>
						</div>
					</div>

					<div class="portlet">
						<div class="portlet-title bottom-line">
							<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-reorder"></i>天然气交费历史信息</div>
								<div class="control-group pull-right" style="margin: 0px;">
								<form:form id="searchFormm" modelAttribute="gzfPayment" action="${ctx}/pay/gzfPayment/list" method="post">
									<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
									<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
									<%-- <form:hidden path="accountId"/>
									<form:hidden path="spePaymentId"/> --%>
									<div class="controls">
										<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
											<form:input path="beginTime" id="beginTime" htmlEscape="false" style="width:100px;" class="Wdate m-wrap m-ctrl-medium date-picker" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',realDateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,isShowClear:false})" value="${fns:getDate('yyyy-MM-dd')}"/>
											<span class="add-on"><i class="icon-calendar"></i></span>
										</div>&nbsp;&nbsp;-&nbsp;&nbsp;
										<div style="width:140px;" class="input-append date date-picker margin-r-10" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
											<form:input path="endTime" id="endTime" htmlEscape="false" style="width:100px;" class="Wdate m-wrap m-ctrl-medium date-picke" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',realDateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,isShowClear:false})" value="${fns:getDate('yyyy-MM-dd')}"/>
											<span class="add-on"><i class="icon-calendar"></i></span>
										</div>
										<div class="pull-right">
											<button type="button" id="search" class="btn blue" style="font-family:'Microsoft YaHei';font-size:12px;"><i class="icon-search"></i> 搜索</button>
										</div>
									</div>
								</form:form>
								<sys:message content="${message}"/>
								</div>
								<div class="portlet-body form">
									<div class="row-fluid">
										<div class="span12">
											<table id="contentTable" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>开始日期</th>
														<th>截止日期</th>
														<th>金额（元）</th>
														<th>缴费方式</th>
														<shiro:hasPermission name="pay:gzfPayment:view"><th>操作</th></shiro:hasPermission>
													</tr>
												</thead>
												<tbody id="resText">
													<c:forEach items="${payList.list}" var="gzfPayment">
														<tr class="odd gradeX">
															<td>
																<fmt:formatDate value="${gzfPayment.effectDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
															</td>
															<td>
																<fmt:formatDate value="${gzfPayment.expireDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
															</td>
															<td>
																<fmt:formatNumber value="${gzfPayment.amount/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber>
															</td>
															<td>
																${fns:getDictLabel(gzfPayment.payMethod, 'paymethod', '')}
															</td>
																<shiro:hasPermission name="pay:gzfPayment:view">
															<td>
																<a class="paydetails houseInfo" href="${ctx}/pay/gzfPayment/detail?id=${gzfPayment.id}">查看详情</a>
															</td></shiro:hasPermission>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<div id="pagination" class="pagination">${payList}</div>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
			<!-- END RENT PAYMENT-->


		<script id="T_tabletpl" type="text/x-handlebars-template">
		<table>
		{{#each this}}
		<tr class="odd gradeX">
		    <td>
		    	{{effectDate}}
			</td>
			<td>
				{{expireDate}}
			</td>
			<td>
				{{amountYuan}}
			</td>
			<td>
				{{payMethodName}}
			</td>
				<shiro:hasPermission name="pay:gzfPayment:view">
			<td>
				<a class="paydetails houseInfo" href="${ctx}/pay/gzfPayment/detail?id={{id}}">查看详情</a>
			</td></shiro:hasPermission>
		</tr>
		{{/each}}
		</table>
	</script>


		<%-- <div class="caption"><i class="icon-reorder"></i>最近一月缴费历史
		<a href="${ctx}/pay/gzfPayment/list?accountId=${gzfAccount.id}" class="add-district btn btn-primary">往月缴费历史查询</a>
		</div>
		 --%>

		</body>
		</html>