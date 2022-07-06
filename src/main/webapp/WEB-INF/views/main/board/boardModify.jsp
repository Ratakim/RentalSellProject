<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main/board/boardModify.css">
	<meta charset="UTF-8">
	<title>boardModify</title>		
</head>
<body>
<form method="post" action="/board/boardModify.go">

     <h1><br/><a>게시글 수정</a></h1>
         
		<table class="tt">		
		<tr>
			<th><span class="badge rounded-pill bg-light">게시판 선택</span></th>
			<td>
				<select name="board_Type">
				    <c:if test="${userInfoId != null}">
				    <option value="공지">공지사항</option>					
					<option value="자주">자주묻는질문</option>
					</c:if>
					<option value="1">1:1문의</option>
					<option value="사기">사기피해정보공유</option>
				</select>
				</td>				
		</tr>
		</table>

        <div class="title">       
		<span class="badge rounded-pill bg-light">제목</span>				
			<input name="title" id="title" placeholder="제목을 입력하세요">
		</div>

       <br>


         <div class="content">		
		<textarea id="content" name="content" rows="5" cols="80" placeholder="내용을 입력하세요"></textarea>
         </div>
         <br>


		<div class="button">
			<button class="btn btn-dark" type="submit">작성하기</button> 
			<button class="btn btn-dark" type="reset">취소</button>
	   </div>
	   <br>
	   
		
	

</form>
</body>
</html>