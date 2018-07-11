<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
                    <i class="curLoca"></i><font class="fontbold">当前位置:</font>商家管理-店铺信息
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
				<label for="searchValueNickName">店铺名称：</label> <input id="searchValue1"
					class="easyui-validatebox">

			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="shopInformationManager.search()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>店铺设置</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
				<c:choose>
					<c:when test="${!empty sessionScope.SessionKeyUser.telPhone}">
						<a id="shareRuleAddBtn" href="javascript:void(0)"
				onclick="shopInformationManager.add()" class="easyui-linkbutton"
				iconCls="icon-add">新增</a> <a id="shareRuleEditBtn"
				href="javascript:void(0)" onclick="shopInformationManager.edit()"
				class="easyui-linkbutton" iconCls="icon-redo">修改</a>
				<a id="shareRuleShowBtn"
				href="javascript:void(0)" onclick="shopInformationManager.yl()"
				class="easyui-linkbutton" iconCls="icon-add">预览</a>
					</c:when>
					<c:otherwise>
					<a id="shareRuleShowBtn"
				href="javascript:void(0)" onclick="shopInformationManager.yl()"
				class="easyui-linkbutton" iconCls="icon-add">预览</a>
				<a id="shareRuleAuditBtn"
				href="javascript:void(0)" onclick="shopInformationManager.audit()"
				class="easyui-linkbutton" iconCls="icon-add">审核</a>
				<a id="shareRuleAuditBtn"
				href="javascript:void(0)" onclick="shopInformationManager.jinyong()"
				class="easyui-linkbutton" iconCls="icon-add">禁用</a>
				<a id="shareRuleAuditBtn"
				href="javascript:void(0)" onclick="shopInformationManager.qiyong()"
				class="easyui-linkbutton" iconCls="icon-add">启用</a>
				<!-- <a id="shareRuleAuditBtn"
				href="javascript:void(0)" onclick="shopInformationManager.audit()"
				class="easyui-linkbutton" iconCls="icon-add">恢复</a> -->
				<a
				id="shareRuleDelBtn" href="javascript:void(0)"
				onclick="shopInformationManager.del()" class="easyui-linkbutton"
				iconCls="icon-remove">删除</a>
					</c:otherwise>
				</c:choose>
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="shopInformationGid" >
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


<div id="auditWindow" class="easyui-window" title="审核操作"
	data-options="closed:true,inline:true"
	style="width: 230px; height: 100px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<input type="hidden" id="auditId"/>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
               href="javascript:void(0)" onclick="$('#auditWindow').window('close');shopInformationManager.auditOK();">审核通过</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
               href="javascript:void(0)" onclick="$('#auditWindow').window('close');shopInformationManager.auditNO();">审核失败</a>
		</div>

	</div>
</div>

<div id="auditNoWindow" class="easyui-window" title="审核失败原因"
     data-options="closed:true,inline:true"
     style="width: 420px; height: 380px; padding: 5px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding: 10px;">
                <input type="hidden" id="auditNoId"/>
                <p>
                    <label class="bolb">审核失败原因:</label>
                    <input type="text" id="auditNoMessage" required/>
                </p>
        </div>
        <div data-options="region:'south',border:false"
             style="text-align: right; padding: 5px 0 0;">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
               href="javascript:void(0)" onclick="$('#auditNoWindow').window('close');shopInformationManager.subAudit();">确定</a> <a
                class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
                href="javascript:void(0)"
                onclick="$('#auditNoWindow').window('close');$('#uploadForm').form('clear');">关闭</a>
        </div>
    </div>
</div>


<div id="ImageSubWindow" class="easyui-window" title="修改头像"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="uploadForm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" id="ImgSubId"/>
				<p>
					<label class="bolb">上传图片:</label><input type="file" id="imgFile" name="imageFile2"/>
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
				onclick="shopInformationManager.submit()">确定</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#ImageSubWindow').window('close');$('#uploadForm').form('clear');">取消</a>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="scripts/opensource/background/shopInformation/Manager.js?id=123"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
