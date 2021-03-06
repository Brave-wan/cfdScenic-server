<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views-commons/header.jsp" %>
<style type="text/css">
    body {
        font-family: "微软雅黑";
    }

    .tb {
        width: 90%;
        margin: 20px auto;
    }

    .tb td {
        height: 45px;
        text-align: left;
    }

    .tb td:first-child {
        width: 10%;
    }

    .tb td:last-child {
        width: 90%;
    }

    .tb td input[type=text] {
        width: 80%;
        height: 30px;
        line-height: 30px;
        padding-left: 10px;
    }

    .tb img {
        width: 150px;
        height: 100px;
    }

    .tb td textarea {
        padding-left: 10px;
    }

    .tb td label {
        float: left;
        width: 100px;
        text-align: left;
    }

    .l-btn-text {
        line-height: 30px;
    }

    .easyui-linkbutton {
        width: 30%;
        height: 30px;
        display: inline-block;
    }

    .uploader { position:relative; display:inline-block; overflow:hidden; cursor:default; padding:0; margin:10px 0px; -moz-box-shadow:0px 0px 5px #ddd; -webkit-box-shadow:0px 0px 5px #ddd; box-shadow:0px 0px 5px #ddd; -moz-border-radius:5px; -webkit-border-radius:5px; border-radius:5px; }
    #filename { float:left; display:inline-block; outline:0 none; height:32px; width:180px; margin:0; padding:8px 10px; overflow:hidden; cursor:default; border:1px solid; border-right:0; font:9pt/100% Arial, Helvetica, sans-serif; color:#777; text-shadow:1px 1px 0px #fff; text-overflow:ellipsis; white-space:nowrap; -moz-border-radius:5px 0px 0px 5px; -webkit-border-radius:5px 0px 0px 5px; border-radius:5px 0px 0px 5px; background:#f5f5f5; background:-moz-linear-gradient(top, #fafafa 0%, #eee 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #fafafa), color-stop(100%, #f5f5f5)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#fafafa', endColorstr='#f5f5f5', GradientType=0);
        border-color:#ccc; -moz-box-shadow:0px 0px 1px #fff inset; -webkit-box-shadow:0px 0px 1px #fff inset; box-shadow:0px 0px 1px #fff inset; -moz-box-sizing:border-box; -webkit-box-sizing:border-box; box-sizing:border-box; }
    #filename1 {
	float: left;
	display: inline-block;
	outline: 0 none;
	height: 32px;
	width: 180px;
	margin: 0;
	padding: 8px 10px;
	overflow: hidden;
	cursor: default;
	border: 1px solid;
	border-right: 0;
	font: 9pt/100% Arial, Helvetica, sans-serif;
	color: #777;
	text-shadow: 1px 1px 0px #fff;
	text-overflow: ellipsis;
	white-space: nowrap;
	-moz-border-radius: 5px 0px 0px 5px;
	-webkit-border-radius: 5px 0px 0px 5px;
	border-radius: 5px 0px 0px 5px;
	background: #f5f5f5;
	background: -moz-linear-gradient(top, #fafafa 0%, #eee 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #fafafa),
		color-stop(100%, #f5f5f5));
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fafafa',
		endColorstr='#f5f5f5', GradientType=0);
	border-color: #ccc;
	-moz-box-shadow: 0px 0px 1px #fff inset;
	-webkit-box-shadow: 0px 0px 1px #fff inset;
	box-shadow: 0px 0px 1px #fff inset;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}    
    .button { float:left; height:32px; display:inline-block; outline:0 none; padding:8px 12px; margin:0; cursor:pointer; border:1px solid; font:bold 9pt/100% Arial, Helvetica, sans-serif; -moz-border-radius:0px 5px 5px 0px; -webkit-border-radius:0px 5px 5px 0px; border-radius:0px 5px 5px 0px; -moz-box-shadow:0px 0px 1px #fff inset; -webkit-box-shadow:0px 0px 1px #fff inset; box-shadow:0px 0px 1px #fff inset; }
    .uploader input[type=file] { position:absolute; top:0; right:0; bottom:0; border:0; padding:0; margin:0; height:30px; cursor:pointer; filter:alpha(opacity=0); -moz-opacity:0; -khtml-opacity: 0; opacity:0; }
    input[type=button]::-moz-focus-inner {
        padding:0;
        border:0 none;
        -moz-box-sizing:content-box;
    }
    input[type=button]::-webkit-focus-inner {
        padding:0;
        border:0 none;
        -webkit-box-sizing:content-box;
    }
    input[type=text]::-moz-focus-inner {
        padding:0;
        border:0 none;
        -moz-box-sizing:content-box;
    }
    input[type=text]::-webkit-focus-inner {
        padding:0;
        border:0 none;
        -webkit-box-sizing:content-box;
    }
    /* White Color Scheme ------------------------ */

    .white .button { color:#555; text-shadow:1px 1px 0px #fff; background:#ddd; background:-moz-linear-gradient(top, #eeeeee 0%, #dddddd 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #eeeeee), color-stop(100%, #dddddd)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#eeeeee', endColorstr='#dddddd', GradientType=0);
        border-color:#ccc; }
    .white:hover .button { background:#eee; background:-moz-linear-gradient(top, #dddddd 0%, #eeeeee 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #dddddd), color-stop(100%, #eeeeee)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#dddddd', endColorstr='#eeeeee', GradientType=0);
    }
    /* Blue Color Scheme ------------------------ */

    .blue .button { color:#fff; text-shadow:1px 1px 0px #09365f; background:#064884; background:-moz-linear-gradient(top, #3b75b4 0%, #064884 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #3b75b4), color-stop(100%, #064884)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#3b75b4', endColorstr='#064884', GradientType=0);
        border-color:#09365f; }
    .blue:hover .button { background:#3b75b4; background:-moz-linear-gradient(top, #064884 0%, #3b75b4 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #064884), color-stop(100%, #3b75b4)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#064884', endColorstr='#3b75b4', GradientType=0);
    }
    #initMap {
        width: 100%;
        height: 100%;
        font-size: 12px;
        font-family: "微软雅黑";
    }
</style>
<div style="width:98.5%;padding:10px;">
    <c:if test="${model.type==1}">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:景点管理-修改景点</div>
    </c:if><c:if test="${model.type==2}">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:景点管理-修改基础活动</div>
    </c:if><c:if test="${model.type==3}">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:景点管理-修改结伴游活动</div>
    </c:if><c:if test="${model.type==4}">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:景点管理-修改积分商品</div>
    </c:if><c:if test="${model.type==5}">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:景点管理-修改积分门票</div>
    </c:if>
    <form id="ff" action="./background/visitorsManage/addVisitors" method="post" enctype="multipart/form-data">
        <input type="hidden" value="${model.id }" name="id"/>
        <input type="hidden" value="${model.type }" id="type"/>
        <table class="tb">
            <tr>
                <td>名称：</td>
                <td><input type="text" id="name" name="name" value="${model.name }" placeholder="请输入名称"/></td>
            </tr>
            <tr>
                <td>英文名称：</td>
                <td><input type="text" id="nameEn" name="nameEn" value="${model.nameEn }" placeholder="请输入英文名称"/></td>
            </tr>
            <tr>
                <td>简介：</td>
                <td><input type="text" id="visitorsDescribe" name="visitorsDescribe" value="${model.visitorsDescribe }" placeholder="请输入简介"/></td>
            </tr>
            <c:if test="${model.type==1 or model.type==2 or model.type==3}">
			<tr>
				<td>原价：</td>
				<td><input type="text" id="price" name="price" value="${model.price}"
					onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
					onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"
					placeholder="请输入原价" /></td>
			</tr>
			<tr>
				<td>折后价：</td>
				<td><input type="text" id="newPrice" name="newPrice" value="${model.newPrice}"
					onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
					onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"
					placeholder="请输入折后价" /></td>
			</tr>
			</c:if>
			<c:if test="${model.type==5 or model.type==4}">
			<tr style="display: none">
				<td>原价：</td>
				<td><input type="text" id="price" name="price" value="0"
					onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
					onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"
					placeholder="请输入原价" /></td>
			</tr>
			<tr style="display: none">
				<td>折后价：</td>
				<td><input type="text" id="newPrice" name="newPrice" value="0"
					onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
					onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"
					placeholder="请输入折后价" /></td>
			</tr>
			</c:if>
            <tr>
                <td>地址：</td>
                <td><input type="text" id="address" name="address" value="${model.address }" placeholder="请输入地址"/></td>
            </tr>
            <tr>
                <td>经度：</td>
                <td><input type="text" name="latitude" value="${model.latitude }" id="latitude" onMouseUp="latAndLong()" readonly placeholder="请获取经度"/></td>
                <td>
                    <div class="latAndLong" style="display:none">
                        <img style="width:auto;height:auto" src="${ctx}/images/dontUpdate.png" >
                    </div>
                </td>
            </tr>
            <tr>
                <td>纬度：</td>
                <td><input type="text" name="longitude" value="${model.longitude }" id="longitude" onMouseUp="latAndLong()" readonly placeholder="请获取纬度"/></td>
                <td>
                    <div class="latAndLong" style="display:none">
                        <img style="width:auto;height:auto" src="${ctx}/images/dontUpdate.png" >
                    </div>
                </td>
            </tr>
            <tr>
                <td>获取经纬度：</td>
                <td>
                    <a href="javascript:void(0)" onclick="openNew()" class="easyui-linkbutton" iconCls="icon-add">点击获取经纬度</a>
                </td>
            </tr>
            <tr>
                <td>备注：</td>
                <td><input type="text" name="memo" value="${model.memo }" placeholder="请输入备注"/></td>
            </tr>
            <c:if test="${model.type==4 or model.type==5}">
                <tr>
                    <td>积分：</td>
                    <td><input type="text" id="integral" name="integral" value="${model.integral }"/></td>
                </tr>
            </c:if>
            <tr>
                <td>配送费：</td>
                <td><input type="text" id="deliverFee" name="deliverFee" value="${model.deliverFee }"
                           onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
                           onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" placeholder="请输入配送费"/></td>
            </tr>
            <c:if test="${model.type==1 or model.type==2 or model.type==3 or model.type==5}">
            	<tr>
	                <td>人数：</td>
	                <td><input type="text" id="number" name="number" value="${model.number }" placeholder="请输入人数"/></td>
            	</tr>
            </c:if>
           	<c:if test="${model.type==4}">
           		<input type="hidden" id="number" name="number" value="${model.number }" placeholder="请输入人数"/>
           	</c:if>
            <tr>
                <td>满意度：</td>
                <td>
                	<input type="hidden" id="satisfaction" value="${model.satisfaction }"/>
                    <select name="satisfaction" id="select" value="${model.satisfaction }">
                        <option  value=''>请选择</option>
                        <option  value='0'>0</option>
                        <option  value='10%'>10%</option>
                        <option  value='20%'>20%</option>
                        <option  value='30%'>30%</option>
                        <option  value='40%'>40%</option>
                        <option  value='50%'>50%</option>
                        <option  value='60%'>60%</option>
                        <option  value='70%'>70%</option>
                        <option  value='80%'>80%</option>
                        <option  value='90%'>90%</option>
                        <option  value='100%'>100%</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>类型：</td>
                <td>
                    <input type="hidden" value="${model.type}" id="typeId" name="typeId"/>
                    <select name="type" disabled>
                        <option value="1"
                                <c:if test="${model.type==1 }">selected</c:if> >景点
                        </option>
                        <option value="2"
                                <c:if test="${model.type==2 }">selected</c:if> >基础活动
                        </option>
                        <option value="3"
                                <c:if test="${model.type==3 }">selected</c:if> >结伴游活动
                        </option>
                        <option value="4"
                                <c:if test="${model.type==4 }">selected</c:if> >积分商品
                        </option>
                        <option value="5"
                                <c:if test="${model.type==5 }">selected</c:if> >积分门票
                        </option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>是否推荐：</td>
                <td>
                    <input type="radio"
                           <c:if test="${model.isRecommend ==0 }">checked </c:if> name="isRecommend" value="0">正常
                    <input type="radio"
                           <c:if test="${model.isRecommend ==1 }">checked </c:if> name="isRecommend" value="1">热门
					<c:if test="${model.type==1}">                           
                    <input type="radio"
                           <c:if test="${model.isRecommend ==2 }">checked </c:if> name="isRecommend" value="2">推荐
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>状态：</td>
                <td>
                    <input type="radio"
                    <c:if test="${model.state ==0 }"> checked</c:if> name="state" value="0">正常
                    <input type="radio"
                    <c:if test="${model.state ==1 }"> checked</c:if> name="state" value="1">删除
                </td>
            </tr>
            <c:if test="${model.type==1 or model.type==2 or model.type==3 or model.type==5}">
            <tr>
                <td>开启时间：</td>
                <td><input type="text" id="startValid" name="startValid" class="easyui-datebox"
                           style="width: 150px;height:30px;line-height:30px;" value="${model.startValid }"
                           data-options="required:true,showSeconds:false"/></td>
            </tr>
            <tr>
                <td>关闭时间：</td>
                <td><input type="text" id="endValid" name="endValid" class="easyui-datebox"
                           style="width: 150px;height:30px;line-height:30px;" value="${model.endValid }"
                           data-options="required:true,showSeconds:false"/></td>
            </tr>
            <tr>
                <td>开放时间设置：</td>
                <td><input type="text" id="openDate" name="openDate" value="${odl.timeDetail}" placeholder="例：9:00-21:00 （6月-8月，周一至周四）"/></td>
                <input type="hidden" name="openDateId" value="${odl.id}" id="openDateId">
            </tr>
            </c:if>
<!--             <tr> -->
<!--                 <td>主图：</td> -->
<!--                 <td> -->
<%--                     <img id="headImg" name="headImg" alt="" src="${model.headImg }"> --%>
<!--                     <input type="hidden" id="pictureUrl" name="pictureUrl"> -->
<!--                 </td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td>修改主图：</td> -->
<!--                 <td> -->
<!--                     <div class="uploader blue"> -->
<!--                         <input type="text" id="filename" class="filename" readonly=""> -->
<!--                         <input type="button" name="imageFile" class="button" value="上传..."> -->
<!--                         <input type="file" size="30" name="imageFile" onchange="uploadImg()"> -->
<!--                     </div> -->
<!--                 </td> -->
<!--             </tr> -->
<tr>
				<td>主图展示：</td>
				<td>
					<div id="headImgId">
					<img alt="" src="${model.headImg}">
					</div>
                   	<input type="hidden" id="headImg" value="${model.headImg}" name="headImg">
				</td>
			</tr>
			<tr>
				<td>主图：</td>
				<td>
					<div class="uploader blue">
						<input type="text" id="filename1" class="filename" readonly="">
						<input type="button" name="imageFile1" class="button" value="上传...">
						<input type="file" size="30" name="imageFile1" id="f2">
					</div>
				</td>
			</tr>
             <c:if test="${model.type != 4}">
             	 <tr>
                <td>图集：</td>
                <td>
                   <div id="pictureId">
                   	<c:forEach items="${picUrl}" var="picUrlList">
                   		<div style='float: left;'>
                   		<c:if test="${picUrlList != ''}">
                   		    <img class="imgUrl" alt="暂时无法显示！" src="${picUrlList}">
                   			<img src='${ctx}/images/xhao.png' style='width: 10px;height: 10px;vertical-align: top; margin-left: -10px;' onclick='deleteImg(this)'>
                   		</c:if>
                   		</div>
                   	</c:forEach>
                   </div> 
                   <input type="hidden" id="pictureUrl" name="pictureUrl">
                </td>
            </tr>
            <tr>
                <td>上传图集：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename" class="filename" readonly="">
                        <input type="button" name="imageFile" class="button" value="上传...">
                        <input type="file" size="30" id="imgFile" multiple="true" name="imgFile" onchange="uploadListImg()">
                    </div>
                </td>
            </tr>
             </c:if>
            <tr>
                <td>须知：</td>
                <td>
                    <script id="editor1" type="text/plain" style="width:1024px;height:500px;"></script>
                    <input type="hidden" name="noticeId" id="noticeId" value="${model.noticeId}"/>

                </td>
            </tr>
            <tr>
                <td>景区详情：</td>
                <td>
                    <script id="editor2" type="text/plain" style="width:1024px;height:500px;"></script>
                    <input type="hidden" name="detailsId" id="detailsId" value="${model.detailsId}"/>

                </td>
            </tr>
        </table>
        <div style="text-align:center;padding:5px">
            <a id="showPageSaveBtn" href="javascript:void(0)" onclick="save()" class="easyui-linkbutton"
               iconCls="icon-save">确定</a>
            <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton"
               iconCls="icon-cancel">取消</a>
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
	<script>
	$(function(){
	    $("#f2").change(function(){
	       /*  $(this).parents(".uploader").find(".filename").val($(this).val()); */
	        $("#filename1").val($(this).val());
	        $("#headImg").val($(this).val());
	        var file = this.files[0]; //选择上传的文件
	        var r = new FileReader();
	        r.readAsDataURL(file); //Base64
	        $(r).load(function(){
	       		$('#headImgId').html('<img src="'+ this.result +'" alt="" />');
	        });
	    });
		});
	
		function latAndLong(){
			$(".latAndLong").css('display','block'); 
		}
	</script>
	<script type="text/javascript">
function openNew(){
	var lat = $("#latitude").val();
	var lon = $("#longitude").val();
	if(lat == null || lat.length == 0){
		lat = 118.449347
	}
	if(lon == null || lon.length == 0){
		lon = 39.272507
	}
	var map = new AMap.Map("initMap",{  
	        resizeEnable: true,
	        center: [lat,lon]
	    });
	var marker = new AMap.Marker({
		position : [ lat, lon ]
	});
	marker.setMap(map);
	map.addControl(new AMap.ToolBar())
    map.setFitView()	
	    map.on('click', function(e) {
	    	map.clearMap();
	        //经度  
	        document.getElementById("lat").value = e.lnglat.getLng();  
	        //纬度  
	        document.getElementById("lon").value = e.lnglat.getLat();  
	        
	        var lat1 = $("#lat").val();
			var lon1 = $("#lon").val();
			var marker = new AMap.Marker({
				position : [ lat1, lon1 ]
			});
			marker.setMap(map);
	    map.addControl(new AMap.ToolBar())
	    });
	$('#mapWindow').window('open');
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

    $(function(){

//         $("input[type=file]").change(function(){
//             $(this).parents(".uploader").find(".filename").val($(this).val());
//         });

//         $("input[type=file]").each(function(){
//             if($(this).val()==""){
//                 $(this).parents(".uploader").find(".filename").val("请选择图片...");
//             }
//         });
        var tem = $("#satisfaction").val();
        $("#select").val(tem);

    });

    $(function () {
        UE.getEditor('editor1').addListener("ready", function () {
            UE.getEditor('editor1').setContent('${htmlText1.contentHtml}');
        })
        UE.getEditor('editor2').addListener("ready", function () {
            UE.getEditor('editor2').setContent('${htmlText2.contentHtml}');
        })
    })



    function save() {
        var type = $("#type").val();
        
        if (type ==1 || type ==2 || type ==3 || type == 5) {
        	if ($("#number").val() == null || $("#number").val() == '') {
                alert("请填写人数!");
                return;
            }
        	}
        	if (type ==1 || type ==2 || type ==3) {
        	if ($("#price").val() == null || $("#price").val() == '') {
                alert("请填写原价!");
                return;
            }
            if ($("#newPrice").val() == null || $("#newPrice").val() == '') {
                alert("请填写折后价!");
                return;
            }
        	}
        	
            if ($("#name").val() == null || $("#name").val() == '') {
                alert("请填写名称!");
                return;
            }
            if ($("#nameEn").val() == null || $("#nameEn").val() == '') {
                alert("请填写英文名称!");
                return;
            }
            if ($("#visitorsDescribe").val() == null || $("#visitorsDescribe").val() == '') {
                alert("请填写简介!");
                return;
            }
            if ($("#address").val() == null || $("#address").val() == '') {
                alert("请填写地址!");
                return;
            }
            if ($("#latitude").val() == null || $("#latitude").val() == '') {
                alert("请获取经纬度!");
                return;
            }
            if (type ==4 || type ==5) {
                if ($("#integral").val() == null || $("#integral").val() == '') {
                    alert("请填写积分!");
                    return;
                }
            }
            if(type != 4){
                if ($("#startValid").datebox("getValue") == null || $("#startValid").datebox("getValue") == '') {
                    alert("请选择开启时间!");
                    return;
                }
                
                if ($("#endValid").datebox("getValue") == null || $("#endValid").datebox("getValue") == '') {
                    alert("请选择关闭时间!");
                    return;
                }
            }
            if ($("#deliverFee").val() == null || $("#deliverFee").val() == '') {
                alert("请填写配送费!");
                return;
            }
            if ($("#select").val() == null || $("#select").val() == '') {
                alert("请选择满意度!");
                return;
            }
            if ($("#headImg").val() == null || $("#headImg").val() == '') {
                alert("请上传主图!");
                return;
            }
        
        
        var arr1 = [];
        arr1.push(UE.getEditor('editor1').getContentTxt());

        var arr2 = [];
        arr2.push(UE.getEditor('editor2').getContentTxt());

        $.post("${basePath}/htmlText/addHtmlText1", {
            id: $("#noticeId").val(),
            contentText: arr1.join("\n"),
            contentHtml: UE.getEditor('editor1').getContent(),
            activityDetail: UE.getEditor('editor1').getContent(),
            type: 3,
            state: 0
        }).success(function (data) {
            if (data.success) {
                if (data.obj != null)
                    $("#noticeId").val(data.obj);
                $.post("${basePath}/htmlText/addHtmlText2", {
                    id: $("#detailsId").val(),
                    contentText: arr2.join("\n"),
                    contentHtml: UE.getEditor('editor2').getContent(),
                    activityDetail: UE.getEditor('editor2').getContent(),
                    type: 4,
                    state: 0
                }).success(function (data) {
                    if (data.success) {
                        if (data.obj != null)
                            $("#detailsId").val(data.obj);
                        if ($("#noticeId").val() == null || $("#noticeId").val() == '') {
                            alert("请填写须知!");
                            return;
                        }
                        if ($("#detailsId").val() == null || $("#detailsId").val() == '') {
                            alert("请填写详情!");
                            return;
                        }
                        var imgUrl = "";
                        $(".imgUrl").each(function () {
                            imgUrl += $(this).attr("src")+",";
                        })
                        $("#pictureUrl").val(imgUrl);
                        
                        $('#ff').submit();
                    } else {
                        eu.showMsg(data);
                    }
                }).error(function (data) {
                    eu.showMsg("系统错误，请联系管理员！");
                })
            } else {
                eu.showMsg(data);
            }
        }).error(function (data) {
            eu.showMsg("系统错误，请联系管理员！");
        })

    }

    function uploadImg() {
        var formElement = document.getElementById("ff");
        var oRep = new XMLHttpRequest();
        oRep.open("POST","${basePath}/background/visitorsManage/uploadPicture",true);
        oRep.send(new FormData(formElement));
        oRep.onreadystatechange = function() {
            if (oRep.readyState == 4) {
                var b = oRep.responseText;
                var a = eval("("+b+")");
                console.log(a.obj);
                if (oRep.status == 200) {
                    if (a.success == true) {
                        //$("#pictureId").html("<img class='imgUrl' src='"+a.obj+"'>");
                        $("#headImg").attr("src",a.obj);
                        /*for (var i = 0 ; i < a.obj.length ; i++){
                         var position = a.obj[i];
                         for(var pro in position){
                         $("#pictureId").append("<div style='float: left;'>" +
                         "<img class='imgUrl' src='"+position[pro]+"'>" +
                         "<img src='${ctx}/images/xhao.png' style='width: 10px;height: 10px;vertical-align: top; margin-left: -10px;' onclick='deleteImg(this)'>" +
                         "</div>");
                         }
                         }*/
                    }
                }
            }
        }
    }

    function latAndLong(){
        $(".latAndLong").css('display','block');
    }

    function openNew(){
        var lat = $("#latitude").val();
        var lon = $("#longitude").val();
        if(lat == null || lat.length == 0){
            lat = 118.449347
        }
        if(lon == null || lon.length == 0){
            lon = 39.272507
        }
        var map = new AMap.Map("initMap",{
            resizeEnable: true,
            center: [lat,lon]
        });
        var marker = new AMap.Marker({
            position : [ lat, lon ]
        });
        marker.setMap(map);
        map.addControl(new AMap.ToolBar())
        map.setFitView()
        map.on('click', function(e) {
            map.clearMap();
            //经度
            document.getElementById("lat").value = e.lnglat.getLng();
            //纬度
            document.getElementById("lon").value = e.lnglat.getLat();

            var lat1 = $("#lat").val();
            var lon1 = $("#lon").val();
            var marker = new AMap.Marker({
                position : [ lat1, lon1 ]
            });
            marker.setMap(map);
            map.addControl(new AMap.ToolBar())
        });
        $('#mapWindow').window('open');
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
    function uploadListImg() {
        var formElement = document.getElementById("ff");
        var oRep = new XMLHttpRequest();
        oRep.open("POST","${basePath}/pictureLibrary/uploadPicture",true);
        oRep.send(new FormData(formElement));
        oRep.onreadystatechange = function() {
            if (oRep.readyState == 4) {
                var b = oRep.responseText;
                var a = eval("("+b+")");
                if (oRep.status == 200) {
                    if (a.success == true) {
                        for (var i = 0 ; i < a.obj.length ; i++){
                            var position = a.obj[i];
                            for(var pro in position){
                                	$("#pictureId").append("<div style='float: left;'>" +
                                        	"<img class='imgUrl' src='"+position[pro]+"'>" +
                                        	"<img src='${ctx}/images/xhao.png' style='width: 10px;height: 10px;vertical-align: top; margin-left: -10px;' onclick='deleteImg(this)'>" +
                                        	"</div>");
                                	var picUrl = $("#pictureUrl").val();
                                	if(picUrl == null || picUrl ==""){
	                                	$("#pictureUrl").val(position[pro])
                                	}else{
	                                	picUrl = picUrl + "," + position[pro];
                                		$("#pictureUrl").val(picUrl)
                                	}
                            }
                        }
                    }
                }
            }
        }
    }
    function deleteImg(obj) {
        $(obj).parent().remove();
    }
</script>
<%@ include file="/WEB-INF/views-commons/footer.jsp" %>