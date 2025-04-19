package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class ScheduleServlet
 */
@WebServlet("/schedule")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleServlet() {
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
		
		String scheduleDate = request.getParameter("scheduleDate");
		String scheduleTime = request.getParameter("scheduleTime");
		String venue = request.getParameter("venue"); 
		int companyId = Integer.parseInt(request.getParameter("companyId"));
		String event = request.getParameter("event");
		System.out.println(scheduleDate);
		System.out.println(companyId);
		
		
		try {
			
			Connection conn = Databaseconnection.getConnection();
			
			String sql = "INSERT INTO schedule (schedule_date, schedule_time, venue, company_id, event) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql); 
			statement.setString(1, "TBD".equals(scheduleDate) ? null : scheduleDate);
			statement.setString(2, "TBD".equals(scheduleTime) ? null : scheduleTime);
			statement.setString(3, venue); 
			statement.setInt(4, companyId);
			statement.setString(5, event);
			int row = statement.executeUpdate(); 
			if (row > 0)
			{ response.getWriter().println("Schedule created successfully!"); } 
			else { response.getWriter().println("Error creating schedule."); }
			conn.close(); }
		
		catch (Exception e) {
			e.printStackTrace(); response.getWriter().println("Error: " + e.getMessage());
		}
		
		
	}
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{ // Handle preflight requests 
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); 
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); 
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); 
		response.setStatus(HttpServletResponse.SC_OK); }
	}


