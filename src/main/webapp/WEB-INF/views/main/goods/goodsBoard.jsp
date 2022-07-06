<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main/goods/goodsBoard.css?after">
	<script type="text/javascript" src="${contextPath}/resources/js/main/goods/goodsBoard.js?after"></script>
	<title>goodsBoard.jsp</title>
</head>
<body>
	<div class = "goodsBoard" >	
		<form class="moveForm" action="/goods/goodsBoard.go?">
		<input class="search-bar"  type="hidden" name="searchValue"
						maxlength="100" placeholder="지역명/물품명을 입력해주세요."  value="${paging.searchValue}"/>
		</form>
		
		<!-- DIV : goodsList-container - START -->
		<div class = "goodsList-container">
		
			<!-- DIV : goodsList-wrap - START -->
			<div id = "goodsList-wrap">

				<c:forEach var="goodsList" items="${goodsList}">
			
					<div class="goodsList-card">
						<a class="goodsList-link" href="/goods/goodsById.go?goods_code=${goodsList.goods_code}">
						
							<!-- 상품 이미지 -->
							<div class=".goods-thumnail">
								<c:choose>
									<c:when test="${!empty goodsList.goodsFileList[0].stored_file_name}">
										<img width="217" height="150" alt="goodsLogo"
						            	src="${contextPath}/common/thumbnails.do?user_ID=${goodsList.user_ID}&goods_code=${goodsList.goodsFileList[0].goods_code}&stored_file_name=${goodsList.goodsFileList[0].stored_file_name}"/>
						            </c:when>
						            <c:otherwise>
						            	<img width="217" height="150" alt="goodsLogo" 
						            	src="${contextPath}/resources/images/seoyool.jpg"/>
						            </c:otherwise>
								</c:choose>
							</div>
							
							<!-- 상품 내용 -->
							<div class="goods-information">
								<div class="goods-title">
									${goodsList.goods_title}
								</div>
								<div class="goods-priceAndEtc">
									<div class="goods-pricePerHour">
										${goodsList.goods_price_comma}
									</div>
								</div>
								<div class="goods-regionAndEtc">
									<div class="goods-region">
										${goodsList.deal_region}
									</div>
									<div class="goods-viewCnt">
										<i class="bi bi-eye-fill"></i>
										${goodsList.goods_hit}
									</div>
								</div>
							</div>
							
						</a>
					</div>
					
				</c:forEach>
				
			</div>
			<!-- DIV : goodsList-wrap - END -->
			
			<!-- DIV : pageNum-button - START -->
			<div id = "pageNum-button">
			
				<div class = "pageNum-wrap">
					<!-- 이전 페이지 가기 -->
					<c:if test="${paging.startPage != 1}">
						<a href="/goods/goodsBoard.go?nowPage=${paging.startPage - 1}&searchValue=${paging.searchValue}" class="pageButton">&lt;</a>
					</c:if>
					
					<!-- 페이지 넘버 -->
					<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
						<c:choose>
							<c:when test="${p == paging.nowPage}">
								<b  class="pageButton">${p}</b>
							</c:when>
							<c:when test="${p != paging.nowPage}">
								<a href="/goods/goodsBoard.go?nowPage=${p}&searchValue=${paging.searchValue}"  class="pageButton">${p}</a>
							</c:when>
						</c:choose>
					</c:forEach>
					
					<!-- 다음 페이지 가기 -->
					<c:if test="${paging.endPage != paging.lastPage}">
						<a href="/goods/goodsBoard.go?nowPage=${paging.endPage+1}&searchValue=${paging.searchValue}"  class="pageButton">&gt;</a>
					</c:if>
					
				</div>
			</div>
			<!-- DIV : pageNum-button - END -->
			
		</div>
		<!-- DIV : goodsList-container - END -->
		
	</div>
</body>
</html>