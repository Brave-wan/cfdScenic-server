var withdrawalApplyManage = {
        updateState : "./background/withdrawalApplyManage/updateState",
		init : function(){
			$(window).load(function(){
				$('#withdrawalApplyGid').datagrid({
					url:'./background/withdrawalApplyManage/getWithdrawalApplyList.json',
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
						field : 'user_id',
						title : 'user_id',
						align:'center',width:100,hidden:true
					},{
						field : 'nickName',
						title : '昵称',
						align:'center',width:100
					},{
						field : 'mobileNo',
						title : '手机号',
						align:'center',width:50
					},{
						field : 'name',
						title : '交易名称',
						align:'center',width:100
					},{
						field : 'balance',
						title : '提现金额',
						align:'center',width:50
					},{
						field : 'begin_balance',
						title : '提现前金额',
						align:'center',width:50
					},{
						field : 'createTime',
						title : '申请时间',
						align:'center',width:50
					},{
						field : 'state',
						title : '状态',
						align:'center',width:50,
                        formatter : function (
                            v, r, i) {
                        	
                        	if(v ==1){
                        		return "审核通过";
                        	}else if(v==2){
                        		return "审核不通过";
                        	}else{
                        		return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
                                + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
                                + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
                                + 'onclick="withdrawalApplyManage.show('
                                + "'"
                                + r.id + "',"+"'"+r.user_id+"',"+"'"+r.name+"',"+"'"+r.balance+"',"+r.begin_balance
                                + ');">审核</a>';
                        	}
                        }
					}] ]
				});
			});
		},

		
		show : function(id,uid,name,balance,beginBalance) {
			$('#withdrawalApplyWindow').window('open');
			$('#logId').val(id);
			$('#uId').val(uid);
			$('#wdaname').val(name);
			$('#balance').val(balance);
			$('#beginBalance').val(beginBalance);
		},
		
        update : function() {
        	var id = $("#logId").val();
        	var state = $('input[name="state"]:checked').val();
        	var wdaname = $("#wdaname").val();
        	var uid = $("#uId").val();
        	var balance = $("#balance").val();
        	var beginBalance = $("#beginBalance").val();
        	
            $.post(withdrawalApplyManage.updateState,{
                id : id,
                uid:uid,
                state : state,
                wdaname :wdaname,
                balance:balance,
                beginBalance:beginBalance
                
            }).success( function (data) {
                if (data.success) {
                	eu.showMsg("操作成功");
					$('#withdrawalApplyWindow').window('close');
                    $('#withdrawalApplyGid').datagrid('reload');
                } else {
                    eu.showMsg(data);
                }
            }).error( function (data) {
                eu.showMsg("系统错误，请联系管理员！");
                $('#withdrawalApplyWindow').window('close');
            })
        },

        search : function(){
            var name =  $("#searchValue1").val();
            var phone =  $("#searchValue2").val();
            var createTime = $('#searchValue3').datebox('getValue');
            $('#withdrawalApplyGid').datagrid('load',{name: name ,phone:phone, createTime: createTime});
            $('#withdrawalApplyGid').datagrid('clearSelections');
        },


};
withdrawalApplyManage.init();