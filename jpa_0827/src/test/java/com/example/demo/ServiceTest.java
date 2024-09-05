package com.example.demo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.guestbook.GuestbookDTO;
import com.example.demo.guestbook.GuestbookService;

@SpringBootTest
public class ServiceTest {
	@Autowired
	private GuestbookService service;
	
	@Test
	void insertTest() {
//		service.saveBook(new GuestbookDTO(0, "bbb", null, "111", "새글내용"));
	}
	
	@Test
	void listTest() {
		ArrayList<GuestbookDTO> list = service.getAll();
		System.out.println(list);
	}
	
	@Test
	void editTest() {
		GuestbookDTO dto = service.getBook(2);
		if(dto != null) {
		dto.setWriter("ㅠㅠㅠ");
		dto.setContent("qwerty");
		service.saveBook(dto);
		}else {
			System.out.println("없는 글");
		}
	}
}
