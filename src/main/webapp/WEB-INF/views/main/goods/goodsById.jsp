<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main/goods/goodsById.css?after">
	<script type="text/javascript" src="${contextPath}/resources/js/main/goods/goodsById.js?after"></script>
	<title>Insert title here</title>
</head>
<body>
	<div id = "article-content">
		<form action="/transaction/reservation.do" id="reservation" name="reservation">
			<main id="goodsById">
				<div class="article-image">
					<div class="slider">
						<c:choose >
							<c:when test="${!empty goodsByIdVO.goodsFileList[0].stored_file_name}">
								<c:forEach var="files" items="${goodsByIdVO.goodsFileList}">
									<div class="item">
										<img src="${contextPath}/common/thumbnails.do?user_ID=${goodsByIdVO.user_ID}&goods_code=${goodsByIdVO.goods_code}&stored_file_name=${files.stored_file_name}"/>
									</div>	
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div class="item">
									<img src="${contextPath}/resources/images/logo.png?after"/>
								</div>
								<div class="item">
									<img src="${contextPath}/resources/images/seoyool.jpg?after"/>
								</div>
								<div class="item">
									<img src="${contextPath}/resources/images/seoyool.jpg?after"/>
								</div>
								<div class="item">
									<img src="${contextPath}/resources/images/seoyool.jpg?after"/>
								</div>
							</c:otherwise>
						</c:choose>

					</div>
					<div class="slick-arrow slick-prev">
						<i class="bi bi-chevron-left"></i>
					</div>
					<div class="slick-arrow slick-next">
						<i class="bi bi-chevron-right"></i>
					</div>
				</div>
				
				<div id="article-profile">
						<div id="contentL">
							<div id="goods-title">
							${goodsByIdVO.goods_title}
							</div>
							<div class="goods-price">
								<div id="hiddenPrice">${goodsByIdVO.goods_price}</div>
								<div id="pricePerHour" >
								${goodsByIdVO.goods_price_comma}
								<span>원/시간</span>
								</div>
								<div id="pricePerDay">
								${goodsByIdVO.goods_price_comma}
								<span>일/시간</span>
								</div>
							</div>
						</div>
						<div id="contentS">
							<div class="otherSub">
								<div id="contetHit">
									<i class="bi bi-eye-fill"></i>
									${goodsByIdVO.goods_hit}
								</div>
								<div id="createdDate">
									<i class="bi bi-clock-fill"></i>
									${goodsByIdVO.create_date}
								</div>
							</div>
							<div class="ctgAndRegion">
								<div id="goods-category">
									<span>렌&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;더 &nbsp;    </span>
									<i class="bi bi-emoji-smile"></i>&nbsp;
									${goodsByIdVO.user_ID}
								</div>
								<div id="goods-category">
									<span>카테고리 &nbsp;     </span>
									<i class="bi bi-card-checklist"></i>
									${getGoodsCtgNM.category_NM}
								</div>
								<div id="deal-region">
									<span>거래지역 &nbsp;    </span>
									<i class="bi bi-geo-alt-fill"></i>
									${goodsByIdVO.deal_region}
								</div>
							</div>
						</div>
				</div>
				
				<div id="article-desc">
					<div id="goods-desc">
						${goodsByIdVO.goods_desc}
					</div>
				</div>
			</main>
			<footer class="rm-registButton">
				<div class="rb-panel">
					<div class="col-md-6 d-flex flex-row">
						<input type="text" class="form-control datetimepicker-input" id="datetimepicker5" data-toggle="datetimepicker" data-target="#datetimepicker5" onchange='calcuratorDate()'/>
						<input type="hidden" id="borrow_period_start" name="borrow_period_start">
						<div class="input-group-append" data-target="#datetimepicker5" data-toggle="datetimepicker">
	                        <div class="input-group-text"><i class="bi bi-calendar-date"></i></div>
	                    </div>
	                    
						<input type="text" class="form-control datetimepicker-input" id="datetimepicker6" data-toggle="datetimepicker" data-target="#datetimepicker6" onchange='calcuratorDate()'/>
						<input type="hidden" id="borrow_period_end" name="borrow_period_end">
						<div class="input-group-append" data-target="#datetimepicker6" data-toggle="datetimepicker">
	                        <div class="input-group-text"><i class="bi bi-calendar-date"></i></div>
	                    </div>
					</div>
					<div class="hourAndPrice">
						<div id="hour"></div>	
						<div id="wholePrice"></div>
						<input type="hidden" class="totalPrice" id="totalPrice" name="total_price">
					</div>
					<div>
						<div>
						<input type="hidden" name="goods_code" value="${goodsByIdVO.goods_code}">
						</div>
						<button type="button" class="rb-send" onclick="talkAbout(this.form)">
						</button>
						<button type="submit" class="rb-main" onclick="checkForm(this.form)">
						</button>
					</div>	
				</div>
			</footer>
		</form>
	</div>
</body>
</html>