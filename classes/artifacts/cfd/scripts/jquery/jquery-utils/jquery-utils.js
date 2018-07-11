/*******************************************************************************
 * author 董少恒
 */
 
function stateToString(intState) {
	if (intState == null || intState == '')
		return '';
	if (intState == -1)
		return "否";
	if (intState == 1)
		return "是";

}
function stateToString2(intState) {
	if (intState == null || intState == '')
		return '';
	if (intState == -1)
		return "×";
	if (intState == 1)
		return "√";

}
function stateToString3(intState) {
	if (intState == null || intState == '')
		return '--';
	if (intState == 0)
		return "否";
	if (intState == 1)
		return "是";

}
function stateToAudit(intState) {
	if (intState == null || intState == '')
		return '';
	if (intState == -1)
		return "审核未通过";
	if (intState == 0)
		return "已保存";
	if (intState == 1)
		return "待审核";
	if (intState == 2)
		return "已阅";
	if (intState == 3)
		return "审核通过";
}
function numToFixed0(num) {
	var number = new Number(num);
	return number.toFixed(0);
}

function numToFixed2(num) {
	var number = new Number(num);
	return number.toFixed(2);
}
function numToFixed4(num) {
	var number = new Number(num);
	return number.toFixed(4);
}
function formatSiState(value,row,index){
	if(value==5){
		return '已发布，正在报名中';
	}else if(value==6){
		return '停止报名';
	}else if(value==10){
		return '项目结束';
	}else if(value==9){
		return '竞价结束';
	}else{
		return '进行中';
	}
}

function formatAgainstate(value,row,index){
	if(value==0)
		return '否';
	if(value==1)
		return '是';
}
/*******************************************************************************
 * 重写JS format
 * 
 * @param format
 * @returns
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	};

	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};

/*******************************************************************************
 * 按百分比返回当前页面像素宽度
 * 
 * @param percent
 * @returns {Number}
 */
function fixWidth(percent) {
	return document.body.clientWidth / 100 * percent;
}

/**
 * Add "put delete" method to jQuery
 * 
 * @author Hejue
 */
jQuery.each([ "put", "del" ], function(i, method) {
	jQuery[method] = function(url, data, callback, type) {
		// shift arguments if data argument was omitted
		if (jQuery.isFunction(data)) {
			type = type || callback;
			callback = data;
			data = {};
		}

		var _method = (method == "del") ? "delete" : method;
		(typeof data == "object") ? (data._method = _method)
				: (data += "&_method=" + _method);

		return jQuery.ajax({
			type : "post",
			url : url,
			data : data,
			success : callback,
			dataType : type
		});
	};
});

function SerializeJsonToStr(oJson) {
	if (oJson == null)
		return "null";
	if (typeof (oJson) == typeof (0))
		return oJson.toString();
	if (typeof (oJson) == typeof ('') || oJson instanceof String) {
		oJson = oJson.toString();
		oJson = oJson.replace('//r/n/', '//r//n');
		oJson = oJson.replace('//n/', '//n');
		oJson = oJson.replace('//"/', '//"');
		return '"' + oJson + '"';
	}
	if (oJson instanceof Array) {
		var strRet = "[";
		for (var i = 0; i < oJson.length; i++) {
			if (strRet.length > 1)
				strRet += ",";
			strRet += SerializeJsonToStr(oJson[i]);
		}
		strRet += "]";
		return strRet;
	}
	if (typeof (oJson) == typeof ({})) {
		var strRet = "{";
		for ( var p in oJson) {
			if (strRet.length > 1)
				strRet += ",";
			strRet += p.toString() + ':' + SerializeJsonToStr(oJson[p]);
		}
		strRet += "}";
		return strRet;
	}
}
// FROM表单序列化转对象
function serializeObject(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
}
//function paramString2obj(serializedParams) {
//	var obj = {};
//	function evalThem(str) {
//		var attributeName = str.split("=")[0];
//		var attributeValue = str.split("=")[1];
//		if (!attributeValue) {
//			return;
//		}
//
//		var array = attributeName.split(".");
//		for (var i = 1; i < array.length; i++) {
//			var tmpArray = Array();
//			tmpArray.push("obj");
//			for (var j = 0; j < i; j++) {
//				tmpArray.push(array[j]);
//			};
//			var evalString = tmpArray.join(".");
//			// alert(evalString);
//			if (!eval(evalString)) {
//				eval(evalString + "={};");
//			}
//		};
//		eval("obj." + attributeName + "='" + attributeValue + "';");
//
//	};
//	var properties = serializedParams.split("&");
//	for (var i = 0; i < properties.length; i++) {
//		evalThem(properties[i]);
//	};
//	return obj;
//}

function strToObj(str){    
    str = str.replace(/&/g,"','");    
    str = str.replace(/=/g,"':'");    
    str = "({'"+str +"'})";    
    obj = eval(str);     
    return obj;    
}  
 function serializeComplexObject(param, key){
    var paramStr="";
    if(param instanceof String||param instanceof Number||param instanceof Boolean){
    	paramStr+="&"+key+"="+param;
    }else{
        $.each(param,function(i,element){
            var k=key==null?i:key+(param instanceof Array?"["+i+"]":"."+i);
            if(element != null && element != '' )
            	paramStr+='&'+serializeComplexObject(this, k);
        });
    }
    return paramStr.substr(1);
}; 


//idcard为身份证ID，age为年龄ID，age为easyui-validatebox readonly
//身份证计算年龄
function resultage(idcard,ageid) {
	//获取输入身份证号码
	var bbsrcaard = $("#"+idcard).val();
	
	if(bbsrcaard == null || bbsrcaard == "" ){
		 $("#"+ageid).val('');
		 return;
	}
	if(bbsrcaard.length!=15&&bbsrcaard.length!=18){
		 $("#"+ageid).val('');
		 return;
	}
	//获取出生日期
	bbsrcaard.substring(6, 10) + "-" + bbsrcaard.substring(10, 12) + "-" + bbsrcaard.substring(12, 14);
	//获取年龄
	var myDate = new Date();
	var month = myDate.getMonth() + 1;
	var day = myDate.getDate();

	var age = myDate.getFullYear() - bbsrcaard.substring(6, 10) - 1;
	if (bbsrcaard.substring(10, 12) < month || bbsrcaard.substring(10, 12) == month && bbsrcaard.substring(12, 14) <= day)
	{
	age++;
	}
	 $("#"+ageid).val(age);
	//年龄 age
};

var isJson = function(obj){
    var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;
    return isjson;
}

 
String.prototype.Trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.LTrim = function() {
	return this.replace(/(^\s*)/g, "");
}
String.prototype.RTrim = function() {
	return this.replace(/(\s*$)/g, "");
}
function getRootPath(){
	//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
//    projectName = "/rtw_mytopcake";
    return (localhostPaht+projectName);
}

/*3.用正则表达式实现html转码*/
function htmlEncodeByRegExp (str){  
     var s = "";
     if(str.length == 0) return "";
     s = str.replace(/&/g,"&amp;");
     s = s.replace(/</g,"&lt;");
     s = s.replace(/>/g,"&gt;");
     s = s.replace(/ /g,"&nbsp;");
     s = s.replace(/\'/g,"&#39;");
     s = s.replace(/\"/g,"&quot;");
     return s;  
}
/*4.用正则表达式实现html解码*/
function htmlDecodeByRegExp (str){  
     var s = "";
     if(str.length == 0) return "";
     s = str.replace(/&amp;/g,"&");
     s = s.replace(/&lt;/g,"<");
     s = s.replace(/&gt;/g,">");
     s = s.replace(/&nbsp;/g," ");
     s = s.replace(/&#39;/g,"\'");
     s = s.replace(/&quot;/g,"\"");
     return s;  
}

//人民币金额转大写程序 JavaScript版     
//CopyRight Bardo QI     
  
function numToCny(n,p){
	if(n == null || n =='' || n==undefined || isNaN(n)){
		n=0;
	}
	if(p == null || p ==''){
		p=1;
	}
	console.info(n);
	console.info(p);
	 var fraction = ['角', '分'];  
     var digit = [  
         '零', '壹', '贰', '叁', '肆',  
         '伍', '陆', '柒', '捌', '玖'  
     ];  
     var unit = [  
         ['元', '万', '亿'],  
         ['', '拾', '佰', '仟']  
     ];  
     var head = n < 0 ? '欠' : '';  

     p = parseInt(p);
     n= n*p;
     n = Math.abs(n); 
     
     var s = '';  
     for (var i = 0; i < fraction.length; i++) {  
         s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');  
     }  
     s = s || '整';  
     n = Math.floor(n);  
     for (var i = 0; i < unit[0].length && n > 0; i++) {  
         var p = '';  
         for (var j = 0; j < unit[1].length && n > 0; j++) {  
             p = digit[n % 10] + unit[1][j] + p;  
             n = Math.floor(n / 10);  
         }  
         s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;  
     }  
     return head + s.replace(/(零.)*零元/, '元')  
         .replace(/(零.)+/g, '零')  
         .replace(/^整$/, '零元整');  
}   