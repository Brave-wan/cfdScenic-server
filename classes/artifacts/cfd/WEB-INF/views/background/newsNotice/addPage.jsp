<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<style type="text/css">
	 body{font-family: "微软雅黑";}
    .tb{width:90%;margin:20px auto;}
    .tb td{height:45px;text-align:left;}
    .tb td:first-child{width:10%;}
    .tb td:last-child{width:90%;}
    .tb td input[type=text]{width:80%;height:30px;line-height: 30px;padding-left: 10px;}
    .tb img{width:150px;height:100px;}
    .tb td textarea{padding-left:10px;}
    .tb td label{float: left;width:100px;text-align: left;}
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


    <div style="width:98.5%;padding:10px;">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:平台管理-添加新闻须知</div>
        <form id="ff" action="./newsNotice/update" method="post" enctype="multipart/form-data">
            <table class="tb">
                <tr>
                    <td>名称：</td>
                    <td>
                    	<input type="text" name="name" id="name"/>
                    </td>
                </tr>
                <tr>
                    <td>描述：</td>
                    <td>
                    	<input type="text" name="newsDescribe" id="miaoshu"/>
                    </td>
                </tr>
                <tr id="img1">
                </tr>
               <tr>
                    <td>主图：</td>
                    <td> 
                    	<div class="uploader blue">
                    		<input type="text" id="filename" class="filename" readonly="">
                        	<input type="button" name="imageFile" class="button" value="上传...">
                        	<input type="file" size="30" name="imageFile">
                       </div>
                      </td>
                </tr>
                <tr>
                    <td>内容：</td>
                    <td>
                    	<input type="text" name="content" id="neirong"/>
                    </td>
                </tr>
                    	<input type="hidden" name="creator" value="1">
                    	<input type="hidden" name="creator_id" value="1">
                    	<input type="hidden" name="type" value="1">
                <tr>
                    <td>详情：</td>
                    <td>
                        <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                        <input type="hidden" name="contentUrl" id="contentId" />
                    </td>
                </tr>
            </table>
            <div style="text-align:center;padding:5px">
                <a id="showPageSaveBtn" href="javascript:void(0)" onclick="save()" class="easyui-linkbutton" iconCls="icon-save">确定</a>
                <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
            </div>
        </form>
    </div>

<script type="text/javascript" src="${ctx}/scripts/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function(){

    $("input[type=file]").change(function(){
        $(this).parents(".uploader").find(".filename").val($(this).val());
        var file = this.files[0]; //选择上传的文件
        var r = new FileReader();
        r.readAsDataURL(file); //Base64
        $(r).load(function(){
       		$('#img1').html('<td></td><td><img src="'+ this.result +'" alt="" /></td>');
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

    function save() {
    	var name = $("#name").val();
    	var miaoshu = $("#miaoshu").val();
    	var neirong = $("#neirong").val();
    	var contentId = UE.getEditor('editor').getContent();
    	if(name==""){
    		alert("请填写名称");
    		return;
    	}if(miaoshu==""){
    		alert("请填写描述");
    		return;
    	}
    	if(neirong==""){
    		alert("请填写内容");
    		return;
    	}
    	if(contentId==""){
    		alert("请填写详情");
    		return;
    	}
        var arr = [];
        arr.push(UE.getEditor('editor').getContentTxt());
        $.post("${basePath}/htmlText/addHtmlText",{
            contentText : arr.join("\n"),
            contentHtml : UE.getEditor('editor').getContent(),
            activityDetail : UE.getEditor('editor').getContent(),
            type:7
        }).success(function (data) {
            if (data.success) {
            	 $("#contentId").val(data.obj);
            	 $('#ff').submit();
            } else {
                eu.showMsg(data);
            }
        }).error(function (data) {
            eu.showMsg("系统错误，请联系管理员！");
        });

	}
   
</script>

<%@ include file="/WEB-INF/views-commons/footer.jsp"%>