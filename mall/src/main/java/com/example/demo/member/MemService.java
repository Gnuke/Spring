package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemService {
	@Autowired
	private MemDAO dao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	// 멤버 추가 & 정보 수정
	public void saveMem(MemDTO dto) {
		dao.save( new Member(dto.getId(), encoder.encode(dto.getPwd()),
				dto.getName(), dto.getEmail(), dto.getType()));
	}
	
	// 회원 탈퇴
	public void deleteMy(String id) {
		dao.deleteById(id);
	}
	
	// 내 정보 검색 (판매자)
	public MemDTO myInfo(String id) {
		Member entity = dao.findById(id).orElse(null);
		//System.out.println( "entity : " + entity.getId());
		if(entity != null) {
			return new MemDTO( entity.getId(), entity.getPwd(), 
					entity.getName(), entity.getEmail(), entity.getType());
		}
		return null;
	}
	
}
