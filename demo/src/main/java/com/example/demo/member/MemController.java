package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mem") // 이 컨트롤러의 공통 URL. 등록하는 모든 url 앞에 자동으로 /mem이 붙음
public class MemController {
	@Autowired
	private MemService service;
	
	@GetMapping("/join")
	public String join() {
		service.addMember();
		return "member/join";
	}
	
	// 검색
	@GetMapping("/get")
	public String search() {
		service.getMember();
		return "member/info";
	}
	
	// 전체검색 url:getall. view:getall.jsp
	@GetMapping("/getall")
	public String searchAll() {
		service.getAll();
		return "member/getall";
	}
	
	// 수정 url: edit. view:edit.jsp
	@GetMapping("/edit")
	public String update() {
		service.editMember();
		return "member/edit";
	}
	
	// 삭제 url:del. view:del.jsp
	@GetMapping("/del")
	public String del() {
		service.delMember();
		return "member/del";
	}
}
