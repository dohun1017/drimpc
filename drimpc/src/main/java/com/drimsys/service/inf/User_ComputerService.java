package com.drimsys.service.inf;

import java.util.List;

import com.drimsys.dto.User_ComputerVO;
 
public interface User_ComputerService {
    
    public List<User_ComputerVO> selectUser_Computer() throws Exception;
    
}

