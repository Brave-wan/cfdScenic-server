<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/views-commons/taglibs.jsp"%>
<%@ include file="/WEB-INF/views-commons/meta.jsp"%>
<script src="${basePath}/scripts/jquery/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/sysHtkj/home/css.css"/>
		<title>忘记密码</title>
		<style type="text/css">
			.yzm{
				width: 25%;
			}
		</style>
		<script type="text/javascript">
		$(function(){
			$(".ddforg").hide();
		});
		
	//<img src="images/tishi.jpg" style="margin-right: 5px;"/>请输入正确的手机号 
	//<img src="images/tishi.jpg" style="margin-right: 5px;"/>两次密码不一致，请再次输入 
		function huoqu(){
			var p = $("#phone").val();
			if(p==""){
				$("#ddforg1").show();
			}else{
				if(!(/^1[34578]\d{9}$/.test(p))){
					$("#ddforg1").html('<span class="tishi"><img src="images/tishi.jpg" style="margin-right: 5px;"/>请填写正确的手机号</span>');
					$("#ddforg1").show();
				}else{
					$.post("${ctx}/ShopUserPcController/fsyzm", {phone:p}).success(function(data){
						if (data.success) {
							alert("发送成功！");
						} else {
							alert(data.message);
						}
					}).error(function(ex){
						alert("系统错误！");
					});
				}
			}
		}
	function tijiao(){
		var p = $("#phone").val();
		if(p==""){
			$("#ddforg1").show();
			return;
		}else{
			if(!(/^1[34578]\d{9}$/.test(p))){
				$("#ddforg1").html('<span class="tishi"><img src="images/tishi.jpg" style="margin-right: 5px;"/>请填写正确的手机号</span>');
				$("#ddforg1").show();
				return;
			}else{
				$("#ddforg1").hide();
			}
		}
		var yzm = $("#yzm").val();
		if(yzm==""){
			$("#ddforg2").show();
			return;
		}else{
			$("#ddforg2").hide();
		}
		var mm = $("#mm").val();
		if(mm==""){
			$("#ddforg3").show();
			return;
		}else{
			if(/^[0-9a-zA-Z_]{6,16}$/.test(mm)){
				$("#ddforg3").hide();
			}else{
				$("#ddforg3").html('<span class="tishi"><img src="images/tishi.jpg" style="margin-right: 5px;"/>请填写正确的密码格式：6-16位字母或数字</span>');
				$("#ddforg3").show();
				return;
			}
		}
		var qrmm = $("#qrmm").val();
		if(qrmm==""){
			$("#ddforg4").show();
			return;
		}else{
			if(/^[0-9a-zA-Z_]{6,16}$/.test(qrmm)){
				$("#ddforg4").hide();
			}else{
				$("#ddforg4").html('<span class="tishi"><img src="images/tishi.jpg" style="margin-right: 5px;"/>请填写正确的密码格式：6-16位字母或数字</span>');
				$("#ddforg4").show();
				return;
			}
		}
		if(mm!=qrmm){
			$("#ddforg5").show();
			return;
		}
		$.post("${ctx}/ShopUserPcController/zhmm", $("#form").serialize()).success(function(data){
			if (data.success) {
				alert("成功找回密码,请重新登陆");
				window.location.href = "${ctx}/ShopUserPcController/login";
			} else {
				alert(data.message);
			}
		}).error(function(ex){
			alert("系统错误！");
		});
	}
	</script>
	</head>
	<body>
		<div class="box">
			<p class="ready-yy">已有账号?<span style="border: 1px solid #fff;padding: 5px;margin-left: 10px;"><a href="${ctx}/ShopUserPcController/login" style="color: #fff;">立即登录</a></span></p>
			<div class="forget">
				<h3>忘记密码</h3>
				<form method="post" id="form">
				<p>
					<label class="reg1"></label>
					<input type="text" placeholder="请输入手机号码"  class="yhm-txt" id="phone" name="telPhone"/>
				</p>
				<div class="ddforg" id="ddforg1"><span class="tishi"><img src="images/tishi.jpg" style="margin-right: 5px;"/>请输入正确的手机号 </span></div>
				<p>
					<label class="reg2"></label>
					<input type="text" id="yzm" name="yzm" placeholder="请输入验证码" style="width: 25%;margin-left: 3px;"  class="yhm-txt"/>
					<span class="huoqu" onclick="huoqu();"  style="cursor:pointer">获取验证码</span>
				</p>
				<div class="ddforg" id="ddforg2"><span class="tishi"><img src="images/tishi.jpg" style="margin-right: 5px;"/>请输入验证码 </span></div>
				<p>
					<label class="reg3"></label>
					<input type="password" id="mm" placeholder="请输入密码" style="margin-left: 3px;"  class="yhm-txt"/>
				</p>
				<div class="ddforg" id="ddforg3"><span class="tishi"><img src="images/tishi.jpg" style="margin-right: 5px;"/>请输入密码 </span></div>
				<p>
					<label class="reg4"></label>
					<input type="password" id="qrmm" name="passWord" placeholder="请再次输入密码" style="margin-left: 0px;"  class="yhm-txt"/>
				</p>
				<div class="ddforg" id="ddforg4"><span class="tishi"><img src="images/tishi.jpg" style="margin-right: 5px;"/>请输入确认密码 </span></div>
				<div class="ddforg" id="ddforg5"><span class="tishi"><img src="images/tishi.jpg" style="margin-right: 5px;"/>两次密码输入不一致 </span></div>
				<p style="border: 0;"><a  class="dl-btn" style="width: 385px;margin: 30px auto;cursor:pointer;" onclick="tijiao()">提交</a></p>
				</form>
			</div>
		</div>
	</body>
</html>
