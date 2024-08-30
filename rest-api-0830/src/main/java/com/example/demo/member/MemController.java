package com.example.demo.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController // rest 요청을 받을 수 있는 Controller
@CrossOrigin(origins = "*") // 요청받을 ip 주소
@RequestMapping("/members")
public class MemController {
	@Autowired
	private MemService service;
	
	//추가
	@PostMapping("")
	public Map add(MemDTO m) {
		Map map = new HashMap();
		MemDTO m2 = service.saveMem(m);
		map.put("dto", m2);
		
		return map;
	}
	
	//pk로 검색
	@GetMapping("/{id}") // url ex) -> /members/aaa 
	// => path variable : ("/{변수명}")
	public Map get(@PathVariable("id") String id) {
		Map map = new HashMap();
		MemDTO dto = service.getMem(id);
		map.put("dto", dto);
		
		return map;
	}
	
	//수정
	@PutMapping("")
	public Map edit(MemDTO m) {
		Map map = new HashMap();
		MemDTO old = service.getMem(m.getId());
		
		//pwd만 수정
		old.setPwd(m.getPwd());
		
		MemDTO m2 = service.saveMem(old);
		
		map.put("dto", m2);
		
		return map;
	}
	
	//삭제
	@DeleteMapping("/{id}")
	public Map delete(@PathVariable("id") String id) {
		Map map = new HashMap();
		boolean flag = true;
		
		try {
			service.delMem(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			flag = false;
		}
		
		map.put("flag", flag);
		return map;
	}
	
	//로그인
	@GetMapping("/login")
	public Map login(String id, String pwd) {
		Map map = new HashMap();
		boolean flag = false;
		MemDTO m = service.getMem(id);
		if( m != null && m.getPwd().equals(pwd) ) {
			flag = true;
			map.put("loginId", m.getId());
			map.put("type", m.getType());
		}
		map.put("flag", flag);
		return map;
	}
}
