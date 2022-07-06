//----------------------------------------------------------------
// 카테고리의 이름을 받아오는 함수
//----------------------------------------------------------------
/*function ctgOutput(response) {
	
	var ctgLink = document.querySelector('.ctg-content');
	
	// 인덱스 순서로 a태그를 생성한다.
	for(var i = 0; i < response.length; i++) {
		// a 태그를 만들고
		var a = document.createElement('a');
		// 그 a 태그를 다시 카테고리에 자식으로 추가한다.
		ctgLink.appendChild(a);
		// a 태그에 자식요소로 텍스트를 추가한다
		a.appendChild(document.createTextNode(response[i].name));
	}
}*/


//----------------------------------------------------------------
// 검색창에 response로 받은 값을 li a 에다가 넣는 함수
//----------------------------------------------------------------
function search(response) {
	
	let max = 0;
	
	// var content = document.querySelector('.dropdownBar-content ul');
	
	// List의 값이 10개가 넘어가면 10개만 넣음
	if(response.length >= 10 ) {
		max = 10;
	} else {
		max = response.length;
	}
	
	for(let i = 0; i < max; i++) {
		// content의 ul의 자식요소 li의 순번에 따라 response값을 부여한다.
		let $contentLink = $('.dropdownBar-content a:nth-child(' + (i+1) + ')');
		$contentLink.text(response[i].category_NM);
		$contentLink.attr("href", ctx + "/goods/goodsList.do?name=" + response[i].category_NM);
	}
}

//----------------------------------------------------------------
// 검색창 테두리 함수
//----------------------------------------------------------------
function seachBarRound(color) {
	
	let searchBar = document.querySelector('.search-bar');
	let searchBtn = document.querySelector('.search-btn');
	
	searchBar.addEventListener('mouseover', function() {
		this.style.boxShadow 		= "0 0 0 3px " + color;
		searchBtn.style.boxShadow 	= "0 0 0 3px " + color;
	});
	
	searchBar.addEventListener('mouseout', function() {
		this.style.boxShadow 		= "0 0 0 0";
		searchBtn.style.boxShadow 	= "0 0 0 0";
	});
	
	searchBtn.addEventListener('mouseover', function() {
		this.style.boxShadow 		= "0 0 0 3px " + color;
		searchBar.style.boxShadow 	= "0 0 0 3px " + color;
	});
	
	searchBtn.addEventListener('mouseout', function() {
		this.style.boxShadow 		= "0 0 0 0";
		searchBar.style.boxShadow 	= "0 0 0 0";
	});
}

// javaScript의 세션을 초기화한다.
function sessionClear() {
	sessionStorage.clear();
}

//----------------------------------------------------------------
// window onload
//----------------------------------------------------------------
window.onload = function() {
	
	//----------------------------------------------------------------
	//  outline 생성 jquery 함수
	//----------------------------------------------------------------
	$.fn.hoverOutline = function(color) {
		$(this).on('mouseover', function() {
			$(this).css('box-shadow', "0 0 0 3px " + color);
		});
		$(this).on('mouseout', function() {
			$(this).css('box-shadow', "0 0 0 0");
		});
		
		return this;
	}
	
	// outline 바꾸는 함수 호출
	$('#barOutline').hoverOutline('gray');
	
	//----------------------------------------------------------------
	// $(document).ready(function(){})  ( 같은 문법 $(function() )
	//----------------------------------------------------------------
	$(function() {
		
		
		//----------------------------------------------------------------
		// ajax 카테고리
		// on과 달리 one은 한번만 실행하고 더 이상 실행하지 않음
		//----------------------------------------------------------------
		
		// ajax 중복 방지		 
		/*var isRun = false;
		
		$('.bi-menu-up').one('mouseover', function() { 
			
			// ajax 중복 방지	
			if(isRun == true) { 
				return; 
			}
			
			isRun = true;

			$.ajax({
				async : false,
				url:			'/common/getCategory.do',
				type:			'post',
				data:			'',
				// 서버에서 --> js로 받는 타입
				dataType:		'json',
				// js에서 --> 서버로 보내는 타입
				contentType:	"application/json; charset=UTF-8", //(contentType의 default 값) 
				success:		function(data) {
					ctgOutput(data);
				},
				error:			function(request, status, error) {
					alert(error);
					alert('데이터 불러오기 실패');
				}
			
			});
			
		});*/
		// ajax 카테고리 - END
		
		//----------------------------------------------------------------
		// ajax 검색창에 값이 들어가면 ajax로 보내고 받기를 모두 실행
		//----------------------------------------------------------------
		$('#searchBar').on('keyup', function() {
			
			let $searchVal = $(this).val();
			
			for(let i = 0; i < 10; i++) {
				 let $contentLink = $('.dropdownBar-content a:nth-child(' + (i+1) + ')');
				 $contentLink.empty();
				 $contentLink.attr("href", "#");
			}
				
			if($searchVal == "") {
				 let $contentLink = $('.dropdownBar-content a:nth-child(1)');
				$contentLink.text('검색어를 입력해 주세요.');
								
			} else {				
			
				// json형태는 이렇게
				// let sendData = { "name" : $searchVal }; 
				// 다수의 파라미터 형태는 이렇게
				let sendData = 	"name=" + $searchVal + "&test=" + "1"; 
				// let sendData = $searchVal; // 단일 파라미터는 이렇게
				
				$.ajax({
					async: 			false,
					url:			"/common/search.do",
					type:			"get",
					/*
					서버에서 보내는 타입
					text으로 받으려면 이렇게
					dataType:		"text",
					*/
					dataType:		"json",	
					// 만약 json으로 받을거면 JSON.stringify(data)형식으로 넣어야함
					//data:			JSON.stringify(sendData),
					data:			sendData,
					/*
					ajax에서 server에 보내는 값을 JSON 형태
					contentType:	"application/json; charset=UTF-8",
					보내는 값을 Query String, form 형태  
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					*/
					success:		function(response) {
						search(response);
					},
					error:			function(request, status, error) {
		    			alert("code:"+request.status+"\n"+"message:"+
		    			request.responseText+"\n"+"error:"+error);
		   			}
		
				});
			}
			
		});
		// ajax 검색창에 값이 들어가면 END
		
		
		//----------------------------------------------------------------
		// 검색창이 포커스 되었을 때
		//----------------------------------------------------------------
		// 검색창이 focus 되었을때
		$('#searchBar').on('focus', function() {
			$('.dropdownBar-content').css('display', 'block');
			$('.dropdownBar-content *').css('display', 'block');
			$("#barOutline").css('box-shadow', "0 0 0 3px gray");
		});
			
		// focus가 해제 됐을때
		$('#searchBar').on('focusout', function() {
			$('.dropdownBar-content').css('display', 'none');
			$('.dropdownBar-content *').css('display', 'none');
			$("#barOutline").css('box-shadow', "0 0 0 0");
		});
		// *******ajax 검색창이 focus 되었을때
		
		//
		 
		let userInfoId = sessionStorage.getItem("userInfoId");
		
		$("#a-link-rtTalk").on('mouseover', () => {
			console.log("userInfoId = " + userInfoId);
			if (userInfoId == null) {
				$("#a-link-rtTalk").css("color", "red");
			} else {
				$("#a-link-rtTalk").css("color", "green");
			}
		});
		
		
	});// == $(document).ready(function() { 과 같은 문법
	
	
	
	

} // window onload - END 





