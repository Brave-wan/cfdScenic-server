var visitorsManager = {
		delUrl : "./visitorsOrder/deleteVisitorsOrderYi",
		ylUrl  : "./visitorsOrder/yiShowPage",
		changeInvoice  : "./visitorsOrder/changeInvoice",
		init : function(){
			$(window).load(function(){
				$('#visitorsGid').datagrid({
					url:'./visitorsOrder/getVisitorsOrderYiList.json',
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
						align:'center',width:100
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
		
		show : function(url) {
			var arr = new Array();
			arr = url.split(",");
			console.log(url);
			$('#ImgWindow').window('open');
			$('#imgId').val(arr[0]);
			$('#selectType').val(arr[2]);
			$('#showImg').attr('src', arr[1]);
		},
		
		edit : function() {
			var row = $('#visitorsGid').datagrid('getSelections');
			if (row.length == 0) {
				eu.showMsg("请至少选择一行再进行操作");
				return;
			}
            for (var i=0 ; i<row.length ; i++){
                if (row[i].is_invoice == 1){
                    eu.showMsg("请选择还未开票的进行开票！");
                    return;
                }
            }
			console.log(row);
			$('#ImageSubWindow').window('open');
			$('#invoiceForm').form('clear');
            var arr = '';
            var linkIds = '';
			for (var i=0 ; i<row.length ; i++){
                arr += row[i].order_code + ',';
                linkIds += row[i].visitors_id + ',';
			}
            $('#orderCodes').val(arr);
            $('#linkIds').val(linkIds);
            $('#type').val(0);
		},
		
		submit : function(){
		    var payUnitName = $("#payUnitName").val();
		    var invoiceNumber = $("#invoiceNumber").val();
		    var einNumber = $("#einNumber").val();
		    var invoiceMoney = $("#invoiceMoney").val();
            if (payUnitName == null || '' == payUnitName){
                eu.showMsg("请填写付款单位名称！");
                return
            }
            if (invoiceNumber == null || '' == invoiceNumber){
                eu.showMsg("请填写发票号！");
                return
            }
            if (einNumber == null || '' == einNumber){
                eu.showMsg("请填写税号！");
                return
            }
            if (invoiceMoney == null || '' == invoiceMoney){
                eu.showMsg("请填写开票金额！");
                return
            }

			$.post(visitorsManager.changeInvoice, $("#invoiceForm").serialize()).success(function (data) {
				if (data.success) {
				    //if (data.code == 1){
                        eu.showMsg("操作成功！");
                        $('#ImageSubWindow').window('close');
                        $('#invoiceForm').form('clear');
                        $("#visitorsGid").datagrid('reload');
                    //} else {
                    //    eu.showMsg("请勿重复开票！");
                     //   $("#visitorsgid").datagrid('reload');
                   // }
				} else {
					eu.showMsg(data.message);
				}
			})
		},
		
		search : function(){
			var orderCode =  $("#searchValue1").val();
			$('#visitorsGid').datagrid('load',{orderCode: orderCode});
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
					$.post(visitorsManager.delUrl, {
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
			window.location.href = visitorsManager.ylUrl+"?id="+row.id;
		}
};
visitorsManager.init();