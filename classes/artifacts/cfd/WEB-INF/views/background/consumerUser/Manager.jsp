<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
                <i class="curLoca"></i><font class="fontbold">当前位置:</font>账号管理-用户管理
			</div>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>查询条件</h2>
				<em></em>
			</div>
		</div>
		<div class="querydiv">
			<p>
				<label for="searchValueNickName">昵称：</label> <input id="searchValue1"
					class="easyui-validatebox">
			</p>
            <p>
				<label for="searchValueMobileNo">手机号：</label> <input id="searchValue2"
					class="easyui-validatebox">
			</p>
            <p>
				<label for="searchValueNickName">状态：</label> <select id="searchValue3"
                    class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
                        <option checked value="">请选择状态</option>
                        <option  value="0">可用</option>
                        <option  value="1">停用</option>
                    </select>
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="consumerUserManager.search()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>用户设置</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			<a id="shareRuleDelBtn" href="javascript:void(0)"
				onclick="consumerUserManager.using()" class="easyui-linkbutton"
				iconCls="icon-remove">启用</a> <a id="shareRuleDelBtn"
				href="javascript:void(0)" onclick="consumerUserManager.del()"
				class="easyui-linkbutton" iconCls="icon-add">停用</a>
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="consumerUserGid" >
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript"
	src="scripts/opensource/background/consumerUser/Manager.js?id=123"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
