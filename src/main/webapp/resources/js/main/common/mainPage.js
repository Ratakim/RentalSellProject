/*
function resizeApply() { 
	var minWidth = 1200; 
	var body = document.getElementsByTagName('body')[0]; 
	if (window.innerWidth < minWidth) { 
		body.style.zoom = (window.innerWidth / minWidth); 
	} 
	else body.style.zoom = 1; 
} 

window.onload = function() { 
	window.addEventListener('resize', function() { 
		resizeApply(); 
		}); 
} 

resizeApply();
*/

window.onload = function() {
	const part = document.querySelectorAll('.action-part');
	window.addEventListener('scroll', function(){
		let partOffsetArr = [];
	    part.forEach((e) => {
			/*
			DOM.getBoundingClientRect()는 해당 요소가 페이지의 어디에 있는지 관련 정보를 제공한다.
			top, y 	=> 화면 상단 부터 대상의 처음 위치 값
			bottom 	=> 화면 상단 부터 대상의 끝 위치 값
			left, x => 화면 좌측 부터 대상의 처음 위치 값
			right 	=> 화면 좌측 부터 대상의 끝 위치 값
			width 	=> 대상의 길이
			height 	=> 대상의 높이
			출처 : https://webisfree.com/2020-09-21/[자바스크립트]-엘리먼트의-페이지-위치-알아내기-getboundingclientrect
			*/
			let partOffset = e.getBoundingClientRect().top;
			
	        /*
	        화면의 Y축의 상단값
			window.pageYOffset
			화면의 Y축의 하단값
			window.pageYOffset + window.innerHeight
			출처: https://divlook.tistory.com/9 [div_look:티스토리]
			*/
	        + window.pageYOffset;
	        console.log(e.getBoundingClientRect().top);
	        partOffsetArr.push(partOffset);
	        console.log(partOffset);
	    });
	     /*
        animation-name: 			myShorthand;
        animation-duration: 		3s;
        animation-timing-function: 	ease-in-out;
        animation-delay: 			1s;
        animation-iteration-count: 	3;
        animation-direction: 		alternate;
        
        animation: myShorthand 3s ease-in-out 1s 3 alternate;
    	*/
	    partOffsetArr.forEach((offset, idx) => {
				console.log(window.scrollY + " & " + offset + " & " + idx);
				if(window.scrollY > offset){
					part[idx].style.animation = 
						'fade  1s  ease-in-out 200ms 1 both';
				} else {
					part[idx].style.animation =
						'fadeout  1s  ease-in-out 200ms 1 both';
				}
	          
	    });
	    
	});

}


