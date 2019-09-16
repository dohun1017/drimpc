package com.drimsys.service.inf;

import java.util.List;

import com.drimsys.dto.ProductVO;
import com.drimsys.dto.UserVO;
import com.drimsys.dto.User_ProductVO;

public interface SalesService {
    
    public List<UserVO> salesUser() throws Exception;
    
    public List<ProductVO> salesProduct() throws Exception;
    
    public ProductVO salesDate(ProductVO productVO) throws Exception;
    
    public User_ProductVO select_salesAll() throws Exception;
    
    public List<ProductVO> batchProduct(ProductVO productVO) throws Exception;
}

