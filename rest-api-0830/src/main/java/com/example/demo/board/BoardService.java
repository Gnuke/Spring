package com.example.demo.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;

@Service
public class BoardService {
	@Autowired
	private BoardDAO dao;
	
	//추가, 수정
	public BoardDTO saveBoard(BoardDTO b) {
		Board entity = dao.save(new Board(b.getNum(), b.getWriter(), 
				b.getWdate(), b.getTitle(), b.getContent()));
		
		return new BoardDTO(entity.getNum(), entity.getWriter(), 
				entity.getWdate(), entity.getTitle(), entity.getContent());
	}
	
	public BoardDTO getBoard(int num) {
		Board b = dao.findById(num).orElse(null);
		if(b != null) {
			return new BoardDTO(b.getNum(), b.getWriter(), b.getWdate(), 
					b.getTitle(), b.getContent());
		}
		return null;
	}
	
	public ArrayList<BoardDTO> getAll(){
		List<Board> l = dao.findAll();
		ArrayList<BoardDTO> list = new ArrayList<>();
		for(Board b:l) {
			list.add(new BoardDTO(b.getNum(), b.getWriter(), b.getWdate(), 
					b.getTitle(), b.getContent()));
		}
		return list;
	}
	
	public ArrayList<BoardDTO> getByTitle(String title){
		List<Board> l = dao.findByTitleLike("%"+title+"%");
		ArrayList<BoardDTO> list = new ArrayList<>();
		for(Board b:l) {
			list.add(new BoardDTO(b.getNum(), b.getWriter(), b.getWdate(), 
					b.getTitle(), b.getContent()));
		}
		return list;
	}
	
	public ArrayList<BoardDTO> getByWriter(String writer){
		List<Board> l = dao.findByWriter(new Member(writer, "","","",""));
		ArrayList<BoardDTO> list = new ArrayList<>();
		for(Board b:l) {
			list.add(new BoardDTO(b.getNum(), b.getWriter(), b.getWdate(), 
					b.getTitle(), b.getContent()));
		}
		return list;
	}
	
	public void delBoard(int num) {
		dao.deleteById(num);
	}
}
