var visitorsManager = {
		addUrl : "./background/perrole/insert",
		delUrl : "./background/perrole/del",
		editUrl :"./background/permiss/findmenu",
		init : function(){
			$(window).load(function(){
				$('#visitorsGid').datagrid({
					url:'./ShopUserPcController/getByid.json',
					border:false,
					fit:true,
					singleSelect:true,
					idField:'id',
					pagination:true,
					pageSize:20,
					striped:true,
					fitColumns:true,
					columns : [ [ {
						field : 'id',
						title : 'id',
						align:'center',width:100,hidden:true

					},{
						field : 'name',
						title : '名称',
						align:'center',width:100
					}] ]
				});
			});
		},
		edit : function() {
			var row = $('#visitorsGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$('#editWindow').window('open');
			$('#id').val(row.id);
		},
		clearForm:function(){
			$('#editWindow').form('clear');
		},
		cancel:function(){
			$('#editWindow').form('clear');
			$('#editWindow').window('close');
		},
		addSub : function() {
			var pwd = $("#pwd").val();
			var pwd1 = $("#pwd1").val();
			var pwd2 = $("#pwd2").val();
			if(pwd==""){
				eu.showMsg("请输入旧密码");
				return;
			}else{
				if(/^[0-9a-zA-Z_]{6,16}$/.test(pwd)){
				}else{
					eu.showMsg("请输入6-16位旧密码，仅支持数字和字母");
					return;
				}
			}
			if(pwd1==""){
				eu.showMsg("请输入新密码");
				return;
			}else{
				if(/^[0-9a-zA-Z_]{6,16}$/.test(pwd1)){
				}else{
					eu.showMsg("请输入6-16位新密码，仅支持数字和字母");
					return;
				}
			}
			if(pwd2==""){
				eu.showMsg("请输入确认密码");
				return;
			}else{
				if(pwd1!=pwd2){
					eu.showMsg("确认密码与新密码不一致");
					return;
				}
			}
			$.post("./ShopUserPcController/uppwd", $("#editForm").serialize()).success(function(data){
				if (data.success) {
					eu.showMsg("密码修改成功!");
					$('#editWindow').form('clear');
					$('#editWindow').window('close');
					/*location.reload();*/
					/*window.location.href = "${ctx}/ShopUserPcController/login";*/
				} else {
					eu.showMsg(data.message);
				}
			}).error(function(ex){
				$.messager.progress('close');
				eu.showMsg("系统错误！");
			});
		}
		
};
visitorsManager.init();