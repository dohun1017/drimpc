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
 
}


