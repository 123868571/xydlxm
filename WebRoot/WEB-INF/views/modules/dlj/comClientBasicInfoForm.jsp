<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>客户资料管理管理</title>
  <meta name="decorator" content="default"/>
  <script type="text/javascript">
    var j = 1;
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
        
      $("#btn_add2").click(function(){  
          document.getElementById("newUpload2").innerHTML += '<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><a href="javascript:del_2('+j+')">删除</a></div>';  
            j = j + 1;  
      });  
    });
    
	function del_2(o){  
	  document.getElementById("newUpload2").removeChild(document.getElementById("div_"+o));
	}  
    
  </script>
</head>
<body>
  <ul class="nav nav-tabs">
    <li><a href="${ctx}/dlj/comClientBasicInfo/">客户资料管理列表</a></li>
    <li class="active"><a href="${ctx}/dlj/comClientBasicInfo/form?id=${comClientBasicInfo.id}">客户资料管理<shiro:hasPermission name="dlj:comClientBasicInfo:edit">${not empty comClientBasicInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dlj:comClientBasicInfo:edit">查看</shiro:lacksPermission></a></li>
  </ul><br/>
  <div class="row-fluid">
    <div class="span12 no-padding no-margin">
      <form:form id="inputForm" modelAttribute="comClientBasicInfo"  enctype="multipart/form-data"
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
                    <div style="border-bottom: 1px solid rgb(245, 245, 245);"
                      class="portlet-title">
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
                                    <form:input path="archiveNo" htmlEscape="false" maxlength="32" class="Default input "/>
                                  </span>
                                  </div>
                                </th>
                              </thead>
                              <tbody>
                                <tr>
                                  <td width="10%"><label class="control-label">客户姓名</label></td>
                                  <td width="15%" class="text-left">
                                    <form:input path="clientName" htmlEscape="false" maxlength="200" class="Default input " placeholder="客户姓名"/>
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
                                value="${comClientBasicInfo.area.id}" labelName="area.name" 
                                labelValue="${comClientBasicInfo.area.name}"
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
              <div class="portlet box blue">
                <div style="border-bottom: 1px solid rgb(245, 245, 245);"
                  class="portlet-title">
                  <div style="color: rgb(77, 144, 254);" class="caption">
                    <i class="icon-edit1"></i><span class="whiteFont">客户资料附件</span>
                  </div>
                </div>
                <div class="portlet-body form form-group">
	              <div id="newUpload2">  
	                <input type="file" name="file"/>
	              </div>  
              	  <input type="button" id="btn_add2" class="btn blue button-next ok" value="添加" />  
              	 <%-- <input type="button" id="upload" class="btn blue button-next ok" value="上传" />  --%>
              	</div>
              </div>
              <%--<div class="form-actions clearfix">
                <a href="javascript:;" class="btn blue button-next ok"> 确认 
                <i class="m-icon-swapright m-icon-white"></i>
                </a>
              </div>
            --%>
              <div class="form-actions">
                <shiro:hasPermission name="dlj:comClientBasicInfo:edit">
                  <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
                  <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
              </div>
            <!-- tab1 end -->
          </div>
      </form:form>
    </div>
  </div>
</body>
</html>