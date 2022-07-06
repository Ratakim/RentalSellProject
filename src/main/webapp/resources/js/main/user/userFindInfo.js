/*
 a 링크 post 방식으로 바꾸기
 */
function aLinkPost(uri) {
	let tempForm = document.createElement('form');
	let inputs = new Array();
	let paramArr = new Array();
	
	console.log(uri);
	let url = uri.split("?")[0];
	let queryString = uri.split("?")[1];
	let paramLength = queryString.split("&").length;
	
	for (var i = 0; i < paramLength; i++) {
		paramArr.push([queryString.split("&")[i].split("=")[0], queryString.split("&")[i].split("=")[1]]);
	}
	console.log(paramArr[0]);
	tempForm.action = url;
	tempForm.method = "post";
	
	for (var i = 0; i < paramLength; i++) {
		inputs[i] = document.createElement("input");
		inputs[i].setAttribute("type", "hidden");
		inputs[i].setAttribute("name", 	paramArr[i][0]);
		inputs[i].setAttribute("value", paramArr[i][1]);
		tempForm.appendChild(inputs[i]);
	}
	
	document.body.appendChild(tempForm);
	tempForm.submit();
}

document.addEventListener('DOMContentLoaded', () => {
	
	// ---------------------------------------
	// 선언부
	// ---------------------------------------
	const resetPwdBtn = document.querySelector("#resetPwd");
	const warningMsg 	= document.querySelector("span.warning");
	const pwd			= document.querySelector("#password");
	const pwdMsg		= document.querySelector("span.warning-pwd");
	const pwdCheck 	= document.querySelector("#passwordCheck");
	const pwdCheckMsg	= document.querySelector("span.warning-pwdcheck");
	
	// 패스워드 확인 카운트
	let checkCount = 0;
	
	// 정규식 검사표
	const letterCheck = {
	    'checkNum': 	/[0-9]/,					// 번호 포함 검사
	    'checkEngA': 	/[A-Z]/,					// 대문자 포함 검사
	    'checkEnga': 	/[a-z]/,					// 소문자 포함 검사
	    'checkEngAll': 	/[a-zA-Z]/,					// 대문자 소문자 섞어쓰기 검사
	    'checkSpc': 	/[~!@#$%^&*()_+|<>?:{}]/,	// 띄어쓰기 포함 검사
	    'checkKor': 	/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/		// 한글 포함 검사
	}
	
	// 검사 후 메시지
	const conditionMessage = {
	    'minLength': 	'자리 이상',
	    'maxLength': 	'자리 이하',
	    'withNum': 		'숫자 포함',
	    'withNoNum': 	'숫자 미포함',
	    'withEngA': 	'영문 대문자 포함',
	    'withNoEngA': 	'영문 대문자 미포함',
	    'withEnga': 	'영문 소문자 포함',
	    'withNoEnga': 	'영문 소문자 미포함',
	    'withSpc': 		'특수문자 포함',
	    'withNoSpc': 	'특수문자 미포함',
	    'withKor': 		'한글 포함',
	    'withNoKor': 	'한글 미포함'
	}
	
	// ps의 조건
	const psCondition = {
	    'minLength': 8,
	    'maxLength': 20,
	    'withNum': true,
	    'withEngA': true,
	    'withEnga': true,
	    'withSpc': true,
	    'withKor': false,
	}
	
	// ---------------------------------------
	// 함수
	// ---------------------------------------
	function checkConditionValue(inputCssSelector, conditionObj, warningSelector) {
		
	 	// 알림메세지에 넣을 조건들을 넣을 배열
	    let conditionAlert = [];
	  
	  	// input의 value
	    let InputVal = inputCssSelector.value;
	  
	  	// 만약 minLength보다 작으면 -> 해당 숫자와 조건 메세지를 같이 묶어서
	  	// conditionAlert 배열에 push
	    if (InputVal.length <= conditionObj.minLength) {
	        conditionAlert.push(conditionObj.minLength + conditionMessage.minLength);
	    }
	    
	  	// 만약 maxLength보다 크면
	    if (InputVal.length >= conditionObj.maxLength) {
	        conditionAlert.push(conditionObj.maxLength + conditionMessage.maxLength);
	    }
	    
	    // 대문자 미포함
	    if (letterCheck.checkEngA.test(InputVal) !== conditionObj.withEngA) {
	        if (conditionObj.withEngA) {
	            conditionAlert.push(conditionMessage.withEngA);
	        } else {
	            conditionAlert.push(conditionMessage.withNoEngA);
	        }
	    }
	    
	    // 특수문자 미포함
	    if (letterCheck.checkSpc.test(InputVal) !== conditionObj.withSpc) {
	        if (!conditionObj.withSpc) {
	            conditionAlert.push(conditionMessage.withSpc);
	        } else {
	            conditionAlert.push(conditionMessage.withNoSpc);
	        }
	    }
	    
	    // 한글 포함되면 안됨
	    if (letterCheck.checkKor.test(InputVal) !== conditionObj.withKor) {
	        if (!conditionObj.withKor) {
	            conditionAlert.push(conditionMessage.withKor);
	        } else {
	            conditionAlert.push(conditionMessage.withNoKor);
	        }
	    }
	    
	  	// conditionAlert에 아무 것도 들어가 있지 않는다면 모든 조건이 충족.
	    if (conditionAlert.length === 0) {
	        // alert('제출되었습니다.');
	        warningSelector.innerText = "사용 가능합니다.";
	        warningSelector.style.color = "green";
	        return 1;
	        
	    } else {
	  		// 아니면 conditionAlert의 모든 엘리먼트들을 join해서 내보낸다.
	        // alert('조건: ' + conditionAlert.join(', '));
	        warningSelector.innerText = "" + conditionAlert.join(', ');
	        warningSelector.style.color = "red";
	        return 0;
	    }
	    
	}
	
	// ---------------------------------------
	// 실행부
	// ---------------------------------------
	document.addEventListener('focusout', (e) => {
		console.log(e.target);
		if(e.target === pwd) {		
			checkConditionValue(pwd, psCondition, pwdMsg);
		} else if (e.target === pwdCheck) {
			checkConditionValue(pwdCheck, psCondition, pwdCheckMsg);
		} else {
			return;
		}
		
	});
	
	resetPwdBtn.addEventListener('click', function () {
		console.log(resetPwdBtn.form);
		console.log(resetPwdBtn.form.elements);
		checkCount += checkConditionValue(pwd, psCondition, pwdMsg);
		checkCount += checkConditionValue(pwdCheck, psCondition, pwdCheckMsg);
		console.log(checkCount);
		if (checkCount != 2) {
			checkCount = 0;
			warningMsg.innerText = "패스워드 유효성 검사를 완료해 주세요.";
			
			return; 
		} else {
			if (resetPwdBtn.form.password.value != pwdCheck.value) {
				warningMsg.innerText = "패스워드 확인과 비밀번호가 같지 않습니다.";
				checkCount = 0;
				
				return;
			} else {
				alert("패스워드를 변경합니다.");
			}
		} 
		
		// 성공시 submit
		resetPwdBtn.form.method = "post";
		resetPwdBtn.form.action = "/find/resetPassword";
		resetPwdBtn.form.submit();
	});
	
});
	