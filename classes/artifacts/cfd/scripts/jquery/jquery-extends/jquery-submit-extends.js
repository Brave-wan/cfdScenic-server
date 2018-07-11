var submit = function(obj,url,params,errorCB){
	if (!obj || !url || !params) {
		alert('submit方法参数不完整');
		return null;
	}
	eu.showProMsg("数据处理中，请稍后...", 3000);
	$.post(url, params, function(data) {
		$.messager.progress('close');
		if (data.success) {
			eu.showMsg("操作成功！");
			if(obj.dataGrid){
				$('#'+obj.dataGrid).datagrid('reload');
				$('#'+obj.dataGrid).datagrid('clearSelections');
			}
			if(obj.sonDataGrid){
				$('#'+obj.sonDataGrid).datagrid({data:[]});
			}
			if(typeof(obj.disableForm) == 'function')
				obj.disableForm();
		} else {
			eu.showMsg(data.message);
			if(errorCB)
				errorCB();
		}
	}).error(function(ex) {
		eu.showMsg(ex.responseText);
		$.messager.progress('close');
		if(errorCB)
			errorCB();
	});
	if(errorCB)
		errorCB();
}