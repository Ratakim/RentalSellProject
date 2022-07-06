//------------------------------------------------------------
// js에서 contextPath 사용
//------------------------------------------------------------ 
sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
function getContextPath() {	return sessionStorage.getItem("contextpath"); }
var ctx = getContextPath();

const letterCheck = {
    'checkNum': 	/[0-9]/,
    'checkEngA': 	/[A-Z]/,
    'checkEnga': 	/[a-z]/,
    'checkEngAll': 	/[a-zA-Z]/,
    'checkSpc': 	/[~!@#$%^&*()_+|<>?:{}]/,
    'checkKor': 	/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/
}

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

// id조건
const idCondition = {
    'minLength': 2, // 2자리 이상
    'maxLength': 20, // 20자리 이하
    'withNum': true, // 숫자 포함
    'withEngA': false, // 영문 대문자 미포함
    'withEnga': true, // 영문 소문자 포함
    'withSpc': false, // 특수문자 미포함
    'withKor': false, // 한글 미포함
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

function checkConditionValue(inputCssSelector, conditionObj, warningSelector) {
	
 	// 알림메세지에 넣을 조건들을 넣을 배열
    let conditionAlert = [];
  
  	// input value의 표현식이 너무 길어서 변수로 할당해주었다.
    let InputVal = document.querySelector(inputCssSelector).value;
  
  	// 만약 minLength보다 작으면 -> 해당 숫자와 조건 메세지를 같이 묶어서
  	// conditionAlert 배열에 push
    if (!(InputVal.length >= conditionObj.minLength)) {
        conditionAlert.push(`${conditionObj.minLength}` + conditionMessage.minLength);
    } 
  	// 이제부턴 주석 생략! 추측해보시라
    if (!(InputVal.length <= conditionObj.maxLength)) {
        conditionAlert.push(`${conditionObj.maxLength}` + conditionMessage.maxLength);
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
    
  	// conditionAlert에 아무 것도 들어가 있지 않는다면 모든 조건이 충족한 것!
    // 성취감을 느끼며 다음 메세지를 띄운다.. '제출되었습니다아......'
    if (conditionAlert.length === 0) {
        //alert('제출되었습니다.');
        document.querySelector(warningSelector).innerText = "사용 가능합니다.";
        document.querySelector(warningSelector).style.color = "green";
        return 1;
    } else {
  	// 아니면 conditionAlert의 모든 엘리먼트들을 join해서 내보낸다.
        //alert('조건: ' + conditionAlert.join(', '));
        document.querySelector(warningSelector).innerText = "" + conditionAlert.join(', ');
        document.querySelector(warningSelector).style.color = "red";
        return 0;
    }
    
}

	
	
	

	


/*//next btn 
function showTab(n) {
  // This function will display the specified tab of the form...
  var x = document.getElementsByClassName("tab");
  x[n].style.display = "block";
  //... and fix the Previous/Next buttons:
  if (n == 0) {
    document.getElementById("data-previous").style.display = "none";
  } else {
    document.getElementById("data-previous").style.display = "inline";
  }
  if (n == (x.length - 1)) {
    document.getElementById("data-next").innerHTML = "Submit";
  } else {
    document.getElementById("data-next").innerHTML = "Next";
  }
  //... and run a function that will display the correct step indicator:
  fixStepIndicator(n)
}*/



// 아이디 특수문자, 중복 체크
function idEffectiveness() {
	return checkConditionValue("#id", idCondition,".checkId");
}

// 아이디 특수문자, 중복 체크
function pwdEffectiveness() {
	return checkConditionValue("#pwd", psCondition, ".pwdWarning");
}

//이메일 인증번호 대조
function  emailCheck() {
	if ($("#emailAccess").val() == $("#emailDoubleChk").val()) {
		$(".successEmailChk").text("인증번호가 일치합니다.");
		$(".successEmailChk").css("color", "green");
		//$("#email").attr("disabled",true);
	} else {
		$(".successEmailChk").text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
		$(".successEmailChk").css("color", "red");
		$("#emailAccess").focus();
	}
}

/*---------------------------------------------------*/
/*				$(document).ready					 */
/*---------------------------------------------------*/
$(document).ready( function() {


	/*---------------------------------------------------*/
	/*				Step Move							 */
	/*---------------------------------------------------*/
	const stepForm = document.querySelector('[data-multi-step]');
	const formSteps = [...stepForm.querySelectorAll('[data-step]')];
	
	// 요소.find()는 어떤 요소의 하위 요소 중 특정 요소를 찾을 때 사용
	// findIndex() 메서드는 주어진 판별 함수를 만족하는 배열의 
	// 첫 번째 요소에 대한 인덱스를 반환합니다. 
	// 만족하는 요소가 없으면 -1을 반환합니다.
	let currentStep = formSteps.findIndex((step) => {
		
		// step의 클래스리스트중에 active가 없으면 false 반환	
		return step.classList.contains("active");
	});
		
	if(currentStep < 0) {	// 어느 한곳에도 active가 없으면 0번째에 active를 해준다.
		currentStep = 0;
		showCurrentStep();
	}
	stepForm.addEventListener('click', (e) => {
		
		console.log("click = " + currentStep);
		
		/*if("#id"){
			result=0;
			return false;
		}*/
		
		// 증감자 변수를 선언하고
		let incrementor;
		console.log(e.target);
		if("#pwd"=="pwdCheck" ){
			return false;
		}
		// next를 누르면 증감자가 카운트를 +1하고
		if (e.target.matches("[data-next]")) {
			incrementor = 1;
		
			if(!inputCheck.validationtest(currentStep)){
				return;
			}
			
		
		// previous를 누르면 증감자가 카운트 -1한다.
		} else if (e.target.matches("[data-previous]")) {
			incrementor = -1;
		}
	
		
		// 만약 증감자값이 널이면 리턴한다.
		if(incrementor == null) return;
		
		// [data-step][현재 인덱스]의 모든 input을 inputs 변수에 넣는다.
	/*	const inputs = [...formSteps[currentStep].querySelectorAll("input")];*/
		
		/*debugger;*/
		
		// 그 인풋들의 값을 확인한다.
		// checkValidity required가 true인 곳에 값이 없다면
		// 증감하지 않고 false를 반환
		/*const allValid = inputs.every( input => input.checkValidity() );*/
		
		/*console.log(inputs[0].checkValidity());
		console.log(inputs);
		console.log(allValid);
		if (allValid) {
			currentStep += incrementor
			showCurrentStep();
		}*/
		
		currentStep += incrementor;
		console.log("currentStep = " + currentStep);
		showCurrentStep();
		
	});
	
	function showCurrentStep() {
		formSteps.forEach((step, index) => {
			// classList에 있으면  제거하고 ture 없으면 넣고 false
			step.classList.toggle("active", index === currentStep);
		})
	}
	
	
	const inputCheck = {
		
		validationtest :  (stepNum) => {
			
			// input 전체 읽음
			const inputs = [...formSteps[stepNum].querySelectorAll("input")];
			console.log(inputs);
			
			// 사용자가 입력한 값을 저장할 변수 선언
			var id = '';
			var pwd = '';
			var pwdCheck = '';
			var name = '';
			var gender = '';
			var email = '';
			var emailAccess = '';
			
			let sample4_postcode='';
			let sample4_roadAddress='';
			let sample4_jibunAddress='';
			let sample4_detailAddress='';
			let sample4_extraAddress='';
			
			// 전체 input을 id 기준으로 비교하여 변수에 값 저장
			for (var i = 0; i < inputs.length; i++) {
				if(stepNum == 0) {
						if 	(inputs[i].id == 'id') {
							id 			= inputs[i].value;
						} else if (inputs[i].id == 'pwd') {
							pwd 		= inputs[i].value;
						} else if (inputs[i].id == 'pwdCheck') {
							pwdCheck 	= inputs[i].value;
						} 

				} else if (stepNum == 1) {
					if (inputs[i].id == 'name') {
						name 		= inputs[i].value;
					}
					
					gender 		= document.querySelector("#gender").value;
					sample4_postcode 		= document.querySelector("#sample4_postcode").value;
					sample4_roadAddress 	= document.querySelector("#sample4_roadAddress").value;
					sample4_jibunAddress 	= document.querySelector("#sample4_jibunAddress").value;
					sample4_detailAddress 	= document.querySelector("#sample4_detailAddress").value;
					sample4_extraAddress 	= document.querySelector("#sample4_extraAddress").value;
						
						
				} else if (stepNum == 2) {
					if (inputs[i].id == 'email') {
						email 		= inputs[i].value;
						
					} else if (inputs[i].id == 'emailAccess') {
						emailAccess = inputs[i].value;
						
					} 
					
				} else {
				}
			}
			
			// pwd, pwdcheck에 값이 없을 경우 alert, return false
			//입력 값이 없음
			if(stepNum == 0) {
				if (pwd ==  pwdCheck == '' || id == '') {
					alert('아이디, 패스워드, 패스워드 확인을 모두 입력해주세요');
					return false;
				}
			}
			if(stepNum == 1) {
				if (name == '' || gender == '' || sample4_extraAddress == ''
							   ||sample4_postcode ==''    || sample4_roadAddress==''
							   ||sample4_jibunAddress=='' || sample4_detailAddress=='') {
					alert('이름, 성별, 주소를 모두 입력해주세요');
					return false;
				} 
			}
			if(stepNum == 2) {
				if (email == '' || emailAccess == '') {
					alert('이메일 입력과 이메일 검사를 모두 완료해야합니다.');
					return false;
				}
			}
			return true;
		}
	
	}
	
	formSteps.forEach(step => {
		// step의 애니메이션이 끝날 때
		step.addEventListener('animationend', (e) => {
			formSteps[currentStep].classList.remove("hide");
			e.target.classList.toggle("hide", !e.target.classList.contains("active")) 
		});
	});
	/*			Step Move - END 				*/
	
	// 회원가입 버튼 유효성 검사
	$('#submitBtn').on('click', () => {
		
		if( document.querySelector("#email").value != '' 
		&& document.querySelector("#emailDoubleChk").value != ''
		&& document.querySelector("#emailAccess").value == document.querySelector("#emailDoubleChk").value) {
			$("#customerForm").submit();
		} else {
			alert("이메일 확인을 완료해 주세요.");
		}
		
	});
	
	
	// 아이디 유효성 검사
	$('#id').on("focusout", function() {
		let result = idEffectiveness();
		console.log("idresult = ", result);
		
		if(result == 0) return
		
		let id = $('#id').val(); // input_id에 입력되는 값
		console.log(id);
		
		$.ajax({
			url : "/user/idCheck.do",
			type : "post",
			data : {"id": id},
			dataType : 'json',
			success : function(result){
				if(result == 1) {
					console.log("result = " + result);
					$(".checkId").text('사용할 수 없는 아이디입니다.');
					$(".checkId").css('color','red');
				} else{
					console.log("result = " + result);
					$(".checkId").text('사용할 수 있는 아이디입니다.');
					$(".checkId").css('color','green');
				}
			},
			error : function(error){
				console.log(error);
				alert("서버요청실패\n" + error);
			}
		});
		 
	});
	
	
	
	// 패스워드에 유효성 검사
	$("#pwd").on("focusout", function(){
		
		let result = pwdEffectiveness();
		console.log("pwdresult = ", result);
		console.log(result)
		if(result == 0) {return;}
		
	});
	
	// 패스워드 확인
	$("#pwdCheck").on("focusout", function() {
		console.log($(this).val());
		console.log($("#pwd").val());
		if($(this).val() != $("#pwd").val() ) {
			console.log($(this).val() != $("#pwd").val());
			$(".pwdCheckWarning").text("패스워드가 같지 않습니다.");
			$(".pwdCheckWarning").css('color', 'red');
		} else if($(this).val() == ""){
			console.log($(this).val() != $("#pwd").val());
			$(".pwdCheckWarning").text("패스워드 확인을 입력해 주십시오.");
			$(".pwdCheckWarning").css('color', 'red');
		} else {
			console.log($(this).val() != $("#pwd").val());
			$(".pwdCheckWarning").text("사용가능한 패스워드입니다.");
			$(".pwdCheckWarning").css('color', 'green');
		}
		
	});
	
	//이메일 인증
	var code = "";
	
	$("#emailChk").click(function() {
		var email = $("#email").val();
		$.ajax({
			type : "GET",
			url : "/user/signUpForm.do?email=" + email,
			cache : false,
			success : function(key) {
				console.log(key);
				if (key == "error") {
					alert("이메일 주소가 올바르지 않습니다. 유효한 이메일 주소를 입력해주세요.");
					$("#email").attr("autofocus", true);
					$(".successEmailChk").text("유효한 이메일 주소를 입력해주세요.");
					$(".successEmailChk").css("color", "red");
				} else {
					alert("인증번호 발송이 완료되었습니다.\n입력한 이메일에서 인증번호 확인을 해주십시오.");
					$("#email2").attr("disabled", false);
					$("#emailChk2").css("display", "inline-block");
					$(".successEmailChk").text("인증번호를 입력한 뒤 이메일 인증을 눌러주십시오.");
					$(".successEmailChk").css("color", "green");
					$("#emailDoubleChk").val(key);
					console.log($("#emailDoubleChk").val());
				}
			}
		});
	});
	
	


}); // document.ready - END 






