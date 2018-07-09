<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"		uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"	uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"	uri="http://www.springframework.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="basePath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${ctx}" />


<%-- Error Messages --%>
<c:if test="${flash.success != null}">
	<div class="flash_success">
		${flash.success}<br/>
	</div>    
</c:if>

<%-- Info Messages --%>
<c:if test="${flash.error != null}">
	<div class="flash_error">
		${flash.error}<br/>
	</div> 
</c:if>
