<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>小区管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//删除 
		    $("#del").click(function(){ 
		    alert("11");
		        var valArr = new Array; 
		        $("input[type='checkbox'][id='subcheck']:checked").each(function(i){ 
		            valArr[i] = $(this).val(); 
		        }); 
		        var vals = valArr.join(','); 
		          $.ajax({url : "${ctx}/gzf/gzfVillage/moredelete",data : {valArr:vals},async : true}).done(function(data){
						if("success"==data){
							/* $("#message").text("删除成功"); */
							alert("删除成功");
							location.reload([true]);
						}else if("false" == data){
							/* $("#message").text("请勾选需要删除项"); */
							alert("请勾选需要删除项");
						}else{
							/* $("#message").text("部分删除失败"); */
							alert("部分删除失败"); 
							location.reload([true]);
						}
					});
		    }); 
			//修改
		    $("#modify").click(function(){
		    	var checkedsub = $("input[type='checkbox'][id='subcheck']:checked").length; //获取选中的subcheck的个数
		    	if(checkedsub > 1 || checkedsub == 0){
		    		return alertx('请选择一条信息修改');
		    	}
		        var valArr = new Array; 
		        $("input[type='checkbox'][id='subcheck']:checked").each(function(i){ 
		            valArr[i] = $(this).val(); 
		        }); 
		        var vals = valArr.join(','); 
		        location.href = "gzf/gzfVillage/form?id="+vals;
		    }); 
		});
		//复选框事件
		//全选、取消全选的事件
		function selectAll(){
			if ($("#SelectAll").attr("checked")) {
				$(":checkbox").attr("checked", true);
			} else {
				$(":checkbox").attr("checked", false);
			}
		}
		//子复选框的事件
		function setSelectAll(){
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
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<style type="text/css">
	a:link {color: #000;text-decoration:none;}
	a:visited {color: #000}
	a:hover {color: #000;text-decoration:underline;}
	a:active {color: #000}
	</style>
</head>
<body>
	<form:form id="searchForm" modelAttribute="gzfVillage" action="${ctx}/gzf/gzfVillage/" method="post" class="breadcrumb form-search" style="background:#fff">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form" style="position:relative;">
			<li>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium" placeholder="请输入小区名称查询"/>
			</li>
			<li class="btns"><button type="submit" class="btn  blue margin-r-20 "><i class="icon-search"></i> 搜索</button></li>
			<li>
			<div class="portlet-title pull-right" style="position:absolute;right:5px;">
				
				<a href="${ctx}/gzf/gzfVillage/form" id="add" class="add-district  btn blue" style="color:#fff;"><i class="icon-plus"></i> 新增</a>
				<!-- <button id="modify" class="btn blue"><i class="icon-wrench"></i> 修改</button> -->
				<a  id="del" class="btn blue"><i class="icon-trash"></i> 删除</a>
				<!-- <button id="exportExcel" class="btn green imp-excel"><i class="icon-share"></i> 导出Excel</button> -->
			</div>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message  content="${message}"/>
	<font color="red"><label id="message"></label></font>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" >
		<thead>
			<tr>
				<th style="text-align:center;"><input type="checkbox" id="SelectAll"  value="全选" onclick="selectAll();"/></th>
				<th style="text-align:center;">名称</th>
				<th style="text-align:center;">建筑年代</th>
				<th style="text-align:center;">归属单位</th>
				<th style="text-align:center;">施工单位</th>
				<th style="text-align:center;">坐落位置</th>
				<shiro:hasPermission name="gzf:gzfVillage:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody style="text-align:center;">
		<c:forEach items="${page.list}" var="gzfVillage">
			<tr style="text-align:center;">
				<td style="text-align:center;"><input type="checkbox" id="subcheck"  value="${gzfVillage.id}" onclick="setSelectAll();"/></td>
				<td style="text-align:center;"><a href="${ctx}/gzf/gzfVillage/form?id=${gzfVillage.id}">
					${gzfVillage.name}
				</a></td>
				<td style="text-align:center;">
					<fmt:formatDate value="${gzfVillage.buildYear}" pattern="yyyy年MM月"/>
				</td>
				<td style="text-align:center;">
					${gzfVillage.belongCompany}
				</td>
				<td style="text-align:center;">
					${gzfVillage.constructCompany}
				</td>
				<td style="text-align:center;">
					${gzfVillage.location}
				</td>
				<shiro:hasPermission name="gzf:gzfVillage:edit"><td style="text-align:center;">
					<a href="${ctx}/gzf/gzfPalaces?gzfVillageId=${gzfVillage.id}">基础信息管理</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>