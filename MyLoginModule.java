package com.example;

import javax.security.auth.spi.LoginModule; 


import javax.security.auth.Subject; 
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.Callback; 
import javax.security.auth.callback.NameCallback; 
import javax.security.auth.callback.PasswordCallback; 
import javax.security.auth.login.LoginException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MyLoginModule implements LoginModule
{ 
	private Subject subject;
	private CallbackHandler callbackHandler; 
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) 
	{
		this.subject = subject;
	this.callbackHandler = callbackHandler;
	}
@Override 
public boolean login() throws LoginException {
	Callback[] callbacks = new Callback[2]; 
	callbacks[0] = new NameCallback("username");
	callbacks[1] = new PasswordCallback("password", false); 
	try { callbackHandler.handle(callbacks); 
	String username = ((NameCallback) callbacks[0]).getName();
	char[] password = ((PasswordCallback) callbacks[1]).getPassword(); 
	System.out.println("email" + username + "  pass:" + password);
	// Perform authentication logic here
	if (username.endsWith("@cumminscollege.in") && authenticate(username, new String(password))) 
	{ return true; } 
	else {
		throw new LoginException("Authentication failed"); 
		}
	}
	catch (Exception e) { 
		throw new LoginException("Authentication failed");
		} 
	} 

private boolean authenticate(String email, String password)
{ String query = "SELECT password FROM users WHERE email = ?";
try (Connection connection = Databaseconnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(query))
{ statement.setString(1, email);
try (ResultSet resultSet = statement.executeQuery()) 
{ if (resultSet.next()) {
	String storedPassword = resultSet.getString("password");
	return storedPassword.equals(password); 
	// Replace with actual password hashing and comparison
	}
}
}
catch (SQLException e) {
	e.printStackTrace();
	} 
return false; 
}

@Override
public boolean commit() throws LoginException 
{ return true;
} 
@Override
public boolean abort() throws LoginException { 
	return false; 
	}
@Override
public boolean logout() throws LoginException { 
	return true;
	} 
}
	



