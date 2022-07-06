<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매니저 회원정보</title>
</head>
<style>
table {
	border: 1px solid ;
	width: 100%;
	height: 100%;
	
}
</style>
<body>
<div>
	<h1>Member.</h1>
	<table id="tab">
		<tr>
			<th> 부서명 </th><th> 이름 </th><th> 아이디 </th><th> 이메일 </th><th> 입사일 </th>
		</tr>
			<c:forEach var="memberList" items="${memberListc}" >
				<tr>
				 <td>${memberList.empnum}</td>
				 <td>${memberList.name}</td>
				 <td>${memberList.id}</td> 
				 <td>${memberList.email}</td> 
				 <td>${memberList.join_date}</td>
				</tr>
			</c:forEach>
	</table>
</div>

</body>
</html>