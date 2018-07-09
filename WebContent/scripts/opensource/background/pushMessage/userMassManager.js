/**
 * Created by Administrator on 2016/10/19 0019.
 */
var consumerUserManager = {
	toSendUrl : "./background/pushMessageManage/toSendPage",
    init : function () {
        $(window).load(function () {
            $('#consumerUserGid').datagrid({
                url : "./background/consumerUserManage/getConsumerUserList",
                border:false,
				fit:true,
				singleSelect:true,
				idField:'id',
				pagination:true,
				pageSize:20,
				striped:true,
				singleSelect: false,
				fitColumns:true,
                columns : [[{
                    field : 'id',
                    title : 'id',
                    align : 'center',
                    width : 100,
                    checkbox:true
                },{
                    field : 'nickName',
                    title : '昵称',
                    align : 'center',
                    width : 100,
                },{
                    field : 'mobileNo',
                    title : '手机号',
                    align : 'center',
                    width : 100,
                },{
                    field : 'gender',
                    title : '性别',
                    align : 'center',
                    width : 100,
                    formatter : function (
                        v, r, i) {
                        switch (v) {
                            case 0:
                                return '男';
                            case 1:
                                return '女';
                        }
                    }
                },{
                    field : 'idCard',
                    title : '身份证',
                    align : 'center',
                    width : 100,
                }]]
            });
        });
    },

    toSend : function(){
		var ids = '';
		var checkids = $('#consumerUserGid').datagrid('getSelections');
		if (checkids.length == 0) {
			eu.showMsg("请至少选择一行再进行操作");
			return;
		}
		for(var i = 0;i<checkids.length;i++){
			ids+=checkids[i].id+",";
		}
		window.location.href = consumerUserManager.toSendUrl+"?ids="+ids+"&type=1";
	},
    
    search : function () {
        var nickName = $("#searchValue1").val();
        var mobileNo = $("#searchValue2").val();
        $('#consumerUserGid').datagrid('load',{nickName : nickName , mobileNo : mobileNo});
        $('#consumerUserGid').datagrid('clearSelections');
    },

};
consumerUserManager.init();