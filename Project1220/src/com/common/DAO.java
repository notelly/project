package com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAO {
	// DB랑 java 연결
	// JAVA -> DB 연결 새주는 객체(JDBC)
	// OJDBC를 자바 프로젝트 추가

	// java -> DB 연결할 떄 쓰는 객체
	protected Connection conn = null;

	// Select(조회) 결과 값 반환 받는 객체 //실행한 결과를 가지고 오는것
	protected ResultSet rs = null;

	// Query 문(SQL)을 가지고 Query문(SQL)을 실행하는 객체
	protected PreparedStatement pstmt = null;

	// Query 문(SQL)을 가지고 Query문(SQL)을 실행하는 객체
	protected Statement stmt = null;
	// 위와 차이는 나중에 설명

	// SELECT, INSERT, UPDATE, DELETE 등등의 Query 문을
	// DB로 가지고 가서 실행 시킴.
	// ex) SELECT * FROM emloyees;
	
	//DB 접속 정보 <고정>
	String driver = "oracle.jdbc.driver.OracleDriver";
									//IP	:port:DBname
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "project";
	String pw = "project";
	//Ddriver[oracle.jdbc.driver.OracleDriver]
	//URL[jdbc:oracle:thin:@localhost:1521:DBNAME]

	//DB 연결 메소드
	public  void conn() {
		try {
			//1. 드라이버 로딩
			//밑줄 뜨는 이유 미리 예외 처리해라 <근데 안뜸>
			Class.forName(driver);
			
			//2. DB 연결
			conn = DriverManager.getConnection(url,id,pw);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//연결 끊는거
	public void disconn() {
		try {
			//else if 쓰면 다음을 만족했을 때 다음으로 넘어가지 않기 때문에
			//각각 if를 써줌.
			if( rs != null) {
				rs.close();
			}
			if( stmt != null) {
				stmt.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if( conn != null) {
				conn.close();
			}		
			
 		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
