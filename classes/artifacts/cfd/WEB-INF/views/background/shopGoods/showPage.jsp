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


<%--酒店详情--%>
<c:if test="${type == 1}">
    <div style="width:98.5%;padding:10px;">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:商家管理-查看详情</div>
        <form id="ff" action="" method="post" >
            <input type="hidden" name="id" value="${model.id}"/>
            <table>
                <tr>
                    <td>房间名称：</td>
                    <td><input type="text" name="goodsName" value="${model.goodsName}" disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>房间描述：</td>
                    <td><input type="text" name="goodsDescribe" value="${model.goodsDescribe}" disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>标签：</td>
                    <td><input type="text" name="label" value="${model.label}" disabled="disabled" /></td>
                </tr>
                <tr>
                    <td>原价：</td>
                    <td><input type="text" name="price" value="${model.price}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"  disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>新价：</td>
                    <td><input type="text" name="newPrice" value="${model.newPrice}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" disabled="disabled" /></td>
                </tr>
                <tr>
                    <td>房间数量：</td>
                    <td><input type="text" name="stock" value="${model.stock}" disabled="disabled" /></td>
                </tr>
                <tr>
                    <td>是否推荐：</td>
                    <td>
                        <input type="text"
                                <c:if test="${model.isRecomment == 0}"> value="否" </c:if>
                                <c:if test="${model.isRecomment == 1}"> value="是" </c:if>disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td>是否热销：</td>
                    <td>
                        <input type="text"
                                <c:if test="${model.isHot == 0}"> value="否" </c:if>
                                <c:if test="${model.isHot == 1}"> value="是" </c:if>disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td>状态：</td>
                    <td>
                        <input type="text"
                                <c:if test="${model.state == 0}"> value="正常" </c:if>
                                <c:if test="${model.state == 1}"> value="停用" </c:if>disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td>所属店铺：</td>
                    <td>
                        <input type="text"  value="${model.name}" disabled="disabled" />
                    </td>
                </tr>
                <tr>
                    <td>商品类型：</td>
                    <td>
                        <input type="text"
                                <c:if test="${model.type == 1}"> value="酒店" </c:if>
                                <c:if test="${model.type == 2}"> value="饭店" </c:if>
                                <c:if test="${model.type == 3}"> value="特产" </c:if>
                                <c:if test="${model.type == 4}"> value="小吃" </c:if>disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td>主图：</td>
                    <td>
                        <img alt="" src="${model.describeImg }">
                    </td>
                </tr>
                <tr>
                    <td>详情：</td>
                    <td>
                        <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                        <input type="hidden" name="contentId" id="contentId" value="${model.contentId}" />
                    </td>
                </tr>
            </table>
            <div style="text-align:center;padding:5px">
            	
                <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-save">确定</a>
            </div>
        </form>
    </div>
</c:if>


<%--饭店详情--%>
<c:if test="${type == 2}">
    <div style="width:98.5%;padding:10px;">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:商家管理-查看详情</div>
        <form id="ff" action="" method="post" >
            <input type="hidden" name="id" value="${model.id}"/>
            <table>
                <tr>
                    <td>单品名称：</td>
                    <td><input type="text" name="goodsName" value="${model.goodsName}" disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>单品描述：</td>
                    <td><input type="text" name="goodsDescribe" value="${model.goodsDescribe}" disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>标签：</td>
                    <td><input type="text" name="label" value="${model.label}" disabled="disabled" /></td>
                </tr>
                <tr>
                    <td>原价：</td>
                    <td><input type="text" name="price" value="${model.price}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"  disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>新价：</td>
                    <td><input type="text" name="newPrice" value="${model.newPrice}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" disabled="disabled" /></td>
                </tr>
              <%--   <tr>
                    <td>配送费：</td>
                    <td><input type="text" name="deliverFee" value="${model.deliverFee}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" disabled="disabled" /></td>
                </tr>
                <tr>
                    <td>库存：</td>
                    <td><input type="text" name="stock" value="${model.stock}" disabled="disabled" /></td>
                </tr> --%>
                <tr>
                    <td>是否推荐：</td>
                    <td>
                        <input type="text"
                                <c:if test="${model.isRecomment == 0}"> value="否" </c:if>
                                <c:if test="${model.isRecomment == 1}"> value="是" </c:if>disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td>是否热销：</td>
                    <td>
                        <input type="text"
                                <c:if test="${model.isHot == 0}"> value="否" </c:if>
                                <c:if test="${model.isHot == 1}"> value="是" </c:if>disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td>状态：</td>
                    <td>
                        <input type="text"
                                <c:if test="${model.state == 0}"> value="正常" </c:if>
                                <c:if test="${model.state == 1}"> value="停用" </c:if>disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td>所属店铺：</td>
                    <td>
                        <input type="text"  value="${model.name}" disabled="disabled" />
                    </td>
                </tr>
                <tr>
                    <td>商品类型：</td>
                    <td>
                        <input type="text"
                                <c:if test="${model.type == 1}"> value="酒店" </c:if>
                                <c:if test="${model.type == 2}"> value="饭店" </c:if>
                                <c:if test="${model.type == 3}"> value="特产" </c:if>
                                <c:if test="${model.type == 4}"> value="小吃" </c:if>disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td>单品图片：</td>
                    <td>
                        <img alt="" src="${model.describeImg }">
                    </td>
                </tr>
                <tr>
                    <td>详情：</td>
                    <td>
                        <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                        <input type="hidden" name="contentId" id="contentId" value="${model.contentId}" />
                    </td>
                </tr>
            </table>
            <div style="text-align:center;padding:5px">
                <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-save">确定</a>
            </div>
        </form>
    </div>
</c:if>


<%--特产详情--%>
<c:if test="${type == 3}">
<div style="width:98.5%;padding:10px;">
		<div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:商家管理-查看详情</div>
			<form id="ff" action="./background/shopGoodsManage/adddanpin" method="post" >
                <input type="hidden" name="id" value="${model.id}"/>
                <input type="hidden" name="type" value="3"/>
				<table>
					<tr>
						<td>商品名称：</td>
						<td><input type="text" name="goodsName" value="${model.goodsName}" disabled="disabled"/></td>
					</tr>
					<tr>
						<td>商品描述：</td>
						<td><input type="text" name="goodsDescribe" value="${model.goodsDescribe}" disabled="disabled"/></td>
					</tr>
                    <tr>
                        <td>标签：</td>
                        <td><input type="text" name="label" value="${model.label}" disabled="disabled" /></td>
                    </tr>
                    <tr>
                        <td>品牌/规格：</td>
                        <td><input type="text" name="brand" value="${model.brand}" disabled="disabled" /></td>
                    </tr>
					<tr>
						<td>原价：</td>
						<td><input type="text" name="price" value="${model.price}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"  disabled="disabled"/></td>
					</tr>
					<tr>
						<td>新价：</td>
						<td><input type="text" name="newPrice" value="${model.newPrice}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" disabled="disabled" /></td>
					</tr>
					<tr>
						<td>配送费：</td>
						<td><input type="text" name="deliverFee" value="${model.deliverFee}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" /></td>
					</tr>
					<tr>
						<td>库存：</td>
						<td><input type="text" name="stock" value="${model.stock}" disabled="disabled" /></td>
                    </tr>
					<tr>
						<td>是否推荐：</td>
                        <td>
                            <input type="text"
                                    <c:if test="${model.isRecomment == 0}"> value="否" </c:if>
                                    <c:if test="${model.isRecomment == 1}"> value="是" </c:if>disabled="disabled"/>
                        </td>
					</tr>
					<tr>
						<td>是否热销：</td>
						<td>
                            <input type="text"
                                    <c:if test="${model.isHot == 0}"> value="否" </c:if>
                                    <c:if test="${model.isHot == 1}"> value="是" </c:if>disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td>状态：</td>
						<td>
                            <input type="text"
                                    <c:if test="${model.state == 0}"> value="正常" </c:if>
                                    <c:if test="${model.state == 1}"> value="停用" </c:if>disabled="disabled"/>
						</td>
					</tr>
                    <tr>
                        <td>所属店铺：</td>
                        <td>
                            <input type="text"  value="${model.name}" disabled="disabled" />
                        </td>
                    </tr>
                    <tr>
                        <td>商品类型：</td>
                        <td>
                            <input type="text"
                                    <c:if test="${model.type == 1}"> value="酒店" </c:if>
                                    <c:if test="${model.type == 2}"> value="饭店" </c:if>
                                    <c:if test="${model.type == 3}"> value="特产" </c:if>
                                    <c:if test="${model.type == 4}"> value="小吃" </c:if>disabled="disabled"/>
                        </td>
                    </tr>
                    <tr>
                        <td>主图：</td>
                        <td>
                            <img alt="" src="${model.describeImg }">
                        </td>
                    </tr>
                    <tr>
                        <td>图集：</td>
                        <td>
                            <c:forEach items="${picture}" var="picture">
                                <div style="float: left;">
                                    <img class='imgUrl' alt="" src="${picture }" >
                                </div>
                            </c:forEach>

                        </td>
                    </tr>
					<tr>
						<td>详情：</td>
						<td>
							<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                            <input type="hidden" name="contentId" id="contentId" value="${model.contentId}" />
						</td>
					</tr>
				</table>
				<div style="text-align:center;padding:5px">
                     <a id="showPageSaveBtn" href="javascript:void(0)" onclick="save()" class="easyui-linkbutton" iconCls="icon-save">确定</a>
                <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
			    </div>
			</form>
</div>
</c:if>


<%--小吃详情--%>
<c:if test="${type == 4}">
    <div style="width:98.5%;padding:10px;">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:商家管理-查看详情</div>
        <form id="ff" action="./background/shopGoodsManage/adddanpin" method="post" >
            <input type="hidden" name="id" value="${model.id}"/>
            <input type="hidden" name="type" value="4"/>
            <table>
                <tr>
                    <td>商品名称：</td>
                    <td><input type="text" name="goodsName" value="${model.goodsName}" disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>商品描述：</td>
                    <td><input type="text" name="goodsDescribe" value="${model.goodsDescribe}" disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>标签：</td>
                    <td><input type="text" name="label" value="${model.label}" disabled="disabled" /></td>
                </tr>
                <tr>
                    <td>品牌/规格：</td>
                    <td><input type="text" name="brand" value="${model.brand}" disabled="disabled" /></td>
                </tr>
                <tr>
                    <td>原价：</td>
                    <td><input type="text" name="price" value="${model.price}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"  disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>新价：</td>
                    <td><input type="text" name="newPrice" value="${model.newPrice}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" disabled="disabled" /></td>
                </tr>
                <tr>
                    <td>配送费：</td>
                    <td><input type="text" name="deliverFee" value="${model.deliverFee}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"  /></td>
                </tr>
                <tr>
                    <td>库存：</td>
                    <td><input type="text" name="stock" value="${model.stock}" disabled="disabled" /></td>
                </tr>
                <tr>
                    <td>是否推荐：</td>
                    <td>
                        <input type="text"
                                <c:if test="${model.isRecomment == 0}"> value="否" </c:if>
                                <c:if test="${model.isRecomment == 1}"> value="是" </c:if>disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td>是否热销：</td>
                    <td>
                        <input type="text"
                                <c:if test="${model.isHot == 0}"> value="否" </c:if>
                                <c:if test="${model.isHot == 1}"> value="是" </c:if>disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td>状态：</td>
                    <td>
                        <input type="text"
                                <c:if test="${model.state == 0}"> value="正常" </c:if>
                                <c:if test="${model.state == 1}"> value="停用" </c:if>disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td>所属店铺：</td>
                    <td>
                        <input type="text"  value="${model.name}" disabled="disabled" />
                    </td>
                </tr>
                <tr>
                    <td>商品类型：</td>
                    <td>
                        <input type="text"
                                <c:if test="${model.type == 1}"> value="酒店" </c:if>
                                <c:if test="${model.type == 2}"> value="饭店" </c:if>
                                <c:if test="${model.type == 3}"> value="特产" </c:if>
                                <c:if test="${model.type == 4}"> value="小吃" </c:if>disabled="disabled"/>
                    </td>
                </tr>
                <tr>
                    <td>主图：</td>
                    <td>
                        <img alt="" src="${model.describeImg }">
                    </td>
                </tr>
                <tr>
                    <td>图集：</td>
                    <td>
                        <c:forEach items="${picture}" var="picture">
                            <div style="float: left;">
                                <img class='imgUrl' alt="" src="${picture }" >
                            </div>
                        </c:forEach>

                    </td>
                </tr>
                <tr>
                    <td>详情：</td>
                    <td>
                        <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                        <input type="hidden" name="contentId" id="contentId" value="${model.contentId}" />
                    </td>
                </tr>
            </table>
            <div style="text-align:center;padding:5px">
            	 <a id="showPageSaveBtn" href="javascript:void(0)" onclick="save()" class="easyui-linkbutton" iconCls="icon-save">确定</a>
                <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
            </div>
        </form>
    </div>
</c:if>
<c:if test="${danpin == 1}">
 <div style="width:98.5%;padding:10px;">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:商家管理-添加饭店-查看套餐详情</div>
        <form id="ff" action="./background/shopGoodsManage/adddanpin" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>套餐名称：</td>
                    <input type="hidden" name="type" value="2"/>
                     <input type="hidden" name="danpin" value="1"/>
                     <input type="hidden" name="id" value="${repack.id}"/>
                    <td><input type="text" value="${repack.name}" disabled="disabled"/></td>
                </tr>
                <tr>
                    <td>原价：</td>
                    <td><input type="text" value="${repack.price }" disabled="disabled"
                     onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" /></td>
                </tr>
                <tr>
                    <td>新价：</td>
                    <td><input type="text" value="${repack.new_price}" disabled="disabled"
                    onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" /></td>
                </tr>
                <%-- <tr>
                    <td>配送费：</td>
                    <td><input type="text" value="${repack.deliverFee }" name="deliverFee" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" /></td>
                </tr> --%>
               <%--  <tr>
                    <td>库存：</td>
                    <td><input type="text" name="stock" value="${repack.stock}"/></td>
                </tr> --%>
             <%--    <tr>
                    <td>所属店铺：</td>
                    <td>
                        <input type="hidden" value="${shopInformation.id}" name="shopInformationId">
                        <input type="text" value="${shopInformation.name}" readonly>
                    </td>
                </tr> --%>
                <tr>
                    <td>是否推荐：</td>
                    <td>
                        <input readonly="readonly" type="radio" <c:if test="${repack.is_recomment == 0}"> checked="checked"</c:if> name="isRecomment" value="0">否
                        <input readonly="readonly" type="radio"<c:if test="${repack.is_recomment == 1}"> checked="checked" </c:if>name="isRecomment" value="1">是
                    </td>
                </tr>
                <tr>
                    <td>是否热销：</td>
                    <td>
                        <input readonly="readonly" type="radio" <c:if test="${repack.is_hot == 0}"> checked="checked"</c:if>  name="isHot" value="0">否
                        <input readonly="readonly" type="radio"<c:if test="${repack.is_hot == 1}"> checked="checked" </c:if> name="isHot" value="1">是
                    </td>
                </tr>
                <tr>
                    <td>状态：</td>
                    <td>
                        <input readonly="readonly" type="radio" <c:if test="${repack.state == 0}"> checked="checked"</c:if> name="state" value="0">正常
                        <input readonly="readonly" type="radio" <c:if test="${repack.state == 1}"> checked="checked"</c:if> name="state" value="1">下架
                    </td>
                </tr>
                <tr>
                	<td>单品:</td>
					<input type="hidden" id="goodsIds" value="${repack.goods_ids}">
					<td>
						<table style="width: 600px;margin-left: 1px;">
							<tr>
								<c:forEach items="${list}" var="s" varStatus="vs">
									<td style="width: 150px;margin-left: 0px; ">
										<img alt=""src="${s.describeImg}">
										<%-- <input type="checkbox" class="inputsign" name="goodsIds" value="${s.id}">${s.goodsName} --%>
									</td>
										<c:if test="${vs.count%4==0}"> <br> </c:if>
								</c:forEach>
							</tr>
						</table>
					</td>
				</tr>
                <tr>
                    <td>套餐图片：</td>
                    <td><img alt="" src="${repack.head_img}"></td>
                </tr>
                <tr>
                    <td>详情：</td>
                    <td>
                        <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                        <input type="hidden" name="contentId" id="contentId" value="${repack.notice_id}"/>

                    </td>
                </tr>
            </table>
            <div style="text-align:center;padding:5px">
                <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
            </div>
        </form>
    </div>
</c:if>

<script type="text/javascript">

    $(function () {
        UE.getEditor('editor').addListener("ready", function () {
            UE.getEditor('editor').setContent('${htmlText.contentHtml}');
            UE.getEditor('editor').setDisabled();
        });
    });
    $(function (){
 	   var a = $("#goodsIds").val();
 	   var b=a.split(",");
 	   $.each($(".inputsign"),function(){
 		   _this=$(this);
 		   var _value=$(this).val();
 		   $.each(b,function(index,res){
 			   if(res==_value){
 				   _this.attr("checked","true");
 			   }
 		   });
 	   });
    });
    
    function save(){
    	 $.post("./background/shopGoodsManage/adddanpin",$("ff").serialize(),{
         }).success(function (data) {
             if (data.success) {
            	 eu.showMsg();
            	 window.location.href = "./background/shopGoodsManage/toShopGoodsManage";
             } else {
                 eu.showMsg(data);
             }
         }).error(function (data) {
             eu.showMsg("系统错误，请联系管理员！");
         });
    }
    
</script>

<%@ include file="/WEB-INF/views-commons/footer.jsp"%>