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
 * Servlet implementation class UpdateScheduleServlet
 */
@WebServlet("/updateschedule")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class UpdateScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateScheduleServlet() {
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
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		request.setCharacterEncoding("UTF-8"); 
		int scheduleId = Integer.parseInt(request.getParameter("scheduleId")); 
		String scheduleDate = request.getParameter("scheduleDate");
		String scheduleTime = request.getParameter("scheduleTime"); 
		String venue = request.getParameter("venue"); 
		int companyId = Integer.parseInt(request.getParameter("companyId"));
		String event = request.getParameter("event");
		try {
			Connection conn = Databaseconnection.getConnection();
			
			String sql = "UPDATE schedule SET schedule_date = ?, schedule_time = ?, venue = ?, company_id = ?, event = ? WHERE schedule_id = ?"; 
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, scheduleDate); 
			statement.setString(2, scheduleTime);
			statement.setString(3, venue);
			statement.setInt(4, companyId);
			statement.setString(5, event);
			statement.setInt(6, scheduleId);
			int row = statement.executeUpdate();
			if (row > 0) { 
				response.getWriter().println("Schedule updated successfully!"); 
				} else {
					response.getWriter().println("Error updating schedule.");
					} conn.close(); }
		catch (Exception e) { 
			e.printStackTrace(); response.getWriter().println("Error: " + e.getMessage());
			}
		
	}

}
