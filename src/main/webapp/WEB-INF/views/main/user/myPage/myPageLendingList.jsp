<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main/user/myPage/myPageLendingList.css?after">
	<script type="text/javascript" src="${contextPath}/resources/js/main/myPage/myPageContainer.js?after"></script>
	<title>Insert title here</title>

</head>
<body>
	<div class="main-container">
		<div class="lendingList">
			<div class="list_tab">
				
				
				<div class="tab_source" id="tab_regist">
					<a href="#" class="tab_link"  id="registedButton">
						<div class="tab_content">
							<div class="tab_title">
								등록된 상품
							</div>
							<div class="tab_count">
								${registCount}
							</div>
						</div>
					</a>
				</div>
				<div class="tab_source" id="tab_request">
					<a href="#" class="tab_link"  id="requestButton">
						<div class="tab_content">
							<div class="tab_title">
								예약 승인중
							</div>
							<div class="tab_count">
								${requestCount}
							</div>
						</div>
					</a>
				</div>
				<div class="tab_source" id="tab_proceed">
					<a href="#" class="tab_link"  id="proceedButton">
						<div class="tab_content">
							<div class="tab_title">
								빌려주기 진행중
							</div>
							<div class="tab_count">
							${proceedCount}
							</div>
						</div>
					</a>
				</div>
				<div class="tab_source" id="tab_ended">
					<a href="#" class="tab_link"  id="endTranButton">
						<div class="tab_content">
							<div class="tab_title">
								종료된 거래
							</div>
							<div class="tab_count">
							${endedCount}
							</div>
						</div>
					</a>
				</div>
				
			</div>
			<div id="parse_area">
			
			</div>
			<div class="period_tab">
			</div>
		</div>
	</div>
</body>
</html>