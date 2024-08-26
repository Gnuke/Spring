package com.example.demo.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemController {
	@Autowired
	MemService service;

	//회원가입--------------------------------------------------------------------
//	@GetMapping("/join")
//	public String joinForm(Model m) {
//		ArrayList<String> list = new ArrayList<String>();
//		list.add("구매자");
//		list.add("판매자");
//		
//		m.addAttribute( "list", list );
//		
//		return "member/join";
//	}
	
	@GetMapping("/join")
	public String joinForm(Model m) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("구매자");
		list.add("판매자");
		m.addAttribute( "list", list );
		
		return "member/joinAjax";
	}
	
	@PostMapping("/join")
	public String join(Member m) {
		service.addMem(m);
		return "index";
	}
	
		//중복체크------------------------------------------------
		@GetMapping("/idcheck")
		public String idcheck() {
			return "member/idcheck";
		}
	
		@PostMapping("/idcheck")
		public String idcheck(String id, Model model) {
			Member m = service.selectMem(id);
			String msg = "중복된 아이디";
			String resultid = "";
			boolean flag = false;
			
			if(m==null) {
				msg = "사용가능한 아이디";
				resultid = id;
				flag = true;
			}
			
			model.addAttribute("resultid", resultid);
			model.addAttribute("msg", msg);
			model.addAttribute("flag", flag);
			
			System.out.println( "Cont의 flag : " + flag );
			
			return "member/idcheck";
		}
		
		//Ajax중복체크-----------------------------------------------
		@ResponseBody
		@GetMapping("/idcheckAjax")
		public Map idcheckAjax(String id) {
			Map map = new HashMap();
			boolean flag = false;
			if(service.selectMem(id) == null) {
				flag = true;
			}
			map.put("flag", flag);
			return map;
		}
	
	//myinfo----------------------------------------------------------------------------
	@GetMapping("/myinfo")
	public String myInfo(HttpSession session, String id, Model m) {
		id = session.getAttribute("loginId").toString();
		Member myinfo =  service.selectMem(id);
		
		m.addAttribute("myinfo", myinfo);
		
		//System.out.println("나의 정보 : " + m);
		
		return "member/myinfo";
	}
	
	//정보수정----------------------------------------------------------------------------
	@GetMapping("/edit")
	public String editForm(String id, HttpSession session, Model m) {
		id = session.getAttribute("loginId").toString();
		Member myinfo =  service.selectMem(id);
		
		m.addAttribute("myinfo", myinfo);
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("구매자");
		list.add("판매자");
		m.addAttribute( "list", list );
		
		return "member/edit";
	}
	
	@PostMapping("/edit")
	public String edit(Member m, HttpSession session) {
		service.editMem(m);
		System.out.println( "바뀐 정보는 : " + m );
		session.setAttribute( "type", m.getType() );
		return "redirect:/";
	}
	
	//탈퇴-----------------------------------------------------------------------
	@GetMapping("/out")
	public String out(HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		service.delMem(loginId);
		return "redirect:/test/logout";
	}
}
