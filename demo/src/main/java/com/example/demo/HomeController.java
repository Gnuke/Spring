package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//stereotype : 클래스 종류
@Controller
public class HomeController {
	@GetMapping("/") // get 요청만 받음
	public String home() {	// 요청을 처리하는 메서드는 view page 경로를 반환
		return "index";
	}
	
	//회원가입폼
	@GetMapping("/member/join")
	public String joinform() {
		return "joinForm";
	}
	
	//회원가입 완료
	@PostMapping("/member/join") // post 요청만 받음
	public String join() {	// 요청을 처리하는 메서드는 view page 경로를 반환
		//service.addMember(m);
		return "join"; // 회원가입 완료 메시지 출력
	}
	
	@GetMapping("/member/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/member/login")
	public String loginResult() {
		return "loginResult";
	}
}
