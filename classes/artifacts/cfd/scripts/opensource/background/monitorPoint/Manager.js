var MonitorManager = {
	checkUrl : "./monitorPoint/monitorList",
	editORsave: "./monitorPoint/toEditPage",
	del : "./monitorPoint/deleteMonitor",
	init : function(){
		$(window).load(function(){
			$('#monitorPointGid').datagrid({
				url:MonitorManager.checkUrl,
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
					title : '开票单号',
					align:'center',width:100,hidden:true
				},{
					field : 'name',
					title : '监控名称',
					align:'center',width:100
				}, {
					field : 'content',
					title : '监控介绍',
					align:'center',width:100
				},{
					field : 'creatime',
					title : '创建时间',
					align:'center',width:100
				}]]
			});
		});
	},
	toEdit : function(){
		var row=$('#monitorPointGid').datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行再进行操作");
			return;
		}
		window.location.href=MonitorManager.editORsave+"?id="+row.id;
	},
	toSave : function(){
		window.location.href = MonitorManager.editORsave;
	},
	toDel : function(){
		var row=$('#monitorPointGid').datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行再进行操作");
			return;
		}
		$.messager.confirm("提示信息","您确定要取消该申请发票信息吗？",function(r){
			if(r){
				$.post(MonitorManager.del,{id : row.id})
				.success(function(data){
								if(data.success){
									eu.showMsg("操作成功！");
									$("#monitorPointGid").datagrid('reload');
								}else{
									eu.showMsg(data.message);
								}
							}
						).error(function(data){
				        	eu.showMsg("系统错误，请联系管理员！");
				        })
			}else{
				$('#monitorPointGid').datagrid('clearSelections');
			}
			});
	}
};
MonitorManager.init();