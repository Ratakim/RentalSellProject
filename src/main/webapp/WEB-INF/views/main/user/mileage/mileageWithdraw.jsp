<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>충전</title>
</head>
<style>

.main {
	background-color: lightgreen;
	float: 	right;
	width: 780px;
	height: 780px;
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
		<h1><br/><a href="#">마일리지 출금</a></h1>
		<h2 class="my">나의 마일리지 : <span>ooooo원</span></h2>
		<hr>
		<div class="div2"><br><h2>마일리지 출금 신청 365일/24시간 마일리지출금</h2>
			<h5>
				· 카드 결재 취소 관련 안내 <br>구매전용 마일리지(카드결재) 취소를 원하실 경우 마일리지 내역에서 가능합니다.<br>·
				· 은행 예금주명이 다르면 출금이 불가능/ 회원 가입시 기입한 은행 계좌번호, 예금주명 확인해 주십시요.
				
 			</h5>
		</div><br><br><br><br>
		
			<table class="table">
				<tr>
					<th></th>
					<th>출금정보</th>
					<th></th>
				</tr>
				<tr>
					<td> 충전방법  </td>
					<td>＊무통장/계좌이체 </td>
					<td>＊신용카드  </td>
				</tr>	
				<tr>
					<td> 계좌번호 </td>
					<td> 국민은행 데이터베이스 정보가져오기. </td>
					<td>  </td>
				</tr>	
				<tr>
					<td> 출금 요청금액 </td>
					<td><input type="text">원 </td>
					<td>  </td>
				</tr>	
				<tr>
					<td> 예금주 </td>
					<td><input type="text"> </td>
					<td>  </td>
				</tr>	
				<tr>
					<td>  </td>
					<td><button> 신청하기</button> </td>
					<td>  </td>
				</tr>	
			</table>
		</div>
		
</body>
</html>