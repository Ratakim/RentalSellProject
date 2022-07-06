<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main/user/myPage/myPageInfo.css?after"/>
	<script type="text/javascript" src="${contextPath}/resources/js/main/user/myPage/myPageInfo.js?after"></script>
	<title>Insert title here</title>
</head>

<body>
	<div class="userInfo-container">
	
		<!-- Modal -->
		<div class="modal">
			<div class="modal-body">
				<form id="deleteForm" name="deleteForm" method="post" action="/user/deleteCustomer.do">
					<div class="deleteForm-main">
						<div>
							<label>아이디</label>
							<input type="text" id="deleteId" name="id" value="${userInfo.id}" readonly/>
						</div>
						<div>
							<label>패스워드</label>
							<input type="password" id="deletePwd" name="pwd" placeholder=" 패스워드를 입력해 주세요."/>
						</div>
						<!-- <div>
							<label>패스워드 확인</label>
							<input type="password" id="deletePwdCheck" name="pwdCheck" placeholder="동일한 패스워드를 입력해 주세요."/>
						</div> -->
						<!-- <div class="security-code">
							<div>
								<label>보안 코드 입력</label>
								<input type="text" id="securityCode" name="securityCode" placeholder="6자리 입력"/>
								<button type="button" id="securityReqBtn" name="securityReqBtn" onclick="requestCodeAjax();">보안 코드 요청</button>
							</div>
							<div>
								<label class="timer-display"></label>
								<button type="button" id="okBtn" name="okBtn">확인</button>
								<label id="codeesult"></label>
							</div>
						</div> -->
						<div>
							<label>탈퇴 확인</label>
							<input type="text" id="deleteFinal" name="deleteFinal" placeholder="'탈퇴확인'을 정확하게 입력해주세요."/>
						</div>
						<div class="submit-btn">
							<button type="submit" >확인</button>
							<button type="button" onclick="">취소</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- Modal -->
	
		<!-- info-main -->
		<div class="info-main">
		
			<form name="myInfoForm" method="post" action="">
				<div class="form-main">
					<div>
						<label><i class="bi bi-postcard-heart-fill"></i> 아이디</label>
						<input type="text" id="id" name="id" value="${userInfo.id}" readonly/>
					</div>
					<div class="pwd-div">
						<label><i class="bi bi-incognito"></i> 패스워드</label>
						<div class="pwd-bar">
							<input type="password" id="pwd" name="pwd" value="${userInfo.pwd}"/>
							<button type="button" id="pwdBtn" name="pwdBtn">
								<i class="bi bi-eye-slash"></i>
								<i class="bi bi-eye"></i>
							</button>
						</div>
					</div>
					<div>
						<label><i class="bi bi-person"></i> 이름</label>
						<input type="text" id="name" name="name" value="${userInfo.name}" readonly/>
					</div>
					<div>
						<label><i class="bi bi-envelope"></i> 이메일</label>
						<input type="email" id="email" name="email" value="${userInfo.email}" />
					</div>
					<div>
						<label><i class="bi bi-gender-ambiguous"></i> 성별</label>
						<input type="text" id="gender" name="gender" value="${userInfo.gender}" readonly/>
					</div>
					<div>
						<label><i class="bi bi-calendar"></i> 가입일</label>
						<input type="date" id="joinDate" name="joinDate" value="${userInfo.join_date}" readonly/>
					</div>
					<div>
						<label><i class="bi bi-calendar-check"></i> 마지막 접속일</label>
						<input type="date" id="lastJoin" name="lastJoin" value="${userInfo.last_join}" readonly/>
					</div>
					
					<div class="info-change-btn">
						<!-- onclick으로  href만들기 -->
						<!-- onclick="location.href='${contextPath}/billyus/updateCustomer.do';" -->
						<button id="updateBtn" class="btn btn-primary" type="button" onclick="submitLink(this.form, '/user/updateUser.do');"><i class="bi bi-person-lines-fill"></i> 회원 정보 수정</button>
						<button class="btn btn-warning btn-open-popup" type="button"><i class="bi bi-person-dash-fill"></i> 회원 탈퇴</button>
						
					</div>
				</div>
			</form>
		
		</div>
		<!-- info-main -->
	
	</div> <!-- END -->
</body>
</html>