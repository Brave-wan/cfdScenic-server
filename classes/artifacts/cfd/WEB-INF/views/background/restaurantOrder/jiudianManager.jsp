<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curLoca"></i><font class="fontbold">当前位置:</font>订单管理-酒店订单管理
			</div>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>查询条件</h2>
				<em></em>
			</div>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>酒店订单设置</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			<!--  <a id="shareRuleYlBtn"
				href="javascript:void(0)" onclick="restaurantOrderManager.yl()" 
				class="easyui-linkbutton" iconCls="icon-add">预览</a> -->
				<a id="shareRuleYlBtn"
				href="javascript:void(0)" onclick="restaurantOrderManager.kaip()" 
				class="easyui-linkbutton" iconCls="icon-add">开票</a>
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="restaurantOrderGid" >
				</table>
			</div>
		</div>
	</div>
</div>

<div id="ImageSubWindow" class="easyui-window" title="订单状态"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="uploadForm" method="post">
				<input type="hidden" id="orderCode" />
                <input type="hidden" id="realPrice" />
                <input type="hidden" id="userId" />
				<p>
					<label class="bolb">订单状态</label>
					<select id="orderState" class="easyui-combobox" data-options="panelHeight: 'auto',editable:false">
                        <option value="6">退款成功</option>
                        <option value="7">退款失败</option>
					</select>
				</p>
					
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)"
				onclick="restaurantOrderManager.submit()">确定</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#ImageSubWindow').window('close');$('#uploadForm').form('clear');">取消</a>
		</div>
	</div>
</div>
<div id="yanpiaoWindow" class="easyui-window" title="验票"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="yanpiaoForm" method="post">
				<input type="hidden" id="yanpiaoId" />
					
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)"
				onclick="restaurantOrderManager.yanpiaoSub()">确定</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#yanpiaoWindow').window('close');$('#yanpiaoForm').form('clear');">取消</a>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="scripts/opensource/background/restaurantOrder/jiudianManager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
