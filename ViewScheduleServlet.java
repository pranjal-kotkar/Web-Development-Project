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
 * Servlet implementation class ViewScheduleServlet
 */
@WebServlet("/viewschedule")
public class ViewScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewScheduleServlet() {
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
		response.setCharacterEncoding("UTF-8"); 
		List<Schedule> schedules = new ArrayList<>();
try {
	Connection conn = Databaseconnection.getConnection();
	 String sql = "SELECT s.schedule_id, s.schedule_date,"
	 		+ " s.schedule_time, s.venue, s.event, c.company_name " 
			 + "FROM schedule s JOIN companies c ON s.company_id = c.company_id";
	 PreparedStatement statement = conn.prepareStatement(sql);
	 ResultSet resultSet = statement.executeQuery(); 
	 while (resultSet.next()) { 
		 Schedule schedule = new Schedule();
		 schedule.setScheduleId(resultSet.getInt("schedule_id"));
		 schedule.setScheduleDate(resultSet.getString("schedule_date"));
		 schedule.setScheduleTime(resultSet.getString("schedule_time"));
		 schedule.setVenue(resultSet.getString("venue"));
		 schedule.setEvent(resultSet.getString("event"));
		 schedule.setCompanyName(resultSet.getString("company_name")); 
		 schedules.add(schedule);
		 } conn.close(); 
		 } 
catch (Exception e) {
	e.printStackTrace(); }
String json = new Gson().toJson(schedules);
response.setContentType("application/json"); 
response.setCharacterEncoding("UTF-8"); 
response.getWriter().write(json);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}


private class Schedule { 
	private int scheduleId;
	private String scheduleDate; 
	private String scheduleTime;
	private String venue; 
	private String event; 
	private String companyName; 
	// Getters and setters 
	public int getScheduleId()
	{ return scheduleId; }
	public void setScheduleId(int scheduleId)
	{ this.scheduleId = scheduleId; } 
	public String getScheduleDate()
	{ return scheduleDate; } 
	public void setScheduleDate(String scheduleDate)
	{ this.scheduleDate = scheduleDate; }
	public String getScheduleTime()
	{ return scheduleTime; } 
	public void setScheduleTime(String scheduleTime) 
	{ this.scheduleTime = scheduleTime; } 
	public String getVenue() 
	{ return venue; } 
	public void setVenue(String venue)
	{ this.venue = venue; } 
	public String getEvent() { return event; } 
	public void setEvent(String event) 
	{ this.event = event; } 
	public String getCompanyName()
	{ return companyName; } 
	public void setCompanyName(String companyName)
	{ this.companyName = companyName; } 
	
}
}
