package com.revature.model;

public class Manager {

	String ManagerName;
	String Email;
	String Address;
	
	public Manager(String managerName, String email, String address, String password) {
		super();
		ManagerName = managerName;
		Email = email;
		Address = address;
		Password = password;
	}
	String Password;
	
	public String getManagerName() {
		return ManagerName;
	}
	public void setManagerName(String managerName) {
		ManagerName = managerName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
	
	
	