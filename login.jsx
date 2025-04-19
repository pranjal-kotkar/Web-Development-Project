import React, { useState } from 'react';
import './login.css';

import { useNavigate } from 'react-router-dom';
function Login({ setIsLoggedIn }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:8080/tnpbackend/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: new URLSearchParams({
        email,
        password,
      }),
    })
    .then((response) => response.json()) .then((data) =>
       { if (data.message === 'Login successful') 
        { setIsLoggedIn(true);
           navigate('/latestcom', { state: { userId: data.userId ,stuId: data.stuId , message: data.message} }); // Redirect to Home after successful login 
           } else if (data.message === 'Admin roleLogin successful')
             { setIsLoggedIn(true); 
              navigate('/admindash', { state: { userId: data.userId , message: data.message} }); // Redirect to Admin page after successful login
               }
               }) .catch((error) => { setErrorMessage(error.message);
                alert("Authentication failed use college emailid only");
                
                });
  };

  return (
    <div className="login-container"> 
    <form className="login-form" onSubmit={handleSubmit}>
       <h2>Login</h2>
        {errorMessage && <div className="error-message">{errorMessage}</div>}
         <div> <label>Email:</label> 
         <input type="email" value={email} onChange={(e) =>
           setEmail(e.target.value)} required /> 
           </div> 
           <div> <label>Password:</label>
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
            </div>
            <button type="submit">Login</button> 
            </form> 
            </div>
  );
}

export default Login;
