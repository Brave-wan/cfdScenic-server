<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
                <c:if test="${type==1}">
                    <i class="curLoca"></i><font class="fontbold">当前位置:</font>商家管理-酒店管理
                </c:if><c:if test="${type==2}">
                <i class="curLoca"></i><font class="fontbold">当前位置:</font>商家管理-饭店管理
                </c:if><c:if test="${type==3}">
                    <i class="curLoca"></i><font class="fontbold">当前位置:</font>商家管理-特产管理
                </c:if><c:if test="${type==4}">
                    <i class="curLoca"></i><font class="fontbold">当前位置:</font>商家管理-小吃管理
                </c:if>
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
				<label for="searchValueNickName">商品名称：</label> <input id="searchValue1"
					class="easyui-validatebox">

			</p>
			<c:if test="${type==2}">
			<p>
                  <label for="searchValue">查询类别：</label> 
                  <select id="searchValue" class="easyui-combobox"
					data-options="panelHeight: 'auto',editable:false">
					<option checked value="0">单品</option>
					<option value="1">套餐</option>
				</select>
			</p>
                </c:if>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="shopGoodsManager.search(${type})">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>商品设置</h2>
				<em></em>
				<input type="hidden" id="danpin" >
			</div>
		</div>
		<div class="menubtndiv">
		<c:choose>
					<c:when test="${!empty sessionScope.SessionKeyUser.telPhone}">
			<a id="shareRuleAddBtn" href="javascript:void(0)"
				onclick="shopGoodsManager.add()" class="easyui-linkbutton"
				iconCls="icon-add">新增</a> <a id="shareRuleEditBtn"
				href="javascript:void(0)" onclick="shopGoodsManager.edit()"
				class="easyui-linkbutton" iconCls="icon-redo">修改</a><a
				id="shareRuleDelBtn" href="javascript:void(0)"
				onclick="shopGoodsManager.del(${type})" class="easyui-linkbutton"
				iconCls="icon-remove">删除</a>
<!-- 				<a id="shareRuleDelBtn" -->
<!-- 				href="javascript:void(0)" onclick="shopGoodsManager.yl()" -->
<!-- 				class="easyui-linkbutton" iconCls="icon-add">预览</a> -->
				</c:when>
					<c:otherwise>
				<a id="shareRuleDelBtn"
				href="javascript:void(0)" onclick="shopGoodsManager.yl()"
				class="easyui-linkbutton" iconCls="icon-add">预览</a>
					</c:otherwise>
				</c:choose>
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="shopGoodsGid" >
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
						name="name" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">描述:</label> <input id="visitorsDescribe"
						name="visitorsDescribe" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">原价:</label> <input id="price"
						name="price" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">折后价:</label> <input id="newPrice"
						name="newPrice" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">地址:</label> <input id="address"
						name="address" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">经度:</label> <input id="longitude"
						name="longitude" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">纬度:</label> <input id="latitude"
						name="latitude" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">上传主图:</label><input type="file" id="imageFile" name="imageFile"/>
				</p>
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="shopGoodsManager.addSub()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="shopGoodsManager.cancel()">取消</a>
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
				href="javascript:void(0)" onclick="$('#ImgWindow').window('close');shopGoodsManager.updateImg();">修改</a> <a
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
				onclick="shopGoodsManager.submit()">确定</a> <a
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
				href="javascript:void(0)" onclick="shopGoodsManager.manyiSub()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#manyiduWindow').window('close');$('#manyiForm').form('clear');">取消</a>
		</div>
	</div>
</div>
<div id="danpinwindow" class="easyui-window" title="选择分类"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form action="post" id="manyiForm">
				<input type="hidden" id="manyiId" />
				<p>
					<label class="bolb">选择分类:</label>
						<input type="radio" value="0" name="danpin" >单品
						<input type="radio" value="1" name="danpin" >套餐
					</select>
				</p>
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="shopGoodsManager.danpin()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#danpinwindow').window('close');$('#manyiForm').form('clear');">取消</a>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="scripts/opensource/background/shopGoods/Manager.js?id=123"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
