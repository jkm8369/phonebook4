package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PhonebookVO;

@Repository
public class PhonebookDAO {

	// 필드
	@Autowired
	private SqlSession sqlSession;

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/phonebook_db";
	private String id = "phonebook";
	private String pw = "phonebook";

	

	// 메소드gs

	// 메소드일반
	// DB연결 메소드-공통
	private void connect() { // 메인에서는 사용하지 못함

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			this.conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 자원정리 메소드-공통
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	//전체데이터 가져오기
	public List<PhonebookVO> phonebookSelect() {
		System.out.println("PhonebookDAO.phonebookSelect()");
		
		List<PhonebookVO> personList = sqlSession.selectList("phonebook.selectList");
		
		return personList;
	}
	
	//등록하기
	public int phonebookInsert(PhonebookVO phonebookVO) {
		System.out.println("PhonebookDAO.phonebookInsert()");
		
		int count = sqlSession.insert("phonebook.insert", phonebookVO);

		return count;
	}

	public int phonebookDelete(int no) {
		int count = -1;

		connect();

		try {

			String query = "";
			query += " delete from phonebook ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			count = pstmt.executeUpdate();

			System.out.println(count + "건 삭제 완료");

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();
		return count;
	}

	public PhonebookVO personSelectOne(int no) {
		PhonebookVO phonebookVO = null;

		connect();

		try {

			String query = "";
			query += " select person_id, ";
			query += " 		  name, ";
			query += " 		  hp, ";
			query += " 		  company ";
			query += " from phonebook ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			rs.next();

			int personId = rs.getInt("person_id");
			String name = rs.getString("name");
			String hp = rs.getString("hp");
			String company = rs.getString("company");

			phonebookVO = new PhonebookVO(personId, name, hp, company);

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();

		return phonebookVO;
	}

	public int personUpdate(PhonebookVO phonebookVO) {
		int count = -1;

		connect();

		try {

			String query = "";
			query += " update phonebook ";
			query += " set name = ?, ";
			query += " 	   hp = ?, ";
			query += " 	   company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, phonebookVO.getName());
			pstmt.setString(2, phonebookVO.getHp());
			pstmt.setString(3, phonebookVO.getCompany());
			pstmt.setInt(4, phonebookVO.getPersonId());

			count = pstmt.executeUpdate();

			System.out.println(count + "건이 수정 되었습니다.");

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();

		return count;
	}

}
