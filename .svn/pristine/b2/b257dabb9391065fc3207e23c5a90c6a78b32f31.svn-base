<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curLoca"></i><font class="fontbold">当前位置:</font>订单管理-退款订单管理
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
				<label for="searchId">用户名：</label> <input id="searchId"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchId1">手机号：</label> <input id="searchId1"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchId2">订单类型：</label> <select id="searchId2"
					class="easyui-combobox"
					data-options="panelHeight: 'auto',editable:false">
					<option checked value="1">商品订单</option>
					<option value="2">酒店订单</option>
					<option value="3">饭店订单</option>
				</select>
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="refundOrderManager.search()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>退款申请审核</h2>
				<em></em>
			</div>
		</div>
	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="refundOrderManagerGid">
				</table>
			</div>
		</div>
	</div>
	
	<div id="refundOrderWindow" class="easyui-window" title="审核"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="refundOrderForm" method="post">
				<input type="hidden" name="mId" id="mId"/>
				<input type="hidden" name="uId" id="uId"/>
				<input type="hidden" name="oId" id="oId"/>
				<input type="hidden" name="sId" id="sId"/>
				<input type="hidden" name="nickName" id="nickName"/>
				<input type="hidden" name="mobile" id="mobile"/>
				<input type="hidden" name="price" id="price"/>
                <p>
                    <label class="bolb">审核结果:</label> 
                    <label class="bolb"><input type="radio" checked="checked" name="state" value="1">审核通过</label> 
<!--                     <label class="bolb"><input type="radio" name="state" value="2">审核不通过</label>  -->
                </p>
<!--                 <p> -->
<!--                     <label class="bolb">审核原因:</label>  -->
<!--                    <textarea name="cause" id="cause" cols="100" rows="8" style="width:250px;height:100px;"></textarea> -->
<!--                 </p> -->
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="refundOrderManager.update()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="$('#refundOrderWindow').window('close');$('#refundOrderForm').form('clear');">取消</a>
		</div>
	</div>
</div>
</div>

<script type="text/javascript"
	src="scripts/opensource/background/Order/refundOrderManager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
