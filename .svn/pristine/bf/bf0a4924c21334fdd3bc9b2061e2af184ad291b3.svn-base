<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<style type="text/css">
	body{font-family: "微软雅黑";}
	table{width:90%;margin:20px auto;}
	table td{height:45px;text-align:left;}
	table td:first-child{width:10%;}
	table td:last-child{width:90%;}
	table td input[type=text]{width:80%;height:30px;line-height: 30px;padding-left: 10px;}
	table img{width:150px;height:100px;}
	table td textarea{padding-left:10px;}
	table td label{float: left;width:100px;text-align: left;}
	.l-btn-text{line-height: 30px;}
	.easyui-linkbutton{width:30%;height:30px;display: inline-block;}
</style>
<div style="width:98.5%;padding:10px;">
		<div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:账户管理-营业额信息</div>

				<table>
					<tr>
						<td>今日营业额：</td>
						<td><input type="text" value="${todayTurnover }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>累计营业额：</td>
						<td><input type="text" value="${turnover }" disabled="disabled"/></td>
					</tr>

				</table>
				<div style="text-align:center;padding:5px">
 					<!-- <a id="showPageSaveBtn" href="javascript:void(0)" onclick="$('#ff').submit()" class="easyui-linkbutton" iconCls="icon-save">确定</a> -->
					<%--<a id="showPageCancleBtn" href="javascript:void(0)" onclick="window.location.href ='./background/visitorsManage/toVisitorsManage?type=${model.type}'" class="easyui-linkbutton" iconCls="icon-save">确定</a>--%>
					<a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-save">确定</a>
			    </div>
</div>

<%@ include file="/WEB-INF/views-commons/footer.jsp"%>