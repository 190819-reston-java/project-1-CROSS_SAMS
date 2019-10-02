package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Manager;
import model.Reimbursement;
import model.Employee;
import repository.ConnectionUtility;

public class Dao{
	
	String NAME = "NAME";
	String MANAGER_PASSWORD = "manager_password";
	String EMPLOYEE_PASSWORD = "employee_password";
	String EMAIL = "email";
	String ADDRESS = "address";
	String PHONE_NUMBER = "phone_number";
	String MANAGERSQL = "SELECT * FROM public.manager_table WHERE email = ? AND manager_password = ?;";
	String EMPLOYEESQL = "SELECT * FROM public.employee_table WHERE email = ? AND employee_password = ?;";
	String TOTAL_EMPLOYEES = "SELECT * FROM public.employee_table;";
	Manager manager = null;
	Employee employee = null;
	Reimbursement reimbursement = null;
	ResultSet resultset = null;
	

		public  Manager loginManager(String email, String password) throws SQLException {
				try (Connection connection = ConnectionUtility.getConnection(); 
					PreparedStatement preparedstatement = connection.prepareStatement(MANAGERSQL);) {
					preparedstatement.setString(1, email);
					preparedstatement.setString(2, password);

						if(preparedstatement.execute()) {
							resultset = preparedstatement.getResultSet();
							while(resultset.next()) {
						manager = new Manager(
						resultset.getString(NAME),
						resultset.getString(EMAIL),
						resultset.getString(ADDRESS),
						resultset.getString(MANAGER_PASSWORD),
						resultset.getString(PHONE_NUMBER));
					}
						}
					StreamCloser.close(preparedstatement);
					StreamCloser.close(resultset);
					} catch (SQLException e) {
						e.printStackTrace();
					}
						
					return manager;
				}
		
		public  Employee loginEmployee(String email, String password) throws SQLException {
				try (Connection connection = ConnectionUtility.getConnection(); 
					PreparedStatement preparedstatement = connection.prepareStatement(EMPLOYEESQL);) {
					preparedstatement.setString(1, email);
					preparedstatement.setString(2, password);

						if(preparedstatement.execute()) {
							resultset = preparedstatement.getResultSet();
							while(resultset.next()) {
						employee = new Employee(
						resultset.getString(NAME),
						resultset.getString(EMAIL),
						resultset.getString(ADDRESS),
						resultset.getString(EMPLOYEE_PASSWORD),
						resultset.getString(PHONE_NUMBER));
					}
						}
					StreamCloser.close(preparedstatement);
					StreamCloser.close(resultset);
					}
						catch(SQLException e) {
							e.printStackTrace();
						}
					return employee;
				}	
		
		public  List<Employee> totalEmployees() throws SQLException {
				List<Employee> employees = new ArrayList<Employee>();
				try (Connection connection = ConnectionUtility.getConnection(); 
					PreparedStatement preparedstatement = connection.prepareStatement(TOTAL_EMPLOYEES);) {
						if(preparedstatement.execute()) {
							resultset = preparedstatement.getResultSet();
							while(resultset.next()) {
						employee = new Employee(
						resultset.getString(NAME),
						resultset.getString(EMAIL),
						resultset.getString(ADDRESS),
						resultset.getString(EMPLOYEE_PASSWORD),
						resultset.getString(PHONE_NUMBER));
						employees.add(employee);
					}
						}
					StreamCloser.close(preparedstatement);
					StreamCloser.close(resultset);
					}
						catch(SQLException e) {
							e.printStackTrace();
						}
					return employees;
				}
		
		//this method can be entered into your current DOA class...
		//allows an employee to view their current outstanding reimbursements...
		public Reimbursement getCurrentEmployeeReimbursements(String employee_session_email) {
			String SQL = "Select * From public.tdatabase where employee_email = " + employee_session_email + ";";
			try (Connection connection = ConnectionUtility.getConnection(); 
					PreparedStatement preparedstatement = connection.prepareStatement(SQL);) {
						if(preparedstatement.execute()) {
							resultset = preparedstatement.getResultSet();
							while(resultset.next()) {
						reimbursement = new Reimbursement(
						resultset.getInt("id"),
						resultset.getString("reason"),
						resultset.getDouble("amount"),
						resultset.getString("date"),
						resultset.getString("employee"),
						resultset.getString("status"),
						resultset.getString("manager"));
					}
						}
					StreamCloser.close(preparedstatement);
					StreamCloser.close(resultset);
					}
						catch(SQLException e) {
							e.printStackTrace();
						}
					return reimbursement;
				}
		public Reimbursement updateEmployeeReimbursements(String employeeEmail) {
			String SQL = "Select * From public.tdatabase where employee_email = ? ;";
			try (Connection connection = ConnectionUtility.getConnection(); 
					PreparedStatement preparedstatement = connection.prepareStatement(SQL);) {
						if(preparedstatement.execute()) {
							resultset = preparedstatement.getResultSet();
							while(resultset.next()) {
						reimbursement = new Reimbursement(
						resultset.getInt("id"),
						resultset.getString("reason"),
						resultset.getDouble("amount"),
						resultset.getString("date"),
						resultset.getString("employee"),
						resultset.getString("status"),
						resultset.getString("manager"));
					}
						}
					StreamCloser.close(preparedstatement);
					StreamCloser.close(resultset);
					}
						catch(SQLException e) {
							e.printStackTrace();
						}
					return reimbursement;
				}
		
		public  List<Reimbursement> totalReimbursements() throws SQLException {
			List<Reimbursement> totalReimbursements = new ArrayList<Reimbursement>();
			String TOTAL_REIMBURSEMENT = "SELECT * FROM public.tdatabase;";
			try (Connection connection = ConnectionUtility.getConnection(); 
				PreparedStatement preparedstatement = connection.prepareStatement(TOTAL_REIMBURSEMENT);) {
					if(preparedstatement.execute()) {
						resultset = preparedstatement.getResultSet();
						while(resultset.next()) {
					reimbursement = new Reimbursement(
					resultset.getInt("id"),
					resultset.getString("reason"),
					resultset.getDouble("amount"),
					resultset.getString("date"),
					resultset.getString("employee"),
					resultset.getString("status"),
					resultset.getString("manager"));
					totalReimbursements.add(reimbursement);
				}
					}
				StreamCloser.close(preparedstatement);
				StreamCloser.close(resultset);
				}
					catch(SQLException e) {
						e.printStackTrace();
					}
				return totalReimbursements;
			}
		
		//the parameter email within this function is derrived from the session get parameter
		//within the login Java Servlet File...The example is here...
		//	HttpSession session = request.getSession(true);
        //	session.setAttribute("user", employeeEmail);
		
		public boolean updateEmployeeAccount(Employee employee, String sessionEmail) {
			PreparedStatement preparedstatement = null;

			final String SQL = "UPDATE public.employee_table SET name = ?, "
					+ "email = ?, address= ?, phone_number=? WHERE email=" + sessionEmail +";";
			try (Connection connection = ConnectionUtility.getConnection()) {
				preparedstatement = connection.prepareStatement(SQL);
				preparedstatement.setString(1, employee.getName());
				preparedstatement.setString(2, employee.getEmail());
				preparedstatement.setString(3, employee.getAddress());
				preparedstatement.setString(4, employee.getPhone_number());
				preparedstatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				StreamCloser.close(preparedstatement);
				StreamCloser.close(resultset);
			}
			return true;
		}
		
		public boolean updateManagerAccount(Manager manager, String sessionEmail) {
			PreparedStatement preparedstatement = null;

			final String SQL = "UPDATE public.manager_table SET name = ?, "
					+ "email = ?, address= ?, phone_number=? WHERE email=" + sessionEmail +";";
			try (Connection connection = ConnectionUtility.getConnection()) {
				preparedstatement = connection.prepareStatement(SQL);
				preparedstatement.setString(1, manager.getName());
				preparedstatement.setString(2, manager.getEmail());
				preparedstatement.setString(3, manager.getAddress());
				preparedstatement.setString(4, manager.getPhone_number());
				preparedstatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				StreamCloser.close(preparedstatement);
				StreamCloser.close(resultset);
			}
			return true;
		}
	
	}