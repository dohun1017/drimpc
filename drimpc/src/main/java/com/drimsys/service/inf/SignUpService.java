package com.drimsys.service.inf;

import java.util.List;

import com.drimsys.dto.UserVO;

public interface SignUpService {
	public List<UserVO> selectSignUpUser(UserVO userVO) throws Exception;
	
	public boolean insertSignUpUser(UserVO userVO) throws Exception;
}
