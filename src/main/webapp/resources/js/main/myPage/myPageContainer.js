$(document).ready(function() {
    $("#registedButton").click(function() {
        $.ajax({
            url : "/transaction/lendingRegist.do",
			type : "get",
            success : function(result) {
                $("#parse_area").html(result);
				$("#tab_proceed").css('border-bottom','')
				$("#tab_regist").css('border-bottom','4px solid black')
				$("#tab_request").css('border-bottom','')
				$("#tab_ended").css('border-bottom','')
            }
        });
    });
	$("#requestButton").click(function() {
        $.ajax({
            url : "/transaction/lendingRequest.do",
			type : "get",
            success : function(result) {
                $("#parse_area").html(result);
				$("#tab_proceed").css('border-bottom','');
				$("#tab_regist").css('border-bottom','');
				$("#tab_request").css('border-bottom','4px solid black');
				$("#tab_ended").css('border-bottom','');
            }
        });
    });
	$("#proceedButton").click(function() {
        $.ajax({
            url : "/transaction/lendingProceed.do",
			type : "get",
            success : function(result) {
                $("#parse_area").html(result);
				$("#tab_proceed").css('border-bottom','4px solid black');
				$("#tab_regist").css('border-bottom','');
				$("#tab_request").css('border-bottom','');
				$("#tab_ended").css('border-bottom','');
            }
        });
    });
	$("#endTranButton").click(function() {
        $.ajax({
            url : "/transaction/lendingEndTran.do",
			type : "get",
            success : function(result) {
                $("#parse_area").html(result);
				$("#tab_proceed").css('border-bottom','');
				$("#tab_regist").css('border-bottom','')
				$("#tab_request").css('border-bottom','');
				$("#tab_ended").css('border-bottom','4px solid black');
            }
        });
    });
	$("#requestButtonB").click(function() {
        $.ajax({
            url : "/transaction/borrowRequest.do",
			type : "get",
            success : function(result) {
                $("#parse_area").html(result);
				$("#tab_proceed").css('border-bottom','')
				$("#tab_request").css('border-bottom','4px solid black');
				$("#tab_ended").css('border-bottom','');
            }
        });
    });
	$("#proceedButtonB").click(function() {
        $.ajax({
            url : "/transaction/borrowProceed.do",
			type : "get",
            success : function(result) {
                $("#parse_area").html(result);
				$("#tab_proceed").css('border-bottom','4px solid black')
				$("#tab_request").css('border-bottom','');
				$("#tab_ended").css('border-bottom','');
            }
        });
    });
	$("#endTranButtonB").click(function() {
        $.ajax({
            url : "/transaction/borrowEndTran.do",
			type : "get",
            success : function(result) {
                $("#parse_area").html(result);
				$("#tab_proceed").css('border-bottom','')
				$("#tab_request").css('border-bottom','');
				$("#tab_ended").css('border-bottom','4px solid black');
            }
        });
    });
});

function approveReserve(form) {
	alert("예약을 승인했습니다.")
	form.submit();
}

function deposit(form) {
	alert("예약을 승인했습니다.")
	form.submit();
}

function cancelReserve(form) {
	alert("예약을 승인했습니다.")
	form.submit();
}
