
/* datetime picker 달력 넣기 */
$(function () {
        $('#datetimepicker5').datetimepicker({
			locale:'ko',
			format: 'YYYY년 MM월 DD일 HH시',
			icons: {
                time: 'bi bi-clock',
                date: 'bi bi-calendar-date',
                up: 'bi bi-arrow-up',
                down: 'bi bi-arrow-down',
                previous: 'bi bi-chevron-left',
                next: 'bi bi-chevron-right',
                today: 'bi bi-calendar-check-o',
                clear: 'bi bi-trash',
                close: 'bi bi-times'
            }
		});
        $('#datetimepicker6').datetimepicker({
			
			format: 'YYYY년 MM월 DD일 HH시',
            useCurrent: false,
			icons: {
                time: 'bi bi-clock',
                date: 'bi bi-calendar-date',
                up: 'bi bi-arrow-up',
                down: 'bi bi-arrow-down',
                previous: 'bi bi-chevron-left',
                next: 'bi bi-chevron-right',
                today: 'bi bi-calendar-check-o',
                clear: 'bi bi-trash',
                close: 'bi bi-times'
            }
        });
        $("#datetimepicker5").on("change.datetimepicker", function (e) {
            $('#datetimepicker6').datetimepicker('minDate', e.date);
        });
        $("#datetimepicker6").on("change.datetimepicker", function (e) {
            $('#datetimepicker5').datetimepicker('maxDate', e.date);
        });
});
/*
function splitString(jbString) {
	var set = " 년월일시";
	var jbStringTemp = jbString;

  for( var j = 0; j < set.length; j++) {
     	const result = jbStringTemp.split(set.charAt(j));
     	jbStringTemp = null;
      
      	for (var i = 0; i < result.length; i++){
          
          	jbStringTemp += result[i];
      	}
    }
	return jbStringTemp;
}
*/

/* tempus로 가져온 문자열 바꾸기 */
function replaceString(jsString) {
	
	console.log(jsString);
	var jsString1 = jsString.replace('년','-');
	var jsString2 = jsString1.replace('월','-');
	var jsString3 = jsString2.replace('일',' ');
	var jsString4 = jsString3.replace('시',':00');
	var jsString5 = jsString4.replace(' ','');
	var jsString6 = jsString5.replace(' ','');
	var jsString7 = jsString6.replace(' ','');
	console.log(jsString7);
	
	return jsString7;
}



/* 예약 기간 총 시간 구하기 + 총이용요금 구하기 */
function calcuratorDate(){
	
	var picker5 		= document.getElementById('datetimepicker5').value;
	var picker6 		= document.getElementById('datetimepicker6').value;
	
	picker5 = replaceString(picker5);
	picker6 = replaceString(picker6);
	console.log(picker5);
	console.log(picker6);
	var start_date		= new Date(picker5);
	var end_date 		= new Date(picker6);
	console.log(start_date);
	console.log(end_date);
	var millisec 	= end_date - start_date;
	console.log(millisec);
	if(millisec >=0 ){
	const hour		= millisec/(1000 * 60 * 60);
	
	const pricePerHour = document.getElementById('hiddenPrice').innerHTML;
	console.log(pricePerHour);
	const wholeprice = hour*pricePerHour;
	console.log(wholeprice);
	var pricecomma= wholeprice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	
	const unit		= new String('원');
	const unitByHour = new String('시간');
	
	const finalHour	= hour + unitByHour;
	const finalResult = pricecomma + unit;
	document.getElementById('totalPrice').value= wholeprice;
	document.getElementById('hour').innerText= finalHour;
	document.getElementById('wholePrice').innerText= finalResult;
	document.getElementById('borrow_period_start').value= picker5;
	document.getElementById('borrow_period_end').value= picker6;
	}
}

/*이미지슬라이더*/
$(document).ready(function(){
	$('.slider').slick({
		prevArrow: $('.slick-prev'),
		nextArrow: $('.slick-next'),
		dots:true
	});
});


//-------------------------------------------------------
// 예약하기 등록시 항목 검사
//-------------------------------------------------------
function checkForm(form) {
	
    if (!form.borrow_period_start.value) {
    	alert("예약 기간을 선택해주세요");
    	form.borrow_period_start.focus();
		return false;
    } else {
		replaceString(form.borrow_period_start.value)
		console.log(replaceString(form.borrow_period_start.value));
	}
	
	if (!form.borrow_period_end.value) {
    	alert("예약 기간을 선택해주세요");
    	form.borrow_period_end.focus();
		return false;
    } else {
		replaceString(form.borrow_period_end.value)
		console.log(replaceString(form.borrow_period_end.value));
	}
	
	
	
	/*form.action = '/common/main.go';*/
	form.submit();
	
}

