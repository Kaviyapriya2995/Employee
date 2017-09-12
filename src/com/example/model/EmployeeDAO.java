package com.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
    public EmployeeDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
    
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
    
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
    public boolean insertEmp(Employee emp) throws SQLException {
        String sql = "INSERT INTO employee_details (emp_name,emp_contact,emp_address,emp_designation) VALUES (?, ?, ?,?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, emp.getName());
        statement.setString(2, emp.getContact());
        statement.setString(3, emp.getAddress());
        statement.setString(4, emp.getDesgn());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Employee> listAllEmployees() throws SQLException {
        List<Employee> listBook = new ArrayList<>();
         
        String sql = "SELECT * FROM employee_details";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("emp_id");
            String name = resultSet.getString("emp_name");
            String contact = resultSet.getString("emp_contact");
            String address = resultSet.getString("emp_address");
            String desgn = resultSet.getString("emp_designation");
            Employee emp = new Employee(id,name, contact, address,desgn);
            listBook.add(emp);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listBook;
    }
     
    public boolean deleteBook(Employee emp) throws SQLException {
        String sql = "DELETE FROM employee_details where emp_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, emp.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        if(rowDeleted)
        {
        	String alter_sql="ALTER TABLE employee_details AUTO_INCREMENT = 1";
        	  PreparedStatement statement1 = jdbcConnection.prepareStatement(alter_sql);
        	  boolean rowDeleted1 = statement1.executeUpdate() > 0;
        	  System.out.println("rowDeleted1"+rowDeleted1);
        	  statement1.close();
        }
        
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateBook(Employee emp) throws SQLException {
        String sql = "UPDATE employee_details SET emp_name = ?, emp_contact = ?, emp_address = ?, emp_designation = ?";
        sql += " WHERE emp_id = ?";
        connect();
      
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, emp.getName());
        statement.setString(2, emp.getContact());
        statement.setString(3, emp.getAddress());
        statement.setString(4, emp.getDesgn());
        statement.setInt(5, emp.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
      
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Employee getEmployee(int id) throws SQLException {
    	Employee emp = null;
        String sql = "SELECT * FROM employee_details WHERE emp_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("emp_name");
            String contact= resultSet.getString("emp_contact");
            String address= resultSet.getString("emp_address");
            String desgn = resultSet.getString("emp_designation");
            emp = new Employee(id,name, contact, address,desgn);
        }
         
        resultSet.close();
        statement.close();
         
        return emp;
    }
    
    
    public String loginCheck(Login obj) throws SQLException {
    	
        String sql = "SELECT * FROM users WHERE username = ? AND password=?";
         String uname=obj.getUname();
         String pwd=obj.getPwd();
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, uname);
        statement.setString(2, pwd);
        ResultSet resultSet = statement.executeQuery();
      
        if (resultSet.next()) {
        	  String result = resultSet.getString("user_role");
        	  System.out.println(result);
        	  resultSet.close();
              statement.close();
            return result;
        }
        else
        {
        	  resultSet.close();
              statement.close();
        	return "false";
        }
         
       
    }
}
