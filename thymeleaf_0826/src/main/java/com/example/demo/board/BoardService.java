package com.example.demo.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	@Autowired
	BoardDAO dao;
	
	public void addBoard(Board b) {
		dao.insert(b);
	}
	
	public Board selectOne(int num) {
		return dao.selectOne(num);
	}

	public List<Board> selectAll(){
		return dao.selectAll();
	}

	public ArrayList<Board> selectByTitle(String title){
		return dao.selectByTitle( "%" + title +"%");
		//return dao.selectByTitle( title );
	}
	
	public ArrayList<Board> selectByWriter(String writer){
		return dao.selectByWriter( "%" + writer +"%");
		//return dao.selectByWriter( writer );
	}
	
	public void update(Board b) {
		dao.update(b);
	}
	
	public void delete(int num) {
		dao.delete(num);
	}
}