package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.guestbook.GuestbookController;
import com.example.demo.guestbook.GuestbookDTO;
import com.example.demo.guestbook.GuestbookService;

@WebMvcTest(GuestbookController.class)
public class GuestbookControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GuestbookService service;
	
	//MockMvc : mvc 웹의 Mock 테스트를 할 수 있는 API 제공해 주는 클래스
	// perform() : Mock 수행 메서드. get(), post()...
	// andDo() : 테스트 시 수행할 내용 작성. print() : 출력
	// andExpect() : 처리 결과 상태 지정
	@Test
	void addFormTest() {
		try {
			mockMvc.perform(get("/guestbook/add"))
				.andDo(print())
				.andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void addTest() {
		String params = "?writer=aaa&pwd=1234&content=ConTest";
		try {
			mockMvc.perform(post("/guestbook/add"+params))
				.andDo(print())
				.andExpect(status().is3xxRedirection()); // redirect:
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void listTest() {
		ArrayList<GuestbookDTO> list = new ArrayList<>();
		list.add(new GuestbookDTO(1, "aaa", new Date(), "1234", "내용1") );
		list.add(new GuestbookDTO(2, "bbb", new Date(), "1234", "내용2") );
		list.add(new GuestbookDTO(3, "ccc", new Date(), "1234", "내용3") );
		list.add(new GuestbookDTO(4, "ddd", new Date(), "1234", "내용4") );
		
		//when(): 테스팅 시나리오에서 특정 이벤트가 발생하면
		//thenReturn() : 이벤트 발생 시 지정한 값 리턴
		when(service.getAll()).thenReturn(list);
		try {
			mockMvc.perform(get("/guestbook/list"))
				.andDo(print())
				.andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
