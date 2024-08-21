package com.example.demo.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestBookController {
	
	@Autowired
	GuestBookService service;
	
	//검색---------------------------------------
	@RequestMapping("/")
	public String home(Model m) {
		//System.out.println("call list");
		m.addAttribute( "list", service.selectAll() );
		return "index";
	}
		
//	@GetMapping("/guest/content")
//	public String content( int num, Model m ) {
//		GuestBook gb = service.getGuest(num);
//		if(gb == null) {
//			m.addAttribute("msg", "없는 글");
//		}else {
//			m.addAttribute("gb", gb);
//		}
//		return "guestbook/content";
//	}
	
	@GetMapping("/guestbook/content")
	public void content( int num, Model m ) {
		m.addAttribute( "gb", service.getGuest(num) );
	}
	
	//write----------------------------------
	//작성 폼
	@GetMapping("/guest/write")
	public String writeForm() {
		return "/guestbook/write";
	}
	
	//작성 완료
	@PostMapping("/guest/write")
	public String write(GuestBook gb) {
		service.writeGuest(gb);
		//System.out.println( "작성완료" );
		return "redirect:/"; // redirect로 이동. 사용자가 새 요청
	}
	
	//수정---------------------------------------
	@GetMapping("/guestbook/edit")
	public String edit(GuestBook gb) {
		service.editBook(gb);
		//System.out.println("수정완료");
		return "redirect:/";
	}
	
	//삭제-----------------------------------------
	@GetMapping("/guestbook/del")
	public String del(int num) {
		service.delBook(num);
		//System.out.println("삭제완료");
		return "redirect:/";
	}
	
}
