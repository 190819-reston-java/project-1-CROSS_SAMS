package com.revature.project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import org.apache.http.HttpException;
import javax.xml.ws.http.HTTPException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.repositories.Project1DAO;
import com.revature.services.DAOServices;

public class Project1 extends HttpServlet {

	private DAOServices DaoService;

	@Override
	public void init() throws ServletException {
		System.out.println("Starting Project1 Controller");
		this.DaoService = new DAOServices(new Project1DAO());
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Handling a GET in the servlet");

		String[] splitURI = req.getRequestURI().split("/");
		String[] tokens = Arrays.copyOfRange(splitURI, 3, splitURI.length);

//		System.out.println(Arrays.deepToString(splitURI));

		System.out.println(Arrays.toString(tokens));

		if (tokens.length == 0) {
			resp.sendError(400, "Usage: /api/players or /api/rpc");
			return;
		}
		switch (tokens[0]) {
		case "reimbursements":
			handleReimbursements(req, resp, tokens);
			break;
		case "employees":
			handleEmployees(req, resp, tokens);
			break;
		case "resolvedReimbursements":
			handleResolvedReimbursements(req, resp, tokens);
			break;
//		case "reimbursementsByEmployee":
//			handleReimbursementsByEmployee(req, resp, tokens);
//			break;
		default:
			resp.sendError(404, "Token not recognized: " + tokens[0]);
			break;
		}
	}

	private void handleReimbursements(HttpServletRequest req, HttpServletResponse resp, String[] tokens)
			throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();

		Reimbursement reimbursement = null;

		switch (req.getMethod()) {
		case "GET":
			// Check if further tokens follow /players:
			if (tokens.length == 1) {
				String jsonUsers = om.writeValueAsString(DaoService.getReimbursement());
				pw.write(jsonUsers);
			} else {
				String jsonUser = om.writeValueAsString(DaoService.getReimbursement(tokens[1]));
				pw.write(jsonUser);
			}
			break;
		case "POST":
			HttpSession session = req.getSession();
			String email = (String) session.getAttribute("user");
			System.out.println(email);
			// We'll read JSON from the request body
			reimbursement = om.readValue(req.getReader(), Reimbursement.class);
			reimbursement.setEmployee(email);
			System.out.println(reimbursement);
			// This should definitely be more informative.
			if (!DaoService.createReimbursement(reimbursement)) {
				resp.sendError(400, "Failed to create player");
			} else {
				pw.write("Successful creation");
			}
			break;
		case "PUT":
			HttpSession employeeSession = req.getSession();
			HttpSession putSession = req.getSession();
			String putEmail = (String) putSession.getAttribute("user");
			String employeePutEmail = (String) employeeSession.getAttribute("user");
			System.out.println(putEmail);

			reimbursement = om.readValue(req.getReader(), Reimbursement.class);
			reimbursement.setEmployee(employeePutEmail);
			reimbursement.setManager(putEmail);
//			reimbursement = om.readValue(req.getReader(), Reimbursement.class);
			if (tokens.length > 1) {
				try {
					reimbursement.setId(Integer.parseInt(tokens[1]));
				} catch (NumberFormatException e) {
					resp.sendError(400, "Must PUT to a valid ID.  PUT by name is not supported.");
				}
			}

			if (!DaoService.updateReimbursement(reimbursement)) {
				resp.sendError(400, "Failed to update Reimbursement");
			} else {
				pw.write("Successful update");
			}
			break;
		}

	}
	
	private void handleResolvedReimbursements(HttpServletRequest req, HttpServletResponse resp, String[] tokens) 
			throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();

		Reimbursement reimbursement = null;

		switch (req.getMethod()) {
		case "GET":
			// Check if further tokens follow /players:
			if (tokens.length == 1) {
				String jsonUsers = om.writeValueAsString(DaoService.getResolvedReimbursement());
				pw.write(jsonUsers);
			} else {
				String jsonUser = om.writeValueAsString(DaoService.getResolvedReimbursement(tokens[1]));
				pw.write(jsonUser);
			}
			break;
		case "POST":
			// We'll read JSON from the request body
			reimbursement = om.readValue(req.getReader(), Reimbursement.class);
			// This should definitely be more informative.
			if (!DaoService.createReimbursement(reimbursement)) {
				resp.sendError(400, "Failed to create player");
			} else {
				pw.write("Successful creation");
			}
			break;
		case "PUT":
			reimbursement = om.readValue(req.getReader(), Reimbursement.class);
			if (tokens.length > 1) {
				try {
					reimbursement.setId(Integer.parseInt(tokens[1]));
				} catch (NumberFormatException e) {
					resp.sendError(400, "Must PUT to a valid ID.  PUT by name is not supported.");
				}
			}

			if (!DaoService.updateReimbursement(reimbursement)) {
				resp.sendError(400, "Failed to update Reimbursement");
			} else {
				pw.write("Successful update");
			}
			break;
		}
	}

	private void handleEmployees(HttpServletRequest req, HttpServletResponse resp, String[] tokens)
			throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();

		User user = null;

		switch (req.getMethod()) {
		case "GET":
			// Check if further tokens follow /players:
			if (tokens.length == 1) {
				String jsonUsers = om.writeValueAsString(DaoService.getUsers());
				pw.write(jsonUsers);
			} else {
				String jsonUser = om.writeValueAsString(DaoService.getUser(tokens[1]));
				pw.write(jsonUser);
			}
			break;
		case "POST":
			// We'll read JSON from the request body
			user = om.readValue(req.getReader(), User.class);
			// This should definitely be more informative.
			if (!DaoService.createUser(user)) {
				resp.sendError(400, "Failed to create Employee");
			} else {
				pw.write("Successful creation");
			}
			break;
		case "PUT":
			user = om.readValue(req.getReader(), User.class);
			if (tokens.length > 1) {
				try {
					user.setId(Integer.parseInt(tokens[1]));
				} catch (NumberFormatException e) {
					resp.sendError(400, "Must PUT to a valid ID.  PUT by name is not supported.");
				}
			}

			if (!DaoService.updateUser(user)) {
				resp.sendError(400, "Failed to update player");
			} else {
				pw.write("Successful update");
			}
			break;
		}

	}
	
//	private void handleReimbursementsByEmployee(HttpServletRequest req, HttpServletResponse resp, String[] tokens)
//			throws ServletException, IOException {
//		
////		private static final long serialVersionUID = 1L;
//		
//		ObjectMapper om = new ObjectMapper();
//		PrintWriter pw = resp.getWriter();
//		
//		Project1DAO dao = new Project1DAO();
//			HttpSession session = req.getSession();
//			String sessionEmail = (String) session.getAttribute("user");
//			try {
//				pw.println("<html>");
//		        pw.println("<head>");
//		        pw.println("<title>Current Employee's Reimbursements</title>");
//		        pw.println("</head>");
//		        pw.println("<body>");
//		        pw.println("<table align =\"center\" border=\"3\">");
//		        for (int i = 0; i < dao.getReimbursementByEmployeeEmail(sessionEmail).size(); i++) {
//		        	pw.println("<tr>");	
//		        	 pw.println("<td>" + 
//		        	dao.getReimbursementByEmployeeEmail(sessionEmail).get(i).getAmount()+ "</td>");
//		             pw.println("<td>" + 
//		        	dao.getReimbursementByEmployeeEmail(sessionEmail).get(i).getDate()+ "</td>");
//		             pw.println("<td>" + 
//		        	dao.getReimbursementByEmployeeEmail(sessionEmail).get(i).getStatus()+ "</td>");
//			        pw.println("</tr>");
//		        }
//		       
//		        pw.println("</table>");
//		        pw.println("</body>");
//		        pw.println("</html>"); 
//
//			} catch (HTTPException e) {
//				resp.setStatus(500);
//				e.printStackTrace();
//			}
//			if (tokens.length == 1) {
//				String jsonUsers = om.writeValueAsString(DaoService.getReimbursement());
//				pw.write(jsonUsers);
//			} else {
//				String jsonUser = om.writeValueAsString(DaoService.getReimbursement(tokens[1]));
//				pw.write(jsonUser);
//			}
//			
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("A post");
		doGet(req, resp);

	}
	
	

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("A put");
		doGet(req, resp);
	}

}