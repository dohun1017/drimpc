package com.drimsys.dao.inf;

import java.util.List;

import com.drimsys.dto.ProductVO;
 
public interface ProductDAO {
    
    public List<ProductVO> selectProduct() throws Exception;
    
//상품 추가 관련(시간, 상품 모두)
    public ProductVO selectProductName(ProductVO productVO) throws Exception;

    public int updateProductTot(ProductVO productVO) throws Exception;
}


