<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
  String strId = request.getParameter("id");

  if(strId == null || strId.trim().equals("")) {
	  out.print("아이디를 입력 해주세요.");
  } else {
	  int id = Integer.parseInt(strId);
	  
	  try {
		  String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		  Connection conn = DriverManager.getConnection(url, "root", "1234");
		  
		  PreparedStatement pstmt =
				  conn.prepareStatement("SELECT * FROM emp WHERE id=?");
		  pstmt.setInt(1, id);
		  
		  ResultSet rs = pstmt.executeQuery(); // sql 실행 후 결과를 rs에 저장
		  
		  if(rs.next()) {
			  out.print(rs.getInt("id") + " " + rs.getString("name"));
		  } else {
			  out.print("테이블에 해당 id가 없습니다.");
		  }
		  conn.close();
		  
	  } catch (Exception e) {
		  e.printStackTrace(); // 예외출력
	  }
  }
%>