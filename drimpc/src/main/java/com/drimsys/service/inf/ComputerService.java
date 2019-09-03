package com.drimsys.service.inf;

import java.util.List;

import com.drimsys.dto.ComputerVO;
 
public interface ComputerService {
    
    public List<ComputerVO> selectComputer() throws Exception;
}

