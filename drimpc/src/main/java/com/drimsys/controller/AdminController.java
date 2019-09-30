package com.drimsys.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drimsys.dto.*;
import com.drimsys.service.inf.ComputerService;
import com.drimsys.service.inf.ProductService;
import com.drimsys.service.inf.SalesService;
import com.drimsys.service.inf.UserService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	public String admin_product(Locale locale, HttpSession session, Model model) throws Exception {

		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		if (login_status.getUser_id().equals("admin")) {
			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(time.getTime());
			model.addAttribute("now_date", date);

			List<ProductVO> productList = product_service.select_admin_Product();
			model.addAttribute("productList", productList);

			return "admin_product";
		} else
			return "redirect:/user_main";
	}

	@RequestMapping(value = "/admin_sales_date", method = RequestMethod.GET)
	public String admin_sales_date(Locale locale, HttpSession session, Model model) throws Exception {

		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		if (login_status.getUser_id().equals("admin")) {
			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(time.getTime());
			model.addAttribute("now_date", date);

			// 날자별 판매 관련
			User_ProductVO Date = new User_ProductVO();
			Date = sales_service.select_salesAll();
			if (Date != null) {
				String start_date = Date.getMin_date();
				String end_date = Date.getMax_date();
				ProductVO salesDate = new ProductVO();
				salesDate.setEnd_date(end_date);
				salesDate.setStart_date(start_date);
				salesDate = sales_service.salesDate(salesDate);
				if (salesDate != null) {
					salesDate.setEnd_date(end_date);
					salesDate.setStart_date(start_date);
					model.addAttribute("salesDate", salesDate);

				}
			}
			return "admin_sales_date";
		}
		return "redirect:/user_main";
	}

	@RequestMapping(value = "/admin_sales_product", method = RequestMethod.GET)
	public String admin_sales_product(Locale locale, HttpSession session, Model model) throws Exception {

		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		if (login_status.getUser_id().equals("admin")) {
			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(time.getTime());
			model.addAttribute("now_date", date);

			List<ProductVO> productList = sales_service.salesProduct();
			model.addAttribute("productList", productList);

			return "admin_sales_product";
		}
		return "redirect:/user_main";
	}

	@RequestMapping(value = "/admin_sales_user", method = RequestMethod.GET)
	public String admin_sales_user(Locale locale, HttpSession session, Model model) throws Exception {

		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		if (login_status.getUser_id().equals("admin")) {
			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(time.getTime());
			model.addAttribute("now_date", date);

			List<UserVO> user_productList = sales_service.salesUser();
			model.addAttribute("user_productList", user_productList);

			return "admin_sales_user";
		}
		return "redirect:/user_main";
	}

	@RequestMapping(value = "/admin_seat", method = RequestMethod.GET)
	public String admin_seat(HttpSession session, Model model) throws Exception {

		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		if (login_status.getUser_id().equals("admin")) {
			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(time.getTime());
			model.addAttribute("now_date", date);

			List<ComputerVO> computerList = computer_service.selectComputer();
			model.addAttribute("computerList", computerList);

			return "admin_seat";
		}
		return "redirect:/user_main";
	}

	@RequestMapping(value = "/admin_user", method = RequestMethod.GET)
	public String admin_user(Locale locale, HttpSession session, Model model) throws Exception {

		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		if (login_status.getUser_id().equals("admin")) {
			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(time.getTime());
			model.addAttribute("now_date", date);

			List<UserVO> admin_userList = user_service.admin_selectUser();
			model.addAttribute("admin_userList", admin_userList);

			return "admin_user";
		}
		return "redirect:/user_main";
	}

	@RequestMapping(value = "/admin_add_product", method = RequestMethod.GET)
	public String admin_add_product(Locale locale, HttpSession session, Model model) throws Exception {

		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		if (login_status.getUser_id().equals("admin")) {
			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(time.getTime());
			model.addAttribute("now_date", date);

			return "admin_add_product";
		}
		return "redirect:/user_main";
	}

	@RequestMapping(value = "/admin_sales_date2", method = RequestMethod.GET)
	public String admin_sales_date2(Locale locale, HttpSession session, Model model) throws Exception {

		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		if (login_status.getUser_id().equals("admin")) {
			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(time.getTime());
			model.addAttribute("now_date", date);

			return "admin_sales_date2";
		}
		return "redirect:/user_main";
	}

	@RequestMapping(value = "/salesDateProcess", method = RequestMethod.GET)
	public ModelAndView salesDateProcess(HttpServletRequest request, HttpSession session, Model model)
			throws Exception {

		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		if (login_status.getUser_id().equals("admin")) {
			ModelAndView mav = new ModelAndView();
			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(time.getTime());
			mav.addObject("now_date", date);

			String start_date = "";
			String end_date = "";
			String start_date2 = "";
			String end_date2 = "";

			int i_year = -1;
			int i_month = -1;
			String year = "";
			String month = "";
			if (request.getParameter("year").equals("") || request.getParameter("month").equals("")) {
				mav.setViewName("admin_sales_date");
				return mav;
			}

			month = request.getParameter("month");
			if (month.equals("0")) {
				i_year = Integer.parseInt(request.getParameter("year"));
				start_date = Integer.toString(i_year) + "-01-01 00:00:00";
				end_date = Integer.toString(i_year + 1) + "-01-01 00:00:00";
				start_date2 = Integer.toString(i_year) + "-01-01";
				end_date2 = Integer.toString(i_year + 1) + "-01-01";
			} else if (month.equals("12")) {
				i_year = Integer.parseInt(request.getParameter("year"));
				i_month = Integer.parseInt(request.getParameter("month"));
				start_date = Integer.toString(i_year) + "-" + Integer.toString(i_month) + "-01 00:00:00";
				end_date = Integer.toString(i_year + 1) + "-01-01 00:00:00";
				start_date2 = Integer.toString(i_year) + "-" + Integer.toString(i_month) + "-01";
				end_date2 = Integer.toString(i_year + 1) + "-01-01";
			} else if (!month.equals("0")) {
				year = request.getParameter("year");
				i_month = Integer.parseInt(request.getParameter("month"));
				start_date = year + "-" + Integer.toString(i_month) + "-01 00:00:00";
				end_date = year + "-" + Integer.toString(i_month + 1) + "-01 00:00:00";
				start_date2 = year + "-" + Integer.toString(i_month) + "-01";
				end_date2 = year + "-" + Integer.toString(i_month + 1) + "-01";
			}
			ProductVO sales_date = new ProductVO();
			sales_date.setStart_date(start_date);
			sales_date.setEnd_date(end_date);
			sales_date = sales_service.salesDate(sales_date);
			if (sales_date == null) {
				sales_date = new ProductVO();
				sales_date.setStart_date(start_date2);
				sales_date.setEnd_date(end_date2);
				sales_date.setJoin_date_price(0);
			}

			mav.setViewName("admin_sales_date2");
			mav.addObject("sales_date", sales_date);
			mav.addObject("start_date", start_date2);
			mav.addObject("end_date", end_date2);

			return mav;
		}
		ModelAndView mav_user = new ModelAndView();
		mav_user.setViewName("user_main");
		return mav_user;
	}

//	@RequestMapping(value = "/productEditProcess", method = RequestMethod.GET)
//	public String orderProcess(HttpServletRequest request, HttpSession session, Model model) throws Exception {
//
//		UserVO login_status = new UserVO();
//		login_status = (UserVO) session.getAttribute("login");
//		if (login_status.getUser_id().equals("admin")) {
//			ProductVO edit_productVO = new ProductVO();
//			Calendar time = Calendar.getInstance();
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//			String date = format.format(time.getTime());
//			model.addAttribute("date", date);
//			int i = 0;
//
//			while (true) {
//				String p_name = "p_name" + Integer.toString(i);
//				String p_tot = "p_tot" + Integer.toString(i);
//				String p_available = "p_available" + Integer.toString(i);
//				i++;
//
//				String product_name = "";
//				int edit_product_tot = -1;
//				int edit_product_avilable = -1;
//				if (request.getParameter(p_tot) == null || request.getParameter(p_name) == null
//						|| request.getParameter(p_available) == null) {
//					break;
//				}
//
//				edit_product_tot = Integer.parseInt(request.getParameter(p_tot));
//				product_name = (String) request.getParameter(p_name);
//				if (((String) request.getParameter(p_available)).equals("판매중")) {
//					edit_product_avilable = 1;
//				} else if (((String) request.getParameter(p_available)).equals("판매중지")) {
//					edit_product_avilable = 0;
//				}
//
//				edit_productVO.setProduct_name(product_name);
//				edit_productVO = product_service.select_admin_check(edit_productVO);
//				if (edit_product_tot == edit_productVO.getProduct_tot()
//						&& edit_product_avilable == edit_productVO.getProduct_available()) {
//					continue;
//				}
//				edit_productVO.setProduct_tot(edit_product_tot);
//				edit_productVO.setProduct_available(edit_product_avilable);
//				if (product_service.update_admin_product(edit_productVO))
//					System.out.println("상품 변경");
//			}
//
//			return "redirect:/admin_product";
//		}
//		return "redirect:/user_main";
//	}
	@GetMapping(value = "/productEditProcess")
	@ResponseBody
	public List<ProductVO> productEditProcess(HttpSession session, Model model,
			@RequestParam("productList") String product_str) throws Exception {
		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		if (login_status.getUser_id().equals("admin")) {
			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(time.getTime());
			model.addAttribute("now_date", date);

			List<ProductVO> product_list = new ArrayList<ProductVO>();
			JsonParser jsonParser = new JsonParser();
			JsonArray jsonArray = (JsonArray) jsonParser.parse(product_str);
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonObject object = (JsonObject) jsonArray.get(i);
				String NAME = object.get("product_name").getAsString();
				int PRICE = object.get("product_price").getAsInt();
				int TOT = object.get("product_tot").getAsInt();
				int AVAILABLE = object.get("product_available").getAsInt();

				ProductVO product = new ProductVO();
				product.setProduct_name(NAME);
				product.setProduct_price(PRICE);
				product.setProduct_tot(TOT);
				product.setProduct_available(AVAILABLE);
				product.setProduct_id(product_service.select_admin_check(product).getProduct_id());
				product_list.add(product);
			}

			for (int i = 0; i < product_list.size(); i++) {
				product_service.update_admin_product(product_list.get(i));
				if (product_list.get(i).getProduct_available() == 1)
					System.out.print(i + "수정(판매중) / ");
				else if (product_list.get(i).getProduct_available() == 0)
					System.out.print(i + "수정(판매중지) / ");
			}
			System.out.println();
			List<ProductVO> productList = product_service.select_admin_Product();
			System.out.println(productList.get(1).getProduct_name());
			return productList;
		}
		return null;
	}

	@GetMapping(value = "/productAddProcess",  produces = "application/text; charset=utf8")
	@ResponseBody
	public String addProcess(HttpSession session, Model model, @RequestParam("addList") String product_str)
			throws Exception {
		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		if (login_status.getUser_id().equals("admin")) {
			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(time.getTime());
			model.addAttribute("date", date);
			int success = 0, fail = 0;

			List<ProductVO> product_list = new ArrayList<ProductVO>();
			JsonParser jsonParser = new JsonParser();
			JsonArray jsonArray = (JsonArray) jsonParser.parse(product_str);
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonObject object = (JsonObject) jsonArray.get(i);
				String ID = object.get("product_id").getAsString();
				String NAME = object.get("product_name").getAsString();
				int PRICE = object.get("product_price").getAsInt();
				int TOT = object.get("product_tot").getAsInt();
				int AVAILABLE = object.get("product_available").getAsInt();
				if (ID.equals("") || NAME.equals("") || PRICE == 0 || TOT == 0 || AVAILABLE == 0)
					continue;
				ProductVO product = new ProductVO();
				product.setProduct_id(ID);
				product.setProduct_name(NAME);
				product.setProduct_price(PRICE);
				product.setProduct_tot(TOT);
				product.setProduct_available(AVAILABLE);
				product_list.add(product);
			}
			for (int i = 0; i < product_list.size(); i++) {
				if (product_service.insert_Product(product_list.get(i))) {
					System.out.print(i + "상품 추가 성공 / ");
					success++;
				} else {
					System.out.print(i + "상품 추가 실패 / ");
					fail++;
				}
			}
			String rtString = product_list.size() + "개 중 " + success + "개 성공 / " + fail + "개 실패";
			System.out.println(rtString);

			return rtString;
		}
		return null;
	}

	@GetMapping(value = "/seatEditProcess")
	@ResponseBody
	public List<ComputerVO> seatEditProcess(HttpSession session, Model model,
			@RequestParam("computerList") String computer_str) throws Exception {

		UserVO login_status = new UserVO();
		login_status = (UserVO) session.getAttribute("login");
		if (login_status.getUser_id().equals("admin")) {
			if (computer_str == null) {
				return null;
			}

			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = format.format(time.getTime());
			model.addAttribute("now_date", date);

			System.out.println(computer_str);

			List<ComputerVO> computer_list = new ArrayList<ComputerVO>();
			JsonParser jsonParser = new JsonParser();
			JsonArray jsonArray = (JsonArray) jsonParser.parse(computer_str);
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonObject object = (JsonObject) jsonArray.get(i);
				int ID = object.get("computer_id").getAsInt();
				int STATUS = object.get("computer_status").getAsInt();

				ComputerVO computer = new ComputerVO();
				computer.setComputer_id(ID);
				computer.setComputer_status(STATUS);
				computer_list.add(computer);
			}

			for (int i = 0; i < computer_list.size(); i++) {
				if (computer_list.get(i).getComputer_status() == 1) {
					if (computer_service.updateComputer_status_t(computer_list.get(i))) {
						System.out.print(i + "수정(정상) / ");
					}
				} else if (computer_list.get(i).getComputer_status() == 0) {
					if (computer_service.updateComputer_status_f(computer_list.get(i))) {
						System.out.print(i + "수정(고장) / ");
					}
				}
			}

			System.out.println();
			List<ComputerVO> computerList = computer_service.selectComputer();
			return computerList;
		}
		return null;
	}
}