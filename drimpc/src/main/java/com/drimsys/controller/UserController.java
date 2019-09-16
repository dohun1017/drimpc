package com.drimsys.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.experimental.categories.Categories.IncludeCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drimsys.dto.*;
import com.drimsys.service.inf.LoginService;
import com.drimsys.service.inf.OrderService;
import com.drimsys.service.inf.ProductService;
import com.drimsys.service.inf.SignUpService;
import com.drimsys.service.inf.UserService;
import com.drimsys.controller.*;

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
	@Inject
	SignUpService signup_service;
	@Inject
	LoginService login_service;

	private InnerThread thread = new InnerThread();

	public class InnerThread extends Thread {
		UserVO userVO;

		public void setUserVO(UserVO userVO) {
			this.userVO = userVO;
		}

		public UserVO getUserVO() {
			return this.userVO;
		}

		private void time() throws Exception {
			while (true) {
				if (userVO != null && userVO.getUser_id().equals("admin"))
					break;
				if(Thread.interrupted()){
					break;
				}
				Thread.sleep(6000);
				int user_time = userVO.getUser_time();
				user_time -= 1;
				userVO.setUser_time(user_time);
				login_service.updateUser_time(userVO);
			}
		}

		@Override
		public void run() {
			try {
				time();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	@RequestMapping(value = "user_logoutProcess", method = RequestMethod.GET)
	public String user_logoutProcess(Locale locale, HttpSession session, Model model) throws Exception {
		thread.interrupt();
		return "redirect:/logoutProcess";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/user_main", method = RequestMethod.GET)
	public String user_main(Locale locale, HttpSession session, Model model) throws Exception {

		UserVO run = ((UserVO) session.getAttribute("login"));
		if(run.getUser_time() == 0) {
			thread.interrupt();
			return "redirect:/logoutProcess";
		}
		if (thread.getState().equals(Thread.State.TIMED_WAITING) || thread.getState().equals(Thread.State.RUNNABLE)) {
			thread.interrupt();
			thread = new InnerThread();
			thread.setUserVO(run);
			thread.start();
		} else{
			thread.setUserVO(run);
			thread.start();
		}
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		model.addAttribute("now_date", date);

		List<ProductVO> productList = product_service.select_Product_available();
		model.addAttribute("productList", productList);

		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		model.addAttribute("login_status", login_status);
		model.addAttribute("user_time", run.getUser_time());
		session.setAttribute("login", run);

		return "user_main";
	}

	@RequestMapping(value = "/time_add", method = RequestMethod.GET)
	public String time_add(Locale locale, Model model) throws Exception {

		return "time_add";
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

			if (request.getParameter(p_num) == null || request.getParameter(p_name) == null
					|| request.getParameter("user_id") == null)
				break;

			order_product_quantity = Integer.parseInt(request.getParameter(p_num));
			product_name = (String) request.getParameter(p_name);
			String user_id = (String) request.getParameter("user_id");

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
				orderUP.setUser_id(user_id);
				orderUP.setProduct_id(order_productVO.getProduct_id());
				orderUP.setProduct_quantity(order_product_quantity);
				orderUP.setDate(date);
				if (order_service.insertUser_Product(orderUP))
					System.out.println("주문 성공");
			}
		}
		return "redirect:/user_main";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Locale locale, Model model) throws Exception {
		return "register";
	}

	@RequestMapping(value = "/forgot_password", method = RequestMethod.GET)
	public String forgot_password(Locale locale, Model model) throws Exception {
		return "forgot_password";
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
		System.out.println(str_user_time);

		if (str_user_time.equals("0"))
			return "time_add";
		int user_time = Integer.parseInt(str_user_time);

		userVO.setUser_id(user_id);
		userVO.setUser_select_quantity(user_time);
		if (user_id.equals("admin"))
			if (order_service.updateUserTime(userVO))
				return "redirect:/login";
		if (order_service.updateUserTime(userVO)) {
			productVO.setProduct_id("시간");
			productVO.setProduct_name("시간");
			productVO.setUser_select_quantity(user_time * -1);
			if (order_service.updateProductTot(productVO)) {
				upVO.setUser_id(user_id);
				upVO.setProduct_id("시간");
				upVO.setProduct_quantity(user_time);
				upVO.setDate(date);
				if (order_service.insertUser_Product(upVO))
					if (session.getAttribute("login") == null)
						return "redirect:/login";
					else if (session.getAttribute("login") != null)
						return "redirect:/user_main";
			}
		}

		return "time_add";
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.GET)
	public String registerProcess(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		if (!request.getParameter("user_pw").equals(request.getParameter("confirm_user_pw"))) {
			System.out.println(request.getParameter("user_pw"));
			System.out.println(request.getParameter("confirm_user_pw"));
			return "redirect:/register";
		}
		if (request.getParameter("user_id") == null || request.getParameter("user_email") == null
				|| request.getParameter("user_pw") == null || request.getParameter("user_name") == null) {
			System.out.println(request.getParameter("user_id"));
			System.out.println(request.getParameter("user_email"));
			System.out.println(request.getParameter("user_name"));
			System.out.println(request.getParameter("user_pw"));
			return "redirect:/register";
		}
		UserVO userVO = new UserVO();
		String user_name = request.getParameter("user_name");
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_email = request.getParameter("user_email");

		userVO.setUser_email(user_email);
		userVO.setUser_id(user_id);
		userVO.setUser_name(user_name);
		userVO.setUser_pw(user_pw);

		List<UserVO> possible = signup_service.selectSignUpUser(userVO);
		Iterator<UserVO> it = possible.iterator();
		if (!it.hasNext()) {
			System.out.println("회원가입 가능 아이디");
			if (signup_service.insertSignUpUser(userVO)) {
				System.out.println("회원가입 성공");
				return "login";
			}
		}
		return "redirect:/register";
	}

	@RequestMapping(value = "/forgotProcess", method = RequestMethod.GET)
	public String forgotProcess(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		if (request.getParameter("user_id") == null || request.getParameter("user_email") == null) {
			return "redirect:/forgot_password";
		}
		UserVO userVO = new UserVO();
		String user_id = request.getParameter("user_id");
		String user_email = request.getParameter("user_email");

		userVO.setUser_email(user_email);
		userVO.setUser_id(user_id);
		userVO.setUser_pw("1234");
		if (user_service.select_forgot_user(userVO) != null) {
			if (user_service.update_user_pw(userVO)) {
				System.out.println("초기화 완료");
				return "login";
			}
		}
		return "redirect:/forgot_password";
	}
}