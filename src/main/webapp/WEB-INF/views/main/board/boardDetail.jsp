<%@page import="org.apache.velocity.tools.view.WebappUberspector.GetAttributeExecutor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% 

request.setCharacterEncoding("UTF-8");

String userInfoId 	= (String) session.getAttribute("userInfoId");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main/board/boardDetail.css">
<meta charset="UTF-8">
<title>Detail</title>
</head>

<body>


		<h1><br/><a>고객센터</a></h1>
                  
            <div class="selectbutton">
			<a href="${contextPath}/board/boardAbout.go?val=공지"><button type="button" class="btn btn-dark">공지사항</button></a>
			<a href="${contextPath}/board/boardAbout.go?val=1"><button type="button" class="btn btn-dark">1:1 문의</button></a>
			<a href="${contextPath}/board/boardAbout.go?val=자주"><button type="button" class="btn btn-dark">자주 묻는 질문</button></a>
			<a href="${contextPath}/board/boardAbout.go?val=사기"><button type="button" class="btn btn-dark">사기 피해 공유</button></a>
		    </div>
     <br>
    <div class="content">
	<div class="card border-secondary mb-3" style="width: 600px;">
		<div class="card-header">${boardDetail.id}</div>
		<div class="card-body">
			<h5 class="card-title">${boardDetail.title}</h5>
			<p class="card-text">${boardDetail.content}</p>
			${boardDetail.write_Date}
		</div>
	 </div>
	</div>

		<c:if test="${boardDetail.id == userInfoId}">
			<div class="mdbutton">
				<button type="button" class="btn btn-outline-dark btn-sm"
					onclick="location.href ='/board/boardModify.go?article_NO=${boardDetail.article_NO}'">게시물
					수정</button>
				<button type="button" class="btn btn-outline-dark btn-sm"
					onclick="location.href ='/board/boardDelete.go?article_NO=${boardDetail.article_NO}'">게시물
					삭제</button>
			</div>
		</c:if>
	



   
	<c:forEach items="${reply}" var="reply">		
		 <div class="replyList">
			<div class="toast show" role="alert" aria-live="assertive"
				aria-atomic="true">
				<div class="toast-header">
					<strong class="me-auto">${reply.id}</strong> &nbsp <small><fmt:formatDate
							value="${reply.write_Date}" pattern="yyyy-MM-dd"/></small>					
				</div>
				 <div class="toast-body">${reply.content }
				 </div>
				 <button type="button" class="btn btn-outline-dark btn-sm"
					onclick="location.href ='/board/ReplyDelete.go?rno=${reply.rno}&bno=${boardDetail.article_NO}'">댓글
					삭제</button>
			</div>
		</div>
	</c:forEach>
	<br>

	<div class="replyWrite">

<c:if test="${userInfoId != null}">
	<form method="post" action="/board/boardWrite.do">
		
		<p>
			<textarea rows="5" cols="50" name="content" placeholder="댓글을 입력하세요"></textarea>
		</p>
		<p>
		     <input type="hidden" name="bno" value="${boardDetail.article_NO}">
			<button type="submit"  class="btn btn-outline-dark btn-sm">댓글 작성</button>
		</p>
	</form>
</c:if>
	
</div>
	
	


</body>
</html>