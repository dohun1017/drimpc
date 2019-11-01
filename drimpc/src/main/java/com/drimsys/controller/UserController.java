package com.drimsys.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drimsys.dto.*;
import com.drimsys.scheduled.TimeProcess;
import com.drimsys.service.inf.LoginService;
import com.drimsys.service.inf.OrderService;
import com.drimsys.service.inf.ProductService;
import com.drimsys.service.inf.SignUpService;
import com.drimsys.service.inf.UserService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

	@RequestMapping(value = "/user_main", method = RequestMethod.GET)
	public String user_main(Locale locale, HttpSession session, Model model) throws Exception {

		UserVO run = ((UserVO) session.getAttribute("login"));
		if (run.getUser_time() == 0) {
			TimeProcess.setUserVO(null);
			return "redirect:/logoutProcess";
		}
		TimeProcess.setUserVO(run);
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		model.addAttribute("now_date", date);

		List<ProductVO> productList = product_service.select_Product_available();
		model.addAttribute("productList", productList);

		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		model.addAttribute("login_status", login_status);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_time", run.getUser_time());
		model.addAttribute("map", map);
		session.setAttribute("login", run);

		return "user_main";
	}

	@RequestMapping(value = "/time_add", method = RequestMethod.GET)
	public String time_add(Locale locale, Model model) throws Exception {

		return "time_add";
	}
	
	@GetMapping(value = "/orderProcess")
	@ResponseBody
	public List<ProductVO> orderProcess(HttpServletRequest request, HttpSession session, Model model,
			@RequestParam("orderList") String order_str) throws Exception {

		String user_id = ((UserVO)session.getAttribute("login")).getUser_id();
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		
		List<ProductVO> product_list = new ArrayList<ProductVO>();
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(order_str);
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject object = (JsonObject) jsonArray.get(i);
			String NAME = object.get("product_name").getAsString();
			int S_QUANTITY = object.get("user_select_quantity").getAsInt();
			
			ProductVO product = new ProductVO();
			product.setProduct_name(NAME);
			product = order_service.selectProductID(product);
			if(product.getProduct_tot() < S_QUANTITY)
				return null;
			product.setUser_select_quantity(S_QUANTITY);
			product_list.add(product);
			
			if (order_service.updateProductTot(product_list.get(i))) {
				User_ProductVO orderUP = new User_ProductVO();
				orderUP.setUser_id(user_id);
				orderUP.setProduct_id(product_list.get(i).getProduct_id());
				orderUP.setProduct_quantity(product_list.get(i).getUser_select_quantity());
				orderUP.setDate(date);
				if (order_service.insertUser_Product(orderUP))
					System.out.println(i+"주문 성공 / ");
			}
		}
		List<ProductVO> productList = product_service.select_Product_available();
		return productList;
		
		
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
	@ResponseBody
	public Object order_time_Process(UserVO userVO) throws Exception{
		ProductVO productVO = new ProductVO();
		User_ProductVO upVO = new User_ProductVO();
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(time.getTime());
		String user_id = userVO.getUser_id();
		int user_time = userVO.getUser_time();

		if (user_time == 0)
			return null;
		userVO.setUser_id(user_id);
		userVO.setUser_select_quantity(user_time);

		if (user_id.equals("admin"))
			if (order_service.updateUserTime(userVO))
				return userVO;
		
		if (order_service.updateUserTime(userVO)) {
			productVO.setProduct_id("시간");
			productVO.setProduct_name("시간");
			productVO.setUser_select_quantity(user_time * -1);
			if (order_service.updateProductTot(productVO)) {
				upVO.setUser_id(user_id);
				upVO.setProduct_id("시간");
				upVO.setProduct_quantity(user_time);
				upVO.setDate(date);
				if (order_service.insertUser_Product(upVO)) {
					System.out.println("시간 추가 성공");
					System.out.println(userVO.getUser_id());
					System.out.println(userVO.getUser_time());
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("user_id", userVO.getUser_id());
					map.put("user_time", userVO.getUser_select_quantity());
//					return map;
					return userVO;
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.GET)
	public String registerProcess(HttpServletRequest request, HttpSession session, Model model) throws Exception {

		if (!request.getParameter("user_pw").equals(request.getParameter("confirm_user_pw"))) {
			return "redirect:/register";
		}
		if (request.getParameter("user_id") == null || request.getParameter("user_email") == null
				|| request.getParameter("user_pw") == null || request.getParameter("user_name") == null) {
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
				return "redirect:/login";
			}
		}
		return "redirect:/forgot_password";
	}

	@RequestMapping(value = "/timeProcess", method = RequestMethod.GET)
	@ResponseBody
	public Object loginProcess(HttpSession session) throws Exception {

		UserVO run = ((UserVO) session.getAttribute("login"));
		if (run != null && run.getUser_time() == 0) {
			TimeProcess.setUserVO(null);
		}
		TimeProcess.setUserVO(run);
		session.setAttribute("login", run);
		return run;
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyProcess", method = RequestMethod.POST)
	public UserVO modifyProcess(HttpSession session, @RequestBody UserVO userVO) throws Exception {

		String user_id = userVO.getUser_id();
		String user_email = user_service.selectUser_email(userVO).getUser_email();
		String user_pw = userVO.getUser_pw();
		
		userVO.setUser_email(user_email);
		userVO.setUser_id(user_id);
		userVO.setUser_pw(user_pw);
		if (user_service.select_forgot_user(userVO) != null) {
			if (user_service.update_user_pw(userVO)) {
				System.out.println("초기화 완료");
				return userVO;
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/user_modify", method = RequestMethod.GET)
	public String user_modify(HttpSession session, Model model) throws Exception {
		
		UserVO userVO = (UserVO)session.getAttribute("login");
		model.addAttribute("login",userVO);
		
		return "user_modify";
	}
	
	

}