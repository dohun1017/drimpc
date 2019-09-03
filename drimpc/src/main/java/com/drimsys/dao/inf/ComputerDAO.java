package com.drimsys.dao.inf;

import java.util.List;

import com.drimsys.dto.ComputerVO;
 
public interface ComputerDAO {
    
    public List<ComputerVO> selectComputer() throws Exception;
    
    //로그인 관련
    public ComputerVO selectComputerUsing(ComputerVO computerVO) throws Exception;

    public int updateComputerUsing(ComputerVO computerVO) throws Exception;
    
    
}


