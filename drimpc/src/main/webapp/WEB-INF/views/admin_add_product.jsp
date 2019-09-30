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

<title>관리자 상품추가</title>

<script src="../drimpc/resources/js/jquery-3.4.1.js"></script>
<!-- Custom fonts for this template-->
<link href="../drimpc/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link
	href="../drimpc/resources/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../drimpc/resources/css/sb-admin.css" rel="stylesheet">

<!-- Bootstrap core JavaScript-->
<script src="../drimpc/resources/vendor/jquery/jquery.min.js"></script>
<script
	src="../drimpc/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script
	src="../drimpc/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Page level plugin JavaScript-->
<script src="../drimpc/resources/vendor/datatables/jquery.dataTables.js"></script>
<script
	src="../drimpc/resources/vendor/datatables/dataTables.bootstrap4.js"></script>

<!-- Custom scripts for all pages-->
<script src="../drimpc/resources/js/sb-admin.min.js"></script>

<!-- Demo scripts for this page-->
<script src="../drimpc/resources/js/demo/datatables-demo.js"></script> 
<script type="text/javascript">
$(document).ready(function() {
	var ID = "ID", NAME = "NAME", PRICE = "PRICE", TOT = "TOT", AVAILABLE = "AVAILABLE";
	draw_table();
	
	$("#addBtn").click(function(e) {

		e.preventDefault();

		var add_table = $("#AddTable").DataTable();

		var data = add_table.rows().data();
		var edit_data = new Array();
		for (var i = 0; i < 5; i++) {
			if (document.getElementById(AVAILABLE+i).value == "판매중지")
				edit_data.push({
					product_id : document.getElementById(ID+i).value,
					product_name : document.getElementById(NAME+i).value,
					product_price : document.getElementById(PRICE+i).value*1,
					product_tot : document.getElementById(TOT+i).value*1,
					product_available : 0
				});
			else
				edit_data.push({
					product_id : document.getElementById(ID+i).value,
					product_name : document.getElementById(NAME+i).value,
					product_price : document.getElementById(PRICE+i).value*1,
					product_tot : document.getElementById(TOT+i).value*1,
					product_available : 1
				});
		}
		var send_data = {};

		edit_data = JSON.stringify(edit_data);
		send_data = ({addList: edit_data});
		$.ajax({
			url : "/drimpc/productAddProcess",
			type : "GET",
			data : send_data,
			contentType : "application/json; charset=utf-8;",
			dataType : "text",
			success : function(result) {
				if(result == null)
					alert(result);
				else{
				alert(result);
				add_table.clear().draw();
				draw_table();
				}
			},
			error : function() {
				alert("추가 실패");
			}
		});
	});
	function draw_table(){
		var tabledata = new Array();
		for(var i = 0; i<5; i++){
			tabledata[i] = ["<input type='text' id='"+ID+i+"' placeholder='예시)과자_1 / 라면_1'>",
			"<input type='text' id='"+NAME+i+"' placeholder='예시)포카칩 / 신라면'>",
			"<input type='number' id='"+PRICE+i+"' min = 0 placeholder='예시)1500 / 2500'>",
			"<input type='number' id='"+TOT+i+"' min = 0 placeholder='예시)10 / 0'>",
			"<input type='text' id='"+AVAILABLE+i+"' placeholder='예시)판매중 / 판매중지'>"];	
		}
		console.log(tabledata);
		$("#AddTable").dataTable().fnAddData(tabledata);
	}
});
</script>
</head>

<body id="page-top">

	<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

		<a class="navbar-brand mr-1" href="admin_product">Drim PC</a>

		<button class="btn btn-link btn-sm text-white order-1 order-sm-0"
			id="sidebarToggle" href="#">
			<i class="fas fa-bars"></i>
		</button>

		<!-- Navbar -->
		<ul class="navbar-nav ml-auto ml-md-0">
			<li class="nav-item dropdown no-arrow"><a
				class="nav-link dropdown-toggle" href="#" id="userDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <i class="fas fa-user-circle fa-fw"></i>
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
					onclick="focusblur()">상품 추가</span></a></li>
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
					<h6 class="dropdown-header">회원 조회</h6>
					<a class="dropdown-item" href="admin_user">회원 조회</a>
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
						<i class="fas fa-table"></i> 상품 추가
					</div>
					<form action="productAddProcess" method="GET">
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="AddTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>상품ID</th>
											<th>상품명</th>
											<th>가격</th>
											<th>수량</th>
											<th>판매여부</th>
										</tr>
									</thead>
									<tbody id = "AddTableTBody">
									</tbody>
								</table>
							</div>
							<p></p>
							<p></p>
							<div align="right">
								<button id="addBtn" class="btn btn-primary">추가하기</button>
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
					<a class="btn btn-primary" href="logoutProcess">확인</a>
				</div>
			</div>
		</div>
	</div>
</body>

</html>