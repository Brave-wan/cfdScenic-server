var refundManager = {
	checkUrl : "./invoice/delete",
	setCheckNoCauseUrl : "./background/Order/setCheckNoCause",
	ylUrl: "./invoice/toupdate",
	dingdanUrl : "./invoice/dingdan",
	init : function(){
		$(window).load(function(){
			$('#refundManagerGid').datagrid({
				url:'./invoice/list.json',
				border:false,
				fit:true,
				singleSelect:true,
				idField:'id',
				pagination:true,
				pageSize:20,
				striped:true,
				fitColumns:true,
				queryParams : {
					type : 1,
					status : 4
				},
				columns : [ [ {
					field : 'id',
					title : '开票单号',
					align:'center',width:100
				},{
					field : 'create_time',
					title : '申请开票日期',
					align:'center',width:100
				}, {
					field : 'pay_unit_name',
					title : '付款单位名称',
					align:'center',width:100
				},{
					field : 'ein_number',
					title : '税号',
					align:'center',width:100
				},{
					field : 'invoice_money',
					title : '开票金额',
					align:'center',width:100
				},{
					field : 'invoice_number',
					title : '发票号',
					align:'center',width:100,
				}, {
					field : 'invoice_time',
					title : '开发票时间',
					align:'center',width:100
				},{
					field : 'order_code',
					title : '订单号',
					align:'center',width:100
				},{
					field : 'link_id',
					title : '景区或者店铺ID',
					align:'center',width:100,hidden:true
				},{
					field : 'type',
					title : '发票类型 0景区 1商铺',
					align:'center',width:100,hidden:true
				}, {
					field : 'invoice_state',
					title : '发票状态',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 2:
							return "已申请发票";
						case 1:
							return "已开发票";
						}
					}
				} ] ]
			});
		});
	},
	toCheck : function(){
		var row=$('#refundManagerGid').datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行再进行操作");
			return;
		}
		if(row.invoice_state==1){
			eu.showMsg("该信息已开发票，请选择其他信息进行操作");
			return;
		}
		$.messager.confirm("提示信息","您确定要取消该申请发票信息吗？",function(r){
			if(r){
				$.post(refundManager.checkUrl,{id : row.id ,code:row.order_code})
				.success(function(data){
								if(data.success){
									eu.showMsg("操作成功！");
									$("#refundManagerGid").datagrid('reload');
								}else{
									eu.showMsg(data.message);
								}
							}
						).error(function(data){
				        	eu.showMsg("系统错误，请联系管理员！");
				        })
			}else{
				$('#refundManagerGid').datagrid('clearSelections');
			}
			});
	},
	kaip : function(){
		var row=$('#refundManagerGid').datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行再进行操作");
			return;
		}
		if(row.invoice_state==1){
			eu.showMsg("该信息已开发票，请选择其他信息进行操作");
			return;
		}
		window.location.href = refundManager.ylUrl+"?id="+row.id;
	},
	dingdan : function(){
		var row=$('#refundManagerGid').datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行再进行操作");
			return;
		}
		window.location.href = refundManager.dingdanUrl+"?id="+row.id+"&&code="+row.order_code;
	},
	search : function(){
		var invoiceState =  $("#searchValue").combobox("getValue");
		$('#refundManagerGid').datagrid('load',{invoiceState: invoiceState});
	},
};
refundManager.init();