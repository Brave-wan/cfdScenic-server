<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
                    <i class="curLoca"></i><font class="fontbold">当前位置:</font>结算管理-账户余额
			</div>
            <%--<input type="hidden" value="${type}" id="typeId"/>--%>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>查询条件</h2>
				<em></em>
			</div>
		</div>
		<div class="querydiv">
			<p>
				<label for="searchValueNickName">昵称：</label> <input id="searchValue1"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchValueNickName">手机号：</label> <input id="searchValue2"
					class="easyui-validatebox">
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="userAccountManage.search()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>账户余额</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			<%--<a id="shareRuleAddBtn" href="javascript:void(0)"
				onclick="userAccountManage.add()" class="easyui-linkbutton"
				iconCls="icon-add">新增</a> <a id="shareRuleEditBtn"
				href="javascript:void(0)" onclick="userAccountManage.edit()"
				class="easyui-linkbutton" iconCls="icon-redo">修改</a><a
				id="shareRuleDelBtn" href="javascript:void(0)"
				onclick="userAccountManage.del()" class="easyui-linkbutton"
				iconCls="icon-remove">删除</a><a id="shareRuleDelBtn"
				href="javascript:void(0)" onclick="userAccountManage.yl()"
				class="easyui-linkbutton" iconCls="icon-add">预览</a>--%>
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="userAccountGid" >
				</table>
			</div>
		</div>
	</div>
</div>






<div id="accountWindow" class="easyui-window" title="充值"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="accountForm" method="post">
				<input type="hidden" name="id" id="accountId"/>
				<input type="hidden" name="id" id="userId"/>
                <p>
                    <label class="bolb">充值金额:</label> <input id="addBalance"
                                                             name="addBalance" type="text" class="easyui-validatebox sleek"
                                                             onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
                                                             onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"/>
                </p>
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="userAccountManage.addSub()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="$('#accountWindow').window('close');$('#accountForm').form('clear');">取消</a>
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
				href="javascript:void(0)" onclick="$('#ImgWindow').window('close');userAccountManage.updateImg();">修改</a> <a
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
				onclick="userAccountManage.submit()">确定</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#ImageSubWindow').window('close');$('#uploadForm').form('clear');">取消</a>
		</div>
	</div>
</div>
<div id="manyiduWindow" class="easyui-window" title="设置满意度"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form action="post" id="manyiForm">
				<input type="hidden" id="manyiId" />
				<p>
					<label class="bolb">选择满意度:</label>
					<input type="text" id="manyiduId" />
					</select>
				</p>
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="userAccountManage.manyiSub()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#manyiduWindow').window('close');$('#manyiForm').form('clear');">取消</a>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="scripts/opensource/background/userAccount/Manager.js?a=1"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
