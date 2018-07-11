var refundManager = {
	toAddUrl : "./aboutus/toadd",
	toupdateUrl : "./aboutus/selectById",
	delUrl : "./aboutus/delete",
	ylUrl : "./aboutus/yl",
	init : function(){
		$(window).load(function(){
			$('#refundManagerGid').datagrid({
				url:'./aboutus/list.json',
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

				}, {
					field : 'name',
					title : '名称',
					align:'center',width:100
				},{
					field : 'create_time',
					title : '创建时间',
					align:'center',width:100,hidden:true
				},{
					field : 'state',
					title : '状态',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 0:
							return "可用";
						case 1:
							return "不可用";
						}
					}
				} ] ]
			});
		});
	},
	clearForm:function(){
		$('#refundManagerForm').form('clear');
	},
	add : function(){
		
		window.location.href = refundManager.toAddUrl;
	},
	edit : function(){
		var row=$('#refundManagerGid').datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行信息");
			return;
		}
		var id =row.id;
		window.location.href = refundManager.toupdateUrl+"?id="+id;
	},
	del : function() {
		var row = $('#refundManagerGid').datagrid('getSelected');
		if (!row) {
			eu.showMsg("请选择一行再进行操作");
			return;
		}
		$.messager.confirm("提示信息", "您确定要将该数据删除吗？", function(r) {
			if (r) {
				$.post(refundManager.delUrl, {
					id : row.id
				}).success(function(data) {
					if (data.success) {
						eu.showMsg("操作成功！");
						$("#refundManagerGid").datagrid('reload');
					} else {
						eu.showMsg(data);
					}
				}).error(function(data) {
					eu.showMsg("系统错误，请联系管理员！");
				})
			} else {
				$('#refundManagerGid').datagrid('clearSelections');
			}
		});
	},
	yl : function(){
		var row = $("#refundManagerGid").datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行再进行操作");
			return;
		}
		window.location.href = refundManager.ylUrl+"?id="+row.id;
	},
};
refundManager.init();