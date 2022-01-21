package test;

import java.time.LocalDate;
import java.util.List;

import todoApp.dao.TodoDaoImpl;
import todoApp.model.Todo;

public class Test {

	public static void main(String[] args) {
		TodoDaoImpl dao = new TodoDaoImpl();
//		Todo todo = new Todo(1L, "할 일1", "drv982", "첫 번째 할 일", LocalDate.parse("2022-01-30"), false);
		
//		 dao.insertTodo(todo);
//		Todo t1 = dao.selectTodo(4);
//		System.out.println(t1.toString());
		
//		dao.deleteTodo(3);
//		Todo todo5 = new Todo(5L, "할 일5수정", "drv982", "세 번째 할 일수정", LocalDate.parse("2052-02-20"), true);
//		dao.updateTodo(todo5);
		
		List<Todo> todos = dao.selectAllTodos();
		
		for(Todo todo : todos) {
			System.out.println(todo.toString());
		}
		
	}

}
