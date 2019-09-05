package com.drimsys.service.inf;

import java.util.List;

import com.drimsys.dto.ComputerVO;
 
public interface ComputerService {
    
    public List<ComputerVO> selectComputer() throws Exception;
  
    //컴퓨터 좌석 수정
    public boolean updateComputer_status(ComputerVO computerVO) throws Exception;

    //컴퓨터 좌석 추가
    public boolean insertComputer(ComputerVO computerVO) throws Exception;
}


