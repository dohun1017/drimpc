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

	@RequestMapping(value = "/productEditProcess", method = RequestMethod.GET)
	public String orderProcess(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		ProductVO edit_productVO = new ProductVO();
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		int i = 0;

		while (true) {
			String p_name = "p_name" + Integer.toString(i);
			String p_tot = "p_tot" + Integer.toString(i);
			String p_available = "p_available" + Integer.toString(i);
			System.out.println(p_name +" / "+p_tot+" / "+p_available);
			i++;

			String product_name = "";
			int edit_product_tot = -1;
			int edit_product_avilable = -1;
			if (request.getParameter(p_tot) == null || request.getParameter(p_name) == null
					|| request.getParameter(p_available) == null) {
				System.out.println("브레이크");
				break;
			}
			

			edit_product_tot = Integer.parseInt(request.getParameter(p_tot));
			product_name = (String) request.getParameter(p_name);
			if (((String)request.getParameter(p_available)).equals("판매중")) {
				edit_product_avilable = 1;
			} else if (((String)request.getParameter(p_available)).equals("판매중지")) {
				edit_product_avilable = 0;
			}
			
//			System.out.println(product_name +" / " + edit_product_avilable + " / " + edit_product_tot);
			
			edit_productVO.setProduct_name(product_name);
			edit_productVO = product_service.select_admin_check(edit_productVO);
			if (edit_product_tot == edit_productVO.getProduct_tot()
					&& edit_product_avilable == edit_productVO.getProduct_available()) {
				System.out.println("변경없음 컨티뉴 : " + edit_productVO.getProduct_name());
				continue;
			}
			edit_productVO.setProduct_tot(edit_product_tot);
			edit_productVO.setProduct_available(edit_product_avilable);
			if (product_service.update_admin_product(edit_productVO))
				System.out.println("업데이트 성공 : " + edit_productVO.getProduct_name());
		}

		return "redirect:/admin_product";
	}

}