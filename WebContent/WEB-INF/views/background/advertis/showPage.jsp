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
		<div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:广告管理-查看详情</div>
			<form id="ff" action="" method="post">
				<input type="hidden" name="id" value="${model.id }"/>
				<table>
					<tr>
						<td>名称：</td>
						<td><input type="text" value="${model.title }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>轮播图来源：</td>
						<td>
						<select name="source" disabled="disabled">
						<option value="1" <c:if test="${model.source==1}">selected</c:if> >景点</option>
						<option value="2" <c:if test="${model.source==2}">selected</c:if> >酒店</option>
						<option value="3" <c:if test="${model.source==3}">selected</c:if> >饭店</option>
						<option value="4" <c:if test="${model.source==4}">selected</c:if> >小吃</option>
						<option value="5" <c:if test="${model.source==5}">selected</c:if> >特产</option>
						<option value="6" <c:if test="${model.source==6}">selected</c:if> >活动</option>
						<option value="7" <c:if test="${model.source==7}">selected</c:if> >结伴游</option>
						<option value="8" <c:if test="${model.source==8}">selected</c:if> >app首页轮播图</option>
						<option value="9" <c:if test="${model.source==9}">selected</c:if> >商城轮播图）</option>
					</select>
						</td>
					</tr>
					<tr>
						<td>状态：</td>
						<td>
						<input type="text" 
						<c:if test="${model.state == 0}"> value="可用" </c:if>
						<c:if test="${model.state == 1}"> value="不可用" </c:if> disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>备注：</td>
						<td><input type="text" value="${model.memo }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>类型：</td>
						<td>
						<input type="text"
						<c:if test="${model.type == 0}"> value="轮播图" </c:if>
						<c:if test="${model.type == 1}"> value="广告位"</c:if>
						<c:if test="${model.type == 2}"> value="商品图片"</c:if>disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>主图：</td>
						<td><img alt="" src="${model.imgUrl}"></td>
					</tr>
					<tr>
						<td>描述：</td>
						<td><input type="text" value="${model.advertDescribe }" disabled="disabled"/></td>
					</tr>
				</table>
				<div style="text-align:center;padding:5px">
 					<!-- <a id="showPageSaveBtn" href="javascript:void(0)" onclick="$('#ff').submit()" class="easyui-linkbutton" iconCls="icon-save">确定</a> -->
					<%--<a id="showPageCancleBtn" href="javascript:void(0)" onclick="window.location.href ='./background/visitorsManage/toVisitorsManage?type=${model.type}'" class="easyui-linkbutton" iconCls="icon-save">确定</a>--%>
					<a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-save">确定</a>
			    </div>
			</form>
</div>
<!-- <script type="text/javascript">

    $(function () {
        UE.getEditor('editor').addListener("ready", function () {
            UE.getEditor('editor').setContent('${htmlText.contentHtml}');
            UE.getEditor('editor').setDisabled();
        })
    })

</script> -->
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>