package com.drimsys.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.drimsys.dto.*;
import com.drimsys.service.inf.*;

@Controller
public class LoginController {
	@Inject
	LoginService login_service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(Locale locale, Model model) throws Exception {
		return "/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) throws Exception {
		return "/login";
	}

	// 로그인 처리하는 부분
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public String loginProcess(HttpServletRequest request, HttpSession session, Model model, UserVO userVO)
			throws Exception {

		String returnURL = "/login";
		Calendar time = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		if (session.getAttribute("login") != null) {
			// 기존에 login이란 세션 값이 존재한다면
			session.removeAttribute("login"); // 기존값을 제거해 준다.
		}
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
//			int computer_id = request.getParameter("computer_id");
		int computer_id = 1;
		System.out.println(user_id);
		System.out.println(user_pw);
		userVO.setUser_id(user_id);
		userVO.setUser_pw(user_pw);

		userVO = login_service.selectUserUsing(userVO);
		if (userVO != null && userVO.getUser_using() == 0) {
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
						session.setAttribute("login", userVO);
						if (userVO.getUser_id().equals("admin"))
							returnURL = "redirect:/admin_product";
						else
							returnURL = "redirect:/user_main";
					}
				}
			}
		}
		return returnURL; // 위에서 설정한 returnURL 을 반환해서 이동시킴
	}
}
