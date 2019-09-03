package com.drimsys.service;

import java.util.List;

import javax.inject.Inject;
 
import org.springframework.stereotype.Service;

import com.drimsys.dao.inf.ComputerDAO;
import com.drimsys.dto.ComputerVO;
import com.drimsys.service.inf.ComputerService;
 
@Service
public class ComputerServiceImpl implements ComputerService {
 
    @Inject
    private ComputerDAO dao;
    
    @Override
    public List<ComputerVO> selectComputer() throws Exception {
 
        return dao.selectComputer();
    }
 
}


