<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/flexpaper/flexpaper.js" type="text/javascript"></script>
<script src="${ctxStatic}/flexpaper/flexpaper_handlers.js" type="text/javascript"></script>
<style type="text/css" media="screen"> 
			html, body	{ height:100%; }
			body { margin:0; padding:0; overflow:auto; }   
			#flashContent { display:none; }
        </style> 

<title>预览</title>
</head>
<body> 
	       <div id="documentViewer" class="flexpaper_viewer"></div>
 
	        <script type="text/javascript"> 

	        var startDocument = "Paper";

	        $('#documentViewer').FlexPaperViewer(
	                { config : {

	                    SWFFile : '${ctxOutput}${swfpath}',
	                    /* PrintEnabled: true,//是否支持打印
	                    Scale : 1,
	                    ZoomTransition : 'easeOut',
	                    ZoomTime : 0.5,
	                    ZoomInterval : 0.2,
	                    FitPageOnLoad : true,
	                    FitWidthOnLoad : false,
	                    FullScreenAsMaxWindow : false,
	                    ProgressiveLoading : false,
	                    MinZoomSize : 0.2,
	                    MaxZoomSize : 5,
	                    SearchMatchAll : false,
	                    InitViewMode : 'Portrait',
	                    RenderingOrder : 'flash',
	                    StartAtPage : '',

	                    ViewModeToolsVisible : true,
	                    ZoomToolsVisible : true,
	                    NavToolsVisible : true,
	                    CursorToolsVisible : true,
	                    SearchToolsVisible : true,
	                    WMode : 'window',
	                    localeChain: 'en_US' */
	                    
                    	Scale: 1,//缩放比例
                    	ZoomTransition: 'easeOut',//Flexpaper中缩放样式，它使用和Tweener一样的样式，默认参数值为easeOut.其他可选值包括: easenone, easeout, linear, easeoutquad
                    	ZoomTime: 0.5,//从一个缩放比例变为另外一个缩放比例需要花费的时间，该参数值应该为0或更大。
                    	ZoomInterval: 0.2,//缩放比例之间间隔，默认值为0.1，该值为正数。
                    	FitPageOnLoad: false,//初始化的时候自适应页面，与使用工具栏上的适应页面按钮同样的效果。
                    	FitWidthOnLoad: true,//初始化的时候自适应页面宽度，与工具栏上的适应宽度按钮同样的效果。
                    	PrintEnabled: true,//是否支持打印
                    	PrintVisible:true,
                    	FullScreenAsMaxWindow: false,//是否支持全屏,当设置为true的时候，单击全屏按钮会打开一个flexpaper最大化的新窗口而不是全屏，当由于flash播放器因为安全而禁止全屏，而使用flexpaper作为独立的flash播放器的时候设置为true是个优先选择。
                    	ProgressiveLoading: false,//当设置为true的时候，展示文档时不会加载完整个文档，而是逐步加载，但是需要将文档转化为9以上的flash版本（使用pdf2swf的时候使用-T 9 标签）。
                    	MinZoomSize: 0.2,//最小的缩放比例。
                    	MaxZoomSize: 10,//设置最大的缩放比例。
                    	SearchMatchAll: true,//设置为true的时候，单击搜索所有符合条件的地方高亮显示。
                    	InitViewMode: 'Portrait',//启动模式，如”Portrait” or “TwoPage”.
                    	ViewModeToolsVisible: false,//工具栏上是否显示样式选择框(就是显示缩略图或分页显示的工具)
                    	ZoomToolsVisible: true,//工具栏上是否显示缩放工具
                    	NavToolsVisible: false,//工具栏上是否显示导航工具(也就是页码工具)
                    	CursorToolsVisible: true,//工具栏上是否显示光标工具
                    	SearchToolsVisible: true,//工具栏上是否显示搜索
                    	localeChain: 'zh_CN'//语言
	                }}
	        );
	   
	        </script>            
</body>
</html>