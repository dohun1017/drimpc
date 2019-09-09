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
    
    //회원정보 조회
    @Override
    public List<UserVO> admin_selectUser() throws Exception {
    	return dao.admin_selectUser();
    }

	//비밀번호 변경
    @Override
    public boolean update_user_pw(UserVO userVO) throws Exception {
    	try {
    		if(dao.update_user_pw(userVO) == 1)
    			return true;
    		else
    			return false;
    	}catch (Exception e) {
			return false;
		}
    }
    
    //암호 초기화 가능 사용자 확인
    @Override
    public UserVO select_forgot_user(UserVO userVO) throws Exception {
    	return dao.select_forgot_user(userVO);
    }
 
}


