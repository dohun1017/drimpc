package com.drimsys.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.drimsys.dao.inf.ProductDAO;
import com.drimsys.dto.ProductVO;
import com.drimsys.service.inf.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDAO dao;

	@Override
	public List<ProductVO> selectProduct() throws Exception {

		return dao.selectProduct();
	}

	// 상품 활성화, 비활성화 관련
	@Override
	public boolean update_Product_available(ProductVO productVO) throws Exception {
		try {
			if (dao.update_Product_available(productVO) == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	// 상품 추가 관련
	@Override
	public boolean insert_Product(ProductVO productVO) throws Exception {
		try {
			if (dao.insert_Product(productVO) == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	// 관리자 상품 조회 관련
	@Override
	public List<ProductVO> select_admin_Product() throws Exception {
		return dao.select_admin_Product();
	}

	// 구매 가능 상품 조회
	@Override
	public List<ProductVO> select_Product_available() throws Exception {
		return dao.select_Product_available();
	}

	// 관리자 상품 체크
	@Override
	public ProductVO select_admin_check(ProductVO productVO) throws Exception {
		return dao.select_admin_check(productVO);
	}

	// 관리자 상품 변경
	@Override
	public boolean update_admin_product(ProductVO productVO) throws Exception {
		try {
			if (dao.update_admin_product(productVO) == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
}
