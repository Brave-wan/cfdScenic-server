var publicPlacesManager = {
		addUrl : "./background/publicPlaces/toSavePublicPlaces",
		editUrl : "./background/publicPlaces/toUpdatePublicPlaces",
		deleteUrl : "./background/publicPlaces/deletePublicPlaces",
		init : function(){
			$(window).load(function(){
				$('#publicPlaces').datagrid({
					url:'./background/publicPlaces/checkPublicPlaces.json',
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
						title : '类型',
						align:'center',width:100,
						formatter : function(v, r, i) {
							switch (v) {
							case 1:
								return "公共厕所";
							case 2:
								return "停车场";
							}
						}
					},{
						field : 'state',
						title : '使用状态',
						align:'center',width:100,
						formatter : function(v, r, i) {
							switch (v) {
							case 0:
								return "正常";
							case 1:
								return "停用";
							}
						}
					}
					] ]
				});
			});
		},
		serch : function(){
			$('#publicPlaces').datagrid('load',{
								name : $('#name').val()
								});
			$('#publicPlaces').datagrid('clearSelections');
		},
		addPublicPlaces : function(){
			$("#ff").sunmit();
		},
		editPublicPlaces : function(){
			var row = $('#publicPlaces').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			window.location.href = publicPlacesManager.editUrl+"?id="+row.id;
		},
		savePublicPlaces : function(){
			window.location.href = publicPlacesManager.addUrl;
		},
		deletePublicPlaces : function(){
			var row = $('#publicPlaces').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$.messager.confirm("提示信息", "您确定要将该数据删除吗？", function(r) {
				if (r) {
					$.post(publicPlacesManager.deleteUrl, {
						id : row.id
					}).success(function(data) {
						if (data.success) {
							eu.showMsg("操作成功！");
							$("#publicPlaces").datagrid('reload');
						} else {
							eu.showMsg(data);
						}
					}).error(function(data) {
						eu.showMsg("系统错误，请联系管理员！");
					})
				} else {
					$('#publicPlaces').datagrid('clearSelections');
				}
			});
		}
};
publicPlacesManager.init();