package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PhonebookDAO;
import com.javaex.vo.PhonebookVO;

@Service
public class PhonebookService {

	//필드
	@Autowired
	private PhonebookDAO phonebookDAO;
	
	//생성자
	
	
	//메소드gs
	
	
	//메소드 일반
	//-전체 리스트 가져오기
	public List<PhonebookVO> exeGetPhonebookList() {
		System.out.println("PhonebookService.exeGetPhonebookList");
		
		//PhonebookDAO phonebookDAO = new PhonebookDAO();
		
		List<PhonebookVO> phonebookList = phonebookDAO.phonebookSelect();
		
		return phonebookList;
	}
	
	//게시판 등록하기
	public int exePhonebookWrite(PhonebookVO phonebookVO) {
		System.out.println("PhonebookService.exePhonebookWrite()");
		
		int count = phonebookDAO.phonebookInsert(phonebookVO);
		
		
		return count;
	}
	
	
	
	
	
}
