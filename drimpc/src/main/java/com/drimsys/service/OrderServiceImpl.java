package com.drimsys.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.drimsys.dao.inf.ProductDAO;
import com.drimsys.dao.inf.UserDAO;
import com.drimsys.dao.inf.User_ProductDAO;
import com.drimsys.dto.ProductVO;
import com.drimsys.dto.UserVO;
import com.drimsys.dto.User_ProductVO;
import com.drimsys.service.inf.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Inject
	private ProductDAO product_dao;
	@Inject
	private User_ProductDAO up_dao;
	@Inject
	private UserDAO user_dao;

	@Override
	public boolean updateProductTot(ProductVO productVO) throws Exception {
		try {
			if (product_dao.updateProductTot(productVO) == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public ProductVO selectProductID(ProductVO productVO) throws Exception {
		return product_dao.selectProductName(productVO);
	}

	@Override
	public boolean insertUser_Product(User_ProductVO upVO) throws Exception {
		try {
			if (up_dao.insertUser_Product(upVO) == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
//시간 추가 관련	
	@Override
	public boolean updateUserTime(UserVO userVO) throws Exception {
		try {
			if(user_dao.updateUserTime(userVO) == 1)
				return true;
			else
				return false;
		}catch (Exception e) {
			return false;
		}
	}
}
