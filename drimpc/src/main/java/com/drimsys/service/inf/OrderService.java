package com.drimsys.service.inf;

import com.drimsys.dto.ProductVO;
import com.drimsys.dto.UserVO;
import com.drimsys.dto.User_ProductVO;

public interface OrderService {
	
	public boolean updateProductTot(ProductVO productVO) throws Exception;
	
	public ProductVO selectProductName(ProductVO productVO) throws Exception;
	
	public boolean insertUser_Product(User_ProductVO upVO) throws Exception;
	
	//시간추가 관련
	public boolean updateUserTime(UserVO userVO) throws Exception;
}
