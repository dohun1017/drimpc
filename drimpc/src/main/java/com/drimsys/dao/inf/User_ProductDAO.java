package com.drimsys.dao.inf;

import java.util.List;

import com.drimsys.dto.User_ProductVO;
 
public interface User_ProductDAO {
    
    public List<User_ProductVO> selectUser_Product() throws Exception;
    
//상품추가 관련(시간, 상품 모두 가능)
    public int insertUser_Product(User_ProductVO upVO) throws Exception;
    
    //상품조회 관련(전체)
    public User_ProductVO select_salesAll() throws Exception;
    
    //
}


