package com.drimsys.service;

import java.util.List;

import javax.inject.Inject;
 
import org.springframework.stereotype.Service;

import com.drimsys.dao.inf.User_ProductDAO;
import com.drimsys.dto.User_ProductVO;
import com.drimsys.service.inf.User_ProductService;
 
@Service
public class User_ProductServiceImpl implements User_ProductService {
 
    @Inject
    private User_ProductDAO dao;
    
    @Override
    public List<User_ProductVO> selectUser_Product() throws Exception {
 
        return dao.selectUser_Product();
    }
 
}


