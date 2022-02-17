package controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import customer.LoginDao;
import model.LoginBean;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private LoginDao loginDao;
    
    @Resource(name="jdbc/hotel")
    private DataSource dataSource;

	public void init() throws ServletException {
		super.init();
		loginDao = new LoginDao(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String customerID = request.getParameter("customerID");
		String customerPassword = request.getParameter("customerPassword");
		LoginBean loginBean = new LoginBean();
		loginBean.setCustomerID(customerID);
		loginBean.setCustomerPassword(customerPassword);
		
		if( loginDao.validate(loginBean) ) {
			System.out.println("로그인 성공!");
			HttpSession session = request.getSession();
			session.setAttribute("customerID", customerID);
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);			
		} 
		else { // 계정 없음 로그인 실패
			System.out.println("로그인 실패!");
			request.setAttribute("id", customerID);   //유저네임은 다시 보냄
			request.setAttribute("message", "로그인 실패!");
			// 로그인 실패 내용을 포워드로 다시 로그인 페이지에 보여주기
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);				
		}
	}

}
