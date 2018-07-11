var pushMessageManage = {
        
		init : function(){
			$(window).load(function(){
				$('#pushMessageGid').datagrid({
					url:'./background/pushMessageManage/getPushMessageList.json',
					border:false,
					fit:true,
					singleSelect:true,
					idField:'id',
					pagination:true,
					pageSize:20,
					striped:true,
					fitColumns:true,
					rownumbers: true,
					columns : [ [ {
						field : 'id',
						title : 'id',
						align:'center',width:100,hidden:true
					},{
						field : 'title',
						title : '标题',
						align:'center',width:100
					},{
						field : 'content',
						title : '内容',
						align:'center',width:100
					},{
						field : 'image',
						title : '图片',
						align : 'center',
						width : 40,
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
										+ 'onclick="pushMessageManage.show('
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
										+ 'onclick="pushMessageManage.show('
										+ "'"
										+ r.id
										+ ","
										+ a
										+ ",1"
										+ "'"
										+ ');">查看</a>';
							}
						}
					},{
						field : 'createTime',
						title : '推送时间',
						align:'center',width:50
					}] ]
				});
			});
		},

		show : function(url) {
			var arr = new Array();
			arr = url.split(",");
			console.log(url);
            $('#showImg').attr('src','');
			$('#ImgWindow').window('open');
			$('#imgId').val(arr[0]);
			if (arr[1] != null && arr[1] != ""){
				$('#showImg').attr('src', arr[1]);
			}
		},

        search : function(){
            var createTime = $('#searchValue').datebox('getValue');
            $('#pushMessageGid').datagrid('load',{createTime: createTime});
            $('#pushMessageGid').datagrid('clearSelections');
        },


};
pushMessageManage.init();