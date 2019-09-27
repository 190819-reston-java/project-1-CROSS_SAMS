package servlet;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import repository.Dao;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Dao doa = new Dao();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String email = request.getParameter("login_email");
            String password = request.getParameter("login_password");

            if(doa.loginManager(email, password) != null)
            {
                RequestDispatcher rd = request.getRequestDispatcher("managerHomepage.html");
                rd.forward(request, response);
            }
            else
            {
                out.println("<font color='red'><b>You have entered incorrect password</b></font>");
                RequestDispatcher rd = request.getRequestDispatcher("login.html");
                rd.include(request, response);
            }
        } catch (SQLException e) {
			e.printStackTrace();
		}
        finally {            
            out.close();
        }
    }
}
        
    