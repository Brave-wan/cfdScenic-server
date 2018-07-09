var refundManager = {
	checkUrl : "./background/Order/checkRefund",
	setCheckNoCauseUrl : "./background/Order/setCheckNoCause",
	ylUrl : "./hotelOrder/yl",
	fpUrl : "./hotelOrder/tokaipiao",
	init : function(){
		$(window).load(function(){
			$('#refundManagerGid').datagrid({
				url:'./hotelOrder/findall.json',
				border:false,
				fit:true,
				idField:'id',
				pagination:true,
				singleSelect:true,
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
				}, {
					field : 'name',
					title : '订单名称',
					align:'center',width:100
				},{
					field : 'order_state',
					title : '订单状态',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 1:
							return "确认订单";
						case 2:
							return "已支付";
						case 3:
							return "申请退款";
						case 4:
							return "已使用";
						case 5:
							return "已过期";
						case 6:
							return "申请退款成功";
						case 7:
							return "申请退款失败";
						}
					}
				},{
					field : 'honame',
					title : '用户',
					align:'center',width:100
				},{
					field : 'order_describe',
					title : '订单描述',
					align:'center',width:100,hidden:true
				},{
					field : 'telphone',
					title : '手机号',
					align:'center',width:100
				},{
					field : 'price',
					title : '原价',
					align:'center',width:100,hidden:true
				},{
					field : 'real_price',
					title : '应付价格',
					align:'center',width:100
				},{
					field : 'start_date',
					title : '入住时间',
					align:'center',width:100,hidden:true
				},{
					field : 'end_date',
					title : '离住时间',
					align:'center',width:100,hidden:true
				},{
					field : 'quantity',
					title : '房间数量',
					align:'center',width:100
				},{
					field : 'pay_way',
					title : '支付方式',
					align:'center',width:100,hidden:true
				},{
					field : 'pay_state',
					title : '支付状态',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 0:
							return "未支付";
						case 1:
							return "已支付";
						}
					}
				},{
					field : 'create_time',
					title : '订单生成时间',
					align:'center',width:100,hidden:true
				},{
					field : 'pay_time',
					title : '支付时间',
					align:'center',width:100
				},{
					field : 'refund_time',
					title : '退付时间',
					align:'center',width:100
				},{
					field : 'is_comment',
					title : '评价',
					align:'center',width:100,hidden:true
				},{
					field : 'goodsname',
					title : '商品',
					align:'center',width:100
				},{
					field : 'siname',
					title : '商户',
					align:'center',width:100
				},{
					field : 'check_days',
					title : '入住天数',
					align:'center',width:100
				}, {
					field : 'is_balance',
					title : '是否结算',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 1:
							return "是";
						case 0:
							return "否";
						}
					}
				}, {
					field : 'shop_information_id',
					title : '商家',
					align:'center',width:100,hidden:true
				}, {
					field : 'is_delete',
					title : '是否删除',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 1:
							return "是";
						case 0:
							return "否";
						}
					}
				  },{
                  	field : 'billing',
						title : '开票状态',
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
				} ] ]
			});
		});
	},
	toCheck : function(t){
		var row=$('#refundManagerGid').datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行再进行操作");
			return;
		}
		$('#editWindow').window('open');
		$('#id').val(row.id);
		$('#realPrice').val(row.real_price);
		$('#userId').val(row.user_id);
		$('#shopInformationId').val(row.shop_information_id);
		
	},
	serch : function () {
 		var type = $('#searchValue1').combobox('getValue');
 		var Id = $('#searchId').val();
 		$('#refundManagerGid').datagrid('load',{orderState: type,id:Id});
		$('#refundManagerGid').datagrid('clearSelections');
	},
	addSub : function() {
		$.post("./hotelOrder/hotelorderupdate", $("#editForm").serialize()).success(function(data){
			if (data.success) {
				$('#editForm').form('clear');
				$('#editWindow').window('close');
				$("#refundManagerGid").datagrid('reload');
				eu.showMsg("保存成功！");
			} else {
				$.messager.progress('close');
				eu.showMsg(data.message);
			}
		}).error(function(ex){
			$.messager.progress('close');
			eu.showMsg("系统错误！");
		});
	},
	cancel:function(){
		$('#editWindow').window('close');
		$('#editForm').form('clear');
	},	
	yl : function(){
		var row = $("#refundManagerGid").datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行再进行操作");
			return;
		}
		window.location.href = refundManager.ylUrl+"?id="+row.id;
	},
	kaip : function(){
		var row = $("#refundManagerGid").datagrid('getSelections');
		if(row==0){
			eu.showMsg("请至少选择一行再进行操作");
			return;
		}
		for (var i = 0; i < row.length; i++) {
			if(row[i].billing==1){
				eu.showMsg("已经开过发票了，请重新选择");
				return;
			}
		}
		var arr;
		var linkIds;
		var dpId;
		for (var i=0 ; i<row.length ; i++){
			if(i>0){
				arr += row[i].order_code + ',';
				linkIds += row[i].id+",";
				if(dpId!=row[i].shop_information_id){
					eu.showMsg("请重新选择一家商店进行开票");
					return;
				}
			}else{
				dpId = row[0].shop_information_id;
				linkIds = row[0].id+",";
				arr = row[i].order_code + ',';
			}
		}
		window.location.href = refundManager.fpUrl+"?id="+linkIds+"&&code="+arr;
	},
};
refundManager.init();