// 
//$.extend($.fn.datagrid.defaults,{
//	onLoadSuccess:function(target){
//		 var opts = $(this).datagrid('options');
//		 var data = $(this).datagrid('getData');
//			 if(data.rows.length > 0){
//				opts.onClickRow.call(this, 0, data.rows[0]);
//			 }
//	 }
//});
 $.extend($.fn.datagrid.defaults, {

    onLoadSuccess: function () { 
        $(this).datagrid("clearSelections"); 
        $(".datagrid-header-check :checkbox").attr("checked", false); 
        //$(this).data("isReload") && 
//        if ($(this).datagrid("getRows").length == 0) { 
//            var msg = $("<div style='display:none;margin-top:20px;padding:10px;border:1px solid #AAAAA;background-color:yellow'>搜索无结果！</div>"); 
//            msg.insertAfter($(this)); 
//            msg.show(200); 
//            setTimeout(function () { 
//                msg.hide(200); 
//            }, 3000); 
//        } 
        //$(this).data("isReload", true); 
        
    }, 
    onUnselect: function (rowIndex, rowData) { 
        $(".datagrid-header-check :checkbox").attr("checked", false); 
    }, 
    onSelect: function () { 
        var tThis = $(this); 
        if (tThis.datagrid("getRows").length == tThis.datagrid("getSelections").length) { 
            $(".datagrid-header-check :checkbox").attr("checked", true); 
        } 
        return true; 
    }, 
    loadFilter: function (data) { 
        for (var i = 0; i < data.rows.length; i++) { 
               for (var att in data.rows[i]) { 
                   if (typeof (data.rows[i][att]) == "string") { 
                       data.rows[i][att] = data.rows[i][att].replace(/</g, "&lt;").replace(/>/g, "&gt;"); 
                   } 
               } 
        } 
        return data; 
    } 

}); 
/**
 * EasyUI from方法扩展
 */
$.extend ($.fn.form.methods, {
    /**
	 * getData 获取数据接口 使用示例:$('#ff').form('getData',true);
	 * 
	 * @param {Object}
	 *            jq
	 * @param {Object}
	 *            params 设置为true的话，会把string型"true"和"false"字符串值转化为boolean型。
	 */
    getData : function (jq, params) {

	    var formArray = jq.serializeArray ();
	    var oRet = {};
	    for ( var i in formArray) {
		    if (typeof (oRet[formArray[i].name]) == 'undefined') {
			    if (params) {
				    oRet[formArray[i].name] = (formArray[i].value == "true" || formArray[i].value == "false") ? formArray[i].value == "true" : formArray[i].value;
			    } else {
				    oRet[formArray[i].name] = formArray[i].value;
			    }
		    } else {
			    if (params) {
				    oRet[formArray[i].name] = (formArray[i].value == "true" || formArray[i].value == "false") ? formArray[i].value == "true" : formArray[i].value;
			    } else {
				    oRet[formArray[i].name] += "," + formArray[i].value;
			    }
		    }
	    }
	    return oRet;
    },
    /**
	 * 将form表单 启用/禁用 $('#fm').form('disable', true); // 禁用
	 * $('#fm').form('disable', false); // 启用
	 * 
	 * @param jq
	 * @param isDisabled
	 *            是否禁用（禁用 true 启用 false）
	 */
    disable : function (jq, isDisabled) {

	    var formId = jq.attr ('id');
	    var attr = "disable";
	    var attr_r = true;
	    if (!isDisabled) {
		    attr = "enable";
		    attr_r = false;
	    }
	    $ ("form[id='" + formId + "'] :text").attr ("disabled", isDisabled);
	    $ ("form[id='" + formId + "'] textarea").attr ("disabled", isDisabled);
	    $ ("form[id='" + formId + "'] select").attr ("disabled", isDisabled);
	    $ ("form[id='" + formId + "'] :radio").attr ("disabled", isDisabled);
	    $ ("form[id='" + formId + "'] :checkbox").attr ("disabled", isDisabled);
	    $ ("form[id='" + formId + "'] :input").attr ("disabled", isDisabled);
	    // 禁用jquery easyui中的下拉选（使用input生成的combox）
	    $ ("#" + formId + " input[class='easyui-combobox combobox-f combo-f textbox-f']").each (function () {

		    if (this.id) {
			    $ ("#" + this.id).combobox (attr);
			    // $("#" + this.id).combobox('readonly', attr_r);
		    }
	    });
	    // 禁用jquery easyui中的下拉选（使用select生成的combox）
	    $ ("#" + formId + " select[class='easyui-combobox combobox-f combo-f textbox-f']").each (function () {

		    if (this.id) {
			    $ ("#" + this.id).combobox (attr);
			    // $("#" + this.id).combobox('readonly', attr_r);
		    }
	    });
	    // 禁用jquery easyui中的下拉选（使用input生成的combotree）
	    $ ("#" + formId + " input[class='easyui-combotree combotree-f combo-f']").each (function () {

		    if (this.id) {
			    // $("#" + this.id).combotree('readonly', attr_r);
			    $ ("#" + this.id).combotree (attr);
		    }
	    });
	    // 禁用jquery easyui中的日期组件dataBox
	    $ ("#" + formId + " input[class='datebox-f combo-f']").each (function () {

		    if (this.id) {
			    $ ("#" + this.id).datebox (attr);
			    // $("#" + this.id).datebox('readonly', attr_r);
		    }
	    });
	    $ ("form[id='" + formId + "'] :input[type=hidden]").attr ("disabled", false);
    }
});

/**
 * 扩展EasyUI tabs方法 addIframeTab方法的参数包含以下属性： 名称 参数类型 描述以及默认值 tab object
 * 该参数是对象，其属性列表同于tabs自带add方法的入参属性列表 iframe.src string 目标框架页面的地址，必填项
 * iframe.height string 框架标签iframe的高度，默认值为'100%' iframe.width string
 * 框架标签iframe的宽度，默认值为'100%' iframe.frameBorder number 框架标签iframe的边框宽度，默认值为0
 * iframe.message string 加载中效果显示的消息，默认值为'页面加载中...' which string/number
 * 更新方法updateIframeTab tab标题或索引号
 */
$
        .extend (
                $.fn.tabs.methods,
                {
                    /**
					 * 加载iframe内容
					 * 
					 * @param {jq
					 *            Object} jq [description]
					 * @param {Object}
					 *            params
					 *            params.which:tab的标题或者index;params.iframe:iframe的相关参数
					 * @return {jq Object} [description]
					 */
                    loadTabIframe : function (jq, params) {

	                    return jq
	                            .each (function () {

		                            var $tab = $ (this).tabs ('getTab', params.which);
		                            if ($tab == null)
			                            return;

		                            var $tabBody = $tab.panel ('body');

		                            // 销毁已有的iframe
		                            var $frame = $ ('iframe', $tabBody);
		                            if ($frame.length > 0) {
			                            $frame[0].contentWindow.document.write ('');
			                            $frame[0].contentWindow.close ();
			                            $frame.remove ();
			                            if ($.browser.msie) {
				                            CollectGarbage ();
			                            }
		                            }
		                            $tabBody.html ('');

		                            $tabBody.css ({
		                                'overflow' : 'hidden',
		                                'position' : 'relative'
		                            });
		                            var $mask = $ ('<div style="position:absolute;z-index:2;width:100%;height:100%;background:#ccc;z-index:1000;opacity:0.3;filter:alpha(opacity=30);"><div>')
		                                    .appendTo ($tabBody);
		                            var $maskMessage = $ (
		                                    '<div class="mask-message" style="z-index:3;width:auto;height:16px;line-height:16px;position:absolute;top:50%;left:50%;margin-top:-20px;margin-left:-92px;border:2px solid #d4d4d4;padding: 12px 5px 10px 30px;background: #ffffff url(\''
		                                            + 'scripts/jquery/easyui-1.3.3/themes/default/images/loading.gif\') no-repeat scroll 5px center;">'
		                                            + (params.iframe.message || '页面加载中...')
		                                            + '</div>').appendTo ($tabBody);
		                            var $containterMask = $ ('<div style="position:absolute;width:100%;height:100%;z-index:1;background:#fff;"></div>').appendTo ($tabBody);
		                            var $containter = $ ('<div style="position:absolute;width:100%;height:100%;z-index:0;"></div>').appendTo ($tabBody);
		                            //     
		                            var iframe = document.createElement ("iframe");
		                            iframe.src = params.iframe.src;
		                            iframe.frameBorder = params.iframe.frameBorder || 0;
		                            iframe.height = params.iframe.height || '100%';
		                            iframe.width = params.iframe.width || '100%';
		                            var finist = function () {

			                            $ ([ $mask[0], $maskMessage[0] ]).fadeOut (params.iframe.delay || 'slow', function () {

				                            $ (this).remove ();
				                            if ($ (this).hasClass ('mask-message')) {
					                            $containterMask.fadeOut (params.iframe.delay || 'slow', function () {

						                            $ (this).remove ();
					                            });
				                            }
			                            });
		                            }
		                            if (iframe.attachEvent) {
			                            iframe.attachEvent ("onload", finist);
		                            } else {
			                            iframe.onload = finist;
		                            }
		                            $containter[0].appendChild (iframe);
	                            });
                    },
                    /**
					 * 增加iframe模式的标签页
					 * 
					 * @param {[type]}
					 *            jq [description]
					 * @param {[type]}
					 *            params [description]
					 */
                    addIframeTab : function (jq, params) {

	                    return jq.each (function () {

		                    if (params.tab.href) {
			                    delete params.tab.href;
		                    }
		                    $ (this).tabs ('add', params.tab);
		                    $ (this).tabs ('loadTabIframe', {
		                        'which' : params.tab.title,
		                        'iframe' : params.iframe
		                    });
	                    });
                    },
                    /**
					 * 更新tab的iframe内容
					 * 
					 * @param {jq
					 *            Object} jq
					 * @param {Object}
					 *            params which:tab标题或索引号
					 * @return {jq Object}
					 */
                    updateIframeTab : function (jq, params) {

	                    return jq.each (function () {

		                    params.iframe = params.iframe || {};
		                    if (!params.iframe.src) {
			                    var $tab = $ (this).tabs ('getTab', params.which);
			                    if ($tab == null)
				                    return;
			                    var $tabBody = $tab.panel ('body');
			                    var $iframe = $tabBody.find ('iframe');
			                    if ($iframe.length === 0)
				                    return;
			                    $.extend (params.iframe, {
				                    'src' : $iframe.attr ('src')
			                    });
		                    }
		                    $ (this).tabs ('loadTabIframe', params);
	                    });
                    }
                });

/**
 * 使panel和datagrid在加载时提示
 * 
 * @author 尔演@Eryan eryanwcp@gmail.com
 * @requires jQuery,EasyUI
 */
$.fn.panel.defaults.loadingMessage = '加载中....';
$.fn.datagrid.defaults.loadMsg = '加载中....';
/**
 * @requires jQuery,EasyUI 避免验证tip屏幕跑偏
 */
var removeEasyuiTipFunction = function () {

	window.setTimeout (function () {

		$ ('div.validatebox-tip').remove ();
	}, 0);
};
$.fn.panel.defaults.onClose = removeEasyuiTipFunction;
$.fn.window.defaults.onClose = removeEasyuiTipFunction;
$.fn.dialog.defaults.onClose = removeEasyuiTipFunction;

/**
 * @requires jQuery,EasyUI 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
 */
var createGridHeaderContextMenu = function (e, field) {

	e.preventDefault ();
	var grid = $ (this);/* grid本身 */
	var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
	if (!headerContextMenu) {
		var tmenu = $ ('<div style="width:100px;"></div>').appendTo ('body');
		var fields = grid.datagrid ('getColumnFields');
		for (var i = 0; i < fields.length; i++) {
			var fildOption = grid.datagrid ('getColumnOption', fields[i]);
			var title = fildOption.title;
			var field = fildOption.field;
			if (field == 'ck') {
				title = "全选";
			}
			if (!fildOption.hidden) {
				$ ('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html (title).appendTo (tmenu);
			} else {
				$ ('<div iconCls="icon-empty" field="' + fields[i] + '"/>').html (title).appendTo (tmenu);
			}
		}
		headerContextMenu = this.headerContextMenu = tmenu.menu ({
			onClick : function (item) {

				var field = $ (item.target).attr ('field');
				if (item.iconCls == 'icon-ok') {
					if (fields.length > 1) {
						grid.datagrid ('hideColumn', field);
						$ (this).menu ('setIcon', {
						    target : item.target,
						    iconCls : 'icon-empty'
						});
					}

				} else {
					grid.datagrid ('showColumn', field);
					$ (this).menu ('setIcon', {
					    target : item.target,
					    iconCls : 'icon-ok'
					});
				}
			}
		});
	}
	headerContextMenu.menu ('show', {
	    left : e.pageX,
	    top : e.pageY
	});
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;

/**
 * @requires jQuery,EasyUI 防止panel/window\dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function (left, top) {

	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt ($ (this).parent ().css ('width')) + 14;
	var height = parseInt ($ (this).parent ().css ('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $ (document).width ();
	var browserHeight = $ (document).height ();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$ (this).parent ().css ({/* 修正面板位置 */
	    left : l,
	    top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;

/**
 * @author 尔演@Eryan eryanwcp@gmail.com
 * @requires jQuery,EasyUI panel关闭时回收内存，主要用于layout使用iframe嵌入网页时的内存泄漏问题
 */
$.fn.panel.defaults.onBeforeDestroy = function () {

	var frame = $ ('iframe', this);
	try {
		if (frame.length > 0) {
			frame[0].contentWindow.document.write ('');
			frame[0].contentWindow.close ();
			frame.remove ();
			if ($.browser != undefined)
				if ($.browser.msie) {
					CollectGarbage ();
				}
		}
	} catch (e) {
	}
};

/**
 * @requires jQuery,EasyUI 扩展datagrid，添加动态增加或删除Editor的方法,提示消息、取消提示消息方法
 *           例子如下，第二个参数可以是数组 datagrid.datagrid('removeEditor', 'cpwd');
 *           datagrid.datagrid('addEditor', [ { field : 'ccreatedatetime',
 *           editor : { type : 'datetimebox', options : { editable : false } } }, {
 *           field : 'cmodifydatetime', editor : { type : 'datetimebox', options : {
 *           editable : false } } } ]);
 */
$.extend ($.fn.datagrid.methods, {
    addEditor : function (jq, param) {

	    if (param instanceof Array) {
		    $.each (param, function (index, item) {

			    var e = $ (jq).datagrid ('getColumnOption', item.field);
			    e.editor = item.editor;
		    });
	    } else {
		    var e = $ (jq).datagrid ('getColumnOption', param.field);
		    e.editor = param.editor;
	    }
    },
    removeEditor : function (jq, param) {

	    if (param instanceof Array) {
		    $.each (param, function (index, item) {

			    var e = $ (jq).datagrid ('getColumnOption', item);
			    e.editor = {};
		    });
	    } else {
		    var e = $ (jq).datagrid ('getColumnOption', param);
		    e.editor = {};
	    }
    },
    /**
     * 扩展easyUI的datagrid的方法
     * 将datagrid的数据转换为url参数字符串
     * 用法:
     * 	$('#dataGridId').data('serialize','abc');
     * 返回值
     * 	abc[0].parm1=XXX&abc[0].parm2=XXX&abc[1].parm1=XXX&abc[1].parm2=XXX
     */
    serialize : function (jq, param) {
		var oGrid = $ (jq);
		if (!param)
			param = oGrid.attr ('name');
		if (!param) {
			alert ('DataGrid的serialize方法参数不完整');
			return null;
		}
		var returnStr = "";
		var rows = oGrid.datagrid ('getData').rows;
		for ( var index in rows)
			for ( var p in rows[index])
				if(rows[index][p] == null || rows[index][p] == undefined)
					returnStr += (param + '[' + index + '].' + p + '=' + '' + '&');
				else
					returnStr += (param + '[' + index + '].' + p + '=' + rows[index][p] + '&');
		returnStr = returnStr.substring (0, returnStr.length - 1);
		return returnStr;
	}
});

// datagrid扩展
//$.extend ($.fn.datagrid.methods, {
//	serialize : function (jq, param) {
//		var oGrid = $ (jq);
//		if (!param)
//			param = oGrid.attr ('name');
//		if (!param) {
//			alert ('serializeUrlStr方法参数不完整');
//			return null;
//		}
//		var returnStr = "";
//		var rows = oGrid.datagrid ('getData').rows;
//		for ( var index in rows)
//			for ( var p in rows[index])
//				returnStr += (param + '[' + index + '].' + p + '=' + rows[index][p] + '&');
//		returnStr = returnStr.substring (0, returnStr.length - 1);
//		return returnStr;
//	}
//});
/**
 * 合并单元格扩展
 */
var gridautoMergeCellsOptions = {
	autoMergeCells : function (jq, fields) {

		return jq.each (function () {

			var target = $ (this);
			if (!fields) {
				fields = target.datagrid ("getColumnFields");
			}
			var rows = target.datagrid ("getRows");
			var i = 0, j = 0, temp = {};
			for (i; i < rows.length; i++) {
				var row = rows[i];
				j = 0;
				for (j; j < fields.length; j++) {
					var field = fields[j];
					var tf = temp[field];
					if (!tf) {
						tf = temp[field] = {};
						tf[row[field]] = [ i ];
					} else {
						var tfv = tf[row[field]];
						if (tfv) {
							tfv.push (i);
						} else {
							tfv = tf[row[field]] = [ i ];
						}
					}
				}
			}
			$.each (temp, function (field, colunm) {

				$.each (colunm, function () {

					var group = this;

					if (group.length > 1) {
						var before, after, megerIndex = group[0];
						for (var i = 0; i < group.length; i++) {
							before = group[i];
							after = group[i + 1];
							if (after && (after - before) == 1) {
								continue;
							}
							var rowspan = before - megerIndex + 1;
							if (rowspan > 1) {
								target.datagrid ('mergeCells', {
								    index : megerIndex,
								    field : field,
								    rowspan : rowspan
								});
							}
							if (after && (after - before) != 1) {
								megerIndex = after;
							}
						}
					}
				});
			});
		});
	}
}
/**
 * grid tooltip参数. onlyShowNowrap string
 * 是否只有在文字被截断时才显示tip，默认值为false，即所有单元格都显示tip。 position string
 * tip的位置，默认值为top,可以为top,botom,right,left。 tipStyler object tip内容的样式，注意要符合jquery
 * css函数的要求。 contentStyler object 整个tip的样式，注意要符合jquery css函数的要求。 fields object
 * 要显示的字段 (如果传递了该参数，则参数onlyShowNowrap无效)
 */
var gridTooltipOptions = {
    showTooltip : function (jq, params) {

	    if (!params) {
		    params = {};
	    }
	    function showTip (showParams, td, e, dg) {

		    // 无文本，不提示。
		    if ($ (td).text () == "")
			    return;

		    var options = dg.data ('datagrid');
		    showParams.content = '<div class="tipcontent">' + showParams.content + '</div>';
		    $ (td).tooltip ({
		        content : showParams.content,
		        trackMouse : true,
		        position : params.position,
		        onHide : function () {

			        $ (this).tooltip ('destroy');
		        },
		        onUpdate : function (p) {

			        var tip = $ (this).tooltip ('tip');
			        if (parseInt (tip.css ('width')) > 500) {
				        tip.css ('width', 500);
			        }
		        },
		        onShow : function () {

			        var tip = $ (this).tooltip ('tip');
			        if (showParams.tipStyler) {
				        tip.css (showParams.tipStyler);
			        }
			        if (showParams.contentStyler) {
				        tip.find ('div.tipcontent').css (showParams.contentStyler);
			        }
			        if (showParams.attachToMouse) {
				        fixPosition (tip, params.position, options);
			        }
		        }
		    }).tooltip ('show');

	    }
	    ;
	    function bindEvent (delegateEle, td, grid) {

		    var options = grid.data ('datagrid');
		    $ (delegateEle).undelegate (td, 'mouseover').undelegate (td, 'mouseout').undelegate (td, 'mousemove').delegate (td, {
		        'mouseover' : function (e) {

			        if ($ (this).attr ('field') === undefined)
				        return;
			        options.factContent = $ (this).find ('>div').clone ().css ({
			            'margin-left' : '-5000px',
			            'width' : 'auto',
			            'display' : 'inline',
			            'position' : 'absolute'
			        }).appendTo ('body');
			        var factContentWidth = options.factContent.width ();
			        params.content = $ (this).find ('>div').clone ().css ({
				        'width' : 'auto'
			        }).html ();
			        if (params.onlyShowInterrupt && params.fields === undefined) {
				        if (factContentWidth > $ (this).width ()) {
					        showTip (params, this, e, grid);
				        }
			        } else {
				        showTip (params, this, e, grid);
			        }
		        },
		        'mouseout' : function (e) {

			        if (options.factContent) {
				        options.factContent.remove ();
				        options.factContent = null;
			        }
		        }
		    });
	    }
	    ;
	    return jq.each (function () {

		    var grid = $ (this);
		    var options = $ (this).data ('datagrid');
		    if (!options.tooltip) {
			    var panel = grid.datagrid ('getPanel').panel ('panel');
			    panel.find ('.datagrid-body').each (function () {

				    var delegateEle = $ (this).find ('> div.datagrid-body-inner').length ? $ (this).find ('> div.datagrid-body-inner')[0] : this;
				    if (params.fields && typeof params.fields == 'object') {
					    $.each (params.fields, function () {

						    var field = this;
						    bindEvent (delegateEle, 'td[field=' + field + ']', grid);
					    });
				    } else {
					    bindEvent (delegateEle, 'td', grid);
				    }
			    });
		    }
	    });
    },
    /**
	 * 关闭消息提示功能
	 * 
	 * @param {}
	 *            jq
	 * @return {}
	 */
    closeTooltip : function (jq) {

	    return jq.each (function () {

		    var data = $ (this).data ('datagrid');
		    if (data.factContent) {
			    data.factContent.remove ();
			    data.factContent = null;
		    }
		    var panel = $ (this).datagrid ('getPanel').panel ('panel');
		    panel.find ('.datagrid-body').undelegate ('td', 'mouseover').undelegate ('td', 'mouseout').undelegate ('td', 'mousemove')
	    });
    }
};
/**
 * Datagrid扩展方法tooltip 基于Easyui 1.3.3，可用于Easyui1.3.3+
 */
$.extend ($.fn.datagrid.methods, gridTooltipOptions, gridautoMergeCellsOptions);

/**
 * Treegrid扩展方法tooltip 基于Easyui 1.3.3，可用于Easyui1.3.3+
 */
$.extend ($.fn.treegrid.methods, gridTooltipOptions);

/**
 * @requires jQuery,EasyUI 扩展tree，使其支持平滑数据格式
 */
$.fn.tree.defaults.loadFilter = function (data, parent) {

	var opt = $ (this).data ().tree.options;
	var idFiled, textFiled, parentField;
	// if (opt.parentField) {
	idFiled = opt.idFiled || 'id';
	textFiled = opt.textFiled || 'text';
	parentField = opt.parentField || 'pid';
	var i, l, treeData = [], tmpMap = [];
	for (i = 0, l = data.length; i < l; i++) {
		tmpMap[data[i][idFiled]] = data[i];
	}
	for (i = 0, l = data.length; i < l; i++) {
		if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
			if (!tmpMap[data[i][parentField]]['children'])
				tmpMap[data[i][parentField]]['children'] = [];
			data[i]['text'] = data[i][textFiled];
			tmpMap[data[i][parentField]]['children'].push (data[i]);
		} else {
			data[i]['text'] = data[i][textFiled];
			treeData.push (data[i]);
		}
	}
	return treeData;
	// }
	// return data;
};

/**
 * @requires jQuery,EasyUI 扩展treegrid，使其支持平滑数据格式
 */
$.fn.treegrid.defaults.loadFilter = function (data, parentId) {

	var opt = $ (this).data ().treegrid.options;
	var idFiled, textFiled, parentField;
	// if (opt.parentField) {
	idFiled = opt.idFiled || 'id';
	textFiled = opt.textFiled || 'text';
	parentField = opt.parentField || 'pid';
	var i, l, treeData = [], tmpMap = [];
	for (i = 0, l = data.length; i < l; i++) {
		tmpMap[data[i][idFiled]] = data[i];
	}
	for (i = 0, l = data.length; i < l; i++) {
		if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
			if (!tmpMap[data[i][parentField]]['children'])
				tmpMap[data[i][parentField]]['children'] = [];
			data[i]['text'] = data[i][textFiled];
			tmpMap[data[i][parentField]]['children'].push (data[i]);
		} else {
			data[i]['text'] = data[i][textFiled];
			treeData.push (data[i]);
		}
	}
	return treeData;
	// }
	// return data;
};

/**
 * @requires jQuery,EasyUI 扩展combotree，使其支持平滑数据格式
 */
$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;

/**
 * @requires jQuery,EasyUI 创建一个模式化的dialog
 * @returns $.modalDialog.handler 这个handler代表弹出的dialog句柄
 * @returns $.modalDialog.xxx 这个xxx是可以自己定义名称，主要用在弹窗关闭时，刷新某些对象的操作，可以将xxx这个对象预定义好
 */
$.modalDialog = function (options) {

	var opts = $.extend ({
	    title : '',
	    width : 840,
	    height : 680,
	    modal : true,
	    onClose : function () {

		    $ (this).dialog ('destroy');
	    }
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	return $.modalDialog.handler = $ ('<div/>').dialog (opts);
};

/**
 * @requires jQuery,EasyUI 扩展datagrid的editor 增加带复选框的下拉树 增加日期时间组件editor
 *           增加多选combobox组件
 */
$.extend ($.fn.datagrid.defaults.editors, {
    combogrid : {
        init : function (container, options) {

	        var input = $ ('<input type="text" class="datagrid-editable-input">').appendTo (container);
	        input.combogrid (options);
	        return input;
        },
        destroy : function (target) {

	        $ (target).combogrid ('destroy');
        },
        getValue : function (target) {

	        return $ (target).combogrid ('getValue');
        },
        setValue : function (target, value) {

	        $ (target).combogrid ('setValue', value);
        },
        resize : function (target, width) {

	        $ (target).combogrid ('resize', width);
        }
    },
    combocheckboxtree : {
        init : function (container, options) {

	        var editor = $ ('<input />').appendTo (container);
	        options.multiple = true;
	        editor.combotree (options);
	        return editor;
        },
        destroy : function (target) {

	        $ (target).combotree ('destroy');
        },
        getValue : function (target) {

	        return $ (target).combotree ('getValues').join (',');
        },
        setValue : function (target, value) {

	        $ (target).combotree ('setValues', $.getList (value));
        },
        resize : function (target, width) {

	        $ (target).combotree ('resize', width);
        }
    },
    datetimebox : {
        init : function (container, options) {

	        var input = $ ('<input type="text"  class="datagrid-editable-input" onfocus=\'WdatePicker({dateFmt:"yyyy-MM-dd HH:mm"})\' oncleared="" >').appendTo (container);

	        if (options != undefined)
		        if (options.required != undefined && options.required) {
			        input.validatebox ({
				        required : true
			        });
		        }
	        return input;
        },
        getValue : function (target) {

	        return $ (target).val ();
        },
        setValue : function (target, value) {

	        $ (target).val (value);
        },
        resize : function (target, width) {

	        var input = $ (target);
	        if ($.boxModel == true) {
		        input.width (width - (input.outerWidth () - input.width ()));
	        } else {
		        input.width (width);
	        }
        }
    },
    datebox : {
        init : function (container, options) {

	        var input = $ ('<input type="text"  class="datagrid-editable-input" onfocus=\'WdatePicker({dateFmt:"yyyy-MM-dd"})\' oncleared="" >').appendTo (container);

	        if (options != undefined)
		        if (options.required != undefined && options.required) {
			        input.validatebox ({
				        required : true
			        });
		        }
	        return input;
        },
        getValue : function (target) {

	        return $ (target).val ();
        },
        setValue : function (target, value) {

	        $ (target).val (value);
        },
        resize : function (target, width) {

	        var input = $ (target);
	        if ($.boxModel == true) {
		        input.width (width - (input.outerWidth () - input.width ()));
	        } else {
		        input.width (width);
	        }
        }
    },
    multiplecombobox : {
        init : function (container, options) {

	        var editor = $ ('<input />').appendTo (container);
	        options.multiple = true;
	        editor.combobox (options);
	        return editor;
        },
        destroy : function (target) {

	        $ (target).combobox ('destroy');
        },
        getValue : function (target) {

	        return $ (target).combobox ('getValues').join (',');
        },
        setValue : function (target, value) {

	        $ (target).combobox ('setValues', $.getList (value));
        },
        resize : function (target, width) {

	        $ (target).combobox ('resize', width);
        }
    },
    numberspinner : {
        init : function (container, options) {

	        var input = $ ('<input type="text">').appendTo (container);
	        return input.numberspinner (options);
        },
        destroy : function (target) {

	        $ (target).numberspinner ('destroy');
        },
        getValue : function (target) {

	        return $ (target).numberspinner ('getValue');
        },
        setValue : function (target, value) {

	        $ (target).numberspinner ('setValue', value);
        },
        resize : function (target, width) {

	        $ (target).numberspinner ('resize', width);
        }
    },
    password : {
        init : function (container, options) {

	        var input = $ ('<input class="datagrid-editable-input" type="password"/>').appendTo (container);

	        if (!$.fn.validatebox.defaults.rules.safepass) {
		        $.extend ($.fn.validatebox.defaults.rules, {
			        safepass : {
			            validator : function (value, param) {

				            return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test (value));
			            },
			            message : '密码由字母和数字组成，至少6位'
			        }
		        });
	        }

	        input.validatebox (options);
	        return input;
        },
        getValue : function (target) {

	        alert ($ (target).val ());
	        alert (target.value);
	        return $ (target).val ();
        },
        setValue : function (target, value) {

	        $ (target).val (value);
        },
        resize : function (target, width) {

	        var input = $ (target);
	        if ($.boxModel == true) {
		        input.width (width - (input.outerWidth () - input.width ()));
	        } else {
		        input.width (width);
	        }
        }
    }
});

/**
 * @requires jQuery,EasyUI 扩展datagrid的datebox、datetimebox格式化
 */
$.fn.datebox.defaults.formatter = function (date) {

	var vDate = new Date (date);
	return vDate.format ('yyyy-MM-dd');
}
$.fn.datebox.defaults.parser = function (s) {

	if (!s)
		return new Date ();
	var ss = new Date (s).format ('yyyy-MM-dd').split ('-');
	var y = parseInt (ss[0], 10);
	var m = parseInt (ss[1], 10);
	var d = parseInt (ss[2], 10);
	if (!isNaN (y) && !isNaN (m) && !isNaN (d)) {
		return new Date (y, m - 1, d);
	} else {
		return new Date ();
	}
}
$.fn.datetimebox.defaults.formatter = function (date) {

	var vDate = new Date (date);
	return vDate.format ('yyyy-MM-dd hh:mm:ss');
}
$.fn.datetimebox.defaults.parser = function (s) {

	if (!s)
		return new Date ();
	var ss = new Date (s).format ('yyyy-MM-dd hh:mm:ss').split ('-');
	var y = parseInt (ss[0], 10);
	var m = parseInt (ss[1], 10);
	var d = parseInt (ss[2], 10);
	if (!isNaN (y) && !isNaN (m) && !isNaN (d)) {
		return new Date (y, m - 1, d);
	} else {
		return new Date ();
	}
}
 

/**
 * @requires jQuery,EasyUI EasyUI组件加载数据通用错误提示
 *           用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 */
var easyuiErrorFunction = function (XMLHttpRequest) {

	$.messager.progress ('close');
	var data = XMLHttpRequest.responseText;
	if (!data) {
		$.messager.alert ('服务器无响应.');
	} else {
		$.messager.alert (data);
	}
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;

/**
 * @requires jQuery 改变jQuery的AJAX默认属性和方法
 */
//$.ajaxSetup ({
//    type : 'POST',
//    error : function (XMLHttpRequest, textStatus, errorThrown) {
//
//	    $.messager.progress ('close');
//	    if (!data) {
//		    // eu.notyError('服务器无响应.');
//		    $.messager.alert ('提示信息！', "服务器无响应", 'error');
//	    } else {
//		    // eu.notyError(data);
//		    $.messager.alert ('提示信息！', "系统错误", 'error');
//	    }
//    }
//});
