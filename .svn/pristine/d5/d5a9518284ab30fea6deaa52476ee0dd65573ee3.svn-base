<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=2f589c0f0d80857dbac2b0b4d36acbfb&plugin=AMap.MouseTool"></script>

<style type="text/css">
	 body{font-family: "微软雅黑";padding:0;}
    #initMap {
	width: 100%;
	height: 100%;
	font-size: 12px;
	font-family: "微软雅黑";
	}
	.tb{width:100%;height:auto;border-collapse:collapse;}
	.tb td{height:40px;border:1px solid #b4b4b4;}
	.tb input[type=text]{height:40px;border:none;width:100%;text-align:center;}
	

</style>


    <div style="width:100%;">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:平台管理-设置导航路线</div>
		<label style="float:left">提示：在地图点击左键开始选择导航线路，点击右键结束选择，景点名称及简介请手动填写</label>
		<div id="mousedown" style="width:100%;height:300px">
			<div id="initMap" tabindex="0"></div>
		</div>
		
			<div id="addTabel"></div>
			<input type="hidden" id="xPoint">
			<input type="hidden" id="yPoint">
			<input type="hidden" id="groupName">
			<input type="hidden" id="name">
			<input type="hidden" id="info">
			<input type="hidden" id="type">
            <div style="text-align:center;padding:5px;margin-top:15px">
                <a id="showPageSaveBtn" href="javascript:void(0)" onclick="clearAll()" class="easyui-linkbutton" iconCls="icon-save">清除所有路线</a>
                <a id="showPageSaveBtn" href="javascript:void(0)" onclick="eachTR()" class="easyui-linkbutton" iconCls="icon-save">确定</a>
                <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
            </div>
    </div>
<script type="text/javascript">
var map = new AMap.Map("initMap",{
    resizeEnable: true,
    zoom:13,
    center: [118.362199,39.205449]
});
var mouseTool = new AMap.MouseTool(map);
mouseTool.polyline();
var sortI = 1;
//事件回调函数
var callBackFn = function(e) {
	var addHtml ='';
	if($("#addTabel").html() != ''){
	  addHtml = '<table class="tb">'+
		'<tr>'+
			'<td width="10%">'+
				'<input type="text" readonly="readonly" class="result" value="'+e.lnglat.getLng()+'"/>'+
			'</td>'+
			'<td width="10%">'+
				'<input type="text" readonly="readonly" class="result" value="'+e.lnglat.getLat()+'"/>'+
			'</td>'+
			'<td width="15%">'+
				'<input type="text" class="result"/>'+
			'</td>'+
			'<td width="22%">'+
				'<input type="text" class="result"/>'+
			'</td>'+
			'<td width="35%">'+
				'<input type="text" class="result"/>'+
			'</td>'+
			'<td width="8%">'+
				'<select class="result">'+
					'<option value="0">否</option>'+    		
					'<option value="1">是</option>'+
				'</select>'+
			'</td>'+
		'</tr>'+
	'</table>';
	}else{
		addHtml = '<table class="tb" style="margin-top:25px">'+
  		'<tr class="title">'+  
			'<td width="10%">经度</td>'+
			'<td width="10%">纬度</td>'+
			'<td width="15%">线路名称</td>'+
			'<td width="22%">经纬点名称</td>'+    
			'<td width="35%">经纬点简介</td>'+
			'<td width="8%">是否是景点</td>'+
		'</tr>'+
		'<tr>'+
			'<td>'+
				'<input type="text" readonly="readonly" class="result" value="'+e.lnglat.getLng()+'"/>'+
			'</td>'+
			'<td>'+
				'<input type="text" readonly="readonly" class="result" value="'+e.lnglat.getLat()+'"/>'+
			'</td>'+
			'<td>'+
				'<input type="text" class="result"/>'+
			'</td>'+
			'<td>'+
				'<input type="text" class="result"/>'+
			'</td>'+
			'<td>'+
				'<input type="text" class="result"/>'+
			'</td>'+
			'<td>'+
				'<select class="result">'+
					'<option value="0">否</option>'+    		
					'<option value="1">是</option>'+
				'</select>'+
			'</td>'+
		'</tr>'+
	'</table>';
	}
sortI++;
$('#addTabel').append(addHtml);
};
map.on('click',callBackFn);
map.on('rightclick', function(e) {
	mouseTool.close();
	map.off('click',callBackFn);
	var mapHtml = $('#addTabel').html();
})
function clearAll(){
	map.clearMap();
	map.on('click',callBackFn);
	mouseTool.polyline();
	$("#addTabel").html("");
}

function eachTR(){
	var a=false;
	$.each($(".tb .title td"),function(index){
		if(a){
			return false;
		}
		var result = '';
		$.each($(".tb tr").not(".title"),function(ind,res){
			if(result.length > 0){
				if($(this).find("td").eq(index).find(".result").val() == '' || typeof($(this).find("td").eq(index).find(".result").val())=="undefined"){
					eu.showMsg("所有参数均不能为空");
					$("#xPoint").val('');
					$("#yPoint").val('');
					$("#groupName").val('');
					$("#name").val('');
					$("#info").val('');
					$("#type").val('');
					a = true;
					return false;
				}else{
					result += "&"+$(this).find("td").eq(index).find(".result").val();
				}
			}else{
				if($(this).find("td").eq(index).find(".result").val() == '' || typeof($(this).find("td").eq(index).find(".result").val())=="undefined"){
					eu.showMsg("所有参数均不能为空");
					$("#xPoint").val('');
					$("#yPoint").val('');
					$("#groupName").val('');
					$("#name").val('');
					$("#info").val('');
					$("#type").val('');
					a = true;
					return false;
				}else{
					result = $(this).find("td").eq(index).find(".result").val();
				}
			}
		})
		
		switch (index) {
		case 0:
			$("#xPoint").val(result);
			break;
		case 1:
			$("#yPoint").val(result);
			break;
		case 2:
			$("#groupName").val(result);
			break;
		case 3:
			$("#name").val(result);
			break;
		case 4:
			$("#info").val(result);
			break;
		case 5:
			$("#type").val(result);
			break;
		}
	})
	if(a){
		$("#xPoint").val('');
		$("#yPoint").val('');
		$("#groupName").val('');
		$("#name").val('');
		$("#info").val('');
		$("#type").val('');
		return false;
	}else{
		var xPoint = $("#xPoint").val();
		var yPoint = $("#yPoint").val();
		var groupName = $("#groupName").val();
		var name = $("#name").val();
		var info = $("#info").val();
		var type = $("#type").val();
		$.post("./way/toAddWay",{xPoint:xPoint,yPoint:yPoint,groupName:groupName,name:name,info:info,type:type},function(data){
			if(data.success){
				eu.showMsg("保存成功！");
				window.location.href="${basePath}/way/toWayPage";
			}else{
				eu.showMsg("保存失败！");
			}
		})
	}
}
</script>

<%@ include file="/WEB-INF/views-commons/footer.jsp"%>