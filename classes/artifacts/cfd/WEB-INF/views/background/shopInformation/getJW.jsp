<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="initial-scale=1.0, user-scalable=no, width=device-width">
<title>经纬度选择</title>
<link rel="stylesheet"
	href="http://cache.amap.com/lbs/static/main1119.css" />
<script type="text/javascript" src="../js/jquery-1.12.3.js"></script>
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=密钥&plugin=AMap.Autocomplete"></script>
<script type="text/javascript"
	src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<style>
body, html, #initMap {
	width: 100%;
	height: 100%;
	font-size: 12px;
	font-family: "微软雅黑";
}
</style>
</head>
<body>
	<div id="initMap"></div>
	<div id="myPageTop">
		<table>
			<tr>
				<td><label>经度：</label></td>
				<td><label>纬度：</label></td>
			</tr>
			<tr>
				<td><input type="text" id="lat" /></td>
				<td><input type="text" id="lon" /></td>
			</tr>
		</table>
	</div>
	<script>  
                var map = new AMap.Map("initMap",{  
                    resizeEnable: true  
                });  
                  
                map.on('click', function(e) {  
                    //经度  
                    document.getElementById("lat").value = e.lnglat.getLng();  
                    //纬度  
                    document.getElementById("lon").value = e.lnglat.getLat();  
                });  
            </script>
</body>
</html>
