var mainModel = {
	init : function() {
		
		
	},
	treeClick : function(node) {
		if (node.state=="open"&&node.children.length==0){
			$('#mainiframe').attr('src',node.attributes.url);
//			eu.addTab('centerTabs', node.text, node.attributes.url, true, '');
		}
	},
	tabsinit:function(){
		
		 //关闭当前标签页
	    $("#closecur").bind("click",function(){
	        var tab = $('#centerTabs').tabs('getSelected');
	        var index = $('#centerTabs').tabs('getTabIndex',tab);
	        $('#centerTabs').tabs('close',index);
	    });
	    //关闭所有标签页
	    $("#closeall").bind("click",function(){
	        var tablist = $('#centerTabs').tabs('tabs');
	        for(var i=tablist.length-1;i>=0;i--){
	            $('#centerTabs').tabs('close',i);
	        }
	    });
	    //关闭非当前标签页（先关闭右侧，再关闭左侧）
	    $("#closeother").bind("click",function(){
	        var tablist = $('#centerTabs').tabs('tabs');
	        var tab = $('#centerTabs').tabs('getSelected');
	        var index = $('#centerTabs').tabs('getTabIndex',tab);
	        for(var i=tablist.length-1;i>index;i--){
	            $('#centerTabs').tabs('close',i);
	        }
	        var num = index-1;
	        for(var i=num;i>=0;i--){
	            $('#centerTabs').tabs('close',0);
	        }
	    });
	}
};
mainModel.init();

//$(function(){
//	mainModel.tabsinit();
//	
//});