package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.PhonebookService;
import com.javaex.vo.PhonebookVO;

@Controller
public class PhonebookController {

	//필드
	@Autowired
	private PhonebookService phonebookService;
	
	//생성자
	
	
	//메소드gs
	
	
	//메소드 일반
	//리스트 전체
	@RequestMapping(value ="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhonebookContoroller.list()"); 
		
		//PhonebookDAO phonebookDAO = new PhonebookDAO();
		
		
		//System.out.println(personList); 확인 완
		
		List<PhonebookVO> personList = phonebookService.exeGetPhonebookList();
		
		model.addAttribute("pList", personList);
		
		return "/list";
	}
	
	//등록폼
	@RequestMapping(value ="wform", method = {RequestMethod.GET, RequestMethod.POST})
	public String wform() {
		//System.out.println("PhonebookController.wform()");
		 
		return "writeForm";
	}
	
	//게시판 등록하기
	@RequestMapping(value ="write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PhonebookVO phonebookVO) {
		System.out.println("PhonebookController.write()");
		
		
		PhonebookService phonebookService = new PhonebookService();
		phonebookService.exePhonebookWrite(phonebookVO);
		
		//phonebookDAO.phonebookInsert(phonebookVO);
		//System.out.println(count);
		
		
		return "redirect:/list";
	}
	
	//삭제
	@RequestMapping(value= "/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam(value="no")int no) {
		//System.out.println("PhonebookController.delete()");
		
		//System.out.println(no);
		
		//PhonebookDAO phonebookDAO = new PhonebookDAO();
		//phonebookDAO.phonebookDelete(no);
		
		
		
		return "redirect:/list";
	}
	
	//수정 폼
	@RequestMapping(value ="/mform", method = {RequestMethod.GET, RequestMethod.POST})
	public String mform(@RequestParam("no") int no, Model model) {
		//System.out.println("PhonebookController.mform()");
		
		//PhonebookDAO phonebookDAO = new PhonebookDAO();
		//PhonebookVO phonebookVO = phonebookDAO.personSelectOne(no);
		
		//model.addAttribute("pbVO", phonebookVO);
		
		
		return "modifyForm";
	}
	
	//수정
	@RequestMapping(value="/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute PhonebookVO phonebookVO) {
		//System.out.println("PhonebookController.modify()");
		
		//System.out.println(phonebookVO);
		
		//PhonebookDAO phonebookDAO = new PhonebookDAO();
		//phonebookDAO.personUpdate(phonebookVO);
	
		return "redirect:/list";
	}
	
	
	
	
	
	
	
	
	
}
