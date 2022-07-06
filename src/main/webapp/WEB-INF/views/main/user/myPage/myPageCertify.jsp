<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myPageMain</title>
</head>
<style>

.main {
	background-color: lightgreen;
	text-align: center;
}
.main div{
	background-color: yellow;
	height: 12%;
	text-align: left;
}
</style>
<body>
<form action="/user/insertUserAccount.go" method="POST">
	<div class="main">
		<div>
			<h5>은행 :</h5><input type="text" name="bank" id="bank">
			<h5>계좌 :</h5><input type="text" name="account" id="account">
		</div>
	
		
		<div>
			<button type="submit">입력</button>
			<button type="reset">취소</button>
		</div>
	</div>
</form>
</body>
</html>