package javax.servlet.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import repository.Dao;


@SuppressWarnings("serial")
public class LoginEmployee extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Dao employee = new Dao();
			String email = request.getParameter("login_email");
			String password = request.getParameter("login_password");
			PrintWriter pw = response.getWriter();
			//whatever we write using pw will go in the body of the response
				try {
					if(employee.loginManager(email, password) != null){
						response.sendRedirect("employee.html");		
					}
				} catch (SQLException e) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST,
							"Incorrect Login Credentials Please try again...");
							e.printStackTrace(pw);
				}
			}
}
