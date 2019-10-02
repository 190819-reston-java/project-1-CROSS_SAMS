package model;

public class Manager {
	
	String name;
	String email;
	String address;
	String manager_password;
	String phone_number;
	
	public Manager(String name, String email, String address, String manager_password, String phone_number) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.manager_password = manager_password;
		this.phone_number = phone_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getManager_password() {
		return manager_password;
	}

	public void setManager_password(String manager_password) {
		this.manager_password = manager_password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

}
