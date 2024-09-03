package com.example.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {
	@Autowired
	private UsersService service;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	// join-----------------------------------------------------------
	@GetMapping("/join")
	public String joinForm() {
		return "member/join";
	}
	
	@PostMapping("/join")
	public String join(UsersDTO dto) {
		service.save(dto);
		return "redirect:/";
	}
	
	// login----------------------------------------------------------
	@GetMapping("/loginform")
	public String loginForm() {
		return "/member/login";
	}
	
	// error page----------------------------------------------------
	@RequestMapping("/autherror")
	public String error() {
		return "error";
	}
	
	// 인증 test-----------------------------------------------------------
	@RequestMapping("/auth/test")
	public String test() {
		System.out.println( "인증 테스트" );
		return "index_auth";
	}
}
