package com.drimsys.controller;

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
import com.drimsys.service.inf.ComputerService;
import com.drimsys.service.inf.ProductService;
import com.drimsys.service.inf.SalesService;
import com.drimsys.service.inf.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {

	@Inject
	private ProductService product_service;
	@Inject
	private SalesService sales_service;
	@Inject
	private ComputerService computer_service;
	@Inject
	private UserService user_service;
	
	@RequestMapping(value = "/admin_product", method = RequestMethod.GET)
	public String admin_product(Locale locale, Model model) throws Exception {

		List<ProductVO> productList = product_service.select_admin_Product();
		model.addAttribute("productList", productList);

		return "admin_product";
	}

	@RequestMapping(value = "/admin_sales_date", method = RequestMethod.GET)
	public String admin_sales_date(Locale locale, Model model) throws Exception {

//		productList = sales_service.salesDate(productVO);

		return "admin_sales_date";
	}

	@RequestMapping(value = "/admin_sales_product", method = RequestMethod.GET)
	public String admin_sales_product(Locale locale, Model model) throws Exception {

		List<ProductVO> productList = sales_service.salesProduct();
		model.addAttribute("productList", productList);

		return "admin_sales_product";
	}

	@RequestMapping(value = "/admin_sales_user", method = RequestMethod.GET)
	public String admin_sales_user(Locale locale, Model model) throws Exception {

		List<UserVO> user_productList = sales_service.salesUser();
		model.addAttribute("user_productList", user_productList);

		return "admin_sales_user";
	}

	@RequestMapping(value = "/admin_seat", method = RequestMethod.GET)
	public String admin_seat(Locale locale, Model model) throws Exception {

		List<ComputerVO> computerList = computer_service.selectComputer();
		model.addAttribute("computerList", computerList);

		return "admin_seat";
	}
	
	@RequestMapping(value = "/admin_user", method = RequestMethod.GET)
	public String admin_user(Locale locale, Model model) throws Exception {

		List<UserVO> admin_userList = user_service.admin_selectUser();
		model.addAttribute("admin_userList", admin_userList);

		return "admin_user";
	}

}