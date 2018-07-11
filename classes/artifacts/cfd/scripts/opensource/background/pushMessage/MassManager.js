var massMessageManage = {
		toSendUrl : "./background/pushMessageManage/toSendPage",
		sendUrl :"./background/pushMessageManage/sendMessage",
		init : function(){
			$(window).load(function(){
				$('#shopUserGid').datagrid({
					url:'./background/pushMessageManage/getShopUserList.json',
					border:false,
					fit:true,
					singleSelect:true,
					idField:'id',
					pagination:true,
					pageSize:20,
					striped:true,
					singleSelect: false,
					fitColumns:true,
					columns : [ [ {
						field : 'id',
						title : 'id',
						align:'center',width:100,checkbox:true

					},{
						field : 'nickname',
						title : '用户昵称',
						align:'center',width:100
					},{
						field : 'telphone',
						title : '电话',
						align:'center',width:100
					},{
						field : 'idcard',
						title : '身份证号',
						align:'center',width:50
					},{
						field : 'sName',
						title : '店铺名称',
						align:'center',width:100
					},{
						field : 'createtime',
						title : '创建时间',
						align:'center',width:100
					},{
						field : 'state',
						title : '商家状态',
						align:'center',width:100,
						formatter : function (
		                        v, r, i) {
		                        switch (v) {
		                            case 0:
		                                return '正常';
		                            case 1:
		                                return '停用';
		                            case 2:
		                            	return '删除';
		                        }
		                    }
					}] ]
				});
			});
		},

		
		toSend : function(){
			var ids = '';
			var checkids = $('#shopUserGid').datagrid('getSelections');
			if (checkids.length == 0) {
				eu.showMsg("请至少选择一行再进行操作");
				return;
			}
			for(var i = 0;i<checkids.length;i++){
				ids+=checkids[i].id+",";
			}
			window.location.href = massMessageManage.toSendUrl+"?ids="+ids+"&type=0";
		},

		submit : function(){
			
        
		},
		
		
        search : function(){
        	var nickName = $("#searchValue1").val();
            var mobileNo = $("#searchValue2").val();
            var shopName = $("#searchValue3").val();
            $('#shopUserGid').datagrid('load',{name : nickName , mobileNo : mobileNo,shopName:shopName});
            $('#shopUserGid').datagrid('clearSelections');
        },


};
massMessageManage.init();
