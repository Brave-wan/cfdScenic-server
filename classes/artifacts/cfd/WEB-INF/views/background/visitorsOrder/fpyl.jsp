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
		<div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:订单管理-查看详情</div>
			<form id="ff" action="" method="post">
				<input type="hidden" name="id" value="${model.id }"/>
				<table>
					<tr>
						<td>订单名称：</td>
						<td><input type="text" value="${model.name }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>订单描述：</td>
						<td><input type="text" value="${model.order_describe }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>原价：</td>
						<td><input type="text" value="${model.price }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>开始有效期：</td>
						<td><input type="text" value="<fmt:formatDate value="${model.start_valid }" pattern="yyyy-MM-dd HH:mm:ss" />" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>结束有效期：</td>
						<td><input type="text" value="<fmt:formatDate value="${model.end_valid }" pattern="yyyy-MM-dd HH:mm:ss" />" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>数量：</td>
						<td><input type="text" value="${model.quantity }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>支付方式：</td>
						<td>
						<input type="text"
						<c:if test="${model.pay_way == 1}"> value="余额" </c:if>
						<c:if test="${model.pay_way == 2}"> value="支付宝" </c:if>
						<c:if test="${model.pay_way == 3}"> value="微信" </c:if>
                        <c:if test="${model.pay_way == 4}"> value="积分支付" </c:if>disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>支付状态：</td>
						<td>
						<input type="text"
						<c:if test="${model.pay_state == 0}"> value="未支付" </c:if>
						<c:if test="${model.pay_state == 1}"> value="已支付" </c:if> disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>订单状态：</td>
						<td>
						<input type="text"
						<c:if test="${model.order_state == 1}"> value="确认订单" </c:if>
						<c:if test="${model.order_state == 2}"> value="已支付" </c:if>
						<c:if test="${model.order_state == 3}"> value="已使用" </c:if>
						<c:if test="${model.order_state == 4}"> value="已完成" </c:if>
						<c:if test="${model.order_state == 5}"> value="申请退款" </c:if>
						<c:if test="${model.order_state == 6}"> value="退款失败" </c:if>
						<c:if test="${model.order_state == 7}"> value="退款成功" </c:if>
						<c:if test="${model.order_state == 8}"> value="已过期" </c:if>
						<c:if test="${model.order_state == 9}"> value="已作废" </c:if> disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>订单生成时间：</td>
						<td><input type="text" value="<fmt:formatDate value="${model.create_time }" pattern="yyyy-MM-dd HH:mm:ss" />" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>支付时间：</td>
						<td><input type="text" value="<fmt:formatDate value="${model.pay_time }" pattern="yyyy-MM-dd HH:mm:ss" />" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>退付时间：</td>
						<td><input type="text" value="${model.refund_time }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>用户：</td>
						<td><input type="text" value="${model.nickName }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>应付价格：</td>
						<td><input type="text" value="${model.real_price }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>订单号：</td>
						<td><input type="text" value="${model.order_code }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>是否评价：</td>
						<td>
						<input type="text"
						<c:if test="${model.is_comment == 0}"> value="否" </c:if>
						<c:if test="${model.is_comment == 1}"> value="是" </c:if> disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>景点：</td>
						<td><input type="text" value="${model.vName }" disabled="disabled"/></td>
					</tr>
                    <tr>
                        <td>是否是积分支付：</td>
                        <td>
                            <input type="text"
                                    <c:if test="${model.type == 0}"> value="否" </c:if>
                                    <c:if test="${model.type == 1}"> value="是" </c:if> disabled="disabled"/>
                        </td>
                    </tr>
					<tr>
						<td>积分价格：</td>
						<td><input type="text" value="${model.integra_price }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>地址：</td>
						<td><input type="text" value="${model.address_id }" disabled="disabled"/></td>
					</tr>

                    <tr>
                        <td>是否自提：</td>
                        <td>
                            <input type="text"
                                    <c:if test="${model.is_mention == 0}"> value="不需要" </c:if>
                                    <c:if test="${model.is_mention == 1}"> value="自提" </c:if>
                                    <c:if test="${model.is_mention == 2}"> value="邮寄" </c:if> disabled="disabled"/>
                        </td>
                    </tr>
                    <tr>
                        <td>是否删除：</td>
                        <td>
                            <input type="text"
                                    <c:if test="${model.is_delete == 0}"> value="否" </c:if>
                                    <c:if test="${model.is_delete == 1}"> value="是" </c:if> disabled="disabled"/>
                        </td>
                    </tr>
                    <tr>
                        <td>开发票状态：</td>
                        <td>
                            <input type="text"
                                    <c:if test="${model.invoice_state == 0}"> value="未开票" </c:if>
                                    <c:if test="${model.invoice_state == 1}"> value="开票中" </c:if>
                                    <c:if test="${model.invoice_state == 2}"> value="已开票" </c:if> disabled="disabled"/>
                        </td>
                    </tr>


				</table>
				<div style="text-align:center;padding:5px">
 					<!-- <a id="showPageSaveBtn" href="javascript:void(0)" onclick="$('#ff').submit()" class="easyui-linkbutton" iconCls="icon-save">确定</a> -->
					<a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-save">确定</a>
			    </div>
			</form>
</div>

<%@ include file="/WEB-INF/views-commons/footer.jsp"%>