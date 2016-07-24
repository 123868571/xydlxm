<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>居民用电申请表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
	initCheckStatus();
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
    //$("#btnApply").click()
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
  <ul class="nav nav-tabs">
    <li><a href="${ctx}/dlj/comResidentElecApply/">居民生活用电表列表</a></li>
    <li class="active"><a href="${ctx}/dlj/comResidentElecApply/form?id=${comResidentElecApply.id}">居民生活用电表<shiro:hasPermission name="dlj:comResidentElecApply:edit">${not empty comResidentElecApply.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dlj:comResidentElecApply:edit">查看</shiro:lacksPermission></a></li>
  </ul><br/>
  <div class="row-fluid">
    <div class="span12 no-padding no-margin">
      <form:form id="inputForm" modelAttribute="comResidentElecApply" 
        action="${ctx}/dlj/comResidentElecApply/save" method="post" class="form-inline">
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
                      <i class="icon-ok"></i>城乡居民生活用电需求表
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
                    <div style="border-bottom: 1px solid rgb(245, 245, 245);"
                      class="portlet-title">
                      <div style="color: rgb(77, 144, 254);" class="caption">
                        <i class="icon-edit1"></i><span class="whiteFont">城乡居民生活用电需求表</span>
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
                                  <h3 class="text-center">城乡居民生活用电需求表</h3> <br />
                                  <form:input path="serial" htmlEscape="false" maxlength="32" class="Default input hide"/>
                                  <div style="width:100%;">
                                <span style="display:block; height:30px; line-height:30px; padding-left:5px; float:left;width:400px;">申请编号: ${comResidentElecApply.serial} </span>
                                <span style="display:block; height:30px; line-height:30px;padding-left:50px;">客户编号: <form:input path="custNo" htmlEscape="false" maxlength="32" class="Default input "/></span>
                                  </div>
                                </th>
                              </thead>
                              <tbody>
                                <tr>
                                  <td width="10%"><label class="control-label">客户姓名</label></td>
                                  <td width="15%" class="text-left">
                                    <form:input path="name" htmlEscape="false" maxlength="200" class="Default input " placeholder="客户姓名"/>
                                    <%--<input type="text" id="name" value="${dto.bean.applyMajorName}">--%>
                                    <input type="hidden" value="${dto.bean.id}" id="memberId">
                                    <input type="hidden" value="${serial}" id="serial">
                                  </td>
                                  <td width="10%"><label class="control-label">身份证号码</label></td>
                                  <td width="18%" colspan="2" class="text-left">
                                    <form:input path="cardId" htmlEscape="false" maxlength="20" class="Default input " placeholder="身份证号码"/>
                                  </td>
                                </tr>
                                <tr>
                                  <td><label class="control-label">用电地址</label></td>
                                  <td colspan="4" class="text-left">
                                    <div class="form-control">
                                      <form:input path="address" htmlEscape="false" maxlength="500" cssClass="form-control" cssStyle="width:55%;" placeholder="用电地址"/>
                                      </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td><label class="control-label">房产证编号</label></td>
                                  <td colspan="2" class="text-left">
                                    <div class="controls">
                                      <form:input path="estateLicense" htmlEscape="false" maxlength="200" class="Default input "/>
                                    </div>
                                  </td>
                                  <td><label class="control-label">发证机关</label></td>
                                  <td class="text-left">
                                    <div class="controls">
                                      <form:input path="issuingAuthority" htmlEscape="false" maxlength="500" class="Default input "/>
                                    </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td><label class="control-label">经办人姓名</label></td>
                                  <td colspan="2" class="text-left">
                                    <div class="controls">
                                      <form:input path="operatorName" htmlEscape="false" maxlength="200" class="Default input "/>
                                    </div>
                                  </td>
                                  <td><label class="control-label">身份证号</label></td>
                                  <td class="text-left">
                                    <div class="controls">
                                      <form:input path="operatorCardId" htmlEscape="false" maxlength="20" class="Default input " placeholder="经办人身份证号码"/>
                                    </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td><label class="control-label">供电及计费方式</label></td>
                                  <td colspan="2" class="text-left">
                                    <div class="radio-inline">
                                      <form:radiobuttons path="elecType" items="${fns:getDictList('elec_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
                                    </div>
                                  </td>
                                  <td><label class="control-label">容量</label> </td>
                                  <td class="text-left">
                                   <div class="controls">
                                     <form:input path="capacity" htmlEscape="false" maxlength="50" class="Default input " placeholder="千瓦"/>
                                   </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td><label class="control-label">电费支付方式</label></td>
                                  <td colspan="4" class="text-left">
                                    <div class="radio-inline">
                                      <form:radiobuttons path="payType" items="${fns:getDictList('pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
                                    </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td><label class="control-label">电费尾款归整</label></td>
                                  <td colspan="4" class="text-left">
                                    <div class="radio-inline">
                                      <form:radiobuttons path="payWraping" items="${fns:getDictList('pay_wraping')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
                                    </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td><label class="control-label">联系人</label> </td>
                                  <td colspan="2" class="text-left">
                                   <div class="controls">
                                     <form:input path="linkName" htmlEscape="false" maxlength="200" class="Default input "/>
                                   </div>
                                  </td>
                                  <td><label class="control-label">联系电话</label> </td>
                                  <td class="text-left">
                                   <div class="controls">
                                     <form:input path="linkTel" htmlEscape="false" maxlength="50" class="Default input "/>
                                   </div>
                                  </td>
                                </tr>
                                <tr>
                                  <td>
                                    <label class="control-label">备注</label>
                                  </td>
                                  <td colspan="4" class="text-left">
                                     <div class="controls">
                                       <form:textarea path="remark" htmlEscape="false" rows="4" maxlength="255" cssClass="input-xxlarge "  cssStyle="width:55%;"/>
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
                                value="${comResidentElecApply.area.id}" labelName="area.name" 
                                labelValue="${comResidentElecApply.area.name}"
                                title="区域" url="/sys/area/treeData" cssClass="" 
                                allowClear="true" notAllowSelectParent="true"/>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="control-group hide">
      <label class="control-label">check_status：</label>
      <div class="controls">
        <form:input path="checkStatus" htmlEscape="false" maxlength="10" class="Default input "/>
      </div>
    </div>
    <div class="control-group hide">
      <label class="control-label">check_content：</label>
      <div class="controls">
        <form:input path="checkContent" htmlEscape="false" maxlength="500" class="Default input "/>
      </div>
    </div>
    <div class="control-group hide">
      <label class="control-label">op_id：</label>
      <div class="controls">
        <form:input path="opId" htmlEscape="false" maxlength="11" class="Default input "/>
      </div>
    </div>
    <div class="control-group hide">
      <label class="control-label">备注：</label>
      <div class="controls">
        <form:textarea path="remark" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
      </div>
    </div>
              <%--<div class="form-actions clearfix">
                <a href="javascript:;" class="btn blue button-next ok"> 确认 
                <i class="m-icon-swapright m-icon-white"></i>
                </a>
              </div>
            --%>
            <div class="form-actions">
      <shiro:hasPermission name="dlj:comResidentElecApply:edit">
        <c:if test="${comResidentElecApply.checkStatus eq '0'}">
          <input id="btnSubmit" class="btn btn-info" type="submit" value="保 存"/>&nbsp;
          <input id="btnApply" class="btn btn-info" type="button" value="提 交"/>&nbsp;
        </c:if>
        </shiro:hasPermission>
      <input id="btnCancel" class="btn btn-info" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
            </div>
            <!-- tab1 end -->
          </div>
      </form:form>
    </div>
  </div>
</body>
</html>