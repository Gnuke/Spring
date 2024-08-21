package com.example.demo.guestbook;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestBookService {
	@Autowired
	private GuestBookDAO dao;
	
	//번호로 검색
	public GuestBook getGuest(int num) {
		return dao.select(num);
	}
	
	//전체목록
	public ArrayList<GuestBook> selectAll(){
		return dao.selectAll();
	}
	
	//추가
	public void writeGuest(GuestBook gb) {
		dao.write(gb);
	}
	
	//수정
	public void editBook(GuestBook gb) {
		dao.update(gb);
	}
	
	//삭제
	public void delBook(int num) {
		dao.delete(num);
	}
	
}
