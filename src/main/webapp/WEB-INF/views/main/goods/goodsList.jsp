<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- import jquery, ajax -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<%-- <link rel="stylesheet" media="screen and (min-width: 800px)" href="${contextPath}/resources/layout/css/media.css?after" /> --%>
	<!-- import 레이아웃 페이지 css, js  -->
	<!-- javascript에 contextPath 추가하기 -->
	<script type="text/javascript" charset="utf-8">
		sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
		function getContextPath() {	return sessionStorage.getItem("contextpath"); }
		var ctx = getContextPath();
	</script>
	<!-- 부트스트랩 4.6 css, 아이콘, js -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" 
			integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" 
			integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
	<style>
	.main-container {
		display: 				flex;
		flex-direction: 		column;
		min-height: 			800px;
		justify-content: 		center;
    	align-items: 			center;
	}
	
	.goods-container {
		width:					1200px;
		display:				grid;
		grid-template-columns: 	repeat(auto-fill, minmax(20%, auto));
		
	}
	
	.goods-list {
		height:					80px; 				
		text-align: 			center;
	}
	
	.recommend-goods {
		width:					500px;
		height:					auto;
		display:				flex;
		flex-direction:			column;
		align-items: 			center;
		justify-content: 		center;
		align-content: 			center;
	}
	
	.recommend-goods-img {
		width: 					500px;
		height: 				500px;
	
	}
	
	.goods-img {
		width:					200px;
		height: 				200px;
	}
	
	.goods-list {
		display:				flex;
		flex-direction:			column-reverse;
		gap:					0;	
		height: 				auto;
		border: 				1px solid #000;
	}
	.goods-list * {
		margin:					0;
		width: 					100%;
	}
	
	</style>
</head>

<body>
	<!-- main-container - START  -->
	<div class="main-container">
	
		<div class="recommend-goods">
			<h1>추천 상품</h1>
			<img class="recommend-goods-img" src="${contextPath}/resources/images/logo2.png"/>
		</div>
		
		<!-- goods-container - START  -->
		<div class="goods-container">
			<c:if test="${!empty goodsList}">
				<c:forEach var="goods" items="${goodsList}">
					<div class="goods-list">
						카테고리 	: ${goods.category_ID}<br/>
						유저ID 		: ${goods.user_ID}<br/>
						지역 		: ${goods.deal_region}<br/>
						제목 		: ${goods.goods_title}<br/>
						가격 		: ${goods.goods_price}<br/>
						<c:choose>
							<c:when test="${!empty goods.goodsFileList}">
								<img width="121" height="154"
				            	src="${contextPath}/common/thumbnails.do?user_ID=${goods.user_ID}&goods_code=${goods.goodsFileList[0].goods_code}&stored_file_name=${goods.goodsFileList[0].stored_file_name}"/>
				            </c:when>
				            <c:otherwise>
				            	<img width="121" height="154" src="${contextPath}/resources/images/seoyool.jpg"/>
				            </c:otherwise>
			            </c:choose>
					</div>
				</c:forEach>
	        </c:if>
		</div>
		<!-- goods-container - END  -->
		
	</div>
	<!-- main-container - END  -->
</body>
</html>