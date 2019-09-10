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
import org.springframework.web.servlet.ModelAndView;

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
		
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		model.addAttribute("now_date",date);
		
		List<ProductVO> productList = product_service.select_admin_Product();
		model.addAttribute("productList", productList);

		return "admin_product";
	}

	@RequestMapping(value = "/admin_sales_date", method = RequestMethod.GET)
	public String admin_sales_date(Locale locale, Model model) throws Exception {

		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		model.addAttribute("now_date",date);
		
		// 날자별 판매 관련
		User_ProductVO Date = new User_ProductVO();
		Date = sales_service.select_salesAll();
		String start_date = Date.getMin_date();
		String end_date = Date.getMax_date();
		ProductVO salesDate = new ProductVO();
		salesDate.setEnd_date(end_date);
		salesDate.setStart_date(start_date);
		salesDate = sales_service.salesDate(salesDate);
		if (salesDate != null) {
			salesDate.setEnd_date(end_date);
			salesDate.setStart_date(start_date);
		}
		model.addAttribute("salesDate",salesDate);

		return "admin_sales_date";
	}

	@RequestMapping(value = "/admin_sales_product", method = RequestMethod.GET)
	public String admin_sales_product(Locale locale, Model model) throws Exception {

		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		model.addAttribute("now_date",date);
		
		List<ProductVO> productList = sales_service.salesProduct();
		model.addAttribute("productList", productList);

		return "admin_sales_product";
	}

	@RequestMapping(value = "/admin_sales_user", method = RequestMethod.GET)
	public String admin_sales_user(Locale locale, Model model) throws Exception {
		
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		model.addAttribute("now_date",date);
		
		List<UserVO> user_productList = sales_service.salesUser();
		model.addAttribute("user_productList", user_productList);

		return "admin_sales_user";
	}

	@RequestMapping(value = "/admin_seat", method = RequestMethod.GET)
	public String admin_seat(Locale locale, Model model) throws Exception {
		
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		model.addAttribute("now_date",date);
		
		List<ComputerVO> computerList = computer_service.selectComputer();
		model.addAttribute("computerList", computerList);

		return "admin_seat";
	}

	@RequestMapping(value = "/admin_user", method = RequestMethod.GET)
	public String admin_user(Locale locale, Model model) throws Exception {

		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		model.addAttribute("now_date",date);
		
		List<UserVO> admin_userList = user_service.admin_selectUser();
		model.addAttribute("admin_userList", admin_userList);

		return "admin_user";
	}

	@RequestMapping(value = "/admin_add_product", method = RequestMethod.GET)
	public String admin_add_product(Locale locale, Model model) throws Exception {

		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		model.addAttribute("now_date",date);
		
		return "admin_add_product";
	}
	
	@RequestMapping(value = "/admin_sales_date2", method = RequestMethod.GET)
	public String admin_sales_date2(Locale locale, Model model) throws Exception {

		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		model.addAttribute("now_date",date);
		
		return "admin_sales_date2";
	}
	
	@RequestMapping(value = "/salesDateProcess", method = RequestMethod.GET)
	public ModelAndView salesDateProcess(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		mav.addObject("now_date",date);
		
		String start_date = "";
		String end_date = "";
		String start_date2 = "";
		String end_date2 = "";
		
		int i_year = -1;
		int i_month = -1;
		String year = "";
		String month = "";
		if(request.getParameter("year").equals("") || request.getParameter("month").equals("")) {
			mav.setViewName("admin_sales_date");
			return mav;
		}
		
		month = request.getParameter("month");
		if(month.equals("0")) {
			i_year = Integer.parseInt(request.getParameter("year"));
			start_date = Integer.toString(i_year) + "-01-01 00:00:00";
			end_date = Integer.toString(i_year+1) + "-01-01 00:00:00";
			start_date2 = Integer.toString(i_year) + "-01-01";
			end_date2 = Integer.toString(i_year+1) + "-01-01";
		}else if(month.equals("12")) {
			i_year = Integer.parseInt(request.getParameter("year"));
			i_month = Integer.parseInt(request.getParameter("month"));
			start_date = Integer.toString(i_year) + "-" + Integer.toString(i_month) + "-01 00:00:00";
			end_date = Integer.toString(i_year+1) + "-01-01 00:00:00";
			start_date2 = Integer.toString(i_year) + "-" + Integer.toString(i_month) + "-01";
			end_date2 = Integer.toString(i_year+1) + "-01-01";
		}else if(!month.equals("0")){
			year = request.getParameter("year");
			i_month = Integer.parseInt(request.getParameter("month"));
			start_date = year + "-" + Integer.toString(i_month) + "-01 00:00:00";
			end_date = year + "-" + Integer.toString(i_month+1) + "-01 00:00:00";
			start_date2 = year + "-" + Integer.toString(i_month) + "-01";
			end_date2 = year + "-" + Integer.toString(i_month+1) + "-01";
		}
		ProductVO sales_date = new ProductVO();
		sales_date.setStart_date(start_date);
		sales_date.setEnd_date(end_date);
		sales_date = sales_service.salesDate(sales_date);
		if(sales_date == null) {
			sales_date = new ProductVO();
			sales_date.setStart_date(start_date2);
			sales_date.setEnd_date(end_date2);
			sales_date.setJoin_date_price(0);
		}
		
		mav.setViewName("admin_sales_date2");
		mav.addObject("sales_date",sales_date);
		
		return mav;
	}

	@RequestMapping(value = "/productEditProcess", method = RequestMethod.GET)
	public String orderProcess(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		ProductVO edit_productVO = new ProductVO();
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		model.addAttribute("date",date);
		int i = 0;

		while (true) {
			String p_name = "p_name" + Integer.toString(i);
			String p_tot = "p_tot" + Integer.toString(i);
			String p_available = "p_available" + Integer.toString(i);
			System.out.println(p_name + " / " + p_tot + " / " + p_available);
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
			if (((String) request.getParameter(p_available)).equals("판매중")) {
				edit_product_avilable = 1;
			} else if (((String) request.getParameter(p_available)).equals("판매중지")) {
				edit_product_avilable = 0;
			}

			edit_productVO.setProduct_name(product_name);
			edit_productVO = product_service.select_admin_check(edit_productVO);
			if (edit_product_tot == edit_productVO.getProduct_tot()
					&& edit_product_avilable == edit_productVO.getProduct_available()) {
				continue;
			}
			edit_productVO.setProduct_tot(edit_product_tot);
			edit_productVO.setProduct_available(edit_product_avilable);
			if (product_service.update_admin_product(edit_productVO))
				System.out.println("상품 변경");
		}

		return "redirect:/admin_product";
	}

	@RequestMapping(value = "/productAddProcess", method = RequestMethod.GET)
	public String addProcess(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		ProductVO add_productVO = new ProductVO();
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		model.addAttribute("date",date);
		int i = 0;

		while (true) {
			String p_id = "p_id" + Integer.toString(i);
			String p_name = "p_name" + Integer.toString(i);
			String p_price = "p_price" + Integer.toString(i);
			String p_tot = "p_tot" + Integer.toString(i);
			String p_available = "p_available" + Integer.toString(i);
			i++;

			String product_id = "";
			String product_name = "";
			int product_price = -1;
			int product_tot = -1;
			int product_available = -1;
			if (request.getParameter(p_id).equals("") || request.getParameter(p_name).equals("")
					|| request.getParameter(p_price).equals("") || request.getParameter(p_tot).equals("")
					|| request.getParameter(p_available).equals("")) {
				break;
			}
			product_id = (String) request.getParameter(p_id);
			product_name = (String) request.getParameter(p_name);
			product_price = Integer.parseInt(request.getParameter(p_price));
			product_tot = Integer.parseInt(request.getParameter(p_tot));
			if (((String) request.getParameter(p_available)).equals("판매중")) {
				product_available = 1;
			} else if (((String) request.getParameter(p_available)).equals("판매중지")) {
				product_available = 0;
			}

			add_productVO.setProduct_id(product_id);
			add_productVO.setProduct_name(product_name);
			add_productVO.setProduct_price(product_price);
			add_productVO.setProduct_tot(product_tot);
			add_productVO.setProduct_available(product_available);
			if (product_service.insert_Product(add_productVO))
				System.out.println("상품 추가 성공");
		}

		return "redirect:/admin_product";
	}

}