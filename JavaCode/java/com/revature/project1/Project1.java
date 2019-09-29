package com.revature.project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
//		DAOServices userService = new DAOServices(new Project1DAO());
//		resp.getWriter().write(userService.getReimbursement().get(0).toString());
		
//		List<Reimbursement> test = userService.getReimbursement();
//		resp.getWriter().print(test);
//		System.out.println(test);
		
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
		case "rpc":
			resp.sendError(501);
			break;
		default:
			resp.sendError(404, "Token not recognized: " + tokens[0]);
			break;
		}
	}

	private void handleReimbursements(
		HttpServletRequest req, HttpServletResponse resp, String[] tokens
		)throws ServletException, IOException {
	
	ObjectMapper om = new ObjectMapper();
	PrintWriter pw = resp.getWriter();
	
	Reimbursement reimbursement = null;
	
	switch(req.getMethod()) {
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
		//We'll read JSON from the request body
		reimbursement = om.readValue(req.getReader(), Reimbursement.class);
		//This should definitely be more informative.
		if(!DaoService.createReimbursement(reimbursement)) {
			resp.sendError(400, "Failed to create player");
		} else {
			pw.write("Successful creation");
		}
		break;
	case "PUT":
		reimbursement = om.readValue(req.getReader(), Reimbursement.class);
		if(tokens.length > 1) {
			try {
				reimbursement.setId(Integer.parseInt(tokens[1]));
			}catch (NumberFormatException e) {
				resp.sendError(400, "Must PUT to a valid ID.  PUT by name is not supported.");
			}
		}
		
		if(!DaoService.updateReimbursement(reimbursement)) {
			resp.sendError(400, "Failed to update player");
		} else {
			pw.write("Successful update");
		}
		break;
		}
		
	}
	
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