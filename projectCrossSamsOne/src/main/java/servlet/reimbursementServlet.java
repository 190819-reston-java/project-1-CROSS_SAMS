package servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.Dao;


public class reimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Dao dao = new Dao();
		try {
			response.getWriter().println("<html>");
	        response.getWriter().println("<head>");
	        response.getWriter().println("<title>Current Reimbursements</title>");
	        response.getWriter().println("</head>");
	        response.getWriter().println("<body>");
	        response.getWriter().println("<table align =\"center\" border=\"3\">");
	        for (int i = 0; i < dao.totalReimbursements().size(); i++) {
	        	response.getWriter().println("<tr>");	
	        	 response.getWriter().println("<td>" + dao.totalReimbursements().get(i).getEmployee()+ "</td>");
	             response.getWriter().println("<td>" + dao.totalReimbursements().get(i).getDate()+ "</td>");
	             response.getWriter().println("<td>" + dao.totalReimbursements().get(i).getAmount()+ "</td>");
	             response.getWriter().println("<td>" + dao.totalReimbursements().get(i).getReason()+ "</td>");
	             response.getWriter().println("<td>" + dao.totalReimbursements().get(i).getStatus()+ "</td>");
	             response.getWriter().println("<td>" + dao.totalReimbursements().get(i).getId()+ "</td>");
	             response.getWriter().println("<td>" + dao.totalReimbursements().get(i).getManager()+ "</td>");
		        response.getWriter().println("</tr>");
	        }
	       
	        response.getWriter().println("</table>");
	        response.getWriter().println("</body>");
	        response.getWriter().println("</html>"); 
			
		} catch (SQLException e) {
				response.setStatus(500);
				e.printStackTrace();
			}
		
		}

}
