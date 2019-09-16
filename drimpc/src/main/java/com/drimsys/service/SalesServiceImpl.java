package com.drimsys.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.drimsys.dao.inf.ProductDAO;
import com.drimsys.dao.inf.UserDAO;
import com.drimsys.dao.inf.User_ProductDAO;
import com.drimsys.dto.ProductVO;
import com.drimsys.dto.UserVO;
import com.drimsys.dto.User_ProductVO;
import com.drimsys.service.inf.SalesService;

@Service
public class SalesServiceImpl implements SalesService{

	@Inject
	private UserDAO user_dao;
	@Inject
	private ProductDAO product_dao;
	@Inject
	private User_ProductDAO up_dao;
	
	@Override
	public List<UserVO> salesUser() throws Exception {
		return user_dao.salesUser();
	}
	
	@Override
	public List<ProductVO> salesProduct() throws Exception {
		return product_dao.salesProduct();
	}
	
	@Override
	public ProductVO salesDate(ProductVO productVO) throws Exception {
		return product_dao.salesDate(productVO);
	}
	
	@Override
	public User_ProductVO select_salesAll() throws Exception {
		return up_dao.select_salesAll();
	}
	
	@Override
	public List<ProductVO> batchProduct(ProductVO productVO) throws Exception {
		return product_dao.batchProduct(productVO);
	}
}
