<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views-commons/taglibs.jsp"%>
<c:set var="viewmodel" value="${ctx}/scripts/zckj" />
<c:set var="controller" value="${ctx}/" />
<meta charset="utf-8">
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/travel/main.css"/>
<script type='text/javascript' src='${ctx}/scripts/travel/jquery-3.1.1.js' charset='utf-8'></script>
<script type='text/javascript' src='${ctx}/scripts/travel/main.js' charset='utf-8'></script>
<title></title>
</head>
<script>
	function onclickCheck(){
		window.location.href = "http://139.129.167.224:88/app/download.jsp"
	}
</script>
<body> 
    <article class="article_fx" onclick="onclickCheck()">
        <header class="article_fx_header">
            <img src="${travelogs.head_img}" class="image1"/>
            <span>${travelogs.nick_name}</span>
            <small>${travelogs.createDate}</small> 
            <img src="${ctx}/images/travel/t1.jpg" class="image2"/>
        </header>
        <div class="image_content">
        	<c:if test="${type == 0}">
            <img src="${pic}"/>
            <a href="javascript:void(0)" class="sign">图集</a>
            <div class="back"></div>
            </c:if>
        	<c:if test="${type == 1}">
            <img src="${travelogs.travel_img}"/>
            <a href="javascript:void(0)" class="sign">视频</a>
            <div class="back"></div>
            </c:if>
        </div>
        <div class="article_content">
            <header class="article_content_header">${travelogs.title}</header>
            <div class="article_content_content">
               ${travelogs.content} 
        	</div>
        <footer class="article_content_footer">
            <img src="${ctx}/images/travel/t5.jpg" class="image1"/>
            <a href="javascript:void(0)" class="address">${travelogs.address}</a>
            <a href="javascript:void(0)" class="click">30</a>
            <img src="${ctx}/images/travel/t4.jpg" class="image2"/>
            <a href="javascript:void(0)" class="click">200</a>
            <img src="${ctx}/images/travel/t2.jpg" class="image2"/>
            <a href="javascript:void(0)" class="click">50</a>
            <img src="${ctx}/images/travel/t6.png" class="image2"/>
        </footer>
    </article>
</body>
</html>
