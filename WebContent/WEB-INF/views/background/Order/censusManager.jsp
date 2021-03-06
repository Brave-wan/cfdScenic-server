<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curLoca"></i><font class="fontbold">当前位置:</font>订单-统计查询
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
<!-- 					<option checked value="1">订单查询</option> -->
					<option value="2">售卖查询</option>
				</select>
			</p>
			<p>
				<label for="searchValue">时间查询：</label> 
				<input type="text" id="dateTimeId" class="easyui-datebox" style="width: 150px;height:30px;line-height:0px;padding:0px"/>
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="refundManager.serch()">查询</a>
			</p>
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
	src="scripts/opensource/background/Order/censusManager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
