package com.example;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/adminpjd")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); 
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Studentdet> studentsnotify = new ArrayList<>();
        try {
        	Connection conn = Databaseconnection.getConnection();
            
        	// Single query to fetch student details and user details 
        	String sql = "SELECT s.student_id, u.email, sa.cgpa FROM students s " 
        	+ "INNER JOIN student_academics sa ON s.student_id = sa.student_id " 
        			+ "INNER JOIN users u ON s.student_id = u.user_id " 
        	+ "WHERE u.role = 'student'";
        	PreparedStatement stmt = conn.prepareStatement(sql);
        	ResultSet rs = stmt.executeQuery();
        	while (rs.next()) { 
        		Studentdet student2 = new Studentdet();
        		student2.setStudentId(rs.getInt("student_id"));
        		student2.setEmail(rs.getString("email"));
        		student2.setCgpa(rs.getBigDecimal("cgpa"));
        		studentsnotify.add(student2);
        		}

        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    String json = new Gson().toJson(studentsnotify);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(json);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
         
	}
	private class Studentdet {
	    private int studentId;
	    private String email;
	    private BigDecimal cgpa;

	    // Getters and setters
	    int getStudentId() {
	        return studentId;
	    }

	    void setStudentId(int studentId) {
	        this.studentId = studentId;
	    }

	     String getEmail() {
	        return email;
	    }

	    void setEmail(String email) {
	        this.email = email;
	    }

	    BigDecimal getCgpa() {
	        return cgpa;
	    }

	     void setCgpa(BigDecimal cgpa) {
	        this.cgpa = cgpa;
	    }
	}


}
