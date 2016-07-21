<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>居民生活用电表审核</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function toApply(id) {
			asyncApply(id);
			/*
			if (confirmx("确定提交申请？",)) {
				loading("正在提交，请稍等...");
				$("#searchForm").attr("action", "${ctx}/dlj/comResidentElecApply/apply?id=" + id);
				$("#searchForm").submit();
			}
			*/
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dlj/comResidentElecApply/">居民生活用电表列表</a></li>
		<shiro:hasPermission name="dlj:comResidentElecApply:edit"><li><a href="${ctx}/dlj/comResidentElecApply/form">居民生活用电表添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="comResidentElecApply" action="${ctx}/dlj/comResidentElecApply/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="hide"><label>地区：</label>
				<sys:treeselect id="area" name="area.id" value="${comResidentElecApply.area.id}" labelName="area.name" labelValue="${comResidentElecApply.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>申请编号：</label>
				<form:input path="serial" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>客户姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>身份证：</label>
				<form:input path="cardId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn blue btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>地区</th>
				<th>申请编号</th>
				<th>客户姓名</th>
				<th>身份证</th>
				<th>供电及计费方式</th>
				<%--<th>容量</th>
				<th>电费尾款归整</th>
				<th>联系人</th>
				<th>联系电话</th>
				--%><th>更新日期</th>
				<th>状态</th>
				<shiro:hasPermission name="dlj:comResidentElecApply:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ea">
			<tr>
				<td><a href="${ctx}/dlj/comResidentElecApply/form?id=${ea.id}">
					${ea.area.name}
				</a></td>
				<td>
					${ea.serial}
				</td>
				<td>
					${ea.name}
				</td>
				<td>
					${ea.cardId}
				</td>
				<td>
					${fns:getDictLabel(ea.elecType, 'elec_type', '')}
				</td>
				<%--<td>
					${ea.capacity}
				</td>
				<td>
					${fns:getDictLabel(ea.payWraping, 'pay_wraping', '')}
				</td>
				<td>
					${ea.linkName}
				</td>
				<td>
					${ea.linkTel}
				</td>
				--%>
				<td>
					<fmt:formatDate value="${ea.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${ea.checkStatus eq '0'}"><!-- 待提交 -->
						<span class="text-info">${fns:getDictLabel(ea.checkStatus, 'check_status', '')}</span>
					</c:if>
					<c:if test="${ea.checkStatus eq '1'}"><!-- 处理中 -->
						<span class="text-info">${fns:getDictLabel(ea.checkStatus, 'check_status', '')}</span>
					</c:if>
					<c:if test="${ea.checkStatus eq '2'}"><!-- 已通过 -->
						<span class="text-success">${fns:getDictLabel(ea.checkStatus, 'check_status', '')}</span>
					</c:if>
					<c:if test="${ea.checkStatus eq '3'}"><!-- 已驳回 -->
						<span class="text-error">${fns:getDictLabel(ea.checkStatus, 'check_status', '')}</span>
					</c:if>
					
				</td>
				<shiro:hasPermission name="dlj:comResidentElecApply:edit">
					<td>
	    				<a href="${ctx}/dlj/comResidentElecApply/form?id=${ea.id}">修改</a>
	    				<a href="javascript:asyncApply('${ea.id}')" onclick="return confirmx('确认要提交用电申请吗？', this.href)">提交</a>
	    				<c:if test="${ea.checkStatus ne '0' }">
	    					<span class="disabled">删除</span>
	    				</c:if>
	    				<c:if test="${ea.checkStatus eq '0' }">
							<a href="${ctx}/dlj/comResidentElecApply/delete?id=${ea.id}" 
								onclick="return confirmx('确认要删除该居民生活用电表吗？', this.href)">删除</a>
		    			</c:if>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>