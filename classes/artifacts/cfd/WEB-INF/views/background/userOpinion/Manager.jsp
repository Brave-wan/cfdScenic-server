<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curLoca"></i><font class="fontbold">当前位置:</font>投诉建议
			</div>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>查询条件</h2>
				<em></em>
			</div>
		</div>
		<div class="querydiv">
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>设置</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			<a id="shareRuleDelBtn"
				href="javascript:void(0)" onclick="refundManager.yl()"
				class="easyui-linkbutton" iconCls="icon-add">查看详细</a>
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
	src="scripts/opensource/background/userOpinion/Manager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
