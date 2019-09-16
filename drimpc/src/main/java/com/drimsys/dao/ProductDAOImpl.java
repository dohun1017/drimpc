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

		return sqlSession.selectList(Namespace + ".selectProduct");
	}

//상품 주문 관련
	@Override
	public ProductVO selectProductName(ProductVO productVO) throws Exception {
		return sqlSession.selectOne("com.drimsys.mapper.orderMapper.selectProductID", productVO);
	}

	@Override
	public int updateProductTot(ProductVO productVO) throws Exception {
		try {
			return sqlSession.update("com.drimsys.mapper.orderMapper.updateProductTot", productVO);
		} catch (Exception e) {
			return -1;
		}
	}

//상품 조회 관련
	@Override
	public List<ProductVO> salesProduct() throws Exception {
		return sqlSession.selectList("com.drimsys.mapper.salesMapper.salesProduct");
	}

//년도별 매출 조회 관련
	@Override
	public ProductVO salesDate(ProductVO productVO) throws Exception {
		return sqlSession.selectOne("com.drimsys.mapper.salesMapper.salesDate", productVO);
	}

//상품 활성화, 비활성화 관련
	@Override
	public int update_Product_available(ProductVO productVO) throws Exception {
		try {
			return sqlSession.update("com.drimsys.mapper.productMapper.update_Product_available", productVO);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

//상품 추가 관련
	@Override
	public int insert_Product(ProductVO productVO) throws Exception {
		try {
			return sqlSession.insert("com.drimsys.mapper.productMapper.insert_Product", productVO);
		} catch (Exception e) {
			return -1;
		}
	}

//관리자 상품 조회 관련
	@Override
	public List<ProductVO> select_admin_Product() throws Exception {
		return sqlSession.selectList("com.drimsys.mapper.productMapper.select_admin_Product");
	}
	
	//구매 가능 상품 조회
	@Override
	public List<ProductVO> select_Product_available() throws Exception {
		return sqlSession.selectList("com.drimsys.mapper.productMapper.select_Product_available");
	}
	
	//관리자 상품 체크
	@Override
	public ProductVO select_admin_check(ProductVO productVO) throws Exception {
		return sqlSession.selectOne("com.drimsys.mapper.productMapper.select_admin_check",productVO);
	}
	
	//관리자 상품 변경
	@Override
	public int update_admin_product(ProductVO productVO) throws Exception {
		try {
			return sqlSession.update("com.drimsys.mapper.productMapper.update_admin_product",productVO);
		}catch (Exception e) {
			return -1;
		}
	}
	
	//배치
	@Override
	public List<ProductVO> batchProduct(ProductVO productVO) throws Exception {
		return sqlSession.selectList("com.drimsys.mapper.salesMapper.batchProduct",productVO);
	}
}
