package com.drimsys.scheduled;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.drimsys.dto.UserVO;
import com.drimsys.service.inf.LoginService;

@Service
public class TimeProcess {

	@Inject
	LoginService login_service;
	private static UserVO userVO;

	public static void setUserVO(UserVO user) {
		userVO = user;
	}

	public static UserVO getUserVO() {
		return userVO;
	}
	
	@Scheduled(cron = "0/6 * * * * ? ")
	public UserVO time() throws Exception {
		if (userVO == null || userVO.getUser_id().equals("admin")) {
			return null;
		}
		int user_time = userVO.getUser_time();
		user_time -= 1;
		userVO.setUser_time(user_time);
		login_service.updateUser_time(userVO);
	
		return userVO;
	}
	
}
