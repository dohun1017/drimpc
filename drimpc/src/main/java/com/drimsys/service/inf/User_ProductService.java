package com.drimsys.service.inf;

import java.util.List;

import com.drimsys.dto.User_ProductVO;
 
public interface User_ProductService {
    
    public List<User_ProductVO> selectUser_Product() throws Exception;
}

