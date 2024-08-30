package com.example.demo.product;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService service;
	
	@Value("${spring.servlet.multipart.location}")
	private String path; // path = C:\shopimg
	
	@GetMapping("/add")
	public void addForm() {}
	
	@PostMapping("/add")
	public String add(ProductDTO dto) {
		//num, name, price, amount
		ProductDTO newData = service.saveProduct(dto);
		String fname = dto.getF().getOriginalFilename();
		fname = newData.getNum() + fname;
		
		File newf = new File( path + "\\" + fname);
		try {
			dto.getF().transferTo(newf);
			newData.setImg(fname);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.saveProduct(newData);
		System.out.println( "fname : " + fname );
		//System.out.println( "Img : " + dto.getImg() );
		return "redirect:/product/list";
	}
	
	@GetMapping("/read-img")
	public ResponseEntity<byte[]> read_img(String fname){
		ResponseEntity<byte[]> result = null;
		File f = new File(path + "\\" + fname);
		
		// 파일 없을 때
		if (fname == null || fname.isEmpty() || !f.exists()) {
			// 클래스패스에서 기본 이미지 파일을 읽어옵니다.
		    try (InputStream in = getClass().getResourceAsStream("/templates/images/default.png")) {
		    	if (in != null) {
		    		// InputStream을 byte[]로 변환하여 응답에 담습니다.
		            byte[] imageBytes = FileCopyUtils.copyToByteArray(in);
		            HttpHeaders headers = new HttpHeaders();
		            headers.add("Content-Type", "image/png");
		            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
		        } else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		    } catch (IOException e) {
		    	e.printStackTrace();
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		}
		
		// 파일 존재할 떄
		// 응답 헤더 정보 저장 객체
		HttpHeaders header = new HttpHeaders();
		try {
			// 전송하는 데이터의 마임 타입 설정
			header.add("Content-Type", Files.probeContentType(f.toPath()));
			result = new ResponseEntity<byte[]>(
								FileCopyUtils.copyToByteArray(f), header, HttpStatus.OK
						);
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/list")
	public void list(Model m, HttpSession session) {
		ArrayList<ProductDTO> list = null;
		String type = (String)session.getAttribute("type");
		String loginId = (String)session.getAttribute("loginId");
		if( type.equals("판매자") ) {
			list = service.getBySeller(loginId);
		}else {
			list = service.getAll();
		}
		m.addAttribute("list", list); // 전체 상품 목록
	}
}
