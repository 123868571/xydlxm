<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>人员信息管理</title>
<meta name="decorator" content="defaultV3" />
<script type="text/javascript">
	$(document).ready(function() {
		//删除 
		$("#del").click(function() {
			var valArr = new Array;
			$("input[type='checkbox'][id='subcheck']:checked").each(function(i) {
				valArr[i] = $(this).val();
			});
			var vals = valArr.join(',');
			$.ajax({
				url : "${ctx}/gzf/gzfHouseholdInfo/moredelete",
				data : {
					valArr : vals
				},
				async : true
			}).done(function(data) {
				if ("success" == data) {
					/* $("#message").text("删除成功"); */
					alert("删除成功");
					location.reload([ true ]);
				} else if ("false" == data) {
					/* $("#message").text("请勾选需要删除项"); */
					alert("请勾选需要删除项");
				} else {
					/* $("#message").text("部分删除失败"); */
					alert("部分删除失败");
					location.reload([ true ]);
				}
			});
		});

		//窗体内的审核选择
		$("#myModal :radio[name=checkVerify]").on('click', function() {
			var _this = $(this);
			if (_this.val() == 0) {
				$('#checkContent').removeAttr("disabled");
			} else {
				$('#checkContent').attr("disabled", "disabled");
			}

		});
		//打开窗体
		$('#myModal').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget);
			if (button.length != 1) {
				return;
			}
			$('#myModalLabel').text(button.data("label") + "意见");
			$('#myModal #checkRowId').val(button.data("id"));
			$('#myModal #checkBit').val(button.data("bit"));
		})

		//关闭窗体
		$('#myModal').on('hidden.bs.modal', function(event) {
			var modal = $(this);
			modal.find('.modal-title').text('');
			$('#myModal #checkRowId').val("");
			$('#myModal #checkBit').val("0");
		});

		//提交意见
		$('#myModal .btn-primary').on('click', function() {
			//$('#defaultForm').formValidation('validate');
			var modal = $('#myModal');
			var cv = $(":radio[name=checkVerify]:checked").val();
			var checkContent = $('#checkContent').val();
			if (isUndefined(cv)) {
				showDanger("请选择意见");
				return;
			}
			if (cv == 0 && checkContent.trim() == '') {
				showDanger("请填写拒绝原因");
				return;
			}

			$.ajax({
				url : "${ctx}/gzf/gzfHouseApplyForm/check",
				data : {
					id : modal.find('#checkRowId').val(),
					bit :  modal.find('#checkBit').val(),
					checkVerify : cv,
					checkContent : checkContent.trim()
				},
				async : true
			}).done(function(data) {
				if ("success" == data) {
					showSuccess("提交成功", function() {
						location.reload([ true ]);
					});
				} else {
					showDanger(data);
				}
			});
		});

		//审核窗口错误展示
		var showDanger = function(errorMsg) {
			if (errorMsg != null && errorMsg != '') {
				$('#bindingDanger strong').text(errorMsg);
			}
			$('#bindingDanger').show();
			$('#bindingDanger').oneTime('3s', function() {
				$('#bindingDanger').hide();
			})
		}
		//审核窗口成功展示
		var showSuccess = function(msg, callback) {
			if (msg != null && msg != '') {
				$('#bindingSuccess strong').text(msg);
			}
			$('#bindingSuccess').show();
			$('#bindingSuccess').oneTime('1s', function() {
				$('#bindingSuccess').hide();
				if (typeof callback == "function") {
					callback();
				}
			})
		}

	});
	var isUndefined = function(obj) {
		if (typeof obj === 'undefined') {
			return true;
		}
		return false;
	}
	//复选框事件
	//全选、取消全选的事件
	function selectAll() {
		if ($("#SelectAll").attr("checked")) {
			$(":checkbox").attr("checked", true);
		} else {
			$(":checkbox").attr("checked", false);
		}
	}
	//子复选框的事件
	function setSelectAll() {
		//当没有选中某个子复选框时，SelectAll取消选中
		if (!$("#subcheck").checked) {
			$("#SelectAll").attr("checked", false);
		}
		var chsub = $("input[type='checkbox'][id='subcheck']").length; //获取subcheck的个数
		var checkedsub = $("input[type='checkbox'][id='subcheck']:checked").length; //获取选中的subcheck的个数
		if (checkedsub == chsub) {
			$("#SelectAll").attr("checked", true);
		}
	}
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/gzf/gzfHouseApplyForm/list");
		$("#searchForm").submit();
		return false;
	}
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

.modal-sm {
	width: 280px;
}
</style>
<script type="text/javascript"
	src="${ctxStatic}/myMedia/js/formValidation.js"></script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="gzfHouseApplyForm"
		action="${ctx}/gzf/gzfHouseApplyForm/list" method="post"
		class=" form-search" style="background:#fff;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<div class="row-fluid">
			<div class="span12">
				<div class="dropd margin-r-20 flo-left">
					<form:input path="applyMajorName" htmlEscape="false"
						maxlength="100" class="input-medium" placeholder="申请人姓名" />
				</div>
				<div class="dropd margin-r-20  flo-left">
					<form:input path="applyMajorPhone" htmlEscape="false"
						maxlength="100" class="input-medium" placeholder="联系电话" />
				</div>
				<div class="dropd margin-r-20  flo-left">
					<form:input path="applyMajorIdcard" htmlEscape="false"
						maxlength="100" class="input-medium" placeholder="身份证" />
				</div>
				<div class="pull-left">
					<button type="submit" class="btn blue">
						<i class="icon-search"></i> 搜索
					</button>
				</div>

			</div>
		</div>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" id="SelectAll" value="全选"
					onclick="selectAll();" /></th>
				<th style="text-align: center;">申请人</th>
				<th style="text-align: center;">联系电话</th>
				<th style="text-align: center;">身份证号</th>
				<th style="text-align: center;">婚姻状况</th>
				<th style="text-align: center;">申请类型</th>
				<th style="text-align: center;">状态</th>
				<th style="text-align: center;">当前步骤</th>
				<th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="bean">
				<tr>
					<td><input type="checkbox" id="subcheck"
						value="${bean.bean.id}" onclick="setSelectAll();" /></td>
					<td style="text-align: center;"><a
						href="${ctx}/gzf/gzfHouseApplyForm/apply/${bean.bean.formType}?id=${bean.bean.id}">
							${bean.bean.applyMajorName} </a></td>
					<td style="text-align: center;">${bean.bean.applyMajorPhone}</td>
					<td style="text-align: center;">${bean.bean.applyMajorIdcard}</td>
					<td style="text-align: center;">
						${fns:getDictLabel(bean.bean.applyMajorMaritalStatus, 'marital_status', '未知')}
					</td>
					<td style="text-align: center;">
						${fns:getDictLabel(bean.bean.formType, 'apply_form_type', '未知')}</td>
					<td style="text-align: center;">${bean.allCheckStatus.name }</td>
					<td style="text-align: center;">
						${fns:getDictLabel(bean.currentCheckStep,bean.dictCheckName,"已完成")}
					</td>

					<%-- <shiro:hasPermission name="gzf:gzfHouseholdInfo:edit"><td style="text-align:center;">
    				<a href="${ctx}/gzf/gzfHouseholdInfo/form?id=${gzfHouseholdInfo.id}">修改</a>
					<a href="${ctx}/gzf/gzfHouseholdInfo/delete?id=${gzfHouseholdInfo.id}" onclick="return confirmx('确认要删除该人员信息吗？', this.href)">删除</a>
 --%>
					<td style="text-align: center;"><c:forEach
							items="${fns:getDictList(bean.dictCheckName)}" var="apc">
							<c:choose>
								<c:when test="${bean.checkStatusMap[apc.value]==0}">
									<c:if test="${apc.value==bean.currentCheckStep }">
										<c:if test="${bean.allCheckStatus.value > -1 }">
											<shiro:hasPermission
												name="gzf:gzfHouseApplyFormCheck${apc.value}:edit">
												<a href="#myModal" data-toggle="modal" class="btn"
													data-id="${bean.bean.id}" data-label="${apc.label}"
													data-bit="${apc.value}">${apc.label}</a>
											</shiro:hasPermission>
											<shiro:lacksPermission
												name="gzf:gzfHouseApplyFormCheck${apc.value}:edit">
												${apc.label}
											</shiro:lacksPermission>
										</c:if>
										<c:if test="${bean.allCheckStatus.value == -1 }">
											${apc.label}
										</c:if>
									</c:if>
									<c:if test="${apc.value!=bean.currentCheckStep }">
											${apc.label}
									</c:if>
								</c:when>
								<c:otherwise>
									${apc.label}
								</c:otherwise>
							</c:choose>
							&nbsp;
						</c:forEach><a class="btn" href="${ctx}/gzf/gzfHouseApplyForm/information?id=${bean.bean.id}">查看详情</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div id="myModal" class="modal hide fade modal-sm" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">审核意见</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal fv-form fv-form-bootstrap"
				id="defaultForm">
				<div class="alert alert-danger alert-dismissible fade in"
					style="display: none" id="bindingDanger" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<strong>错误!</strong>
				</div>
				<div class="alert alert-success alert-dismissible fade in"
					style="display: none" id="bindingSuccess" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<strong>成功!</strong>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<input type="hidden" id="checkRowId" value=""></input> <input
							type="hidden" id="checkBit" value="0"></input><input type="radio"
							name="checkVerify" id="checkVerify1" value="1">同意</input> <input
							type="radio" name="checkVerify" id="checkVerify0" value="0">不同意</input>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<textarea class="form-control" rows="3" cols="50"
							id="checkContent" name="checkContent" disabled="disabled"
							placeholder="不同意时请备注信息"></textarea>
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			<button class="btn btn-primary">提交</button>
		</div>
	</div>
</body>
</html>