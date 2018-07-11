/**
 * 扩展form表单对象的方法
 * 将表单转换为一个对象,表单中字段转换为对象的属性-
 * 表单各个输入框的值转换为对象的属性值
 * 
 * 用法:
 * 	$('#formId').serializeObj();
 * 返回:
 * 	{parm1:XXX,parm2:XXX,parm3:XXX}
 */
$.fn.serializeObj = function(){
	var o = {};
	$.each($(this).serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
}