package com.example;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
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
		 String action = request.getParameter("action");
		 HttpSession session = request.getSession(); 
		 if ("login".equals(action)) {
			 String email = request.getParameter("email");
			 String password = request.getParameter("password"); 
			 // Perform authentication logic here 
			 if (authenticate(email, password)) { 
				 session.setAttribute("user", email); 
				 response.getWriter().write("Login successful");
				 } else { response.getWriter().write("Login failed");
				 } 
			 } 
		 else if ("logout".equals(action)) { 
			 session.invalidate(); response.getWriter().write("Logout successful");
			 } 
		 }
	
	private boolean authenticate(String email, String password)
		 { // Implement your authentication logic here
			 return true; // Replace with actual authentication logic
			 }
		 }


