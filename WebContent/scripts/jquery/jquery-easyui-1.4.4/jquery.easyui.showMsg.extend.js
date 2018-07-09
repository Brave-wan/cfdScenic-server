/**
 * 包含easyui的扩展和常用的jQuery方法扩展.
 * 
 * @author 尔演&Eryan eryanwcp@gmail.com
 * @version 2013-05-26
 */

// Easyui 扩展
/**
 * 定义全局对象，easyui的扩展命名空间.
 */
var eu = $.extend({}, eu);

eu.showMsg = function(msg) {
	 $.messager.alert('提示信息！', msg);
//	$.messager.show({
//				title : '提示信息',
//				msg : msg,
//				timeout : 1000,
//				showType : 'slide'
//			});

}
//eu.showMsg = function(msg) {
//	var n = noty({
//  		text: msg,
//  		type: 'information',
//      dismissQueue: true,
//  		layout: 'center',
//  		theme: 'defaultTheme'
//  	});
//}
//eu.notyError = function(msg) {
//	var n = noty({
//  		text: msg,
//  		type: 'error',
//      dismissQueue: true,
//  		layout: 'center',
//  		theme: 'defaultTheme'
//  	});
//}
/**
 * 弹出窗口 提示信息.
 * 
 * @param msgString
 * @param msgType
 *            info,error,question,warning
 */
eu.showAlertMsg = function(msgString, msgType) {
	$.messager.alert('提示信息！', msgString, msgType);
}

/**
 * 带进度条提示信息
 * 
 * @param msg
 *            消息内容
 * @param time
 *            超时时间(毫秒值)
 */
eu.showProMsg = function(msg, time) {
	$.messager.progress({
				title : '提示信息！',
				msg : msg
			});
	setTimeout(function() {
				$.messager.progress('close');
			}, time);
}

/**
 * 添加页面到指定选项卡.
 * 
 * @param tabs
 *            选项卡对象,或者选项卡的id
 * @param title
 *            标题
 * @param url
 *            链接地址
 * @param closeAble
 *            是否允许关闭
 * @param iconCls
 *            图标
 */
eu.addTab = function(tabs, title, url, closeAble, iconCls) {
	var closable = true;
	if (undefined != typeof closeAble) {
		closable = closeAble;
	}
	// 如果tabs是字符串类型则代表选项卡的id
	if (typeof tabs == 'string') {
		tabs = $('#' + tabs).tabs();
	}
	if (iconCls == 'undefined') {
		iconCls = '';
	}
	// 如果当前title的tab不存在则创建一个tab
	if (!tabs.tabs('exists', title)) {
		// tabs.tabs('add',
		// {title:title,closable:closable,iconCls:iconCls,cache:true,href:url});
		tabs.tabs('add', {
			title : title,
			closable : closable,
			iconCls : iconCls,
			content : '<iframe src="'
					+ url
					+ '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>'
		});
		// tabs.tabs('addIframeTab',{
		// tab:{title:title,closable:true},iframe:{src:url} });
		$(".tabs-header").bind('contextmenu',function(e){
	        e.preventDefault();
	        $('#centerTabssMenu').menu('show', {
	            left: e.pageX,
	            top: e.pageY
	        });
	    });
	} else {
		tabs.tabs('select', title);
	}
}

/**
 * 扩展easyui属性 dategrid表头居中.
 */
eu.datagridCenter = function() {
	$(".datagrid-cell").css('text-align', 'center');
}
/**
 * easyui title居中
 */
eu.panelCenter = function() {
	$(".panel-title").css('text-align', 'center');
}

/**
 * 
 * @requires jQuery,EasySSH,jQuery cookie plugin
 * 
 * 更换EasySSH主题的方法
 * 
 * @param themeName
 *            主题名称
 */
eu.changeTheme = function(themeName) {
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName
			+ '/easyui.css';
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for (var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#easyuiTheme').attr('href', href);
		}
	}

	$.cookie('easyuiThemeName', themeName, {
				expires : 7
			});
};

/**
 * 列表点击不选择
 * 
 * @param id
 *            datagrid的ID
 * @param rowIndex
 * @param rowData
 */
eu.unSelected = function(id, rowIndex, rowData) {
	var selected = $('#' + id).datagrid('getSelections');
	// 获取所有选中的行数组，如果里边有这个，不选中 否则选中
	if (jQuery.inArray(rowData, selected) != -1) {
		$('#' + id).datagrid('unselectRow', rowIndex);
	} else {
		$('#' + id).datagrid('selectRow', rowIndex);
	}
}
/**
 * 自定义combotree级联选择（适用于jQuery easyui 1.3.2） 父节点选中,子节点将全被选中；子节点选中,所有父节点也将被选中.
 * 
 * @param tree
 *            combotree下的树tree
 * @param node
 *            选中的节点
 */
eu.myCascadeCheck = function(tree, node) {
	if (tree == 'undefined' || node == 'undefined') {
		return;
	}
	if (node.checked) {
		node.checked = false;
		tree.tree('uncheck', node.target);
	} else {
		node.checked = true;
		tree.tree('check', node.target);
	}

	var tempNode;// 被点节点 临时变量
	tempNode = node;
	// 循环遍历父节点
	while (tempNode) {
		var parentNode = tree.tree('getParent', tempNode.target);// 父节点
		tempNode = parentNode;
		if (tempNode) {
			tree.tree('check', tempNode.target);
		}

	}
	tempNode = node;
	if (tempNode) {
		var childNodes = tree.tree('getChildren', tempNode.target);// 得到该节点下的所有节点数组
		var childNode;
		for (var i = 0; i <= childNodes.length; i++) {
			childNode = childNodes[i];
			if (childNode) {
				if (tempNode.checked) {
					tree.tree('check', childNode.target);
				} else {
					tree.tree('uncheck', childNode.target);
				}

			}
		}

	}
}
