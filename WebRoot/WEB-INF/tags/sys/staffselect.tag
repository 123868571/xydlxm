<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="输入框名称"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="输入框值"%>
<i id="${id}Icon" class="icon-${not empty value?value:' hide'}"></i>&nbsp;<label id="${id}IconLabel">${not empty value?value:'无'}</label>&nbsp;
<input id="${id}" name="${name}" type="hidden" value="${value}"/><a id="${id}Button" href="javascript:" class="btn">选择</a>&nbsp;&nbsp;
<script type="text/javascript">
	$("#${id}Button").click(function(){
		top.$.jBox.open("iframe:${ctx}/tag/staffselect?value="+$("#${id}").val(), "选择人员", 700, $(top.document).height()-180, {
            buttons:{"确定":"ok", "清除":"clear", "关闭":true}, submit:function(v, h, f){
                if (v=="ok"){
                	alert("ttt");
                	var staffids = h.find("iframe")[0].contentWindow.$("#staffids").val();
                	$("#${id}Staffids").attr("class", "staffids-"+staffids);
	                $("#${id}StaffidsLabel").text(staffids);
	                $("#${id}").val(staffids);
                }else if (v=="clear"){
	                $("#${id}Staffids").attr("class", "staffids- hide");
	                $("#${id}StaffidsLabel").text("无");
	                $("#${id}").val("");
                }
            }, loaded:function(h){
                $(".jbox-content", top.document).css("overflow-y","hidden");
            }
        });
	});
</script>