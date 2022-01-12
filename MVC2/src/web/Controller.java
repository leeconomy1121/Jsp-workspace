package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// 서블릿 종료 시 한 번만 실행
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// 서블릿이 생성될 때 한 번만실행
		super.init();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String page = null;
		if(action == null) {
			page = "/error.jsp";
		} else if (action.equals("login")) {
			page = "/login.jsp";
		} else if (action.equals("about")) {
			page = "/about.jsp";
		} else {
			page = "/error.jsp";
		}
		
		getServletContext().getRequestDispatcher(page).forward(request, response);
	}

}
