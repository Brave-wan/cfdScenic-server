var wayManager = {
	checkUrl : "./way/toWayList",
	save: "./way/toAddPage",
	del: "./way/toDeleteWay",
	init : function(){
		$(window).load(function(){
			$('#wayGid').datagrid({
				url:wayManager.checkUrl,
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
					title : '',
					align:'center',width:100,hidden:true
				},{
					field : 'group_id',
					title : '',
					align:'center',width:100,hidden:true
				}, {
					field : 'group_name',
					title : '路线名称',
					align:'center',width:100
				},{
					field : 'creatime',
					title : '创建时间',
					align:'center',width:100
				}]]
			});
		});
	},
	toSave : function(){
		window.location.href = wayManager.save;
	},
	yl : function(){
		var row=$('#wayGid').datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行再进行操作");
			return;
		}
		window.location.href="./way/toWayDetail?groupId="+row.group_id;
	},
	toDel : function(){
		var row=$('#wayGid').datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行再进行操作");
			return;
		}
		$.messager.confirm("提示信息","您确定要删除此条导航路线吗？",function(r){
			if(r){
				$.post(wayManager.del,{groupId : row.group_id})
				.success(function(data){
								if(data.success){
									eu.showMsg("删除成功！");
									$("#wayGid").datagrid('reload');
								}else{
									eu.showMsg(data.message);
								}
							}
						).error(function(data){
				        	eu.showMsg("系统错误，请联系管理员！");
				        })
			}else{
				$('#wayGid').datagrid('clearSelections');
			}
			});
	}
};
wayManager.init();