package com.drimsys.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drimsys.dto.ComputerVO;
import com.drimsys.dto.ProductVO;
import com.drimsys.dto.UserVO;
import com.drimsys.dto.User_ComputerVO;
import com.drimsys.dto.User_ProductVO;
import com.drimsys.service.inf.ComputerService;
import com.drimsys.service.inf.LoginService;
import com.drimsys.service.inf.OrderService;
import com.drimsys.service.inf.ProductService;
import com.drimsys.service.inf.SalesService;
import com.drimsys.service.inf.SignUpService;
import com.drimsys.service.inf.UserService;
import com.drimsys.service.inf.User_ComputerService;
import com.drimsys.service.inf.User_ProductService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private UserService user_service;
	@Inject
	private ComputerService computer_service;
	@Inject
	private ProductService product_service;
	@Inject
	private User_ComputerService user_computer_service;
	@Inject
	private User_ProductService user_product_service;
	@Inject
	private LoginService login_service;
	@Inject
	private SignUpService signUp_service;
	@Inject
	private OrderService order_service;
	@Inject
	private SalesService sales_service;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {

		logger.info("home");

		List<UserVO> userList = user_service.selectUser();
		List<ComputerVO> computerList = computer_service.selectComputer();
		List<ProductVO> productList = product_service.selectProduct();
		List<User_ComputerVO> user_computerList = user_computer_service.selectUser_Computer();
		List<User_ProductVO> user_productList = user_product_service.selectUser_Product();
		
		model.addAttribute("userList", userList);
		model.addAttribute("computerList", computerList);
		model.addAttribute("productList", productList);
		model.addAttribute("user_computerList", user_computerList);
		model.addAttribute("user_productList", user_productList);

		List<UserVO> salesList = sales_service.salesUser();
		model.addAttribute("salesList",salesList);
		
		List<ProductVO> salesList2 = sales_service.salesProduct();
		model.addAttribute("salesList2", salesList2);
		
//		//로그인 API
//		String user_id = "one1";
//		String user_pw = "1111";
//		int computer_id = 1;
//		UserVO login_user = new UserVO();
//		login_user.setUser_id(user_id);
//		login_user.setUser_pw(user_pw);
//		login_service.selectUserUsing(login_user);
//		if(login_service.selectUserUsing(login_user).getUser_using() == 0) {
//			System.out.println("로그인 가능 아이디");
//			ComputerVO login_computer = new ComputerVO();
//			login_computer.setComputer_id(computer_id);
//			login_computer = login_service.selectComputerUsing(login_computer);
//			if(login_computer.getComputer_using() == 0 && login_computer.getComputer_status() == 1) {
//				System.out.println("컴퓨터 사용 가능");
//				login_computer.setComputer_id(computer_id);
//				if(login_service.updateUserUsing(login_user) == true)
//					if(login_service.updateComputerUsing(login_computer) == true)
//					{
//						User_ComputerVO login_uc = new User_ComputerVO();
//						login_uc.setUser_id(user_id);
//						login_uc.setComputer_id(computer_id);
//						login_uc.setStart("2019-09-04 17:00");
//						login_uc.setEnd("2019-09-04 17:00");
//						if(login_service.insertLoginUser_Computer(login_uc))
//							System.out.println("로그인 성공");
//					}
//			}
//		}
		
//		//회원가입 API
//		String user_name = "십일";
//		String user_id = "ten11";
//		String user_pw = "11111111";
//		String user_email = "eleven11@google.com";
//		int user_using = 0;
//		int user_time = 0;
//		UserVO signUpUser = new UserVO();
//		signUpUser.setUser_name(user_name);
//		signUpUser.setUser_id(user_id);
//		signUpUser.setUser_pw(user_pw);
//		signUpUser.setUser_using(user_using);
//		signUpUser.setUser_time(user_time);
//		signUpUser.setUser_email(user_email);
//		List<UserVO> test = signUp_service.selectSignUpUser(signUpUser);
//		Iterator<UserVO> it = test.iterator();
//		if(it.hasNext() == false) {
//			System.out.println("회원가입가능한 아이디");
//			if(signUp_service.insertSignUpUser(signUpUser))
//				System.out.println("회원가입 성공");
//		}
		
//		//시간 추가 API
//		String user_id = "one1";
//		String product_id = "시간";
//		int product_quantity = 4;
//		String date = "2019-09-04 13:54";
//		UserVO timeUser = new UserVO();
//		timeUser.setUser_id(user_id);
//		timeUser.setUser_select_quantity(product_quantity);
//		if(order_service.updateUserTime(timeUser)) {
//			ProductVO timeProduct = new ProductVO();
//			timeProduct.setProduct_id(product_id);
//			timeProduct.setUser_select_quantity(product_quantity);
//			if(order_service.updateProductTot(timeProduct)) {
//				User_ProductVO timeUP = new User_ProductVO();
//				timeUP.setUser_id(user_id);
//				timeUP.setProduct_id(product_id);
//				timeUP.setProduct_quantity(product_quantity);
//				timeUP.setDate(date);
//				if(order_service.insertUser_Product(timeUP))
//					System.out.println("시간추가 성공");
//			}
//			
//		}
		
//		//상품 주문
//		String user_id = "one1";
//		String product_id = "음료_1";
//		int product_quantity = 1;
//		String date = "2019-07-31 11:21";
//		ProductVO orderProduct = new ProductVO();
//		orderProduct.setProduct_id(product_id);
//		orderProduct.setUser_select_quantity(product_quantity);
//		if(order_service.updateProductTot(orderProduct)) {
//			User_ProductVO orderUP = new User_ProductVO();
//			orderUP.setUser_id(user_id);
//			orderUP.setProduct_id(product_id);
//			orderUP.setProduct_quantity(product_quantity);
//			orderUP.setDate(date);
//			if(order_service.insertUser_Product(orderUP))
//				System.out.println("주문 성공");
//		}
		return "home";
	}

}
