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
<link
	href="../drimpc/resources/vendor/fontawesome-free/../drimpc/resources/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template-->
<link href="../drimpc/resources/css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">

	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">시간 추가</div>
			<div class="card-body">
				<div class="text-center mb-4">
					<h4>Drim PC 시간 추가</h4>
				</div>
				<form action="order_time_Process" method="get">
					<div class="form-group">
						<div class="form-label-group">
							<div class="form-group">
								<div class="form-label-group">
									<input type="text" name="user_id" class="form-control"
										required="required" autofocus="autofocus"> <label>아이디</label>
								</div>
							</div>
							<div class="form-group">
								<div class="form-label-group">
									<input type="number" name="user_time" class="form-control"
										required="required" min=0 value=0 autofocus="autofocus">
									<label>시간입력</label>
								</div>
							</div>
						</div>
					</div>
					<button type="submit" id="addBtn" class="btn btn-primary btn-block">시간
						추가</button>
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="register">회원가입</a> 
					<a class="d-block small" href="login">로그인 페이지</a> 
					<a class="d-block small" href="forgot_password">암호 분실</a>
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
