var orderInfoManager = {
	delUrl : "./background/Order/delOrderInfo",
	init : function(){
		$(window).load(function(){
			$('#orderInfoManagerGid').datagrid({
				url:'./background/Order/orderInfoLvXingData.json',
				border:false,
				fit:true,
				singleSelect:true,
				idField:'id',
				pagination:true,
				pageSize:20,
				striped:true,
				fitColumns:true,
				queryParams : {
					
				},
				columns : [ [ {
					field : 'id',
					title : 'id',
					align:'center',width:100,hidden:true

				}, {
					field : 'fromuser',
					title : '人id',
					align:'center',width:100,hidden:true
				},{
					field : 'travelinfo',
					title : '行程id',
					align:'center',width:100,hidden:true
				},{
					field : 'status',
					title : '状态',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 1:
							return "已提交";
						case 2:
							return "已完成";
						case 3:
							return "已评论";
						case 4:
							return "申请退款中";
						case 5:
							return "申请退款失败";
						case 6:
							return "申请退款成功";
						case 8:
							return "已结算";
						}
					}
				},{
					field : 'deletestatue',
					title : '删除状态',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 1:
							return "已删除";
						case 0:
							return "未删除";
						}
					}
				},{
					field : 'nickname',
					title : '申请认证人',
					align:'center',width:100
				}, {
					field : 'days',
					title : '天数',
					align:'center',width:100
				}, {
					field : 'membercount',
					title : '人数',
					align:'center',width:100
				},{
					field : 'workbegintime',
					title : '开始时间',
					align:'center',width:100
				},{
					field : 'workendtime',
					title : '结束时间',
					align:'center',width:100
				},{
					field : 'appointtime',
					title : '预约小时',
					align:'center',width:100
				},{
					field : 'addr',
					title : '集合地点',
					align:'center',width:100
				},{
					field : 'orderamt',
					title : '订单金额',
					align:'center',width:100
				},{
					field : 'attentions',
					title : '注意事项',
					align:'center',width:100
				},{
					field : 'name',
					title : '姓名',
					align:'center',width:100
				},{
					field : 'idcard',
					title : '身份证号',
					align:'center',width:100
				},{
					field : 'telphone',
					title : '手机号码',
					align:'center',width:100
				},{
					field : 'ctime',
					title : '创建时间',
					align:'center',width:100
				}] ]
			});
		});
	},
	search : function () {
 		var type = $('#searchValue').combobox('getValue');
 		var status = $('#searchValue1').combobox('getValue');
 		var status1 = $('#searchValue5').combobox('getValue');
 		var serviceType = $('#searchValue6').combobox('getValue');
 		var insurance = $('#searchValue7').combobox('getValue');
 		var orderId = $('#searchValue2').val();
 		var nickname = $('#searchValue3').val();
 		var orderMoeny = $('#searchValue4').val();
		$('#orderInfoManagerGid').datagrid('clearSelections');
		switch (type) {
		case '1':// 旅行订单
			$('#orderInfoManagerGid').datagrid({
				url:'./background/Order/orderInfoLvXingData.json',
				border:false,
				fit:true,
				singleSelect:true,
				idField:'id',
				pagination:true,
				pageSize:20,
				striped:true,
				fitColumns:true,
				queryParams : {
					status : status,
					id : orderId,
					nickname : nickname,
					orderamt : orderMoeny
				},
				columns : [ [ {
					field : 'id',
					title : 'id',
					align:'center',width:100,hidden:true

				}, {
					field : 'fromuser',
					title : '人id',
					align:'center',width:100,hidden:true
				},{
					field : 'travelinfo',
					title : '行程id',
					align:'center',width:100,hidden:true
				},{
					field : 'status',
					title : '状态',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 1:
							return "已提交";
						case 2:
							return "已完成";
						case 3:
							return "已评论";
						case 4:
							return "申请退款中";
						case 5:
							return "申请退款失败";
						case 6:
							return "申请退款成功";
						case 8:
							return "已结算";
						}
					}
				},{
					field : 'deletestatue',
					title : '删除状态',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 1:
							return "已删除";
						case 0:
							return "未删除";
						}
					}
				},{
					field : 'nickname',
					title : '申请认证人',
					align:'center',width:100
				}, {
					field : 'days',
					title : '天数',
					align:'center',width:100
				}, {
					field : 'membercount',
					title : '人数',
					align:'center',width:100
				},{
					field : 'workbegintime',
					title : '开始时间',
					align:'center',width:100
				},{
					field : 'workendtime',
					title : '结束时间',
					align:'center',width:100
				},{
					field : 'appointtime',
					title : '预约小时',
					align:'center',width:100
				},{
					field : 'addr',
					title : '集合地点',
					align:'center',width:100
				},{
					field : 'orderamt',
					title : '订单金额',
					align:'center',width:100
				},{
					field : 'attentions',
					title : '注意事项',
					align:'center',width:100
				},{
					field : 'name',
					title : '姓名',
					align:'center',width:100
				},{
					field : 'idcard',
					title : '身份证号',
					align:'center',width:100
				},{
					field : 'telphone',
					title : '手机号码',
					align:'center',width:100
				},{
					field : 'ctime',
					title : '创建时间',
					align:'center',width:100
				}] ]
			});
			break;
		case '2':// 嗨服务
			$('#orderInfoManagerGid').datagrid({
				url:'./background/Order/orderInfoFuWuData.json',
				border:false,
				fit:true,
				singleSelect:true,
				idField:'id',
				pagination:true,
				pageSize:20,
				striped:true,
				fitColumns:true,
				queryParams : {
					status : status1,
					id : orderId,
					nickname : nickname,
					orderamt : orderMoeny,
					servicetype : serviceType,
					insurance : insurance
				},
				columns : [ [ {
					field : 'id',
					title : 'id',
					align:'center',width:100,hidden:true

				}, {
					field : 'fromuser',
					title : '人id',
					align:'center',width:100,hidden:true
				},{
					field : 'touser',
					title : '人id',
					align:'center',width:100,hidden:true
				},{
					field : 'nickname',
					title : '下单人',
					align:'center',width:100
				},{
					field : 'nickname1',
					title : '接单人',
					align:'center',width:100
				},{
					field : 'deletestatus',
					title : '删除状态',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 1:
							return "已删除";
						case 0:
							return "未删除";
						}
					}
				},{
					field : 'servicetype',
					title : '服务类别',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 0:
							return "达人";
						case 1:
							return "导游";
						case 2:
							return "司机";
						}
					}
				},{
					field : 'insurance',
					title : '保险服务',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 0:
							return "未入保险";
						case 1:
							return "已入保险";
						}
					}
				},{
					field : 'status',
					title : '状态',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 1:
							return "已提交";
						case 2:
							return "已接单";
						case 3:
							return "已汇合";
						case 4:
							return "已完成";
						case 5:
							return "申请退款";
						case 6:
							return "申请退款成功";
						case 7:
							return "申请退款失败";
						case 8:
							return "已结算";
						case 9:
							return "已经点击提醒接单";
						}
					}
				},{
					field : 'workDateTemp',
					title : '预约开始日期',
					align:'center',width:100
				}, {
					field : 'workEndTimeTemp',
					title : '预约开始时间',
					align:'center',width:100
				}, {
					field : 'workBeginTimeTemp',
					title : '预约结束时间',
					align:'center',width:100
				},{
					field : 'appointtime',
					title : '预约小时',
					align:'center',width:100
				},{
					field : 'addr',
					title : '集合地点',
					align:'center',width:100
				},{
					field : 'orderamt',
					title : '订单金额',
					align:'center',width:100
				},{
					field : 'beginTimeTemp',
					title : '创建时间',
					align:'center',width:100
				}] ]
			});
			break;
		}
		$('#nowType').val(type);
	},
	del : function(){
		var row=$('#orderInfoManagerGid').datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行再进行删除");
			return;
		}
		$.messager.confirm("提示信息","您确定要对该订单进行删除操作吗？",function(r){
			if(r){
				$.post(orderInfoManager.delUrl,{id : row.id ,type : $('#nowType').val()})
				.success(function(data){
								if(data.success){
									eu.showMsg("操作成功！");
									$("#orderInfoManagerGid").datagrid('reload');
								}else{
									eu.showMsg(data.message);
								}
							}
						).error(function(data){
				        	eu.showMsg("系统错误，请联系管理员！");
				        })
			
			}else{
				$('#orderInfoManagerGid').datagrid('clearSelections');
			}
			});
	}
};
orderInfoManager.init();