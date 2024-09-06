package com.example.demo.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;

@Service
public class ProductService {
	@Autowired
	private ProductDAO dao;
	
	// 상품 등록 & 수정
	public void registP(ProductDTO dto) {
		dao.save( new Product( dto.getNum(), dto.getWriter(), dto.getPName(),
				dto.getPText(), dto.getPrice(), dto.getQuantity(), dto.getImg()));
	}
	
	// pk로 검색
	public ProductDTO getP(int num) {
		Product entity = dao.findById(num).orElse(null);
		if( entity != null ) {
			return new ProductDTO( entity.getNum(), entity.getWriter(), entity.getPName(),
					entity.getPText(), entity.getPrice(), entity.getQuantity(), entity.getImg(), null );
		}
		return null;
	}
	
	// 전체 검색 -> 구매에서 실행
//	public ArrayList<SellProductDTO> getAll() {
//		List<SellProduct> l = dao.findAll();
//		ArrayList<SellProductDTO> list = new ArrayList<>();
//		for( SellProduct sp : l ) {
//			list.add( new SellProductDTO( sp.getNum(), sp.getWriter(), sp.getPName(),
//					sp.getPText(), sp.getPrice(), sp.getQuantity(), sp.getImg(), null));
//		}
//		return list;
//	}
	
	// 내 상품 목록
	public ArrayList<ProductDTO> getMySell(String writer){
		//System.out.println("Swriter : " + writer);
		List<Product> l = dao.findByWriter( new Member(writer,"","","",""));
		ArrayList<ProductDTO> list = new ArrayList<>();
		for(Product sp : l ) {
			list.add(new ProductDTO( sp.getNum(), sp.getWriter(), sp.getPName(),
					sp.getPText(), sp.getPrice(), sp.getQuantity(), sp.getImg(), null));
		}
		//System.out.println( "나는 : " + list );
		return list;
	}
	
	// 삭제
	public void delete(int num) {
		dao.deleteById(num);
	}
}
