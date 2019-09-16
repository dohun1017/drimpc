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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.drimsys.controller.UserController.InnerThread;
import com.drimsys.dto.*;
import com.drimsys.service.inf.*;

@Controller
public class LoginController {

	@Inject
	LoginService login_service;
	@Inject
	ComputerService computer_service;
	@Inject
	SalesService sales_service;
	
	private BatchThread thread = new BatchThread();

	public class BatchThread extends Thread {
		public Calendar time;
		public SimpleDateFormat fmDate = new SimpleDateFormat("yyyy-MM-dd");
		public SimpleDateFormat fmHour = new SimpleDateFormat("HH:mm");
		
		public String end_date = "";
		public String start_date = "";
		public String hour = "";
		
		public List<ProductVO> productList;

		private void time() throws Exception {
			while (true) {
				time = Calendar.getInstance();
				hour = fmHour.format(time.getTime());
				end_date = fmDate.format(time.getTime());

				time.add(Calendar.DATE, -1);
				start_date = fmDate.format(time.getTime());
				end_date = end_date + " " + hour;
				start_date = start_date + " " + hour;
				
//				if (hour.equals("00:00")) {
				if (hour.equals("00:00")||hour.equals("02:02")) {
					ProductVO productVO = new ProductVO();
//					productVO.setEnd_date(end_date);
//					productVO.setStart_date(start_date);
					productVO.setEnd_date("2019-09-12 00:00");
					productVO.setStart_date("2019-09-11 00:00");
					productList = sales_service.batchProduct(productVO);
					if(productList != null) {
						Iterator<ProductVO> it = productList.iterator();
						System.out.println("상품명\t판매량\t날짜\t");
						while(it.hasNext()) {
							productVO = it.next();
							System.out.println(productVO.getProduct_name()+"\t"+productVO.getUcVO().getProduct_quantity()+"\t"+productVO.getUcVO().getDate());
						}
					}
					Thread.sleep(30000);
				} else {
					Thread.sleep(30000);
				}
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

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(Locale locale, HttpSession session, Model model) throws Exception {

		if (thread.getState().equals(Thread.State.NEW)){
			thread.start();
		}
		if (session.getAttribute("login") != null) {
			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String end_date = format.format(time.getTime());

			UserVO userVO = (UserVO) session.getAttribute("login");
			User_ComputerVO ucVO = (User_ComputerVO) session.getAttribute("ucVO");
			ComputerVO computerVO = (ComputerVO) session.getAttribute("computerVO");
			ucVO.setEnd(end_date);

			if (login_service.logoutUser(userVO))
				if (login_service.logoutComputer(computerVO))
					if (login_service.updateUser_Computer(ucVO)) {
						session.removeAttribute("login");
						session.removeAttribute("ucVO");
						session.removeAttribute("computerVO");
						return "redirect:/login";
					}
		}

		List<ComputerVO> computerList = computer_service.selectComputer();
		model.addAttribute("computerList", computerList);

		return "/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, HttpSession session, Model model) throws Exception {

		if (session.getAttribute("login") != null) {
			Calendar time = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String end_date = format.format(time.getTime());

			UserVO userVO = (UserVO) session.getAttribute("login");
			User_ComputerVO ucVO = (User_ComputerVO) session.getAttribute("ucVO");
			ComputerVO computerVO = (ComputerVO) session.getAttribute("computerVO");
			ucVO.setEnd(end_date);

			if (login_service.logoutUser(userVO))
				if (login_service.logoutComputer(computerVO))
					if (login_service.updateUser_Computer(ucVO)) {
						session.removeAttribute("login");
						session.removeAttribute("ucVO");
						session.removeAttribute("computerVO");
						return "redirect:/login";
					}
		}
		List<ComputerVO> computerList = computer_service.selectComputer();
		model.addAttribute("computerList", computerList);

		return "/login";
	}

	// 로그인 처리하는 부분
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpSession session, Model model, UserVO userVO)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/login");

		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		int computer_id = Integer.parseInt(request.getParameter("computer_id"));
		System.out.println(user_id);
		System.out.println(user_pw);
		userVO.setUser_id(user_id);
		userVO.setUser_pw(user_pw);

		userVO = login_service.selectUserUsing(userVO);
		if (userVO != null && userVO.getUser_using() == 0
				&& (userVO.getUser_time() != 0 || userVO.getUser_id().equals("admin"))) {
			ComputerVO computerVO = new ComputerVO();
			computerVO.setComputer_id(computer_id);
			computerVO = login_service.selectComputerUsing(computerVO);
			if (computerVO.getComputer_using() == 0 && computerVO.getComputer_status() == 1) {
				if (login_service.updateUserUsing(userVO) && login_service.updateComputerUsing(computerVO)) {
					User_ComputerVO ucVO = new User_ComputerVO();
					ucVO.setUser_id(user_id);
					ucVO.setComputer_id(computer_id);
					String start = format.format(time.getTime());
					ucVO.setStart(start);
					ucVO.setEnd(start);
					if (login_service.insertLoginUser_Computer(ucVO)) {
						if (userVO.getUser_id().equals("admin"))
							mav.setViewName("redirect:/admin_product");
						else
							mav.setViewName("redirect:/user_main");
						mav.addObject("userVO", userVO);
						session.setAttribute("login", userVO);
						session.setAttribute("ucVO", ucVO);
						session.setAttribute("computerVO", computerVO);
						System.out.println("로그인 성공");
					}
				}
			}
		}
		return mav; // 위에서 설정한 returnURL 을 반환해서 이동시킴
	}

	@RequestMapping(value = "/logoutProcess", method = RequestMethod.GET)
	public String logoutProcess(Locale locale, HttpSession session, Model model) throws Exception {

		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String end_date = format.format(time.getTime());

		UserVO userVO = (UserVO) session.getAttribute("login");
		User_ComputerVO ucVO = (User_ComputerVO) session.getAttribute("ucVO");
		ComputerVO computerVO = (ComputerVO) session.getAttribute("computerVO");
		ucVO.setEnd(end_date);

		if (login_service.logoutUser(userVO)) {
			System.out.println("INFO : 유저 성공");
			if (login_service.updateUser_Computer(ucVO)) {
				System.out.println("INFO : 유저 컴퓨터 성공");
				if (login_service.logoutComputer(computerVO)) {
					System.out.println("INFO : 컴퓨터 성공");
					session.removeAttribute("login");
					session.removeAttribute("ucVO");
					session.removeAttribute("computerVO");
					return "redirect:/login";
				}
			}
		}
		return "null";
	}
}
