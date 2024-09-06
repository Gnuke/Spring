package com.example.demo.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.TokenProvider;

@RestController
@CrossOrigin(origins = "*")
public class MemController {
	@Autowired
	private MemService service;
	
	@Autowired
	private TokenProvider provider;
	
	@Autowired
	private AuthenticationManagerBuilder abuilder;
	
	@PostMapping("/member/join")
	public Map join(@RequestBody MemDTO dto) {
		boolean flag = true;
		Map map = new HashMap();
		try {
			service.saveMem(dto);
			map.put("dto", dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			flag = false;
			e.printStackTrace();
		}
		map.put("flag", flag);
		
		return map;
	}
	
	@PostMapping("/login")
	public Map login(String id, String pwd) {
		UsernamePasswordAuthenticationToken authtoken = 
				new UsernamePasswordAuthenticationToken(id, pwd);
		Authentication auth = 
				abuilder.getObject().authenticate(authtoken);
		boolean flag = auth.isAuthenticated(); //인증 결과
		System.out.println( "인증 결과 : " + flag );
		Map map = new HashMap();
		if(flag) {// 정상 인증
			String token = provider.getToken(service.myInfo(id));
			map.put("token", token);
			String userId = auth.getName();
			//System.out.println( "//"+userId );
			MemDTO dto = service.myInfo(userId);
			map.put("dto", dto);
		}
		map.put("flag", flag);
		return map;
	}
	
	@GetMapping("/auth/meminfo")
	public Map myinfo() {
		Map map = new HashMap();
		Authentication auth =
				SecurityContextHolder.getContext().getAuthentication();
		String id = auth.getName();
		MemDTO dto = service.myInfo(id);
		map.put("dto", dto);
		return map;
	}
	
	//탈퇴
	@DeleteMapping("/member/{id}")
	public Map delete(@PathVariable("id") String id) {
		Map map = new HashMap();
		boolean flag = true;
		
		try {
			service.deleteMy(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			flag = false;
		}
		
		map.put("flag", flag);
		return map;
	}
}
