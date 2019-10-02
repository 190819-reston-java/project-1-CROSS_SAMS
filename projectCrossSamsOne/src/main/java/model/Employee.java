package model;

public class Employee {
	
	String name;
	String email;
	String address;
	String employee_password;
	String phone_number;
	
	public Employee(String name, String email, String address, String employee_password, String phone_number) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.employee_password = employee_password;
		this.phone_number = phone_number;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmployee_password() {
		return employee_password;
	}
	public void setEmployee_password(String employee_password) {
		this.employee_password = employee_password;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
