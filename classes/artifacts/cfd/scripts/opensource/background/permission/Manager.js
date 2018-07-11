var visitorsManager = {
		addUrl : "./background/perrole/insert",
		delUrl : "./background/perrole/del",
		editUrl :"./background/permiss/findmenu",
		init : function(){
			$(window).load(function(){
				$('#visitorsGid').datagrid({
					url:'./background/permiss/getPermissionList.json',
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
					},{
						field : 'type',
						title : '角色',
						align:'center',width:100,
						formatter : function(v, r, i) {
							switch (v) {
							case 0:
								return "平台";
							case 1:
								return "商户";
							}
						}
					}] ]
				});
			});
		},
		install : function() {
			var row = $('#visitorsGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			window.location.href = visitorsManager.editUrl+"?id="+row.id+"&&name="+row.name;
		},
		add : function(){
			$('#editWindow').window('open');
			$('#editWindow').form('clear');
		/*	$('#type').combobox("setValue",1);*/
		},
		edit : function() {
			var row = $('#visitorsGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$('#editWindow').window('open');
			$('#id').val(row.id);
			$('#name').val(row.name);
			$("#type").combobox("setValue",row.type);
		},
		clearForm:function(){
			$('#editWindow').form('clear');
		},
		cancel:function(){
			$('#editWindow').form('clear');
			$('#editWindow').window('close');
		},
		del : function(){
			var row=$('#visitorsGid').datagrid('getSelected');
			if(!row){
				eu.showMsg("请选择一行信息");
				return;
			}
			$.messager.confirm("提示信息","您确定要删除所选数据吗？,删除后不可恢复!",function(r){
				if(r){
					$.post(visitorsManager.delUrl,{id : row.id })
					.success(function(data){
									if(data.success){
										eu.showMsg("删除成功！");
										$("#visitorsGid").datagrid('reload');
									}else{
										eu.showMsg(data);
									}
								}
							).error(function(data){
					        	eu.showMsg("系统错误，请联系管理员！");
					        })
				}else{
					$('#visitorsGid').datagrid('clearSelections');
				}
				});
		},
		addSub : function() {
			var type = $("#type").combobox('getValue')
			if(type == null || type == ""){
				alert("请选择角色！")
				return;
			}
			var name = $("#name").val();
			if(name == null || name == ""){
				alert("请输入角色名！")
				return;
			}
			$.post("./background/perrole/insert", $("#editForm").serialize()).success(function(data){
				if (data.success) {
					eu.showMsg("保存成功！");
					location.reload();
				} else {
					eu.showMsg(data);
				}
			}).error(function(ex){
				$.messager.progress('close');
				eu.showMsg("系统错误！");
			});
		}
		
};
visitorsManager.init();