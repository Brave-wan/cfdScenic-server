<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<link rel="stylesheet"
	href="http://cache.amap.com/lbs/static/main1119.css" />
<script type="text/javascript" src="../js/jquery-1.12.3.js"></script>
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=2f589c0f0d80857dbac2b0b4d36acbfb&plugin=AMap.Autocomplete"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=2f589c0f0d80857dbac2b0b4d36acbfb&plugin=AMap.ToolBar"></script>
<script type="text/javascript"
	src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<style type="text/css">
	body{font-family: "微软雅黑";}
	table{width:90%;margin:20px auto;}
	table td{height:45px;text-align:left;}
	table td:first-child{width:10%;}
	table td:last-child{width:90%;}
	table td input[type=text]{width:80%;height:30px;line-height: 30px;padding-left: 10px;}
	table img{width:150px;height:100px;}
	table td textarea{padding-left:10px;}
	table td label{float: left;width:100px;text-align: left;}
	.l-btn-text{line-height: 30px;}
	.easyui-linkbutton{width:30%;height:30px;display: inline-block;}
	#initMap {
	width: 100%;
	height: 100%;
	font-size: 12px;
	font-family: "微软雅黑";
	}
</style>
<div style="width:98.5%;padding:10px;">
		<div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:景点管理-查看详情</div>
			<form id="ff" action="" method="post">
                <table>
                    <tr>
                        <td>店铺名称：</td>
                        <td><input type="text" name="name" value="${model.name}" disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>地址：</td>
                        <td><input type="text" name="address" value="${model.address}" disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>经度：</td>
                        <td><input type="text" name="latitude" id="latitude" value="${model.latitude}" disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>纬度：</td>
                        <td><input type="text" name="longitude" id="longitude" value="${model.longitude}" disabled="disabled"/>
                        </td>
                    </tr>
<!--                      <tr> -->
<!--                     	<td>获取经纬度：</td> -->
<!--                         <td> -->
<!--                         	<a href="javascript:void(0)" onclick="openNew()" class="easyui-linkbutton" iconCls="icon-add">点击获取经纬度</a> -->
<!--                         </td> -->
<!--                     </tr> -->
                    <tr>
                        <td>电话：</td>
                        <td><input type="text" name="phone" value="${model.phone}" disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>内容介绍：</td>
                        <td><input type="text" name="content" value="${model.content}" disabled="disabled" /></td>
                            <input type="hidden"  value="${model.sgName}"/> 
                    </tr>
<!--                     <tr>
                        <td>店铺分类：</td>
                        <td>
                        </td>
                    </tr>
 -->                    <tr>
                        <td>年龄：</td>
                        <td><input type="text" name="age" value="${model.age}" disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>真实姓名：</td>
                        <td><input type="text" name="realName" value="${model.realname}" disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>身份证：</td>
                        <td><input type="text" name="idCard" value="${model.idcard}" disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>经营范围：</td>
                        <td><input type="text" name="businessScope" value="${model.business_scope}" disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>账户名称：</td>
                        <td><input type="text" name="accountName" value="${model.account_name}" disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>银行卡号：</td>
                        <td><input type="text" name="bankCard" value="${model.bank_card}" disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>开户行：</td>
                        <td><input type="text" name="accountBank" value="${model.account_bank}" disabled="disabled" /></td>
                    </tr>
<!--                     <tr> -->
<!--                         <td>提现密码：</td> -->
<%--                         <td><input type="text" name="cashPassWord" value="${model.cash_password}" disabled="disabled" /></td> --%>
<!--                     </tr> -->
                    <tr>
                        <td>人均消费：</td>
                        <td><input type="text" name="consumption" value="${model.consumption}" disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>审核失败原因：</td>
                        <td><input type="text" name="consumption" value="${model.audit_fail}" disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>账户类型：</td>
                        <td>
                            <input type="text" <c:if test="${model.account_type == 0}">value="对公"</c:if>
                                   <c:if test="${model.account_type == 1}">value="个人"</c:if> disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <td>性别：</td>
                        <td>
                            <input type="text" <c:if test="${model.sex == 0}">value="男"</c:if>
                                   <c:if test="${model.sex == 1}">value="女"</c:if> disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <td>是否有营业执照：</td>
                        <td>
                            <input type="text" <c:if test="${model.is_license == 0}">value="否"</c:if>
                             <c:if test="${model.is_license == 1}">value="是"</c:if> disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <td>是否有无线：</td>
                        <td>
                            <input type="text" <c:if test="${model.is_wifi == 0}">value="否"</c:if>
                                   <c:if test="${model.is_wifi == 1}">value="是"</c:if>  disabled>
                        </td>
                    </tr>
                    <tr>
                        <td>是否有浴室：</td>
                        <td>
                            <input type="text" <c:if test="${model.is_yushi == 0}">value="否"</c:if>
                                   <c:if test="${model.is_yushi == 1}">value="是"</c:if> disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <td>是否有便利设施：</td>
                        <td>
                            <input type="text" <c:if test="${model.is_blss == 0}">value="否"</c:if>
                                   <c:if test="${model.is_blss == 1}">value="是"</c:if> disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <td>是否有媒体或科技：</td>
                        <td>
                            <input type="text" <c:if test="${model.is_media == 0}">value="否"</c:if>
                                   <c:if test="${model.is_media == 1}">value="是"</c:if> disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <td>是否有食品或饮品：</td>
                        <td>
                            <input type="text" <c:if test="${model.is_food == 0}">value="否"</c:if>
                                   <c:if test="${model.is_food == 1}">value="是"</c:if> disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <td>就餐开始时间：</td>
                        <td><input type="text"  value="<fmt:formatDate value="${model.start_date }" pattern="yyyy-MM-dd HH:mm:ss" />"  disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>就餐结束时间：</td>
                        <td><input type="text"  value="<fmt:formatDate value="${model.end_date }" pattern="yyyy-MM-dd HH:mm:ss" />"   disabled="disabled"/></td>
                    </tr>
                    <tr>
                        <td>头像：</td>
                        <td><img src="${model.head_img}" alt="暂时没有图片"></td>
                    </tr>
                    <tr>
                        <td>背景图：</td>
                        <td><img src="${model.backgroud_img}" alt="暂时没有图片"></td>
                    </tr>
                    <tr>
                        <td>手持证件照：</td>
                        <td><img src="${model.hold_card_img}" alt="暂时没有图片"></td>
                    </tr>
                    <tr>
                        <td>身份证正面照：</td>
                        <td><img src="${model.face_card_img}" alt="暂时没有图片"></td>
                    </tr>
                    <tr>
                        <td>身份证反面照：</td>
                        <td><img src="${model.back_card_img}" alt="暂时没有图片"></td>
                    </tr>
                    <tr>
                        <td>营业执照照片：</td>
                        <td><img src="${model.license_img}" alt="暂时没有图片"></td>
                    </tr>
                    <tr>
                        <td>其他证件照1：</td>
                        <td><img src="${model.other_img1}" alt="暂时没有图片"></td>
                    </tr>
                    <tr>
                        <td>其他证件照2：</td>
                        <td><img src="${model.other_img2}" alt="暂时没有图片"></td>
                    </tr>
                    <tr>
                        <td>商家logo：</td>
                        <td><img src="${model.shop_img}" alt="暂时没有图片"></td>
                    </tr>
                    <tr>
                        <td>图集：</td>
                        <td>
                            <c:forEach items="${picture}" var="picture">
                                <div style="float: left;">
                                    <img class='imgUrl' alt="" src="${picture }" >
                                </div>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td>店铺信息介绍：</td>
                        <td>
                            <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                            <input type="hidden" name="detailId" id="detailId" value="${model.detail_id}"  />

                        </td>
                    </tr>

                </table>
				<div style="text-align:center;padding:5px">
 					<!-- <a id="showPageSaveBtn" href="javascript:void(0)" onclick="$('#ff').submit()" class="easyui-linkbutton" iconCls="icon-save">确定</a> -->
					<%--<a id="showPageCancleBtn" href="javascript:void(0)" onclick="window.location.href ='./background/visitorsManage/toVisitorsManage?type=${model.type}'" class="easyui-linkbutton" iconCls="icon-save">确定</a>--%>
					<a id="showPageCancleBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save">确定</a>
			    </div>
			</form>
</div>
<div id="mapWindow" class="easyui-dialog" title="选择经纬度" data-options="closed:true,inline:true"
	style="width: 1000px; height: 500px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
				<div id="initMap" tabindex="0"></div>
 			<div id="myPageTop" style="width: 400px; height: 40px;">
				<table style="width:90%;margin:0px;">
					<tr>
						<td style="width:25%;height:45px;text-align:left;">经度：</td>
						<td style="height:25%;text-align:left;"><input style="width:100px;height:20px;line-height: 10px;padding-left: 10px;" type="text" id="lat"/></td>
						<td style="width:25%;height:45px;text-align:left;">纬度：</td>
						<td style="height:25%;text-align:left;"><input style="width:100px;height:20px;line-height: 10px;padding-left: 10px;" type="text" id="lon"/></td>
					</tr>
				</table>
			</div>
		</div>
		
		<div data-options="region:'south',border:false" style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="saveJW()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="closeJW()">关闭</a>
		</div>
		</div>
	</div>
<script type="text/javascript">

// function openNew(){
// 	var map = new AMap.Map("initMap",{  
// 	        resizeEnable: true  
// 	    });  
// 	    map.on('click', function(e) {  
// 	        //经度  
// 	        document.getElementById("lat").value = e.lnglat.getLng();  
// 	        //纬度  
// 	        document.getElementById("lon").value = e.lnglat.getLat();  
	        
// 	        var lat = $("#lat").val();
// 			var lon = $("#lon").val();
// 			var marker = new AMap.Marker({
// 				position : [ lat, lon ]
// 			});
// 			marker.setMap(map);
// 	    map.addControl(new AMap.ToolBar())
// 	    });
// 	$('#mapWindow').window('open');
// }
function openNew(){
	var lat = $("latitude").val();
	if(lat == null){
		lat = 0
	}
	var lon = $("longitude").val();
	if(lon == null){
		lon = 0
	}
	var map = new AMap.Map("initMap",{  
	        resizeEnable: true,
	        layers : [ new AMap.TileLayer.RoadNet(),
						new AMap.TileLayer.Satellite() ],
				center: [lat,lon]
	    });
	
	    
	$('#mapWindow').window('open');
}
map.on('click', function(e) {  
    //经度  
    document.getElementById("lat").value = e.lnglat.getLng();  
    //纬度  
    document.getElementById("lon").value = e.lnglat.getLat();  
    
    var lat = $("#lat").val();
	var lon = $("#lon").val();
	var marker = new AMap.Marker({
		position : [ lat, lon ]
	});
	marker.setMap(map);
	map.addControl(new AMap.ToolBar())
});

	function onclickAdd() {
		var lat = $("#lat").val();
		var lon = $("#lon").val();
		var map = new AMap.Map('initMap', {
			resizeEnable : false,
			zoom : 11,
			layers : [ new AMap.TileLayer.RoadNet(),
					new AMap.TileLayer.Satellite() ],
			center: [lat,lon]		
		});
		var marker = new AMap.Marker({
			position : [ lat, lon ]
		});
		marker.setMap(map);
	map.setCenter(marker.getPosition())
    map.addControl(new AMap.ToolBar())
    map.setFitView()
	}
	function saveJW() {
		var lat = $('#lat').val();
		var lon = $('#lon').val();
		if (lat == "" || lon == "") {
			alert("请选择经纬度！");
			return false;
		}
		$("#latitude").val(lat);
		$("#longitude").val(lon);
		$('#mapWindow').window('close');
	}
	function closeJW() {
		$('#mapWindow').window('close');
	}
</script>

<script type="text/javascript">
    $(function () {
        UE.getEditor('editor').addListener("ready", function () {
            UE.getEditor('editor').setContent('${htmlText.contentHtml}');
           /*  UE.getEditor('editor').setDisabled(); */
        })
    })
</script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>