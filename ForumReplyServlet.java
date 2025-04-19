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
 * Servlet implementation class ForumReplyServlet
 */
@WebServlet("/forum/reply")
public class ForumReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForumReplyServlet() {
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
		
			String userId = request.getParameter("userId");
			String postId = request.getParameter("postId");
			String replyContent = request.getParameter("replyContent");
			try {
				Connection conn = Databaseconnection.getConnection();
				
				String sql = "INSERT INTO forum_replies (post_id, user_id, reply_content) VALUES (?, ?, ?)"; 
				PreparedStatement statement = conn.prepareStatement(sql); 
				statement.setInt(1, Integer.parseInt(postId));
				statement.setInt(2, Integer.parseInt(userId)); 
				statement.setString(3, replyContent); 
				int rowsInserted = statement.executeUpdate();
				JsonObject jsonResponse = new JsonObject(); 
				if (rowsInserted > 0) {
					jsonResponse.addProperty("message", "Reply created successfully"); 
					} else { jsonResponse.addProperty("message", "Failed to create reply");
					} conn.close(); 
					response.getWriter().write(jsonResponse.toString());
			}
			catch (Exception e) {
				e.printStackTrace();
				JsonObject jsonResponse = new JsonObject(); 
				jsonResponse.addProperty("message", "Error: " + e.getMessage()); 
				response.getWriter().write(jsonResponse.toString()); }
	}

}
