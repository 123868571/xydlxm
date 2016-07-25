<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人员信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
	<style type="text/css">
	a:link {color: #000;text-decoration:none;}
	a:visited {color: #000}
	a:hover {color: #000;text-decoration:underline;}
	a:active {color: #000}
	</style>
</head>
<body>
	<!--BEGIN HOUSE INFORMATION-->
	<div id="houseInformation" class="row-fluid">
		<form:form id="inputForm"  modelAttribute="sysAreaInfo" action="${ctx}/sys/areaInfo/" method="post" class="form-horizontal">
			<div class="row-fluid">
				<!-- 房屋信息 -->
				<div class="span6">
					<div class="portlet">
						<div class="portlet-title" style="border-bottom: #f5f5f5 1px solid;">
							<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-edit"></i>区域信息</div>
						</div>
						<div class="portlet-body">
							<div class="tabbable tabbable-custom tabbable-full-width">
								<div class="tab-pane profile-classic row-fluid">
									<ul class="unstyled span12 ">
										<li class="no-padding no-margin">
											<span class="pad">更新时间:</span>
											<div class="pad">
												<fmt:formatDate value="${gzfHouseInfo.updateDate}" pattern="yyyy-MM-dd"/>
											</div>
										</li>
	
										<li class="no-padding no-margin">
											<span class="pad">房租类型:</span>
											<div class="pad">
											${fns:getDictLabel(gzfHouseInfo.houseType, 'house_type', '')}
											</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad">房租地址:</span>
											<div class="pad">
												${gzfHouseInfo.gzfPalaces.gzfVillage.name}
												${gzfHouseInfo.gzfPalaces.name}
												${gzfHouseInfo.buildNum}号
												${gzfHouseInfo.unit}单元
												${gzfHouseInfo.room}房间
											</div>
										</li>
	
										<li class="no-padding no-margin">
											<span class="pad">套内面积:</span>
											<div class="pad">${gzfHouseInfo.innerArea}</div>
											<span class="pad">m<sup>2</sup></span>
										</li>
	
										<li class="no-padding no-margin">
											<span class="pad">使用面积:</span>
											<div class="pad">${gzfHouseInfo.useArea}</div>
											<span class="pad">m<sup>2</sup></span>
										</li>
	
										<li class="no-padding no-margin">
											<span class="pad">房屋属性:</span>
											<div class="pad">${gzfHouseInfo.gzfHouseProperty.name}</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad">房屋缩略图:</span>
											<div class="pad">
											<img class="thumbnail" src="${gzfHouseInfo.photo}" alt="房屋缩略图"></img></div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad">备注信息:</span>
											<div class="pad">${gzfHouseInfo.remarks}</div>
										</li>
										
										<li>
											<span class="pad padWidth"></span>
											<%-- <shiro:hasPermission name="gzf:gzfHouseInfo:edit">
											<button id="btnSubmit" class="btn blue margin-r-20 " type="submit"><i class="icon-ok"></i>保存</button></shiro:hasPermission> --%>
											<input id="btnCancel" class="btn margin-r-20" type="button" value="返 回" onclick="history.go(-1)"/>
										</li>
										
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
		</form:form>
	</div>
</body>
</html>