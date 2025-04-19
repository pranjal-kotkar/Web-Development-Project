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
 * Servlet implementation class DeletePostServlet
 */
@WebServlet("/deletepost")
public class DeletePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePostServlet() {
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
		int postId = Integer.parseInt(request.getParameter("postId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
System.out.println(postId);
System.out.println(userId);
        try {
        	Connection conn = Databaseconnection.getConnection();
			
            String sql = "DELETE FROM forum_posts WHERE post_id = ? and user_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, postId);
            statement.setInt(2, userId);
            int rowsDeleted = statement.executeUpdate();

            conn.close();

            if (rowsDeleted > 0) {
                response.getWriter().write("Post deleted successfully");
            } else {
                response.getWriter().write("Failed to delete post");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
		
	}

}



