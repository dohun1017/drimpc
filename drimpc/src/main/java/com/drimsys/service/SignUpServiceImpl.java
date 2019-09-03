package com.drimsys.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.drimsys.dao.inf.UserDAO;
import com.drimsys.dto.UserVO;
import com.drimsys.service.inf.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService{

	@Inject
	private UserDAO dao;
	
	@Override
	public List<UserVO> selectSignUpUser(UserVO userVO) throws Exception {
		return dao.selectSignUpUser(userVO);
	}
	
	@Override
	public boolean insertSignUpUser(UserVO userVO) throws Exception {
		try{
			if(dao.insertSignUpUser(userVO) == 1)
				return true;
			else 
				return false;
		}catch (Exception e) {
			return false;
		}
	}
}
