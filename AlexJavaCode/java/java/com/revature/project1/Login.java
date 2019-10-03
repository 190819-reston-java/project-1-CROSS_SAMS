package com.revature.project1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.repositories.Project1DAO;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Project1DAO dao = new Project1DAO();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {

			String email = request.getParameter("login_email");
			String password = request.getParameter("login_password");

			if (dao.loginManager(email, password) != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user", email);
				RequestDispatcher redirect = request.getRequestDispatcher("managerHomepage.html");
				redirect.forward(request, response);
			}

			else if (dao.loginUser(email, password) != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user", email);
				RequestDispatcher redirect = request.getRequestDispatcher("employee.html");
				redirect.forward(request, response);
			} else {
				out.println("<font color='red'><b> Sorry Incorrect Password try again...</b></font>");
				RequestDispatcher redirect = request.getRequestDispatcher("login.html");
				redirect.include(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			out.close();
		}
	}
}
