<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Insert title here</title>
<script src="../drimpc/resources/js/jquery-3.4.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#testBtn").click(function(){
		$.ajax({ 
			type: 'GET', 
			url: "/drimpc/testProcess", //실행 JAVA URL 
			dataType : 'xml', //return 형식 xml 
			data : { 
				MEMO :'xml 실행 메모' //보낼 테이터가 있다면 세팅 
			}, 
			success: function(result) { //XML 데이터중 STATUS 변수값의 내용을 가져옴
// 					if($(result).find('status').text() == "OK"){
// 						alert("xml OK");
// 					}
					$(result).find('item').each(function(){
						$("#result1").text("");
						$("#result1").append("STATUS : "+$(this).find("status").text()+"<br>");
						$("#result1").append("ID : "+$(this).find("id").text()+"<br>");
						$("#result1").append("TEXT : "+$(this).find("text").text()+"<br>");
					});
					alert("xml 전송 성공 하였습니다."); 
			}, 
			error: function(result) {
				$("#result1").text("error");
				alert("error !"); 
			}
		})
	});
	
	/* class TestVO{
		List<Byte> byteList;
		List<Integer> dataLengths;
		int totalLength;
		List<String> description;
	} */
	
	$("#byteBtn").click(function(){
		$.ajax({
			type: 'GET', 
			url: "/drimpc/byteProcess", 
			dataType : 'json', 
			success: function(result) {
				alert("byte 수신 성공 하였습니다.");
// 				$("#byteDiv").text("");
// 				for(var i = 0; i<result.length; i++){
// 					$("#byteDiv").append("Byte : ");
// 					for(var j = 0; j<result[i].byteList.length; j++){
// 						$("#byteDiv").append(result[i].byteList[j]);
// 					}
					
// 				}
			}, 
			error: function(result) {
				$("#byteDiv").text("error");
				alert("error !"); 
			}
		})
	});
});
</script>
</head>
<body>
<button id = "testBtn">testBtn</button>
<div id = "result1"></div>
<button id = "byteBtn">byteBtn</button>
<div id = "byteDiv"></div>
</body>
</html>