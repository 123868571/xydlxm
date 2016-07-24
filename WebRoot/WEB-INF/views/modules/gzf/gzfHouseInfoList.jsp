<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>房屋信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/gzf/gzfHouseInfo/export");
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
		          $.ajax({url : "${ctx}/gzf/gzfHouseInfo/moredelete",data : {valArr:vals},async : true}).done(function(data){
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
			$("#searchForm").attr("action","${ctx}/gzf/gzfHouseInfo/list");
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
		<form id="importForm" action="${ctx}/gzf/gzfHouseInfo/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/gzf/gzfHouseInfo/import/template">下载模板</a>
		</form>
	</div>
	<div id="infomanage-1-add">
	
	<form:form id="searchForm" modelAttribute="gzfHouseInfo" action="${ctx}/gzf/gzfHouseInfo/" method="post" class="breadcrumb form-search" style="background:#fff">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="row-fluid">
			<div class="span12">
				<div class="dropd margin-r-20 flo-left">
					<label class="flo-left">房屋类型：</label>
					<form:select path="houseType" class="input-medium">
						<form:option value="" label="全部"/>
						<form:options items="${fns:getDictList('house_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				<div class="dropd margin-r-20  flo-left">
					<label class="flo-left">房屋状态：</label>
					<form:select path="houseStat" class="input-medium">
						<form:option value="" label="全部"/>
						<form:options items="${fns:getDictList('house_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				<div class="pull-left">
					<form:input type="text" path="room" htmlEscape="false" class="m-wrap medium" placeholder="请输入房间号查询"/>
					<button type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button>
				</div>

				<div class="portlet-title pull-right">
					<a href="${ctx}/gzf/gzfHouseInfo/form" id="add" class="add-district btn blue" style="color:#fff;"><i class="icon-plus"></i> 新增</a>
					<!-- <button id="modify" class="btn blue"><i class="icon-wrench"></i> 修改</button> -->
					<a id="del" class="btn blue" style="color:#fff;"><i class="icon-trash"></i> 删除</a>
					<input id="btnExport" class="btn blue" type="button" value="导出"/>
					<input id="btnImport" class="btn blue" type="button" value="导入"/>
					<!-- <button id="exportExcel" class="btn green imp-excel"><i class="icon-share"></i> 导出Excel</button> -->
				</div>
			</div>
		</div>
			
			
		<%-- <ul class="ul-form">
			<li>
				<form:input path="houseAll" htmlEscape="false" class="input-medium" placeholder="请输入房间号,住户姓名，手机号查询"/>
			</li>
			<li><label>房屋类型：</label>
				<form:select path="houseType" class="input-medium">
					<form:options items="${fns:getDictList('house_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>房屋状态：</label>
				<form:select path="houseStat" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('house_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" style="margin-left:30px;" type="submit"/><i class="icon-search"></i>搜索</li>
			<li class="btns"><button id="btnSubmit" type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button></li>
			 <li>
           <div class="portlet-title pull-right" style="position:absolute;right:5px;">
				<a href="${ctx}/gzf/gzfHouseInfo/form" id="add" class="add-district btn blue" style="color:#fff;"><i class="icon-plus"></i> 新增</a>
				<!-- <button id="modify" class="btn blue"><i class="icon-wrench"></i> 修改</button> -->
				<a id="del" class="btn blue" style="color:#fff;"><i class="icon-trash"></i> 删除</a>
				<!-- <button id="exportExcel" class="btn green imp-excel"><i class="icon-share"></i> 导出Excel</button> -->
			</div>
			</li>
			<li class="clearfix"></li>
		</ul> --%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;"><input type="checkbox" id="SelectAll"  value="全选" onclick="selectAll();"/></th>
				<th style="text-align:center;">苑名称</th>
				<th style="text-align:center;">楼栋</th>
				<th style="text-align:center;">单元</th>
				<th style="text-align:center;">房间号</th>
				<th style="text-align:center;">建筑面积</th>
				<th style="text-align:center;">房屋类型</th>
				<th style="text-align:center;">房屋状态</th>
				<th style="text-align:center;">住户姓名</th>
				<th style="text-align:center;">手机号</th>
				<shiro:hasPermission name="gzf:gzfHouseInfo:edit"><th style="text-align:center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfHouseInfo">
			<tr>
				<td style="text-align:center;"><input type="checkbox" id="subcheck"  value="${gzfHouseInfo.id}" onclick="setSelectAll();"/></td>
				<td style="text-align:center;"><a href="${ctx}/gzf/gzfHouseInfo/form?id=${gzfHouseInfo.id}">
					${gzfHouseInfo.gzfPalaces.name}
				</a></td>
				<td style="text-align:center;">
					${gzfHouseInfo.buildNum}
				</td>
				<td style="text-align:center;">
					${gzfHouseInfo.unit}
				</td>
				<td style="text-align:center;">
					${gzfHouseInfo.room}
				</td>
				<td style="text-align:center;">
					${gzfHouseInfo.innerArea}
				</td>
				<td style="text-align:center;">
					${fns:getDictLabel(gzfHouseInfo.houseType, 'house_type', '')}
				</td>
				<td style="text-align:center;">
					${fns:getDictLabel(gzfHouseInfo.houseStat, 'house_status', '')}
				</td>
				<td style="text-align:center;">
					${gzfHouseInfo.gzfHousePerson.gzfHouseholdInfo.name}
				</td>
				<td style="text-align:center;">
					${gzfHouseInfo.gzfHousePerson.gzfHouseholdInfo.phone}
				</td>
				<shiro:hasPermission name="gzf:gzfHouseInfo:edit"><td style="text-align:center;">
				<a href="${ctx}/gzf/gzfHouseInfo/information?id=${gzfHouseInfo.id}">历史使用</a>|
				<a href="${ctx}/gzf/gzfHouseInfo/detail?id=${gzfHouseInfo.id}">查看详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>