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

<title>Drim PC</title>

<!-- Custom fonts for this template-->
<link href="../drimpc/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template-->
<link href="../drimpc/resources/css/sb-admin.css" rel="stylesheet">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
</head>

<body class="bg-dark">

	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">Drim PC 로그인</div>
			<div class="card-body">

				<form action="loginProcess" method="post">
					<div class="form-group">
						<div class="form-label-group">
							<input type="text" name="user_id" class="form-control"
								required="required" autofocus="autofocus"> <label>아이디</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-label-group">
							<input type="password" name="user_pw" class="form-control"
								required="required"> <label>비밀번호</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-label-group">
							<input type="number" min = 0 name="computer_id" class="form-control"
								required="required"> <label>좌석 번호</label>
						</div>
					</div>
					<button type="submit" id="loginBtn"
						class="btn btn-primary btn-block">로그인</button>
				</form>

				<div class="text-center">
					<a class="d-block small mt-3" href="register">회원 가입</a>
		  	        <a class="d-block small" href="forgot_password">암호 분실</a> 
					<a class="d-block small" href="time_add">시간 추가</a> 
				</div>
			</div>
		</div>
		<p></p><p></p>
						<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-table"></i> 좌석
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable" width="100%"
								cellspacing="0">
								<thead>
									<tr>
										<th>좌석 번호</th>
										<th>사용 가능</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${computerList}" var="computer">
										<tr>
											<td>${computer.computer_id}</td>
											<c:choose>
												<c:when test='${computer.computer_using == 0 && computer.computer_status == 1}' >
													<td>O</td>
												</c:when>
												<c:when test='${computer.computer_using == 1 || computer.computer_status == 0 }'>
													<td>X</td>
												</c:when>
											</c:choose>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="card-footer small text-muted">Updated at ${now_date}</div>
				</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="../drimpc/resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="../drimpc/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="../drimpc/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	
</body>

</html>
