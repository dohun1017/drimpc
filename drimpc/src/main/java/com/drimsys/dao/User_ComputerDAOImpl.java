package com.drimsys.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.drimsys.dao.inf.User_ComputerDAO;
import com.drimsys.dto.User_ComputerVO;

@Repository
public class User_ComputerDAOImpl implements User_ComputerDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String Namespace = "com.drimsys.mapper.user_computerMapper";

	@Override
	public List<User_ComputerVO> selectUser_Computer() throws Exception {

		return sqlSession.selectList(Namespace + ".selectUser_Computer");

	}
	
//로그인 관련
	@Override
	public int insertLoginUser_Computer(User_ComputerVO ucVO) throws Exception {
		// TODO Auto-generated method stub
		try {
			return sqlSession.insert("com.drimsys.mapper.loginMapper.insertLoginUser_Computer", ucVO);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

}
