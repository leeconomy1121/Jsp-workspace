package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Contact;

public class ContactDao {
	// DB에 있는 contact 테이블을 CRUD 하는 클래스
	// DB 연결 객체들
	private DataSource dataSource; // jdbc/demo 커넥션 풀 연결 객체
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ContactDao(DataSource dataSource) {
		this.dataSource = dataSource; // 객체 생성 시 커넥션 풀 dataSource를 입력
	}
	
	// 모든 연락처를 리스트로 리턴
	public List<Contact> findAll() {
		List<Contact> list = new ArrayList<Contact>(); // 빈 리스트 생성
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM contacts");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setPhone(rs.getString("phone"));
				
				list.add(contact);
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러");
		} finally { // 에러에 상관없이 무조건 실행
			// DB연결 객체들을 다는 과정이 필요(리소스를 반환), 불필요한 자원(네트워크 및 메모리) 낭비를 막기위함 
			closeAll();
		}
		
		return list;
	}

	private void closeAll() {
		
		try {
			// 나중에 연 순서부터 닫음 rs => pstmt => conn(풀로 되돌아감)
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		} catch (Exception e) {
			System.out.println("DB연결 닫을 때 에러 발생");
		}
		
	}
	
	public Contact find(int id) {
		Contact contact = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM contacts WHERE id = ?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러");
		} finally { 
			closeAll();
		}
		
		return contact;
	}
	
	public boolean save(Contact contact) {
		boolean rowAffected = false;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO contacts (name, email, phone) values (?, ?, ?)");
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getEmail());
			pstmt.setString(3, contact.getPhone());
			
			rowAffected = pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("SQL 추가 에러");
		} finally {
			closeAll();
		}
		
		return rowAffected;
	}
	
	public boolean update(Contact contact) {
		boolean rowAffected = false;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("UPDATE contacts set name = ?, email = ?, phone = ? where id = ?");
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getEmail());
			pstmt.setString(3, contact.getPhone());
			pstmt.setInt(4, contact.getId());
			
			rowAffected = pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("SQL 업데이트 에러");
		} finally {
			closeAll();
		}
		
		return rowAffected;
	}
	
	public boolean delete(int id) {
		boolean rowAffected = false;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM contacts WHERE id = ?");
			pstmt.setInt(1, id);
			
			rowAffected = pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("SQL 삭제 에러");
		} finally {
			closeAll();
		}
		
		return rowAffected;
	}
	
}
