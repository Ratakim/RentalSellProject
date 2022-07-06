<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c"	  uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% 
request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/css/main/user/loginForm.css?after"/>
	<script type="text/javascript" src="${contextPath}/resources/js/main/user/loginForm.js?after" ></script>
</head>

<body>

	<div id="login-body-1">
		<div class="main-logo">
			<a  class="main-logo" href="${contextPath}/common/main.go">
				<img class="main-logo" title="메인 로고" id="mainLogo" src="${contextPath}/resources/images/logo2.png"/>
			</a>
		</div>
		<form method="post"  action="${contextPath}/user/userLogin.do">
			<div class="login-main">
				<div class="form-group">
					<label>아이디</label>
					<input type="text" id="id" name="id" placeholder="아이디를 입력하세요." />
				</div>
				
				<div class="form-group">
					<span class="warning">
						<c:if test="${param.loginResult}">
							아이디가 다릅니다.
						</c:if>
					</span>
				</div>
				
				<div class="form-group">
					<label>패스워드</label>
					<input type="password" id="pwd" name="pwd" placeholder="비밀번호를 입력하세요"/>
				</div>
				
				<div class="form-group">
					<span class="warning">
						<c:if test="${param.loginResult}">
							패스워드가 다릅니다.
						</c:if>
					</span>
				</div>
			</div>
			<div class="login-btns">
				<button type="submit" >로그인</button>
				<button type="button" onclick="history.back();" >취소</button>
				<!-- <label>아이디 기억하기</label>
				<input type="checkbox" id="saveId" name="saveId"/> -->
			</div>
			<!-- 	${param.name}은 
					${request.getParameter("name")} 와 같다 	-->
			<div>
				<span class="warning">
					<c:choose>
						<c:when test="${param.loginResult == 'idDenied'}">
							존재하지 않은 아이디입니다.
						</c:when>
						<c:when test="${param.loginResult == 'pwdDenied'}">
							비밀번호가 다릅니다.
						</c:when>
						<c:otherwise>
							아이디와 비밀번호를 입력해주세요.
						</c:otherwise>
					</c:choose>
				</span>
			</div>
			
			<div>
				<div id="findId"><a href="#" onclick="aLinkPost('/find/userInfo?checkType=id')">아이디 찾기</a></div>
				<div id="findPwd"><a href="#" onclick="aLinkPost('/find/userInfo?checkType=pwd')">비밀번호 찾기</a></div>
			</div>
			<!-- <div id="scriptTest"></div> -->
			
		</form>
		
	</div>
</body>
</html>