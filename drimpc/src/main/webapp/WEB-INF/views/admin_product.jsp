<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<script type="text/javascript">
</script>

<title>관리자 상품관리</title>

<!-- Custom fonts for this template-->
<link href="../drimpc/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link
	href="../drimpc/resources/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../drimpc/resources/css/sb-admin.css" rel="stylesheet">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>

<body id="page-top">

	<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

		<a class="navbar-brand mr-1" href="user_main">Drim PC</a>

		<button class="btn btn-link btn-sm text-white order-1 order-sm-0"
			id="sidebarToggle" href="#">
			<i class="fas fa-bars"></i>
		</button>

		<!-- Navbar -->
		<ul class="navbar-nav ml-auto ml-md-0">
			<li class="nav-item dropdown no-arrow"><a
				class="nav-link dropdown-toggle" href="#" id="userDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false" > <i class="fas fa-user-circle fa-fw"></i>
			</a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="userDropdown">
					<a class="dropdown-item" href="#" data-toggle="modal"
						data-target="#logoutModal">로그아웃</a>
				</div></li>
		</ul>

	</nav>

	<div id="wrapper">

		<!-- 사이드바 -->
		<ul class="sidebar navbar-nav">
			<li class="nav-item active"><a class="nav-link"> <i
					class="fas fa-fw fa-table"></i> <span id="사이드바"
					onclick="focusblur()">상품 관리</span></a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> <span>상세
						메뉴</span>
			</a>
				<div class="dropdown-menu" aria-labelledby="pagesDropdown">
					<h6 class="dropdown-header">상품 관리</h6>
					<a class="dropdown-item" href="admin_product">상품 관리</a>
					<a class="dropdown-item" href="admin_add_product">상품 추가</a>
					<div class="dropdown-divider"></div>
					<h6 class="dropdown-header">매출 확인</h6>
					<a class="dropdown-item" href="admin_sales_user">회원별</a> <a
						class="dropdown-item" href="admin_sales_product">상품별</a> <a
						class="dropdown-item" href="admin_sales_date">날짜별</a>
					<div class="dropdown-divider"></div>
					<h6 class="dropdown-header">좌석 관리</h6>
					<a class="dropdown-item" href="admin_seat">좌석 관리</a>
					<div class="dropdown-divider"></div>
					<h6 class="dropdown-header">회원 관리</h6>
					<a class="dropdown-item" href="admin_user">회원 관리</a>
				</div></li>
		</ul>

		<div id="content-wrapper">

			<div class="container-fluid">

				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item active">Drim PC</li>
				</ol>

				<!-- 테이블 표시 -->
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-table"></i> 상품 관리
					</div>
					<form action="productEditProcess" method="GET">
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>상품명</th>
											<th>가격</th>
											<th>수량</th>
											<th>판매여부</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>상품명</th>
											<th>가격</th>
											<th>수량</th>
											<th>판매여부</th>
										</tr>
									</tfoot>
									<tbody>
										<%
											int i = 0;
											String p_name = "";
											String p_tot = "";
											String p_available = "";
										%>
										<c:forEach items="${productList}" var="product">
											<%
												p_name = "p_name" + Integer.toString(i);
													p_tot = "p_tot" + Integer.toString(i);
													p_available = "p_available" + Integer.toString(i);
													i++;
											%>
											<tr>
												<td>${product.product_name}
													<input type="hidden" name = <%=p_name%> value = "${product.product_name}">
												</td>
												<td>${product.product_price}</td>
												<td><input type="number" class="form-control" name = <%=p_tot%>
													placeholder="수량입력" value = "${product.product_tot}" min = 0></td>
												<c:choose>
													<c:when test='${product.product_available == 1}'>
														<td><input type="text" class="form-control" name = <%=p_available%>
													placeholder="상태입력(판매중, 판매중지)" value = "판매중"></td>
													</c:when>
													<c:when test='${product.product_available == 0}'>
														<td><input type="text" class="form-control" name = <%=p_available%>
													placeholder="상태입력(판매중, 판매중지)" value = "판매중지"></td>
													</c:when>
												</c:choose>
											</tr>

										</c:forEach>
									</tbody>
								</table>
							</div>
							<p></p>
							<p></p>
							<div align="right">
								<button type="submit" id="orderBtn" class="btn btn-primary">변경하기</button>
							</div>
						</div>
					</form>

					<div class="card-footer small text-muted">Updated at ${now_date}</div>
				</div>
			</div>


		</div>
		<!-- /.container-fluid -->

		<!-- Sticky Footer -->
		<footer class="sticky-footer">
			<div class="container my-auto">
				<div class="copyright text-center my-auto">
					<span>Copyright © Drim PC</span>
				</div>
			</div>
		</footer>

	</div>
	<!-- /.content-wrapper -->


	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal -->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">로그아웃 하시겠습니까?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">로그인 화면으로 이동합니다.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">취소</button>
					<a class="btn btn-primary" href="login">확인</a>
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

	<!-- Page level plugin JavaScript-->
	<script
		src="../drimpc/resources/vendor/datatables/dataTables.bootstrap4.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../drimpc/resources/js/sb-admin.min.js"></script>

	<!-- Demo scripts for this page-->
	<script src="../drimpc/resources/js/demo/datatables-demo.js"></script>
</body>

</html>