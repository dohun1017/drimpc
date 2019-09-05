package com.drimsys.dao.inf;

import java.util.List;

import com.drimsys.dto.ProductVO;

public interface ProductDAO {

	public List<ProductVO> selectProduct() throws Exception;

//상품 추가 관련(시간, 상품 모두)
	public ProductVO selectProductName(ProductVO productVO) throws Exception;

	public int updateProductTot(ProductVO productVO) throws Exception;

//상품 조회 관련
	public List<ProductVO> salesProduct() throws Exception;

//년도별 매출 조회 관련
	public ProductVO salesDate(ProductVO productVO) throws Exception;

//상품 활성화, 비활성화 관련
	public int update_Product_available(ProductVO productVO) throws Exception;

//상품 추가 관련
	public int insert_Product(ProductVO productVO) throws Exception;

//관리자 상품 조회
	public List<ProductVO> select_admin_Product() throws Exception;
}
