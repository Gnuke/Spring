package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.test.MemService;
import com.example.demo.test.Member;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/test")
public class TestContorller {
	@Autowired
	MemService service;
	
//	@GetMapping("/login")
//	public void loginForm() {}
	
//	@PostMapping("/login")
//	public String login(String id, String pwd, HttpSession session) {
//		// session info
//		System.out.println("session id : " + session.getId());
//		System.out.println("CreationTime : " + session.getCreationTime());
//		System.out.println("lastAccessedTime : " + session.getLastAccessedTime());
//		System.out.println("MaxInact : " + session.getMaxInactiveInterval() );
//		
//		// login 처리 / id : aaa, pwd : 111
//		String msg = "로그인 실패";
//		
//		if( id.equals("aaa") && pwd.equals("111") ) {
//			session.setAttribute("loginId", "aaa");
//			session.setAttribute("type", "구매자");
//			msg = "로그인 성공";
//		}
//		
//		session.setAttribute("msg", msg);
//		
//		return "index";
//	}
	
	//AjaxLogin-----------------------------------------------------
	@GetMapping("/loginAjax")
	public void loginForm() {}
	
	@PostMapping("/loginAjax")
	public String login(Member m, HttpSession session, Model model) {
		Member m2 = service.selectMem(m.getId());
		if(m2 != null && m2.getPwd().equals(m.getPwd())) {
			session.setAttribute("loginId", m2.getId());
			session.setAttribute("type", m2.getType());
		}else {
			model.addAttribute("msg", "로그인 실패");
		}
		return "index";
	}
	
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "index";
//	}
	
	//AjaxLogout---------------------------------------------------
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
}
