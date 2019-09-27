package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.repository.ConnectionUtility;


public class Doa{
	String NAME = "NAME";
	String PASSWORD = "PASSWORD";
	String EMAIL = "EMAIL";
	String Address = "Address";
	public String sQl = "SELECT * MANAGER_TABLE WHERE EMAIL  = ? AND MANAGER_PASSWORD = ? ;";
	Manager manager = null;
	Employee employee = null;

		public  Manager loginManager(String email, String password) throws SQLException {
			try {
				ResultSet resultset = null;
				try (Connection connection = ConnectionUtility.getConnection(); 
					PreparedStatement preparedstatement = connection.prepareStatement(sQl);) {
					preparedstatement.setString(1, email);
					preparedstatement.setString(2, password);
					
						if(preparedstatement.execute()) {
							resultset = preparedstatement.getResultSet();
							while(resultset.next()) {
						manager = new Manager(
						resultset.getString(NAME),
						resultset.getString(EMAIL),
						resultset.getString(Address),
						resultset.getString(PASSWORD));
					}
						}
					StreamCloser.close(preparedstatement);
					StreamCloser.close(resultset);
					}
						}catch(SQLException e) {
							e.printStackTrace();
						}
					return manager;
				}
		public  Employee loginEmployee(String email, String password) throws SQLException {
			try {
				ResultSet resultset = null;
				try (Connection connection = ConnectionUtility.getConnection(); 
					PreparedStatement preparedstatement = connection.prepareStatement(sQl);) {
					preparedstatement.setString(1, email);
					preparedstatement.setString(2, password);
					
						if(preparedstatement.execute()) {
							resultset = preparedstatement.getResultSet();
							while(resultset.next()) {
						manager = new Manager(
						resultset.getString(NAME),
						resultset.getString(EMAIL),
						resultset.getString(Address),
						resultset.getString(PASSWORD));
					}
						}
					StreamCloser.close(preparedstatement);
					StreamCloser.close(resultset);
					}
						}catch(SQLException e) {
							e.printStackTrace();
						}
					return employee;
				}
		
			}

		public boolean nReimbursement(Reimbursement r) {
			Connection conn = null;
			PreparedStatement stmt = null;
			
			String query = "INSERT INTO eDatabase VALUES (DEFAULT, ?, ?, ?);";
			
			try {
				conn = connectionUtility.getConnection();
				stmt = conn.prepareStatement(query);
				stmt.setString(1, r.getReason());
				stmt.setString(2, r.getAmount());
				stmt.setString(3, r.getDate());
				stmt.execute();
			} catch (SQLException r) {
				e.printStackTrace();
				return false;
			} finally {
				StreamCloser.close(stmt);
				StreamCloser.close(conn);
			}
			
			return true;
		}
		
