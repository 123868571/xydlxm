<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page import="com.paopao.hzgzf.common.persistence.Page" %>
<%@ page import="com.paopao.hzgzf.modules.dlj.entity.ComJdManger" %>
<%@ page import="java.util.*" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'view.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=36OObWxGGteFPvpl5rGQ36aA"></script>
  </head>
  
  <body>
    <div id="allmap"></div>
  </body>
</html>

<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap",{minZoom:6,maxZoom:14}); // 创建Map实例,设置地图允许的最小/大级别
	<c:forEach items="${page.list}" var="comJdManger" varStatus="status">
 	var sContent${status.index + 1} =
 	"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>贴心服务网格员：</h4>" + 
	"<h4 style='margin:0 0 5px 0 red;padding:0.2em 0'>${comJdManger.name}(${comJdManger.phone})</h4>" + 
	"<img style='float:right;margin:4px' id='imgDemo${ status.index + 1}' src='${comJdManger.photo}' width='139' height='104' title='${comJdManger.address}'/>" + 
	"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>${comJdManger.remarks}</p>" + 
	"</div>";
	var point${status.index + 1} = new BMap.Point(${comJdManger.point});
	map.centerAndZoom(point${status.index + 1}, 8);
	var marker${status.index + 1} = new BMap.Marker(point${status.index + 1});  // 创建标注
	var infoWindow${status.index + 1} = new BMap.InfoWindow(sContent${status.index + 1});  // 创建信息窗口对象
	map.addOverlay(marker${status.index + 1});               // 将标注添加到地图中
	marker${status.index + 1}.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	marker${status.index + 1}.addEventListener("click", function(){         
	   this.openInfoWindow(infoWindow${status.index + 1});
	   //图片加载完毕重绘infowindow
	   document.getElementById('imgDemo${status.index + 1}').onload = function (){
		   infoWindow${status.index+1}.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
	   }
	});
	</c:forEach>
	
	setTimeout(function(){
		map.setZoom(18);   
	}, 1000);  //2秒后放大到14级
	map.enableScrollWheelZoom(true);
</script>
