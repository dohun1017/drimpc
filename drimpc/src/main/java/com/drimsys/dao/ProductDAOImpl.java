package com.drimsys.dao;

import java.util.List;

import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.drimsys.dao.inf.ProductDAO;
import com.drimsys.dto.ProductVO;
 
 
@Repository
public class ProductDAOImpl implements ProductDAO {
 
    @Inject
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.drimsys.mapper.productMapper";
    
    @Override
    public List<ProductVO> selectProduct() throws Exception {
 
        return sqlSession.selectList(Namespace+".selectProduct");
    }
    
//상품 주문 관련
    @Override
    public ProductVO selectProductName(ProductVO productVO) throws Exception {
    	return sqlSession.selectOne("com.drimsys.mapper.orderMapper.selectProductName", productVO);
    }
    
    @Override
    public int updateProductTot(ProductVO productVO) throws Exception {
    	try {
    		return sqlSession.update("com.drimsys.mapper.orderMapper.updateProductTot", productVO);
    	}catch (Exception e) {
    		return -1;
    	}
    }
 
}

