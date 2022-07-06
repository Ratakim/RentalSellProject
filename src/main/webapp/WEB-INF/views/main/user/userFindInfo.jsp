<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main/user/userFindInfo.css?after" />
	<script type="text/javascript" src="${contextPath}/resources/js/main/user/userFindInfo.js?after"></script>
</head>

<body>
	<div id="find-userinfo-container">
		<div class="main-logo">
			<a  class="main-logo" href="${contextPath}/common/main.go">
				<img class="main-logo" title="메인 로고" id="mainLogo" src="${contextPath}/resources/images/logo2.png"/>
			</a>
		</div>
		<!-- form - Start -->
		<form class="d-flex flex-column" action="${contextPath}/find/userInfo" method="post">
			
			<!-- c:if - END -->	
			<c:if test="${param.checkType != 'pwdSuccess'}">
			
				<c:choose>
					<%-- id 검사 --%>
					<c:when test="${param.checkType == 'id'}">
						<div>
							<label for="#name">이  름</label><input type="text" id="name" name="name" style="ime-mode:	active;" required="required"/>
						</div>
						<div>
							<label for="#email">이메일</label><input type="email" id="email" name="email" required="required"/>
						</div>
						<div class="d-flex flex-row">
							<button type="submit">아이디 찾기</button>
							<button type="button" onclick="location.href = ctx + '/common/main.go';">메인으로 이동</button>
						</div>
						<div id="findPwd">
							<a href="#" onclick="aLinkPost('/find/userInfo?checkType=pwd')">비밀번호 찾기</a>
						</div>
					</c:when>
					<%-- 패스워드 검사 --%>
					<c:otherwise>
						<div>
							<label for="#id">아이디</label><input type="text" id="id" name="id"  required="required"/>
						</div>
						<div>
							<label for="#name">이  름</label><input type="text" id="name" name="name" style="ime-mode:	active;" required="required"/>
						</div>
						<div>
							<label for="#email">이메일</label><input type="email" id="email" name="email" required="required"/>
						</div>
						<div class="d-flex flex-row">
							<button type="submit">패스워드 확인하기</button>
							<button type="button" onclick="location.href = ctx + '/common/main.go';">메인으로 이동</button>
						</div>
						<div id="findId">
							<a href="#" onclick="aLinkPost('/find/userInfo?checkType=id')">아이디 찾기</a>
						</div>
					</c:otherwise>
				</c:choose>
			</c:if>
			<!-- c:if - END -->
			
			<div class="warning-msg">
				<c:choose>
					<%-- 아이디 확인 성공 메시지 --%>
					<c:when test="${checkResult == 'idSuccess'}">
						<span class="success">
							입력하신 정보에 맞는 아이디를 이메일로 발송했습니다.<br/>
							${userInfoVO.email} 메일을 확인해 주세요.
						</span>
					</c:when>
					<%-- 패스워드 확인 성공 메시지 --%>
					<c:when test="${checkResult == 'pwdSuccess'}">
						<span class="success">
							비밀번호 재설정을 위한 메일을 발송했습니다. <br/>
							${userInfoVO.email} 메일을 확인해 주세요.
						</span>
					</c:when>
					<c:when test="${checkResult == 'resetPwdFail'}">
						<span class="warning">비밀번호 변경에 실패했습니다. 고객센터에 문의해 주세요.</span>
					</c:when>
					<%-- 패스워드 변경 창 --%>
					<c:when test="${checkResult == 'pwdUpdate'}">
						<input type="hidden" name="id" value="${userInfoVO.id}"/>
						<div class="d-flex flex-column">
							<label 	for="password">패스워드</label>
							<input 	type="password" id="password" name="password" placeholder="password" required="required"/>
							<span  	class="warning-pwd"></span>
						</div>
						<div class="d-flex flex-column">
							<label 	for="passwordCheck">패스워드 확인</label>
							<input 	type="password" id="passwordCheck" placeholder="password 확인" required="required"/>
							<span 	class="warning-pwdcheck"></span>
						</div>
						<div class="d-flex flex-row">
							<button type="button"  	id="resetPwd">비밀번호 변경하기</button>
							<button type="button" 	onclick="location.href = ctx + '/common/main.go';">메인으로 이동</button>
						</div>
						<span class="warning">비밀번호 재설정을 위해 비밀번호를 수정해 주세요.</span>
					</c:when>
					<%-- 해당 정보 경고 메시지 --%>
					<c:when test="${checkResult == 'noInfo'}">
						<span class="warning">입력하신 정보에 맞는 회원 정보가 존재하지 않습니다.</span>
					</c:when>
					<c:when test="${checkResult == 'resetPwdFail'}">
						<span class="warning">비밀번호 변경에 실패했습니다. 고객센터에 문의해 주세요.</span>
					</c:when>
					<c:when test="${checkResult == 'resetPwdSuccess'}">
						<span class="warning">비밀번호 변경에 성공했습니다. 다시 로그인 해주세요</span>
						<a href="${contextPath}/user/loginForm.go">로그인 하러가기</a>
					</c:when>
					<%-- 입력 요청 메시지 --%>
					<c:otherwise>
						<span class="normal">회원 정보 찾기에 필요한 정보를 입력해주세요.</span>
					</c:otherwise>
				</c:choose>
				<input type="hidden" id="checkType" name="checkType" value="${param.checkType}"/>
			</div>
		</form>
		<!-- form - END -->
		
		
	</div>
	
</body>
</html>