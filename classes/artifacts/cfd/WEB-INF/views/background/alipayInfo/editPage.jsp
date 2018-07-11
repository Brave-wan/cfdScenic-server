<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<%@ include file="/WEB-INF/views-commons/taglibs.jsp"%>
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
	width: 20%;
	height: 30px;
	clear: center;
	float: left;
	margin: 1em;
	margin-left:15%;
}

.uploader {
	position: relative;
	display: inline-block;
	overflow: hidden;
	cursor: default;
	padding: 0;
	margin: 10px 0px;
	-moz-box-shadow: 0px 0px 5px #ddd;
	-webkit-box-shadow: 0px 0px 5px #ddd;
	box-shadow: 0px 0px 5px #ddd;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
}

.button {
	float: left;
	height: 32px;
	display: inline-block;
	outline: 0 none;
	padding: 8px 12px;
	margin: 0;
	cursor: pointer;
	border: 1px solid;
	font: bold 9pt/100% Arial, Helvetica, sans-serif;
	-moz-border-radius: 0px 5px 5px 0px;
	-webkit-border-radius: 0px 5px 5px 0px;
	border-radius: 0px 5px 5px 0px;
	-moz-box-shadow: 0px 0px 1px #fff inset;
	-webkit-box-shadow: 0px 0px 1px #fff inset;
	box-shadow: 0px 0px 1px #fff inset;
}

.uploader input[type=file] {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	border: 0;
	padding: 0;
	margin: 0;
	height: 30px;
	cursor: pointer;
	filter: alpha(opacity = 0);
	-moz-opacity: 0;
	-khtml-opacity: 0;
	opacity: 0;
}

input[type=button]::-moz-focus-inner {
	padding: 0;
	border: 0 none;
	-moz-box-sizing: content-box;
}

input[type=button]::-webkit-focus-inner {
	padding: 0;
	border: 0 none;
	-webkit-box-sizing: content-box;
}

input[type=text]::-moz-focus-inner {
	padding: 0;
	border: 0 none;
	-moz-box-sizing: content-box;
}

input[type=text]::-webkit-focus-inner {
	padding: 0;
	border: 0 none;
	-webkit-box-sizing: content-box;
}
/* White Color Scheme ------------------------ */
.white .button {
	color: #555;
	text-shadow: 1px 1px 0px #fff;
	background: #ddd;
	background: -moz-linear-gradient(top, #eeeeee 0%, #dddddd 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #eeeeee),
		color-stop(100%, #dddddd));
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#eeeeee',
		endColorstr='#dddddd', GradientType=0);
	border-color: #ccc;
}

.white:hover .button {
	background: #eee;
	background: -moz-linear-gradient(top, #dddddd 0%, #eeeeee 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #dddddd),
		color-stop(100%, #eeeeee));
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#dddddd',
		endColorstr='#eeeeee', GradientType=0);
}
/* Blue Color Scheme ------------------------ */
.blue .button {
	color: #fff;
	text-shadow: 1px 1px 0px #09365f;
	background: #064884;
	background: -moz-linear-gradient(top, #3b75b4 0%, #064884 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #3b75b4),
		color-stop(100%, #064884));
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#3b75b4',
		endColorstr='#064884', GradientType=0);
	border-color: #09365f;
}

.blue:hover .button {
	background: #3b75b4;
	background: -moz-linear-gradient(top, #064884 0%, #3b75b4 100%);
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #064884),
		color-stop(100%, #3b75b4));
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#064884',
		endColorstr='#3b75b4', GradientType=0);
}
</style>
<div style="width: 98.5%; padding: 10px;">
	<div style="padding: 8px 2px; border-bottom: 1px solid #ccc">当前位置:商家管理-支付宝信息</div>
	<form id="ff"
		action="./background/shopInformationManager/editAlipayInfo"
		method="post">
		<input type="hidden" name="id" id="id" value="${model.id}"/>
		<input type="hidden" name="shopUserId" id="shopUserId" value="${model.shopUserId}"/>
		<table class="tb">
			<tr>
				<td>partner值：</td>
				<td><input type="text" name="partner" value="${model.partner}" id="partner" disabled="true"/></td>
			</tr>

			<tr>
				<td>seller值：</td>
				<td><input type="text" name="seller" value="${model.seller}" id="seller" disabled="true"/></td>
			</tr>
			<tr>
				<td>privateKey值：</td>
				<td><input type="text" name="privateKey" id="privateKey" value="${model.privateKey}" disabled="true"/></td>
			</tr>
		</table>
		<div data-options="region:'south',border:false" style="text-align: right; padding: 5px 0 0;">
			<a id="showPageSaveBtn" href="javascript:void(0)" onclick="save()"
				class="easyui-linkbutton" iconCls="icon-save">保存</a>
			<a id="edit" href="javascript:void(0)"
				onclick="editAlipayInfo(0)" class="easyui-linkbutton"
				iconCls="icon-add" style="display:block">编辑</a>
			<a id="editNo" href="javascript:void(0)"
				onclick="editAlipayInfo(1)" class="easyui-linkbutton"
				iconCls="icon-redo" style="display:none">取消编辑</a>
		</div>
	</form>
</div>
<script>
	function editAlipayInfo(obj){
		if(obj == 0){
			$('#partner').attr("disabled",false);
			$('#seller').attr("disabled",false);
			$('#privateKey').attr("disabled",false);
			$('#edit').css('display','none');
			$('#editNo').css('display','block');
		}else{
			$('#partner').attr("disabled",true);
			$('#seller').attr("disabled",true);
			$('#privateKey').attr("disabled",true);
			$('#edit').css('display','block');
			$('#editNo').css('display','none');
		}
	}
	function save(){
		var id = $("#id").val();
		var shopUserId = $("#shopUserId").val();
		var partner = $("#partner").val();
		var seller = $("#seller").val();
		var privateKey = $("#privateKey").val();
		$.post("${basePath}/background/shopInformationManager/editAlipayInfo", {
			id:id,
			shopUserId:shopUserId,
			partner:partner,
			seller:seller,
			privateKey:privateKey
        }).success(function (data) {
            if (data.success) {
            	alert("保存成功！");
            	window.location.reload();
            } else {
                eu.showMsg(data);
            }
        }).error(function (data) {
            eu.showMsg("系统错误，请联系管理员！");
        })
	}
</script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>