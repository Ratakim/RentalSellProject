<%@page import="org.apache.velocity.tools.view.WebappUberspector.GetAttributeExecutor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/main/board/boardAbout.css">
<meta charset="UTF-8">
<title>about</title>
</head>

<body>

 
	<h1><br/><a>고객센터</a></h1>
                  
            <div class="selectbutton">
			<a href="${contextPath}/board/boardAbout.go?val=공지"><button type="button" class="btn btn-dark">공지사항</button></a>
			<a href="${contextPath}/board/boardAbout.go?val=1"><button type="button" class="btn btn-dark">1:1 문의</button></a>
			<a href="${contextPath}/board/boardAbout.go?val=자주"><button type="button" class="btn btn-dark">자주 묻는 질문</button></a>
			<a href="${contextPath}/board/boardAbout.go?val=사기"><button type="button" class="btn btn-dark">사기 피해 공유</button></a>
		    </div>
		
	
		<h2>${boardPaging[0].board_Type}</h2>
		
		<div class="search">
			<div>
				<select name="searchType" class="select">
					<option value="t"
						<c:if test="${param.searchType eq 't'}">selected</c:if>>제목</option>
					<option value="c"
						<c:if test="${param.searchType eq 'c'}">selected</c:if>>내용</option>
					<option value="i"
						<c:if test="${param.searchType eq 'i'}">selected</c:if>>ID</option>
				</select> 
				
				<input type="text" id="searchbar" name="keyword" value="${param.keyword}" />
				<button type="button" class="btn btn-outline-dark"  onclick="searchBtn();">검색</button>
			</div>
		</div>

			<script>
				function searchBtn() {
					let searchType = document.getElementsByName("searchType")[0].value;
					let keyword = document.getElementsByName("keyword")[0].value;

					location.href = "/board/boardAbout.go?num=1"
							+ "&searchType=" + searchType + "&keyword="
							+ keyword;
				}
			/* 
				document.getElementById("searchBtn").onclick = function() {
										
					let searchType = document.getElementsByName("searchType")[0].value;
					let keyword = document.getElementsByName("keyword")[0].value;

					location.href = "/board/boardAbout.go?num=1"+"&val=" +${param.val}
							+ "&searchType=" + searchType + "&keyword="
							+ keyword;

				}; */
			</script>
                       			
            <c:if test="${loginResult != null}">
			<button type="button" class="btn btn-dark" onclick="location.href ='/board/boardWriting.go'">작성하기</button>
			</c:if>
		<div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">ID</th>
						<th scope="col">작성일</th>
					</tr>
				</thead>
				<c:forEach var="board" items="${boardPaging}">
					<tr>
						<td>${board.article_NO}</td>
						<td><a
							href="/board/boardDetail.go?article_NO=${board.article_NO}">${board.title}</a></td>
						<td>${board.id }</td>
						<td>${board.write_Date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>


		<div>

	</div>		
   
     <ul class="pagination">
    	<c:if test="${prev}">      	
			<li class="page-item">
             <a class="page-link" href="/board/boardAbout.go?num=${startPageNum - 1}&val=${param.val}">&laquo;</a>
            </li>
		</c:if>
		
		<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
						
		    <c:if test="${select != num}">		  
			   <li class="page-item">
               <a class="page-link" href="/board/boardAbout.go?num=${num}&val=${param.val}">${num}</a>
               </li>
			</c:if> 
			<c:if test="${select == num}">
			  <li class="page-item">
               <a class="page-link">${num}</a>
			</c:if>

			
		</c:forEach>

		<c:if test="${next}">			
			 <li class="page-item">
             <a class="page-link" href="/board/boardAbout.go?num=${endPageNum + 1}&val=${param.val}">&raquo;</a>
             </li>
		</c:if>		
		 </ul>
	
	
 
 
</body>
</html>