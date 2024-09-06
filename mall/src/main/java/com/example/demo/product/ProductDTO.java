package com.example.demo.product;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.member.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
	private int num;
	private Member writer;
	private String pName;
	private String pText; // 상품 설명
	private int price; // 가격
	private int quantity; // 수량
	private String img;
	private MultipartFile f;
}
