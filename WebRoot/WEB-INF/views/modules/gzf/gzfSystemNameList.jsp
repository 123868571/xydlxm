<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统名称管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
			$("#modify").click(function(){
				$("#modifyInfo").show();
				$("#personalInfo").hide();
			})
			
			$("#save").click(function(){
				$("#modifyInfo").hide();
				$("#personalInfo").show();
			}) 				
		})
	</script>
</head>
<body>
	<div id="warning-add">
		<div class="portlet">
			<div class="portlet-title bottom-line" style="border-bottom: #f5f5f5 1px solid;">
				<div class="caption"><i class="icon-edit"></i>系统名称</div>
			</div>
			<form class="form-horizontal" action="#">
				<div id="personalInfo">
					<div class="control-group margin-t-40">
						<div class="controls">
							<img src="${ctxStatic}\media\image\avatar.png" width="100px" height="100px" />
						</div>
					    <h6></h6>
						<div class="controls">
							<span class="help-inline">数源科技</span>
						</div>	
					</div>
					<div class="form-actions save-cancle">
						<button id="modify" class="btn blue margin-r-20"><i class="icon-wrench"></i> 修改</button>
					</div>
				</div>
				<div id="modifyInfo" class="hide">
					<div class="control-group margin-t-40">
						<div class="controls">
							<img src="${ctxStatic}\media\image\avatar.png" width="100px" height="100px" />
						</div>
					    <h6></h6>
						<label class="control-label">系统LOGO</label>
						<div class="controls">
							<input class="m-wrap medium" type="text" />
							<span class="help-inline"></span>
							<button class="btn blue">浏览...</button>
						</div>
					</div>
					<div class="control-group margin-t-20">
						<label class="control-label">系统名称</label>
						<div class="controls">
							<input class="m-wrap medium" type="text" />
							<span class="help-inline"></span>
						</div>
					</div>
					<div class="form-actions save-cancle ">
						<button id="save" class="btn blue margin-r-20 "><i class="icon-ok"></i> 保存</button>
						<button onclick="tab1back();" type="button" class="btn">取消</button>
					</div>
				</div>
			</form>	
		</div>
	</div>
</body>
</html>