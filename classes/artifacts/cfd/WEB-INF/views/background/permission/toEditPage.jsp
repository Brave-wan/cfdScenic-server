<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views-commons/header.jsp" %>
<style type="text/css">
    body {
        font-family: "微软雅黑";
    }

    table {
        width: 90%;
        margin: 20px auto;
    }

    table td {
        height: 45px;
        text-align: left;
    }

    table td:first-child {
        width: 10%;
    }

    table td:last-child {
        width: 90%;
    }

    table td input[type=text] {
        width: 80%;
        height: 30px;
        line-height: 30px;
        padding-left: 10px;
    }

    table img {
        width: 150px;
        height: 100px;
    }

    table td textarea {
        padding-left: 10px;
    }

    table td label {
        float: left;
        width: 100px;
        text-align: left;
    }

    .l-btn-text {
        line-height: 30px;
    }

    .easyui-linkbutton {
        width: 30%;
        height: 30px;
        display: inline-block;
    }
</style>
<div style="width:98.5%;padding:10px;">
    <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:权限设置-修改权限</div>
    <form id="ff" method="post" action="${basePath}/background/permiss/insert">
        您当前修改的是：${name}
        <input type="hidden" value="${brid }" name="brid"/>
        <input type="hidden" value="${role }" id="mid">
        <input type="hidden" value="${roleid }" id="id" name="id">
        <table>
				<input type="hidden" value="${basePath}/background/permiss/insert"
					id="url">
				<c:forEach items="${list}" var="l" >
					<c:if test="${l.BMPARENTID ==NULL}">
						<tr id="tr">
					</c:if>
					<td style="width: 30px;"><input type="checkbox" name="ms" value="${l.BMID}"
						class="ms">${l.BMNAME} </td>
			</c:forEach>

		</table>
        <div style="text-align:center;padding:5px">
            <a id="showPageSaveBtn" href="javascript:void(0)" onclick="save()" class="easyui-linkbutton"
               iconCls="icon-save">确定</a><!-- onclick="save()" type="submit"--> 
            <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton"
               iconCls="icon-cancel">取消</a>
        </div>
    </form>
</div>

<script type="text/javascript">
    function save() {
    	var url1=$("#url").val();
   	$.post(url1, $("#ff").serialize()).success(function(data){
		if (data.success) {
			alert("保存成功！");
			location.reload();
		} else {
			eu.showMsg(data);
		}
	}).error(function(ex){
		$.messager.progress('close');
		eu.showMsg("系统错误！");
	});
    }
$(function (){
	var m = $("#mid").val().split(",");
	var ms = $(".ms").val();
	for (var int = 0; int <m.length ; int++) {
		$(".ms").each(function(){
				if($(this).val()==m[int]){
					$(this).attr("checked",'true');
				}
			});
	}
	$(".ms").each(function(){
		$(this).prepend("</tr>");
	});
	
});
</script>
<%@ include file="/WEB-INF/views-commons/footer.jsp" %>