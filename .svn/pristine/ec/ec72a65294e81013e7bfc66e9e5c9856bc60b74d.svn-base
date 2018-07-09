var refundManager = {
	ylUrl : "./userOpinion/yl",
	init : function(){
		$(window).load(function(){
			$('#refundManagerGid').datagrid({
				url:'./userOpinion/list.json',
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
					field : 'nickName',
					title : '用户名',
					align:'center',width:100,
				}, {
					field : 'shopName',
					title : '店铺名称',
					align:'center',width:100
				},{
					field : 'create_time',
					title : '创建时间',
					align:'center',width:100,hidden:true
				} ] ]
			});
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