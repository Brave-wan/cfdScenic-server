/**
 * 
 */
(function ($, K) {
    if (!K)
        throw "KindEditor未定义!";

    function create(target) {
//    	console.info($(target).text());
//    	  $(target).text(htmlDecodeByRegExp($(target).text()));
        var opts = $.data(target, 'kindeditor').options;
        var editor = K.create(target, opts);
        $.data(target, 'kindeditor').options.editor = editor;
        if($(target).attr('disabled')=='disabled'){
//        	editor.hideMenu();
        	editor.readonly(true);
        }
      
    }

    $.fn.kindeditor = function (options, param) {
        if (typeof options == 'string') {
            var method = $.fn.kindeditor.methods[options];
            if (method) {
                return method(this, param);
            }
        }
        options = options || {};
        return this.each(function () {
            var state = $.data(this, 'kindeditor');
            if (state) {
                $.extend(state.options, options);
            } else {
                state = $.data(this, 'kindeditor', {
                    options: $.extend({}, $.fn.kindeditor.defaults, $.fn.kindeditor.parseOptions(this), options)
                });
            }
            create(this);
        });
    }

    $.fn.kindeditor.parseOptions = function (target) {
        return $.extend({}, $.parser.parseOptions(target, []));
    };

    $.fn.kindeditor.methods = {
        editor: function (jq) {
            return $.data(jq[0], 'kindeditor').options.editor;
        }
    };

    $.fn.kindeditor.defaults = {
		cssPath : 'scripts/kindeditor/themes/default/default.css',
		uploadJson : 'scripts/kindeditor/jsp/upload_json.jsp',
		fileManagerJson : 'scripts/kindeditor/jsp/file_manager_json.jsp',
		allowFileManager : true,
        allowPreviewEmoticons: false,
        afterChange: function () {
            this.sync();
        },
        afterBlur: function () { this.sync(); }
    };
    $.parser.plugins.push("kindeditor");
})(jQuery, KindEditor);