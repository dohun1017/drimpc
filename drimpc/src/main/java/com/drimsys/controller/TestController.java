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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drimsys.dto.TestVO;

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

	@RequestMapping(value = "1234", method = RequestMethod.GET)
	public String A1234(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/1234";
	}

	@RequestMapping(value = "testProcess", method = RequestMethod.GET, produces = "application/xml;charset=utf-8")
	@ResponseBody
	public String testProcess(@RequestParam(value = "MEMO") String str) throws Exception {
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

	@RequestMapping(value = "byteProcess", method = RequestMethod.GET)
	@ResponseBody
	public TestVO byteProcess() throws Exception {

		TestVO temp = new TestVO();

		byte[] a = { (byte) 0x16, (byte) 0x4f, (byte) 0x4b };
		List<Byte> tempBytes = new ArrayList<Byte>();
		tempBytes.add(a[0]);
		tempBytes.add(a[1]);
		tempBytes.add(a[2]);
		
		temp.byteList = tempBytes;

		List<Integer> tempInteger = new ArrayList<Integer>();
		tempInteger.add(1);
		tempInteger.add(2);
		
		
		temp.dataLengths = tempInteger;
		temp.totalLength = temp.byteList.size();

		List<String> tempDes = new ArrayList<String>();
		String a1 = "가나다라";
		String b1 = "마바사아";
		tempDes.add(a1);
		tempDes.add(b1);
		temp.description = tempDes;
		
		List<String> type = new ArrayList<String>();
		String t1 = "hex";
		String t2 = "text";
		type.add(t1);
		type.add(t2);
		
		temp.type = type;

		return temp;
	}

}
