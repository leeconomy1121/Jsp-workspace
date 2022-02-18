package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.LoginBean;

public class LoginDao {
	
	private DataSource dataSource;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public LoginDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public boolean validate(LoginBean loginBean) {
		boolean status = false;
		String sql = "SELECT * FROM customer WHERE id = ? and password = ?";
		
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginBean.getCustomerID());
			pstmt.setString(2, loginBean.getCustomerPassword());
			System.out.println(rs);
			
			rs = pstmt.executeQuery();
			
			status = rs.next();
					
		} catch (SQLException e) {
			System.out.println("SQL 에러");
			
		} finally {
			closeAll();
		}
		
		return status;
		
	}

	private void closeAll() {
		try {
			
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close(); // Connection Pool에 반납
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
