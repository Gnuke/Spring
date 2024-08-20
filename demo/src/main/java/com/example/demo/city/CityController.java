package com.example.demo.city;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/city")
public class CityController {
	
	@Autowired
	private CityService service;
	
	//검색---------------------------
	@GetMapping("/info")
	public String info() {
		return "city/info";
	}
	
	@GetMapping("/detail")
	public String detail(int id, Model m) { 
	// Model : view 페이지에 전달할 값을 담는 용도, param으로 지정하면 객체를 자동 생성해주고 값을 view 페이지로 자동으로 전달한다. 
		City city = service.getCity(id);
		if( city == null ) {
			m.addAttribute("msg", "없는 아이디");
		}else {
			m.addAttribute("city",city); // addAttribute("view 페이지에서 부를 이름", 전달할 값)
			// -> view page에서 ${city.XXX} 로 표현
		}
		
		System.out.println(m);
		return "city/detail";
	}
	
	//전체검색---------------------------------
	@RequestMapping("/list")
	public String list(Model m) {
		m.addAttribute("list", service.selectAll() );
		return "city/list";
	}

	//추가-----------------------------------
	@GetMapping("/add")
	public String addForm( Model m ) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("ABW");
		list.add("AFG");
		list.add("AGO");
		list.add("AIA");
		list.add("ALB");
		list.add("AND");
		
		m.addAttribute( "list", list );
		
		return "city/add";
	}
	
	@PostMapping("/add")
	public String add(City city) {
		service.addCity(city);
		return "index";
	}
	
	//수정--------------------------------------------------
	@PostMapping("/edit")
	public String edit(City city) {
		service.editCity(city);
		return "index";
	}
	
	//삭제---------------------------------------------------
	@GetMapping("/del")
	public String del(int id) {
		System.out.println( id );
		service.delCity(id);
		return "index";
	}
}
