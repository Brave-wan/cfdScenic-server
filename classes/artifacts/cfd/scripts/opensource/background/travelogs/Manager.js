var refundManager = {
	delUrl : "./travelogs/delete",
	ylUrl : "./travelogs/selectById",
	init : function(){
		$(window).load(function(){
			$('#refundManagerGid').datagrid({
				url:'./travelogs/list.json',
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
					field : 'nick_name',
					title : '游记人',
					align:'center',width:100
				}, {
					field : 'title',
					title : '游记标题',
					align:'center',width:100
				}, {
					field : 'content',
					title : '游记内容',
					align:'center',width:100
				}, {
					field : 'createdate',
					title : '创建时间',
					align:'center',width:100
				}, {
					field : 'travel_name',
					title : '旅游名称',
					align:'center',width:100,hidden:true
				}, {
					field : 'travel_type',
					title : '游记类型',
					align:'center',width:100,hidden:true,
					formatter : function (
                            v, r, i) {
                            switch (v) {
                                case 1:
                                    return "视频";
                                case 2:
                                    return "图片";
                                case 3:
                                    return "文字";
                            }
                        }
				}, {
					field : 'type',
					title : '类型',
					align:'center',width:100,hidden:true,
					 formatter : function (
	                            v, r, i) {
	                            switch (v) {
	                                case 0:
	                                    return "视频攻略";
	                                case 1:
	                                    return "精彩游记";
	                                case 2:
	                                    return "必去清单";
	                            }
	                        }					
				},{
					field : 'address',
					title : '地址',
					align:'center',width:100
				} ] ]
			});
		});
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