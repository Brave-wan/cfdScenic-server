var shopInformationManager = {
		addUrl : "./background/shopInformationManager/addVisitors",
		delUrl : "./background/shopInformationManager/deleteShopInformation",
		qiyongurl : "./background/shopInformationManager/qiyong",
		jinyongurl : "./background/shopInformationManager/jinyong",
		ylUrl  : "./background/shopInformationManager/showPage",
		toAddUrl  : "./background/shopInformationManager/toAddPage",
		editUrl  : "./background/shopInformationManager/toEditPage",
		//manyiduUrl :"./background/shopInformationManager/manyidu",
		updateImgUrl :"./background/shopInformationManager/updateImg",
        auditUrl : "./background/shopInformationManager/audit",
		init : function(){
			$(window).load(function(){
				$('#shopInformationGid').datagrid({
					url:'./background/shopInformationManager/getShopInformationList.json',
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
						field : 'name',
						title : '店铺名称',
						align:'center',width:100
					},{
						field : 'address',
						title : '地址',
						align:'center',width:200
					},{
						field : 'headImg',
						title : '头像',
						align : 'center',
						width : 40,
						formatter : function(
								v, r, i) {
								var a = "";
							if (v != null){
								a = r.headImg;
								a = a.replace(
										"\\",
										"/");
								return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
										+ "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
										+ "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
										+ 'onclick="shopInformationManager.show('
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
										+ 'onclick="shopInformationManager.show('
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
                        field : 'backgroudImg',
                        title : '背景图',
                        align : 'center',
                        width : 40,
                        formatter : function(
                            v, r, i) {
                            var a = "";
                            if (v != null){
                                a = r.backgroudImg;
                                a = a.replace(
                                    "\\",
                                    "/");
                                return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
                                    + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
                                    + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
                                    + 'onclick="shopInformationManager.show('
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
                                    + 'onclick="shopInformationManager.show('
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
                        field : 'holdCardImg',
                        title : '手持证件照',
                        align : 'center',
                        width : 40,
                        formatter : function(
                            v, r, i) {
                            var a = "";
                            if (v != null){
                                a = r.holdCardImg;
                                a = a.replace(
                                    "\\",
                                    "/");
                                return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
                                    + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
                                    + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
                                    + 'onclick="shopInformationManager.show('
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
                                    + 'onclick="shopInformationManager.show('
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
                        field : 'faceCardImg',
                        title : '身份证正面照',
                        align : 'center',
                        width : 40,
                        formatter : function(
                            v, r, i) {
                            var a = "";
                            if (v != null){
                                a = r.faceCardImg;
                                a = a.replace(
                                    "\\",
                                    "/");
                                return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
                                    + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
                                    + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
                                    + 'onclick="shopInformationManager.show('
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
                                    + 'onclick="shopInformationManager.show('
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
                        field : 'backCardImg',
                        title : '身份证反面照',
                        align : 'center',
                        width : 40,
                        formatter : function(
                            v, r, i) {
                            var a = "";
                            if (v != null){
                                a = r.backCardImg;
                                a = a.replace(
                                    "\\",
                                    "/");
                                return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
                                    + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
                                    + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
                                    + 'onclick="shopInformationManager.show('
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
                                    + 'onclick="shopInformationManager.show('
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
                        field : 'shopImg',
                        title : '商家logo',
                        align : 'center',
                        width : 30,
                        formatter : function(
                            v, r, i) {
                            var a = "";
                            if (v != null){
                                a = r.shopImg;
                                a = a.replace(
                                    "\\",
                                    "/");
                                return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
                                    + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
                                    + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
                                    + 'onclick="shopInformationManager.show('
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
                                    + 'onclick="shopInformationManager.show('
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
						field : 'groupName',
						title : '店铺分类',
						align:'center',width:50
					},{
						field : 'isAudit',
						title : '审核状态',
						align:'center',width:50,
                        formatter : function (
                            v, r, i) {
                            switch (v) {
                                case 0:
                                    return "提交审核";
                                case 1:
                                    return "审核通过";
                                case 2:
                                    return "审核失败";
                            }
                        }
					},{
                        field : 'auditFail',
                        title : '审核失败原因',
                        align:'center',width:50
					},{
						field : 'state',
						title : '店铺状态',
						align:'center',width:50,
	                    formatter : function (
	                        v, r, i) {
	                        switch (v) {
	                            case 0:
	                                return "正常";
	                            case 1:
	                                return "禁用";
	                            case 2:
	                                return "删除";
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
            $('#showImg').attr('src','');
			$('#ImgWindow').window('open');
			$('#imgId').val(arr[0]);
			$('#selectType').val(arr[2]);
			if (arr[1] != null && arr[1] != ""){
				$('#showImg').attr('src', arr[1]);
			}
		},
        
		
		add : function(){
			 $.post(shopInformationManager.addUrl).success( function (data) {
	                if (data.success) {
	                	window.location.href = shopInformationManager.toAddUrl;
	                } else {
	                    eu.showMsg(data.message);
	                }
	            }).error( function (data) {
	                eu.showMsg("系统错误，请联系管理员！");
	            })
		},
		
		
		edit : function() {
			var row = $('#shopInformationGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			if(row.isAudit==0){
				eu.showMsg("该信息还未进行审核，不能修改");
				return;
			}
			if(row.isAudit==1){
				eu.showMsg("该信息已审核通过，不能修改");
				return;
			}
			window.location.href = shopInformationManager.editUrl+"?id="+row.id;
		},
		
		updateImg : function(){
			var row = $('#shopInformationGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			
			$('#ImageSubWindow').window('open');
			$('#uploadForm').form('clear');
			$('#ImgSubId').val(row.id);
			$('#imgSrcId').attr('src',row.backgroudImg);
		},
		
		submit : function(){
			var a = shopInformationManager.updateImgUrl;
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
							$('#shopInformationGid').datagrid('reload');
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

        search : function(){
			var name =  $("#searchValue1").val();
			$('#shopInformationGid').datagrid('load',{name: name});
			$('#shopInformationGid').datagrid('clearSelections');
		},
		
		del : function() {
			var row = $('#shopInformationGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$.messager.confirm("提示信息", "您确定要将该店铺删除吗？", function(r) {
				if (r) {
					$.post(shopInformationManager.delUrl, {
						id : row.id
					}).success(function(data) {
						if (data.success) {
							eu.showMsg("操作成功！");
							$("#shopInformationGid").datagrid('reload');
						} else {
							eu.showMsg(data);
						}
					}).error(function(data) {
						eu.showMsg("系统错误，请联系管理员！");
					})
				} else {
					$('#shopInformationGid').datagrid('clearSelections');
				}
			});
		},
		qiyong : function() {
			var row = $('#shopInformationGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$.messager.confirm("提示信息", "您确定要启用该店铺吗？", function(r) {
				if (r) {
					$.post(shopInformationManager.qiyongurl, {
						id : row.id
					}).success(function(data) {
						if (data.success) {
							eu.showMsg("操作成功！");
							$("#shopInformationGid").datagrid('reload');
						} else {
							eu.showMsg(data);
						}
					}).error(function(data) {
						eu.showMsg("系统错误，请联系管理员！");
					})
				} else {
					$('#shopInformationGid').datagrid('clearSelections');
				}
			});
		},
		jinyong : function() {
			var row = $('#shopInformationGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$.messager.confirm("提示信息", "您确定要禁用该店铺吗？", function(r) {
				if (r) {
					$.post(shopInformationManager.jinyongurl, {
						id : row.id
					}).success(function(data) {
						if (data.success) {
							eu.showMsg("操作成功！");
							$("#shopInformationGid").datagrid('reload');
						} else {
							eu.showMsg(data);
						}
					}).error(function(data) {
						eu.showMsg("系统错误，请联系管理员！");
					})
				} else {
					$('#shopInformationGid').datagrid('clearSelections');
				}
			});
		},
		yl : function(){
			var row = $("#shopInformationGid").datagrid('getSelected');
			if(!row){
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			window.location.href = shopInformationManager.ylUrl+"?id="+row.id;
		},

        audit : function () {
            var row = $("#shopInformationGid").datagrid("getSelected");
            if (!row) {
                eu.showMsg("请选择一行再进行操作");
                return;
            }
			if(row.isAudit==1){
				eu.showMsg("该信息已审核通过，不能修改");
				return;
			}
            $("#auditWindow").window("open");
            $("#auditId").val(row.id);
        },

        auditOK : function () {
            var auditId = $("#auditId").val();
            $.post(shopInformationManager.auditUrl, {
                id : auditId,
                isAudit : 1,
            }).success( function (data) {
                if (data.success) {
                    eu.showMsg("操作成功！");
                    $("#auditWindow").window("close");
                    $("#shopInformationGid").datagrid('reload');
                } else {
                    eu.showMsg(data);
                }
            }).error( function (data) {
                eu.showMsg("系统错误，请联系管理员！");
            })
        },

        auditNO : function () {
            var auditId = $("#auditId").val();
            $("#auditWindow").window("close");
            $("#auditNoWindow").window("open");
            $("#auditNoId").val(auditId);
        },

        subAudit : function () {
            var auditId = $("#auditNoId").val();
            var auditNoMessage = $("#auditNoMessage").val();
            if (auditNoMessage == null || "" ==auditNoMessage) {
                eu.showMsg("请填写审核失败原因！");
                return;
            }
            $.post(shopInformationManager.auditUrl, {
                id : auditId,
                isAudit : 2,
                auditFail : auditNoMessage
            }).success( function (data) {
                if (data.success) {
                    eu.showMsg("操作成功！");
                    $('#uploadForm').form('clear');
                    $("#auditWindow").window("close");
                    $("#shopInformationGid").datagrid('reload');
                } else {
                    eu.showMsg(data);
                }
            }).error( function (data) {
                eu.showMsg("系统错误，请联系管理员！");
            })
        }

};
shopInformationManager.init();