var visitorsManager = {
		addUrl : "./background/visitorsManage/addVisitors",
		delUrl : "./background/visitorsManage/deleteVisitors",
		ylUrl  : "./background/visitorsManage/showPage",
		toAddUrl  : "./background/visitorsManage/toAddPage",
		editUrl  : "./background/visitorsManage/toEditPage",
		manyiduUrl :"./background/visitorsManage/manyidu",
		updateImgUrl :"./background/visitorsManage/updateImg",
		
		init : function(){
			$(window).load(function(){
				var type = $("#typeId").val();
				
				if(type == 1 || type == 2 || type == 3){
				var columns = [ [ {
					field : 'id',
					title : 'id',
					align:'center',width:100,hidden:true

				},{
					field : 'name',
					title : '名称',
					align:'center',width:100
				},{
					field : 'visitors_describe',
					title : '简介',
					align:'center',width:200
				},{
					field : 'price',
					title : '原价',
					align:'center',width:50
				},{
					field : 'new_price',
					title : '折后价',
					align:'center',width:50
				},{
					field : 'address',
					title : '地址',
					align:'center',width:100
				},
				{
					field : 'head_img',
					title : '主图',
					align : 'center',
					width : 50,
					formatter : function(
							v, r, i) {
							var a = "";
						if (v != null){
							a = r.head_img;
							a = a.replace(
									"\\",
									"/");
							return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
									+ "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
									+ "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
									+ 'onclick="visitorsManager.show('
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
									+ 'onclick="visitorsManager.show('
									+ "'"
									+ r.id
									+ ","
									+ a
									+ ",1"
									+ "'"
									+ ');">查看</a>';
						}
					}
				}
				,{
					field : 'satisfaction',
					title : '总体满意度',
					align:'center',width:50,
					
				}] ];
				}
				if(type==4 || type ==5){
					
					var columns = [ [ {
						field : 'id',
						title : 'id',
						align:'center',width:100,hidden:true

					},{
						field : 'name',
						title : '名称',
						align:'center',width:100
					},{
						field : 'visitors_describe',
						title : '简介',
						align:'center',width:200
					},{
						field : 'address',
						title : '地址',
						align:'center',width:100
					},
					{
						field : 'head_img',
						title : '主图',
						align : 'center',
						width : 50,
						formatter : function(
								v, r, i) {
								var a = "";
							if (v != null){
								a = r.head_img;
								a = a.replace(
										"\\",
										"/");
								return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
										+ "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
										+ "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
										+ 'onclick="visitorsManager.show('
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
										+ 'onclick="visitorsManager.show('
										+ "'"
										+ r.id
										+ ","
										+ a
										+ ",1"
										+ "'"
										+ ');">查看</a>';
							}
						}
					}
					,{
						field : 'satisfaction',
						title : '总体满意度',
						align:'center',width:50,
						
					}] ];
					
					columns[0].push({
						field : 'integral',
						title : '积分',
						align:'center',width:50
					},{
						field : 'is_recommend',
						title : '推荐',
						align : 'center',
						width : 50,
						formatter : function(
								v, r, i) {
						        if(r.is_recommend == 0) {
		                            return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
		                                    + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
		                                    + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
		                                    + 'onclick="visitorsManager.updateRecommend('
		                                    + "'"
		                                    + r.id
		                                    + ",1"
		                                    + "'"
		                                    + ');">推荐</a>';
		                        }
						        if(r.is_recommend == 1) {
		                            return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
		                                    + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
		                                    + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
		                                    + 'onclick="visitorsManager.updateRecommend('
		                                    + "'"
		                                    + r.id
		                                    + ",0"
		                                    + "'"
		                                    + ');">取消推荐</a>';
		                        }
						}
					});
					columns[0].push({
						field : 'state',
						title : '上/下架',
						align : 'center',
						width : 50,
						formatter : function(
								v, r, i) {
						        if(r.state == 0) {
		                            return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
		                                    + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
		                                    + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
		                                    + 'onclick="visitorsManager.updateState('
		                                    + "'"
		                                    + r.id
		                                    + ",1"
		                                    + "'"
		                                    + ');">下架</a>';
		                        }
						        if(r.state == 1) {
		                            return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
		                                    + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
		                                    + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
		                                    + 'onclick="visitorsManager.updateState('
		                                    + "'"
		                                    + r.id
		                                    + ",0"
		                                    + "'"
		                                    + ');">上架</a>';
		                        }
						}
					});
					
				}
				$('#visitorsGid').datagrid({
					url:'./background/visitorsManage/getVisitorsList.json',
					border:false,
					fit:true,
					singleSelect:true,
					idField:'id',
					pagination:true,
					pageSize:20,
					striped:true,
					fitColumns:true,
					queryParams:{
						type : $("#typeId").val()
					},
					columns : columns
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
			$('#selectType').val(arr[2]);
			if (arr[1] != null && arr[1] != ""){
				$('#showImg').attr('src', arr[1]);
			}else {
				$('#showImg').attr('alt', '暂时还没有主图，请上传！');
			}
		},
		
		add : function(){
			var type = $("#typeId").val();
			window.location.href = visitorsManager.toAddUrl+"?type="+type;
		},
		
		
		edit : function() {
			var row = $('#visitorsGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			window.location.href = visitorsManager.editUrl+"?id="+row.id;
		},
		
		updateImg : function(){
			var row = $('#visitorsGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			
			$('#ImageSubWindow').window('open');
			$('#uploadForm').form('clear');
			$('#ImgId').val(row.id);
			$('#imgSrcId').attr('src',row.head_img);
		},
		
		submit : function(){
			var a = visitorsManager.updateImgUrl;
			/*if($('#imgFile').val() == null || $('#imgFile').val() == ''){
				a = bankListManager.subUrl1;
			}*/
			var formElement = document.getElementById("uploadForm");
			var oReq = new XMLHttpRequest();
			$.messager.progress({
				title : '提示信息',
				msg : '请稍候...',
				interval : 2000
			});
			oReq.open("POST", a, true);
			oReq.send(new FormData(formElement));
			oReq.onreadystatechange = function() {
				if (oReq.readyState == 4) {
					console.log(oReq.status);
					if (oReq.status == 200) {
						var b = oReq.responseText;
						console.log(b);
						if (b == "success") {
                            eu.showMsg("操作成功");
							$.messager.progress('close');
							$('#ImageSubWindow').window('close');
							$('#visitorsGid').datagrid('reload');
						} else {
							$.messager.progress('close');
							eu.showMsg("失败");
						}
					} else {
						$.messager.progress('close');
						eu.showMsg("失败");
					}
				}
			}
		},
		
		serch : function(){
			var name =  $("#searchValue1").val();
			var type = $("#typeId").val();
			$('#visitorsGid').datagrid('load',{name: name,type:type});
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
		},
		
		manyidu : function(){
			var row = $('#visitorsGid').datagrid('getSelected');
			if(!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$('#manyiduWindow').window('open');
			$('#manyiForm').form('clear');
			$('#manyiId').val(row.id);
			$('#manyiduId').val(row.satisfaction);

		},
		
		manyiSub : function(){
			var id = $('#manyiId').val();
			var manyidu = $('#manyiduId').combobox('getValue');
			$.post(visitorsManager.manyiduUrl, {
				id : id,
				satisfaction :manyidu
			}).success(function(data) {
				if (data.success) {
					eu.showMsg("操作成功！");
					$('#visitorsGid').datagrid('reload');
					$('#manyiduWindow').window('close');
					$('#manyiForm').form('clear');
				} else {
					eu.showMsg(data);
				}
			}).error (function (data) {
				eu.showMsg("系统错误，请联系管理员！")
			})
		},
		//更新“推荐”状态
		updateRecommend:function(param){
			var arr = new Array();
			arr = param.split(",");
			$.post(visitorsManager.manyiduUrl, {
				id : arr[0],
				isRecommend :arr[1]
			}).success(function(data) {
				if (data.success) {
					eu.showMsg("操作成功！");
					$('#visitorsGid').datagrid('reload');
				} else {
					eu.showMsg(data);
				}
			}).error (function (data) {
				eu.showMsg("系统错误，请联系管理员！")
			})
		},
		//上/下架，更新商品状态
		updateState : function(param){
			var arr = new Array();
			arr = param.split(",");
			$.post(visitorsManager.manyiduUrl, {
				id : arr[0],
				state :arr[1]
			}).success(function(data) {
				if (data.success) {
					eu.showMsg("操作成功！");
					$('#visitorsGid').datagrid('reload');
				} else {
					eu.showMsg(data);
				}
			}).error (function (data) {
				eu.showMsg("系统错误，请联系管理员！")
			})
		}
};
visitorsManager.init();