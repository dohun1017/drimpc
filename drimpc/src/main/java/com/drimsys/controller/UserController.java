package com.drimsys.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drimsys.dto.*;
import com.drimsys.service.inf.OrderService;
import com.drimsys.service.inf.ProductService;
import com.drimsys.service.inf.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {

	@Inject
	ProductService product_service;
	@Inject
	OrderService order_service;
	@Inject
	UserService user_service;

	@RequestMapping(value = "/user_main", method = RequestMethod.GET)
	public String user_main(Locale locale, Model model) throws Exception {

		List<ProductVO> productList = product_service.select_Product_available();
		model.addAttribute("productList", productList);
		return "user_main";
	}

	@RequestMapping(value = "/user_add", method = RequestMethod.GET)
	public String user_add(Locale locale, Model model) throws Exception {

		return "user_add";
	}

	@RequestMapping(value = "/orderProcess", method = RequestMethod.GET)
	public String orderProcess(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		ProductVO order_productVO = new ProductVO();
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		int i = 0;

		while (true) {
			String p_name = "p_name" + Integer.toString(i);
			String p_num = "p_num" + Integer.toString(i);
			i++;

			int order_product_quantity = -1;
			String product_name = "";

			if (request.getParameter(p_num) == null || request.getParameter(p_name) == null)
				break;

			order_product_quantity = Integer.parseInt(request.getParameter(p_num));
			product_name = (String) request.getParameter(p_name);

			if (order_product_quantity == 0) {
				continue;
			}

			order_productVO.setProduct_name(product_name);
			order_productVO = order_service.selectProductID(order_productVO);

			if (order_productVO.getProduct_tot() < order_product_quantity) {
				continue;
			}

			order_productVO.setUser_select_quantity(order_product_quantity);
			if (order_service.updateProductTot(order_productVO)) {
				System.out.println("업데이트 성공");
				User_ProductVO orderUP = new User_ProductVO();
				orderUP.setUser_id("one1");
				orderUP.setProduct_id(order_productVO.getProduct_id());
				orderUP.setProduct_quantity(order_product_quantity);
				orderUP.setDate(date);
				if (order_service.insertUser_Product(orderUP))
					System.out.println("주문 성공");
			}
		}
		return "redirect:/user_main";
	}

	@RequestMapping(value = "/order_time_Process", method = RequestMethod.GET)
	public String order_time_Process(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		ProductVO productVO = new ProductVO();
		User_ProductVO upVO = new User_ProductVO();
		UserVO userVO = new UserVO();

		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());

		String user_id = request.getParameter("user_id");
		String str_user_time = request.getParameter("user_time");

		if (str_user_time.equals("0"))
			return "user_add";
		int user_time = Integer.parseInt(str_user_time);

		userVO.setUser_id(user_id);
		userVO.setUser_time(user_time * 60);
		if (order_service.updateUserTime(userVO)) {
			productVO.setProduct_id("시간");
			productVO.setProduct_name("시간");
			productVO.setUser_select_quantity(user_time * -1);
			if (order_service.updateProductTot(productVO)) {
				upVO.setUser_id(user_id);
				upVO.setProduct_id("시간");
				upVO.setProduct_quantity(user_time);
				upVO.setDate(date);
				if(order_service.insertUser_Product(upVO))
					return "user_add";
			}
		}

		return "request:/user_main";
	}
}