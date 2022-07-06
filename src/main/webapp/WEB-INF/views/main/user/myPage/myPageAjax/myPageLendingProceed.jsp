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
	<div class="lending_list_container">
		
		<!-- lending-list - END -->
		<c:forEach var="goodsList" items="${goodsList}">
		<div class="lending_list_wrap">
				<div class="goods-title">
					<div class="lending-list-thumnail">
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
					<div class="lending-title">
						${goodsList.goods_title}
					</div>
				</div>
				
				<!-- lending-list - START -->
				<c:forEach var="reserveList" items="${goodsList.reserveList}">
				<div class="lending-list">
				
					<!-- lending-list-inform - Start -->
					<div class="lending-list-inform">
						<div class="lending-periodAndPrice">
							<div class="lending-period">
								<div class="borrower-id">
									예약자 닉네임 : ${reserveList.user_ID}님
								</div>
								<div class="lending-periodStart">
									예약 시작 : ${reserveList.borrow_period_start}
								</div>
								<div class="lending-periodEnd">
									예약 종료 : ${reserveList.borrow_period_end}
								</div>
							</div>
							<div class="lending-price">
								
								결제예정금액 : ${reserveList.total_price}원
							</div>
						</div>
					</div>
					<!-- lending-list-inform - End -->
					
					<!-- lending-buttonList - Start -->
					<div class="lending-buttonList">
							<c:choose>
								<c:when test="${reserveList.transaction_status == '제품인수완료'}">
									<div class="btn-source" id="accept-btn">
										<form action="/transaction/returned.do" id="returned" name="returned">
										<input type="hidden" name="reserve_NUM" value="${reserveList.reserve_NUM}"/>
											<button type="submit" class="buttonList" id="acceptButton" onclick="returned(this.form)">
												반납 받기
											</button>
										</form>
									</div>
								</c:when>
								<c:otherwise>	
									<div  class="btn-source" id="accept-btn">
										<button type="button" class="buttonList" >
											예약 날짜에  물건인계
										</button>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="btn-source" id="talkAbout-btn">
								<button class="buttonList" id="talkAboutButton">
									연락 하기
								</button>
							</div>
					</div>
					<!-- lending-buttonList - END -->
					
				</div>
				</c:forEach>
				<!-- lending-list - END -->
				
			</div>
			</c:forEach>	
			<!-- lending-list - END -->
	</div>
	
</body>
</html>