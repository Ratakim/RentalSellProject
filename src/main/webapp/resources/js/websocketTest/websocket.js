var ws;

function openSocket() {
	
    if(ws !== undefined && ws.readyState !== WebSocket.CLOSED ){
        writeResponse("WebSocket is already opened.");
        return;
    }
    
    //웹소켓 객체 만드는 코드
    ws = new WebSocket("ws://localhost:8083/rentaltalk.do");
    
    // 소켓 오픈시
    ws.onopen = function(event){
	
        if(event.data === undefined){
      		return;
        }

        writeResponse(event.data);
        
    }; // ws.onopen - END 
    
    // 소켓 메시지를 보낸다.
    ws.onmessage = function(event){
        console.log('writeResponse');
        console.log(event.data);
        // 메시지 창에 문자를 쓴다.
        writeResponse(event.data);
    };
    
    // 소켓을 닫는다.
    ws.onclose = function(event){
	
        writeResponse("대화 종료");
    }
    
}

// 메세지 출력
/*function send() {
   	// var text=document.getElementById("messageinput").value+","+document.getElementById("sender").value;
   	if (document.getElementById("messageinput").value == ''){return;}
   	var text 	= 	document.getElementById("messageinput").value
   	 			+	","
   	 			+	document.getElementById("sender").innerText;
   	console.log(text);
   	ws.send(text);
   	$('#messageinput').css('height', '60px');
   	document.getElementById("messageinput").value = '';
   	text = "";
}*/

function closeSocket() {
    ws.close();
}

function writeResponse(text) {
    document.getElementById("messages").innerHTML += `<br/> ${text}`;
}

function clearText() {
    console.log(messages.parentNode);
    document.getElementById("messages").innerText = '';
}

// +버튼을 누르면 파일 추가가된다.
let fileInput = () => {
	let input = document.querySelector("#plusFile");
	input.click();
}

// 출처 : https://detegice.github.io/read-local-file-using-javascript/
let writeFile = (sendName, revName, fileName, msg) => {
	
	if(sendName == "" || revName == "" || fileName == "" || msg == "") return false;
	
	var defaultPath = "C:\\data\\rentaltalk";
	
	var rawFile = new XMLHttpRequest();
	
	var fullPath = defaultPath + "\\" + sendName + "\\" + revName + "\\" + fileName;
	
	rawFile.open("GET", fullPath, false);
	rawFile.onreadystatechange = function () {
		if(rawFile.readyState === 4) {
			
			if(rawFile.status === 200 || rawFile.status == 0) {
				
				var allText = rawFile.responseText;
				alert(allText);
				
			}
		}
	}
	
	rawFile.send(null);
}

function fetchFile(filePath) {
	// txt 파일 읽기
	fetch(filePath)
	  .then(response => response.text())
	  .then(text => console.log(text))
	
	/*// json 파일 읽기
	fetch(filePath)
	  .then(response => response.json())
	  .then(jsonResponse => console.log(jsonResponse))*/
  
}

/* html이 준비되면 */
$(document).ready(function () {
	
	const adjustHeight = 30; 
	const msginput = $('#messageinput');
	
	// 현재 Height
	let nowHeight;
	// 현재 줄수
	let nowRows;
	// 전의 줄수
	let pastRows = 0;
	
	msginput.on("propertychange change keyup paste input", () => {
		nowRows = msginput.val().length / 15;
		nowHeight = msginput.css('height');
		
		console.log("nowHeight=" + nowHeight);
		console.log("pastRows=" + pastRows);
		console.log("nowRows=" + nowRows);
		console.log("val-length=" + msginput.val().length);
		console.log(nowHeight.split('px')[0]);
		
		if ((msginput.val().length % 15) == 0) {
			
			$(this).css('overflow', 'auto' );
			
			// 전의 로우보다 지금의 로우가 크다면
			if (pastRows < nowRows) {
				msginput.val(msginput.val() + '\n');
				msginput.css('height', 
				(parseInt(nowHeight.split('px')[0]) + adjustHeight) + 'px' );
				pastRows++;
			
			// 전의 로우보다 지금의 로우가 작으면	
			} else if (pastRows > nowRows){
				console.log((nowHeight.split('px')[0] - adjustHeight));
				msginput.css('height', 
				(parseInt(nowHeight.split('px')[0]) - adjustHeight) + 'px' );
				pastRows--;
			} else {
				console.log("doing nothing");
			}
			
		} 
		
	}); // msginput.on - END 
	
	// send 버튼을 누르면
	$("#sendBtn").on("click", function send() {
	   	// var text=document.getElementById("messageinput").value+","+document.getElementById("sender").value;
	   	if (document.getElementById("messageinput").value == ''){return;}
	   	var text 	= 	document.getElementById("messageinput").value
	   	 			+	","
	   	 			+	document.getElementById("sender").innerText;
	   	console.log(text);
	   	ws.send(text);
	   	
	   	// 높이와 이전 로우를 초기화한다.
	   	$('#messageinput').css('height', '60px');
	   	pastRows = 0;
	   	// text와 textarea의 value를 초기화한다.
	   	document.getElementById("messageinput").value = '';
	   	text = "";
	});
	
	/*var txtPath = '<c:out value="${textPath}"/>';*/
	/*var txtPath = ${textPath};*/
	console.log(txtPath + "/test.txt");
	console.log(txtPath2 + "/test.txt");

	var path = txtPath2 + "/test.txt";
	fetchFile(path);
		
});

	