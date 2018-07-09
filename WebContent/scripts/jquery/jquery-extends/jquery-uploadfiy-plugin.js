(function($) {
	// 定义加载上传控件方法

	$.fn.zckjuploadU = function(options) {
		if (this.size() != 1) {
			alert("上传控件不支持多对象操作！");
			return this;
		}
		;
		this.append(fileuploadhtml_base);
		$.parser.parse('#' + this.attr('id'));
		$('#uploadGrid').datagrid({
			url : 'file/pageFindFile',
			fit : true,
			fitColumns : true,
			pagination : true,
			border : false,
			striped : true,
			singleSelect:true,
			columns : [ [ {
				field : 'uplooldname',
				title : '文件名称',
				sortable : true,
				width : 170,
				formatter : zckjuploadU_Formatfiledname
			}, {
				field : 'uplodate',
				title : '上传时间',
				sortable : true,
				width : 100,
				formatter : formatDateTime
			}, {
				field : 'uplobytes',
				title : '文件大小(M)',
				sortable : true,
				width : 60,
				formatter : zckjuploadU_Formatfiledsize
			} ] ]
		});

		this.loading = loading;
		$('#win').window('close');
		return this;
	};

	// 格式化文件名称，点击弹出下载界面
	function zckjuploadU_Formatfiledname(value, row, index) {
		var oldName = row.uplooldName;
		return '<a href="file/downFile?id=' + row.uploid + '" target="_blank" >'
				+ oldName + '</a>';
	}

	// 格式化大小
	function zckjuploadU_Formatfiledsize(value, row, index) {
		return (value / 1024.0 / 1024.0).toFixed(2);
	}

	// 删除按钮
	function zckjuploadU_Remotedelupfiled(uploadid) {
		$.post('file/deleteById', {
			id : uploadid
		}).success(function(data) {
			if (data.success) {
				$('#uploadGrid').datagrid('reload');// 重新加载
				$('#uploadGrid').datagrid('unselectAll');// 选中项全部取消
			} else {
				$.messager.alert('警告', data.msg);
			}
		}).error(function(ex) {
			eu.showMsg(ex.responseText);
		});
	}

	// 实现控件的方法
	function loading(options) {
		var isadmin=null;
		if (options.fileid != null) {  
			 isadmin = options.isadmin; 
			$('#win').window('open');
			$('#uploadGrid').datagrid({
				queryParams : {
					fileid : options.fileid
				}
			});

			$('#fileuploadhtml_baselayout').layout('remove', 'west');
			$('#fileuploadhtml_gridlayout').layout('remove', 'north');
			if (isadmin) {
				$('#fileuploadhtml_baselayout').layout('add', {
					region : 'west',
					width : 220,
					split : true,
					content : fileuploadhtml_upform
				});
				$('#fileuploadhtml_gridlayout').layout('add', {
					region : 'north',
					height : 40,
					split : true,
					content : fileuploadhtml_delbtn
				});
				// 加载uploadify控件
				if (!options.sizeLimit) {
					options.sizeLimit = '307204KB';
				}
				if (!options.typeExts) {
					options.typeExts = '*.*;';
				}
				if (!options.quantityLimit) {
					options.quantityLimit = 3;
				}
				$("#file_upload").uploadify({
					'height' : 27,
					'width' : 80,
					'buttonText' : '添加附件',
					'swf' : getRootPath()+'/scripts/jquery/jquery-uploadify/uploadify.swf',
					'preventCaching' : true,
					'uploader' : getRootPath()+'/file/uploadFile', // 上传路径
					'method' : 'post',
					'auto' : false, // 是否自动提交，若为true，则在文件选中后立即提交
					'fileSizeLimit' : options.sizeLimit, // 文件限制大小
					// ('30*1024KB')
					'fileTypeExts' : options.typeExts, // 限定上传格式 ('*.doc;
					// *.jpg; *.rar')
					'uploadLimit' : options.quantityLimit, // 上传个数限制 (3)
					'onUploadStart' : function(file) {
					},
					'onUploadSuccess' : function(file, data, response) { // 所有文件上传成功后触发，data可以接收返回值
						$('#uploadGrid').datagrid('reload');
					},
					'onUploadComplete' : function() {
					}
				});
			}
		} 
		// 全部取消上传文件
		$('#Ucancel').bind('click', function() {
			$('#file_upload').uploadify('cancel', '*');
		});
		// 全部上传文件
		$('#Umfiled').bind('click', function() {
			$('#file_upload').uploadify('settings', 'formData', {
				'fileid' :options.fileid
			});
			$('#file_upload').uploadify('upload', '*');
		});
		// 删除一行的操作
		$('#Urfiled').bind('click', function() {
			var row = $('#uploadGrid').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '您确认想要删除选中【'+row.uplooldName+'】的文件吗？', function(r) {
					if (r) {
						zckjuploadU_Remotedelupfiled(row.uploid);
					}
				});
			}
		});

	}
	var fileuploadhtml_base = ' <div id="win" class="easyui-window"  data-options="closed:true" title="附件上传" style="height:400px;width:660px; " > '
			+ '<div id="fileuploadhtml_baselayout" class="easyui-layout" data-options="fit:true">'
			+ '<div data-options="region:\'center\',border:true" data-options="fit:true">'
			+ '<div  id="fileuploadhtml_gridlayout" class="easyui-layout" data-options="fit:true">'
			+ '<div data-options="region:\'center\',border:false" data-options="fit:true">'
			+ '<table id="uploadGrid" class="easyui-datagrid"></table>'
			+ '</div>' + '</div>' + '</div>' + '</div>' + '</div>';

	fileuploadhtml_upform = '<div class="easyui-layout" data-options="fit:true">'
			+ '<div data-options="region:\'center\',border:false" data-options="fit:true">'
			+ '<form id="uploadFrom" name="uploadFrom" enctype="multipart/form-data" method="post" action="file/uploadFile">'
			+ '<input type="file" name="uploadify" id="file_upload" /><br/>'
			+ '</form>'
			+ '</div>'
			+ '<div data-options="region:\'south\',border:false" style="height:26px;">'
			+ '<a id="Umfiled" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:\'icon-save\',plain:true">附件上传</a>'
			+ '<a id="Ucancel" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:\'icon-remove\',plain:true">全部取消</a>'
			+ '</div>' + '</div>';
	fileuploadhtml_delbtn = '<a id="Urfiled" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:\'icon-remove\',plain:true">删除文件</a>';

})(jQuery);
