package com.example;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import com.google.gson.Gson;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		
		 String username = request.getParameter("email"); 
		 String password = request.getParameter("password"); 
		 try { 
			 LoginContext lc = new LoginContext("MyLoginModule", new MyCallbackHandler(username, password)); 
			 lc.login();
			 HttpSession session = request.getSession();
			 session.setAttribute("user", username);
			 System.out.println(session.getAttribute("user"));
			// Check the user's role
			 String role = getUserRole(username); 
			 int userid= getUserid(username);
			 
			 System.out.println(role);
			 Map<String, Object> jsonResponse = new HashMap<>(); 
			 jsonResponse.put("userId", userid);
			 if ("admin".equals(role)) 
			 { jsonResponse.put("message", "Admin roleLogin successful");
			 
			 } else if ("student".equals(role))
			 { session.setAttribute("student_id", getStudentId(username)); 
			 // Assuming you have a method to get student_id
			 
			 int stuid = getStudentId(username);
			 jsonResponse.put("stuId", stuid);
			 jsonResponse.put("message", "Login successful"); 
			 }		
			 else {jsonResponse.put("message", "Invalid role"); }
			 String json = new Gson().toJson(jsonResponse); 
			 response.setContentType("application/json"); 
			 response.setCharacterEncoding("UTF-8"); 
			 response.getWriter().write(json);
			 }
		 catch (LoginException e) {
			 response.getWriter().write("Login failed: " + e.getMessage());
			 }
		 }
	
	private String getUserRole(String username) {
		String role = null;
		try {  Connection conn = Databaseconnection.getConnection();  
		String sql = "SELECT role FROM users WHERE username = ?"; 
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) { role = resultSet.getString("role"); } 
		conn.close();
		} catch (Exception e) { e.printStackTrace(); } 
		return role; } 
	
	private int getUserid(String username) {
		int role = 0;
		try {  Connection conn = Databaseconnection.getConnection();  
		String sql = "SELECT user_id  FROM users WHERE username = ?"; 
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) { role = resultSet.getInt("user_id"); } 
		conn.close();
		} catch (Exception e) { e.printStackTrace(); }
		System.out.println("hi"+ role);
		return role; } 
	
	private int getStudentId(String username) { 
		int studentId = 0; 
		try { 
			Connection conn = Databaseconnection.getConnection();
				String sql = "SELECT student_id FROM students WHERE user_id = ?"; 
				PreparedStatement statement = conn.prepareStatement(sql); 
				statement.setInt(1, getUserid(username));
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					studentId = resultSet.getInt("student_id");
					} 
				conn.close(); 
				} catch (Exception e) 
		{ e.printStackTrace();
		} 
		 System.out.println("hi student "+ studentId);
		return studentId; 
		}
	
	}
class MyCallbackHandler implements javax.security.auth.callback.CallbackHandler {
	private String username;
	private String password; 
	public MyCallbackHandler(String username, String password) 
	{ this.username = username;
	this.password = password; 
	} 
	@Override 
	public void handle(javax.security.auth.callback.Callback[] callbacks) throws IOException, javax.security.auth.callback.UnsupportedCallbackException
	{ 
		for (javax.security.auth.callback.Callback callback : callbacks)
		{ if (callback instanceof NameCallback)
		{ ((NameCallback) callback).setName(username); 
		} else if (callback instanceof PasswordCallback) 
		{ ((PasswordCallback) callback).setPassword(password.toCharArray());
		} else { throw new javax.security.auth.callback.UnsupportedCallbackException(callback); 
		} }
		}
}

