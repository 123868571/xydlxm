<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡查设备</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<div id="deviceAddlist" >
		<div class="portlet-body">
			<div class="pull-left">
				<input type="text" class="m-wrap medium" placeholder="请输入项目名称查询">
				<button type="button" class="btn blue"><i class="icon-search"></i> 搜索</button>
			</div>
			<div class="portlet-title pull-right">
				<a href="${ctx}/gzf/gzfInspectionAdd" id="add" class="add-district btn blue" style="color:#fff;"><i class="icon-plus"></i> 新增</a>
			</div>								
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th style="width:8px;">
							<input type="checkbox" class="group-checkable" data-set="" />
						</th>
						<th>操作日期</th>
						<th>项目名称</th>
						<th>操作人</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr class="odd gradeX">
						<td>
							<input type="checkbox" class="group-checkable" data-set="" />
						</td>
						<td>2015-12-13 16:02</td>
						<td>漓江山水花园如意苑</td>
						<td>马丽江</td>
						<td>
							<a class="inspectinfo" href="">修改</a>|
							<a class="inspectinfo" href="">删除</a>
						</td>
					</tr>
					<tr class="odd gradeX">
						<td>
							<input type="checkbox" class="group-checkable" data-set="" />
						</td>
						<td>2015-12-13 16:02</td>
						<td>漓江山水花园如意苑</td>
						<td>马丽江</td>
						<td>
							<a class="inspectinfo" href="">修改</a>|
							<a class="inspectinfo" href="">删除</a>
						</td>
					</tr>
					<tr class="odd gradeX">
						<td>
							<input type="checkbox" class="group-checkable" data-set="" />
						</td>
						<td>2015-12-13 16:02</td>
						<td>漓江山水花园如意苑</td>
						<td>马丽江</td>
						<td>
							<a class="inspectinfo" href="">修改</a>|
							<a class="inspectinfo" href="">删除</a>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="left">显示<span id="">1</span>-<span id="">3</span>，总共：<span id="">3</span> 行</div>
		</div>
	</div>
	<!-- IDC MOVIE-->

</body>
</html>