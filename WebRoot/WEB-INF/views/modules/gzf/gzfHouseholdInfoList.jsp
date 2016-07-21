<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人员信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/gzf/gzfHouseholdInfo/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			//删除 
		    $("#del").click(function(){ 
		        var valArr = new Array; 
		        $("input[type='checkbox'][id='subcheck']:checked").each(function(i){ 
		            valArr[i] = $(this).val(); 
		        }); 
		        var vals = valArr.join(','); 
		          $.ajax({url : "${ctx}/gzf/gzfHouseholdInfo/moredelete",data : {valArr:vals},async : true}).done(function(data){
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
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/gzf/gzfHouseholdInfo/list");
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
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/gzf/gzfHouseholdInfo/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/gzf/gzfHouseholdInfo/import/template">下载模板</a>
		</form>
	</div>
	<form:form id="searchForm" modelAttribute="gzfHouseholdInfo" action="${ctx}/gzf/gzfHouseholdInfo/" method="post" class=" form-search" style="background:#fff;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="row-fluid">
			<div class="span12">
				<div class="dropd margin-r-20 flo-left">
					<label class="flo-left">绑定状态：</label>
					<form:select path="gzfHousePerson.bind" class="input-medium">
						<form:option value="" label="全部"/>
						<form:options items="${fns:getDictList('house_person_bind')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				<div class="dropd margin-r-20  flo-left">
					<label class="flo-left">审核状态：</label>
					<form:select path="gzfHousePerson.review" class="input-medium">
						<form:option value="" label="全部"/>
						<form:options items="${fns:getDictList('house_person_review')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				<div class="pull-left">
					<form:input path="allName" htmlEscape="false" maxlength="64" class="input-medium" placeholder="请输入人名、手机号查询"/>
					<button type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button>
				</div>

				<div class="portlet-title pull-right">
					<a href="${ctx}/gzf/gzfHouseholdInfo/form" id="add" class="add-district btn blue" style="color:#fff;"><i class="icon-plus"></i> 新增</a>
					<!-- <button id="modify" class="btn blue"><i class="icon-wrench"></i> 修改</button> -->
					<a id="del" class="btn blue" style="color:#fff;"><i class="icon-trash"></i> 删除</a>
					<input id="btnExport" class="btn blue" type="button" value="导出"/>
					<input id="btnImport" class="btn blue" type="button" value="导入"/>
					<!-- <button id="exportExcel" class="btn green imp-excel"><i class="icon-share"></i> 导出Excel</button> -->
				</div>
			</div>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th><input type="checkbox" id="SelectAll"  value="全选" onclick="selectAll();"/></th>
				<th style="text-align:center;">姓名</th>
				<th style="text-align:center;">性别</th>
				<th style="text-align:center;">工作单位</th>
				<th style="text-align:center;">学历</th>
				<th style="text-align:center;">联系电话</th>
				<th style="text-align:center;">身份证号</th>
				<th style="text-align:center;">小区信息</th>
				<th style="text-align:center;">审核状态</th>
				<th style="text-align:center;">绑定状态</th>
				<shiro:hasPermission name="gzf:gzfHouseholdInfo:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfHouseholdInfo">
			<tr><td><input type="checkbox" id="subcheck"  value="${gzfHouseholdInfo.id}" onclick="setSelectAll();"/></td>
				<td style="text-align:center;"><a href="${ctx}/gzf/gzfHouseholdInfo/form?id=${gzfHouseholdInfo.id}">
					${gzfHouseholdInfo.name}
				</a></td>
				<td style="text-align:center;">
					${fns:getDictLabel(gzfHouseholdInfo.sex, 'sex', '')}
				</td>
				<td style="text-align:center;">
					${gzfHouseholdInfo.company}
				</td>
				<td style="text-align:center;">
					${fns:getDictLabel(gzfHouseholdInfo.education, 'education', '')}
				</td>
				<td style="text-align:center;">
					${gzfHouseholdInfo.phone}
				</td>
				<td style="text-align:center;">
					${gzfHouseholdInfo.cardid}
				</td>

				<%-- <shiro:hasPermission name="gzf:gzfHouseholdInfo:edit"><td style="text-align:center;">
    				<a href="${ctx}/gzf/gzfHouseholdInfo/form?id=${gzfHouseholdInfo.id}">修改</a>
					<a href="${ctx}/gzf/gzfHouseholdInfo/delete?id=${gzfHouseholdInfo.id}" onclick="return confirmx('确认要删除该人员信息吗？', this.href)">删除</a>
 --%>
				<td style="text-align:center;">
					${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.gzfPalaces.gzfVillage.name}
					${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.gzfPalaces.name}
					${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.buildNum}号
					${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.unit}单元
					${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.room}房间
				</td>
				<td style="text-align:center;">
				${fns:getDictLabel(gzfHouseholdInfo.gzfHousePerson.review, 'house_person_review', '')}
				</td>
				<td style="text-align:center;">
				${fns:getDictLabel(gzfHouseholdInfo.gzfHousePerson.bind, 'house_person_bind', '')}
				</td>
				<shiro:hasPermission name="gzf:gzfHouseholdInfo:edit"><td style="text-align:center;">
				<c:choose>
			   		<c:when test="${empty  gzfHouseholdInfo.gzfHousePerson.bind}"> 
			   			<a href="${ctx}/gzf/gzfHousePerson/form?gzfHouseholdInfoId=${gzfHouseholdInfo.id}">绑定房源</a>| 
			   		</c:when>
			   		<c:otherwise> 
			    		<font color="#63696F"> 绑定房源</font>| 
			   		</c:otherwise>
				</c:choose>
				<c:choose>
			   		<c:when test="${gzfHouseholdInfo.gzfHousePerson.review == 1 || empty gzfHouseholdInfo.gzfHousePerson.review}">
			   			<font color="#63696F">修改房源 </font>|
			   		</c:when>
			   		<c:otherwise> 
			    		<a href="${ctx}/gzf/gzfHousePerson/formChange?gzfHouseholdInfoId=${gzfHouseholdInfo.id}&id=${gzfHouseholdInfo.gzfHousePerson.id}">修改房源 </a>|
			   		</c:otherwise>
				</c:choose>
				<a href="${ctx}/gzf/gzfHouseholdInfo/detail?id=${gzfHouseholdInfo.id}"><b>租房历史</b></a>|
				<a href="${ctx}/gzf/gzfHousePerson/information?gzfHouseholdInfoId=${gzfHouseholdInfo.id}&gzfHouseInfoId=${gzfHouseholdInfo.gzfHousePerson.gzfHouseInfo.id}">查看详情</a><%-- |	
    			<c:choose>
			   		<c:when test="${empty  gzfHouseholdInfo.gzfHousePerson.bind}"> 
			   			<font color="#63696F">住房申请表 </font>| 
			   		</c:when>
			   		<c:otherwise> 
			    		<a href="${ctx}/gzf/gzfHouseApplyForm/apply/0?housePersonId=${gzfHouseholdInfo.gzfHousePerson.id}"><b>住房申请表</b></a>
			   		</c:otherwise>
				</c:choose> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>