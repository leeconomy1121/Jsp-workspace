package gui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// HttpServlet 을 상속한 클래스 => 서블릿
@WebServlet("/hi")
public class Hijsp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 주소url로 요청시
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<b>Hi!!!</b>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// form 데이터 즉, url에 보이지 않는 예) 로그인
		super.doPost(req, resp);
	}
	
}
