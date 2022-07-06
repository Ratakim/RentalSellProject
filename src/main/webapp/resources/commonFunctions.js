/**
 *      공통 기능
 */

//-------------------------------------------------------
// 책 등록시 항목 검사
//-------------------------------------------------------
function checkForm(form) {
	
	
    if (!form.book_title.value) {
    	alert("책의 제목을 입력하십시오.");
    	form.book_title.focus();
    	return false;
    }
    
    if (!form.book_price.value) {
    	alert("책의 가격을 입력하십시오.");
    	form.book_price.focus();
    	return false;
    }
    if (isNumberCheck(form.book_price.value) == false) {
    	alert("책의 가격은 숫자만 입력하셔야 합니다.");
    	form.book_price.focus();
    	return false;
    }
    
    if (!form.book_count.value) {
    	alert("책의 수량을 입력하십시오.");
    	form.book_count.focus();
    	return false;
    }
    
    if (isNumberCheck(form.book_count.value) == false) {
    	alert("책의 수량은 숫자만 입력하셔야 합니다.");
    	form.book_count.focus();
    	return false;
    }
    
    if (!form.author.value) {
    	alert("저자의 이름을 입력하십시오.");
    	form.author.focus();
    	return false;
    }
    // 저자 이름은 2자 이상 30자 이하 이어야합니다.
    if ((form.author.value.length < 2) || (form.author.value.length > 30)) {
    	alert("이름은 2자 이상 30자 이하이어야 합니다.");
    	form.author.focus();
    	return false;
    }
    if (isAlphaNumCheck(form.author.value) == false) {
    	alert("이름은 영문자와 숫자만 가능합니다.");
    	form.author.focus();
    	return false;
    }
    
    if (!form.publishing_com.value) {
    	alert("출판사의 이름을 입력하십시오.");
    	form.publishing_com.focus();
    	return false;
    }
    
    if (!form.book_content.value) {
    	alert("책의 내용을 입력하십시오.");
    	form.book_content.focus();
    	return false;
    }
    
    if (!form.discount_rate.value) {
    	alert("할인율을 입력하십시오.");
    	form.discount_rate.focus();
    	return false;
    }
    
    if (isNumberCheck(form.discount_rate.value) == false) {
    	alert("할인율은 숫자만 입력하셔야 합니다.");
    	form.discount_rate.focus();
    	return false;
    }
    
    var year = document.getElementById('publishing_year');
    var month = document.getElementById('publishing_month');
    var day = document.getElementById('publishing_day');
    
    // selectedIndex : 화면에서 선택된 태그 값의 인덱스번호
    alert(year.options[year.selectedIndex].value + month.options[month.selectedIndex].value
    		+ day.options[day.selectedIndex].value);
    
    // 1자리면 ? 0 + 날짜 : 날짜
    var yy = year.options[year.selectedIndex].value;
    var mm = (month.options[month.selectedIndex].value.length == 1)
    		 ? "0" + month.options[month.selectedIndex].value 
    		 : month.options[month.selectedIndex].value;
    		 
    var dd = (day.options[day.selectedIndex].value.length == 1)
    		 ? "0" + day.options[day.selectedIndex].value 
    		 : day.options[day.selectedIndex].value;
    
    var yyyymmdd = yy + mm + dd;
    alert(yyyymmdd);
    
   // Form에 숨겨져 있는 변수 publishing_date에 값을 넣는다.
	document.bookWriteform.publishing_date.value = yyyymmdd;
	
	document.bookWriteform.action = "/goods/bookRegister.do";
	document.bookWriteform.submit();
    
}

//------------------------------------------------------
// 각종 검사
//------------------------------------------------------

// 숫자 검사
function isNumberCheck(inputVal) {
	var var_normalize = /[^0-9]/gi; // 정규식
	if (var_normalize.test(inputVal) == true) 	return false;
	else 										return true;
}

// 영문자와 숫자 검사
function isAlphaNumCheck(input) {
	var chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	return containsCharsOnly(input, chars);
}

function containsCharsOnly(input, chars) {
	for (var inx = 0; inx < input.length; inx++) {
		// indexOf 안에 charAt으로 각 문자를 쪼개서 검사했을때 없는 값이 나오면 indexOf는 -1을 반환한다.
		if (chars.indexOf(input.charAt(inx)) == -1) {	 
			return false;
		}
	}
}