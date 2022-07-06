<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"		uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<div class="borrow_list_container">
		<!-- lending-list - END -->
		<c:forEach var="goodsList" items="${goodsList}">
		<div class="borrow_list_wrap ">
				<div class="goods-title">
					<div class="borrow-list-thumnail">
					<c:choose>
						<c:when test="${goodsList.goodsFileList[0].stored_file_name != null}">
							<img width="100%" height="100%"
							src="${contextPath}/common/thumbnails.do?user_ID=${goodsList.user_ID}&goods_code=${goodsList.goodsFileList[0].goods_code}&stored_file_name=${goodsList.goodsFileList[0].stored_file_name}"/>
						</c:when>
						<c:otherwise>
							<img width="120px" height="120px" src="${contextPath}/resources/images/seoyool.jpg"/>
						</c:otherwise>
					</c:choose>
					</div>
					<div class="borrow-titleAndInform">
						<div class="borrow-title">
							${goodsList.goods_title}
						</div>
						<div class="borrow-region">
							<i class="bi bi-cursor-fill"></i>지역  <span>${goodsList.deal_region}</span>
						</div>
						<div class="borrower-id">
							렌더 닉네임  <span>${goodsList.user_ID}</span>
						</div>
					</div>
				</div>
				
				<!-- lending-list - START -->
				<c:forEach var="reserveList" items="${goodsList.reserveList}">
				<div class="borrow-list">
				
					<!-- lending-list-inform - Start -->
					<div class="borrow-list-inform">
						<div class="borrow-periodAndPrice">
							<div class="borrow-period">
								<div class="borrow-periodStart">
									예약 시작 : ${reserveList.borrow_period_start}
								</div>
								<div class="borrow-periodEnd">
									예약 종료 : ${reserveList.borrow_period_end}
								</div>
							</div>
							<div class="borrow-price">
								
								결제예정금액 : ${reserveList.total_price}원
							</div>
						</div>
					</div>
					<!-- lending-list-inform - End -->
					
						<!-- borrow-buttonList - Start -->
						<div class="borrow-buttonList">
						
						
								<c:choose>
									<c:when test="${goodsList.reserveList[0].transaction_status == '반납완료'}">		
										
										<div class="btn-source" id="accept-btn">
											<button type="submit"class="buttonList" >
												종료된 거래
											</button>
										</div>

									</c:when>
									
									<c:otherwise>
										<div class="btn-source" id="accept-btn">
											<button type="button"class="buttonList" >
												물품 사용중
											</button>
										</div>
									</c:otherwise>
								</c:choose>			
								<div class="btn-source" id="talkAbout-btn">
									<button class="buttonList" id="talkAboutButton">
										연락하기
									</button>
								</div>
						</div>
						<!-- borrow-buttonList - Start -->
					
				</div>
				</c:forEach>
				<!-- lending-list - END -->
				
			</div>
			</c:forEach>	
			<!-- lending-list - END -->
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>