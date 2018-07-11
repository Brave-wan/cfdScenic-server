var refundOrderManager = {
	updateState : "./background/refundOrderManage/updateState",
	init : function(){
		$(window).load(function(){
			$('#refundOrderManagerGid').datagrid({
				url:'./background/refundOrderManage/getRefundOrderList.json',
				border:false,
				fit:true,
				singleSelect:true,
				idField:'id',
				pagination:true,
				pageSize:20,
				striped:true,
				fitColumns:true,
				queryParams : {
					orderType : 1
				},
				columns : [ [ {
					field : 'id',
					title : 'id',
					align:'center',width:100,hidden:true
				},{
					field : 'user_id',
					title : '用户ID',
					align:'center',width:100,hidden:true
				},{
					field : 'shop_information_id',
					title : '店铺编号',
					align:'center',width:100,hidden:true
				},{
					field : 'nick_name',
					title : '用户名',
					align:'center',width:100
				},{
					field : 'mobile_no',
					title : '手机号',
					align:'center',width:100
				},{
					field : 'order_code',
					title : '订单编号',
					align:'center',width:100
				},{
					field : 'name',
					title : '订单名',
					align:'center',width:100
				},{
					field : 'price',
					title : '订单价格',
					align:'center',width:100
				},{
					field : 'payTime',
					title : '付款时间',
					align:'center',width:100
				},{
					field : 'op',
					title : '操作',
					align:'center',width:50,
                    formatter : function (
                        v, r, i) {
                    		return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
                            + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
                            + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
                            + 'onclick="refundOrderManager.show('
                            + "'"
                            + r.id+"','"+r.user_id + "','"+r.order_code+"','"+r.shop_information_id+"','"+r.nick_name
                            + "','"+r.price+"','"+r.mobile_no+"'"
                            + ');">审核</a>';

                    }
				}] ]
			});
		});
	},
	show : function(id,uId,oId,sId,name,price,mobile) {
		$('#refundOrderWindow').window('open');
		$('#mId').val(id);
		$('#uId').val(uId);
		$('#oId').val(oId);
		$('#sId').val(sId);
		$('#nickName').val(name);
		$('#mobile').val(mobile);
		$('#price').val(price);
	},
	
	search : function(){
        var name =  $("#searchId").val();
        var mobileNo =  $("#searchId1").val();
        var orderType = $('#searchId2').combobox('getValue');
        $('#refundOrderManagerGid').datagrid('load',{nickName: name ,mobileNo:mobileNo,orderType:orderType});
        $('#refundOrderManagerGid').datagrid('clearSelections');
    },
    
    update : function() {
    	var id = $("#mId").val();
    	var uId = $("#uId").val();
    	var oId = $("#oId").val();
    	var sId = $("#sId").val();
    	var name = $("#nickName").val();
    	var mobile = $("#mobile").val();
    	var state = $('input[name="state"]:checked').val();
    	var cause = $("#cause").val();
    	var orderType = $('#searchId2').combobox('getValue');
    	var price = $("#price").val();
    	
    	if(state==2 && cause ==""){
    		eu.showMsg("请填写未通过原因");
    		return;
    	}
    	
        $.post(refundOrderManager.updateState,{
        	id:id,
        	uId : uId,
        	oId:oId,
        	sId : sId,
        	name :name,
        	mobile:mobile,
        	state:state,
        	cause:cause,
        	orderType:orderType,
        	price:price
            
        }).success( function (data) {
            if (data.success) {
            	eu.showMsg("操作成功");
				$('#refundOrderWindow').window('close');
                $('#refundOrderManagerGid').datagrid('reload');
            } else {
                eu.showMsg(data);
            }
        }).error( function (data) {
            eu.showMsg("系统错误，请联系管理员！");
            $('#refundOrderWindow').window('close');
        })
    },
};
refundOrderManager.init();