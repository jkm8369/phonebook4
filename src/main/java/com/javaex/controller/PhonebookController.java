package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhonebookDAO;
import com.javaex.vo.PhonebookVO;

@Controller
public class PhonebookController {

	//필드
	
	
	//생성자
	
	
	//메소드gs
	
	
	//메소드 일반
	@RequestMapping(value ="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		//System.out.println("PhonebookContoroller.list()"); 
		
		PhonebookDAO phonebookDAO = new PhonebookDAO();
		List<PhonebookVO> personList = phonebookDAO.phonbookSelect();
		
		//System.out.println(personList); 확인 완
		
		model.addAttribute("pList", personList);
		
		return "/list";
	}
	
	@RequestMapping(value ="wform", method = {RequestMethod.GET, RequestMethod.POST})
	public String wform() {
		//System.out.println("PhonebookController.wform()");
		 
		return "writeForm";
	}
	
	@RequestMapping(value ="write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PhonebookVO phonebookVO) {
		//System.out.println("PhonebookController.write()");
		
		System.out.println(phonebookVO);
		
		PhonebookDAO phonebookDAO = new PhonebookDAO();
		phonebookDAO.phonebookInsert(phonebookVO);
		//System.out.println(count);
		
		
		return "redirect:/list";
	}
	
	@RequestMapping(value= "/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam(value="no")int no) {
		System.out.println("PhonebookController.delete()");
		
		System.out.println(no);
		
		PhonebookDAO phonebookDAO = new PhonebookDAO();
		phonebookDAO.phonebookDelete(no);
		
		
		
		return "redirect/list";
	}
	
	
	
	
	
	
	
	
	
}
