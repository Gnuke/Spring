package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.users.Users;
import com.example.demo.users.UsersDAO;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UsersDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users u = dao.findById(username)
		.orElseThrow(
		()->new UsernameNotFoundException("not found username"+username));
		System.out.println("userdetails service:"+u);
		return new UserDetailsImpl(u);
	}

}
