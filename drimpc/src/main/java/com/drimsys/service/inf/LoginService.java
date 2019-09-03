package com.drimsys.service.inf;

import com.drimsys.dto.ComputerVO;
import com.drimsys.dto.UserVO;
import com.drimsys.dto.User_ComputerVO;

public interface LoginService {
	
	public UserVO selectUserUsing(UserVO userVO) throws Exception;
	
	public ComputerVO selectComputerUsing(ComputerVO computerVO) throws Exception;
	
	public boolean updateUserUsing(UserVO userVO) throws Exception;
	
	public boolean updateComputerUsing(ComputerVO computerVO) throws Exception;
	
	public boolean insertLoginUser_Computer(User_ComputerVO ucVO) throws Exception;
}
