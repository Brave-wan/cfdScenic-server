var restaurantOrderManager = {
        ylUrl : "./background/goodsOrderManage/showPage",
        fpurl : "./background/goodsOrderManage/kaipiao",
		init : function(){
			$(window).load(function(){
				$('#restaurantOrderGid').datagrid({
					url:'./background/goodsOrderManage/getGoodsOrderkpList.json',
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
                        field : 'order_state',
                        title : '订单状态',
                        align:'center',width:50,
                        formatter : function(
                            v,r,i){
                            switch(v){
                                case 1:
                                    return '待支付';
                                case 2:
                                    return '待发货';
                                case 3:
                                    return '已发货';
                                case 4:
                                    return '已收货';
                                case 5:
                                    return '订单完成';
                                case 6:
                                    return '申请退款';
                                case 7:
                                    return '审核通过';
                                case 11:
                                    return '待收货';
                                case 8:
                                    return '退款中';
                                case 9:
                                    return '退款成功';
                                case 10:
                                    return '已驳回';
                            }
                        }
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
						field : 'create_time',
						title : '下单时间',
						align:'center',width:50,
                        formatter : function (
                                v, r, i) {
                            var date = new Date(v);
                            var year = date.getFullYear().toString();
                            var month = (date.getMonth() + 1);
                            var day = date.getDate().toString();
                            var hour = date.getHours().toString();
                            var minutes = date.getMinutes().toString();
                            var seconds = date.getSeconds().toString();
                            if (month < 10) {
                                month = "0" + month;
                            }
                            if (day < 10) {
                                day = "0" + day;
                            }
                            if (hour < 10) {
                                hour = "0" + hour;
                            }
                            if (minutes < 10) {
                                minutes = "0" + minutes;
                            }
                            if (seconds < 10) {
                                seconds = "0" + seconds;
                            }
                            return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
                        }
					},{
					    field : 'is_comment',
                        title : '是否评价',
                        align : 'center',
                        width : 30,
                        formatter : function (
                                v, r, i) {
                            switch (v) {
                                case 0 :
                                    return '否';
                                case 1 :
                                    return '是';
                            }
                        }
                    },{
					    field : 'is_pickup',
                        title : '是否自提',
                        align : 'center',
                        width : 30,
                        formatter : function (
                                v, r, i) {
                            switch (v) {
                                case 0 :
                                    return '否';
                                case 1 :
                                    return '是';
                            }
                        }
                    },{
					    field : 'is_delete',
                        title : '是否删除',
                        align : 'center',
                        width : 30,
                        formatter : function (
                                v, r, i) {
                            switch (v) {
                                case 0 :
                                    return '否';
                                case 1 :
                                    return '是';
                            }
                        }
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
			window.location.href = restaurantOrderManager.fpurl+"?id="+row.id+"&&code="+code;
		},
};
restaurantOrderManager.init();