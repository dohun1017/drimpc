<%@page import="com.mysql.cj.Session"%>
<%@page import="com.drimsys.dto.UserVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="utf-8">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>시간 추가</title>

<!-- Custom fonts for this template-->
<!-- <link -->
<!-- 	href="../drimpc/resources/vendor/fontawesome-free/resources/css/all.min.css" -->
<!-- 	rel="stylesheet" type="text/css"> -->

<!-- Custom styles for this template-->
<link href="../drimpc/resources/css/sb-admin.css" rel="stylesheet">
<script src="../drimpc/resources/js/jquery-3.4.1.js"></script>
<script type="text/javascript">
$(function() {
	$("#addBtn").click(function(e) {
		
		e.preventDefault();
		var user_id = document.getElementById("user_id").value;
		var user_time = document.getElementById("user_time").value;
		var login_id = document.getElementById("login_id").value;
		var form = {
				user_id : user_id,
				user_time : user_time
		}
		$.ajax({
			url : "/drimpc/order_time_Process",
			type : "GET",
			data : form,
			contentType : "application/json; charset=utf-8;",
			dataType : "json",
			success : function(data) {
				alert("시간 추가 성공\n아이디 : " + data.user_id + "\n시간 : "+data.user_time);
				if(login_id == null)
					location.href = "/drimpc/login";
				else if(login_id == "admin")
						location.href = "/drimpc/admin_product";
				else
					location.href = "/drimpc/user_main";
			},
			error : function(data) {
				alert("시간 추가 실패");
			}
		});
	});
});
</script>
</head>
<input type = "hidden" id = "login_id" value = "${login.user_id}" }/>
<body class="bg-dark">

	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">시간 추가</div>
			<div class="card-body">
				<div class="text-center mb-4">
					<h4>Drim PC 시간 추가</h4>
				</div>
				<form>
					<div class="form-group">
						<div class="form-label-group">
							<div class="form-group">
								<div class="form-label-group">
									<input type="text" id="user_id" class="form-control"
										required="required" autofocus="autofocus"> <label>아이디</label>
								</div>
							</div>
							<div class="form-group">
								<div class="form-label-group">
									<input type="number" id="user_time" class="form-control"
										required="required" min=0 value=0 autofocus="autofocus">
									<label>시간입력</label>
								</div>
							</div>
						</div>
					</div>
					<button id="addBtn" class="btn btn-primary btn-block">시간
						추가</button>
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="register">회원가입</a> <a
						class="d-block small" href="login">로그인 페이지</a> <a
						class="d-block small" href="forgot_password">암호 분실</a> <a
						class="d-block small" href="user_main">메인 화면으로 돌아가기</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="../drimpc/resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="../drimpc/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="../drimpc/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

</body>

</html>
