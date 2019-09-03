package com.drimsys.dao.inf;

import java.util.List;

import com.drimsys.dto.User_ComputerVO;
 
public interface User_ComputerDAO {
    
    public List<User_ComputerVO> selectUser_Computer() throws Exception;
    
    //로그인 관련
    public int insertLoginUser_Computer(User_ComputerVO ucVO) throws Exception;
}


