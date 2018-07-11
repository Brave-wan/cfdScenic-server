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
				<label for="searchValue">嗨旅行订单状态：</label> <select id="searchValue1"
					class="easyui-combobox"
					data-options="panelHeight: 'auto',editable:false">
					<option checked value="">全部</option>
					<option value="1">已提交</option>
					<option value="2">已完成</option>
					<option value="3">已评论</option>
					<option value="4">申请中</option>
					<option value="5">退款申请不通过</option>
					<option value="6">退款申请通过</option>
					<option value="8">已结算</option>
				</select>
			</p>
			<p>
				<label for="searchValue">嗨服务订单状态：</label> <select id="searchValue5"
					class="easyui-combobox"
					data-options="panelHeight: 'auto',editable:false">
					<option checked value="">全部</option>
					<option value="1">已提交</option>
					<option value="2">已接单</option>
					<option value="9">已经点击提醒接单</option>
					<option value="3">已汇合</option>
					<option value="4">已完成</option>
					<option value="5">申请退款中</option>
					<option value="6">退款申请通过</option>
					<option value="7">申请退款失败</option>
					<option value="8">已结算</option>
				</select>
			</p>
			<p>
				<label for="searchValue">嗨服务订单类型：</label> <select id="searchValue6"
					class="easyui-combobox"
					data-options="panelHeight: 'auto',editable:false">
					<option checked value="">全部</option>
					<option value="0">达人</option>
					<option value="1">导游</option>
					<option value="2">司机</option>
				</select>
			</p>
			<p>
				<label for="searchValue">嗨服务保险：</label> <select id="searchValue7"
					class="easyui-combobox"
					data-options="panelHeight: 'auto',editable:false">
					<option checked value="">全部</option>
					<option value="0">未入保险</option>
					<option value="1">已入保险</option>
				</select>
			</p>
			<p>
				<label for="searchValueNickName">订单号：</label> <input id="searchValue2"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchValueNickName">下单人昵称：</label> <input id="searchValue3"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchValueNickName">订单金额：</label> <input id="searchValue4"
					class="easyui-validatebox">
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="orderInfoManager.search()">查询</a>
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
				onclick="orderInfoManager.del()" class="easyui-linkbutton"
				iconCls="icon-remove">删除</a>
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="orderInfoManagerGid">
				</table>
			</div>
		</div>
	</div>
	<input type="hidden" id="nowType" value="1" />
</div>
<script type="text/javascript" src="scripts/opensource/background/Order/orderInfoManager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>