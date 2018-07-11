<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curLoca"></i><font class="fontbold">当前位置:</font>订单-消息中心
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
					<option checked value="0">系统消息</option>
					<option value="1">订单消息</option>
				</select>
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
<div id="ImgWindow" class="easyui-window" title="图片查看"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 380px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<p>
				<img alt="图片暂时无法显示" style="width: 350px; height: auto" src=""
					id="showImg">
			</p>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			 <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#ImgWindow').window('close');$('#showImg').attr('src','');">关闭</a>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="scripts/opensource/background/news/Manager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
