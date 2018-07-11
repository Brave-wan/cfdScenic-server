
/**
 * 人员选择控件，通过$('#userselections').userselections()方法将此组件定义为人员选择控件
 * 定义组件时可以携带参数{onSave:function(user){},onClear:function(){}},当用户点击确定按钮时调用，调用onSave方法参数为所选用户的全部信息,当用户确定清除框内数据时调用onClear方法
 * 当此组件点击时，将判断此组件是否有值，如有，则提示是否清空组件内数据，如果选“是”则清空组件的数据并弹出人员选择框，如果“否”则直接弹出人员选择框
 * 示例：
  				<input type="text" id="username" class="easyui-validatebox" readonly/>
  				<input type="hidden" id="userid" />
  				
  				
  				$('#username').userselections({
					onSave:function(user){
						console.info(user);
						$('#userid').val(user.buId);
					}
				});
 * 
 * 被设置为人员选择框的组件，将会自动填充用户名，由于onSave是在填充用户名之后执行的，所以可以通过onSave设置其他值（最好设置为readonly）
 * 
 */
var us = {
		nameInput:undefined,
		isReady:false,
		options:undefined,
		onUserSelectionsDeptClick:function(node){
			if(node.attributes==undefined || node.attributes==null){
				$('#userSelectionsUser').datagrid({
					url:'base/baseuser/pageFindAll'
				});
			}else{
				$('#userSelectionsUser').datagrid({
					url:'base/basedepartment/pageFindAllUser',
					queryParams:{
						bdId : node.attributes.bdId,
					}
				});
			}
		},
		sure:function(){
			us.save($('#userSelectionsUser'));
		},
		sureAll:function(){
			us.save($('#userSelectionsAllUser'));
		},
		save:function(datagrid){
			var row=datagrid.datagrid('getSelected');
			if(row){
				$(us.nameInput).val(row.buName);
				if(us.options.onSave!=undefined){
					us.options.onSave(row);
				}
				$('#userSelectionsWindow').window('close');
			}else{
				eu.showMsg("请选择人员！");
			}
		},
		
		search:function(){
			var searchValue=$('#userSelectionsUserName').val();
			$('#userSelectionsAllUser').datagrid('load',{searchValue:searchValue});
		},
		cancel:function(){
			$('#userSelectionsWindow').window('close');
		},
		initModules:function(){
			us.isReady=true;
			var str="<div id='userSelectionsWindow' class='easyui-window' title='人员选择' "
    			+"style='width: 720px; height: 500px; padding: 5px;'>"
    			+"<div id='userSelectionsTabs' class='easyui-tabs' data-options='fit:true'>"
    				+"<div title='按部门选择'>"
    					+"<div class='easyui-layout' id='userSelectionsLayout'>"
    						+"<div data-options='region:"+'"west"'+"' style='width:200px;'>"
    							+"<ul id='userSelectionsDept' class='easyui-tree'</ul>"
    						+"</div>"
    						+"<div data-options='region:"+'"center"'+"' >"
    								+"<table id='userSelectionsUser' class='easyui-datagrid' ></table>"
    						+"</div>"
    						+'<div data-options="'+"region:'south',border:false"+'" style="text-align: right;height:50px; padding: 10px 0 0;">'
    							+'<a id="userSelectionsSureBtn" class="easyui-linkbutton" data-options="'+"iconCls:'icon-ok'"+'" href="javascript:void(0)" style="margin-right:10px;">确定</a>'
    							+'<a id="userSelectionsCancelBtn" class="easyui-linkbutton" data-options="'+"iconCls:'icon-cancel'"+'" href="javascript:void(0)">取消</a>'
    						+"</div>"
    					+"</div>"
    				+"</div>"
    				+"<div title='按用户名选择'>"
    					+"<div class='easyui-layout' id='userSelectionsAllLayout'>"
    						+"<div data-options='region:"+'"north"'+"' style='height:50px;'>"
    							+"用户名：<input id='userSelectionsUserName' class='easyui-validatebox'/>" +
    									"<a class='easyui-linkbutton' href='javascript:void(0)' id='userSelectionsSearch'>查询</a>"
    						+"</div>"
    						+"<div data-options='region:"+'"center"'+"' >"
    								+"<table id='userSelectionsAllUser' class='easyui-datagrid' ></table>"
    						+"</div>"
    						+'<div data-options="'+"region:'south',border:false"+'" style="text-align: right;height:50px; padding: 10px 0 0;">'
    							+'<a id="userSelectionsAllSureBtn" class="easyui-linkbutton" data-options="'+"iconCls:'icon-ok'"+'" href="javascript:void(0)" style="margin-right:10px;">确定</a>'
    							+'<a id="userSelectionsAllCancelBtn" class="easyui-linkbutton" data-options="'+"iconCls:'icon-cancel'"+'" href="javascript:void(0)">取消</a>'
    						+"</div>"
    					+"</div>"
    				+"</div>"
    			+"</div>"
    		+"</div>";
			$('body').append(str);
			
			$('#userSelectionsWindow').window({
				closed:true
			});
			
			$('#userSelectionsTabs').tabs();
			$('#userSelectionsLayout').layout({fit:true});
			$('#userSelectionsAllLayout').layout({fit:true});
			
			$('#userSelectionsDept').tree({
				url:"base/basedepartment/findDeptTree",
				method:"post",
				animate:true,
				onClick:us.onUserSelectionsDeptClick
			});
			
			$('#userSelectionsUser').datagrid({
				border:false,
				fit:true,
				singleSelect:true,
				idField:'buId',
				pagination:true,
				pageSize:10,
				striped:true,
				columns:[
				         [    
				          {field:'buName',align:'center',sortable:true,title:"用户名",width:100},    
				          {field:'buPhone',align:'center',sortable:true,title:"手机号",width:100}    
				          ]
				         ] 
			});
			
			$('#userSelectionsAllUser').datagrid({
				url:'base/baseuser/pageFindAll',
				border:false,
				fit:true,
				singleSelect:true,
				idField:'buId',
				pagination:true,
				pageSize:10,
				striped:true,
				columns:[
				         [    
				          {field:'buName',align:'center',sortable:true,title:"用户名",width:100},    
				          {field:'buPhone',align:'center',sortable:true,title:"手机号",width:100}    
				          ]
				         ] 
			});
			$('#userSelectionsSureBtn').linkbutton({onClick:us.sure});
			$('#userSelectionsCancelBtn').linkbutton({onClick:us.cancel});
			$('#userSelectionsAllSureBtn').linkbutton({onClick:us.sureAll});
			$('#userSelectionsAllCancelBtn').linkbutton({onClick:us.cancel});
			$('#userSelectionsSearch').linkbutton({onClick:us.search})
		}
}
$.parser.plugins.push("userselections");
$.fn.userselections = function (options, param){
	 if (typeof options == "string") { 
		 return $.fn.combobox.apply(this, arguments); 
		}
     options = options || {};
     
     return this.each(
    		 function(){
				if(!us.isReady){
					us.initModules();
				}
				$(this).on('click',function(){
					us.nameInput=this;
					us.options=options;
					var userWindow=$('#userSelectionsWindow');
					if($(this).val().length>0){
						$.messager.confirm("提示","是否清空人员数据？",function(r){
							if(r){
								$(us.nameInput).val('');
								if(options.onClear!=undefined){
									options.onClear();
								}
							}
							userWindow.window('open');
						})
					}else{
						userWindow.window('open');
					}
				})
    		 }
     	)
}