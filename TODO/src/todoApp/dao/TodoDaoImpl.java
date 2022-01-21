package todoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import todoApp.model.Todo;
import todoApp.utils.JDBCUtils;

public class TodoDaoImpl implements TodoDao{
	// DB연결하고 각 기능에 맞게 작업한다. 데이터베이스 todos테이블에 CRUD 작업
	
	@Override
	public void insertTodo(Todo todo) {
		String INSERT_USER_SQL = "INSERT INTO todos(title,username,description,target_date,is_done) "
				+ "VALUE (?,?,?,?,?)";
		
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_USER_SQL);
			pstmt.setString(1, todo.getTitle());
			pstmt.setString(2, todo.getUsername());
			pstmt.setString(3, todo.getDescription());
			pstmt.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
			pstmt.setBoolean(5, todo.isStatus());
			
			pstmt.executeUpdate(); // 결과가 없는 업데이트, 삭제, 입력 등은 쿼리 업데이트 한 줄의 갯수가 리턴됨
			
		} catch (SQLException e) {
			System.out.println("SQL 입력 에러");
		}
		
	}
	
	@Override
	public Todo selectTodo(long todoId) {
		Todo todo = null;
		
		Connection conn = JDBCUtils.getConnection();
		
		String SELECT_TODO_BY_ID = "SELECT id,title,username,description,target_date,is_done FROM todos WHERE id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SELECT_TODO_BY_ID);
			pstmt.setLong(1, todoId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) { // 검색 결과가 있을 경우에 값을 저장한다. (없는데 저장하면 에러발생)
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				Boolean status = rs.getBoolean("is_done");
				todo = new Todo(id, title, username, description, targetDate, status);	
			}
			
		} catch (SQLException e) {
			System.out.println("SQL todo 검색 에러");
		}
		
		return todo;
	}

	@Override
	public List<Todo> selectAllTodos() {
		List<Todo> todos = new ArrayList<>(); // 빈 리스트를 생성
		
		Connection conn = JDBCUtils.getConnection();
		
		String SELECT_ALL_TODOS = "SELECT * FROM todos"; // todos 테이블 전체 검색
		try {
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_TODOS);
			
			ResultSet rs = pstmt.executeQuery(); // 쿼리 실행 후 결과 저장
			// 결과가 여러 줄일 경우 while() 사용하여 처리, 한줄 일때 if()
			while(rs.next()) { // 검색 결과가 있을 경우에 값을 저장한다. (없는데 저장하면 에러발생)
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				Boolean status = rs.getBoolean("is_done");
				// 리스트에 담기 (todo 객체로 입력)
				todos.add(new Todo(id, title, username, description, targetDate, status));	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL todo ALL 검색 에러");
			return null;
		}
		
		return todos;
	}

	@Override
	public boolean deleteTodo(long todoId) {
		boolean rowDeleted = false;
		String DELETE_TODO_BY_ID = "DELETE FROM todos WHERE id = ?";
		
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_TODO_BY_ID);
			pstmt.setLong(1, todoId);
			
			rowDeleted = pstmt.executeUpdate() > 0; // 한 줄이상 삭제가 되면 true
			
		} catch (SQLException e) {
			System.out.println("SQL 삭제 에러");
		}
		
		return rowDeleted;
	}

	@Override
	public boolean updateTodo(Todo todo) {
		boolean rowUpdated = false;
		String UPDATE_TODO = "UPDATE todos set title = ?, username = ?, description = ?, target_date = ?, is_done = ? WHERE id = ?";
		
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_TODO);
			pstmt.setString(1, todo.getTitle());
			pstmt.setString(2, todo.getUsername());
			pstmt.setString(3, todo.getDescription());
			pstmt.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
			pstmt.setBoolean(5, todo.isStatus());
			pstmt.setLong(6, todo.getId());
			
			rowUpdated = pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			System.out.println("SQL 업데이트 에러");
			return false;
		}
		
		System.out.println("업데이트 완료");
		return rowUpdated;
	}
	
}