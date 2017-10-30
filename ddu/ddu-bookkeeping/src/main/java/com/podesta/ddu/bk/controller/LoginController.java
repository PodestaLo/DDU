package com.podesta.ddu.bk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.podesta.ddu.bk.model.User;
import com.podesta.ddu.bk.service.IUserService;

@Controller
public class LoginController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value ="login")
	@ResponseBody
	public String login(HttpSession session, HttpServletRequest request,
			HttpServletRequest response, @ModelAttribute("user") User user) {
		User user1 = userService.getByID(1);
//		session.setAttribute("user", user1);
//		System.out.println(user1.getUsername() + "===" + user1.getPassword());
		
		session.setAttribute("user", user);
		System.out.println(user.getUsername() + "===" + user.getPassword());

		return "success";
	}
	
	@RequestMapping(value ="demo")
	public String demo(HttpServletRequest request,
			HttpServletRequest response) {
		
//		List<Wjxw> list=BjzcwjxwService.getAll(new QueryFilter(request,"BjzcwjxwItem"));
		return "success";
	}
	
	@RequestMapping(value ="query")
	@ResponseBody
	public String query(HttpSession session, HttpServletRequest request,
			HttpServletRequest response, @ModelAttribute("user") User user) {
		User user1 = userService.getByID(1);
//		session.setAttribute("user", user1);
//		System.out.println(user1.getUsername() + "===" + user1.getPassword());
		
		session.setAttribute("user", user);
		System.out.println(user.getUsername() + "===" + user.getPassword());

		return "success";
	}
}
