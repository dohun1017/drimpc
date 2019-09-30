package com.drimsys.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/test";
	}
	@RequestMapping(value = "test2", method = RequestMethod.POST)
	public String test2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/test2";
	}
	@RequestMapping(value = "testProcess", method = RequestMethod.GET, produces="application/xml;charset=utf-8")
	@ResponseBody
	public String testProcess(@RequestParam(value = "MEMO")String str) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<item>");
		buffer.append("<status>");
		buffer.append("    <![CDATA[ OK ]]>");
		buffer.append("</status>");
		buffer.append("  <id>");
		buffer.append("    <![CDATA[ HOON ]]>");
		buffer.append("  </id>");
		buffer.append("  <text>");
		buffer.append("    <![CDATA[ GOOD ]]>");
		buffer.append("  </text>");
		buffer.append("</item>");
		
		System.out.println(str);
		return buffer.toString();
	}	

}
