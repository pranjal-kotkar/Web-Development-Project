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
 * Servlet implementation class ShortlistServlet
 */
@WebServlet("/shortlist")
public class ShortlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShortlistServlet() {
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
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); 
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); 
		 String compid = request.getParameter("companyId");
	        String studid = request.getParameter("studentId");
	        String jobStatus = request.getParameter("jobStatus");
	        boolean selected = Boolean.parseBoolean(request.getParameter("selected"));
	        int sele=0;
	        if (selected ==true) {
	        	 sele=1;
	        }
	        
	        System.out.println(compid+" "+studid+" "+selected);
	        try {
	        	Connection conn = Databaseconnection.getConnection();
				int studentId = Integer.parseInt(studid);
				int companyId = Integer.parseInt(compid);
	            String sql = "UPDATE applications SET application_status = ?, selected = ? " +
	                         "WHERE student_id = ? " +
	                         "AND company_id = ?";
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setString(1, jobStatus);
	            statement.setInt(2, sele);
	            statement.setInt(3, studentId);
	            statement.setInt(4, companyId);

	            int rowsUpdated = statement.executeUpdate();
	            JsonObject jsonResponse = new JsonObject();
	            if (rowsUpdated > 0) {
	                jsonResponse.addProperty("message", "Shortlist updated successfully");
	            } else {
	                jsonResponse.addProperty("message", "Failed to update shortlist");
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
