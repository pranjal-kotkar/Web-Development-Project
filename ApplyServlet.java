package com.example;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.google.gson.JsonObject;

/**
 * Servlet implementation class ApplyServlet
 */
@WebServlet("/apply")
public class ApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyServlet() {
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
		// Set CORS headers 
				response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); 
				response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
				response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
				
		 int studentId = Integer.parseInt(request.getParameter("studentId"));
	        int companyId = Integer.parseInt(request.getParameter("companyId"));
	        
	        System.out.println("Applied by: "+ studentId);
	        System.out.println("Applied to: "+ companyId);
	        try {
	        	Connection conn = Databaseconnection.getConnection();
	            String sql = "INSERT INTO applications (student_id, company_id) VALUES (?, ?)";
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setInt(1, studentId);
	            statement.setInt(2, companyId);

	            int rowsInserted = statement.executeUpdate();
	            JsonObject jsonResponse = new JsonObject();
	            if (rowsInserted > 0) {
	                jsonResponse.addProperty("message", "Application submitted successfully");
	            } else {
	                jsonResponse.addProperty("message", "Failed to submit application");
	            }

	            conn.close();
	            response.getWriter().write(jsonResponse.toString());
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            JsonObject jsonResponse = new JsonObject();
	            jsonResponse.addProperty("message", "Error: " + e.getMessage());
	            response.getWriter().write(jsonResponse.toString());
	        }

	}

}




