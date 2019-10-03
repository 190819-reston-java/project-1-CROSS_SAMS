package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Manager;
import com.revature.model.Reimbursement;
//import com.revature.model.Player;
import com.revature.model.User;
import com.revature.utils.Close;
import com.revature.utils.connectionUtility;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

public class Project1DAO implements Project1 {
	User user = null;
	Manager manager = null;
	ResultSet  resultset = null;

	@Override
	public User getUser(int id) {
		User user = null;

		try (Connection conn = connectionUtility.getConnection()) {
			String query = "SELECT * FROM User_table WHERE id = ?;";

			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setInt(1, id);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							user = createUserFromRS(resultSet);
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();

			return user;
		}
	}
	
	public Reimbursement getReimbursement(int id) {
		Reimbursement reimbursement = null;

		try (Connection conn = connectionUtility.getConnection()) {
			String query = "SELECT * FROM tdatabase WHERE id = ?;";

			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, id);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							reimbursement = createReimbursementFromRS(resultSet);
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return reimbursement;
		} catch (SQLException e) {
			e.printStackTrace();

			return reimbursement;
		}
	}
	
	public Reimbursement getResolvedReimbursement(int id) {
		Reimbursement reimbursement = null;

		try (Connection conn = connectionUtility.getConnection()) {
			String query = "SELECT * FROM tdatabase WHERE id = ?;";

			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, id);
				if (stmt.execute()) {
					try (ResultSet resultSet = stmt.getResultSet()) {
						if (resultSet.next()) {
							reimbursement = createReimbursementFromRS(resultSet);
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return reimbursement;
		} catch (SQLException e) {
			e.printStackTrace();

			return reimbursement;
		}
	}
	
	private User createUserFromRS(ResultSet resultSet) throws SQLException {
		return new User(
				resultSet.getInt("id"),
				resultSet.getString("name"),
				resultSet.getString("email"),
				resultSet.getString("address"),
				resultSet.getString("password"),
				resultSet.getString("phone")
				);
	}
	
	private Reimbursement createReimbursementFromRS(ResultSet resultSet) throws SQLException {
		return new Reimbursement(
				resultSet.getInt("id"),
				resultSet.getString("reason"),
				resultSet.getDouble("amount"),
				resultSet.getString("date"),
				resultSet.getString("status"),
				resultSet.getString("employee"),
				resultSet.getString("manager")
				);
	}

	@Override
	public User getUser(String email) {
		ResultSet resultSet = null;
		//prepared statements are better than simple ones
		PreparedStatement statement = null;
		
		User user = null;
		
		//try-with-resources
		//any resource that is AutoClosable (an interface) can be used with try (resource) {} and it will close itself
		try (Connection conn = connectionUtility.getConnection()) {
			statement = conn.prepareStatement(
					"SELECT * FROM User_table WHERE email = ?;");
			//in our PreparedStatement, we set values to be filled in later with ?. We'll set those values using the "index" of the ?, starting at 1
			
			//fill in the question mark with name argument
			statement.setString(1, email);
			
			//try to execute SQL query
			if(statement.execute()) {
				//get the ResultSet
				resultSet = statement.getResultSet();
				//check for a single
				if(resultSet.next()) {
					user = new User(
							resultSet.getInt("id"),
							resultSet.getString("name"),
							resultSet.getString("email"),
							resultSet.getString("address"),
							resultSet.getString("password"),
							resultSet.getString("phone")
							);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			Close.close(resultSet);
			Close.close(statement);
		}
		return user;
	}
	
	public Reimbursement getReimbursement(String reason) {
		ResultSet resultSet = null;
		//prepared statements are better than simple ones
		PreparedStatement statement = null;
		
		Reimbursement reimbursement = null;
		
		//try-with-resources
		//any resource that is AutoClosable (an interface) can be used with try (resource) {} and it will close itself
		try (Connection conn = connectionUtility.getConnection()) {
			statement = conn.prepareStatement(
					"SELECT * FROM tdatabase WHERE reason = ?;");
			//in our PreparedStatement, we set values to be filled in later with ?. We'll set those values using the "index" of the ?, starting at 1
			
			//fill in the question mark with name argument
			statement.setString(1, reason);
			
			//try to execute SQL query
			if(statement.execute()) {
				//get the ResultSet
				resultSet = statement.getResultSet();
				//check for a single
				if(resultSet.next()) {
					reimbursement = createReimbursementFromRS(resultSet);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			Close.close(resultSet);
			Close.close(statement);
		}
		return reimbursement;
	}
	
	public Reimbursement getResolvedReimbursement(String status) {
		ResultSet resultSet = null;
		//prepared statements are better than simple ones
		PreparedStatement statement = null;
		
		Reimbursement reimbursement = null;
		
		//try-with-resources
		//any resource that is AutoClosable (an interface) can be used with try (resource) {} and it will close itself
		try (Connection conn = connectionUtility.getConnection()) {
			statement = conn.prepareStatement(
					"SELECT * FROM tdatabase WHERE status != '';");
			//in our PreparedStatement, we set values to be filled in later with ?. We'll set those values using the "index" of the ?, starting at 1
			
			//fill in the question mark with name argument
			statement.setString(1, status);
			
			//try to execute SQL query
			if(statement.execute()) {
				//get the ResultSet
				resultSet = statement.getResultSet();
				//check for a single
				if(resultSet.next()) {
					reimbursement = createReimbursementFromRS(resultSet);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			Close.close(resultSet);
			Close.close(statement);
		}
		return reimbursement;
	}
	 
	@Override
	public List<User> getUsers() {
		//statement and resultSet interfaces
				Statement statement = null;
				ResultSet resultSet = null;
				Connection conn = null;
				
				//list to return
				List<User> users = new ArrayList<User>();
				
				try {
					//get connection from ConnectionUtil
					conn = connectionUtility.getConnection();
							
					//create statement from connection
					statement = conn.createStatement();
					
					//Statements can execute SQL queries
					//ResultSet stores the results of a query
					resultSet = statement.executeQuery("Select * FROM employee_table;");
							
					//loop through resultSet		
					while (resultSet.next()) {
						//At each row in the ResultSet, do the following:
						users.add(new User(
								resultSet.getInt("id"),
								resultSet.getString("name"),
								resultSet.getString("email"),
								resultSet.getString("address"),
								resultSet.getString("password"),
								resultSet.getString("phone")
								));
					}
							
							
							
							
							
				}catch (SQLException e) {
					e.printStackTrace();
				}finally {
					//close all open resources to prevent memory leak
					Close.close(resultSet);
					Close.close(statement);
					Close.close(conn);
				}
				
				return users;
	}
	
	public List<Reimbursement> getReimbursements() {
		Statement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		
		//list to return
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try {
			//get connection from ConnectionUtil
			conn = connectionUtility.getConnection();
					
			//create statement from connection
			statement = conn.createStatement();
			
			//Statements can execute SQL queries
			//ResultSet stores the results of a query
			resultSet = statement.executeQuery("Select * FROM tdatabase WHERE status IS NULL");
					
			//loop through resultSet		
			while (resultSet.next()) {
				//At each row in the ResultSet, do the following:
//				reimbursements.add(new Reimbursement(
//						resultSet.getInt("id"),
//						resultSet.getString("Reason"),
//						resultSet.getDouble("Amount"),
//						resultSet.getString("Date"),
//						resultSet.getString("status")
//						));
				reimbursements.add(createReimbursementFromRS(resultSet));
			}
					
					
					
					
					
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//close all open resources to prevent memory leak
			Close.close(resultSet);
			Close.close(statement);
			Close.close(conn);
		}
		
		return reimbursements;
	}
	
	
	public List<Reimbursement> getResolvedReimbursements() {
		Statement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try {
			conn = connectionUtility.getConnection();
					
			statement = conn.createStatement();
			
			resultSet = statement.executeQuery("Select * FROM tdatabase WHERE status != '';");
							
			while (resultSet.next()) {

				reimbursements.add(createReimbursementFromRS(resultSet));
			}		
					
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Close.close(resultSet);
			Close.close(statement);
			Close.close(conn);
		}
		
		return reimbursements;
	}
	
//	public List<Reimbursement> getReimbursementByEmployeeEmail(String sessionEmail) throws HTTPException {
//		
//		Statement statement = null;
//		ResultSet resultSet = null;
//		Connection conn = null;
//		
//		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
//		
//		String SQL = "SELECT * FROM tdatabase WHERE  employee = ? ";
//		
//		String ALLREIMBURSEMEENTSSQL = "SELECT * FROM tdatabase";
//		try(Connection connection = connectionUtility.getConnection()) {
//			PreparedStatement preparedstatement;
//			if(sessionEmail != null ) {
//			    preparedstatement = connection.prepareStatement(SQL);
//				preparedstatement.setString(1,  sessionEmail);	
//			}
//			else {
//				preparedstatement = connection.prepareStatement(ALLREIMBURSEMEENTSSQL);
//			}
//			resultset = preparedstatement.executeQuery();
//			while(resultset.next()) {
////				reimbursement = new Reimbursement(
////						resultset.getInt("id"),
////						resultset.getString("reason"),
////						resultset.getDouble("amount"),
////						resultset.getString("date"),
////						resultset.getString("employee"),
////						resultset.getString("status"),
////						resultset.getString("manager"));
////				reimbursements.add(reimbursement);
//				reimbursements.add(createReimbursementFromRS(resultSet));
//			}
//		} catch(SQLException e) {
//			e.printStackTrace();
////			throw new HTTPException("400");
//		}
//		
//		return reimbursements;
//	}

	@Override
	public boolean createUser(User u) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		final String query = "INSERT INTO User_table VALUES (DEFAULT, ?, ?, ?, ?, ?);";
		
		try {
			conn = connectionUtility.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, u.getName());
			stmt.setString(2, u.getEmail());
			stmt.setString(3, u.getAddress());
			stmt.setString(4, u.getPassword());
			stmt.setString(5, u.getPhone());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			Close.close(stmt);
			Close.close(conn);
		}
		
		return true;
	}
	
	public boolean createReimbursement (Reimbursement r) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		final String query = "INSERT INTO tdatabase VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";
		
		try {
			conn = connectionUtility.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, r.getReason());
			stmt.setDouble(2, r.getAmount());
			stmt.setString(3, r.getDate());
			stmt.setString(4, r.getEmployee());
			stmt.setString(5, r.getStatus());
			stmt.setString(6, r.getManager());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			Close.close(stmt);
			Close.close(conn);
		}
		
		return true;
	}

	@Override
	public boolean updateUser(User u) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		final String query = "UPDATE User_table SET name = ?, email = ?, address = ?, password = ?, phone = ? WHERE id = ?;";
		
		try {
			conn = connectionUtility.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, u.getName());
			stmt.setString(2, u.getEmail());
			stmt.setString(3, u.getAddress());
			stmt.setString(4, u.getPassword());
			stmt.setString(5, u.getPhone());
			stmt.setInt(6, u.getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Close.close(stmt);
			Close.close(conn);
		}
		return true;
	}
	
	@Override
	public boolean updateReimbursement(Reimbursement r) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		final String query = "UPDATE tdatabase SET reason = ?, amount = ?, date = ?, employee = ?, status = ?, manager = ? WHERE id = ?;";
		
		try {
			conn = connectionUtility.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, r.getReason());
			stmt.setDouble(2, r.getAmount());
			stmt.setString(3, r.getDate());
			stmt.setString(4, r.getEmployee());
			stmt.setString(5, r.getStatus());
			stmt.setString(6, r.getManager());
			stmt.setInt(7, r.getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Close.close(stmt);
			Close.close(conn);
		}
		return true;
	}
	
	

	
	public  Manager loginManager(String email, String password) throws SQLException {
		String MANAGERSQL = "SELECT * FROM manager_table WHERE email = ? AND password = ?;";
		try (Connection connection = connectionUtility.getConnection();
		PreparedStatement preparedstatement = connection.prepareStatement(MANAGERSQL);) {
		preparedstatement.setString(1, email);
		preparedstatement.setString(2, password);

		if(preparedstatement.execute()) {
		resultset = preparedstatement.getResultSet();
		while(resultset.next()) {
		manager = new Manager(
		resultset.getInt("id"),
		resultset.getString("name"),
		resultset.getString("email"),
		resultset.getString("address"),
		resultset.getString("password"),
		resultset.getString("phone"));
		}
		}
		Close.close(preparedstatement);
		Close.close(resultset);
		} catch (SQLException e) {
		e.printStackTrace();
		}

		return manager;
		}


	public  User loginUser(String email, String password) throws SQLException {
		String UserSQL = "SELECT * FROM employee_table WHERE email = ? AND password = ?;";
		try (Connection connection = connectionUtility.getConnection();
		PreparedStatement preparedstatement = connection.prepareStatement(UserSQL);) {
		preparedstatement.setString(1, email);
		preparedstatement.setString(2, password);

		if(preparedstatement.execute()) {
		resultset = preparedstatement.getResultSet();
		while(resultset.next()) {
		user = new User(
		resultset.getInt("id"),
		resultset.getString("name"),
		resultset.getString("email"),
		resultset.getString("address"),
		resultset.getString("password"),
		resultset.getString("phone"));
		}
		}
		Close.close(preparedstatement);
		Close.close(resultset);
		}
		catch(SQLException e) {
		e.printStackTrace();
		}
		return user;
		}


	

}
