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
		<div class="lending_list_wrap">
			<c:forEach var="goodsList" items="${goodsList}">
			<div class="lending-list">
				<div class="lending-list-thumnail">
					<c:choose>
					<c:when test="${goodsList.goodsFileList[0].stored_file_name != null}">
						<img width="120px" height="120px"
						src="${contextPath}/common/thumbnails.do?user_ID=${goodsList.user_ID}&goods_code=${goodsList.goodsFileList[0].goods_code}&stored_file_name=${goodsList.goodsFileList[0].stored_file_name}"/>
					</c:when>
					<c:otherwise>
						<img width="100%" height="120px" src="${contextPath}/resources/images/seoyool.jpg"/>
					</c:otherwise>
					</c:choose>
				</div>
				<div class="lending-list-inform">
					<div class="lending-title">
					${goodsList.goods_title}
					</div>
					<div class="lending-periodAndPrice">
						<div class="lending-period">
						</div>
						<div class="lending-price">
						${goodsList.goods_price_comma} 원/시간
						</div>
					</div>
				</div>
				<div class="lending-buttonList">
					<div class="btn-source" id="accept-btn">
						<button class="buttonList" id="acceptButton">
							수정 하기
						</button>
					</div>
					<div class="btn-source" id="cancel-btn">
						<button class="buttonList" id="cancelButton">
							등록 취소
						</button>
					</div>
					<div class="btn-source" id="talkAbout-btn">
						<button class="buttonList" id="talkAboutButton">
							연락 하기
						</button>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>