<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>myPageMain</title>
	<style>
	.main {
		background-color: 	lightgreen;
		text-align: 		center;
	}
	.main2{
		text-align: 		center;
	
	}
	.main div{
		background-color: 	#lightgray;
		text-align: 		center;
		height: 			200;
	}
	
	.table-div {
		display: 			flex;
		border:				0px;
		align-content: 		center;
		justify-content: 	center;
	}
	
	table, table * {
		border:				2px solid red;
	}
	table {
		background-color: 	#FFF;
		height: 			60px;
		width: 				600px;
		margin: 			0;
		padding: 			0 2px 0 0px;
		text-align: 		center;
	
	}
	
	th {
		text-align: 		center;
	}
	
	td {
		text-align: 		center;
	}
	
	.sr {
		display: 			flex;
		background-color: 	lightgray;
		height: 			250px;
		width: 				800px;
		border:				0px;
		align-content: 		center;
		justify-content: 	center;
		text-align: 		center;
		padding:			300px;
	
	}
	span {
		color: 				red;
		padding: 			0 10px 0 0;
	}
	.sh {
		height: 			100px;
		padding-top: 		50px;
	}
	</style>
</head>
<body>
	<div class="main">
	
		<h1><br/><a>내 거래</a></h1>
		<hr>
		<div id="main2"><br><h6><a href="">빌려주기</a></h6>
				<div class="table-div">
					<table>
						<tr>
						<th><a href="#">등록</a></th>
						<th><a href="#">대여중</a></th>
						<th><a href="#">완료</a></th>
						</tr>
						<tr>
						<td><a href="#">0건</a></td>
						<td><a href="#">0건</a></td>
						<td><a href="#">0건</a></td>
						</tr>
					</table>
				</div>
		</div>
		<div><br>빌려오기
			<div class="table-div">
				<table>
					<tr>
					<th><a href="#">등록</a></th>
					<th><a href="#">대여중</a></th>
					<th><a href="#">완료</a></th>
					</tr>
					<tr>
					<td><a href="#">0건</a></td>
					<td><a href="#">0건</a></td>
					<td><a href="#">0건</a></td>
					</tr>
				</table>
			</div>
		</div>
			
		<div class="sh">
			<h3>관심 상품</h3>
		</div>
		<div class="sr">
			<h6><span>스크랩</span></h6>
			<h6>내역이없습니다.</h6>
		</div>
	</div>
</body>
</html>