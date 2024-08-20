package com.example.demo.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guest")
public class GuestBookController {
	
	@Autowired
	GuestBookService service;
	
	//write----------------------------------
	@GetMapping("/write")
	public String writeForm() {
		return "/guestbook/write";
	}
	
	@PostMapping("/write")
	public String write(GuestBook gb) {
		service.writeGuest(gb);
		System.out.println( "작성완료" );
		return "index";
	}
	
	//selectAll---------------------------------------
	@RequestMapping("/list")
	public String list(Model m) {
		m.addAttribute( "list", service.selectAll() );
		return "/guestbook/list";
	}

}
