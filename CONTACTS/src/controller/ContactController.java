package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.ContactDao;
import model.Contact;

@WebServlet("/contact")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ContactDao contactDao;
	
	@Resource(name = "jdbc/demo")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		contactDao = new ContactDao(dataSource);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		findAll 메소드 테스트 성공!
//		List<Contact> list = contactDao.findAll();
//		list.forEach(contact -> System.out.println(contact.toString()));
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
