<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
                    <i class="curLoca"></i><font class="fontbold">当前位置:</font>消息中心-商铺推送消息
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
				<label for="searchValueMobileNo">店铺名称：</label> <input id="searchValue3"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchValueNickName">昵称：</label> <input id="searchValue1"
					class="easyui-validatebox">
			</p>
            <p>
				<label for="searchValueMobileNo">手机号：</label> <input id="searchValue2"
					class="easyui-validatebox">
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="massMessageManage.search()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>推送消息列表</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			<a id="shareRuleAddBtn" href="javascript:void(0)"
				onclick="massMessageManage.toSend(0)" class="easyui-linkbutton"
				iconCls="icon-add">发送消息</a> 
				<%--<a id="shareRuleEditBtn"
				href="javascript:void(0)" onclick="userAccountManage.edit()"
				class="easyui-linkbutton" iconCls="icon-redo">修改</a><a
				id="shareRuleDelBtn" href="javascript:void(0)"
				onclick="userAccountManage.del()" class="easyui-linkbutton"
				iconCls="icon-remove">删除</a><a id="shareRuleDelBtn"
				href="javascript:void(0)" onclick="userAccountManage.yl()"
				class="easyui-linkbutton" iconCls="icon-add">预览</a>--%>
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="shopUserGid" >
				</table>
			</div>
		</div>
	</div>

</div>


<script type="text/javascript"
	src="scripts/opensource/background/pushMessage/MassManager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
