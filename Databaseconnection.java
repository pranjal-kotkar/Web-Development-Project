package com.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Databaseconnection {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/react";
	static final String USER = "root";
	static final String PASS = "Mysql@123";
	Connection conn = null;
	public static Connection getConnection() throws SQLException
	{    try { Class.forName(JDBC_DRIVER);
		}
	catch(ClassNotFoundException e) {
		e.printStackTrace();
	}
	return DriverManager.getConnection(URL, USER, PASS);
	}
	
	 public List<Company> getAllCompanies() {
	        List<Company> companies = new ArrayList<>();
	        String query = "SELECT * FROM companies";

	        try {
	        		Class.forName(JDBC_DRIVER);
	        		conn=DriverManager.getConnection(URL,USER,PASS);
	        		System.out.println("Welcome to TNP Database");
	        		
	             PreparedStatement statement = conn.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery() ;

	            while (resultSet.next()) {
	            	Company company = new Company();
	            	company.setGoogleFormLink(resultSet.getString("formLink"));
	            	company.setCompanyId(resultSet.getInt("company_id"));
	            	company.setCompanyName(resultSet.getString("company_name"));
	            	company.setContactPerson(resultSet.getString("contact_person"));
	            	company.setContactEmail(resultSet.getString("contact_email"));
	            	company.setDetails(resultSet.getString("details"));
	            	company.setStipend(resultSet.getDouble("stipend")); 
	            	company.setEligibleBranches(resultSet.getString("eligible_branches"));
	            	company.setLocation(resultSet.getString("location"));
	            	company.setScheduleDate(resultSet.getDate("schedule_date"));
	            	company.setCreatedAt(resultSet.getTimestamp("created_at"));
	            	company.setCompanyImage(resultSet.getString("company_image"));
	            	company.setCgpaCriteria(resultSet.getDouble("cgpa_criteria")); 
	            	
	            	companies.add(company);
	            	
	            }
	        } 
	        catch(ClassNotFoundException e) {
	    		e.printStackTrace();
	    	}
	        catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return companies;
	    }
	 }

