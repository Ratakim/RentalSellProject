<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main/user/myPage/myPageBorrowList.css?after">
	<script type="text/javascript" src="${contextPath}/resources/js/main/myPage/myPageContainer.js?after"></script>
	<title>Insert title here</title>
</head>
<body>
	<div class="main-container">
		<div class="borrowList">
			<div class="list_tab">
				
				<div class="tab_source" id="tab_request">
					<a href="#" class="tab_link" id="requestButtonB">
						<div class="tab_content">
							<div class="tab_title">
								예약 요청중
							</div>
							<div class="tab_count">
							${requestCount}
							</div>
						</div>
					</a>
				</div>
				<div class="tab_source" id="tab_proceed">
					<a href="#" class="tab_link" id="proceedButtonB">
						<div class="tab_content">
							<div class="tab_title">
								빌리기 진행중
							</div>
							<div class="tab_count">
							${proceedCount}
							</div>
						</div>
					</a>
				</div>
				<div class="tab_source" id="tab_ended">
					<a href="#" class="tab_link" id="endTranButtonB">
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