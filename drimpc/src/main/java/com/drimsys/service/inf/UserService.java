package com.drimsys.service.inf;

import java.util.List;

import com.drimsys.dto.UserVO;
 
public interface UserService {
    
    public List<UserVO> selectUser() throws Exception;
    
    
}

