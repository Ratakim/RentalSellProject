<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- 호환성 보기 관용모드
		(quirks mode)로 지정된 DOCTYPE에 상관없이 IE5 렌더링 방식이 사용됩니다. -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<!-- css, js -->
	<link rel="stylesheet" type="text/css"
		href="${contextPath}/resources/css/main/user/signUpPage.css?after" />
	<script type="text/javascript" src="${contextPath}/resources/js/main/user/signUpPage.js?after"></script>
	<!-- import jquery, ajax -->
	<!-- javascript에 contextPath 추가하기 -->
	<!-- 부트스트랩 4.6 css, 아이콘, js -->
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
		integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
		crossorigin="anonymous">
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
		crossorigin="anonymous"></script>
		
		
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
	
	
	
</head>
<body>

	<div class="d-flex flex-column">
		<!-- step-bar -->
		<div class="step-header">
			<div>
				<a href="${contextPath}/common/main.go"> <img title="메인 로고" id="mainLogo" src="${contextPath}/resources/images/logo2.png" />
				</a>
			</div>
			<div class="step-bar">
				<div class="step-box"></div>

				<div class="step-line"></div>

				<div class="step-box"></div>

				<div class="step-line"></div>

				<div class="step-box"></div>
			</div>
		</div>
		<!-- step-bar - END -->

		<!-- 회원가입 페이지 form -->
		<main>
			<form data-multi-step class="multi-step-form" id="customerForm"
				method="post" action="${contextPath}/user/insertUser.do">

				<div class="step" data-step>
					<div class="step-title">Step 1</div>
					<div class="form-group">
						<label for="id">아이디</label><br /> 
						<input type="text" id="id" name="id" maxlength="20" placeholder="아이디" required/>
						<span class="checkId"></span>
					</div>

					<div class="form-group">
						<label for="pwd">패스워드</label><br /> 
						<input type="password"
							id="pwd" name="pwd" maxlength="40" placeholder="패스워드" required/>
						<span class="pwdWarning"></span>
					</div>
					<div class="form-group">
						<label for="pwdCheck">패스워드 확인</label><br /> 
						<input type="password" id="pwdCheck" name="pwdCheck" maxlength="40"
							placeholder="패스워드" required/>
						<span class="pwdCheckWarning"></span>
					</div>
					<button type="button" data-next id="next-button ">Next</button>
				</div>
				<!-- <div>
					<label>패스워드 확인</label><br/>
					<input type="password"	id="pwdCheck"   maxlength="40" 	placeholder="패스워드 확인"/>
				</div> -->
				<div class="step" data-step>
					<div class="step-title">Step 2</div>
					<div class="form-group">
						<label for="name">이름</label><br /> 
						<input type="text" id="name" required="required"
							name="name" maxlength="80" placeholder="이름을 입력하세요." />
					</div>
					<div class="form-group">
						<label for="gender">성별</label><br /> 
						<select id="gender" required="required"
							name="gender">
							<option value="여성">여성</option>
							<option value="남성">남성</option>
						</select>
					</div>
					<div class="form-group">
						<label for="address">주소</label><br /> 
						<input type="text" id="sample4_postcode" name="sample4_postcode" placeholder="우편번호" readonly="readonly"/>
						<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
						<input type="text" id="sample4_roadAddress" name="sample4_roadAddress"  placeholder="도로명주소" readonly required/>
						<input type="text" id="sample4_jibunAddress" name="sample4_jibunAddress"  placeholder="지번주소" readonly required/>
						<span id="guide" style="color:#999;display:none"></span>
						<input type="text" id="sample4_detailAddress" name="sample4_detailAddress"  placeholder="상세주소"readonly required/>
						<input type="text" id="sample4_extraAddress" name="sample4_extraAddress"  placeholder="참고항목" readonly required/>
					</div>

					
					<button type="button" data-previous id="Previous">Previous</button>
					<button type="button" data-next>Next</button>
				</div>

				<div class="step" data-step>
					<div class="step-title">Step 3</div>
					<div class="form-group">
						<label for="email">이메일</label> 
						<input type="email"
							class="form-control" name="email" id="email"
									placeholder="이메일을 입력하세요." required/>
							
						<div>
							<!--인증코드-->
							<button class="btn btn-outline-info btn-sm px-3" type="button" id="emailChk">
								<i class="fa fa-envelope"></i>&nbsp;인증 요청
							</button>
						<input type="text" style="margin-top: 5px;" class="email_form"	   id="emailAccess" name="emailAccess"
							placeholder="인증번호를 입력해주세요!"  required/>
						<button type="button" class="btn btn-outline-danger btn-sm px-3" onclick=" emailCheck();">
							<i class="fa fa-envelope"></i>&nbsp;인증	
						</button>&nbsp;
						<span class="successEmailChk">이메일 입력후 인증번호 보내기를 해주십시오</span>
						<input type="hidden" id="emailDoubleChk" required/>
					</div>
					<button type="button" data-previous >Previous</button>
					<div class="form-group submit-buttons flex-row">
						<button type="button" id="submitBtn" >회원가입</button>
						<br/>
						<button type="button" id="cancelBtn" onclick="history.back();">취소</button>
					</div>
				</div>

			</form>
		</main>
		<!-- 회원가입 페이지 form 끝 -->

	</div>
</body>
</html>