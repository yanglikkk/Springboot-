package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.util.JwtUtil;

@Controller
public class LoginController {
	List<String> list = new ArrayList<String>();
	
	@RequestMapping(value="/preLogin",method=RequestMethod.GET)
	public String preLogin(String url,HttpServletRequest request,Model model) {
		System.out.println(url);
		HttpSession session = request.getSession(false);
		System.out.println(session == null);
		if(session == null) {
			model.addAttribute("url",url);
			return "login";
		}else {
			String token = (String) session.getAttribute("token");
			return "redirect:http://"+url+"?token="+token;
		}
		
	}
	@RequestMapping("/login")
	public String login(String username,String password,String url,HttpServletRequest request) {
		if("123456".equals(password) && username.equals("yangli")) {
			String token = null;
			try {
				token = JwtUtil.createJwt();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("token", token);
			list.add(token);
			return "redirect:http://"+url+"?token="+token;
		}else {
			return "login";
		}
	}
	@RequestMapping("/checkToken")
	@ResponseBody
	public String checkToken(String token) {
		if(list.contains(token) && JwtUtil.verifyJwt(token)) {
			return "correct";
		}else {
			return "incorrect";
		}
	}
	
}
