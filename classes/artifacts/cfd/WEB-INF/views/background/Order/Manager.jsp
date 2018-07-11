<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curLoca"></i><font class="fontbold">当前位置:</font>订单-达人申请审核
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
				<label for="searchValue">查询类别：</label> <select id="searchValue"
					class="easyui-combobox"
					data-options="panelHeight: 'auto',editable:false">
					<option checked value="1">嗨旅行订单</option>
					<option value="2">嗨服务订单</option>
				</select>
			</p>
			<p>
				<label for="searchValue">退款状态：</label> <select id="searchValue1"
					class="easyui-combobox"
					data-options="panelHeight: 'auto',editable:false">
					<option checked value="4">申请中</option>
					<option value="5">退款申请不通过</option>
					<option value="6">退款申请通过</option>
				</select>
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="refundManager.serch()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>用户申请审核</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			<a id="baseCompanyManagerUserBtn" href="javascript:void(0)"
				onclick="refundManager.toCheck(1)" class="easyui-linkbutton"
				iconCls="icon-redo">退款申请通过</a> <a id="baseCompanyManagerRoleBtn"
				href="javascript:void(0)" onclick="refundManager.toCheck(2)"
				class="easyui-linkbutton" iconCls="icon-redo">退款申请不通过</a>
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="refundManagerGid">
				</table>
			</div>
		</div>
	</div>
	<input type="hidden" id="nowType" value="1" />
</div>

<div id="refundManagerWindow" class="easyui-window" title="设置服务价格" data-options="closed:true,inline:true,onOpen:refundManager.clearForm"
	style="width: 420px; height: 200px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="refundManagerForm">
				<p>
					<label class="bolb">退款不通过原因:</label>
					<input id="orderCause"  type="text" class="easyui-validatebox sleek"
								data-options="required:true" maxlength="300"/>
				</p>
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="refundManager.submit()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="refundManager.cancel()">取消</a>
		</div>
	</div>
	<input type="hidden" id="nowId" value="-1" />
</div>
<script type="text/javascript"
	src="scripts/opensource/background/Order/Manager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
