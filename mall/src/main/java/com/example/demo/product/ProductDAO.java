package com.example.demo.product;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
	//내상품 목록
	ArrayList<Product> findByWriter( Member writer );
}
