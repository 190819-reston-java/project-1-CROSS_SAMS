package javax.servlet.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

/**
 * Servlet implementation class Reimbursements
 */
public class ManagerReimbursements extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManagerReimbursements() {
        super();
    }
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		String type = (String) session.getAttribute("rType");
		double amount = (Double) session.getAttribute("rAmount");
		String date = (String) session.getAttribute("rDate");
		PrintWriter pw = response.getWriter();
		if(type == "Travel") {
			response.sendRedirect("managerHomepage.html");	
			}else if(type == "Equipment") {
				response.sendRedirect("managerHomepage.html");	
		}else {
			pw.println("Hello ");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
