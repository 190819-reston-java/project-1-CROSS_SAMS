package com.revature;

public class Employee {
	
	String EmpoloyeeName;
	String Email;
	String Adress;
	String Password;
	
	public Employee(String employeePosition, String empoloyeeName, String email, String adress, String password,
			String phoneNumber) {
		super();
		EmpoloyeeName = empoloyeeName;
		Email = email;
		Adress = adress;
		Password = password;
		PhoneNumber = phoneNumber;
	}
	
	public String getEmpoloyeeName() {
		return EmpoloyeeName;
	}
	public void setEmpoloyeeName(String empoloyeeName) {
		EmpoloyeeName = empoloyeeName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getAdress() {
		return Adress;
	}
	public void setAdress(String adress) {
		Adress = adress;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	String PhoneNumber;


}
