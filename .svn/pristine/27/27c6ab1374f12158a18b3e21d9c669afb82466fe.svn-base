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
		<div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:景点管理-查看详情</div>
			<form id="ff" action="" method="post">
				<input type="hidden" name="id" value="${model.id }"/>
				<table>
					<tr>
						<td>名称：</td>
						<td><input type="text" value="${model.name }" disabled="disabled"/></td>
					</tr>
                    <tr>
                        <td>英文名称：</td>
                        <td><input type="text" value="${model.nameEn }" disabled="disabled"/></td>
                    </tr>
					<tr>
						<td>简介：</td>
						<td><input type="text" value="${model.visitorsDescribe}" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>原价：</td>
						<td><input type="text" value="${model.price }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>折后价：</td>
						<td><input type="text" value="${model.newPrice }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>地址：</td>
						<td><input type="text" value="${model.address }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>经度：</td>
						<td><input type="text" value="${model.longitude }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>纬度：</td>
						<td><input type="text" value="${model.latitude }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>须知：</td>
						<td><input type="text" value="${model.noticeId }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>是否推荐：</td>
						<td>
						<input type="text"
						<c:if test="${model.isRecommend == 0}"> value="正常" </c:if>
						<c:if test="${model.isRecommend == 1}"> value="推荐" </c:if>disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>状态：</td>
						<td>
						<input type="text" 
						<c:if test="${model.state == 0}"> value="正常" </c:if>
						<c:if test="${model.state == 1}"> value="删除" </c:if> disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>创建时间：</td>
						<td><input type="text" value="${model.createTime }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>备注：</td>
						<td><input type="text" value="${model.memo }" disabled="disabled"/></td>
					</tr>
					<c:if test="${model.type != 4}">
					<tr>
						<td>有效期开始时间：</td>
						<td><input type="text" value="${model.startValid }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>有效期结束时间：</td>
						<td><input type="text" value="${model.endValid }" disabled="disabled"/></td>
					</tr>
					</c:if>
                    <c:if test="${model.type == 4}">
                        <tr>
                            <td>积分：</td>
                            <td><input type="text" value="${model.integral }" disabled="disabled"/></td>
                        </tr>
                    </c:if>
                    <c:if test="${model.type == 5}">
                        <tr>
                            <td>积分：</td>
                            <td><input type="text" value="${model.integral }" disabled="disabled"/></td>
                        </tr>
                    </c:if>
					<tr>
						<td>配送费：</td>
						<td><input type="text" value="${model.deliverFee }" disabled="disabled"/></td>
					</tr>
                    <c:if test="${model.type == 1}">
                        <tr>
                            <td>人数：</td>
                            <td><input type="text" value="${model.number }" disabled="disabled"/></td>
                        </tr>
                    </c:if>
                    <c:if test="${model.type == 2}">
                        <tr>
                            <td>人数：</td>
                            <td><input type="text" value="${model.number }" disabled="disabled"/></td>
                        </tr>
                    </c:if>
                    <c:if test="${model.type == 3}">
                        <tr>
                            <td>人数：</td>
                            <td><input type="text" value="${model.number }" disabled="disabled"/></td>
                        </tr>
                    </c:if>
                    <c:if test="${model.type == 5}">
                        <tr>
                            <td>人数：</td>
                            <td><input type="text" value="${model.number }" disabled="disabled"/></td>
                        </tr>
                    </c:if>

					<tr>
						<td>满意度：</td>
						<td><input type="text" value="${model.satisfaction }" disabled="disabled"/></td>
					</tr>

					<tr>
						<td>类型：</td>
						<td>
						<input type="text"
						<c:if test="${model.type == 1}"> value="景点" </c:if>
						<c:if test="${model.type == 2}"> value="基础活动"</c:if>
						<c:if test="${model.type == 3}"> value="结伴游活动"</c:if>
						<c:if test="${model.type == 4}"> value="积分商品"</c:if>
						<c:if test="${model.type == 5}"> value="积分门票"</c:if> disabled="disabled"/>
						</td>
					</tr>

					<tr>
						<td>主图：</td>
						<td><img src="${model.headImg}" /></td>
					</tr>
                    <tr>
                        <td>须知：</td>
                        <td>
                            <script id="editor1" type="text/plain" style="width:1024px;height:500px;"></script>
                            <input type="hidden" name="noticeId" id="noticeId" value="${model.noticeId}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>景区详情：</td>
                        <td>
                            <script id="editor2" type="text/plain" style="width:1024px;height:500px;"></script>
                            <input type="hidden" name="detailsId" id="detailsId" value="${model.detailsId}"/>
                        </td>
                    </tr>
				</table>
				<div style="text-align:center;padding:5px">
 					<!-- <a id="showPageSaveBtn" href="javascript:void(0)" onclick="$('#ff').submit()" class="easyui-linkbutton" iconCls="icon-save">确定</a> -->
					<%--<a id="showPageCancleBtn" href="javascript:void(0)" onclick="window.location.href ='./background/visitorsManage/toVisitorsManage?type=${model.type}'" class="easyui-linkbutton" iconCls="icon-save">确定</a>--%>
					<a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-save">确定</a>
			    </div>
			</form>
</div>
<script type="text/javascript">

    $(function () {
        UE.getEditor('editor1').addListener("ready", function () {
            UE.getEditor('editor1').setContent('${htmlText1.contentHtml}');
            UE.getEditor('editor1').setDisabled();
        })
        UE.getEditor('editor2').addListener("ready", function () {
            UE.getEditor('editor2').setContent('${htmlText2.contentHtml}');
            UE.getEditor('editor2').setDisabled();
        })
    })



</script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>