package model;

public class Employee {
	
	String EmployeeName;
	String Email;
	String Adress;
	String Password;
	String PhoneNumber;

	
	public Employee( String empoloyeeName, String email, String adress, String password,String phoneNumber) {
		this.EmployeeName = empoloyeeName;
		this.Email = email;
		this.Adress = adress;
		this.Password = password;
		this.PhoneNumber = phoneNumber;
	}
	
	public String getEmpoloyeeName() {
		return EmployeeName;
	}
	public void setEmpoloyeeName(String empoloyeeName) {
		EmployeeName = empoloyeeName;
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

}
