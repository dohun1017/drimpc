package com.drimsys.service.inf;

import java.util.List;

import com.drimsys.dto.ProductVO;

public interface ProductService {

	public List<ProductVO> selectProduct() throws Exception;

	// 상품 활성화, 비활성화 관련
	public boolean update_Product_available(ProductVO productVO) throws Exception;

	// 상품 추가 관련
	public boolean insert_Product(ProductVO productVO) throws Exception;

	// 관리자 상품 조회 관련
	public List<ProductVO> select_admin_Product() throws Exception;
	
	// 구매 가능 상품 조회
	public List<ProductVO> select_Product_available() throws Exception;
	
	//관리자 상품 체크
	public ProductVO select_admin_check(ProductVO productVO) throws Exception;
	
	//관리자 상품 변경
	public boolean update_admin_product(ProductVO productVO) throws Exception;
}
