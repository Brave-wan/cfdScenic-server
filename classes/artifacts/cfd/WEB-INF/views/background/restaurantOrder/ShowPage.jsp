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
						<td>订单号：</td>
						<td><input type="text" value="${model.order_code }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>用户：</td>
						<td><input type="text" value="${model.nickName }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>订单名称：</td>
						<td><input type="text" value="${model.name }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>订单描述：</td>
						<td><input type="text" value="${model.order_describe }" disabled="disabled"/></td>
					</tr>
					 <tr>
                        <td>类型：</td>
                        <td>
                            <input type="text"
                                    <c:if test="${model.goods_type == 0}"> value="单品" </c:if>
                                    <c:if test="${model.goods_type == 1}"> value="套餐" </c:if> disabled="disabled"/>
                        </td>
                    </tr>
					<tr>
						<td>商品：</td>
						<td><input type="text" value="${model.goodsName }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>原价：</td>
						<td><input type="text" value="${model.price }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>数量：</td>
						<td><input type="text" value="${model.quantity }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>应付价格：</td>
						<td><input type="text" value="${model.real_price }" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>就餐时间：</td>
						<td><input type="text" value="<fmt:formatDate value="${model.eat_date }" pattern="yyyy-MM-dd HH:mm:ss" />" disabled="disabled"/></td>
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
						<c:if test="${model.order_state == 2}"> value="未使用" </c:if>
						<c:if test="${model.order_state == 3}"> value="申请退款" </c:if>
						<c:if test="${model.order_state == 4}"> value="已使用" </c:if>
						<c:if test="${model.order_state == 5}"> value="已过期" </c:if>
						<c:if test="${model.order_state == 6}"> value="申请退款成功" </c:if>
						<c:if test="${model.order_state == 7}"> value="申请退款失败" </c:if> disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>支付方式：</td>
						<td>
						<input type="text" 
						<c:if test="${model.pay_way == 0}"> value="未支付" </c:if>
						<c:if test="${model.pay_way == 1}"> value="余额" </c:if>
						<c:if test="${model.pay_way == 2}"> value="支付宝" </c:if>
						<c:if test="${model.pay_way == 3}"> value="微信" </c:if> disabled="disabled"/>
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
						<td>是否评价：</td>
						<td>
						<input type="text" 
						<c:if test="${model.is_comment == 0}"> value="否" </c:if>
						<c:if test="${model.is_comment == 1}"> value="是" </c:if> disabled="disabled"/>
						</td>
					</tr>
					 <tr>
                        <td>是否结算：</td>
                        <td>
                            <input type="text"
                                    <c:if test="${model.is_balance == 0}"> value="未结算" </c:if>
                                    <c:if test="${model.is_balance == 1}"> value="已结算" </c:if> disabled="disabled"/>
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
					<%-- <tr >
						<td>店铺：</td>
						<td><input type="hidden" value="${model.shopName }" disabled="disabled"/></td>
					</tr> --%>
					<tr>
						<td>手机号：</td>
						<td><input type="text" value="${model.telphone }" disabled="disabled"/></td>
					</tr>
                   
                   

				</table>
				<div style="text-align:center;padding:5px">
 					<!-- <a id="showPageSaveBtn" href="javascript:void(0)" onclick="$('#ff').submit()" class="easyui-linkbutton" iconCls="icon-save">确定</a> -->
					<a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-save">确定</a>
			    </div>
			</form>
</div>

<%@ include file="/WEB-INF/views-commons/footer.jsp"%>