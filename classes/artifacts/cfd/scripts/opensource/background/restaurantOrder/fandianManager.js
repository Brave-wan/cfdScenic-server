var restaurantOrderManager = {
		delUrl : "./background/restaurantOrderManage/deleteRestaurantOrder",
        ylUrl : "./background/restaurantOrderManage/showPage",
        updateUrl : "./background/restaurantOrderManage/updateRestaurantOrder",
        fpurl : "./background/restaurantOrderManage/tokaipiao",
		init : function(){
			$(window).load(function(){
				$('#restaurantOrderGid').datagrid({
					url:'./background/restaurantOrderManage/getrestaurantOrderkp.json',
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
						field : 'order_code',
						title : '订单号',
						align:'center',width:100
                    },{
						field : 'nickName',
						title : '下单人',
						align:'center',width:50
					},{
						field : 'goodsName',
						title : '商品名称',
						align:'center',width:100
					},{
						field : 'price',
						title : '原价',
						align:'center',width:50
					},{
						field : 'real_price',
						title : '应付价格',
						align:'center',width:50
                    },{
                    	field : 'billing',
						title : '发票状态',
						align:'center',width:50,
						formatter : function (
                                v, r, i) {
                            switch (v) {
                                case 0 :
                                    return '未开';
                                case 1 :
                                    return '已开';
                            }
                        }
                    }] ]
				});
			});
		},
		yl : function(){
			var row = $("#restaurantOrderGid").datagrid('getSelected');
			if(!row){
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			window.location.href = restaurantOrderManager.ylUrl+"?id="+row.id;
		},
		kaip : function(){
			var row = $("#restaurantOrderGid").datagrid('getSelected');
			if(!row){
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			var billing = row.billing;
			if(billing==1){
				eu.showMsg("已经开过发票了，请重新选择");
				return;
			}
			var code = row.order_code;
			window.location.href = restaurantOrderManager.fpurl+"?code="+code;
		}
};
restaurantOrderManager.init();