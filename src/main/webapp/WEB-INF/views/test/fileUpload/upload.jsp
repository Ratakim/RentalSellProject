<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
String user_ID = (String) session.getAttribute("userInfoId");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style>
	body {
		display:			flex;
		flex-direction:		column;
		justify-content:	center;
		align-items:		center;
		min-height: 		800px;
	}
	.form-group {
		display: 			flex;
		flex-direction:		column;
		width:				300px;
	}
	.form-group > textarea {
		min-height: 		250px;
	}
	.form-group > select {
		text-align: 		center;	
	}
	.form-div {
		display: 			flex;
		justify-content: 	center;
		align-items: 		center;
	}
	.file-group {
		display: 			flex;
		flex-direction: 	row;
	}
	</style>
</head>

<body>
	<!-- enctype property 사용법
	application/x-www-form-urlencoded
	기본값으로, 모든 문자들은 서버로 보내기 전에 인코딩됨을 명시함.
	
	multipart/form-data
	모든 문자를 인코딩하지 않음을 명시함.
	이 방식은 <form> 요소가 파일이나 이미지를 서버로 전송할 때 주로 사용함.
	
	text/plain
	공백 문자(space)는 "+" 기호로 변환하지만, 나머지 문자는 모두 인코딩되지 않음을 명시함. -->
	<div class="form-div">
		<form action="${contextPath}/goods/registeredGoodsArr.do" method="post" enctype="multipart/form-data">
			
			<div class="form-group">
				<label for="category_ID">카테고리</label>
				<select id="category_ID" name="category_ID">
					<option value="101">모바일</option>
					<option value="102">생활가전</option>
					<option value="103">모니터/빔프로젝트</option>
					<option value="104">pc/노트북</option>
					<option value="105">게임기</option>
					<option value="106">카메라</option>
				</select>
			</div>
			
			<div class="form-group">
				<label for="user_ID">user_ID</label>
				<input type="text" id="user_ID" name="user_ID" maxlength="20" value="<%=user_ID %>" readonly="readonly"/>
			</div>
		<!-- 	<div class="form-group">
				<label for="goods_code">goods_code</label>
				<input type="number" id="goods_code" name="goods_code" maxlength="20"/>
			</div> -->
			<div class="form-group">
				<label for="goods_title">제목</label>
				<input type="text" id="goods_title" name="goods_title" maxlength="20"/>
			</div>
			<div class="form-group">
				<label for="deal_region">거래 지역</label>
				<input type="text" id="deal_region" name="deal_region" maxlength="20"/>
			</div>
			<div class="form-group">
				<label for="goods_desc">내용</label>
				<textarea id="goods_desc" name="goods_desc" maxlength="50" >내용을 입력해 주세요.</textarea>
			</div>
			<div class="form-group">
				<label for="goods_price">가격</label>
				<input type="number" id="goods_price" name="goods_price"/>
			</div>
			
			
			
			<div class="form-group" id="file-list">
				<a href="javascript:void(0);" onclick="addFile()">파일 추가</a>
				<!-- <div class="file-group">
					<input type="file" name="file"/>
					<a href="#this" name="file-delete">삭제</a>
				</div> -->
			</div>
			<button type="submit">작성하기</button>
		</form>
	</div>

	<script type="text/javascript">
		// 출처: https://to-dy.tistory.com/95 [todyDev]
		$(document).ready(function() {
			
			// Event 인터페이스의 preventDefault() 메서드는 어떤 이벤트를 명시적으로 처리하지 않은 경우, 
			// 해당 이벤트에 대한 사용자 에이전트의 기본 동작을 실행하지 않도록 지정합니다.
			/* $(".del-img-btn").on("click", (e) => {
				e.preventDefault();
				deleteFile($(this)); // this = $("a[name='file-delete']")
			});
			 */
		});
		
		function addFile() {
			// addFile을 하면 파일 div를 하나 추가한다.
			var str = "<div class='file-group'><input type='file' name='file'><button type='button' class='del-img-btn' onclick='deleteFile(this)'>삭제</button></div>";
			
			// 파일 리스트 div에 자식요소로 추가한다.
			$("#file-list").append(str);
		}
		
		function deleteFile(obj) {
			console.log(obj);
			console.log(obj.parentElement);
			obj.parentElement.remove();
		}
		
	
	</script>
	
</body>
</html>