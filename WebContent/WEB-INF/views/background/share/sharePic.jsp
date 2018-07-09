<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/views-commons/taglibs.jsp"%>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<title>智慧景区--游乐圈</title>
		<!--公共样式-->
		<link rel="stylesheet" href="${ctx}/styles/share/NormalizeFile.css">
		<link rel="stylesheet" href="${ctx}/styles/share/public.css">
		<!--自定义样式-->
		<link rel="stylesheet" href="${ctx}/styles/share/style.css">
		<!--jquery库-->
		<script src="${ctx}/scripts/share/jquery.min.js"></script>	
		<!--rem自适应-->
		<script src="${ctx}/scripts/share/flexible.js"></script>	
	</head>

	<body>
		<!--头部菜单begin-->
		<header class="header box-bar">
			<div class="cell back" onclick="javascript:history.back(-1);"></div>
			<div class="box-bar-list title">智慧景区--游乐圈</div>
		</header>
		<!--头部菜单end-->
		<!--主体部分begin-->
		<div class="wrap">
			<!--会员信息beghin-->
			<div class="user-bar box-bar mar-bot">
				<div class="box-bar-list user-inf">
					<div class="user-tx"><img src="images/tx.png"></div>
					<div class="user-name">${name}</div>
				</div>
				<div class="cell user-time">${date}</div>
			</div>
			<!--会员信息end-->
			<!--图文信息begin-->
			<div class="share-wrap pad10">
				<h3 class="share-hd">${title}</h3>
				<p class="share-bd">${content}</p>
			</div>
			<div class="share-wrap pic-dis">
				<c:forEach items="${pic}" var="picArr">
					<img src="${picArr}" />
				</c:forEach>
			</div>
			<!--图文信息end-->
		</div>
		<!--主体部分end-->
		<footer class="foot box-bar">
			<p class="box-bar-list">想知道更多精彩游记，请下载“智慧景区”APP</p>
			<a class="cell djload-btn" href="javascript:void(0);" onclick="downLoad()">点击下载</a>
		</footer>
	</body>
	<script>
		function downLoad(){
			alert("下载成功！");
		}
	</script>
</html>