var visitorsOrderManager = {
        changeInvoice  : "./background/visitorsOrderManage/changeInvoice",
		init : function(){
			$(window).load(function(){
				$('#visitorsGid').datagrid({
					url:'./background/visitorsOrderManage/getInvoiceList.json',
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
						field : 'orderCode',
						title : '订单号',
						align:'center',width:100
					},{
						field : 'nickName',
						title : '订票人',
						align:'center',width:50
					},{
						field : 'content',
						title : '订单名称',
						align:'center',width:100
					},{
						field : 'quantity',
						title : '数量',
						align:'center',width:50
					},{
						field : 'realPrice',
						title : '总价',
						align:'center',width:50
					},{
						field : 'createTime',
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
					}] ]
				});
			});
		},





        edit : function() {
            var row = $('#visitorsGid').datagrid('getSelections');
            if (row.length == 0) {
                eu.showMsg("请至少选择一行再进行操作");
                return;
            }

            console.log(row);
            $('#ImageSubWindow').window('open');
            $('#invoiceForm').form('clear');
            var arr = '';
            var linkIds = '';
            for (var i=0 ; i<row.length ; i++){
                arr += row[i].orderCode + ',';
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
    
            $.post(visitorsOrderManager.changeInvoice, $("#invoiceForm").serialize()).success(function (data) {
                if (data.success) {
                    //if (data.code == 1){
                    eu.showMsg("操作成功！");
                    $('#ImageSubWindow').window('close');
                    $('#invoiceForm').form('clear');
                    $("#visitorsGid").datagrid('reload');
                    //} else {
                    //    eu.showMsg("请勿重复开票！");
                    //   $("#visitorsGid").datagrid('reload');
                    // }
                } else {
                    eu.showMsg(data.message);
                }
            })
        },

		search : function(){
			var orderCode =  $("#searchValue1").val();
			var nickName =  $("#searchValue2").val();
			var createTime =  $("#searchValue3").val();
			$('#visitorsGid').datagrid('load',{orderCode: orderCode , nickName: nickName , createTime: createTime});
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
		
		
};
visitorsOrderManager.init();