package com.drimsys.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.drimsys.dao.inf.ComputerDAO;
import com.drimsys.dao.inf.UserDAO;
import com.drimsys.dao.inf.User_ComputerDAO;
import com.drimsys.dto.ComputerVO;
import com.drimsys.dto.UserVO;
import com.drimsys.dto.User_ComputerVO;
import com.drimsys.service.inf.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Inject
	private UserDAO u_dao;
	@Inject
	private ComputerDAO c_dao;
	@Inject
	private User_ComputerDAO uc_dao;
	
	@Override
	public UserVO selectUserUsing(UserVO userVO) throws Exception {
		return u_dao.selectUserUsing(userVO);
	}
	
	@Override
	public ComputerVO selectComputerUsing(ComputerVO computerVO) throws Exception {
		return c_dao.selectComputerUsing(computerVO);
	}
	
	@Override
	public boolean updateUserUsing(UserVO userVO) throws Exception {
		try {
			if(u_dao.updateUserUsing(userVO) == 1)
				return true;
			else
				return false;
		}catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean updateComputerUsing(ComputerVO computerVO) throws Exception {
		try {
			if(c_dao.updateComputerUsing(computerVO) == 1)
				return true;
			else
				return false;
		}catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean insertLoginUser_Computer(User_ComputerVO ucVO) throws Exception {
		// TODO Auto-generated method stub
    	try {
    		if(uc_dao.insertLoginUser_Computer(ucVO) == 1)
    			return true;
    		else
    			return false;
    	}catch (Exception e) {
			// TODO: handle exception
    		return false;
		}
	}
}
