import React, { useState } from 'react';
import './RegistrationPage.css';

function RegistrationPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    
    if(password !== confirmPassword) {
      alert('Passwords do not match!');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      const data = await response.json();
      
      if (response.ok) {
        console.log('Registration successful', data);
        setUsername('');
        setPassword('');
        setConfirmPassword('');
      } else {
        console.error('Registration failed', data);
      }
    } catch (error) {
      console.error('There was an error registering the user:', error);
    }
  };

  return (
    <body>
    <div className="navigation">
        <nav className="navbar">
          <nav class="navbar">
            <div class="navbar-container">
                <h1>Battleship Game</h1>
            </div>
          </nav>
        </nav>
    </div>
    <main>
      <div className="registration-container-wrapper">
        <div className="registration-container">
          <h2>REGISTRATION</h2>
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label htmlFor="username">Username: </label>
              <input
                type="text"
                id="username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="password">Password: </label>
              <input
                type="password"
                id="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="confirm-password">Confirm Password: </label>
              <input
                type="password"
                id="confirm-password"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                required
              />
            </div>
            <button type="submit"  class="button">CREATE ACCOUNT</button>
          </form>
        </div>
        <div className="registration-container">
          <h2>LOGIN</h2>
          <form onSubmit={handleSubmit}>
            <div className="form-group" id="loginUser">
              <label htmlFor="login-username">Username: </label>
              <input
                type="text"
                id="login-username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
              />
            </div>
            <div className="form-group" id="loginPass">
              <label htmlFor="login-password">Password: </label>
              <input
                type="password"
                id="login-password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>
            <button type="submit" class="button" id="loginButton">LOGIN ACCOUNT</button>
          </form>
        </div>
      </div>
    </main>
  </body>
  );
}

export default RegistrationPage;
