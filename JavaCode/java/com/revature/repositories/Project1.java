package com.revature.repositories;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.User;

public interface Project1 {
	
	User getUser(int id);
	
	User getUser(String email);
	
	Reimbursement getReimbursement(int id);
	
	Reimbursement getReimbursement(String reason);
	
	List<User> getUsers();
	
	List<Reimbursement>getReimbursements();
	
	boolean createUser(User u);
	
	boolean createReimbursement(Reimbursement r);
	
	boolean updateUser(User u);
	
	boolean updateReimbursement(Reimbursement r);

}
