package com.example.demo.product;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{
	//판매자로 검색
	ArrayList<Product> findBySeller(Member seller);
	
	//가격대로 검색
	ArrayList<Product> findByPriceBetween(int p1, int p2);
	
	//상품명으로 검색
	ArrayList<Product> findByNameLike(String name);
}
