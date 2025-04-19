package com.example;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * Servlet implementation class ShortlistedStudentsservlet
 */
@WebServlet("/shortlistedstudents")
public class ShortlistedStudentsservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShortlistedStudentsservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); 
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		request.setCharacterEncoding("UTF-8");
		 List<ShortlistedStudent> students = new ArrayList<>();
		 Map<Integer, String> branchMap = new HashMap<>();
		 try {
			 Connection conn = Databaseconnection.getConnection();
			 String sql = "SELECT a.company_id, s.student_id, s.profile_details " + "FROM students s " + "INNER JOIN applications a ON s.student_id = a.student_id " + "WHERE a.selected = 1";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
     // Query 2: Get Branch Name
        String branchNameSql = "SELECT student_id, branch_name " + "FROM student_academics";
        PreparedStatement branchNameStmt = conn.prepareStatement(branchNameSql); 
        ResultSet branchNameRs = branchNameStmt.executeQuery(); // Populate branchMap with student_id and branch_name 
        while (branchNameRs.next()) {
        	branchMap.put(branchNameRs.getInt("student_id"), branchNameRs.getString("branch_name")); }

        while (resultSet.next()) {
            ShortlistedStudent student = new ShortlistedStudent();
            student.setCompanyId(resultSet.getInt("company_id"));
            student.setStudentId(resultSet.getInt("student_id"));
            student.setProfileDetails(resultSet.getString("profile_details"));
            student.setBranchName(branchMap.get(resultSet.getInt("student_id")));
            students.add(student);
        }

        conn.close();
		 }
		 catch (Exception e) {
	            e.printStackTrace();
	        }

	        String json = new Gson().toJson(students);
	        response.getWriter().write(json);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}



