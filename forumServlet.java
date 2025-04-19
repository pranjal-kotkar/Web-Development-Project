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
import com.google.gson.JsonObject;

/**
 * Servlet implementation class forumServlet
 */
@WebServlet("/forum")
public class forumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); 
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); 
			
	       
	        List<ForumPost> posts = new ArrayList<>();
	        try {
	        	Connection conn = Databaseconnection.getConnection();
				
	            String sql = "SELECT p.post_id, p.user_id, p.post_title, p.post_content, p.created_at, u.username " +
	                         "FROM forum_posts p " +
	                         "INNER JOIN users u ON p.user_id = u.user_id";
	            PreparedStatement statement = conn.prepareStatement(sql);
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                ForumPost post = new ForumPost();
	                post.setPostId(resultSet.getInt("post_id"));
	                post.setUserId(resultSet.getInt("user_id"));
	                post.setPostTitle(resultSet.getString("post_title"));
	                post.setPostContent(resultSet.getString("post_content"));
	                post.setCreatedAt(resultSet.getTimestamp("created_at"));
	                post.setUsername(resultSet.getString("username"));

	                String replySql = "SELECT r.reply_id, r.post_id, r.user_id, r.reply_content, r.created_at, u.username " +
	                                  "FROM forum_replies r " +
	                                  "INNER JOIN users u ON r.user_id = u.user_id " +
	                                  "WHERE r.post_id = ?";
	                PreparedStatement replyStatement = conn.prepareStatement(replySql);
	                replyStatement.setInt(1, post.getPostId());
	                ResultSet replyResultSet = replyStatement.executeQuery();

	                List<ForumReply> replies = new ArrayList<>();
	                while (replyResultSet.next()) {
	                    ForumReply reply = new ForumReply();
	                    reply.setReplyId(replyResultSet.getInt("reply_id"));
	                    reply.setPostId(replyResultSet.getInt("post_id"));
	                    reply.setUserId(replyResultSet.getInt("user_id"));
	                    reply.setReplyContent(replyResultSet.getString("reply_content"));
	                    reply.setCreatedAt(replyResultSet.getTimestamp("created_at"));
	                    reply.setUsername(replyResultSet.getString("username"));
	                    replies.add(reply);
	                }
	                post.setReplies(replies);
	                posts.add(post);
	            }

	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        String json = new Gson().toJson(posts);
	        response.getWriter().write(json);
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
	        String postTitle = request.getParameter("postTitle");
	        String postContent = request.getParameter("postContent");

	        try {
	        	Connection conn = Databaseconnection.getConnection();
				
	              String sql = "INSERT INTO forum_posts (user_id, post_title, post_content) VALUES (?, ?, ?)";
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setInt(1, Integer.parseInt(userId));
	            statement.setString(2, postTitle);
	            statement.setString(3, postContent);

	            int rowsInserted = statement.executeUpdate();
	            JsonObject jsonResponse = new JsonObject();
	            if (rowsInserted > 0) {
	                jsonResponse.addProperty("message", "Post created successfully");
	            } else {
	                jsonResponse.addProperty("message", "Failed to create post");
	            }

	            conn.close();
	            response.getWriter().write(jsonResponse.toString());
	        } catch (Exception e) {
	            e.printStackTrace();
	            JsonObject jsonResponse = new JsonObject();
	            jsonResponse.addProperty("message", "Error: " + e.getMessage());
	            response.getWriter().write(jsonResponse.toString());
	        }
		
	}

}





