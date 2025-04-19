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
import java.util.List;

import com.google.gson.Gson;

/**
 * Servlet implementation class ViewApplicationServlet
 */
@WebServlet("/appliedstudents")
public class ViewApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); 
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); 
			 String compId = request.getParameter("companyId");
			 List<StudentProfile> students = new ArrayList<>();
			 try {
		        	Connection conn = Databaseconnection.getConnection();
					int companyId= Integer.parseInt(compId);
		             String sql ="SELECT s.student_id, s.profile_details " +
		             "FROM students s " + "INNER JOIN applications a "
		             		+ "ON s.student_id = a.student_id " + "WHERE a.company_id = ?";
		             
		            PreparedStatement statement = conn.prepareStatement(sql);
		            statement.setInt(1, companyId);
		            ResultSet resultSet = statement.executeQuery();

		            while (resultSet.next()) {
		                StudentProfile student = new StudentProfile();
		                student.setStudentId(resultSet.getInt("student_id"));
		                student.setProfileDetails(resultSet.getString("profile_details"));
		                students.add(student);
		            }

		            conn.close();
		        } catch (Exception e) {
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