<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
					<i class="curLoca"></i><font class="fontbold">当前位置:</font>公共场所设置
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
				<label for="searchValueNickName">公共场所名称：</label> <input id="searchValue"
					class="easyui-validatebox">
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="publicPlacesManager.serch()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
			</div>
		</div>
		<div class="menubtndiv">
			<a id="shareRuleAddBtn" href="javascript:void(0)"
				onclick="publicPlacesManager.savePublicPlaces()" class="easyui-linkbutton"
				iconCls="icon-add">新增</a> 
			<a id="shareRuleEditBtn"
				href="javascript:void(0)" onclick="publicPlacesManager.editPublicPlaces()"
				class="easyui-linkbutton" iconCls="icon-redo">修改</a>
				<a
				id="shareRuleDelBtn" href="javascript:void(0)"
				onclick="publicPlacesManager.deletePublicPlaces()" class="easyui-linkbutton"
				iconCls="icon-add">删除</a>
		</div>
	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="publicPlaces" >
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="scripts/opensource/background/publicPlaces/Manager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>