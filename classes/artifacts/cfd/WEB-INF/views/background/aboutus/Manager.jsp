<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curLoca"></i><font class="fontbold">当前位置:</font>关于我们
			</div>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>查询条件</h2>
				<em></em>
			</div>
		</div>
		<div class="querydiv">
			<!-- <p>
				<label for="searchValue">查询类别：</label> <select id="searchValue"
					class="easyui-combobox"
					data-options="panelHeight: 'auto',editable:false">
					<option checked value="0">系统消息</option>
					<option value="1">订单消息</option>
				</select>
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="refundManager.serch()">查询</a>
			</p> -->
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>设置</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			<a id="shareRuleAddBtn" href="javascript:void(0)"
				onclick="refundManager.add()" class="easyui-linkbutton"
				iconCls="icon-add">新增</a> <a id="shareRuleEditBtn"
				href="javascript:void(0)" onclick="refundManager.edit()"
				class="easyui-linkbutton" iconCls="icon-redo">修改</a><a
				id="shareRuleDelBtn" href="javascript:void(0)"
				onclick="refundManager.del()" class="easyui-linkbutton"
				iconCls="icon-remove">删除</a><a id="shareRuleDelBtn"
				href="javascript:void(0)" onclick="refundManager.yl()"
				class="easyui-linkbutton" iconCls="icon-add">预览</a>
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
</div>
<script type="text/javascript"
	src="scripts/opensource/background/aboutus/Manager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
