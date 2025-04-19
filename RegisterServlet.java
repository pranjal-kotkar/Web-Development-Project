package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.google.gson.Gson;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); 
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		request.setCharacterEncoding("UTF-8");
		
		String userIdstr = request.getParameter("userid");
		int userId = Integer.parseInt(userIdstr);
		String name = request.getParameter("fullName"); 
		String email = request.getParameter("email"); 
		String cnum = request.getParameter("academicNumber"); 
		
		 
		String contact = request.getParameter("contact");
		String cgpa = request.getParameter("cgpa");
		String percentage_10th = request.getParameter("percentage10th"); 
		String percentage_12th = request.getParameter("percentage12th"); 
		String deadBacklogs = request.getParameter("deadBacklogs"); 
		String liveBacklogs = request.getParameter("liveBacklogs");
		String branch = request.getParameter("branch");
		Part profilePicturePart = request.getPart("profilePicture");
		
		
		
		System.out.println(userId);
		System.out.println(name);
		System.out.println(email);
		System.out.println(cgpa);
		System.out.println(contact);
		System.out.println(percentage_10th);
		System.out.println(percentage_12th);
		InputStream profilePictureInputStream = null;
		if (profilePicturePart != null) 
		{ profilePictureInputStream = profilePicturePart.getInputStream();
		 }
		try {Connection conn = Databaseconnection.getConnection();
			// Insert into students table
			String sqlStudents = "INSERT INTO students (user_id, profile_details) VALUES (?, ?)"; 
			PreparedStatement statementStudents = conn.prepareStatement(sqlStudents, PreparedStatement.RETURN_GENERATED_KEYS);
			statementStudents.setInt(1, userId);
			statementStudents.setString(2, new Gson().toJson(new HashMap<String, String>()
			{{ put("name", name); put("email", email); put("contact", contact); 
			}})); 
			statementStudents.executeUpdate();
			// Get the generated student_id
			ResultSet generatedKeys = statementStudents.getGeneratedKeys();
			
			int studentId = 0;
			if (generatedKeys.next())
			{ studentId = generatedKeys.getInt(1); } 
			// Insert into student_academics table
			String sqlAcademics = "INSERT INTO student_academics (student_id, live_backlogs, dead_backlogs, cgpa, percentage_10th, percentage_12th, branch_name, profile_picture, contact_no) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statementAcademics = conn.prepareStatement(sqlAcademics); 
			statementAcademics.setInt(1, studentId);
			statementAcademics.setInt(2, Integer.parseInt(liveBacklogs));
			statementAcademics.setInt(3, Integer.parseInt(deadBacklogs));
			statementAcademics.setBigDecimal(4, new BigDecimal(cgpa));
			statementAcademics.setBigDecimal(5, new BigDecimal(percentage_10th));
			statementAcademics.setBigDecimal(6, new BigDecimal(percentage_12th));
			statementAcademics.setString(7, branch);
			if (profilePictureInputStream != null)
			{ statementAcademics.setBlob(8, profilePictureInputStream); } 
			statementAcademics.setString(9, contact);
			int rowAcademics = statementAcademics.executeUpdate();
			
			
			if (rowAcademics > 0) { response.getWriter().println("Registration successful!"); }
			conn.close();
		}
		catch (Exception e)
		{ e.printStackTrace();
		response.getWriter().println("Error: " + e.getMessage()); 
		}
		 
	}

}
