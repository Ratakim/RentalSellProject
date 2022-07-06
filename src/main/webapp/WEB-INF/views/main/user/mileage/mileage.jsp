<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myPageMain</title>
</head>
<style>

.main {
	background-color: lightgreen;
	position:		relative;
	width: 780px;
	height: 700px;
}
.main div{
	background-color: yellow;
	height: 12%;
	text-align: left;
}
.my {
	text-align: left;
}
</style>
<body>
	<div class="main">
		<h1><br/><a href="#">마일리지 페이지</a></h1>
		<h2 class="my">나의 마일리지 : <span>ooooo원</span></h2>
		<hr>
		<div><br><a href="/user/mileageList.go">마일리지 사용내역</a></div>
		
		<div><br><a href="/user/mileageCharge.go">마일리지 충전 신청</a></div>
		
		<div><br><a href="/user/mileageWithdraw.go">마일리지 출금 신청</a></div>
	</div>
</body>
</html>