var userAccountManage = {
        addBalanceUrl : "./background/userAccountManage/addBalance",
        updateState : "./background/userAccountManage/updateState",
		init : function(){
			$(window).load(function(){
				$('#userAccountGid').datagrid({
					url:'./background/userAccountManage/getUserAccountList.json',
					border:false,
					fit:true,
					singleSelect:true,
					idField:'id',
					pagination:true,
					pageSize:20,
					striped:true,
					fitColumns:true,
					/*queryParams:{
						type : $("#typeId").val()
					},*/
					columns : [ [ {
						field : 'id',
						title : 'id',
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
						field : 'balance',
						title : '余额',
						align:'center',width:50
					},{
						field : 'integration',
						title : '积分',
						align:'center',width:50
					},{
						field : 'state',
						title : '状态',
						align:'center',width:50,
                        formatter : function (
                            v, r, i) {
                            switch (v) {
                                case 0:
                                    return "可用";
                                case 1:
                                    return "停用";
                            }
                        }
					},{
						field : 'opt',
						title : '充值',
						align : 'center',
						width : 50,
						formatter : function(
								v, r, i) {
								return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
										+ "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
										+ "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
										+ 'onclick="userAccountManage.show('
										+ "'"
										+ r.id
                                        + ","
                                        + r.userId
										+ "'"
										+ ');">充值</a>';
						}
					},{
						field : 'op',
						title : '修改状态',
						align : 'center',
						width : 50,
						formatter : function(
								v, r, i) {
						        if(r.state == 0) {
                                    return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
                                            + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
                                            + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
                                            + 'onclick="userAccountManage.update('
                                            + "'"
                                            + r.userId
                                            + ","
                                            + r.state
                                            + "'"
                                            + ');">停用</a>';
                                }
						        if(r.state == 1) {
                                    return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
                                            + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
                                            + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
                                            + 'onclick="userAccountManage.update('
                                            + "'"
                                            + r.userId
                                            + ","
                                            + r.state
                                            + "'"
                                            + ');">可用</a>';
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
			$('#accountWindow').window('open');
			$('#accountId').val(arr[0]);
			$('#userId').val(arr[1]);
		},


        addSub : function () {
		    var accountId = $("#accountId").val();
            var addBalance = $("#addBalance").val();
            var userId = $("#userId").val();
            $.post(userAccountManage.addBalanceUrl,{
                id : accountId,
                addBalance : addBalance,
                userId : userId
            }).success( function (data) {
                if (data.success) {
                    $('#accountWindow').window('close');
                    $('#accountForm').form('clear');
                    $('#userAccountGid').datagrid('reload');
                } else {
                    eu.showMsg(data);
                }
            }).error( function (data) {
                eu.showMsg("系统错误，请联系管理员！");
            })
        },

        update : function(url) {
            var arr = new Array();
            arr = url.split(",");
            var id = arr[0];
            var state = arr[1];
            $.post(userAccountManage.updateState,{
                id : id,
                state : state
            }).success( function (data) {
                if (data.success) {
                    $('#userAccountGid').datagrid('reload');
                } else {
                    eu.showMsg(data);
                }
            }).error( function (data) {
                eu.showMsg("系统错误，请联系管理员！");
            })
        },

        search : function(){
            var name =  $("#searchValue1").val();
            var phone =  $("#searchValue2").val();
            $('#userAccountGid').datagrid('load',{nickName: name , mobileNo: phone});
            $('#userAccountGid').datagrid('clearSelections');
        },


};
userAccountManage.init();