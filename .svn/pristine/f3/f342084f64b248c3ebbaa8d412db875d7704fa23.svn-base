<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
                    <i class="curLoca"></i><font class="fontbold">当前位置:</font>结算管理-提现申请审核
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
				<label for="searchValueName">昵称：</label> <input id="searchValue1"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchValuePhone">手机：</label> <input id="searchValue2"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchValueCreateTime">交易日期：</label> 
					<input id="searchValue3" class="easyui-datebox" style="width: 150px;"/>
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="withdrawalApplyManage.search()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>提现申请审核</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			<%--<a id="shareRuleAddBtn" href="javascript:void(0)"
				onclick="userAccountManage.add()" class="easyui-linkbutton"
				iconCls="icon-add">新增</a> <a id="shareRuleEditBtn"
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
				<table id="withdrawalApplyGid" >
				</table>
			</div>
		</div>
	</div>
	
	<div id="withdrawalApplyWindow" class="easyui-window" title="审核"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="withdrawalApplyForm" method="post">
				<input type="hidden" name="logId" id="logId"/>
				<input type="hidden" name="uId" id="uId"/>
				<input type="hidden" name="wdaname" id="wdaname"/>
				<input type="hidden" name="balance" id="balance"/>
				<input type="hidden" name="beginBalance" id="beginBalance"/>
                <p>
                    <label class="bolb">审核结果:</label> 
                    <label class="bolb"><input type="radio" checked="checked" name="state" value="1">审核通过</label> 
                    <label class="bolb"><input type="radio" name="state" value="2">审核不通过</label> 
                </p>
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="withdrawalApplyManage.update()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="$('#withdrawalApplyWindow').window('close');$('#withdrawalApplyForm').form('clear');">取消</a>
		</div>
	</div>
</div>
	
	
</div>


<script type="text/javascript"
	src="scripts/opensource/background/withdrawalApply/Manager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
