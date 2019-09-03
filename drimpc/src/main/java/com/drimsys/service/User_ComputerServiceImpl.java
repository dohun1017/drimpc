package com.drimsys.service;

import java.util.List;

import javax.inject.Inject;
 
import org.springframework.stereotype.Service;

import com.drimsys.dao.inf.User_ComputerDAO;
import com.drimsys.dto.User_ComputerVO;
import com.drimsys.service.inf.User_ComputerService;
 
@Service
public class User_ComputerServiceImpl implements User_ComputerService {
 
    @Inject
    private User_ComputerDAO dao;
    
    @Override
    public List<User_ComputerVO> selectUser_Computer() throws Exception {
 
        return dao.selectUser_Computer();
    }
 
}


