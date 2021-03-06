<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curLoca"></i><font class="fontbold">当前位置:</font>广告管理-广告列表
			</div>
		<input type="hidden" value="${type}" id="typeId"/>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>查询条件</h2>
				<em></em>
			</div>
		</div>
		<div class="querydiv">
			<p>
				<label for="searchValueNickName">广告名称：</label> <input id="searchValue1"
					class="easyui-validatebox">

			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="advertisManage.search()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>广告设置</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			<a id="shareRuleAddBtn" href="javascript:void(0)"
				onclick="advertisManage.add()" class="easyui-linkbutton"
				iconCls="icon-add">新增</a> <a id="shareRuleEditBtn"
				href="javascript:void(0)" onclick="advertisManage.edit()"
				class="easyui-linkbutton" iconCls="icon-redo">修改</a><a
				id="shareRuleDelBtn" href="javascript:void(0)"
				onclick="advertisManage.del()" class="easyui-linkbutton"
				iconCls="icon-remove">删除</a><a id="shareRuleDelBtn"
				href="javascript:void(0)" onclick="advertisManage.yl()"
				class="easyui-linkbutton" iconCls="icon-add">预览</a>
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="advertisid" >
				</table>
			</div>
		</div>
	</div>
</div>

<div id="visitorsWindow" class="easyui-window" title="添加景点信息"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="visitorsForm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" id="id"/>
				<p>
					<label class="bolb">名称:</label> <input id="name"
						name="title" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">描述:</label> <input id="visitorsDescribe"
						name="advertDescribe" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
				<tr>
						<label class="bolb">轮播图来源:</label> <input id="price"
						name="source" type="text" class="easyui-validatebox sleek"/>
				</tr>
				</p>
				<p>
					<label class="bolb">类型:</label> <input id="price"
						name="type" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">状态:</label> <input id="newPrice"
						name="state" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">主图:</label><input type="file" id="imgFile" name="imageFile"/>  
				</p>
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="advertisManage.addSub()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="advertisManage.cancel()">取消</a>
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
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="$('#ImgWindow').window('close');advertisManage.updateImg();">修改</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#ImgWindow').window('close');$('#showImg').attr('src','');">关闭</a>
		</div>
	</div>
</div>

<div id="ImageSubWindow" class="easyui-window" title="修改主图"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="uploadForm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" id="ImgId"/>
				<input type="hidden" name="type" value="0"/>
				<p>
					<label class="bolb">上传图片:</label><input type="file" id="imgFile" name="imageFile"/>  
				</p>
				<p>
					<label class="bolb">图片:</label> <img id="imgSrcId" style="height: 50%;width: 50%;" src="" alt="图片暂时无法显示"/>
				</p>
					
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)"
				onclick="advertisManage.submit()">确定</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#ImageSubWindow').window('close');$('#uploadForm').form('clear');">取消</a>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="scripts/opensource/background/advertis/Manager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
