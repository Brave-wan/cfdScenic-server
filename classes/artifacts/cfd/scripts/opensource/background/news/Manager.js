var refundManager = {
	checkUrl : "./background/Order/checkRefund",
	setCheckNoCauseUrl : "./background/Order/setCheckNoCause",
	init : function(){
		$(window).load(function(){
			$('#refundManagerGid').datagrid({
				url:'./pushMessage/news.json',
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
					title : 'id',
					align:'center',width:100,hidden:true

				},{
					field : 'shopInformationId',
					title : '店铺id',
					align:'center',width:100,hidden:true
				},{
					field : 'type',
					title : '消息类别',
					align:'center',width:100,
					formatter : function(v, r, i) {
						switch (v) {
						case 0:
							return "系统消息";
						case 1:
							return "订单消息";
						}
					}
				},{
					field : 'orderCode',
					title : '订单号',
					align:'center',width:100
				}, {
					field : 'image',
					title : '图片',
					align : 'center',
					width : 100,
					formatter : function(
							v, r, i) {
						var a = "";
						if (v != null){
							a = r.image;
							a = a.replace(
									"\\",
									"/");
							return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
									+ "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
									+ "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
									+ 'onclick="refundManager.show('
									+ "'"
									+ r.id
									+ ","
									+ a
									+ ",1"
									+ "'"
									+ ');">查看</a>';
						}else {
							return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
									+ "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
									+ "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
									+ 'onclick="refundManager.show('
									+ "'"
									+ r.id
									+ ","
									+ a
									+ ",1"
									+ "'"
									+ ');">查看</a>';
						}
					}
				}, {
					field : 'title',
					title : '标题',
					align:'center',width:100
				}, {
					field : 'createDate',
					title : '创建时间',
					align:'center',width:100
				} ] ]
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
		if (arr[1] != null && arr[1] != ""){
			$('#showImg').attr('src', arr[1]);
		}else {
			$('#showImg').attr('alt', '暂时还没有主图，请上传！');
		}
	},
	
	search : function () {
        var title =  $("#searchValue").val();
        $("#refundManagerGid").datagrid('load',{type:type});
        $("#refundManagerGid").datagrid('clearSelections');
    }
};
refundManager.init();