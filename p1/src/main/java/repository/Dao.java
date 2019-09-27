package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Manager;
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
	String TOTAL_EMPLOYEES = "Select * FROM public.employee_table ;";
	Manager manager = null;
	Employee employee = null;
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
		
		public  List<Employee> Employees() throws SQLException {
				ArrayList<Employee> employees = new ArrayList<Employee>();
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
	
			}