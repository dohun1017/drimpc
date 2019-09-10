package com.drimsys.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.drimsys.dao.inf.User_ProductDAO;
import com.drimsys.dto.User_ProductVO;

@Repository
public class User_ProductDAOImpl implements User_ProductDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String Namespace = "com.drimsys.mapper.user_productMapper";

	@Override
	public List<User_ProductVO> selectUser_Product() throws Exception {

		return sqlSession.selectList(Namespace + ".selectUser_Product");
	}

//상품추가 관련(시간, 상품 모두 가능)
	@Override
	public int insertUser_Product(User_ProductVO upVO) throws Exception {
		try {
			return sqlSession.insert("com.drimsys.mapper.orderMapper.insertUser_Product",upVO);
		}catch (Exception e) {
			return -1;
		}
	}
	
	//상품조회 관련(전체)
	@Override
	public User_ProductVO select_salesAll() throws Exception {
		return sqlSession.selectOne("com.drimsys.mapper.salesMapper.select_salesAll");
	}
}
