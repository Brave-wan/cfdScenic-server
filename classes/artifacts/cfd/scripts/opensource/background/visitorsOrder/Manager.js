var visitorsOrderManager = {
		delUrl : "./background/visitorsOrderManage/deleteVisitorsOrder",
        ylUrl : "./background/visitorsOrderManage/showPage",
        updateUrl : "./background/visitorsOrderManage/updateVisitorsOrder",
        yanpiaoUrl : "./background/visitorsOrderManage/updateVisitorsOrder",
		init : function(){
			$(window).load(function(){
				$('#visitorsGid').datagrid({
					url:'./background/visitorsOrderManage/getVisitorsOrderList.json',
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
                                    return '确认订单';
                                case 2:
                                    return '已支付';
                                case 3:
                                    return '已使用';
                                case 4:
                                    return '已完成';
                                case 5:
                                    return '申请退款';
                                case 6:
                                    return '退款失败';
                                case 7:
                                    return '退款成功';
                                case 8:
                                    return '已过期';
                                case 9:
                                    return '作废';
                            }
                        }
                    },{
						field : 'nickName',
						title : '订票人',
						align:'center',width:50
					},{
						field : 'v_name',
						title : '景区名称',
						align:'center',width:100
					},{
						field : 'price',
						title : '原价',
						align:'center',width:50
					},{
						field : 'real_price',
						title : '总价',
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
						field : 'invoice_state',
						title : '发票状态',
						align:'center',width:50,
						formatter : function(
								v,r,i) {
							switch(v) {
							case 0:
								return '未开票';
							case 1:
								return '开票中';
							case 2:
								return '已开票';
							}
						}
					},{
						field : 'is_delete',
						title : '是否删除',
						align:'center',width:50,
						formatter : function(
								v,r,i) {
							switch(v) {
							case 0:
								return '否';
							case 1:
								return '是';
							}
						}
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
			console.log(row);
            $('#orderState option[value != ""]').remove();
			$('#ImageSubWindow').window('open');
			$('#uploadForm').form('clear');
			$('#orderCode').val(row.order_code);
            $('#realPrice').val(row.real_price);
            $('#userId').val(row.user_id);
//			$('#searchValue').combobox('setValue',row.status);
            if (row.order_state == 1){
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 2) {
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 3) {
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 4) {
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 5) {
                $('#orderState').append("<option  value='7'>退款成功</option>");
                $('#orderState').append("<option  value='6'>退款失败</option>");
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 6) {
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 7) {
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 8) {
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 9) {

            }
            $('#orderState').combobox({});

		},
		
		submit : function(){
			var orderCode = $('#orderCode').val();
            var realPrice = $('#realPrice').val();
            var userId = $('#userId').val();
			var a = $('#orderState').combobox('getValue');
			$.post(visitorsOrderManager.updateUrl, {
				orderCode : orderCode ,
                orderState : a,
                realPrice : realPrice,
                userId :userId
			}).success(function(data){
				if(data.success){
					eu.showMsg("操作成功！");
					$("#visitorsGid").datagrid('reload');
					$('#ImageSubWindow').window('close');
					$('#uploadForm').form('clear');
				} else {
					eu.showMsg(data);
				}
			}).error(function(data){
				eu.showMsg("系统错误，请联系管理员！");
			})
		},

		search : function(){
			var orderCode =  $("#searchValue1").val();
			var nickName =  $("#searchValue2").val();
			var createTime =  $("#searchValue3").val();
			var orderState =  $("#searchValue4").combobox("getValue");
			$('#visitorsGid').datagrid('load',{orderCode: orderCode , nickName: nickName , createTime: createTime , orderState: orderState});
			$('#visitorsGid').datagrid('clearSelections');
		},
		
		del : function() {
			var row = $('#visitorsGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$.messager.confirm("提示信息", "您确定要将该数据删除吗？", function(r) {
				if (r) {
					$.post(visitorsOrderManager.delUrl, {
						id : row.id
					}).success(function(data) {
						if (data.success) {
							eu.showMsg("操作成功！");
							$("#visitorsGid").datagrid('reload');
						} else {
							eu.showMsg(data);
						}
					}).error(function(data) {
						eu.showMsg("系统错误，请联系管理员！");
					})
				} else {
					$('#visitorsGid').datagrid('clearSelections');
				}
			});
		},
		
		yl : function(){
			var row = $("#visitorsGid").datagrid('getSelected');
			if(!row){
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			window.location.href = visitorsOrderManager.ylUrl+"?id="+row.id;
		},
		
		yanpiao : function(){
			var row = $('#visitorsGid').datagrid('getSelected');
			if(!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$('#yanpiaoWindow').window('open');
			$('#yanpiaoForm').form('clear');
			$('#yanpiaoId').val(row.order_code);
		},
		
		yanpiaoSub : function(){
			if(confirm("是否确认验票？")){
			var yanpiaoId = $('#yanpiaoId').val();
			var row = $('#visitorsGid').datagrid('getSelected');
			$.post(visitorsOrderManager.yanpiaoUrl, {
				orderCode : row.order_code
			}).success(function(data){
				if(data.success){
					eu.showMsg("操作成功！");
				} else {
					eu.showMsg("操作失败！");
				}
			}).error(function(data){
				eu.showMsg("系统错误，请联系管理员！");
			})
			}
		}
};
visitorsOrderManager.init();