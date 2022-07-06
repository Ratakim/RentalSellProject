<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% 

request.setCharacterEncoding("UTF-8");

String userInfoId 	= (String) session.getAttribute("userInfoId");

%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main/user/myPage/myPageMain.css?after"/>
<meta charset="UTF-8">
<title>myPageMain</title>
<style>
</style>
</head>
<body>
	<div class="main">
	<br>
		<h1>마이 페이지</h1>
		<hr>
		
		<div><br>
			<span id="main2"> ${userInfoId} 님 환영합니다.</span>
			<hr>
			
			
		</div>

		<div class="main3">
			<br>
			<h5>인증현황</h5>
			<br>
			<c:choose>
				<c:when test="${resultAccount == 1}">
					<a>계좌 등록</a> : <span>등록완료</span>
				</c:when>
				<c:otherwise>
					<a href="/user/myPageCertify.go">계좌 등록</a> : <span>미등록</span>

				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${resultCard == 1}">
					<a>카드 등록</a>: <span>등록완료</span>
				</c:when>
				<c:otherwise>
					<a href="/user/myPageCard.go">카드 등록</a>: <span>미등록</span>
				</c:otherwise>
			</c:choose>
		</div>

		<div class="main4">
			<br>
			<h5>거래현황</h5>
			<br> 
			<span> 빌 리 기 :<span>0건</span>
			</span> 
			<span> 빌려주기 :<span>0건</span> 
			<br>
			</span>
			<span> 진 행 중 :<span>0건</span>
			</span> 
			<span> 완 료 :<span>0건</span>
			</span>

		</div>



	</div>
</body>
</html>