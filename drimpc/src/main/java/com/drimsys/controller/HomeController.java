package com.drimsys.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping(value="test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) throws Exception{
		return "test";
	}
/*	@RequestMapping(value = "/", method = RequestMethod.GET)
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

		// 사용자별 판매 관련
		List<UserVO> salesUserList = sales_service.salesUser();
		model.addAttribute("salesUserList", salesUserList);

		// 상품별 판매 관련
		List<ProductVO> salesProductList = sales_service.salesProduct();
		model.addAttribute("salesProductList", salesProductList);

		// 날자별 판매 관련
		ProductVO salesDate = new ProductVO();
		String start_date = "2019-08-01";
		String end_date = "2019-09-01";
		salesDate.setEnd_date(end_date);
		salesDate.setStart_date(start_date);
		salesDate = sales_service.salesDate(salesDate);
		if (salesDate != null) {
			salesDate.setEnd_date(end_date);
			salesDate.setStart_date(start_date);
		}
		model.addAttribute("salesDate", salesDate);

//		// 로그인 API
//		String login_user_id = "two2";
//		String login_user_pw = "2222";
//		int login_computer_id = 1;
//		UserVO login_user = new UserVO();
//		login_user.setUser_id(login_user_id);
//		login_user.setUser_pw(login_user_pw);
//		login_user = login_service.selectUserUsing(login_user);
//		if (login_user != null) {
//			if (login_user.getUser_using() == 0) {
//				System.out.println("로그인 가능 아이디");
//				ComputerVO login_computer = new ComputerVO();
//				login_computer.setComputer_id(login_computer_id);
//				login_computer = login_service.selectComputerUsing(login_computer);
//				if (login_computer.getComputer_using() == 0 && login_computer.getComputer_status() == 1) {
//					System.out.println("컴퓨터 사용 가능");
//					login_user.setUser_id(login_user_id);
//					login_user.setUser_pw(login_user_pw);
//					login_computer.setComputer_id(login_computer_id);
//					if (login_service.updateUserUsing(login_user) == true)
//						if (login_service.updateComputerUsing(login_computer) == true) {
//							User_ComputerVO login_uc = new User_ComputerVO();
//							login_uc.setUser_id(login_user_id);
//							login_uc.setComputer_id(login_computer_id);
//							login_uc.setStart("2019-09-05 17:00");
//							login_uc.setEnd("2019-09-05 17:00");
//							if (login_service.insertLoginUser_Computer(login_uc))
//								System.out.println("로그인 성공");
//						}
//				}
//			}
//		}
//
//		//회원가입 API
//		String sign_up_user_name = "십이";
//		String sign_up_user_id = "twelve12";
//		String sign_up_user_pw = "12121212";
//		String sign_up_user_email = "twelve12@google.com";
//		int sign_up_user_using = 0;
//		int sign_up_user_time = 0;
//		UserVO signUpUser = new UserVO();
//		signUpUser.setUser_name(sign_up_user_name);
//		signUpUser.setUser_id(sign_up_user_id);
//		signUpUser.setUser_pw(sign_up_user_pw);
//		signUpUser.setUser_using(sign_up_user_using);
//		signUpUser.setUser_time(sign_up_user_time);
//		signUpUser.setUser_email(sign_up_user_email);
//		List<UserVO> test = signUp_service.selectSignUpUser(signUpUser);
//		Iterator<UserVO> it = test.iterator();
//		if(it.hasNext() == false) {
//			System.out.println("회원가입가능한 아이디");
//			if(signUp_service.insertSignUpUser(signUpUser))
//				System.out.println("회원가입 성공");
//		}
//		
//		//시간 추가 API
//		String add_time_user_id = "one1";
//		String add_time_product_id = "시간";
//		int add_time_product_quantity = 4;
//		String add_time_date = "2019-09-05 18:02";
//		UserVO timeUser = new UserVO();
//		timeUser.setUser_id(add_time_user_id);
//		timeUser.setUser_select_quantity(add_time_product_quantity);
//		if(order_service.updateUserTime(timeUser)) {
//			ProductVO timeProduct = new ProductVO();
//			timeProduct.setProduct_id(add_time_product_id);
//			timeProduct.setUser_select_quantity(add_time_product_quantity);
//			if(order_service.updateProductTot(timeProduct)) {
//				User_ProductVO timeUP = new User_ProductVO();
//				timeUP.setUser_id(add_time_user_id);
//				timeUP.setProduct_id(add_time_product_id);
//				timeUP.setProduct_quantity(add_time_product_quantity);
//				timeUP.setDate(add_time_date);
//				if(order_service.insertUser_Product(timeUP))
//					System.out.println("시간추가 성공");
//			}
//			
//		}
//		
//		//상품 주문
//		String order_user_id = "one1";
//		String order_product_id = "음료_1";
//		int order_product_quantity = 1;
//		String order_date = "2019-09-04 13:46";
//		ProductVO orderProduct = new ProductVO();
//		orderProduct.setProduct_id(order_product_id);
//		orderProduct.setUser_select_quantity(order_product_quantity);
//		if(order_service.updateProductTot(orderProduct)) {
//			User_ProductVO orderUP = new User_ProductVO();
//			orderUP.setUser_id(order_user_id);
//			orderUP.setProduct_id(order_product_id);
//			orderUP.setProduct_quantity(order_product_quantity);
//			orderUP.setDate(order_date);
//			if(order_service.insertUser_Product(orderUP))
//				System.out.println("주문 성공");
//		}
//
//		//좌석 수정API
//		int update_computer_id = 2;
//		ComputerVO update_computerVO = new ComputerVO();
//		update_computerVO.setComputer_id(update_computer_id);
//		if(computer_service.updateComputer_status(update_computerVO))
//				System.out.println("컴퓨터 상태 변경");
//		else
//			System.out.println("컴퓨터 상태 변경 실패");
//		
//		//상품 상태 변경 API
//		String update_product_id = "라면_1";
//		ProductVO update_productVO = new ProductVO();
//		update_productVO.setProduct_id(update_product_id);
//		if(product_service.update_Product_available(update_productVO))
//			System.out.println("상품 상태 변경");
//		else
//			System.out.println("상품 상태 변경 실패");
//		
//		//상품 추가 API
//		String insert_product_id = "라면_4";
//		String insert_product_name ="불닭+짜파게티";
//		int insert_product_price = 1000;
//		int insert_product_tot = 18;
//		int insert_product_available = 1;
//		ProductVO insert_productVO = new ProductVO();
//		insert_productVO.setProduct_id(insert_product_id);
//		insert_productVO.setProduct_name(insert_product_name);
//		insert_productVO.setProduct_price(insert_product_price);
//		insert_productVO.setProduct_tot(insert_product_tot);
//		insert_productVO.setProduct_available(insert_product_available);
//		if(product_service.insert_Product(insert_productVO))
//			System.out.println("상품 추가 성공");
//		else
//			System.out.println("상품 추가 실패");

		// 회원정보 조회
		List<UserVO> admin_selectList = user_service.admin_selectUser();
		model.addAttribute("admin_selectList", admin_selectList);

		// 비밀번호 변경
		String update_user_id = "two2";
		String update_user_pw = "2222";
		UserVO update_userVO = new UserVO();
		update_userVO.setUser_id(update_user_id);
		update_userVO.setUser_pw(update_user_pw);
		if (user_service.update_user_pw(update_userVO))
			System.out.println("암호 변경 완료");
		else
			System.out.println("암호 변경 실패");

		return "test";
	}*/

}
