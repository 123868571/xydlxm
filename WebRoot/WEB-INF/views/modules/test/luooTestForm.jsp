<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<html>
<head>
    <title>test管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            alert(1);
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                    var name = $("#name").val();
                    var remark = $("#remark").val();
                    var age = $("#age").val();
                    var luooTest = {name:name,age:age,remark:remark};
                    var aluooTest  = JSON.stringify(luooTest);


                    var flag;
                    $.ajax({
                        type : 'post',
                        dataType:"json",
                        contentType: "application/json; charset=utf-8",
                        url : '${ctx}/test/luooTest/checkForm1',
                        async : false,
                        data : aluooTest,
                        success : function(data) {
                            if (data.name == '1') {
                                flag = true;
                            } else {
                                alert("输入有误，请先更正。");
                                flag = false;
                            }
                        }
                    });
                    if (flag){
                        loading('正在提交，请稍等...');
                        form.submit();
                    }

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
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/test/luooTest/">test列表</a></li>
    <li class="active"><a href="${ctx}/test/luooTest/form?id=${luooTest.id}">test<shiro:hasPermission name="test:luooTest:edit">${not empty luooTest.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="test:luooTest:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="luooTest" action="${ctx}/test/luooTest/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">name：</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">age：</label>
        <div class="controls">
            <form:input path="age" htmlEscape="false" maxlength="2" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">remark：</label>
        <div class="controls">
            <form:input path="remark" htmlEscape="false" maxlength="400" class="input-xlarge "/>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="test:luooTest:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>