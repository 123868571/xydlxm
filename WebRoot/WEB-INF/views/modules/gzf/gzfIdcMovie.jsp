<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>身份证扫描示例</title>
<meta name="decorator" content="default" />
</head>
<body>
	<h3>身份证扫描示例</h3>
	<span id='scanner_status'>正在连接身份证扫描器...</span>
	<p>
		<input id="scan" type="button" style="width: 120;" value="扫描"
			onclick="showcover('1')" />
	</p>
	<div id='idCardInfo' />
	<script type="text/javascript">
		var $parent = parent;
		$(function() {
			$parent.link(link_callback);
			//连接身份证扫描仪
			function link_callback(ev) {
				var r = ev.detail;
				switch (r.result) {
				case "ok":
					$parent.enableScanner();
					$("#scanner_status").text("已连接身份证扫描仪");
					break;
				case "error":
					$("#scanner_status").text("未检测到身份证扫描仪");
					break;
				default:
					$("#scanner_status").text("未检测到身份证扫描仪");
					break;
				}
			}
		});
		function showIdCard(idCard) {
			idCardInfo.innerHTML = "<table>" + "<tr><td>号码</td><td>" + idCard.number + "</td></tr>" + "<tr><td>地区</td><td>" + idCard.region + "</td></tr>" + "<tr><td>姓名</td><td>" + idCard.name + "</td></tr>" + "<tr><td>性别</td><td>" + idCard.sex
					+ "</td></tr>" + "<tr><td>出生日期</td><td>" + idCard.dateOfBirth + "</td></tr>" + "<tr><td>民族</td><td>" + idCard.ethnicGroup + "</td></tr>" + "<tr><td>地址</td><td>" + idCard.address + "</td></tr>" + "<tr><td>发证机关</td><td>"
					+ idCard.issuingAuthority + "</td></tr>" + "<tr><td>有效期</td><td>" + idCard.validFrom + "-" + idCard.validTo + "</td></tr>" + "<tr><td>照片</td><td><img src=\"data:image/png;base64," + idCard.photo + "\"/></td></tr>" + "</table>";

			$.ajax({
				url : "${ctx}/gzf/gzfBrushCard/check",
				data : {
					idCard : idCard.number
				},
				async : false
			}).done(function(data) {
				if ("exist" == data) {
					var number = idCard.number;
					// number = "610303198311024532";
					window.location.href = "${ctx}/gzf/gzfHouseholdInfo?cardid=" + number;

				} else if ("success" == data) {
					window.location.href = "${ctx}/gzf/gzfHouseholdInfo/form?cardid=" + idCard.number + "&name=" + idCard.name+"&address="+idCard.address;
				} else {
					alert(data);
					location.reload([ true ]);
				}
			});

		}
		//显示扫描身份证遮罩层
		function showcover(a) {
			$nowNum = a;
			$parent.scan(scan_callback);
		}
		//扫描身份证
		function scan_callback(ev) {
			var r = ev.detail;
			var a = $nowNum;
			switch (r.result) {
			case "ok":
				showIdCard(r.idCard);
				break;
			case "error":
			default:
				var errMsg = "读卡失败";
				if (r.message)
					errMsg = errMsg + ": " + r.message;
				statusBar.innerHTML = errMsg;
				break;
			}
		}
	</script>
</body>
</html>

