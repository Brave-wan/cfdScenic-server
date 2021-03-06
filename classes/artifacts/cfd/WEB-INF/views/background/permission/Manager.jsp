<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
					<i class="curLoca"></i><font class="fontbold">当前位置:</font>权限设置
			</div>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<em></em>
			</div>
		</div>
		<div class="querydiv">
			<p>

			</p>
			<p>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
			</div>
		</div>
		<div class="menubtndiv">
			<a id="shareRuleEditBtn" href="javascript:void(0)" onclick="javascript:openAuthDialog()" class="easyui-linkbutton" iconCls="icon-redo">设置权限</a>
			<a id="shareRuleAddBtn" href="javascript:void(0)"
				onclick="visitorsManager.add()" class="easyui-linkbutton"
				iconCls="icon-add">新增角色</a> 
			<a id="shareRuleEditBtn"
				href="javascript:void(0)" onclick="visitorsManager.edit()"
				class="easyui-linkbutton" iconCls="icon-redo">修改角色</a>
				<a
				id="shareRuleDelBtn" href="javascript:void(0)"
				onclick="visitorsManager.del()" class="easyui-linkbutton"
				iconCls="icon-add">删除角色</a>
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
	<div id="editWindow" class="easyui-window" title="角色操作"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="editForm" method="post">
				<table>
					<tr>
					<input type="hidden" name="id" id="id"/>
						<td>角色名称:</td>
						<td><input id="name" name="name" type="text" class="easyui-validatebox sleek"/></td>
					</tr>
					<tr>
						<td>角色：</td>
						<td>
							<select name="type" id="type" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">>
								<option>请选择</option>
								<option value="0">平台</option>
								<option value="1">商户</option>
							</select>
						</td>
					</tr>
				</table>
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
<div id="dlg2" class="easyui-dialog" style="width: 300px;height: 450px;padding: 10px 20px"
  closed="true" buttons="#dlg2-buttons">
	<ul id="tree" class="easyui-tree"></ul>
</div>

<div id="dlg2-buttons">
	<a href="javascript:saveAuth()" class="easyui-linkbutton" iconCls="icon-ok" >授权</a>
	<a href="javascript:closeAuthDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>

<script type="text/javascript"
	src="scripts/opensource/background/permission/Manager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
<script type="text/javascript">
function openAuthDialog(){
	var selectedRows=$("#visitorsGid").datagrid('getSelections');
	if(selectedRows.length!=1){
		$.messager.alert('系统提示','请选择一条要授权的角色！');
		return;
	}
	var row=selectedRows[0];
	var roleId=row.id;
	$("#dlg2").dialog("open").dialog("setTitle","角色授权");
	url="${ctx}/background/permiss/authMenuAction?type="+row.type+"&roleId="+roleId;
	
	$("#tree").tree({
		lines:true,
		url:url,
		checkbox:true,
		cascadeCheck:false,
		onLoadSuccess:function(){
			$("#tree").tree('expandAll');
		},
		onCheck:function(node,checked){
			if(checked){
				checkNode($('#tree').tree('getParent',node.target));
			}
		}
	});
}
function checkNode(node){
	if(!node){
		return;
	}else{
		checkNode($('#tree').tree('getParent',node.target));
		$('#tree').tree('check',node.target);
	}
}

function closeAuthDialog(){
	$("#dlg2").dialog("close");
}
function saveAuth(){
	var nodes=$('#tree').tree('getChecked');
	if(nodes==""){
		alert("您没有选择权限");
		return;
	}
	var authArrIds=[];
	for(var i=0;i<nodes.length;i++){
		authArrIds.push(nodes[i].id);
	}
	var menuid=authArrIds.join(",");
	var selectedRows=$("#visitorsGid").datagrid('getSelections');
	var row=selectedRows[0];
	var brid=row.id;
	$.post("${basePath}/background/permiss/insert",{menuid:menuid,brid:brid},function(result){
		if(result.success){
			$.messager.alert('系统提示','授权成功！');
			closeAuthDialog();
		}else{
			$.messager.alert('系统提示',result.errorMsg);
		}
	},"json");
}
</script>