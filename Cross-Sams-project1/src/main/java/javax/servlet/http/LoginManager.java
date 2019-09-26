package javax.servlet.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;

import repository.Dao;

@SuppressWarnings("serial")
public class LoginManager extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao manager = new Dao();
		HttpSession session = request.getSession();	
		String email = (String) session.getAttribute("login_email");
		String password = (String) session.getAttribute("login_password");
		PrintWriter pw = response.getWriter();
		
			try {
				if(manager.loginManager(email, password) != null){
					response.sendRedirect("managerHomepage.html");	
				}
			} catch (SQLException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"Incorrect Login Credentials Please try again...");
						e.printStackTrace(pw);
			}
		}
}
