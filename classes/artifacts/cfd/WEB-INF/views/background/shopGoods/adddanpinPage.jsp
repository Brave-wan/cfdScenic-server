<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
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

    .uploader { position:relative; display:inline-block; overflow:hidden; cursor:default; padding:0; margin:10px 0px; -moz-box-shadow:0px 0px 5px #ddd; -webkit-box-shadow:0px 0px 5px #ddd; box-shadow:0px 0px 5px #ddd; -moz-border-radius:5px; -webkit-border-radius:5px; border-radius:5px; }
    #filename { float:left; display:inline-block; outline:0 none; height:32px; width:180px; margin:0; padding:8px 10px; overflow:hidden; cursor:default; border:1px solid; border-right:0; font:9pt/100% Arial, Helvetica, sans-serif; color:#777; text-shadow:1px 1px 0px #fff; text-overflow:ellipsis; white-space:nowrap; -moz-border-radius:5px 0px 0px 5px; -webkit-border-radius:5px 0px 0px 5px; border-radius:5px 0px 0px 5px; background:#f5f5f5; background:-moz-linear-gradient(top, #fafafa 0%, #eee 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #fafafa), color-stop(100%, #f5f5f5)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#fafafa', endColorstr='#f5f5f5', GradientType=0);
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
</style>



<%--添加饭店--%>
<c:if test="${danpin==1}">
    <div style="width:98.5%;padding:10px;">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:商家管理-添加饭店-添加套餐</div>
        <form id="ff" action="./background/shopGoodsManage/adddanpin" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>套餐名称：</td>
                    <input type="hidden" name="type" value="2"/>
                     <input type="hidden" name="danpin" value="1"/>
                    <td><input type="text" name="goodsName" id="ming"
                     class="easyui-validatebox" required="true" missingMessage="请输入套餐名称"/></td>
                </tr>
               <!--  <tr>
                    <td>套餐描述：</td>
                    <td><input type="text" name="goodsDescribe" id="miao"
                     class="easyui-validatebox" required="true" missingMessage="请输入套餐描述"/></td>
                </tr>
                <tr>
                    <td>标签：</td>
                    <td><input type="text" name="label" id="biao"
                    class="easyui-validatebox" required="true" missingMessage="请输入标签"/></td>
                </tr> -->
                <tr>
                    <td>原价：</td>
                    <td><input id="yuan" type="text" name="price"
                    class="easyui-validatebox" required="true" missingMessage="原价"
                     onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" /></td>
                </tr>
                <tr>
                    <td>新价：</td>
                    <td><input id="xin" type="text" name="newPrice" 
                    class="easyui-validatebox" required="true" missingMessage="新价"
                    onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" /></td>
                </tr>
                <!-- <tr>
                    <td>配送费：</td>
                    <td><input type="text" name="deliverFee" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" /></td>
                </tr> -->
                
 				<input type="hidden" name="deliverFee" value="1"/>
                <input type="hidden" name="stock" value="1"/>
                <input type="hidden" name="brand" value="1"/>
                <tr>
                    <td>所属店铺：</td>
                    <td>
                        <input type="hidden" value="${shopInformation.id}" name="shopInformationId">
                        <input type="text" value="${shopInformation.name}" readonly>
                    </td>
                </tr>
<!--                 <tr> -->
<!--                     <td>是否推荐：</td> -->
<!--                     <td id="tui"> -->
<!--                         <input type="radio" checked="checked" id="isRecomment" name="isRecomment" value="0">否 -->
<!--                         <input type="radio" name="isRecomment" id="isRecomment" value="1">是 -->
<!--                     </td> -->
<!--                 </tr> -->
                <input type="hidden" id="isNotReturn" name="isNotReturn" value="0">
                <input type="hidden" id="isRecomment" name="isRecomment" value="0">
                <tr>
                    <td>是否热销：</td>
                    <td>
                        <input type="radio" checked="checked" name="isHot" value="0">否
                        <input type="radio" name="isHot" value="1">是
                    </td>
                </tr>
                <tr>
                    <td>状态：</td>
                    <td>
                        <input type="radio" checked="checked" name="state" value="0">正常
                        <input type="radio" name="state" value="1">下架
                    </td>
                </tr>
                <tr>
                    <td>库存：</td>
                    <td><input type="text" name="packageStock" /></td>
                </tr>
                <tr>
                	<td>单品:</td>
						<td>
							<table style="width: 600px;margin-left: 1px;">
								<tr>
					<c:forEach items="${list}" var="s" varStatus="vs">
									<td style="width: 150px;margin-left: 0px; "><img alt="" src="${s.describe_img}"> 
									<input type="checkbox" name="goodsIds" value="${s.id}">${s.goodsName}
									</td>
						<c:if test="${vs.count%4==0}"><br></c:if>
					</c:forEach>
								</tr>
							</table>
						</td>
					
                </tr>
                <tr>
                    <td>套餐图片：</td>
                    <td>
                        <div class="uploader blue">
                            <input type="text" id="filename" class="filename" readonly="">
                            <input type="button" name="imageFile" class="button" value="上传...">
                            <input type="file" size="30" name="imageFile">
                        </div>
                    </td>
                </tr>
                <tr id="img">
                </tr>
                <tr>
                    <td>详情：</td>
                    <td>
                        <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                        <input type="hidden" name="noticeId" id="noticeId" />

                    </td>
                </tr>
            </table>
            <div style="text-align:center;padding:5px">
                <a id="showPageSaveBtn" href="javascript:void(0)" onclick="save(1)" class="easyui-linkbutton" iconCls="icon-save">确定</a>
                    <%--<a id="showPageCancleBtn" href="javascript:void(0)" onclick="window.location.href ='./background/visitorsManage/toVisitorsManage'" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>--%>
                <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
            </div>
        </form>
    </div>
</c:if>
<c:if test="${danpin==0 }">
<div style="width:98.5%;padding:10px;">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:商家管理-添加饭店-添加单品</div>
        <form id="ff" action="./background/shopGoodsManage/adddanpin" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>单品名称：</td>
                    <td><input type="text" class="easyui-validatebox" id="ming1" 
                    required="true" missingMessage="请输入单品名称" name="goodsName"/>
                    </td>
                    <input type="hidden"  name="type" value="${type}"/>
                    <input type="hidden" name="danpin" value="${danpin}"/>
                </tr>
                <tr>
                    <td>单品描述：</td>
                    <td><input type="text" name="goodsDescribe" id="miao1" 
                    class="easyui-validatebox" required="true" missingMessage="请输入单品描述"/></td>
                </tr>
                <tr>
                    <td>标签：</td>
                    <td><input type="text" name="label" id="biao1" 
                    class="easyui-validatebox" required="true" missingMessage="请输入标签"/></td>
                </tr>
                <tr>
                    <td>原价：</td>
                    <td><input class="easyui-validatebox" required="true" missingMessage="请输入原价" id="yuan1" 
                    type="text" name="price" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" 
                    onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" /></td>
                </tr>
                <tr>
                    <td>新价：</td>
                    <td><input class="easyui-validatebox" required="true" missingMessage="请输入现价" id="xin1"
                    type="text" name="newPrice" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" 
                    onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" /></td>
                </tr>
                <input type="hidden" name="deliverFee" value="1"/>
                <input type="hidden" name="stock" value="1"/>
                <input type="hidden" name="brand" value="1"/>
                <tr>
                    <td>所属店铺：</td>
                    <td>
                        <input type="hidden" value="${shopInformation.id}" name="shopInformationId">
                        <input type="text" value="${shopInformation.name}" readonly>
                    </td>
                </tr>
                <tr>
                    <td>是否推荐：</td>
                    <td>
                        <input type="radio" checked="checked" id="isRecomment" name="isRecomment" value="0">否
                        <input type="radio" name="isRecomment" id="isRecomment" value="1">是
                    </td>
                </tr>
                <tr>
                    <td>是否热销：</td>
                    <td>
                        <input type="radio" checked="checked" name="isHot" value="0">否
                        <input type="radio" name="isHot" value="1">是
                    </td>
                </tr>
                <tr>
                    <td>状态：</td>
                    <td>
                        <input type="radio" checked="checked" name="state" value="0">正常
                        <input type="radio" name="state" value="1">下架
                    </td>
                </tr>
                <tr id="img">
                </tr>
                <tr>
                    <td>单品图片：</td>
                    <td>
                        <div class="uploader blue">
                            <input type="text" id="filename" class="filename" readonly="">
                            <input type="button" name="imageFile" class="button" value="上传...">
                            <input type="file" size="30" name="imageFile">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>详情：</td>
                    <td>
                        <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                        <input type="hidden" name="contentId" id="contentId" />

                    </td>
                </tr>
            </table>
            <div style="text-align:center;padding:5px">
                <a id="showPageSaveBtn" href="javascript:void(0)" onclick="save(2)" class="easyui-linkbutton" iconCls="icon-save">确定</a>
                <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
            </div>
        </form>
    </div>

</c:if>





<script type="text/javascript" src="${ctx}/scripts/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

    $(function(){

        $("input[type=file]").change(function(){
            $(this).parents(".uploader").find(".filename").val($(this).val());
            var file = this.files[0]; //选择上传的文件
            var r = new FileReader();
            r.readAsDataURL(file); //Base64
            $(r).load(function(){
           		$('#img').html('<td></td><td><img src="'+ this.result +'" alt="" /></td>');
            });
        });

        $("input[type=file]").each(function(){
            if($(this).val()==""){
                $(this).parents(".uploader").find(".filename").val("请选择图片...");
            }
        });

    });

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

    function save(obj) {
    	var isRecomment = $("input[name='isRecomment']:checked").val();
    	if(isRecomment == 1){
			alert("只能推荐小吃或者特产！");
			return false; 
		}
    	if(obj==2){
    		var ming = $("#ming1").val();
    		if(ming==""){
    			alert("请输入单品名称");
    			return;
    		}
    		var miao = $("#miao1").val();
    		if(miao==""){
    			alert("请输入单品描述");
    			return;
    		}
    		var biao = $("#biao1").val();
    		if(biao==""){
    			alert("请输入标签");
    			return;
    		}
    		var yuan = $("#yuan1").val();
    		if(yuan==""){
    			alert("请输入原价");
    			return;
    		}
    		var xianjia = $("#xianjia1").val();
    		if(xianjia==""){
    			alert("请输入现价");
    			return;
    		}
    	}else{
    		var ming = $("#ming").val();
    		if(ming==""){
    			alert("请输入套餐名称");
    			return;
    		}
    		var miao = $("#miao").val();
    		if(miao==""){
    			alert("请输入套餐描述");
    			return;
    		}
    		var biao = $("#biao").val();
    		if(biao==""){
    			alert("请输入标签");
    			return;
    		}
    		var yuan = $("#yuan").val();
    		if(yuan==""){
    			alert("请输入原价");
    			return;
    		}
    		var xianjia = $("#xianjia").val();
    		if(xianjia==""){
    			alert("请输入现价");
    			return;
    		}
    		if($("input[type='checkbox']").is(':checked')){
        		
        	}else{
        		alert("请选择单品");
        		return;
        	}
    	}
        var arr = [];
        arr.push(UE.getEditor('editor').getContentTxt());

        $.post("${basePath}/htmlText/addHtmlText",{
            contentText : arr.join("\n"),
            contentHtml : UE.getEditor('editor').getContent(),
            activityDetail : UE.getEditor('editor').getContent(),
            type : 2,
            state : 0
        }).success(function (data) {
            if (data.success) {
                $("#contentId").val(data.obj);
                //$('#typeId').removeAttr("disabled");
                $('#ff').submit();
            } else {
                eu.showMsg(data);
            }
        }).error(function (data) {
            eu.showMsg("系统错误，请联系管理员！");
        })

	}
   
</script>

<%@ include file="/WEB-INF/views-commons/footer.jsp"%>