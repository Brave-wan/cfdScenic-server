<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curLoca"></i><font class="fontbold">当前位置:</font>门票管理-已使用门票管理
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
				<label for="searchValueNickName">订单号：</label> <input id="searchValue1"
					class="easyui-validatebox">

			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="visitorsManager.search()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>已使用门票设置</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			 <%--<a id="shareRuleEditBtn"
				href="javascript:void(0)" onclick="visitorsManager.edit()"
				class="easyui-linkbutton" iconCls="icon-redo">开票</a>--%>
				<a id="shareRuleDelBtn" href="javascript:void(0)" 
				onclick="visitorsManager.yl()" class="easyui-linkbutton" 
				iconCls="icon-add">预览</a>
				<a id="shareRuleDelBtn" href="javascript:void(0)"
				onclick="visitorsManager.del()" class="easyui-linkbutton"
				iconCls="icon-remove">删除</a>
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="visitorsGid" >
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
					<label class="bolb">上传主图:</label><input type="file" id="imgFile" name="imageFile"/>  
				</p>
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="visitorsManager.addSub()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="visitorsManager.cancel()">取消</a>
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
				href="javascript:void(0)" onclick="$('#ImgWindow').window('close');visitorsManager.edit();">修改</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#ImgWindow').window('close');$('#showImg').attr('src','');">关闭</a>
		</div>
	</div>
</div>

<div id="ImageSubWindow" class="easyui-window" title="开票"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="invoiceForm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="orderCodes" id="orderCodes"/>
				<input type="hidden" name="linkIds" id="linkIds"/>
				<input type="hidden" name="type" id="type"/>

				<p>
					<label class="bolb">付款单位名称:</label> <input id="payUnitName"
						name="payUnitName" type="text" class="easyui-validatebox sleek" required/>
				</p>
                <p>
					<label class="bolb">发票号:</label> <input id="invoiceNumber"
						name="invoiceNumber" type="text" class="easyui-validatebox sleek;easyui-numberbox" required/>
				</p>
                <p>
					<label class="bolb">税号:</label> <input id="einNumber"
						name="einNumber" type="text" class="easyui-validatebox sleek" required/>
				</p>
                <p>
					<label class="bolb">开票金额:</label> <input id="invoiceMoney"
						name="invoiceMoney" type="text" class="easyui-validatebox sleek;easyui-numberbox" required/>
				</p>

			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)"
				onclick="visitorsManager.submit()">确定</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#ImageSubWindow').window('close');$('#invoiceForm').form('clear');">取消</a>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="scripts/opensource/background/visitorsOrder/yiManager.js?id=123"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
