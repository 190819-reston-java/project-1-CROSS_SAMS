package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.Manager;
import com.revature.repository.ConnectionUtility;


public class Doa{
	String NAME = "NAME";
	String PASSWORD = "PASSWORD";
	String EMAIL = "EMAIL";
	String Address = "Address";
	Manager manager = null;

		public  Manager loginManager(String email, String password) throws SQLException {
			try {
			String sQl = "SELECT * MANAGER_TABLE WHERE EMAIL  = ? AND MANAGER_PASSWORD = ? ;";
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
			}
		
