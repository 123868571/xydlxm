<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人员信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			
		});
		/* function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        } */
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
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
	<!--BEGIN HOUSE INFORMATION-->
	<div id="houseInformation" class="row-fluid">
		<form:form id="inputForm"  modelAttribute="gzfHouseInfo" action="${ctx}/gzf/gzfHouseInfo/" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
			<div class="row-fluid">
				<!-- 房屋信息 -->
				<div class="span6">
					<div class="portlet">
						<div class="portlet-title" style="border-bottom: #f5f5f5 1px solid;">
							<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-edit"></i>房屋信息</div>
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
				<!-- 住户信息 -->
				<div class="span6">
					<div class="portlet">
						<div class="portlet-title" style="border-bottom: #f5f5f5 1px solid;">
							<div class="caption" style="color: rgb(77, 144, 254);"><i class="icon-list"></i>住户信息</div>
						</div>
						<div class="portlet-body">
							<div class="tabbable tabbable-custom tabbable-full-width">
								<div class="tab-pane profile-classic row-fluid">
									<ul class="unstyled span12 ">
										<li class="no-padding no-margin">
											<span class="pad">姓名:</span>
											<div class="pad">${gzfHouseholdInfo.name}</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad">性别:</span>
											<div class="pad">
											${fns:getDictLabel(gzfHouseholdInfo.sex, 'sex', '')}
											</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad">年龄:</span>
											<div class="pad">${gzfHouseholdInfo.age}</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad">电话:</span>
											<div class="pad">${gzfHouseholdInfo.phone}</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad">身份证号:</span>
											<div class="pad">${gzfHouseholdInfo.cardid}</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad">学历:</span>
											<div class="pad">
											${fns:getDictLabel(gzfHouseholdInfo.education, 'education', '')}
											</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad">籍贯:</span>
											<div class="pad">
												${gzfHouseholdInfo.nativeProvince}
												${gzfHouseholdInfo.nativeCity}
												${gzfHouseholdInfo.nativeArea}
												${gzfHouseholdInfo.nativeAddress}
											</div>
										</li>
	
										<li class="no-padding no-margin">
											<span class="pad">工作单位:</span>
											<div class="pad">${gzfHouseholdInfo.company}</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad">家庭住址:</span>
											<div class="pad">
												${gzfHouseholdInfo.province}
												${gzfHouseholdInfo.city}
												${gzfHouseholdInfo.area}
												${gzfHouseholdInfo.address}
											</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad">紧急联系人:</span>
											<div class="pad">${gzfHouseholdInfo.emergencyContact}</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad">紧急联系人电话:</span>
											<div class="pad">${gzfHouseholdInfo.emergencyPhone}</div>
										</li>
										<li class="no-padding no-margin">
											<span class="pad">备注信息:</span>
											<div class="pad">${gzfHouseholdInfo.remarks}</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<!--BEGIN HOUSE INFORMATION-->
	<form:form id="searchForm" modelAttribute="gzfMaintenance" action="${ctx}/gzf/gzfMaintenance/" method="post" class="form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>维修状态：</label>
				<form:select path="maintenanceId" class="input-xlarge required" style="width:80px;">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('repair')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>报修项目：</label>
				<form:select id="gzfRepairManagerProject.gzfRepairProjectId" path="gzfRepairManagerProject.gzfRepairProjectId" class="input-xlarge" style="width:80px;">
					<form:option value="" label="全部"/>
					<form:options items="${repairTypeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>报修时间：</label>
				<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
				<input name="startDate" type="text"" maxlength="20" class="m-wrap m-ctrl-medium date-picker Wdate"
					value="<fmt:formatDate value="${gzfMaintenance.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width: 100px;"/>
					<span class="add-on"><i class="icon-calendar"></i></span>
					</div>&nbsp;-&nbsp;
					<div style="width:140px;" class="input-append date date-picker" data-date="2015-11-15" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
					<input name="endDate" type="text" maxlength="20" class="m-wrap m-ctrl-medium date-picker Wdate"
					value="<fmt:formatDate value="${gzfMaintenance.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" style="width: 100px;"/>
					<span class="add-on"><i class="icon-calendar"></i></span>
					</div>
			</li>
			<li style="float:right"><label></label>
				<form:input path="allSelect" htmlEscape="false" maxlength="64" class="input-medium" placeholder="请输入房间号、手机号查询" style="padding:5px 4px"/>
				<button id="btnSubmit" type="submit" class="btn blue" style="margin-left:-4px;"><i class="icon-search"></i>搜索</button>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">地址</th>
				<th style="text-align:center;">报修项目</th>
				<th style="text-align:center;">报修时间</th>
				<th style="text-align:center;">报修人</th>
				<th style="text-align:center;">报修人电话</th>
				<th style="text-align:center;">故障现象</th>
				<th style="text-align:center;">维修状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzfMaintenance">
			<tr>
				<td style="text-align:center;">
					${gzfMaintenance.gzfHouseInfo.gzfPalaces.gzfVillage.name}
					${gzfMaintenance.gzfHouseInfo.gzfPalaces.name}
					${gzfMaintenance.gzfHouseInfo.buildNum}幢
					${gzfMaintenance.gzfHouseInfo.unit}单元
					${gzfMaintenance.gzfHouseInfo.room}室
				</td>
				<td style="text-align:center;">
					${gzfMaintenance.gzfRepairProject.name}
				</td>
				<td style="text-align:center;">
					<fmt:formatDate value="${gzfMaintenance.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="text-align:center;">
					${gzfMaintenance.name}
				</td>
				<td style="text-align:center;">
					${gzfMaintenance.phone}
				</td>
				<td style="text-align:center;">
					${gzfMaintenance.content}
				</td>
				<td style="text-align:center;">
					${fns:getDictLabel(gzfMaintenance.maintenanceId, 'repair', '待维修')}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>