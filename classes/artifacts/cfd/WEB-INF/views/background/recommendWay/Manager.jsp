<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
                    <i class="curLoca"></i><font class="fontbold">当前位置:</font>导航路线
			</div>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
<!-- 				<h2>查询条件</h2> -->
<!-- 				<em></em> -->
			</div>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>导航线路列表</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			<a id="shareRuleAddBtn" href="javascript:void(0)"
				class="easyui-linkbutton"
				iconCls="icon-add" onclick="wayManager.toSave()">新增</a> 
				<a id="shareRuleDelBtn" href="javascript:void(0)"
				onclick="wayManager.toDel()" class="easyui-linkbutton"
				iconCls="icon-remove">删除</a>
				<a id="shareRuleDelBtn"
				href="javascript:void(0)" onclick="wayManager.yl()"
				class="easyui-linkbutton" iconCls="icon-add">预览</a>
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="wayGid" >
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript"
	src="scripts/opensource/background/recommendWay/Manager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
