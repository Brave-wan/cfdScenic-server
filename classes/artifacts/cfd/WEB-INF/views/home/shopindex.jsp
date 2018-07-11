<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">

	<div data-options="region:'north',border:true" style="height: 70px; padding: 10px;">
		<div style="height: 40px; width: 400px; float: left;">
			<h2>华腾后台管理系统</h2>
		</div>
		<div style="float: right; margin-right: 10px; height: 40px; width: 400px;">
			<span> 欢迎您 【<font color="red"><c:out value="${user.name}" /></font>】！！！
			</span>&nbsp;&nbsp;<a href="shoplogout"> 退出</a>
		</div>
	</div>
	<div region="west" data-options="border:true" style="width: 200px; padding: 0 10 10px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curmenu"></i><font class="fontbold">功能菜单</font>
			</div>
		</div>
		<div class="clearfix"></div>
		<ul id="menutree" class="easyui-tree" sytle="padding-top: 20px;"
			data-options="url:'getMenuShopTree',method:'get',onClick:mainModel.treeClick,parentField:'pid'">
		</ul>
	</div>
	<div data-options="region:'center',iconCls:'icon-ok'" id="homeMain">
		<iframe id="mainiframe" name="mainiframe" src="welcome" frameborder="0" style="border: 0; width: 100%; height: 100%;"></iframe>
	</div>
	<div data-options="region:'south',border:true" style="height: 40px; padding: 0px; background: #e6eef8; text-align: center;">
		<p class="foot">版权所有：石家庄华腾软件科技有限公司</p>
	</div>
	
</div>
<script type="text/javascript" src="scripts/sysHtkj/home/index.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>