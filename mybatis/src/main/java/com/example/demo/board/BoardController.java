package com.example.demo.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@RequestMapping("/list")
	public String list(Model m) {
		m.addAttribute("list", service.selectAll());
		return "/board/list";
	}
	
	@GetMapping("/insert")
	public void insertForm() {}
	
	@PostMapping("/insert")
	public String insert(Board b) {
		service.addBoard(b);
		return "redirect:/";
	}
	
	@ResponseBody
	@GetMapping( "/getAjax" )
	public Map getAjax(int num) {
		Board b = service.selectOne(num);
		
		Map map = new HashMap();
		map.put("num", b.getNum());
		map.put("writer", b.getWriter());
		map.put("wdate", b.getWdate()+"");
		map.put("title", b.getTitle());
		map.put("content", b.getContent());
		
		return map;
	}
	
	@GetMapping("/detail")
	public String detail(int num, Model m) {
		m.addAttribute("b", service.selectOne(num));
		
		return "/board/detail";
	}
	
	@GetMapping("/update")
	public String update(Board b) {
		//System.out.println( "post update에서 : " + b.getNum());
		service.update(b);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/del")
	public String del(int num) {
		service.delete(num);
		return "redirect:/board/list";
	}
	
}
