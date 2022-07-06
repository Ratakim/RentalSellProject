
// $(document).ready(function() {
	
	
$(document).ready(function() {
	
	/********  패스워드 보는 버튼 만들기 **************/
	var $eyeSlash 	= $('.bi-eye-slash');
	var $eye 	 	= $('.bi-eye');
	
	$(pwdBtn).on({
		mousedown:	function() {
			$(pwd).attr('type', 'text');
			$eyeSlash.css('display', 'none');
			$eye.css('display', 'block');
		},
		mouseup:	function() {
			$(pwd).attr('type', 'password');
			$eye.css('display', 'none');
			$eyeSlash.css('display', 'block');
		}
	});
	/********  패스워드 보는 버튼 만들기 **************/
	
	/************ delete 모달만들기 ************/
	
	var modal 			= document.querySelector('.modal');
	var body 			= document.querySelector('#myInfo-body');
	var btnOpenPopup 	= document.querySelector('.btn-open-popup');
	
	// const $modal = $('.modal')
	
	// 삭제 버튼을 클릭하면
	// (value)=> {} 는  function(value) {}라고 표기한 것과 같다.
	btnOpenPopup.addEventListener('click', function() {
	console.log('deleteBtn 클릭 리스너 in');
		//classList.toggle() 
		// 클래스가 있으면 제거하고 false 반환
		// 클래스가 없으면 추가하고 true를 반환
		modal.classList.toggle('show');
		
		//classList.contains()
		// 만약 클래스 목록에 show클래스가 
		// 있다면 true 없다면 false
		if(modal.classList.contains('show')) { // show가 있다면
			// 모달이 활성화가 되므로 body의 스크롤을 감춰 비활성화한다.
			body.style.overflow = 'hidden'; 
		}
	console.log('deleteBtn 클릭 리스너 out');	
	});
	
	// 모달 메인을 클릭하면
	modal.addEventListener('click', function(event) {
	console.log('modal 클릭 리스너 in');
		// event target은 현재 이벤트가 발생한 element를 반환하고
		// event currentTarget 부모 element를 반환한다.
		if (event.target === modal) { // = 3개 (===) 는 데이터 타입을 비교한다.
			modal.classList.toggle('show');
			
			// 모달의 클래스에 show가 없다면
			if(!modal.classList.contains('show')) {
				body.style.overflow = 'auto';
			}
		}
		
	console.log('modal 클릭 리스너 out');
	
	});
	/************ delete 모달만들기 - END ************/
	
	
	/************ 보안 코드 요청 만들기 ************/
	
	/* ajax로 보안코드 요청하여 난수 받기 */
	var code;
	
	function requestCodeAjax() {
		
		/* 보안코드를 서버에 저장할 방법 찾기 */
		$.ajax({
			async:			false,
			url: 			'/user/securityCode.do',
			type:			'GET',
			data:			'',
			dataType:		'json',
			contentType:	'application/json; charset=UTF-8',
			success:		function(response) {
				$('#codeResult').text(response);
			},
			error:			function(error) {
				alert(error);
			}
			
		});
	}
	/* ajax로 보안코드 요청하여 난수 받기 - END */
	
	/* 난수와 입력값이 같은지 확인하고 submit */
	var codeCertification = false;
	/*$(okBtn).on('click', function(e) {
		if($(securityCode).val() == $('.code-result').text()) {
			alert("인증 성공");
			codeCertification = true;
		} else {
			return false;
		}
	});*/
	
	/* 보안코드 요청 후 타이머 작동하기 */
	var timer 		= null;
	var isRunning 	= false;
	
	// 디스플레이에 넣을 타이머 문자열
	function startTimer(count, display) {
	
		var minutes, seconds;
		
		timer = setInterval(function () {
			// parserInt( 숫자값, 몇진수인지) int로 형변환해준다.
			minutes = parseInt(count / 60, 10); // 60으로 나눈게 분
			seconds = parseInt(count % 60, 10); // 60으로 나눈 나머지가 초
			
			// 일의 자리 숫자만 있으면 '0'을 붙여 01과 같은 형태로 만듦
			minutes = (minutes < 10 ? '0' + minutes : minutes);
			seconds = (seconds < 10 ? '0' + seconds : seconds);
			
			display.html(minutes + " : " + seconds);
			
			// 타이머 끝
			if(--count < 0) { // 180초 끝나면
				clearInterval(timer);
				display.html("시간초과");
				$('.okBtn').attr('disabled', 'disabled');
				isRunning = false;		
			}
		}, 1000);
		isRunning = true;
	
	}
	
	$(securityReqBtn).on('click', function(e) {
		var display = $('.timer-display');
		var leftSec = 180;
		
		// 남은 시간
		// 이미 타이머가 작동중이면 중지
		if(isRunning) {				// 이미 타이머가 작동중이면

			clearInterval(timer);	// 타이머 중지
			display.html("");		// display를 비우고
			startTimer(leftSec, display);	// 다시 시작
		} else {
			startTimer(leftSec, display);	// 그게 아니면 다시 시작
		}
	});
	/* 보안코드 요청 후 타이머 작동하기 - END */
	
/************ 보안 코드 요청 만들기- END ************/
	
	
}); // $(document).ready(function() -- END 
	
//회원 수정 버튼만들기
function submitLink(form, link) {
	
	form.action = link;
	form.submit();
	
}







