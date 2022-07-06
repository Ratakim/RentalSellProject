<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta charset="UTF-8">
</head>


<footer id="footer" class="footer">	
	<div>
		<img width="120px" height="80px"src="${contextPath}/resources/images/logo.png" >
	</div>
	<ul class="footer-list">
		<li>
		    <a  href="${contextPath}/common/termsOfUse.go" 	style="color: black;">이용약관</a>&nbsp;
	        <a  href="${contextPath}/common/perInfo.go" 	style="color: black;">개인정보처리방침</a>&nbsp;
	        <a  href="${contextPath}/common/locService.go" 	style="color: black;">위치기반서비스 이용약관</a>&nbsp;
        </li>
        <li>
			COMPANY (주)빌리어스 OWNER
			김땡땡
		</li>
		<li>
			BUSINESS LICENSE 344-87-02141 ONLINE-LICENSE
			제2021-진건퇴계원-333호 ADDRESS 서울시 마포구 양화로33길 33 푸른숲서교빌딩 3층
		</li>
		<li>
			TEL 02-333-3333 FAX 02-333-3333 E-MAIL
			 333333@hanmail.net
		</li>
	</ul>
	
</footer>


</html>
