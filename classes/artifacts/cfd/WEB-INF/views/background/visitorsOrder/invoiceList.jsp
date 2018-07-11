<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curLoca"></i><font class="fontbold">当前位置:</font>订单管理-景点订单开票
			</div>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>查询条件</h2>
				<em></em>
			</div>
		</div>
		<div class="querydiv">
			<p>
				<label for="searchValueNickName">订单号：</label> <input id="searchValue1"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchValueNickName">订票人：</label> <input id="searchValue2"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchValueNickName">下单时间：</label> <input id="searchValue3"
					class="easyui-validatebox">
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="visitorsOrderManager.search()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>景点订单开票</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			 <a id="shareRuleEditBtn"
				href="javascript:void(0)" onclick="visitorsOrderManager.edit()"
				class="easyui-linkbutton" iconCls="icon-redo">开票</a>

		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="visitorsGid" >
				</table>
			</div>
		</div>
	</div>
</div>

<div id="ImageSubWindow" class="easyui-window" title="开票"
     data-options="closed:true,inline:true"
     style="width: 420px; height: 300px; padding: 5px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding: 10px;">
            <form id="invoiceForm" method="post" enctype="multipart/form-data">
                <input type="hidden" name="orderCodes" id="orderCodes"/>
                <input type="hidden" name="linkIds" id="linkIds"/>
                <input type="hidden" name="type" id="type"/>

                <p>
                    <label class="bolb">付款单位名称:</label> <input id="payUnitName"
                                                               name="payUnitName" type="text" class="easyui-validatebox sleek" required/>
                </p>
                <p>
                    <label class="bolb">发票号:</label> <input id="invoiceNumber"
                                                            name="invoiceNumber" type="text" class="easyui-validatebox sleek;easyui-numberbox" required/>
                </p>
                <p>
                    <label class="bolb">税号:</label> <input id="einNumber"
                                                           name="einNumber" type="text" class="easyui-validatebox sleek" required/>
                </p>
                <p>
                    <label class="bolb">开票金额:</label> <input id="invoiceMoney"
                                                             name="invoiceMoney" type="text" class="easyui-validatebox sleek;easyui-numberbox" required/>
                </p>

            </form>
        </div>
        <div data-options="region:'south',border:false"
             style="text-align: right; padding: 5px 0 0;">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
               href="javascript:void(0)"
               onclick="visitorsOrderManager.submit()">确定</a> <a
                class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
                href="javascript:void(0)"
                onclick="$('#ImageSubWindow').window('close');$('#invoiceForm').form('clear');">取消</a>
        </div>
    </div>
</div>
<script type="text/javascript"
	src="scripts/opensource/background/visitorsOrder/invoiceList.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
