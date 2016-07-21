<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>房源审核</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//删除 
		    $("#allsubmit").click(function(){ 
		        var valArr = new Array; 
		        $("input[type='checkbox'][id='subcheck']:checked").each(function(i){ 
		            valArr[i] = $(this).val(); 
		        }); 
		        var vals = valArr.join(','); 
		        $.ajax({url : "${ctx}/gzf/gzfHousePersonAudit/moreReview",data : {valArr:vals},async : true}).done(function(data){
					if("success"==data){
						/* $("#message").text("删除成功"); */
						alert("审核成功");
						 location.reload([true]);
					}else if("false" == data){
						/* $("#message").text("请勾选需要删除项"); */
						alert("请勾选需要审核项");
					}else{
						/* $("#message").text("部分删除失败"); */
						alert("部分审核失败"); 
					}
				});
		    });
			//修改
		    $("#modify").click(function(){
		    	var checkedsub = $("input[type='checkbox'][id='subcheck']:checked").length; //获取选中的subcheck的个数
		    	if(checkedsub > 1 || checkedsub == 0){
		    		return alertx('请选择一条信息修改');
		    	}
		        var valArr = new Array; 
		        $("input[type='checkbox'][id='subcheck']:checked").each(function(i){ 
		            valArr[i] = $(this).val(); 
		        }); 
		        var vals = valArr.join(','); 
		        location.href = "gzf/gzfVillage/form?id="+vals;
		    }); 
		});
		//复选框事件
		//全选、取消全选的事件
		function selectAll(){
			if ($("#SelectAll").attr("checked")) {
				$(":checkbox").attr("checked", true);
			} else {
				$(":checkbox").attr("checked", false);
			}
		}
		//子复选框的事件
		function setSelectAll(){
			//当没有选中某个子复选框时，SelectAll取消选中
			if (!$("#subcheck").checked) {
				$("#SelectAll").attr("checked", false);
			}
			var chsub = $("input[type='checkbox'][id='subcheck']").length; //获取subcheck的个数
			var checkedsub = $("input[type='checkbox'][id='subcheck']:checked").length; //获取选中的subcheck的个数
			if (checkedsub == chsub) {
				$("#SelectAll").attr("checked", true);
			}
		}
		
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
	<form:form id="searchForm" modelAttribute="gzfHousePerson"  action="${ctx}/gzf/gzfHousePersonAudit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<div class="row-fluid">
			<div class="span12">
				<div class="dropd margin-r-20  flo-left">
					<input id="allsubmit" class="btn blue" type="button" value="全部通过"/>
				</div>
				<div class="dropd margin-r-20 flo-left">
					<label class="flo-left">审核状态：</label>
					<form:select path="review" class="input-medium">
						<form:option label="全部" value=""/>
						<form:options items="${fns:getDictList('house_person_review')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				<div class="pull-left">
					<form:input path="allSelect" maxlength="100" class="input-medium" placeholder="请输入小区，住户名称，电话查询"/>
					<button type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button>
				</div>
			</div>
		</div>
		
		<%-- <ul class="ul-form" style="position:relative;">
			
			<li>
			<input id="allsubmit" class="btn blue" type="button" value="全部通过"/>
			</li>
			<li><label>审核状态：</label>
				<form:select path="review" class="input-medium">
					<form:option label="全部" value=""/>
					<form:options items="${fns:getDictList('house_person_review')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<form:input path="allSelect" maxlength="100" class="input-medium" placeholder="请输入小区，住户名称，电话查询"/>
			</li>
			<li><label>审核查询状态：</label>
				<form:select path="" class="input-medium">
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><button type="submit" class="btn blue"><i class="icon-search"></i> 搜索</button></li>
			<li class="clearfix"></li>
		</ul> --%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" >
		<thead>
			<tr>
				<th style="text-align:center;"><input type="checkbox" id="SelectAll"  value="全选" onclick="selectAll();"/></th>
				<th style="text-align:center;">姓名</th>
				<th style="text-align:center;">电话</th>
				<th style="text-align:center;">学历</th>
				<th style="text-align:center;">户口属性</th>
				<th style="text-align:center;">房卡数</th>
				<th style="text-align:center;">单位所属行业</th>
				<th style="text-align:center;">小区信息</th>
				<th style="text-align:center;">审核状态</th>
				<th style="text-align:center;">审核时间</th>
				<th style="text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody style="text-align:center;">
		<c:forEach items="${page.list}" var="gzfHousePerson">
		<tr>
			<td style="text-align:center;"><input type="checkbox" id="subcheck"  value="${gzfHousePerson.id}" onclick="setSelectAll();"/></td>
			<td style="text-align:center;">${gzfHousePerson.gzfHouseholdInfo.name}</td>
			<td style="text-align:center;">${gzfHousePerson.gzfHouseholdInfo.phone}</td>
			<td style="text-align:center;">
			${fns:getDictLabel(gzfHousePerson.gzfHouseholdInfo.education, 'education', '')}
			</td>
			<td style="text-align:center;">${fns:getDictLabel(gzfHousePerson.gzfHouseholdInfo.household, 'household', '')}</td>
			<td style="text-align:center;">${gzfHousePerson.gzfHouseholdInfo.cardnum}
			</td>
			<td style="text-align:center;">${fns:getDictLabel(gzfHousePerson.gzfHouseholdInfo.industry, 'industry', '')}
			</td>
			<td style="text-align:center;">${gzfHousePerson.gzfHouseInfo.gzfPalaces.gzfVillage.name}
				${gzfHousePerson.gzfHouseInfo.gzfPalaces.name}
				${gzfHousePerson.gzfHouseInfo.buildNum}号
				${gzfHousePerson.gzfHouseInfo.unit}单元
				${gzfHousePerson.gzfHouseInfo.room}房间</td>
			<td style="text-align:center;">
			${fns:getDictLabel(gzfHousePerson.review, 'house_person_review', '')}
			</td>
			<td style="text-align:center;">
			<fmt:formatDate value="${gzfHousePerson.updateDate}" pattern="yyyy-MM-dd"/>
			</td>
			<td style="text-align:center;">
			<a href="${ctx}/gzf/gzfHousePersonAudit/save?id=${gzfHousePerson.id}&isReview=1">审核通过</a>|
			<a href="${ctx}/gzf/gzfHousePersonAudit/audit?id=${gzfHousePerson.id}&isReview=2">审核驳回</a>|
			<a href="${ctx}/gzf/gzfHousePersonAudit/form?id=${gzfHousePerson.id}">查看详情</a>
			</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>