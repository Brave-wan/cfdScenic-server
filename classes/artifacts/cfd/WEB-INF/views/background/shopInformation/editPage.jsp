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
     #filename1 { float:left; display:inline-block; outline:0 none; height:32px; width:180px; margin:0; padding:8px 10px; overflow:hidden; cursor:default; border:1px solid; border-right:0; font:9pt/100% Arial, Helvetica, sans-serif; color:#777; text-shadow:1px 1px 0px #fff; text-overflow:ellipsis; white-space:nowrap; -moz-border-radius:5px 0px 0px 5px; -webkit-border-radius:5px 0px 0px 5px; border-radius:5px 0px 0px 5px; background:#f5f5f5; background:-moz-linear-gradient(top, #fafafa 0%, #eee 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #fafafa), color-stop(100%, #f5f5f5)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#fafafa', endColorstr='#f5f5f5', GradientType=0);
        border-color:#ccc; -moz-box-shadow:0px 0px 1px #fff inset; -webkit-box-shadow:0px 0px 1px #fff inset; box-shadow:0px 0px 1px #fff inset; -moz-box-sizing:border-box; -webkit-box-sizing:border-box; box-sizing:border-box; }
     #filename2 { float:left; display:inline-block; outline:0 none; height:32px; width:180px; margin:0; padding:8px 10px; overflow:hidden; cursor:default; border:1px solid; border-right:0; font:9pt/100% Arial, Helvetica, sans-serif; color:#777; text-shadow:1px 1px 0px #fff; text-overflow:ellipsis; white-space:nowrap; -moz-border-radius:5px 0px 0px 5px; -webkit-border-radius:5px 0px 0px 5px; border-radius:5px 0px 0px 5px; background:#f5f5f5; background:-moz-linear-gradient(top, #fafafa 0%, #eee 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #fafafa), color-stop(100%, #f5f5f5)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#fafafa', endColorstr='#f5f5f5', GradientType=0);
        border-color:#ccc; -moz-box-shadow:0px 0px 1px #fff inset; -webkit-box-shadow:0px 0px 1px #fff inset; box-shadow:0px 0px 1px #fff inset; -moz-box-sizing:border-box; -webkit-box-sizing:border-box; box-sizing:border-box; }
    
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
    <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:景点管理-修改店铺</div>
    <form id="ff" action="./background/shopInformationManager/saveShopInformation" method="post" enctype="multipart/form-data">
    	<input type="hidden" id="jumpType" name="jumpType" value="0">
        <input type="hidden" name="id" value="${model.id }"/>
        <table class="tb">
            <tr>
                <td>店铺名称：</td>
                <td><input type="text" name="name" value="${model.name}" id="name" onMouseUp="nameIsNotUpdate()" readonly/>
                </td>
                <td>
                <div id="nameIsNotUpdate" style="display:none">                	
                		<img style="width:auto;height:auto" src="${ctx}/images/dontUpdate.png" >
                	</div>
                </td>
            </tr>

            <tr>
                <td>地址：</td>
                <td><input type="text" name="address" value="${model.address}" id="address"/></td>
                <td>
                <div id="nameIsNotUpdate" style="display:none">                	
                		<img style="width:auto;height:auto" src="${ctx}/images/dontUpdate.png" >
                	</div>
                </td>
            </tr>
            <tr>
                <td>经度：</td>
                <td><input type="text" name="latitude" id="latitude" value="${model.latitude}" onMouseUp="latAndLong()" readonly/></td>
                <td>
                <div class="latAndLong" style="display:none">                	
                		<img style="width:auto;height:auto" src="${ctx}/images/dontUpdate.png" >
                	</div>
                </td>
            </tr>
            <tr> 
                <td>纬度：</td>
                <td><input type="text" name="longitude" id="longitude" value="${model.longitude}" onMouseUp="latAndLong()" readonly/></td>
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
                <td>电话：</td>
                <td><input type="text" name="phone" value="${model.phone}" id="phone"/></td>
            </tr>

            <tr>
                <td>内容介绍：</td>
                <td><input type="text" name="content" id="content" value="${model.content}" /></td> 
            </tr>
            <%--<tr>
                <td>店铺分类：</td>
                <td>
                    <select name="shopId">
                        <c:forEach items="${list}" var="list">
                            <option value="${list.id}" <c:if test="${model.shop_id == list.id}"> selected</c:if>   >${list.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>--%>

              <tr>
                  <td>年龄：</td>
                  <td><input type="text" name="age" value="${model.age}" id="age"/></td>
              </tr>
             <tr>
                <td>性别：</td>
                <td>
                    <input type="radio" disabled <c:if test="${model.sex == 0}">checked="checked"</c:if> name="sex" value="0">男
                    <input type="radio" disabled <c:if test="${model.sex == 1}">checked="checked"</c:if> name="sex" value="1">女
                </td>
            </tr>
              <tr>
                  <td>真实姓名：</td>
                  <td><input type="text" name="realName" value="${model.realname}" disabled="disabled"/></td>
              </tr>
           
<!--               <tr> -->
<!--                   <td>身份证：</td> -->
<%--                   <td><input type="text" name="idCard" value="${model.idcard}" disabled="disabled"/></td> --%>
<!--               </tr> -->

              <tr>
                  <td>经营范围：</td>
                  <td><input type="text" name="businessScope" value="${model.business_scope}" disabled="disabled"/></td>
              </tr>

              <tr>
                  <td>账户名称：</td>
                  <td><input type="text" name="accountName" value="${model.account_name}" disabled="disabled"/></td>
              </tr>
              <tr>
                  <td>银行卡号：</td>
                  <td><input type="text" name="bankCard" value="${model.bank_card}" disabled="disabled"/></td>
              </tr>
<!--               <tr> -->
<!--                   <td>开户行：</td> -->
<%--                   <td><input type="text" name="accountBank" value="${model.account_bank}" disabled="disabled"/></td> --%>
<!--               </tr> -->
            <tr>
                <td>提现密码：</td>
                <td>
                <input type="passWord" id="cashPassWordShow" name="cashPassWordShow" value="${model.cash_password}" placeholder="6~14位数字密码"/>
                <input type="hidden" id="cashPassWord" name="cashPassWord" value="${model.cash_password}"/>
                </td>
            </tr>
            <tr>
                <td>人均消费：</td>
                <td><input type="text"  name="consumption" value="${model.consumption}" id="consumption"/></td>
            </tr>
<!--             <tr> -->
<!--                 <td>账户类型：</td> -->
<!--                 <td> -->
<%--                     <input type="radio" disabled <c:if test="${model.account_type == 0}">checked="checked"</c:if> name="accountType" value="0">对公 --%>
<%--                     <input type="radio" disabled <c:if test="${model.account_type == 1}">checked="checked"</c:if> name="accountType" value="1">个人 --%>
<!--                 </td> -->
<!--             </tr> -->

            <tr>
                  <td>是否有营业执照：</td>
                  <td>
                      <input type="radio" disabled <c:if test="${model.is_license == 0}">checked="checked"</c:if> name="isLicense" value="0">否
                      <input type="radio" disabled <c:if test="${model.is_license == 1}">checked="checked"</c:if> name="isLicense" value="1">是
                  </td>
            </tr>
            <tr>
                <td>是否有无线：</td>
                <td>
                    <input type="radio" <c:if test="${model.is_wifi == 0}">checked="checked"</c:if> name="isWifi" value="0">否
                    <input type="radio" <c:if test="${model.is_wifi == 1}">checked="checked"</c:if> name="isWifi" value="1">是
                </td>
            </tr>
            <c:if test="${model.shop_id == 1}">
            <tr>
                <td>是否有浴室：</td>
                <td>
                    <input type="radio" <c:if test="${model.is_yushi == 0}">checked="checked"</c:if> name="isYushi" value="0">否
                    <input type="radio" <c:if test="${model.is_yushi == 1}">checked="checked"</c:if> name="isYushi" value="1">是
                </td>
            </tr>
            </c:if>
            <c:if test="${model.shop_id != 1}">
                <input type="hidden" name="isYushi" value="0">
            </c:if>
             <c:if test="${model.shop_id ==1 || model.shop_id ==2}">
            <tr>
                <td>是否有便利设施：</td>
                <td>
                    <input type="radio" <c:if test="${model.is_blss == 0}">checked="checked"</c:if> name="isBlss" value="0">否
                    <input type="radio" <c:if test="${model.is_blss == 1}">checked="checked"</c:if> name="isBlss" value="1">是
                </td>
            </tr>
            </c:if>
            <c:if test="${model.shop_id ==3 || model.shop_id ==4}">
            	 <input type="hidden" name="isBlss" value="0">
            </c:if>
            <tr>
                <td>是否有媒体或科技：</td>
                <td>
                    <input type="radio" <c:if test="${model.is_media == 0}">checked="checked"</c:if> name="isMedia" value="0">否
                    <input type="radio" <c:if test="${model.is_media == 1}">checked="checked"</c:if> name="isMedia" value="1">是
                </td>
            </tr>
            <tr>
                <td>是否有食品或饮品：</td>
                <td>
                    <input type="radio" <c:if test="${model.is_food == 0}">checked="checked"</c:if> name="isFood" value="0">否
                    <input type="radio" <c:if test="${model.is_food == 1}">checked="checked"</c:if> name="isFood" value="1">是
                </td>
            </tr>
<!--             	暂时没用 -->
<!--             <tr> -->
<!--                 <td>开始时间：</td>  -->
<%--                 <td><input type="text" id="startDate" name="startDate" class="easyui-datebox" style="width: 150px;height:30px;line-height:0px;padding:0px" value="${model.start_date}" required="required" /></td> --%>
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td>结束时间：</td> -->
<%--                 <td><input type="text" id="endDate" name="endDate" class="easyui-datebox" style="width: 150px;height:30px;line-height:0px;padding:0px" value="${model.end_date}" required="required" /></td> --%>
<!--             </tr> -->
            
            <tr>
                <td>头像：</td>
                <td id="img1">
                	<img id="headImgId" class="headImgId" name="headImgId" alt="" src="${model.head_img}">
                </td>
            </tr>
            <tr>
                <td>修改头像：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename1" class="filename" readonly="">
                        <input type="button" name="imageFile1" class="button"  value="上传...">
                        <input type="file" size="30" name="imageFile1" id="f1" onchange="tou()">
                    </div>
                </td>
            </tr>
            <tr>
                <td>背景图：</td>
                <td id="img2"><img id="backGroundId" class="backGroundId" name="backGroundId" src="${model.backgroud_img}" alt="暂时没有图片"></td>
            </tr>
            <tr>
                <td>修改背景图：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename2" class="filename" readonly="">
                        <input type="button" name="imageFile2" class="button" value="上传...">
                        <input type="file" size="30" name="imageFile2" id="f2">
                    </div>
                </td>
            </tr>
            <tr>
                <td>图集：</td>
                <td>
                    <c:forEach items="${picture}" var="picture">
                        <div style="float: left;">
                            <img class='imgUrl' alt="" src="${picture }" >
                            <c:if test="${picture != ''}">
                                <img src="${ctx}/images/xhao.png" style="width: 10px;height: 10px;vertical-align: top; margin-left: -14px;" onclick="deleteImg(this)">
                            </c:if>
                        </div>
                    </c:forEach>
                    <div id="pictureId"></div>
                    <input type="hidden" id="pictureUrl" name="pictureUrl">
                </td>
            </tr>
            <tr>
                <td>上传图集：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename" class="filename" readonly="">
                        <input type="button" name="imageFile" class="button" value="上传...">
                        <input type="file" size="30" id="imgFile" multiple="true" name="imgFile" onchange="uploadImg()">
                    </div>
                </td>
            </tr>
              <tr>
                  <td>店铺信息介绍：</td>
                  <td>
                      <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                      <input type="hidden" name="detailId" id="detailId" value="${model.detail_id}" />

                  </td>
              </tr>

        </table>
        <div style="text-align:center;padding:5px">
            <a id="showPageSaveBtn" href="javascript:void(0)" onclick="save()" class="easyui-linkbutton"
               iconCls="icon-save">确定</a>
            <%--<a id="showPageCancleBtn" href="javascript:void(0)" onclick="window.location.href ='./background/visitorsManage/toVisitorsManage?type=${model.type}'" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>--%>
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
		window.onload=function(){
		};
		function nameIsNotUpdate(){
			$("#nameIsNotUpdate").css('display','block'); 
		}
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
	
<%-- <script type="text/javascript" src="${ctx}/scripts/jquery/jquery-1.8.3.min.js"></script> --%>
<%-- <script type="text/javascript" src="${ctx}/scripts/jquery/jquery-2.0.3.js"></script> --%>
<script type="text/javascript">
    $(function(){
        $("#f1").change(function(){
           /*  $(this).parents(".uploader").find(".filename").val($(this).val()); */
            $("#filename1").val($(this).val());
            var file = this.files[0]; //选择上传的文件
            var r = new FileReader();
            r.readAsDataURL(file); //Base64
            $(r).load(function(){
           		$('#img1').html('<img src="'+ this.result +'" alt="" />');
            });
        });
        $("#f2").change(function(){
           /*  $(this).parents(".uploader").find(".filename").val($(this).val()); */
            $("#filename2").val($(this).val());
            var file = this.files[0]; //选择上传的文件
            var r = new FileReader();
            r.readAsDataURL(file); //Base64
            $(r).load(function(){
           		$('#img2').html('<img src="'+ this.result +'" alt="" />');
            });
        });
        $("input[type=file]").each(function(){
            if($(this).val()==""){
                $(this).parents(".uploader").find(".filename").val("请选择图片...");
            }
        });

    });

    $(function () {
        UE.getEditor('editor').addListener("ready", function () {
            UE.getEditor('editor').setContent('${htmlText.contentHtml}');
        })
    })

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');


    //获取内容
    function getAllHtml() {
        alert(UE.getEditor('editor').getContent())
    }

    //获取纯文本内容
    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }

    function save() {
    	var startDate = $("#startDate").val();
    	var endDate = $("#endDate").val();
    	var phone = $("#phone").val();
        var content = $("#content").val();
        var cashPassWord = $("#cashPassWord").val();
        var cashPassWordShow = $("#cashPassWordShow").val();
        var consumption = $("#consumption").val();
        var age = $("#age").val();
        
        var seq = /^\d{0,2}$/;
        if(cashPassWordShow != cashPassWord){
        	cashPassWord = cashPassWordShow;
        	$("#cashPassWord").val(cashPassWordShow);
        }
        if(isNaN(phone)){
			alert('请输入正确的手机号！')
			return false;
        }
        if(content == null || content == ''){
        	alert('请输入店铺内容介绍！')
        	return false;
        }
        if(!seq.test(age)){
        	alert('请输入正确的年龄！')
        	return false;
        }
        if(cashPassWordShow == null || cashPassWordShow == ''){
        	if(cashPassWordShow.length <= 6 || cashPassWordShow.length >= 14){
        	alert("请输入6~14位的数字密码！")	
        	return false;
        	}else{
        	alert('请输入提现密码！')
        	return false;
        	}
        }
        if(isNaN(consumption)){
        	alert('请输入人均消费！')
        	return false;
        }
//         if(startDate == null || startDate == ''){
//         	alert('请选择就餐开始时间！')
//         	return false;
//         }
//         if(endDate == null || endDate == ''){
// 			alert('请选择就餐结束时间！')
//         	return false;
//         }
        if($('#img1').html() == ''){
        	alert('请上传主图！')
        	return false;
        }
        if($('#img2').html() == ''){
        	alert('请上传店铺背景图片！')
        	return false;
        }
        
        
        
        var arr = [];
        arr.push(UE.getEditor('editor').getContentTxt());

        $.post("${basePath}/htmlText/addHtmlText", {
            id: $("#noticeId").val(), 
            contentText: arr.join("\n"),
            contentHtml: UE.getEditor('editor').getContent(),
            activityDetail: UE.getEditor('editor').getContent(),
            type: 3,
            state: 0
        }).success(function (data) {
            if (data.success) {
                if (data.obj != null)
                $("#detailId").val(data.obj);
                var imgUrl = "";
                $(".imgUrl").each(function () {
                    imgUrl += $(this).attr("src")+",";
                })
                $("#pictureUrl").val(imgUrl);
                var detailId = $("#detailId").val();
                if(detailId == "" || detailId == null){
                	alert("请填写店铺信息介绍！");
                	return false;
                }
                $('#ff').submit();
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