package com.revature.services;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.repositories.Project1;

public class DAOServices {
	
//	public DAOServices(Project1 projectDao) {
//		this.project1 = projectDao;
//		this.selectedUser = new User(0, "N", "user", "an@email.co", "1057 m st", "(757)685-0413");
//	}
	
	public DAOServices(Project1 reimbursementDao) {
		this.project1 = reimbursementDao;
		this.selectedReimbursement = new Reimbursement(0, "reason", 0.00, "date", "status", "employee", "manager");
		this.selectedUser = new User(0, "name", "email", "address", "password", "333-####");
	}
	
	private User selectedUser;
	private Reimbursement selectedReimbursement;
	private Project1 project1;
	
	public User getSeclectedUser() {
		return selectedUser;
	}
	
	public void setSelectedPlayer(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	public Reimbursement getSelectedReimbursement() {
		return selectedReimbursement;
	}
	
	public void setSelectedReimbursement(Reimbursement selectedReimbursement) {
		this.selectedReimbursement = selectedReimbursement;
	}
	
	public List<User> getUsers() {
		return project1.getUsers();
	}
	
	public List<Reimbursement> getReimbursement() {
		return project1.getReimbursements();
	}
	
	public List<Reimbursement> getResolvedReimbursement() {
		return project1.getResolvedReimbursements();
	}
	
//	public List<Reimbursement> getReimbursementByEmployeeEmail() {
//		return project1.getReimbursementByEmployeeEmail();
//	}
	
	public void updateSelectedPlayer() {
		project1.updateUser(selectedUser);
	}
	
	public User getUser(String string) {
		try {
			return project1.getUser(Integer.parseInt(string));
		} catch (NumberFormatException e ) {
			return project1.getUser(string);
		}
	}
	
	public boolean createUser(User user) {
		return project1.createUser(user);
	}
	
	public Reimbursement getReimbursement(String string) {
		try {
			return project1.getReimbursement(Integer.parseInt(string));
		} catch (NumberFormatException e) {
			return project1.getReimbursement(string);
		}
	}
	
	public Reimbursement getResolvedReimbursement(String string) {
		try {
			return project1.getResolvedReimbursement(Integer.parseInt(string));
		} catch (NumberFormatException e) {
			return project1.getResolvedReimbursement(string);
		}
	}
	
	public boolean createReimbursement(Reimbursement reimbursement) {
		return project1.createReimbursement(reimbursement);
	}
	
	public boolean updateUser(User user) {
		return project1.updateUser(user);
	}
	
	public boolean updateReimbursement(Reimbursement reimbursement) {
		return project1.updateReimbursement(reimbursement);
	}
	
}
