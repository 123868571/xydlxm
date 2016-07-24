<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<meta name="decorator" content="blank"/>
	<link href="${ctxStatic}/myMedia/css/login.css" type="text/css" rel="stylesheet" />
	<style type="text/css">

      <%--html,body,table{background-color:#666;width:100%;}--%>
	.login h1{text-align:center;}
	.login .content {border-radius:0;}
	/* #content {position:fixed;top:50%;margin-top:-160px;left:50%;margin-left:-200px;} */
	.control-group {text-align:left;padding-bottom:0;border-bottom:none;}
	.input-icon {height:34px;}
	.input-icon .input-block-level{margin-bottom:0;border-radius:0;padding:6px;}
	.login .content h4 {margin:10px 0;}
	input[type="radio"], input[type="checkbox"] {margin: 3px;}
	.login .content .form-actions .blue {width:100%;background:#4d90fe;margin-top:30px;}
    #bg {width:100%;height:100%;}
	 .form-signin-heading{font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:36px;margin-bottom:20px;color:#0663a2;}

      .header{height:80px;padding-top:20px;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
	  <%--.footer {color:#fff;background:transparent;text-align:center;}--%>
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				}
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	</script>

	<script>
	$(document).ready(function(){
	$("#bg").height($(window).height());
	$("#bg").width($(window).width());
	});
	</script>
</head>
<body>
	<div class="login" id="bg">
		<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
		<div class="header">
			<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
				<label id="loginError" class="error">${message}</label>
			</div>
		</div>

		<div class="content" id="content">
			<div class="logo" style="position:absolute;margin-top:-80px;margin-left:40px;">
				<img src="${ctxStatic}/myMedia/image/login_logo.png" alt="" />
			</div>
			<form id="loginForm" class="form-vertical login-form" action="${ctx}/login" method="post">
				<%--2015-12-24 登录修改--%>
				<h4 class="">&nbsp;</h4>
				<div class="control-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
					<label class="control-label visible-ie8 visible-ie9">用户名</label>
					<div class="controls">
						<div class="input-icon left">
							<i class="icon-user" style="font-size:20px;"></i>
							<input type="text" id="username" name="username" class="input-block-level required" placeholder="请输入用户名" value="${username}">
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label visible-ie8 visible-ie9">密码</label>
					<div class="controls">
						<div class="input-icon left">
							<i class="icon-lock" style="font-size:20px;"></i>
							<input type="password" id="password" name="password" class="input-block-level required" placeholder="请输入密码">
						</div>
						<span class="redFont help-inline">请输入用户名和密码</span>
					</div>
				</div>
				<div class="form-actions">
					<label class="checkbox" for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住我（公共场所慎用）</label>
					<div class="pull-right">
						<a  href="" style="font-size:14px;color: #0362FD;"> 忘记密码</a>
					</div>
					<br/>
					<input class="btn btn-large btn-primary blue" type="submit" value="登 录"/>&nbsp;&nbsp;
				</div>
			</form>
		</div>
		<div class="footer" style="background-color:transparent;color:#fff;text-align:center;">
			Copyright &copy; 2014-${fns:getConfig('copyrightYear')} <a href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> - ${fns:getConfig('version')}
		</div>

	</div>
	<%--<script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script>--%>
</body>
</html>