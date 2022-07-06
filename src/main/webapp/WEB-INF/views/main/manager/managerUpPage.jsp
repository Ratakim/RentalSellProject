<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<!-- daum 우편번호 서비스 -->
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
		    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
		    function sample4_execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
		                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var roadAddr = data.roadAddress; // 도로명 주소 변수
		                var extraRoadAddr = ''; // 참고 항목 변수
		
		                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                    extraRoadAddr += data.bname;
		                }
		                // 건물명이 있고, 공동주택일 경우 추가한다.
		                if(data.buildingName !== '' && data.apartment === 'Y'){
		                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                }
		                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                if(extraRoadAddr !== ''){
		                    extraRoadAddr = ' (' + extraRoadAddr + ')';
		                }
		
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById('sample4_postcode').value = data.zonecode;
		                document.getElementById("sample4_roadAddress").value = roadAddr;
		                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
		                
		                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
		                if(roadAddr !== ''){
		                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
		                } else {
		                    document.getElementById("sample4_extraAddress").value = '';
		                }
		
		                var guideTextBox = document.getElementById("guide");
		                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
		                if(data.autoRoadAddress) {
		                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
		                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
		                    guideTextBox.style.display = 'block';
		
		                } else if(data.autoJibunAddress) {
		                    var expJibunAddr = data.autoJibunAddress;
		                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
		                    guideTextBox.style.display = 'block';
		                } else {
		                    guideTextBox.innerHTML = '';
		                    guideTextBox.style.display = 'none';
		                }
		            }
		        }).open();
		    }
		</script>
		
		
	<!-- daum 우편번호 서비스끝 -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style>

.bmain{
display: flex;
flex-direction: column;
justify-content: center;
align-items: center;
border: 1px solid red;
	
}

.form-group label{
	width: 110px;
	height: 20px;
	text-align: left;
}
</style>
<body>
<h1>Manager</h1>
<form method="POST" action="${contextPath}/manager/managerUpPage.do">
	<div class="bmain">
		<h1>매니저 회원가입</h1>	
		<div class="step">
			<div class="form-group">
				<label for="id">아이디:</label><span class="idWarning"></span>
					<input type="text" id="id" name="id" maxlength="20" placeholder="아이디를입력하세요" required/><br/>
			</div>
			<div class="form-group">
				<label for="pwd">패스워드:</label><span class="pwdWarning"></span>
					<input type="password" id="pwd" name = "pwd" maxlength="20" placeholder="패스워드 입력" required/><br/>
			</div>
			<div class="form-group">
				<label for="pwdCheck">패스워드확인:</label><span class="pwdCheckWarning"></span>
					<input type="password" id="pwdCheck" name="pwdCheck" maxlength="20" placeholder="패스워드 확인" required/><br/>
			</div>
			<div class="form-group">
				<label for="empnun" >부서명:</label>
					<select id="empnun" name="empnun">
						<option value="인사">인사팀</option>
						<option value="기획">기획팀</option>
						<option value="상품">상품관리팀</option>
						<option value="IT">IT팀</option>
					</select>
			</div>
			<div class="form-group">
				<label for="name" >이름:</label>
					<input type="text" id="name" name="name" maxlength="20" placeholder="이름을 입력하세요" required/><br/>
			</div>
			<div class="form-group">
				<label for="email">이메일:</label>
					<input type="text" id="email" name="email" maxlength="20" placeholder="E-mail 입력하세요" required/><br/>
			</div>
			<div class="form-group">
				<label for="emailaccess">메일인증번호:</label>
					<input type="text" id="emailCheck" name="emailaccess" maxlength="6" placeholder="메일 인증번호입력해주세요" required/><br/>
			</div>
			<div class="form-group">
				<label for="gender" >성별:</label>
					<label><input type="radio" name="gender" value="남성">남성</label>
					<label><input type="radio" name="gender" value="여성">여성</label>
					<br/>
			</div>	
			<div class="form-group">
				<label for="address">주소</label>
				<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample4_postcode" name="sample4_postcode" placeholder="우편번호" readonly required/><br>
				<input type="text" id="sample4_roadAddress" name="sample4_roadAddress"  placeholder="도로명주소"  readonly required/><br>
				<input type="text" id="sample4_jibunAddress" name="sample4_jibunAddress"  placeholder="지번주소" required/><br>
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" id="sample4_detailAddress" name="sample4_detailAddress"  placeholder="상세주소" required><br>
				<!-- <input type="text" id="sample4_extraAddress" name="sample4_extraAddress"  placeholder="참고항목" required><br> -->
			</div>
		</div>
			<div>
				<button type="submit">회원가입</button>
				<button type="reset">취소</button>
			</div>
	</div>
</form>
</body>
</html>
