package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.member.MemDAO;
import com.example.demo.member.Member;

@Service
public class MemDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private MemDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Member m = dao.findById(username)
				.orElseThrow(
						() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 : " + username)
				);
		System.out.println( "MemdetailsService : " + m);
		return new MemDetailsImpl(m);
	}
}
