<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}</title>
<meta name="decorator" content="blank" />
<c:set var="tabmode"
	value="${empty cookie.tabmode.value ? '1' : cookie.tabmode.value}" />
<c:if test="${tabmode eq '1'}">
	<link rel="Stylesheet"
		href="${ctxStatic}/jerichotab/css/jquery.jerichotab.css" />
    <link rel="Stylesheet"  href="${ctxStatic}/myMedia/css/style.css" />	<script type="text/javascript"
		src="${ctxStatic}/jerichotab/js/jquery.jerichotab.js"></script>
</c:if>
<script type="text/javascript"
	src="${ctxStatic}/myMedia/js/jquery.slimscroll.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/myMedia/js/jquery-ui-1.10.1.custom.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/myMedia/js/app.js"></script>
<style type="text/css">
body {
	background-color: #3d3d3d !important
}

#main {
	padding: 0;
	margin: 0;
}

#header {
	margin: 0;
	position: fixed;
}

#header li {
	font-size: 14px;
	_font-size: 12px;
}

.navbar-inner {
	background-color: #212121 !important;
	border: none !important;
	background-image: none !important;
}

#header .brand {
	font-family: Helvetica, Georgia, Arial, sans-serif, 黑体;
	font-size: 26px;
	padding: 0;
	margin-left:25px;
	margin-top:10px;
	height:30px;
	width:140px;
}
.brand img {
	width:120px;
	height:25px;
	}

#footer {
	margin: 0;
	padding: 14px 20px;
	font-size: 12px;
	text-align: left;
	background: #212121;
	position: fixed;
	bottom: 0
}

#footer,#footer a {
	color: #999;
}

#left1 {
	margin-right: 20px
}

#left1 .collapse {
	position: static;
}

#right1 {
	margin-top:50px;
}

#right1 {
	margin-top: 50px;
}

#userControl>li>a { /*color:#fff;*/
	text-shadow: none;
}

#userControl>li>a:hover,#user #userControl>li.open>a {
	background: transparent;
}
</style>

<script type="text/javascript">
		$(document).ready(function() {

			<%--2015-15-24  添加app对象--%>
			App.init(); // initlayout and core plugins

			
		});
		
	</script>
</head>
<body onload="opener.location.reload()">
	<div id="main">
		<div id="header" class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="brand">
					<%--<span id="productName">${fns:getConfig('productName')}</span>--%>
					<a href="index.html">
					<img src="${ctxStatic}/myMedia/image/logo.png" alt="logo"/>
					</a>
				</div>

				<ul id="userControl" class="nav pull-right">
					<li class="dropdown" id="header_notification_bar">
						<a href="${ctx}/gzf/gzfRepairManagement?maintenanceId=1" target="mainFrame" class="dropdown-toggle">
						<i class="icon-warning-sign"></i>
						<span class="badge">${repairNum}</span>
						</a>
					</li>
				<!-- 2015-12-24  添加顶部警告 -->
					<li id="userInfo" class="dropdown">
						<!-- 2015-12-24  添加顶部个人信息 --> 
						<a class="dropdown-toggle"
						data-toggle="dropdown" href="#" title="个人信息"> <img src=""
							alt=""> 您好, ${fns:getUser().name}&nbsp;<span id="notifyNum"
							class="label label-info hide"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/sys/user/info" target="mainFrame"><i
									class="icon-user"></i>&nbsp; 个人信息</a></li>
							<li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame"><i
									class="icon-lock"></i>&nbsp; 修改密码</a></li>							
						</ul>
					</li>
					<li><a href="${ctx}/logout" title="退出登录">退出</a></li>
					<li>&nbsp;</li>
				</ul>

				<!-- 2015-12-24  删除顶部第一级菜单，左侧的二级菜单为一级菜单 -->
			</div>
		</div>
		<div id="content" class="page-container row-fluid">
			<div id="left1" class="page-sidebar nav-collapse collapse"  style="margin-top:50px;">
				<ul class="page-sidebar-menu hidden-phone hidden-tablet">
					<li>
						<div class="sidebar-toggler hidden-phone"></div> <br />
					</li>
					<li class="first"><a href="${ctx}/gzf/main/index" target="mainFrame"> <i class="icon-home"></i>
							<span class="title">概况总览</span>
					</a></li>
					
					<c:set var="menuList" value="${fns:getMenuList()}" />
					<c:set var="firstMenu" value="true" />
					<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
						<c:if
							test="${menu.parent.id eq (not empty param.parentId ? param.parentId:'1')&&menu.isShow eq '1'}">
							<c:forEach items="${menuList}" var="menu2">
								<c:if test="${menu2.parent.id eq menu.id&&menu2.isShow eq '1'}">

									<li class=""><a href="javascript:;"><i
											class="${not empty menu2.icon ? menu2.icon : 'icon-list'}"></i>
											<span class="title">${menu2.name}</span>
											<span class="arrow "></span>
									</a>
										<ul class="sub-menu">
											<c:forEach items="${menuList}" var="menu3">
												<c:if
													test="${menu3.parent.id eq menu2.id&&menu3.isShow eq '1'}">
													<li><a
														href="${fn:indexOf(menu3.href, '://') eq -1 ? ctx : ''}${not empty menu3.href ? menu3.href : '/404'}"
														target="${not empty menu3.target ? menu3.target : 'mainFrame'}">${menu3.name}</a></li>
												</c:if>
												
											</c:forEach>
										</ul></li>
									<c:set var="firstMenu" value="false" />
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</ul>

				<!-- 2015-12-24  一级菜单置于此 -->
				<!--/.nav-collapse -->

				<%--  <iframe id="menuFrame" name="menuFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe> --%>
			</div>
			<div id="right1" class="page-content">
				<div class="container-fluid">

					<%--2015-12-24 添加面包屑--%>
					<div class="row-fluid">

						<div class="span12">
							<h6></h6>
							<ul class="breadcrumb pos-rel">
								<li><i class="icon-signout"></i> <a id="home" href="#"></a><i class="icon-angle-right"></i></li>
								<li><a id="guide_infotitle" href=""><span id="menuName2"></span></a> <i class="icon-angle-right"></i></li>
								<li><a id="guide_district_manage"><span id="menuName3"></span></a></li>
							 </ul>
						</div>
					</div>
					<iframe src="${ctx}/gzf/main/index" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="100%" height="100%" id="mainFrame" name="mainFrame" onLoad="iFrameHeight()" ></iframe>

				</div>
			</div>
		</div>
		<div id="footer" class="row-fluid" style="text-align:center;">
			Copyright &copy; 2015-${fns:getConfig('copyrightYear')}
			${fns:getConfig('productName')} - ${fns:getConfig('version')}
		</div>
	</div>
	<script>
	 /* $(function(){  
	    var $uli = $(".page-sidebar-menu li"),
	    	$subMenu = $uli.find("ul.sub-menu"),
	   		$subMenuli = $(".page-sidebar-menu li.first");
	    
		    $uli.on("click",function(){
		    	console.log($(this))
		    	if($(this).hasClass("first")){
		    		$subMenu.css("display","none");
			    	$uli.removeClass("open");
		    		$subMenuli.addClass("active");
		    		var menuText = $('.open a .title').text();
		            $subMenua = $(".page-sidebar-menu .open a").find(".title");
		            if($subMenua.length > 0){
			            console.log($subMenua[0].textContent);
			            $("#menuName2").text($subMenua[0].textContent);
		            }
		    	} else {
		    		$subMenuli.removeClass("active");
		    	}
		    });
		})  */
		var curr_li = undefined;
        $(function () {
 
            var $uli = $(".page-sidebar-menu").find("li:first").nextAll();
          
            $subMenu = $uli.find("ul.sub-menu")
            $subMenuli = $("ul.sub-menu").find("li");
            
            $uli.each(function () {
           
                $(this).unbind().click(function (e) {
                 
                    if (curr_li != undefined) {
                        curr_li.removeClass("active");
                        curr_li.removeClass("open");
                        curr_li.find("ul").hide();
                        curr_li.find('.arrow').removeClass("open");
                    }
                    $subMenu.css("display", "none");
                    $(this).addClass("open");
                    $(this).addClass("active");
                    $(this).find('.arrow').addClass("open");
                   
                    curr_li = $(this);
                    curr_li.find("ul").show();
                    var menuText = $('.start,.active,.open a .title').text();
                    $subMenua = $("ul li.active a").find(".title");
                   
                    if ($subMenua.length > 0) {
                    	 $("#menuName3").text("");
                        $("#menuName2").text($subMenua[0].textContent);
                    }
                    else
                    	{
                    	 
                    	
                    	}
                    
                    e.stopPropagation();
                });
            })

            $subMenuli.on("click", function (e) {
                $subMenuli.removeClass("active")
                $(this).addClass("active")
                $("#menuName3").text(this.textContent);
                e.stopPropagation();
            })
        })
	</script>

	
	<script>
		/* var collapse = $(window.frames["mainFrame"].document).find(".collapse"); */
		$(window.frames["mainFrame"].document).find(".collapse").on('click', function (e) {
	        e.preventDefault();
	        	console.log($this);
	            var el = jQuery(this).closest(".portlet").children(".portlet-body");
	            if (jQuery(this).hasClass("collapse")) {
	                jQuery(this).removeClass("collapse").addClass("expand");
	                el.slideUp(200);
	            } else {
	                jQuery(this).removeClass("expand").addClass("collapse");
	                el.slideDown(200);
	            }
	    });
	</script>
	
	<script>
		 function iFrameHeight() {
	        var ifm= document.getElementById("mainFrame");
	        var subWeb = document.frames ? document.frames["mainFrame"].document : ifm.contentDocument;
		            if(ifm != null && subWeb != null) {
		            	var height = subWeb.body.scrollHeight;
		            ifm.height = height;
		            $("#right1").height(height+48+50+80);
		            }
		    }

	</script>
	 <script type="text/javascript">
        var EV_IDCARD_INIT = 'lvbutc_idcard_init';
        var EV_IDCARD_INIT_RESULT = 'lvbutc_idcard_init_result';
        var EV_IDCARD_SCAN = 'lvbutc_idcard_scan';
        var EV_IDCARD_SCAN_RESULT = 'lvbutc_idcard_scan_result';
        var isScannerReady = false;
        var link_callback = null;
        
        function link(callback) {
            link_callback = callback;
            var ev = new Event(EV_IDCARD_INIT);
            document.dispatchEvent(ev);
        }
        document.addEventListener(EV_IDCARD_INIT_RESULT,
                function (ev) {
                    if (link_callback != null)
                        link_callback(ev);
                }, false);

        function enableScanner() {
            isScannerReady = true;
        }

        var scan_callback = null;
        function scan(callback) {
            if (!isScannerReady) {
                alert('未连接身份证扫描器');
                return;
            }
            scan_callback = callback;
            var ev = new Event(EV_IDCARD_SCAN);
            document.dispatchEvent(ev);
        }
        document.addEventListener(EV_IDCARD_SCAN_RESULT,
                function (ev) {
                    if (scan_callback != null)
                        scan_callback(ev);
                }, false);
    </script>
</body>
</html>