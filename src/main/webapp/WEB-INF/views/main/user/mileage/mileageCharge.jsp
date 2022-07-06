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
		<h1><br/><a href="#">마일리지 충전</a></h1>
		<h2 class="my">나의 마일리지 : <span>ooooo원</span></h2>
		<hr>
		<div class="div2"><br><h2>마일리지 충전 신청 365일/24시간 마일리지충전</h2>
			<h5>
				· Billyus 계좌로 충전 신청금액을 송금 (Billyus 회원명, 입금자명 반드시 동일해야 함)<br>
				· Billyus 사이트에서 "무통장/계좌이체" 선택후, 충전 신청하기<br>
				· 무통장/계좌이체 거래란 회원님이 본인의 이름으로 직접 송금해 주시는 방식입니다.<br>
				· 신청 후 30분 내 송금하셔야 합니다.<br>
				※ 회원명과 다른 이름으로 송금한 경우, 고객센터 문의하기에서 입금내역(스크린샷 혹은 사진)을 보내주시면 확인 후 안내 도와드리겠습니다.<br>
 				※ 꼭 회원 본인 이름으로 송금해 주십시오.<br>
 			</h5>
		</div><br><br><br><br><hr><br>
		
			<table class="table">
				<tr>
					<th></th>
					<th>결제정보</th>
					<th></th>
				</tr>
				<tr>
					<td> 충전방법  </td>
					<td>＊무통장/계좌이체 </td>
					<td>＊신용카드 결제 </td>
				</tr>	
				<tr>
					<td> 계좌번호 </td>
					<td>  국민은행 01001001010 Billyus </td>
					<td>  </td>
				</tr>	
				<tr>
					<td> 충전요청금액 </td>
					<td><input type="text">원 </td>
					<td>  </td>
				</tr>	
				<tr>
					<td> 입금자명 </td>
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