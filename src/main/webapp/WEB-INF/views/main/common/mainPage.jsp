<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main/common/mainPage.css?after">
	<script src="${contextPath}/resources/js/main/common/mainPage.js?after"></script>
</head>
<body>

<main>

	<div class="billy">
		<h3>Billy Us</h3>
	</div>

	<div class="main-infoFirst">
		<div class="slider">
			<input type="radio" name="slide_switch" id="id1"/>
			<label for="id1">
				<img src="${contextPath}/resources/images/main1.jpg" width="100"/>
			</label>
			<img src="${contextPath}/resources/images/main1.jpg"/>
			
			<!--Lets show the second image by default on page load-->
			<input type="radio" name="slide_switch" id="id2" checked="checked"/>
			<label for="id2">
				<img src="${contextPath}/resources/images/main2.jpg" width="100"/>
			</label>
			<img src="${contextPath}/resources/images/main2.jpg"/>
			
			<input type="radio" name="slide_switch" id="id3"/>
			<label for="id3">
				<img src="${contextPath}/resources/images/main3.jpg" width="100"/>
			</label>
			<img src="${contextPath}/resources/images/main3.jpg"/>
			
			<input type="radio" name="slide_switch" id="id4"/>
			<label for="id4">
				<img src="${contextPath}/resources/images/main4.jpg" width="100"/>
			</label>
			<img src= "${contextPath}/resources/images/main4.jpg" />
			
			<input type="radio" name="slide_switch" id="id5"/>
			<label for="id5">
				<img src="${contextPath}/resources/images/main5.jpg" width="100"/>
			</label>
			<img src="${contextPath}/resources/images/main5.jpg"/>
			<div class="landing-page">
			</div>
		</div>
	
		<!-- We will use PrefixFree - a script that takes care of CSS3 vendor prefixes
		You can download it from https://leaverou.github.com/prefixfree/ -->
		<script src="http://thecodeplayer.com/uploads/js/prefixfree.js" type="text/javascript"></script>
		
		<div class="landing-page">
			<div class="info">								
				<p>여러분의 물건들을 마음껏 빌리고 빌려주세요 내 주변 렌탈 서비스, 이웃과 함께해 가깝고 따뜻한 당신의 근처를
					만들어요 사람들과 가깝고 따뜻한 거래 지금 경험해보세요.</p>
				<button type="button" onclick="location.href='/goods/goodsBoard.go?nowPage=1&searchValue='">상품 둘러보기</button>
			</div>
			<div class="clearfix"></div>
		</div>
		
	</div>
	
	<div class="action-main">
		<div class="action-part-container">
			
			<div class="action-part">
				<div class="action-img">
					<img src="${contextPath}/resources/images/main5.jpg">
				</div>
				<div class="action-text">
					<p>모두 함께해 보세요.</p>
					<p><b>새로운 시작입니다.</b></p>
					<p>언제나 함께입니다.</p>
				</div>
			</div>
			
			<div class="action-part">
				<div class="action-img">
					<img src="${contextPath}/resources/images/main5.jpg">
				</div>
				<div class="action-text">
					<p>모두 함께해 보세요.</p>
					<p><b>새로운 시작입니다.</b></p>
					<p>언제나 함께입니다.</p>
				</div>
			</div>
			
		</div>
	</div>
	
</main>

</body>
</html>