package com.example.demo.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/boards")
public class BoardController {
	@Autowired
	private BoardService service;
	
	//추가
	@PostMapping("")
	public Map add(BoardDTO b) {
		Map map = new HashMap();
		boolean flag = true;
		
		try {
			service.saveBoard(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		
		map.put("flag", flag);
		
		return map;
	}
	
	//전체검색
	@GetMapping("")
	public Map list() {
		Map map = new HashMap();
		ArrayList<BoardDTO> list = service.getAll();
		map.put("list", list);
		
		return map;
	}
	
	//글번호로 검색
	@GetMapping("/{num}")
	public Map getNum(@PathVariable("num") int num) {
		Map map = new HashMap();
		BoardDTO dto = service.getBoard(num);
		map.put("dto", dto);
		
		return map;
	}
	
	//제목으로 검색
	@GetMapping("/title/{title}")
	public Map getTitle(@PathVariable("title") String title) {
		Map map = new HashMap();
		ArrayList<BoardDTO> list = service.getByTitle(title);
		
		map.put("list", list);
		
		return map;
	}
	
	//작성자로 검색
	@GetMapping("/writer/{writer}")
	public Map getWriter(@PathVariable("writer") String writer) {
		Map map = new HashMap();
		ArrayList<BoardDTO> list = service.getByWriter(writer);
		
		map.put("list", list);
		
		return map;
	}
	
	//수정
	@PutMapping("")
	public Map edit(BoardDTO b){
		Map map = new HashMap();
		//원 데이터를 수정할 글번호로 검색. 전체 정보 받아옴
		BoardDTO old = service.getBoard(b.getNum());
		old.setTitle(b.getTitle());
		old.setContent(b.getContent());
		boolean flag = true;
		
		try {
			service.saveBoard(old);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		map.put("flag", flag);
		
		return map;
	}
	
	//삭제
	@DeleteMapping("/{num}")
	public Map delete(@PathVariable("num") int num) {
		Map map = new HashMap();
		boolean flag = true;
		
		try {
			service.delBoard(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		
		map.put("flag", flag);
		return map;
	}
	
}
