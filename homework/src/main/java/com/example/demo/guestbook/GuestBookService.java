package com.example.demo.guestbook;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestBookService {
	@Autowired
	private GuestBookDAO dao;
	
	public void writeGuest(GuestBook gb) {
		dao.write(gb);
	}
	
	public ArrayList<GuestBook> selectAll(){
		return dao.selectAll();
	}
}
