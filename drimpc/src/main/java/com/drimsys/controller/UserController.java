package com.drimsys.controller;

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

import com.drimsys.dto.*;
import com.drimsys.service.inf.ProductService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Locale locale, Model model) throws Exception {

		return "user_main";
	}

	@RequestMapping(value = "/user_add", method = RequestMethod.GET)
	public String user_add(Locale locale, Model model) throws Exception {

		return "user_add";
	}

}