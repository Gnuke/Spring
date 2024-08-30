package com.example.demo.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;

@Service
public class ProductService {
	@Autowired
	ProductDAO dao;
	
	// insert & update-----------------------------------------------------------------------
	public ProductDTO saveProduct(ProductDTO p) {
		Product entity = dao.save(new Product(p.getNum(), p.getName(), p.getPrice(),
				p.getAmount(), p.getSeller(), p.getImg()));
		
		return new ProductDTO(entity.getNum(), entity.getName(), entity.getPrice(),
				entity.getAmount(), entity.getSeller(), entity.getImg(), null);
	}
	
	// select--------------------------------------------------------------------------------
		//번호로 검색
		public ProductDTO getProduct(int num) {
			Product entity = dao.findById(num).orElse(null);
			if( entity != null ) {
				return new ProductDTO(entity.getNum(), entity.getName(), entity.getPrice(),
						entity.getAmount(), entity.getSeller(), entity.getImg(), null);
			}
			return null;
		}
		
		//전체 검색
		public ArrayList<ProductDTO> getAll(){
			List<Product> l = dao.findAll();
			ArrayList<ProductDTO> list = new ArrayList<>();
			for(Product entity : l) {
				list.add(new ProductDTO(entity.getNum(), entity.getName(), entity.getPrice(),
						entity.getAmount(), entity.getSeller(), entity.getImg(), null));
			}
			return list;
		}
	
		//판매자로 검색
		public ArrayList<ProductDTO> getBySeller(String seller){
			List<Product> l = dao.findBySeller(new Member(seller, "", "", "", ""));
			ArrayList<ProductDTO> list = new ArrayList<>();
			for(Product entity : l) {
				list.add(new ProductDTO(entity.getNum(), entity.getName(), entity.getPrice(),
						entity.getAmount(), entity.getSeller(), entity.getImg(), null));
			}
			return list;
		}
		
		//가격으로 검색
		public ArrayList<ProductDTO> getByPrice(int p1, int p2){
			List<Product> l = dao.findByPriceBetween(p1, p2);
			ArrayList<ProductDTO> list = new ArrayList<>();
			for(Product entity : l) {
				list.add(new ProductDTO(entity.getNum(), entity.getName(), entity.getPrice(),
						entity.getAmount(), entity.getSeller(), entity.getImg(), null));
			}
			return list;
		}
		
		//상품명으로 검색
		public ArrayList<ProductDTO> getByName(String name){
			List<Product> l = dao.findByNameLike("%"+name+"%");
			ArrayList<ProductDTO> list = new ArrayList<>();
			for(Product entity : l) {
				list.add(new ProductDTO(entity.getNum(), entity.getName(), entity.getPrice(),
						entity.getAmount(), entity.getSeller(), entity.getImg(), null));
			}
			return list;
		}
	
	// delete----------------------------------------------------------------------------
	public void delProduct(int num) {
		dao.deleteById(num);
	}
}
