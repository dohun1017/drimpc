package com.drimsys.drimpc;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.drimsys.dao.inf.ProductDAO;
import com.drimsys.service.inf.ProductService;
import com.drimsys.dto.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })

@WebAppConfiguration

public class TestDaoProduct {

	@Inject
	ProductService product_service;

	@Test
	public void testProduct() throws Exception{

		List<ProductVO> productList = product_service.selectProduct();

		for (ProductVO product : productList) {

			System.out.println(product);

		}

	}

}