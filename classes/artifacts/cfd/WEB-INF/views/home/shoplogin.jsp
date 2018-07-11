<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/views-commons/taglibs.jsp"%>
<%@ include file="/WEB-INF/views-commons/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/sysHtkj/home/css.css"/>
<script src="${basePath}/scripts/jquery/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
		<title>曹妃甸商家登录</title>
<script type="text/javascript">
		function tijiao(){
			var p = $("#phone").val();
			if(p==""){
				$("#span").html('<span id="span" style="margin-top: 5px;float: left;color: red;">请填写正确的手机号</span>');
				$("#span").show();
				return false;
			}else{
				if(!(/^1[34578]\d{9}$/.test(p))){
					$("#span").html('<span id="span" style="margin-top: 5px;float: left;color: red;">请填写正确的手机号</span>');
					$("#span").show();
					return false;
				}else{
					$("#span").hide();
				}
			}
			var qrmm = $("#password").val();
			if(qrmm==""){
				$("#span").html('<span id="span" style="margin-top: 5px;float: left;color: red;">密码不能为空</span>');
				$("#span").show();
				return false;
			}else{
				$("#span").hide();
				return true;
			}
		}
	
	</script>
	</head>
	<body>
		<div class="box">
			<div class="login">
				<h3>曹妃甸商家登录</h3>
				<form method="post" action="${ctx}/ShopUserPcController/tologin" id="form" onsubmit="return tijiao();">
					<p><i></i><input type="text" name="telPhone" id="phone" placeholder="请输入手机号码" autocomplete="off" class="yhm-txt"/>
					</p>
					<p><i class="mm-ico"></i><input id="password" type="password" placeholder="请输入密码" name="passWord" autocomplete="off" class="yhm-txt"/></p>
					<p style="border: 0;"><span class="ljzc"><a href="${ctx}/ShopUserPcController/toregister">立即注册</a>
					</span><span class="wjmm"><a href="${ctx}/ShopUserPcController/toretrieve">忘记密码?</a></span></p>
					<span id="span" style="margin-top: 5px;float: left;margin-left: 35%;color: red;height: 1px;text-align: center;">${showMsg}</span>
					<p style="border: 0;"><input type="submit" class="dl-btn" value="登录"></p>
				</form>
			</div>
		</div>
	</body>
</html>
