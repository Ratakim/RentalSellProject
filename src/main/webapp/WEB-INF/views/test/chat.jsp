<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="textPath" value="${contextPath}/resources/files/chatLog"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Simple Chat</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<!-- javascript에 contextPath 추가하기 -->
	<script type="text/javascript" charset="utf-8">
		sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
		function getContextPath() {	return sessionStorage.getItem("contextpath"); }
		var ctx = getContextPath();
		
		// 채팅 로그 저장 변수
		const txtPath = '<c:out value="${textPath}"/>';
		const txtPath2 = '${textPath}';
	</script>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/websocketTest/websocket.css"/>
	<script type="text/javascript" src="${contextPath}/resources/js/websocketTest/websocket.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- import jquery, ajax -->
	<!-- slick-cdn -->
	<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
	<!-- jqueryui -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" 
	integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" 
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" 
	integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ==" 
	crossorigin="anonymous" referrerpolicy="no-referrer" />
	<!-- tempusdominus -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.0/moment.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.39.0/js/tempusdominus-bootstrap-4.min.js" 
	integrity="sha512-k6/Bkb8Fxf/c1Tkyl39yJwcOZ1P4cRrJu77p83zJjN2Z55prbFHxPs9vN7q3l3+tSMGPDdoH51AEU8Vgo1cgAA==" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.39.0/css/tempusdominus-bootstrap-4.min.css" 
	integrity="sha512-3JRrEUwaCkFUBLK1N8HehwQgu8e23jTH4np5NHOmQOobuC4ROQxFwFgBLTnhcnQRMs84muMh0PnnwXlPq5MGjg==" crossorigin="anonymous" />
	<!-- import 레이아웃 페이지 css, js  -->
	<script type="text/javascript" src="${contextPath}/resources/js/header/header.js?after"></script>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/layout/common.css?after">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/footer/footer.css?after">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/header/header.css?after">	
	
	<!-- 부트스트랩 4.6 css, 아이콘, js -->
	<link rel="stylesheet"  href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" 
			integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" 
			integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
</head>
<body>
	<!-- main-rentaltalk-container - Start -->
	<div class="main-rentaltalk-container">
	
		<!-- talkList-container - Start -->
		<div class="talkList-container">
			<div>
			
			</div>
		</div>
		<!-- talkList-container - End -->
		
		<!-- msg-container - Start -->
		<div class="msg-container">
			<div class="main-div">
				<div class="chat-top"></div>
				<div id="inAndOutChat">
					<button type="button" onclick="openSocket();">대화방 참여</button>
				    <button type="button" onclick="closeSocket();">대회방 나가기</button>
			    </div>
			    <div class="chat-title">
				   <div id="sessionName">나</div> : <div id="sender">${sessionScope.userInfoId}</div> ,<div id="receiver">receiver</div>
			    </div>
			    
			    <div id="messages">
			    	<div id="msgBottom">
			    	</div>
			    </div>
			    
			    <div class="msg-buttons">
			    	<div class="send-msg">
				  		<input type="file" id="plusFile"/>
				  		<button id="plusFileBtn" onclick="fileInput();"><i class="bi bi-plus-lg"></i></button>
				  		<div class="messageinput-div">
				        	<textarea id="messageinput" rows="4" cols="200"></textarea>
				        </div>
				        <button id="sendBtn" type="button"><i class="bi bi-arrow-left"></i></button>
				        <button type="button" onclick="javascript:clearText();"><i class="bi bi-stars"></i></button>
			        </div>
			        <div class="chat-bottom"></div>
			    </div>
		    </div>
	    </div>
	    <!-- msg-container - END -->
	</div>
	<!-- main-rentaltalk-container - END -->
</body>
</html>