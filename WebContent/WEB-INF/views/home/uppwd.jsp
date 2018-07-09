
<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<style type="text/css">
    body{font-family: "微软雅黑";}
    table{width:90%;margin:20px auto;}
    table td{height:45px;text-align:left;}
    table td:first-child{width:10%;}
    table td:last-child{width:90%;}
    table td input[type=text]{width:80%;height:30px;line-height: 30px;padding-left: 10px;}
    table td input[type=password]{width:80%;height:30px;line-height: 30px;padding-left: 10px;}
    table img{width:150px;height:100px;}
    table td textarea{padding-left:10px;}
    table td label{float: left;width:100px;text-align: left;}
    .l-btn-text{line-height: 30px;}
    .easyui-linkbutton{width:30%;height:30px;display: inline-block;}
</style>

<div style="width:98.5%;padding:10px;">
    <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:账号管理-修改密码</div>
    <form id="editForm" method="post">
        <input type="hidden" name="id" value="${model.id}"/>
        <table>
            <tr>
                <td>请输入旧密码：</td>
                <td><input type="text" id="pwd" name="realName"></td>
            </tr>
            <tr>
                <td>请输入新密码：</td>
                <td><input type="password" id="pwd1"></td>
            </tr>
            <tr>
                <td>请输入确认密码：</td>
                <td><input type="password" name="passWord" id="pwd2"></td>
            </tr>
        </table>
        <div style="text-align:center;padding:5px">
            <a id="showPageSubBtn" href="javascript:void(0)" onclick="addSub()" class="easyui-linkbutton" iconCls="icon-save">确定</a>
            <a id="showPageCancelBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
        </div>
    </form>
</div>

<script type="text/javascript">
     function addSub() {
        var pwd = $("#pwd").val();
        var pwd1 = $("#pwd1").val();
        var pwd2 = $("#pwd2").val();
        if(pwd==""){
            eu.showMsg("请输入旧密码");
            return;
        }else{
            if(/^[0-9a-zA-Z_]{6,16}$/.test(pwd)){
            }else{
                eu.showMsg("请输入6-16位旧密码，仅支持数字和字母");
                return;
            }
        }
        if(pwd1==""){
            eu.showMsg("请输入新密码");
            return;
        }else{
            if(/^[0-9a-zA-Z_]{6,16}$/.test(pwd1)){
            }else{
                eu.showMsg("请输入6-16位新密码，仅支持数字和字母");
                return;
            }
        }
        if(pwd2==""){
            eu.showMsg("请输入确认密码");
            return;
        }else{
            if(pwd1!=pwd2){
                eu.showMsg("确认密码与新密码不一致");
                return;
            }
        }
        $.post("${basePath}/ShopUserPcController/uppwd", $("#editForm").serialize()).success(function(data){
            if (data.success) {
                eu.showMsg("密码修改成功!");
                $("#editForm").form("clear");
            } else {
                eu.showMsg(data.message);
            }
        }).error(function(ex){
            $.messager.progress('close');
            eu.showMsg("系统错误！");
        });
    }
</script>

<%--
<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
					<i class="curLoca"></i><font class="fontbold">当前位置:</font>密码修改
			</div>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<em></em>
			</div>
		</div>
		<div class="querydiv">
			<p>

			</p>
			<p>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
			</div>
		</div>
		<div class="menubtndiv">
			<a id="shareRuleEditBtn"
				href="javascript:void(0)" onclick="visitorsManager.edit()"
				class="easyui-linkbutton" iconCls="icon-redo">修改密码</a>
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="visitorsGid" >
				</table>
			</div>
		</div>
	</div>
</div>
	<div id="editWindow" class="easyui-window" title="修改密码"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="editForm" method="post">
			<table>
				<input type="hidden" name="id" id="id"/>
				<tr>
				<td>
				请输入旧密码</td>
				<td>
					<input type="text" id="pwd" name="realName">
					<!-- 借用 进行新旧密码比对 -->
				</td>
				</tr>
				<tr>
				<td>请输入新密码</td>
				<td>
					<input type="password" id="pwd1">
				</td>
				</tr>
				<tr>
				<td>请输入确认密码</td>
				<td>
					<input type="password" name="passWord" id="pwd2">
				</td>
				</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="visitorsManager.addSub()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="visitorsManager.cancel()">取消</a>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="scripts/opensource/background/home/uppwd.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>

--%>

