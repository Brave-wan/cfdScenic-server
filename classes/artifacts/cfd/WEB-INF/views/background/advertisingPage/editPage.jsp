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
		<div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:系统管理-修改详情</div>
			<form id="ff" action="./background/advertisingPage/saveAdvertisingPage" method="post" enctype="multipart/form-data">
				<input type="hidden" id="id" name="id" value="${model.id}">
				<table class="tb">
					<tr>
						<td>名称：</td>
						<td><input type="text" id="name" name="name" value="${model.name}" required="required"/>
						</td>
					</tr>
					 <tr>
						<td>类型：</td>
						<td>
                            <select name="type" style="width:100px">
                                <option <c:if test="${model.type == 1}">selected</c:if> value="1">观鸟景点</option>
                                <option <c:if test="${model.type == 2}">selected</c:if> value="2">湿地保护</option>
                                <option <c:if test="${model.type == 3}">selected</c:if> value="3">招商信息</option>
                            </select>
                        </td>
					</tr>
					<tr>
						<td>详情：</td>
						<td><script id="editor1" type="text/plain" value="${model.contentHtml}"
						style="width: 1024px; height: 500px;"></script> <input
						type="hidden" name="htmlId" id="htmlId"/></td>
					</tr>
					<input style="display: none;"  type="submit" id="sub" value="submit" />
				</table>
				<div style="text-align:center;padding:5px">
 					<a id="showPageSaveBtn" href="javascript:void(0)" onclick="tijiao()" class="easyui-linkbutton" iconCls="icon-save">确定</a>
					<%--<a id="showPageCancleBtn" href="javascript:void(0)" onclick="window.location.href ='./background/visitorsManage/toVisitorsManage'" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>--%>
					<a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
			    </div>
			</form>
</div>
<script type="text/javascript">
$(function () {
    UE.getEditor('editor1').addListener("ready", function () {
        UE.getEditor('editor1').setContent('${model.contentHtml}');
    })
})

function tijiao(){
	 var arr1 = [];
     arr1.push(UE.getEditor('editor1').getContentTxt());
	
	$.post("${basePath}/htmlText/addHtmlText1",{
        contentText : arr1.join("\n"),
        contentHtml : UE.getEditor('editor1').getContent(),
        activityDetail : UE.getEditor('editor1').getContent(),
        type : 8,
        state : 0
    }).success(function (data) {
                if (data.success) {
                    $("#htmlId").val(data.obj);
                    if ($("#htmlId").val() == null || $("#htmlId").val() == '') {
                    	eu.showMsg("请填写详情信息");
                        return;
                    }else{
                    	if(UE.getEditor('editor1').getContentTxt() == ''){
	                    	eu.showMsg("请填写详情信息");
	                    	return false;
                    	}
                    }
                    if($("#name").val() == null || $("#name").val() == ''){
                    	eu.showMsg("请填写名称");
                    	return false;
                    }
                    $("#sub").click();
                } else {
                    eu.showMsg(data);
                }
    }).error(function (data) {
        eu.showMsg("系统错误，请联系管理员！");
    })
}

//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('editor1');


//获取内容
function getAllHtml() {
    alert(UE.getEditor('editor1').getContent())
}

//获取纯文本内容
function getContentTxt() {
    var arr1 = [];
    arr1.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
    arr1.push("编辑器的纯文本内容为：");
    arr1.push(UE.getEditor('editor1').getContentTxt());
    alert(arr1.join("\n"));
}
</script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>