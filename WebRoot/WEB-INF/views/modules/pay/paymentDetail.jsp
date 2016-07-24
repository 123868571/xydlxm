<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>缴费详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="gzfPayment" action="${ctx}/pay/gzfPayment/detail" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">缴费时间：</label>
			<div class="controls">
				<fmt:formatDate value="${gzfPayment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">费用开始日期：</label>
			<div class="controls">
				<fmt:formatDate value="${gzfPayment.effectDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">费用结束日期：</label>
			<div class="controls">
				<fmt:formatDate value="${gzfPayment.expireDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缴费方式：</label>
			<div class="controls">
				${fns:getDictLabel(gzfPayment.payMethod, 'paymethod', '')}
			</div>
		</div>
		<c:choose>
			<c:when test="${gzfPayment.payMethod =='2' }">
				<div class="control-group">
					<label class="control-label">缴费单据：</label>
					<div class="controls">
						${gzfPayment.certificateCode}
					</div>
					<img class="thumbnail" src="${gzfPayment.photo}" alt="缩略图"></img></div>
				</div>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
		<%-- <div class="control-group">
			<label class="control-label">能耗费用总额：</label>
			<div class="controls">
				<fmt:formatNumber value="${gzfPayment.amount/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber>元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物业费用总额：</label>
			<div class="controls">
				<fmt:formatNumber value="${gzfPayment.amount/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber>元
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">缴费总金额：</label>
			<div class="controls">
				<fmt:formatNumber value="${gzfPayment.amount/100}" maxFractionDigits="2" pattern=""></fmt:formatNumber>元
			</div>
		</div>
	</form:form>
	<div class="form-actions">
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</body>
</html>