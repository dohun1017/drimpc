package com.drimsys.dao;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import org.slf4j.Logger;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.drimsys.dao.inf.UserDAO;
import com.drimsys.dto.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String Namespace = "com.drimsys.mapper.userMapper";

	@Override
	public List<UserVO> selectUser() throws Exception {

		return sqlSession.selectList(Namespace + ".selectUser");
	}

//로그인 관련
	@Override
	public UserVO selectUserUsing(UserVO userVO) throws Exception {

		return sqlSession.selectOne("com.drimsys.mapper.loginMapper.selectUserUsing", userVO);
	}

	@Override
	public int updateUserUsing(UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		try {
			return sqlSession.update("com.drimsys.mapper.loginMapper.updateUserUsing", userVO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}

//회원가입 관련
	@Override
	public List<UserVO> selectSignUpUser(UserVO userVO) throws Exception {
		return sqlSession.selectList("com.drimsys.mapper.signupMapper.selectSignUpUser", userVO);
	}

	@Override
	public int insertSignUpUser(UserVO userVO) throws Exception {
		try {
			return sqlSession.insert("com.drimsys.mapper.signupMapper.insertSignUpUser", userVO);
		} catch (Exception e) {
			return -1;
		}
	}

//시간추가 관련
	@Override
	public int updateUserTime(UserVO userVO) throws Exception {
		try {
			return sqlSession.update("com.drimsys.mapper.orderMapper.updateUserTime", userVO);
		} catch (Exception e) {
			return -1;
		}
	}

//상품 조회 관련
	@Override
	public List<UserVO> salesUser() throws Exception {
		return sqlSession.selectList("com.drimsys.mapper.salesMapper.salesUser");
	}

// 회원 조회
	@Override
	public List<UserVO> admin_selectUser() throws Exception {
		return sqlSession.selectList("com.drimsys.mapper.userMapper.admin_selectUser");
	}
	
// 비밀번호 변경
	@Override
	public int update_user_pw(UserVO userVO) throws Exception {
		return sqlSession.update("com.drimsys.mapper.userMapper.update_user_pw",userVO);
	}
	
    //암호 초기화 가능 사용자 확인
	@Override
	public UserVO select_forgot_user(UserVO userVO) throws Exception {
		return sqlSession.selectOne("com.drimsys.mapper.userMapper.select_forgot_user",userVO);
	}
}
