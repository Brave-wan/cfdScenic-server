/**
 * print
 */

function pirntHtml(url){
	var ww = $(window).width(), // 浏览器宽
	wh = $(window).height();// 浏览器高
	var wwh = wh * 0.85;
	var wwt = ww * 0.90;
	art.dialog.open(url, {title : '打印',
	    ok: function () {
	    	var iframe = this.iframe.contentWindow;
	    	if (!iframe.document.body) {
	        	alert('还没加载完毕呢')
	        	return false;
	        };
	        iframe.print();
	    },
	    okVal:'打印',width : wwt,height : wwh,left:70,top:10});
}