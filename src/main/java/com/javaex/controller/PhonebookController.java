package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/person")
public class PhonebookController {

	//필드
	
	
	//생성자
	
	
	//메소드gs
	
	
	//메소드 일반
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String select() {
		System.out.println("PhonebookContoroller.select()");
		
		return "";
	}
	
	@RequestMapping(value = "/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String insert() {
		System.out.println("PhonebookController.insert()");
		
		return "";
	}
	
}
