package com.drimsys.dao.inf;

import java.util.List;

import com.drimsys.dto.UserVO;
 
public interface UserDAO {
    
    public List<UserVO> selectUser() throws Exception;
    
    //로그인 관련
    public UserVO selectUserUsing(UserVO userVO) throws Exception;
    
    public int updateUserUsing(UserVO userVO) throws Exception;
    
    //회원가입 관련
    public List<UserVO> selectSignUpUser(UserVO userVO) throws Exception;
    
    public int insertSignUpUser(UserVO userVO) throws Exception;
    
    //시간추가 관련
    public int updateUserTime(UserVO userVO) throws Exception;
    
    //상품 조회 관련
    public List<UserVO> salesUser() throws Exception;
    
    //회원 조회
    public List<UserVO> admin_selectUser() throws Exception;
    
    //비밀번호 변경
    public int update_user_pw(UserVO userVO) throws Exception;
    
}


