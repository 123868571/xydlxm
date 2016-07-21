<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>客户资料管理管理</title>
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
      initCheckStatus();
    });
    
    function initCheckStatus() {
  	  var status = "${comResidentElecApply.checkStatus}";
  	  if (status != '0' && status != '') {
  		  $("form input").attr("disabled", "disabled");
  		  $("form radio").attr("disabled", "disabled");
  		  $("form select").attr("disabled", "disabled");
  		  $("form textarea").attr("disabled", "disabled");
  		  $("#btnCancel").removeAttr("disabled");
  		  $("#areaButton").hide();
  	  }
    }
  </script>
</head>
<body>
  <ul class="nav nav-tabs">
    <li><a href="${ctx}/dlj/comClientBasicInfo/">客户资料管理列表</a></li>
    <li class="active"><a href="${ctx}/dlj/comClientBasicInfo/view?id=${comClientBasicInfo.id}">客户资料查看</a></li>
  </ul><br/>
  <div class="row-fluid">
    <div class="span12 no-padding no-margin">
      <form:form id="inputForm" modelAttribute="comClientBasicInfo" 
        action="${ctx}/dlj/comClientBasicInfo/save" method="post" class="form-inline">
        <form:hidden path="id"/>
      <%--<form novalidate="novalidate" action="#" class="form-horizontal" id="submit_form">--%>
        <div class="form-wizard">
          <div class="navbar steps hide">
            <div class="navbar-inner">
              <ul class="row-fluid nav nav-pills">
                <li class="span3 active">
                  <a href="#tab1" data-toggle="tab" class="step active"> 
                    <span class="number">1</span> 
                    <span class="desc">
                      <i class="icon-ok"></i>客户基本资料管理
                    </span>
                  </a>
                </li>
              </ul>
            </div>
          </div>
          <div class="tab-content">
            <!-- tab1 start -->
            <div class="tab-pane active" id="tab1">
              <div class="row-fluid">
                <div class="span12">
                  <div class="portlet box blue">
                    <div style="border-bottom: 1px solid rgb(245, 245, 245);" class="portlet-title">
                      <div style="color: rgb(77, 144, 254);" class="caption">
                        <i class="icon-edit1"></i><span class="whiteFont">客户基本资料管理</span>
                      </div>
                    </div>
                    <div class="portlet-body form">
                      <div class="row-fluid">
                        <div class="span12">
                          <div class="control-group">
                            <%--<table class="table table-bordered table-condensed"> --%>
                            <table border="1" style="width: 100%">
                            <thead>
                                <th colspan="7">
                                  <h3 class="text-center">客户基本信息</h3> <br />
                                  <div style="width:100%;">
                                  <span class="text-center">档案编号: 
                                    <%--<form:input path="archiveNo" htmlEscape="false" maxlength="32" class="Default input "/>--%>
                                    ${comClientBasicInfo.archiveNo}
                                  </span>
                                  </div>
                                </th>
                              </thead>
                              <tbody>
                                <tr>
                                  <td width="10%"><label class="control-label">客户姓名</label></td>
                                  <td width="15%" class="text-left">
                                    <%--<form:input path="clientName" htmlEscape="false" maxlength="200" class="Default input " placeholder="客户姓名"/>--%>
                                    ${comClientBasicInfo.clientName}
                                  </td>
                                  <td width="10%"><label class="control-label">身份证号码</label></td>
                                  <td width="18%" colspan="2" class="text-left">
                                    <%--<form:input path="cardId" htmlEscape="false" maxlength="20" class="Default input " placeholder="身份证号码"/>--%>
                                    ${comClientBasicInfo.cardId}
                                  </td>
                                </tr>
                                <tr>
                                  <td><label class="control-label">用电地址</label></td>
                                  <td colspan="4" class="text-left">
                                    <div class="form-control">
                                      <%--<form:input path="address" htmlEscape="false" maxlength="500" cssClass="form-control" cssStyle="width:55%;" placeholder="用电地址"/>--%>
                                      ${comClientBasicInfo.address}
                                      </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td><label class="control-label">房产证编号</label></td>
                                  <td colspan="2" class="text-left">
                                    <div class="controls">
                                      <%--<form:input path="estateLicense" htmlEscape="false" maxlength="200" class="Default input "/>--%>
                                      ${comClientBasicInfo.estateLicense}
                                    </div>
                                  </td>
                                  <td><label class="control-label">发证机关</label></td>
                                  <td class="text-left">
                                    <div class="controls">
                                      <%--<form:input path="issuingAuthority" htmlEscape="false" maxlength="500" class="Default input "/>--%>
                                      ${comClientBasicInfo.issuingAuthority}
                                    </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td><label class="control-label">经办人姓名</label></td>
                                  <td colspan="2" class="text-left">
                                    <div class="controls">
                                      <%--<form:input path="operatorName" htmlEscape="false" maxlength="200" class="Default input "/>--%>
                                      ${comClientBasicInfo.operatorName}
                                    </div>
                                  </td>
                                  <td><label class="control-label">身份证号</label></td>
                                  <td class="text-left">
                                    <div class="controls">
                                      <%--<form:input path="operatorCardId" htmlEscape="false" maxlength="20" class="Default input " placeholder="经办人身份证号码"/>--%>
                                      ${comClientBasicInfo.operatorCardId}
                                    </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <label class="control-label">备注</label>
                                  </td>
                                  <td colspan="4" class="text-left">
                                     <div class="controls">
                                       <%--<form:textarea path="remark" htmlEscape="false" rows="4" maxlength="255" cssClass="input-xxlarge "  cssStyle="width:55%;"/>--%>
                                       ${comClientBasicInfo.remark}
                                     </div>
                                  </td>
                                </tr>
                              </tbody>
                            </table>
                          </div>
                          <div class="control-group">
                            <label class="col-sm-2 control-label">所属地区：</label>
                            <div class="controls">
                              <sys:treeselect id="area" name="area.id" 
                                value="${comClientBasicInfo.area.id}" labelName="area.name" 
                                labelValue="${comClientBasicInfo.area.name}"
                                title="区域" url="/sys/area/treeData" cssClass="" 
                                allowClear="true" notAllowSelectParent="true" disabled="disabled"/>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <%--<div class="form-actions clearfix">
                <a href="javascript:;" class="btn blue button-next ok"> 确认 
                <i class="m-icon-swapright m-icon-white"></i>
                </a>
              </div>
            --%>
            <div class="portlet box blue">
              <div style="border-bottom: 1px solid rgb(245, 245, 245);" class="portlet-title">
                <div style="color: rgb(77, 144, 254);" class="caption">
                  <i class="icon-edit1"></i><span class="whiteFont">修改记录</span>
                </div>
              </div>
            <div class="portlet-body form">
        <table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>修改的栏目</th>
				<th>修改前内容</th>
				<th>修改后内容</th>
				<th>是否是附件</th>
				<th>修改类型</th>
				<th>修改人</th>
				<th>更新日期</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comRecord">
			<tr>
				<td>
					${comRecord.changeColumnDesc}
				</td>
				<td>
					${comRecord.befValue}
				</td>
				<td>
					${comRecord.aftValue}
				</td>
				<td>
					<c:if test="${comRecord.isAttach eq '0'}">
						否
					</c:if>
					<c:if test="${comRecord.isAttach eq '1'}">
						是
					</c:if>
				</td>
				<td>
					<c:if test="${comRecord.modifyType eq '0'}">
						基本信息修改
					</c:if>
					<c:if test="${comRecord.modifyType eq '1'}">
						添加附件
					</c:if>
					<c:if test="${comRecord.modifyType eq '2'}">
						删除附件
					</c:if>
				</td>
				<td>
					${comRecord.updateBy.name }
				</td>
				<td>
					<fmt:formatDate value="${comRecord.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div class="pagination">${page}</div>
	</div>
	</div>
              <div class="form-actions">
                  <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
              </div>
            <!-- tab1 end -->
          </div>
      </form:form>
    </div>
  </div>
</body>
</html>