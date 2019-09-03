package com.drimsys.service.inf;

import java.util.List;

import com.drimsys.dto.ProductVO;
 
public interface ProductService {
    
    public List<ProductVO> selectProduct() throws Exception;
}

