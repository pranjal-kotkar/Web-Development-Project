package com.example;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class JDServlet
 */
@WebServlet("/JDServlet")
public class JDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JDServlet() {
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
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String companyName = request.getParameter("companyName");
        String contactPerson = request.getParameter("contactPerson");
        String contactEmail = request.getParameter("contactEmail");
        String details = request.getParameter("details");
        String stipend = request.getParameter("stipend");
        String eligibleBranches = request.getParameter("eligibleBranches");
        String location = request.getParameter("location");
        String scheduleDate = request.getParameter("scheduleDate");
        String formLink = request.getParameter("googleFormLink");
        System.out.println(formLink);
        try {
        	Connection conn = Databaseconnection.getConnection();
            String sql = "INSERT INTO companies (company_name, contact_person, contact_email, details, stipend, eligible_branches, location, schedule_date, formLink) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, companyName);
            statement.setString(2, contactPerson);
            statement.setString(3, contactEmail);
            statement.setString(4, details);
            statement.setString(5, stipend);
            statement.setString(6, eligibleBranches);
            statement.setString(7, location);
            statement.setString(8, scheduleDate);
            statement.setString(9, formLink);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                response.getWriter().write("{\"message\": \"Job description submitted successfully!\"}");
            } else {
                response.getWriter().write("{\"message\": \"Failed to submit job description.\"}");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"message\": \"An error occurred: " + e.getMessage() + "\"}");
        }
	}

}





   
