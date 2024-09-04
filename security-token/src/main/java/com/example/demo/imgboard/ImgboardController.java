package com.example.demo.imgboard;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.users.Users;

@RestController
@CrossOrigin(origins = "*")
public class ImgboardController {
	@Autowired
	private ImgboardService service;
	
	@Value("${spring.servlet.multipart.location}")
	private String path;
	
	@PostMapping("/board")
	public Map add(ImgboardDTO dto) {
		String fname = dto.getF().getOriginalFilename();
		File newf = new File(path + fname);
		boolean flag = false;
		try {
			dto.getF().transferTo(newf);
			dto.setImg(fname);
			Authentication a = 
					SecurityContextHolder.getContext().getAuthentication();
			Users u = new Users( a.getName(), "", "");
			dto.setWriter(u);
			service.save(dto);
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
	
	@GetMapping("/board")
	public Map list() {
		Map map = new HashMap();
		map.put("list", service.getAll());
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
