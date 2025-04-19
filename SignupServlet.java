package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
		//doGet(request, response);
		// Set CORS headers 
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); 
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		String fullName = request.getParameter("fullName"); 
		String email = request.getParameter("email"); 
		String password = request.getParameter("password"); 
		String role = request.getParameter("role");
		//String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		// Print received input to the console
		System.out.println("Received signup request:");
		System.out.println("Full Name: " + fullName);
		System.out.println("Email: " + email);
		System.out.println("Password: " + password);
		System.out.println("Role: " + role);
		String query = "INSERT INTO users (username, password, full_name, email, role) VALUES (?, ?, ?, ?, ?)"; 
		try (Connection connection = Databaseconnection.getConnection(); 
				
				PreparedStatement statement = connection.prepareStatement(query))
		{ statement.setString(1, email); // username is set to email
		statement.setString(2, password);
		statement.setString(3, fullName); 
		statement.setString(4, email); 
		statement.setString(5, role);
		statement.executeUpdate();
		response.getWriter().write("Signup successful"); 
		} 
		catch (SQLException e) {
			e.printStackTrace(); 
			response.getWriter().write("Signup failed: ");
		}
	}

}
