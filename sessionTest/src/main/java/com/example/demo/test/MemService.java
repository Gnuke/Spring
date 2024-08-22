package com.example.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class MemService {
	@Autowired
	private MemDAO dao;
	
	public void addMem(Member m) {
		dao.insert(m);
	}
	
	public Member selectMem(String id) {
		return dao.select(id);
	}
	
//	public void selectMem(HttpSession session) {
//		dao.selectMy(session.getAttribute("loginId"));
//	}

	public void editMem(Member m) {
		dao.update(m);
	}

	public void delMem(String id) {
		dao.delete(id);
	}
}
