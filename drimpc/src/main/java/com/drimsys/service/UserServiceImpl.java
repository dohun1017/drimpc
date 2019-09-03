package com.drimsys.service;

import java.util.List;

import javax.inject.Inject;
 
import org.springframework.stereotype.Service;

import com.drimsys.dao.inf.UserDAO;
import com.drimsys.dto.UserVO;
import com.drimsys.service.inf.UserService;
 
@Service
public class UserServiceImpl implements UserService {
 
    @Inject
    private UserDAO dao;
    
    @Override
    public List<UserVO> selectUser() throws Exception {
 
        return dao.selectUser();
    }

	
 
}


