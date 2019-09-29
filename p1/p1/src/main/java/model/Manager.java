package model;

public class Manager {

	String ManagerName;
	String Email;
	String Address;
	String Password;
	String phoneNumber;
	
	public Manager(String managerName, String email, String address, String password, String phoneNumber) {
		this.ManagerName = managerName;
		this.Email = email;
		this.Address = address;
		this.Password = password;
		this.phoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
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
