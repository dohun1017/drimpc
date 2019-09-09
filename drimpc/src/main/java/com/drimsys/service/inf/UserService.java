package com.drimsys.service.inf;

import java.util.List;

import com.drimsys.dto.UserVO;
 
public interface UserService {
    
    public List<UserVO> selectUser() throws Exception;
    
    //회원 정보 조회
    public List<UserVO> admin_selectUser() throws Exception;
    
    //비밀번호 변경
    public boolean update_user_pw(UserVO userVO) throws Exception;
    
    //암호 초기화 가능 사용자 확인
    public UserVO select_forgot_user(UserVO userVO) throws Exception;
}

