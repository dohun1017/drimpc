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

<title>비밀번호 초기화</title>

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
			<div class="card-header">비밀번호 초기화</div>
			<div class="card-body">
				<div class="text-center mb-4">
					<h4>비밀번호 초기화</h4>
					<p>아이디와 이메일을 입력 후 버튼을 클릭하면 비밀번호가 1234로 변경 됩니다.</p>
				</div>
				<form action="forgotProcess" method="get">
					<div class="form-group">
						<div class="form-label-group">
							<div class="form-group">
								<div class="form-label-group">
									<input type="text" name="user_id" class="form-control"
										required="required"> <label>아이디</label>
								</div>
							</div>
							<div class="form-group">
								<div class="form-label-group">
									<input type="email" name="user_email" class="form-control"
										required="required"> <label>이메일</label>
								</div>
							</div>
						</div>
					</div>
					<button type="submit" id="resetBtn"
						class="btn btn-primary btn-block">비밀번호 초기화</button>
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="register">회원가입</a> <a
						class="d-block small" href="login">로그인 페이지</a> 
					<a class="d-block small" href="time_add">시간 추가</a>
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
