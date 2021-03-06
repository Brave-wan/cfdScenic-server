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
    <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:广告管理-修改广告</div>
    <form id="ff" action="./background/advertisManage/addAdvertis" method="post" enctype="multipart/form-data">
        <input type="hidden" value="${model.id }" name="id"/>
        <table class="tb">
            <tr>
                <td>名称：</td>
                <td><input type="text" name="title" value="${model.title }" required="required"/>
                </td>
            </tr>
            <tr>
            <tr>
					<td>轮播图来源：</td>
					<td>
					<select name="source" >
						<option value="1" <c:if test="${model.source==1}">selected</c:if> >景点</option>
						<option value="2" <c:if test="${model.source==2}">selected</c:if> >酒店</option>
						<option value="3" <c:if test="${model.source==3}">selected</c:if> >饭店</option>
						<option value="4" <c:if test="${model.source==4}">selected</c:if> >小吃</option>
						<option value="5" <c:if test="${model.source==5}">selected</c:if> >特产</option>
						<option value="6" <c:if test="${model.source==6}">selected</c:if> >活动</option>
						<option value="7" <c:if test="${model.source==7}">selected</c:if> >结伴游</option>
						<option value="8" <c:if test="${model.source==8}">selected</c:if> >app首页轮播图</option>
						<option value="9" <c:if test="${model.source==9}">selected</c:if> >商城轮播图</option>
					</select></td>
			</tr>
            <tr>
                <td>状态：</td>
                <td>

                    <input type="radio"
                    <c:if test="${model.state ==0 }"> checked</c:if> name="state" value="0">可用
                    <input type="radio"
                    <c:if test="${model.state ==1 }"> checked</c:if> name="state" value="1">不可用

                </td>
            </tr>
            <tr>
                <td>备注：</td>
                <td><input type="text" name="memo" value="${model.memo }" required="required"/></td>
                 <input type="hidden" value="${model.type}" id="typeId" name="type"/>
                 <input type="hidden" name="advertisementType" value="1"/>
            </tr>
           
              <%-- 
            <tr>
                <td>类型：</td>
                <td>
                   
                  <select name="type">
                        <option value="0"
                                <c:if test="${model.type==0 }">selected</c:if> >轮播图
                        </option>
                        <option value="1"
                                <c:if test="${model.type==1 }">selected</c:if> >广告位
                        </option>
                        <option value="2"
                                <c:if test="${model.type==2 }">selected</c:if> >商品图片
                        </option>
                    </select> --%>
            <tr>
                <td>主图：</td>
                <td id="img">
                    <img alt="" src="${model.imgUrl }">
                </td>
            </tr>
            <tr>
                <td>修改主图：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename" class="filename" readonly="">
                        <input type="button" name="imageFile" class="button" value="上传...">
                        <input type="file" size="30" name="imageFile">
                    </div>
                </td>
            </tr>
            <tr>
				<td>描述：</td>
				<td><input type="text" name="advertDescribe" value="${model.advertDescribe }" required="required"/></td>
			</tr>
        </table>
        <input style="display: none;" type="submit" id="sub" value="submit" />
        <div style="text-align:center;padding:5px">
            <a id="showPageSaveBtn" href="javascript:void(0)" onclick="tijiao()" class="easyui-linkbutton"
               iconCls="icon-save">确定</a>
            <%--<a id="showPageCancleBtn" href="javascript:void(0)" onclick="window.location.href ='./background/visitorsManage/toVisitorsManage?type=${model.type}'" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>--%>
            <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton"
               iconCls="icon-cancel">取消</a>
        </div>
    </form>
</div>
<script type="text/javascript">

    $(function(){

        $("input[type=file]").change(function(){
            $(this).parents(".uploader").find(".filename").val($(this).val());
            var file = this.files[0]; //选择上传的文件
            var r = new FileReader();
            r.readAsDataURL(file); //Base64
            $(r).load(function(){
           		$('#img').html('<img src="'+ this.result +'" alt="" />');
            });
        });

        $("input[type=file]").each(function(){
            if($(this).val()==""){
                $(this).parents(".uploader").find(".filename").val("请选择图片...");
            }
        });
    });
    function tijiao(){
    	$("#sub").click();
    }
</script>
<%@ include file="/WEB-INF/views-commons/footer.jsp" %>