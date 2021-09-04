package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;

import org.apache.commons.beanutils.BeanUtils;

import dao.UserDao;
import entity.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet({"/UserServlet", "/UserServlet/create","/UserServlet/update",
	"/UserServlet/edit", "/UserServlet/delete", "/UserServlet/reset"})
public class UserServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		User user = null;
		if (url.contains("delete")) {
			delete(request, response);
			user = new User();
			request.setAttribute("user", user);
		}else if (url.contains("edit")) {
			edit(request, response);
		}else if (url.contains("reset")) {
			user = new User();
			request.setAttribute("user", user);
		}	
		findAll(request, response);
		request.getRequestDispatcher("/views/user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		if(url.contains("create")) {
			insert(request, response);
		} else if (url.contains("delete")) {
			delete(request, response);
			
			request.setAttribute("user", new User());
		}else if (url.contains("update")) {
			update(request, response);
		}else if (url.contains("reset")) {
			request.setAttribute("user", new User());
		}
		findAll(request, response);
		request.getRequestDispatcher("/views/user.jsp").forward(request, response);
	}
	protected void insert(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			
			UserDao dao = new UserDao();
			dao.insert(user);
			
			request.setAttribute("message", "Insert successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: "+e.getMessage());
		}
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			
			UserDao dao = new UserDao();
			dao.update(user);
			
			request.setAttribute("user", user);
			request.setAttribute("message", "Update successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: "+e.getMessage());
		}
	}
	protected void delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			
			UserDao dao = new UserDao();
			dao.delete(id);
			
			request.setAttribute("message", "Delete successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: "+e.getMessage());
		}
	}
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			
			UserDao dao = new UserDao();
			User user = dao.findById(id);
			
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: "+e.getMessage());
		}
	}
	protected void findAll(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			UserDao dao = new UserDao();
			List<User> lst = dao.findAll();
			
			request.setAttribute("users", lst);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: "+e.getMessage());
		}
	}

}
