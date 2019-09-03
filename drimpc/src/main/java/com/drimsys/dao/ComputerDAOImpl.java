package com.drimsys.dao;

import java.util.List;

import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.drimsys.dao.inf.ComputerDAO;
import com.drimsys.dto.ComputerVO;
 
 
@Repository
public class ComputerDAOImpl implements ComputerDAO {
 
    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.drimsys.mapper.computerMapper";
    
    @Override
    public List<ComputerVO> selectComputer() throws Exception {
 
        return sqlSession.selectList(Namespace+".selectComputer");
    }
    
//로그인 관련
    @Override
    public ComputerVO selectComputerUsing(ComputerVO computerVO) throws Exception {
    	return sqlSession.selectOne("com.drimsys.mapper.loginMapper.selectComputerUsing",computerVO);
    }
    
    @Override
	public int updateComputerUsing(ComputerVO computerVO) throws Exception {
		try {
			return sqlSession.update("com.drimsys.mapper.loginMapper.updateComputerUsing",computerVO);
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}

