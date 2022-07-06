<%@ page language="java" 	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="requestURL"	 value="${pageContext.request.requestURL}"/>
<% 

request.setCharacterEncoding("UTF-8");

// 세션 값 가져오기 객체를 String으로 형변환
String loginResult = "";
loginResult = (String) session.getAttribute("loginResult");

/*
String customerInfo = "";
매니져의 세션 아이디를 가져온다.
customerInfo 	= (String) session.getAttribute("customerInfo");
isLogin 	= (String) session.getAttribute("loginResult"); 
${sessionScope.customerInfo.id}
*/

%>

<html>
<head>
	<meta charset="UTF-8">
	<!-- 서버 세션 아이디를 java sessionStorage에 저장한다.-->
	<script type="text/javascript">
		if("${sessionScope.userInfoId}" == "") {
			sessionStorage.clear();
		} else {
			sessionStorage.setItem("userInfoId", "${sessionScope.userInfoId}");
		}
	</script>
</head>
<body>
<div class="rentalTalk">
	<div>
		<i class="bi bi-chat-quote"></i><a id="a-link-rtTalk"  href="${contextPath}/rentaltalk/rentaltalkProcess.do">렌탈톡</a>
	</div>
	<div>
		<a onclick="sessionClear();" href="${contextPath}/user/removeAllSession.do"> 세션값 지우기 </a>
		<p> ${loginResult} </p>
	</div>
</div>

<!-- div : header-login -->
<div class="header-login header-font-size">
	<div class="d-flex flex-row bd-highlight">
		<a href="#"><i class="bi bi-download"></i>&nbsp;앱다운로드</a>&nbsp;
		<button type="button"><i class="bi bi-star-fill"></i>&nbsp;즐겨찾기		
		</button>
		<a href="/board/boardAbout.go"><i class=""></i>&nbsp;고객센터</a>		
	</div>

	<c:choose>
		<%-- 로그인 성공시  --%>
		<c:when test="${loginResult == 'access'}">
			<div id="member-form-link-logon" class="d-flex flex-row">
				<a> ${sessionScope.userInfoId}님&nbsp;&nbsp;</a>
				<a href="/user/userLogout.do" onclick="sessionClear();">로그아웃</a>
				<a href="/user/myPageMain.go">마이페이지</a>
				<a href="/goods/registerForm.go">상품 등록</a>
			</div>
		</c:when>
		<%-- 로그인 실패시 혹은 평상시  --%>
		<c:otherwise>
			<div id="member-form-link" 	class="d-flex flex-row">
				<a href="/user/loginForm.go">로그인/</a>
				<a href="/user/signUpForm.go">회원가입</a>
			</div>
		</c:otherwise>
	</c:choose>
	
</div>
<!-- div : header-login end -->


<!-- div : header-search -->
<div class="header-search">

	<div>
		<a href="${contextPath}/common/main.go">
			<img title="메인 로고" id="mainLogo" src="${contextPath}/resources/images/logo.png"/>
		</a>
	</div>
	
	<!-- form -->
	<!-- 검색바로 서버에 있는 상품 검색 -->
	<form class="search-bar-form"  action="/goods/goodsBoard.go" id="searchForm">
		<div class="dropdownBar">
			<div class="dropdownBar-search d-flex flex-row" id="barOutline">
				<input type = "hidden" name="nowPage" value="1" id="nowPage">
				<input class="search-bar"  id="searchBar" type="text" name="searchValue"
						maxlength="100" placeholder="지역명/물품명을 입력해주세요."  value="${paging.searchValue}"/>
				<button class="search-btn" id="searchBtn" type="submit"><i class="bi bi-search"></i></button>
			</div>
			<div class="dropdownBar-content">
				<a href="#"></a>
				<a href="#"></a>
				<a href="#"></a>
				<a href="#"></a>
				<a href="#"></a>
				<a href="#"></a>
				<a href="#"></a>
				<a href="#"></a>
				<a href="#"></a>
				<a href="#"></a>
			</div>
		</div>
	</form>
	<!-- form end -->
	
	<div class="header-sell header-font-size">
		
		

	
	</div>
	
</div>
<!-- div : header-search end -->

<!-- div : header-nav -->
<!-- <div class="header-nav d-flex flex-row align-items-center">
	<div class="dropdown">
		<i class="bi bi-menu-up"></i>		
		드롭다운 목록을 서버에서 불러와서 웹페이지가 바뀌지 않고 불러오기
		<div class="dropdown-content ctg-content">
		</div>
	</div>
	
	<div>
		<a href="#">테스트</a>
	</div>
</div> -->




<!-- div : header-nav -->
</body>
</html>