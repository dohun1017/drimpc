package com.drimsys.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drimsys.dto.*;
import com.drimsys.scheduled.TimeProcess;
import com.drimsys.service.inf.*;

@Controller
public class LoginController {

	@Inject
	LoginService login_service;
	@Inject
	ComputerService computer_service;
	@Inject
	SalesService sales_service;

	@RequestMapping(value = "/logoutProcess", method = RequestMethod.GET)
	public String logoutProcess(Locale locale, HttpSession session, Model model) throws Exception {
		TimeProcess.setUserVO(null);
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String end_date = format.format(time.getTime());

		UserVO userVO = (UserVO) session.getAttribute("login");
		User_ComputerVO ucVO = (User_ComputerVO) session.getAttribute("ucVO");
		ComputerVO computerVO = (ComputerVO) session.getAttribute("computerVO");
		ucVO.setEnd(end_date);

		if (login_service.logoutUser(userVO)) {
			if (login_service.updateUser_Computer(ucVO)) {
				if (login_service.logoutComputer(computerVO)) {
					System.out.println(((UserVO)session.getAttribute("login")).getUser_id() + " 로그아웃 성공");
					session.removeAttribute("login");
					session.removeAttribute("ucVO");
					session.removeAttribute("computerVO");
					return "redirect:/login";
				}
			}
		}
		return "null";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(Locale locale, HttpSession session, Model model) throws Exception {

		return "redirect:/login";
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
			TimeProcess.setUserVO(null);

			if (login_service.logoutUser(userVO))
				if (login_service.logoutComputer(computerVO))
					if (login_service.updateUser_Computer(ucVO)) {
						System.out.println(((UserVO)session.getAttribute("login")).getUser_id() + " 로그아웃 성공");
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
	@ResponseBody
	public UserVO loginProcess(HttpServletRequest request, HttpSession session, Model model, @RequestBody UserVO userVO)
			throws Exception {
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		int computer_id = userVO.getSelect_computer();

		userVO = login_service.selectUserUsing(userVO);
		if (userVO != null && userVO.getUser_using() == 0
				&& (userVO.getUser_time() != 0 || userVO.getUser_id().equals("admin"))) {
			ComputerVO computerVO = new ComputerVO();
			computerVO.setComputer_id(computer_id);
			computerVO = login_service.selectComputerUsing(computerVO);
			if (computerVO.getComputer_using() == 0 && computerVO.getComputer_status() == 1) {
				if (login_service.updateUserUsing(userVO) && login_service.updateComputerUsing(computerVO)) {
					User_ComputerVO ucVO = new User_ComputerVO();
					ucVO.setUser_id(userVO.getUser_id());
					ucVO.setComputer_id(computer_id);
					String start = format.format(time.getTime());
					ucVO.setStart(start);
					ucVO.setEnd(start);
					if (login_service.insertLoginUser_Computer(ucVO)) {
						userVO.setSelect_computer(computer_id);
						session.setAttribute("login", userVO);
						session.setAttribute("ucVO", ucVO);
						session.setAttribute("computerVO", computerVO);
						System.out.println(userVO.getUser_id()+" 로그인 성공");
						return userVO;
					}
				}
			}
		}
		return null;
	}
}
