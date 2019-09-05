<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	사용자 테이블
	<br>
	<table>
		<thead>
			<tr>
				<th>아이디&nbsp;</th>
				<th>비밀번호&nbsp;</th>
				<th>이름&nbsp;</th>
				<th>남은시간&nbsp;</th>
				<th>사용여부&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.user_id}&nbsp;</td>
					<td>${user.user_pw}&nbsp;</td>
					<td>${user.user_name}&nbsp;</td>
					<td>${user.user_time}&nbsp;</td>
					<c:choose>
						<c:when test='${user.user_using == 0}'>
							<td>사용가능&nbsp;</td>
						</c:when>
						<c:when test='${user.user_using == 1}'>
							<td>사용중&nbsp;</td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br> 좌석 테이블
	<br>
	<table>
		<thead>
			<tr>
				<th>아이디&nbsp;</th>
				<th>사용여부&nbsp;</th>
				<th>고장여부&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${computerList}" var="computer">
				<tr>
					<td>${computer.computer_id}&nbsp;</td>
					<c:choose>
						<c:when test='${computer.computer_using == 0}'>
							<td>사용가능&nbsp;</td>
						</c:when>
						<c:when test='${computer.computer_using == 1}'>
							<td>사용중&nbsp;</td>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test='${computer.computer_status == 1}'>
							<td>정상&nbsp;</td>
						</c:when>
						<c:when test='${computer.computer_status == 0}'>
							<td>고장&nbsp;</td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br> 상품 테이블
	<br>
	<table>
		<thead>
			<tr>
				<th>아이디&nbsp;</th>
				<th>이름&nbsp;</th>
				<th>가격&nbsp;</th>
				<th>수량&nbsp;</th>
				<th>판매/구매 여부</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productList}" var="product">
				<tr>
					<td>${product.product_id}&nbsp;</td>
					<td>${product.product_name}&nbsp;</td>
					<td>${product.product_price}&nbsp;</td>
					<td>${product.product_tot}&nbsp;</td>
					<c:choose>
						<c:when test='${product.product_available == 1}'>
							<td>가능&nbsp;</td>
						</c:when>
						<c:when test='${product.product_available == 0}'>
							<td>불가능&nbsp;</td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br> 사용자-좌석 테이블
	<br>
	<table>
		<thead>
			<tr>
				<th>사용자 아이디&nbsp;</th>
				<th>좌석번호&nbsp;</th>
				<th>시작&nbsp;</th>
				<th>종료&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${user_computerList}" var="user_computer">
				<tr>
					<td>${user_computer.user_id}&nbsp;</td>
					<td>${user_computer.computer_id}&nbsp;</td>
					<td>${user_computer.start}&nbsp;</td>
					<td>${user_computer.end}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br> 사용자-상품 테이블
	<br>
	<table>
		<thead>
			<tr>
				<th>사용자 아이디&nbsp;</th>
				<th>상품 아이디&nbsp;</th>
				<th>상품 수량&nbsp;</th>
				<th>날짜&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${user_productList}" var="user_product">
				<tr>
					<td>${user_product.user_id}&nbsp;</td>
					<td>${user_product.product_id}&nbsp;</td>
					<td>${user_product.product_quantity}&nbsp;</td>
					<td>${user_product.date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br> 회원별 매출 조회 테이블
	<br>
	<table>
		<thead>
			<tr>
				<th>사용자 이름&nbsp;</th>
				<th>상품 명&nbsp;</th>
				<th>상품 수량&nbsp;</th>
				<th>상품 가격&nbsp;</th>
				<th>날짜&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${salesUserList}" var="salesUser">
				<tr>
					<td>${salesUser.user_name}&nbsp;</td>
					<td>${salesUser.productVO.product_name}&nbsp;</td>
					<td>${salesUser.ucVO.product_quantity}&nbsp;</td>
					<td>${salesUser.join_user_all_price}&nbsp;</td>
					<td>${salesUser.ucVO.date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br> 상품별 매출 조회 테이블
	<br>
	<table>
		<thead>
			<tr>
				<th>상품 명&nbsp;</th>
				<th>상품 수량&nbsp;</th>
				<th>상품 가격&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${salesProductList}" var="salesProduct">
				<tr>
					<td>${salesProduct.product_name}&nbsp;</td>
					<td>${salesProduct.join_product_all_quantity}&nbsp;</td>
					<td>${salesProduct.join_product_all_price}&nbsp;</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br> 날짜별 매출 조회 테이블
	<br>
	<table>
		<thead>
			<tr>
				<th>기간&nbsp;</th>
				<th>금액&nbsp;</th>
			</tr>
		</thead>
		<tbody>
				<tr>
					<td>${salesDate.start_date} ~ ${salesDate.end_date}&nbsp;</td>
					<td>${salesDate.join_date_price}&nbsp;</td>
				</tr>
		</tbody>
	</table>
	<br>
	<br> 회원 정보 조회
	<br>
	<table>
		<thead>
			<tr>
				<th>사용자 이름&nbsp;</th>
				<th>사용자 아이디&nbsp;</th>
				<th>사용자 이메일&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${admin_selectList}" var="admin_user">
				<tr>
					<td>${admin_user.user_name}&nbsp;</td>
					<td>${admin_user.user_id}&nbsp;</td>
					<td>${admin_user.user_email}&nbsp;</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>

