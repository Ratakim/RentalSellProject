<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내역</title>
</head>
<style>

.main {
	background-color: lightgreen;
	float: 	right;
	width: 780px;
	height: 700px;
}
.main div{
	background-color: yellow;
	height: 12%;
	text-align: left;
}
.my {
	text-align: left;
	
}
.table{
	boder: 1px solid;
	text-align: center;
}
</style>
<body>
	<div class="main">
		<h1><br/><a href="#">마일리지 페이지</a></h1>
		<h2 class="my">나의 마일리지 : <span>ooooo원</span></h2>
		<hr>
		<div class="div2"><br><h2>마일리지 사용내역</h2></div>
		
			<table class="table">
				<tr>
					<th>마일리지 사용내역</th>
					<th>충전/출금 금액</th>
					<th>날짜</th>
				</tr>
				<tr>
					<td> 충전 </td>
					<td>400,000원 </td>
					<td> 2022-05-09 </td>
				</tr>	
				<tr>
					<td> 		사용 </td>
					<td>  350,000원 </td>
					<td> 2022-05-09 </td>
				</tr>	
				<tr>
					<td> 		출금 </td>
					<td>	50,000원 </td>
					<td> 2022-05-09 </td>
				</tr>	
			</table>
		</div>
		
</body>
</html>