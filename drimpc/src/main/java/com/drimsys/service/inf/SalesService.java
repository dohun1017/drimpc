package com.drimsys.service.inf;

import java.util.List;

import com.drimsys.dto.ProductVO;
import com.drimsys.dto.UserVO;

public interface SalesService {
    
    public List<UserVO> salesUser() throws Exception;
    
    public List<ProductVO> salesProduct() throws Exception;
}

