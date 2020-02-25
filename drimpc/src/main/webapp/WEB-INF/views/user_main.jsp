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
<title>사용자 - 주문</title>

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
	setInterval(function(){
		$.ajax({
			url : "/drimpc/timeProcess",
	    	type: "GET",
			contentType : "application/json; charset=utf-8;",
			dataType : "json",
			success : function(result){
				$("#user_time").text("");
				$("#user_time").append("<i class=\"fas fa-table\"></i>");
				$("#user_time").append("남은 시간 : "+result.user_time+"분");
				if(result.user_time == 0)
					location.href = "/drimpc/logoutProcess";
			},
			error : function(){
				alert("남은 시간 갱신 오류");
			}
		});
	},6000);
	
	var first = new Array();
	<c:forEach items = "${productList}" var = "product">
		first.push({
			product_name:"${product.product_name}",
			product_price:"${product.product_price}", 
			product_tot:"${product.product_tot}",
			user_select_quantity : 0*1});
	</c:forEach>
	draw_table(first);
	
	$("#orderBtn").click(function(e) {

		e.preventDefault();

		var product_table = $("#productTable").DataTable();
		var data = product_table.rows().data();
		var edit_data = new Array();
		for(var i = 0; i < first.length; i++) {
			if(document.getElementById(data[i][0]).value != 0)
				edit_data.push({
					product_name : data[i][0],
					user_select_quantity : document.getElementById(data[i][0]).value*1
				});
		}
		var send_data = {};

		edit_data = JSON.stringify(edit_data);
		send_data = ({orderList: edit_data});
		$.ajax({
			url : "/drimpc/orderProcess",
			type : "GET",
			data : send_data,
			contentType : "application/json; charset=utf-8;",
			dataType : "json",
			success : function(result) {
				var count = 0;
				if(result.length == first.length){
					for(var i = 0; i<result.length; i++)
						if(result[i].product_tot == first[i].product_tot)
							count++;
					if(count == result.length)
						alert("주문 없음");
				}
				if(count != result.length){
					alert("주문 성공");
					product_table.clear().draw();
					draw_table(result);
				}
			},
			error : function() {
				alert("주문 실패");
			}
		});
	});
	function draw_table(productList){
		var tabledata = new Array();
		for(var i = 0; i<productList.length; i++){
			tabledata[i] = [
				productList[i].product_name, 
				productList[i].product_price, 
				productList[i].product_tot,
				"<input type='number' class='form-control' id='"+productList[i].product_name+"'"+
				" placeholder='수량입력' value=0 min=0 max="+productList[i].product_tot+"/>"];
		}
		first = productList;
		$("#productTable").dataTable().fnAddData(tabledata);
	}
});
</script>
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
				aria-expanded="false"> <i class="fas fa-user-circle fa-fw"></i>
			</a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="userDropdown">
					<a class="dropdown-item" href="#" data-toggle="modal"
						data-target="#modifyModal">비밀번호 변경</a>
					<a class="dropdown-item" href="#" data-toggle="modal"
						data-target="#logoutModal">로그아웃</a>
				</div></li>
		</ul>

	</nav>

	<div id="wrapper">

		<!-- 사이드바 -->
		<ul class="sidebar navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="user_main">
					<i class="fas fa-fw fa-table"></i> <span>상품 주문</span>
			</a></li>
			<li class="nav-item active"><a class="nav-link" href="time_add">
					<i class="fas fa-fw fa-table"></i> <span>시간 추가</span>
			</a></li>
		</ul>

		<div id="content-wrapper">

			<div class="container-fluid">

				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item active">Drim PC</li>
				</ol>

				<!-- 테이블 표시 -->
				<div class="card mb-3">
					<div class="card-header" id = "user_time">
						<i class="fas fa-table"></i> 남은 시간 : ${map.get("user_time")}분
					</div>
					<div class="card-header">
						<i class="fas fa-table"></i> 상품 목록
					</div>
					<form>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="productTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>상품명</th>
											<th>가격</th>
											<th>남은 수량</th>
											<th>주문 수량</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>상품명</th>
											<th>가격</th>
											<th>남은 수량</th>
											<th>주문 수량</th>
										</tr>
									</tfoot>
									<tbody id = "productTableTBody">
									</tbody>
								</table>
							</div>
							<div align="right">
								<input type="hidden" id="user_id" name = "user_id"
									value="${login_status.user_id}">
								<button id="orderBtn" class="btn btn-primary">주문하기</button>
							</div>
					</form>
				</div>
				<div class="card-footer small text-muted">Updated at
					${now_date}</div>
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

	</div>
	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
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
	
	<!-- Modify Modal-->
	<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">비밀번호 변경 페이지로 이동 하시겠습니까?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">비밀번호 변경 페이지로 이동합니다..</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">취소</button>
					<a class="btn btn-primary" href="user_modify">확인</a>
				</div>
			</div>
		</div>
	</div>
</body>

</html>