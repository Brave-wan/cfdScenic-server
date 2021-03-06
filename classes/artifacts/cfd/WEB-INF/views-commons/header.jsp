<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="taglibs.jsp"%>
<%@ include file="meta.jsp"%>
<title>华腾后台管理系统</title>
<c:set var="viewmodel" value="${ctx}/scripts/zckj" />
<c:set var="controller" value="${ctx}/" />
<%-- 设置当前控制器的路径，简化路径操作 --%>
<link rel="shortcut icon" href="${basePath}/favicon.ico" />

<script type="text/javascript" src="scripts/jquery/jquery-1.9.1.js" charset="utf-8"></script>

<!-- easyui -->
<link rel="stylesheet" type="text/css" href="scripts/jquery/jquery-easyui-1.4.4/themes/metro/easyui.css" />
<link rel="stylesheet" type="text/css" href="scripts/jquery/jquery-easyui-1.4.4/themes/icon.css" />

<script type="text/javascript" src="scripts/jquery/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-easyui-1.4.4/jquery.easyui.extend.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-easyui-1.4.4/jquery.easyui.showMsg.extend.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-easyui-1.4.4/jquery.easyui.userSelections.extends.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-easyui-1.4.4/jquery.easyui.validatebox.extend.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-easyui-datagridview/datagrid-defaultview.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-easyui-datagridview/datagrid-bufferview.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-easyui-datagridview/datagrid-groupview.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-easyui-datagridview/datagrid-scrollview.js"></script>
<!-- My97DatePicker -->
<script type="text/javascript" src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>

<!-- ym-extends-js -->
<script type="text/javascript" src="scripts/jquery/jquery-extends/jquery-datetimeformatter-extends.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-extends/jquery-formserializeobj-extends.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-extends/jquery-submit-extends.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-extends/jquery-onclick-getsondata.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-utils/jquery-utils.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-utils/jquery-idcardParse.js"></script>

<!-- kindeditor -->
<!-- <script type="text/javascript" src="scripts/kindeditor/kindeditor.js"></script> -->
<!-- <script type="text/javascript" src="scripts/kindeditor/kindeditor-all.js"></script> -->
<script type="text/javascript" src="scripts/kindeditor/kindeditor-all-min.js"></script>
<!-- <script type="text/javascript" src="scripts/kindeditor/kindeditor-min.js"></script> -->
<script type="text/javascript" src="scripts/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="scripts/kindeditor/jquery.easyui.kind.extend.js"></script>
<link rel="stylesheet" href="scripts/kindeditor/themes/default/default.css" />
<!-- <link rel="stylesheet" href="scripts/kindeditor/themes/default/index.css" /> -->
<!-- <link rel="stylesheet" href="scripts/kindeditor/plugins/code/prettify.css" /> -->
<!--汉字拼音转换  -->
<script type="text/javascript" src="scripts/jquery/jQuery.Hz2Py-min.js"></script>
<script type="text/javascript" src="scripts/jquery/jQuery.py-min.js"></script>


<link type="text/css" rel="stylesheet" href="scripts/jquery/jquery-uploadify/uploadify.css" />
<script type="text/javascript" charset="utf-8" src="scripts/jquery/jquery-uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" charset="utf-8" src="scripts/jquery/jquery-extends/jquery-uploadfiy-plugin.js"></script>
<!-- 地图显示 -->
<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css" />
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=2f589c0f0d80857dbac2b0b4d36acbfb&plugin=AMap.Autocomplete"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=2f589c0f0d80857dbac2b0b4d36acbfb&plugin=AMap.ToolBar"></script>
<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>

<link type="text/css" rel="stylesheet" href="scripts/ueditor/themes/default/css/ueditor.min.css" />
<script type="text/javascript" charset="utf-8" src="scripts/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="scripts/ueditor/ueditor.all.min.js"> </script>


<!--自定义样式 -->
<link rel="stylesheet" type="text/css"  href="styles/style.css"/> 
<link rel="stylesheet" type="text/css"  href="styles/div.css"/> 
<link rel="stylesheet" type="text/css"  href="styles/form.css"/> 
<link rel="stylesheet" type="text/css"  href="styles/ico.css"/> 
  <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->  
    <!--[if lt IE 9]><script src="ie8-responsive-file-warning.js"></script><![endif]-->  
    <script src="scripts/ie/ie-emulation-modes-warning.js"></script>  
  
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->  
    <script src="scripts/ie/ie10-viewport-bug-workaround.js"></script>  
  
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->  
    <!--[if lt IE 9]>  
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>  
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>  
    <![endif]-->  
    <!--[if lt IE 9]> 
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<!--[if lte IE 8]> 
<!-- 	<noscript> -->
<!-- 	     <style>.html5-wrappers{display:none!important;}</style> -->
<!-- 	     <div class="ie-noscript-warning">您的浏览器禁用了脚本，请<a href="">查看这里</a>来启用脚本!或者<a href="/?noscript=1">继续访问</a>. -->
<!-- 	     </div> -->
<!-- 	</noscript> -->
<!-- 	<![endif]--> 
<title>系统</title>
</head>