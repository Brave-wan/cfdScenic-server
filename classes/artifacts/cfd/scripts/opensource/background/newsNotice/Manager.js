var refundManager = {
	toAddUrl : "./newsNotice/toadd",
	toupdateUrl : "./newsNotice/toupdate",
	delUrl : "./newsNotice/delete",
	ylUrl : "./newsNotice/yl",
	init : function(){
		$(window).load(function(){
			$('#refundManagerGid').datagrid({
				url:'./newsNotice/list.json',
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

				}, {
					field : 'name',
					title : '名称',
					align:'center',width:100
				}, {
					field : 'news_describe',
					title : '描述',
					align:'center',width:100
				}, {
					field : 'content',
					title : '内容',
					align:'center',width:100
				}, {
					field : 'creator',
					title : '创建人',
					align:'center',width:100,hidden:true
				}, {
					field : 'creator_id',
					title : '创建人',
					align:'center',width:100,hidden:true
				},{
					field : 'create_time',
					title : '创建时间',
					align:'center',width:100
				},{
				  field : 'header_img',
                  title : '图片',
                  align : 'center',
                  width : 50,
                  formatter : function(
                      v, r, i) {
                      var a = "";
                      if (v != null){
                          a = r.header_img;
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
				} ] ]
			});
		});
	},
	clearForm:function(){
		$('#refundManagerForm').form('clear');
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
	            $('#showImg').attr('alt', '暂时还没有图片，请上传！');
	        }
	    },
	add : function(){
		
		window.location.href = refundManager.toAddUrl;
	},
	edit : function(){
		var row=$('#refundManagerGid').datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行信息");
			return;
		}
		var id =row.id;
		window.location.href = refundManager.toupdateUrl+"?id="+id;
	},
	del : function() {
		var row = $('#refundManagerGid').datagrid('getSelected');
		if (!row) {
			eu.showMsg("请选择一行再进行操作");
			return;
		}
		$.messager.confirm("提示信息", "您确定要将该数据删除吗？", function(r) {
			if (r) {
				$.post(refundManager.delUrl, {
					id : row.id
				}).success(function(data) {
					if (data.success) {
						eu.showMsg("操作成功！");
						$("#refundManagerGid").datagrid('reload');
					} else {
						eu.showMsg(data);
					}
				}).error(function(data) {
					eu.showMsg("系统错误，请联系管理员！");
				})
			} else {
				$('#refundManagerGid').datagrid('clearSelections');
			}
		});
	},
	yl : function(){
		var row = $("#refundManagerGid").datagrid('getSelected');
		if(!row){
			eu.showMsg("请选择一行再进行操作");
			return;
		}
		window.location.href = refundManager.ylUrl+"?id="+row.id;
	},
};
refundManager.init();