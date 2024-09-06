package com.example.demo.product;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.member.Member;


@RestController
@CrossOrigin(origins="*")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@Value("${spring.servlet.multipart.location}")
	private String path;
	
	@PostMapping("/product/add")
	public Map add(ProductDTO dto) {
		String fname = dto.getF().getOriginalFilename();
		File newf = new File(path + fname);
		boolean flag = false;
		try {
			dto.getF().transferTo(newf);
			dto.setImg(fname);
			Authentication a = 
					SecurityContextHolder.getContext().getAuthentication();
			Member m = new Member( a.getName(), "","","","");
			dto.setWriter(m);
			service.registP(dto);
			flag = true;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		Map map = new HashMap();
		map.put("flag", flag);
		return map;
	}
	
	@PutMapping("/product/detail/{bnum}")
	public Map edit(@PathVariable("bnum") ProductDTO p) {
		Map map = new HashMap();
		
		ProductDTO old = service.getP(p.getNum());
		old.setPName(p.getPName());
		old.setPText(p.getPText());
		old.setPrice(p.getPrice());
		old.setQuantity(p.getQuantity());
		
		String fname = p.getF().getOriginalFilename();
		File newf = new File(path+fname);
		
		boolean flag = false;
		try {
			p.getF().transferTo(newf);
			old.setImg(fname);
			
			service.registP(old);
			flag = true;
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		map.put("flag", flag);
		return map;
	}
	
	
	@GetMapping("/product/{id}")
	public Map myList(@PathVariable("id") String writer) {
		Map map = new HashMap();
		ArrayList<ProductDTO> list = service.getMySell(writer);
		map.put("list", service.getMySell(writer));
		//System.out.println( "map : " + map );
		return map;
	}
	
	@GetMapping("/product/detail/{bnum}")
	public Map detail(@PathVariable("bnum") int num ) {
		Map map = new HashMap();
		ProductDTO dto = service.getP(num);
		map.put("dto", dto);
		System.out.println( "map : " + map );
		return map;
	}
	
	@DeleteMapping("/product/detail/{bnum}")
	public Map delete(@PathVariable("bnum") int num) {
		Map map = new HashMap();
		boolean flag = true;
		
		try {
			service.delete(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		
		map.put("flag", flag);
		return map;
	}
	
	@GetMapping("/read-img/{fname}")
	public ResponseEntity<byte[]> read_img(@PathVariable("fname") String fname){
		ResponseEntity<byte[]> result = null;
		File f = new File(path + fname);
		HttpHeaders header = new HttpHeaders(); // import org.springframework.http.HttpHeaders;
		try {
			header.add("Content-Type", Files.probeContentType(f.toPath()));
			header.add(HttpHeaders.CONTENT_DISPOSITION,
					"attachment;filename=\"" +
			URLEncoder.encode(fname, "UTF-8") + "\"");
			result = new ResponseEntity<byte[]>(
					FileCopyUtils.copyToByteArray(f), header, 
					HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
