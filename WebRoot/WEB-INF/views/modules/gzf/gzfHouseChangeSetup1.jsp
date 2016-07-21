<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调房管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.form-wizard .step .desc {
			display:inline-block;
			color:#555;
		}
	</style>
</head>
<body>
	<div class="row-fluid">
		<div class="span12 no-padding no-margin">
			<form:form id="inputForm" modelAttribute="gzfHouseInfo" action="${ctx}/gzf/gzfHouseChange/setup2" method="post" class="form-horizontal">
				<div class="form-wizard">
					<div class="navbar steps">
						<div class="navbar-inner">
							<ul class="row-fluid nav nav-pills">
								<li class="span3 active">
									<a href="#tab1" data-toggle="tab" class="step active">
										<span class="number">1</span>
										<span class="desc"><i class="icon-ok"></i>上传同意书</span>   
									</a>
								</li>
								<li class="span3">
									<a href="#tab2" data-toggle="tab" class="step active">
										<span class="number">2</span>
										<span class="desc"><i class="icon-ok"></i>选择调房双方</span>   
									</a>
								</li>
								<li class="span3">
									<a href="#tab3" data-toggle="tab" class="step">
										<span class="number">3</span>
										<span class="desc"><i class="icon-ok"></i>确认调房信息</span>   
									</a>
								</li>
								<li class="span3">
									<a href="#tab4" data-toggle="tab" class="step">
										<span class="number">4</span>
										<span class="desc"><i class="icon-ok"></i>打印调房协议完成调房</span>   
									</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="tab-content">
						<div class="tab-pane active" id="tab1">
							<div class="row-fluid">
								<div class="span12">
									<div class="portlet box blue">
										<div style="border-bottom: 1px solid rgb(245, 245, 245);" class="portlet-title">
											<div style="color: rgb(77, 144, 254);" class="caption"><i class="icon-edit"></i><span class="whiteFont">上传同意单</span></div>
											<div class="tools">
												<a href="javascript:;" class="collapse"></a>
											</div>
										</div>
										<div class="portlet-body form">
											<div class="row-fluid">
												<div class="span12">
													<!-- <div class="control-group">
													    <label class="control-label">同意单:</label>
														<div class="controls">
															<img src="" width="300px"/>	
															<div class="margin-t-20">
																<input type="text" placeholder="" class="m-wrap large" />
																<a class="btn btn-file">浏览</a>
																<a class="btn blue">开始上传</a>
															</div>
														</div>
													</div> -->
													<div class="control-group">
														<label class="control-label">上传同意书:</label>
														<div class="controls">
															<form:hidden id="nameImage" path="remarks" htmlEscape="false" maxlength="255" class="input-xlarge" />
															<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="300" maxHeight="300"/>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-actions clearfix">
								<button id="btnSubmit" class="btn blue margin-r-20 " type="submit" >确认<i class="m-icon-swapright m-icon-white"></i></button>
							</div>	
						</div>
					</div>
				</div>
			</form:form>
			</div>
	</div>	
</body>
</html>