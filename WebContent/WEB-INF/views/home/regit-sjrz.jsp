<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/views-commons/taglibs.jsp"%>
<%@ include file="/WEB-INF/views-commons/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/sysHtkj/home/css.css"/>
<script src="${basePath}/scripts/jquery/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
		<title>商家注册-商家认证</title>
		<style type="text/css">
			.yzm{
				width: 25%;
			}
		</style>
		<script type="text/javascript">
		$(function(){
			$(".tips").click(function(){
				$(this).hide();
			});
		});
		function tijiao(){
			var formElement = document.getElementById("form");
			var oReq = new XMLHttpRequest();
			oReq.open("POST","${ctx}/background/shopInformationManager/renztjShopInformation", true);
			oReq.send(new FormData(formElement));
			oReq.onreadystatechange = function() {
				if (oReq.readyState == 4) {
					console.log(oReq.status);
					if (oReq.status == 200) {
						var b = oReq.responseText;
						var obj = jQuery.parseJSON(b);
						console.log(obj);
						console.log(obj.success);
						if (obj.success) {
                            $(".tips").show();
						} else {
							alert("上传失败2");
						}
					} else {
						alert("上传失败1");
					}
				}
			}
		}
		function imgf4() {
			var docObj = document.getElementById("imageFile4");
			var imgObjPreview = document.getElementById("ima4");
			if (docObj.files && docObj.files[0]) {
				//火狐下，直接设img属性  
				imgObjPreview.style.display = 'block';
				imgObjPreview.style.width = '120px';
				imgObjPreview.style.height = '120px';
				//imgObjPreview.src = docObj.files[0].getAsDataURL();
				//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式     
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
			} else {
				//IE下，使用滤镜    
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById("imageFile4");
				//必须设置初始大小 
				localImagId.style.width = "120px";    
				localImagId.style.height = "120px";
				//图片异常的捕捉，防止用户修改后缀来伪造图片 
				try {
					localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters
							.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
				} catch (e) {
					alert("您上传的图片格式不正确，请重新选择!");
					return false;
				}
				imgObjPreview.style.display = 'none';

				document.selection.empty();
			}
			return true;
		}
		function imgf5() {
			var docObj = document.getElementById("imageFile5");
			var imgObjPreview = document.getElementById("ima5");
			if (docObj.files && docObj.files[0]) {
				//火狐下，直接设img属性  
				imgObjPreview.style.display = 'block';
				imgObjPreview.style.width = '120px';
				imgObjPreview.style.height = '120px';
				//imgObjPreview.src = docObj.files[0].getAsDataURL();
				//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式     
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
			} else {
				//IE下，使用滤镜    
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById("imageFile5");
				//必须设置初始大小 
				localImagId.style.width = "120px";    
				localImagId.style.height = "120px";
				//图片异常的捕捉，防止用户修改后缀来伪造图片 
				try {
					localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters
							.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
				} catch (e) {
					alert("您上传的图片格式不正确，请重新选择!");
					return false;
				}
				imgObjPreview.style.display = 'none';

				document.selection.empty();
			}
			return true;
		}
		function imgf6() {
			var docObj = document.getElementById("imageFile6");
			var imgObjPreview = document.getElementById("ima6");
			if (docObj.files && docObj.files[0]) {
				//火狐下，直接设img属性  
				imgObjPreview.style.display = 'block';
				imgObjPreview.style.width = '120px';
				imgObjPreview.style.height = '120px';
				//imgObjPreview.src = docObj.files[0].getAsDataURL();
				//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式     
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
			} else {
				//IE下，使用滤镜    
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById("imageFile6");
				//必须设置初始大小 
				localImagId.style.width = "120px";    
				localImagId.style.height = "120px";
				//图片异常的捕捉，防止用户修改后缀来伪造图片 
				try {
					localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters
							.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
				} catch (e) {
					alert("您上传的图片格式不正确，请重新选择!");
					return false;
				}
				imgObjPreview.style.display = 'none';

				document.selection.empty();
			}
			return true;
		}
		function imgf7() {
			var docObj = document.getElementById("imageFile7");
			var imgObjPreview = document.getElementById("ima7");
			if (docObj.files && docObj.files[0]) {
				//火狐下，直接设img属性  
				imgObjPreview.style.display = 'block';
				imgObjPreview.style.width = '120px';
				imgObjPreview.style.height = '120px';
				//imgObjPreview.src = docObj.files[0].getAsDataURL();
				//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式     
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
			} else {
				//IE下，使用滤镜    
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById("imageFile7");
				//必须设置初始大小 
				localImagId.style.width = "120px";    
				localImagId.style.height = "120px";
				//图片异常的捕捉，防止用户修改后缀来伪造图片 
				try {
					localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters
							.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
				} catch (e) {
					alert("您上传的图片格式不正确，请重新选择!");
					return false;
				}
				imgObjPreview.style.display = 'none';

				document.selection.empty();
			}
			return true;
		}
	</script>
	</head>
	<body>
		<div class="box">
			<p class="ready-yy">已有账号?<span style="border: 1px solid #fff;padding: 5px;margin-left: 10px;"><a href="#" style="color: #fff;">立即登录</a></span></p>
			<div class="regis-rz">
				<h3>商家注册-商家认证</h3>
				<form method="post" id="form" enctype="multipart/form-data">
				<div class="buss-dz">
					<input type="hidden" name="id" value="${id}">
					<p>商家名称</p>
					<input type="text" placeholder="请输入商家名称" name="name"  class="regist"/>
				</div>
				<div class="buss-dz">
					<p>店铺类型</p>
					<select name="shopId" style="margin-left: 25px;width: 60px;">
						<option value="1">酒店</option>
						<option value="2">饭店</option>
						<option value="3">特产</option>
						<option value="4">小吃</option>
					</select>
				</div>
				<div class="buss-dz">
					<p>经营产品</p>
					<input type="text" name="businessScope" placeholder="请输入主要经营产品"  class="regist" style="width: 140px;"/>
				</div>
				<div class="add-infor">
					<p style="color: #F75B5C;">添加结算信息</p>
					<div class="actu-lx">
						<p>账户类型</p>
						<select name="accountType">
							<option value="0">对公</option>
							<option value="1">个人</option>
						</select>
					</div>
					<div class="actu-lx">
						<p>账户名称</p>
						<input type="text" name="accountName" placeholder="请输入账户名称" style="width: 170px;"/>
					</div>
					<div class="actu-lx">
						<p>银行账号</p>
						<input type="text" name="bankCard" placeholder="请输入银行账号"/>
					</div>
					<div class="actu-lx">
						<p>开户行</p>
						<input type="text" name="accountBank" placeholder="请输入银行名称" style="width: 170px;"/>
					</div>
					<div class="actu-lx" style="margin-top: 20px;">
						<p>是否有营业执照</p>
						<input type="radio" name="isLicense" value="1" style="margin: 0 10px;width: 20px;vertical-align: middle;" />是 <input type="radio" name="isLicense" value="0"  style="margin: 0px 10px;width: 20px;vertical-align: middle;"/>否
					</div>
				</div>
				<div class="upload-photos">
					<p>请上传证件照片</p>
					<div class="uplo">
						<img src="" id="ima4" style="width: 120px;height: 120px;"/>
						<p style="">营业执照照片</p>
						<p>
							<a href="javascript:void(0)" class="file" class="sczp-btn" >请点击添加
								   <input type="file" name="imageFile4" id="imageFile4" onchange="imgf4()">		   				 
							</a>
						</p>
					</div>
					<div class="uplo">
						<img src="images/4.jpg" style="width: 120px;height: 120px;"/>
						<p style="">营业执照照片示例</p>
					</div>
					<div class="uplo">
						<img src="" id="ima5" style="width: 120px;height: 120px;"/>
						<p style="">其他证件照1</p>
						<p>
							<a href="javascript:void(0)" class="file" class="sczp-btn">请点击添加
								   <input type="file" name="imageFile5" id="imageFile5" onchange="imgf5()">		   	   				 
							</a>
						</p>
					</div>
					<div class="uplo">
						<img src="" id="ima6" style="width: 120px;height: 120px;"/>
						<p style="">其他证件照2</p>
						<p>
							<a href="javascript:void(0)" class="file" class="sczp-btn">请点击添加
								    <input type="file" name="imageFile6" id="imageFile6" onchange="imgf6()">		   		   				 
							</a>
						</p>
					</div>
					<div class="uplo">
						
						<img src="" id="ima7" style="width: 120px;height: 120px;"/>
						<p style="">商家logo </p>
						<p>
							<a href="javascript:void(0)" class="file" class="sczp-btn">请点击添加
								    <input type="file" name="imageFile7" id="imageFile7" onchange="imgf7()">		   		   				 
							</a>
						</p>
					</div>
				</div>
				<p style="color: #999;float: left;font-size: 12px;margin-top: 80px;margin-left: 30px;">隐私声明：我们承诺此信息仅用于核实商家信息，不做任何其他用途！</p>
				<p style="border: 0;text-align: center;"><a href="javascript:void(0)" onclick="tijiao();" class="dl-btn" style="width: 550px;">提交注册申请</a></p>
				</form>
			</div>
		</div>
		<div class="tips">
			<div class="tishia">
				<p>温馨提示</p>
				<p>您的商家注册提交已提交，请等待审核，成功后我们将以短信形式通知您！</p>
				<a href="${ctx}/ShopUserPcController/login" style="display: block;margin-top: 35px;color: #3598DB;border-top: 1px solid #3598DB;padding-top: 10px;">好的</a>
			</div>
		</div>
	</body>
</html>
