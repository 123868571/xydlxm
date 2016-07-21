<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>图标选择</title>
	<meta name="decorator" content="blank"/>
    <style type="text/css">
    	.page-header {clear:both;margin:0 20px;padding-top:20px;}
		.the-icons {padding:25px 10px 15px;list-style:none;}
		.the-icons li {float:left;width:22%;line-height:25px;margin:2px 5px;cursor:pointer;}
		.the-icons i {margin:1px 5px;font-size:16px;} .the-icons li:hover {background-color:#efefef;}
        .the-icons li.active {background-color:#0088CC;color:#ffffff;}
        .the-icons li:hover i{font-size:20px;}
    </style>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	$("#icons li").click(function(){
	    		$("#icons li").removeClass("active");
	    		$("#icons li i").removeClass("icon-white");
	    		$(this).addClass("active");
	    		$(this).children("i").addClass("icon-white");
	    		$("#icon").val($(this).text());
	    	});
	    	$("#icons li").each(function(){
	    		if ($(this).text()=="${value}"){
	    			$(this).click();
	    		}
	    	});
	    	$("#icons li").dblclick(function(){
	    		top.$.jBox.getBox().find("button[value='ok']").trigger("click");
	    	});
	    });
    </script>
</head>
<body>
<input type="hidden" id="staffids" value="${value}" />
<div id="icons">
		
	    <h2 class="page-header"> Web 应用的图标</h2>
	    
	    <ul class="the-icons">
	      <li><i class="icon-adjust"></i> icon-adjust</li>
	      <li><i class="icon-asterisk"></i> icon-asterisk</li>
	      <li><i class="icon-ban-circle"></i> icon-ban-circle</li>
	      <li><i class="icon-bar-chart"></i> icon-bar-chart</li>
	      <li><i class="icon-barcode"></i> icon-barcode</li>
	      <li><i class="icon-beaker"></i> icon-beaker</li>
	      <li><i class="icon-beer"></i> icon-beer</li>
	      <li><i class="icon-bell"></i> icon-bell</li>
	      <li><i class="icon-bell-alt"></i> icon-bell-alt</li>
	      <li><i class="icon-bolt"></i> icon-bolt</li>
	      <li><i class="icon-book"></i> icon-book</li>
	      <li><i class="icon-bookmark"></i> icon-bookmark</li>
	      <li><i class="icon-bookmark-empty"></i> icon-bookmark-empty</li>
	      <li><i class="icon-briefcase"></i> icon-briefcase</li>
	      <li><i class="icon-bullhorn"></i> icon-bullhorn</li>
	      <li><i class="icon-calendar"></i> icon-calendar</li>
	      <li><i class="icon-camera"></i> icon-camera</li>
	      <li><i class="icon-camera-retro"></i> icon-camera-retro</li>
	      <li><i class="icon-certificate"></i> icon-certificate</li>
	      <li><i class="icon-check"></i> icon-check</li>
	      <li><i class="icon-check-empty"></i> icon-check-empty</li>
	      <li><i class="icon-circle"></i> icon-circle</li>
	      <li><i class="icon-circle-blank"></i> icon-circle-blank</li>
	      <li><i class="icon-cloud"></i> icon-cloud</li>
	      <li><i class="icon-cloud-download"></i> icon-cloud-download</li>
	      <li><i class="icon-cloud-upload"></i> icon-cloud-upload</li>
	      <li><i class="icon-coffee"></i> icon-coffee</li>
	      <li><i class="icon-cog"></i> icon-cog</li>
	      <li><i class="icon-cogs"></i> icon-cogs</li>
	      <li><i class="icon-comment"></i> icon-comment</li>
	      <li><i class="icon-comment-alt"></i> icon-comment-alt</li>
	      <li><i class="icon-comments"></i> icon-comments</li>
	      <li><i class="icon-comments-alt"></i> icon-comments-alt</li>
	      <li><i class="icon-credit-card"></i> icon-credit-card</li>
	      <li><i class="icon-dashboard"></i> icon-dashboard</li>
	      <li><i class="icon-desktop"></i> icon-desktop</li>
	      <li><i class="icon-download"></i> icon-download</li>
	      <li><i class="icon-download-alt"></i> icon-download-alt</li>
	    </ul>
	<br/><br/>
</div>
</body>