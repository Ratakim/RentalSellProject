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
									<c:when test="${reserveList.transaction_status == '구매자입금중'}">		
										<div class="btn-source" id="accept-btn">
											<form action="/transaction/deposit.do" id="deposit" name="deposit">
												<input type="hidden" name="reserve_NUM" value="${goodsList.reserveList[0].reserve_NUM}">
												<button type="button"class="buttonList" data-bs-toggle="modal" data-bs-target="#exampleModal">
													입금 하기
												</button>
											</form>
										</div>
									</c:when>
									<c:otherwise>
										<div class="btn-source" id="accept-btn">
											<button type="button"class="buttonList" >
												예약 요청중
											</button>
										</div>
									</c:otherwise>
								</c:choose>	
										
								<div class="btn-source" id="cancel-btn">
									<form action="/transaction/cancelReserve.do"  id="cancelReserve" name="cancelReserve">
										<input type="hidden" name="reserve_NUM" value="${goodsList.reserveList[0].reserve_NUM}">
										<button type="submit" class="buttonList" id="cancelButton" onclick="cancelReserve(this.form)">
											예약 취소
										</button>
									</form>	
								</div>
								<div  class="btn-source" id="talkAbout-btn">
									<button class="buttonList" id="talkAboutButton">
										연락 하기
									</button>
								</div>
								<!-- Modal -->
								<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog modal-dialog-centered">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"><i class="bi bi-x"></i></button>
								      </div>
								      <div class="modal-body">
								        등록된 카드로 결제를 하시겠습니까?
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
								        <div class="btn-source" id="accept-btn">
											<form action="/transaction/deposit.do" id="deposit" name="deposit">
												<input type="hidden" name="reserve_NUM" value="${goodsList.reserveList[0].reserve_NUM}">
												<button type="submit"class="buttonList" id="depositButton" onclick="deposit(this.form)">
													입금 하기
												</button>
											</form>
										</div>
								      </div>
								    </div>
								  </div>
								</div>
								<!-- Modal End-->
						</div>
						<!-- borrow-buttonList - End -->
					
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