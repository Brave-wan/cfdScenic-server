/**
 * 扩展jquery的方法
 * 将时间毫秒数格式的时间转换为-
 * 日期格式(yyyy-mm-dd)或者日期时间格式(yyyy-mm-dd hh:mi:ss)
 * 
 * 用法:
 * 	formatDate(140234987000);
 * 	formatDateTime(140234987000);
 * 返回:
 * 	YYYY-MM-DD hh:mi:ss 格式字符串
 * 
 * 另:
 * 	该方法还可以继续扩展:执行转换成的时间格式,如:yyyy年mm月dd日
 */
var formatDate = function(dateMiSecond){
	if(!dateMiSecond){
		alert('formatDate方法参数不完整');
		return null;
	}
	var datetime = new Date(dateMiSecond);
	var year = datetime.getFullYear();
	var month = datetime.getMonth()+1;
	var day = datetime.getDate();
	
	var dataStr = year+'-'+((month < 10)?'0'+month:month)+'-'+((day < 10)?'0'+day:day);
	return dataStr;
}
	
var formatDateTime = function(dateTimeMiSecond){
	if(!dateTimeMiSecond){
		alert('formatDateTime方法参数不完整');
		return null;
	}
	var datetime = new Date(dateTimeMiSecond);
	var year = datetime.getFullYear();
	var month = datetime.getMonth()+1;
	var day = datetime.getDate();
	var hour = datetime.getHours();
	var minute = datetime.getMinutes();
	var second = datetime.getSeconds();
	
	var dateTimeStr = year+'-'+((month < 10)?'0'+month:month)+'-'+((day < 10)?'0'+day:day)+' '+((hour<10)?'0'+hour:hour)+':'+((minute<10)?'0'+minute:minute)+':'+((second<10)?'0'+second:second);
	return dateTimeStr;
}