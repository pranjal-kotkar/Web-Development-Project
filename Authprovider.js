import React, { createContext, useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(localStorage.getItem('token') || '');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const login = async ({ email, password }) => {
    try {
      const response = await fetch('http://localhost:8080/tnpbackend/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
          email,
          password,
        }),
      });

      const result = await response.json();
      console.log(result); // Log the response object for debugging

      if (response.ok && result.token) {
        // Successful login
        setUser(result.user);
        setToken(result.token);
        localStorage.setItem('token', result.token);
        navigate('/latestcom')

    }} catch (error) {
      console.error('An unexpected error occurred during login:', error);
      setErrorMessage('An unexpected error occurred');
    }
  };

  const logout = () => {
    setUser(null);
    setToken('');
    localStorage.removeItem('token');
    navigate('/login');
  };

  return (
    <AuthContext.Provider value={{ user, token, login, logout, errorMessage }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  return useContext(AuthContext);
};
