package com.revature.model;

public class Reimbursement {
	
	int id;
	String reason;
	double amount;
	String date;

	public Reimbursement(int id, String reason, double amount, String date) {
		super();
		this.id = id;
		this.reason = reason;
		this.amount = amount;
		this.date = date;
	}
	
	public Reimbursement() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", reason=" + reason + ", amount=" + amount + ", date=" + date + "]";
	}



	
	
	
	
	
	
	

}
