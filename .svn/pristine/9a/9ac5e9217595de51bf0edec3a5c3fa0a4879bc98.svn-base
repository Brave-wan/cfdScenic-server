<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=2f589c0f0d80857dbac2b0b4d36acbfb&plugin=AMap.MouseTool"></script>

<style type="text/css">
	 body{font-family: "微软雅黑";padding:0;}
/*     #initMap { */
/* 	width: 100%; */
/* 	height: 100%; */
/* 	font-size: 12px; */
/* 	font-family: "微软雅黑"; */
/* 	} */
	.tb{width:100%;height:auto;border-collapse:collapse;}
	.tb td{height:40px;border:1px solid #b4b4b4;}
	.tb input[type=text]{height:40px;border:none;width:100%;text-align:center;}
	

</style>


    <div style="width:100%;">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:平台管理-导航路线详情</div>
<!-- 		<label style="float:left">提示：在地图点击左键开始选择导航线路，点击右键结束选择，景点名称及简介请手动填写</label> -->
<!-- 		<div id="mousedown" style="width:100%;height:300px"> -->
<!-- 			<div id="initMap" tabindex="0"></div> -->
<!-- 		</div> -->
			
			
			<div id="addTabel">
				<table class="tb" style="margin-top:25px">
					<tr>
						<td width="10%">经度</td>
						<td width="10%">纬度</td>
						<td width="15%">线路名称</td>
						<td width="22%">经纬点名称</td>
						<td width="35%">经纬点简介</td>
						<td width="8%">是否是景点</td>
					</tr>
					<c:forEach items="${list}" var="l">
						<tr>
							<td>
								<input type="text" readonly="readonly" class="result" value="${l.x_point}"/>
							</td>
							<td>
								<input type="text" readonly="readonly" class="result" value="${l.y_point}"/>
							</td>
							<td>
									<input type="text" readonly="readonly" value="${l.group_name}"/>
							</td>
							<td>
									<input type="text" readonly="readonly" value="${l.name}"/>
							</td>
							<td>
									<input type="text" readonly="readonly" value="${l.info}"/>
							</td>
							<td>
								<c:if test="${l.type == 0}">
									<input type="text" readonly="readonly" value="否"/>
								</c:if>
								<c:if test="${l.type == 1}">
									<input type="text" readonly="readonly" value="是"/>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
            <div style="text-align:center;padding:5px;margin-top:15px">
                <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-redo">返回</a>
            </div>
    </div>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>