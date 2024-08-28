package com.example.demo.guestbook;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookDAO dao;

	// 글작성, 글수정
	//save(): 방금 추가/수정 된 행을 entity에 담아서 반환
	public GuestbookDTO saveBook(GuestbookDTO dto) {
		Guestbook entity = dao
				.save(new Guestbook(dto.getNum(), dto.getWriter(), dto.getWdate(), dto.getPwd(), dto.getContent()));
		return new GuestbookDTO(entity.getNum(), entity.getWriter(), entity.getWdate(), entity.getPwd(),
				entity.getContent());
	}

	// 전체목록
	public ArrayList<GuestbookDTO> getAll() {
		ArrayList<Guestbook> l = (ArrayList<Guestbook>) dao.findAll();// 전체 검색
		ArrayList<GuestbookDTO> list = new ArrayList<>();
		for (Guestbook entity : l) {
			list.add(new GuestbookDTO(entity.getNum(), entity.getWriter(), entity.getWdate(), entity.getPwd(),
				entity.getContent()));
		}
		return list;
	}
	
	// num 검색
	public GuestbookDTO getBook(int num) {
		Guestbook entity = dao.findById(num).orElse(null);
		if(entity != null) {
			return new GuestbookDTO(entity.getNum(), entity.getWriter(), entity.getWdate(), entity.getPwd(),
					entity.getContent());
		}
		return null;
	}
	
	// 삭제
	public void delBook(int num) {
		dao.deleteById(num);
	}
	
	//작성자로 검색
	public ArrayList<GuestbookDTO> getByWriter(String writer){
		ArrayList<Guestbook> l = dao.findByWriter(writer);
		ArrayList<GuestbookDTO> list = new ArrayList<>();
		for (Guestbook entity : l) {
			list.add(new GuestbookDTO(entity.getNum(), entity.getWriter(), 
					entity.getWdate(), entity.getPwd(),
				entity.getContent()));
		}
		return list;
	}
}









