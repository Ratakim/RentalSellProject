
//-------------------------------------------------------
// 상품 등록시 항목 검사
//-------------------------------------------------------
function checkForm(form) {
	
    if (form.goods_title.value.length < 2) {
    	alert("제목을 입력해주세요.");
    	form.goods_title.focus();
		myFunction("#error-name", 'block');
    	return false;
    } else {
		myFunction("#error-name", 'none');
		
	}
	
	if (!form.category_ID.value) {
    	alert("세부 카테고리를  선택해주세요.");
    	
		var offset2 = $('.rm-cn-it').offset();
		$('html, body').animate({scrollTop : offset2.top}, 400);
		myFunction(".error-ctg", 'block');
    	return false;
    } else {
		myFunction(".error-ctg", 'none');
	}
	
	if (!form.imageRegist.value) {
    	alert("이미지를 입력해주세요.");
    	var offset1 = $('.imageRegist').offset();
		$('html, body').animate({scrollTop : offset1.top}, 400);
		myFunction(".eeeee-e", 'block');
    	return false;
    } else {
		myFunction(".eeeee-e", 'none');
	}
	
	if (!form.deal_region.value) {
    	alert("지역을 입력해주세요.");
    	form.deal_region.focus();
		var offset2 = $('.region-input').offset();
		$('html, body').animate({scrollTop : offset2.top}, 400);
		myFunction(".error-region", 'block');
    	return false;
    } else {
		myFunction(".error-region", 'none');
	}
	
	if (form.goods_desc.value.length< 10) {
    	alert("내용을 입력해주세요.");
    	form.goods_desc.focus();
		var offset2 = $('.rm-gi-it').offset();
		$('html, body').animate({scrollTop : offset2.top}, 400);
		myFunction(".error-explain", 'block');
    	return false;
    } else {
		myFunction(".error-explain", 'none');
	}
	
	if (form.goods_price.value.length < 3) {
    	alert("금액을 입력해주세요.");
    	form.price_hour.focus();
		var offset3 = $('.rm-price-hour').offset();
		$('html, body').animate({scrollTop : offset3.top}, 400);
		myFunction(".error-price", 'block');
    	return false;
    } else {
		myFunction(".error-price", 'none');
	}
	
	/*form.action = '/common/main.go';*/
	form.submit();
	

}
//-------------------------------------------------------
// 항목검사시 display 스타일 바꿔주기 
//-------------------------------------------------------
function myFunction(name, value) { 
	//$(name).css('display', value);
	document.querySelector(name).style.display = value;
	//document.getElementById(name).style.display = value; 
}



//-------------------------------------------------------
// 중분류 칸에 요소 넣기
//-------------------------------------------------------
function categoryOutput(response) {
	
	let ctgLink = document.querySelector('.rm-cn-itt-middle-a');
	
		for(let i = 0; i < response.length; i++) {
			let li = document.createElement('li');
			ctgLink.appendChild(li);
			let button = document.createElement('button');
			li.appendChild(button);	
			button.appendChild(document.createTextNode(response[i].category_NM));
			li.setAttribute('class', "large-a");
			button.setAttribute('class', "large-a-s");
			button.setAttribute('type', "button");
			button.setAttribute('id', response[i].category_ID);
			button.setAttribute('onclick' , "bringSmallCategory(this);");
		}
}

//-------------------------------------------------------
// ajax를 이용하여 중분류(DB) 가져오기
//-------------------------------------------------------
function bringCategory(btn) {

	
	//선택된 대분류 따로 빼서 '선택된 카테고리'에 보여주기
	let $selectedCtg = $(btn).text();
	document.getElementById('rm-cn-iff').innerText = $selectedCtg;
	
	// 대분류가 선택되면 중분류칸 비우기
	$(".rm-cn-itt-middle-a").empty();
	$("#rm-cn-iffS").empty();
	$("#middle-ctg").val("");
	let $bringCtg = $(btn).text();
	console.log(btn);
	console.log($bringCtg);
	console.log(123456);
	let sendData = {"category_NM" : $bringCtg };
	console.log(sendData);
	
	$.ajax({
			async: 			false,
			url:			"/goods/getCategoryM.do",
			type:			"get",
			// js에서 sendData -> controller String name
			data:			sendData,
			// js -> controller 보내주는 타입
			dataType:		"json",
			// controller -> js contentType
			contentType:	"application/json; charset=UTF-8",
			success:	function(response) {
				categoryOutput(response);
			},
			error:			function(request, status, error) {
    			alert("code:"+request.status+"\n"+"message:"+
    			request.responseText+"\n"+"error:"+error);
   			}
		
	});
}

//-------------------------------------------------------
// 소분류 칸에 요소 넣기
//-------------------------------------------------------
function categoryOutput2(response) {
	
	let ctgLink = document.querySelector('.rm-cn-itt-small-a');
	
		for(let i = 0; i < response.length; i++) {
			let li = document.createElement('li');
			ctgLink.appendChild(li);
			let button = document.createElement('button');
			li.appendChild(button);	
			button.appendChild(document.createTextNode(response[i].category_NM));
			li.setAttribute('class', "large-a");
			button.setAttribute('class', "large-a-s");
			button.setAttribute('type', "button");
			
		}
}

//-------------------------------------------------------
// ajax를 이용하여 소분류(DB) 가져오기
//-------------------------------------------------------
function bringSmallCategory(btn) {

	//선택된 중분류 따로 빼서 '선택된 카테고리'에 보여주기
	
	
	let $selectedCtg = $(btn).text();
	let $ctg_id = $(btn).attr("id");
	console.log($ctg_id);
	
	document.getElementById('rm-cn-iffS').innerText = $selectedCtg
	$("#middle-ctg").val($ctg_id);
	
	// 중분류가 선택되면 소분류칸 비우기
	$(".rm-cn-itt-small-a").empty();
	
	let $bringCtg = $(btn).text();
	console.log(btn);
	console.log($bringCtg);
	
	let sendData = {"category_NM" : $bringCtg };
	
	console.log(sendData);
	
	$.ajax({
			async: 			false,
			url:			"/goods/getCategoryS.do",
			type:			"get",
			// js에서 sendData -> controller String name
			data:			sendData,
			// js -> controller 보내주는 타입
			dataType:		"json",
			// controller -> js contentType
			contentType:	"application/json; charset=UTF-8",
			success:	function(response) {
				categoryOutput2(response);
			},
			error:			function(request, status, error) {
    			alert("code:"+request.status+"\n"+"message:"+
    			request.responseText+"\n"+"error:"+error);
   			}
		
	});
}

//-------------------------------------------------------
// 이미지파일 업로드 및 취소버튼 넣기
// 참조
// https://coco-log.tistory.com/161
// https://developer.mozilla.org/ko/docs/Web/API/FileReader/readAsDataURL
//-------------------------------------------------------
function previewFiles() {

	var preview = document.querySelector('#preview');
	var files   = document.querySelector('input[type=file]').files;
	
	function readAndPreview(file) {
			
		    // `file.name` 형태의 확장자 규칙에 주의하세요
		if ( /\.(jpe?g|png|gif)$/i.test(file.name) ) {
			var reader = new FileReader();
			
			reader.addEventListener("load", function () {
				var image = new Image();
				image.height = 100;
				image.title = file.name;
				image.src = this.result;
				preview.appendChild(image);
		    }, false);
		
		    reader.readAsDataURL(file);
		}
		
	}
	
	// 만약 파일이 존재한다면, 파일 목록을 
  	if (files) {
		[].forEach.call(files, readAndPreview);
		console.log(files.length);
 	}

}


        
        
//--------------------------------------------------------------
// 파일 읽은 핸들러 객체 생성
//--------------------------------------------------------------
/*const FileHandler = {
	
	readURL: (fileInput) => {
		
		let ul = document.querySelector(".rm-ir-m");
		let files = fileInput.files;
		
		function readFile(file) {
			if ( /\.(jpe?g|png|gif)$/i.test(file.name) ) {
				// 파일리더 인스턴스 생성
				var reader = new FileReader();
				
				reader.addEventListener('load', function(e) {
					
					files.forEach(file => {
						// ul에 넣을 innerHTML을 만든다.
						ul.innerHTML 	+= 	"<li id='${file.lastModified}' class='imgClass'>"
										+ 		"<img width='200px' height='200px' src='" + e.target.result + "' title='"+ file.name +"'/>" 
										+ 		"<button type='button' data-index='${file.lastModified}' class='imgButton file-remove'></button>"
										+ 	"</li>";
					});
					
				}, false);
				
				// dataUrl을 읽는다.
				reader.readAsDataURL(file);
			
			} 
		}// function readFile - END
		 
		// 만약 파일 데이터 하나라도 들어있으면 실행
			
		if(files) {
			[].forEach.call(files, readFile());
			console.log(files);
			console.log(files.length);
			document.querySelector('#fileCount').innerText = files.length;
		}
		
	}, // readURL - END 
	
	removeFile: (btn) => {
		
		 document.addEventListener('click', (e) => {
			
			// 타겟의 클래스 리스트에 file-remove가 없으면 구문을 나간다.
			if(e.target.classList.contains('file-remove') != true) return;
			console.log("id = " + e.target.dataset.index);
			
			// 타겟의 아이디를 가져온다.
			const removeTargetId = e.target.dataset.index;
			const removeTarget = document.getElementById(removeTargetId);
			const files = document.querySelector('#imageInput').files;
			const dataTranster = new DataTransfer();
			
			Array.from(files).filter(file => file.lastModifued != removeTargetId).forEach(file => {
					dataTranster.items.add(file);
			});
			
			document.querySelector('#imageInput').files = dataTranster.files;
			
			document.querySelector('#fileCount').innerText = document.querySelector('#imageInput').files.length;
			console.log(document.querySelector('#imageInput').files.length);
			console.log(document.querySelector('#imageInput').files);
			
			removeTarget.remove();
		});
		
	} // removeFile - END
	 
} // const FileHandler 객체 - END */

//------------------------------------------------------------------------------
// isEmpty() 객체나 배열이 비어 있는지 확인하는 함수
//------------------------------------------------------------------------------
const isEmpty = function(val) {
	if (val === '' || val === null || val === undefined ||
		(val !== null && typeof val === "object" && !Object.keys(val).length)) {
			return true;
	} else {
		return false;
	}
}

//------------------------------------------------------------------------------
// window.onload = function()
//------------------------------------------------------------------------------
window.onload = function() {
	
	// 주소 불러오기 ()다음 카카오 API에서 따옴)
	document.getElementById("address_kakao").addEventListener("click", function(){
		new daum.Postcode({
			oncomplete: function(data) {
				document.querySelector('.region-input').value = data.address;
			}
		}).open();
	});
	

	//유사 배열이 들어갈 임시 객체 생성
	let dataDel = new DataTransfer();
	
	const handler = {
		
		/* 해결해야할 문제 */
		// 같은 이름과 타입의 파일이 들어왔을 때 처리할 것이 필요하다.
		
	    init() {
	        const fileInput = document.querySelector('#imageInput');
	        const preview = document.querySelector('.rm-ir-m.preview');
	        
	        // 파일의 크기를 조정한다.
	        if (fileInput.files && fileInput.files[0]) {

				var maxSize = 5 * 1024 * 1024; // 10MB
				var fileSize;
				for(var i = 0; i < fileInput.files.length; i++){
					fileSize[i] = fileInput.files[i].size;
		
					if(fileSize[i] > maxSize){
						alert("첨부파일 사이즈는 5MB 이내로 등록 가능합니다.");
						fileInput.files.remove(i);
					}
				}
				console.log(fileSize);
			}
			
	        fileInput.addEventListener('change', () => {
		
	            
	            // 임시 유사배열에 지금 넣은 파일 유사배열을 넣는다.
	            
	           	// dataDel에 지금 들어온 파일을 합친다.
	           	// 객체는 isEmpty()로 검사를 해야 빈 객체인지 알 수 있다.
	           	if(!isEmpty(dataDel.files)) {
					// 삭제 후 상태인 유사배열의 길이
	           		let delLeng = dataDel.files.length;

					Array.from(fileInput.files)
			            .forEach(file => {
			            dataDel.items.add(file);
			         });
					
					fileInput.files = dataDel.files;
					console.log('fileInput = ' + fileInput.files); 
								
				// dataDel이 비어 있다면
				} else {
					// 임시 객체에 파일리스트를 저장
			        Array.from(fileInput.files)
			            .forEach(file => {
			            dataDel.items.add(file);
			         });
					
					fileInput.files = dataDel.files;
					console.log('fileInput = ' + fileInput.files);
					
				}
				
				// 같은 목록의 파일이 들어오면 중복을 삭제한다.
				/*let dataTranster = new DataTransfer();
				
				Array.from(fileInput.files)
			            .forEach(file => {
			            dataTranster.items.add(file);
			         });
				console.log('dataTranster = ' + dataTranster.files.length);
					
				// 12개 이상이라면 그 뒤의 목록을 지운다.
				if (dataTranster.files.length > 12) {
					for(var i = 12; i < dataTranster.files.length; i++) {
						dataTranster.items.remove(dataTranster.files.item.arguments);
						console.log(dataTranster.files.item.arguments);
					}
					fileInput.files = dataTranster.files;
				}
				*/
				
				// 파일의 개수를 카운트한다.
	            document.querySelector('#fileCount span').innerText = fileInput.files.length;

	            // 유사 배열을 배열로 바꿔준다.
	           	const files = Array.from(fileInput.files);
	           	console.log(files);
	           	
	           	[].forEach.call(files, (file) => {
	           	// 안의 내용을 초기화하고 다시 쓴다.
	           		let reader = new FileReader();
	           	
	          		preview.innerHTML = '';
	          	
		          	reader.addEventListener('load', function(e) {
			
			                preview.innerHTML += `
				                <li id="${file.lastModified}" class="imgClass">
				                    <img width="200px" height="200px" src="${e.target.result}" title="${file.name}"/>
				                    <button type="button" data-index='${file.lastModified}' class='imgButton file-remove'></button>
				                </li>`;

			             
		            }); // reader.addEventListener - END
		            
	            	// dataUrl을 읽는다.
					reader.readAsDataURL(file);
					
	            }); // [].forEach.call - END
	            
	            document.querySelector('#fileCount span').innerText = fileInput.files.length;
	        });
	        
	    }, // init() - END 
	
	    removeFile: () => {
	        document.addEventListener('click', (e) => {
			// 타겟의 클래스리스트에 file-remove가 있으면
			if(e.target.classList.contains('file-remove') == true) {
				//타겟의 data-index의 값을 가져온다.
		        const removeTargetId = e.target.dataset.index;
		        // 타겟의 인텍스를 id로 갖는 부모 요소를 찾는다.
		        const removeTarget = document.getElementById(removeTargetId);
		        const files = document.querySelector('#imageInput').files;
		        // 파일리스트를 변환해줄 DataTransfer를 선언한다.
		        const dataTranster = new DataTransfer();
		
		        // document.querySelector('#file-input').files =
		        //             Array.from(files).filter(file => file.lastModified !== removeTarget);

				// Array에 filter에 삭제된 파일의 인덱스 외의 다른 파일들을 dataTranster에 집어 넣는다.
		        Array.from(files)
		            .filter(file => (file.lastModified) != removeTargetId)
		            .forEach(file => {
		            dataTranster.items.add(file);
		         });
		        console.log(dataTranster.files);
		        
		        // 임시 저장 객체에 현재 변환된 리스트를 넣는다.
				dataDel = dataTranster;
				
				// 변환값을 input 파일리스트에 넣어 교체한다.
		        document.querySelector('#imageInput').files =  dataTranster.files;
				document.querySelector('#fileCount span').innerText =  document.querySelector('#imageInput').files.length;
				console.log(document.querySelector('#imageInput').files);
				
				// 타겟의 부모요소를 지운다.
		        removeTarget.remove();
		    
		    // 타겟의 클래스 이름이 file-remove가 아니면 false 반환
		    } else {
				return false;		
			}
	    })
	    }
	}
	document.querySelector('#fileCount span').innerText = '0';
	handler.init()
	handler.removeFile()
	
	/*FileHandler.removeFile();*/
}