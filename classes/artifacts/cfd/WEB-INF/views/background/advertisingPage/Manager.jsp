<%@ page contentType="text/html; charset=UTF-8"
         trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false" style="height: 220px;">
        <div class="panel_title">
            <div style="float: left; padding: 10px">
                <i class="curLoca"></i><font>当前位置:</font>系统管理-详情管理
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
                <label for="searchValueNickName">名称:</label> <input id="searchValue1"
                    class="easyui-validatebox" />
            </p>
            <p>
	            <label for="searchValueNickName">类型：</label> 
	           	<select id="searchValue2" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
                    <option checked value="">请选择类型</option>
                    <option  value="1">观鸟景点</option>
                    <option  value="2">湿地保护</option>
                    <option  value="3">招商信息</option>
	            </select>
            </p>
            <p>
                <a href="javascript:void(0)" class="easyui-linkbutton"
                    iconCls="icon-search" onclick="advertisingPageManager.search()">查询</a>
            </p>
        </div>
        <div class="titlediv">
            <div class="titleTxt">
                <h2>轮播图设置</h2>
                <em></em>
            </div>
        </div>
        <div class="menubtndiv">
            <a id="shareRuleAddBtn" href="javascript:void(0)" onclick="advertisingPageManager.add()" class="easyui-linkbutton" iconCls="icon-add">新增</a>
            <a id="shareRuleEditBtn" href="javascript:void(0)" onclick="advertisingPageManager.edit()" class="easyui-linkbutton" iconCls="icon-redo">修改</a>
            <a id="shareRuleDelBtn" href="javascript:void(0)" onclick="advertisingPageManager.del()" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
        </div>
    </div>
    <div data-options="region:'center',border:true">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'center'">
                <table id="advertisingPageGid">
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="scripts/opensource/background/advertisingPage/Manager.js"></script>
<%@include file="/WEB-INF/views-commons/footer.jsp"%>