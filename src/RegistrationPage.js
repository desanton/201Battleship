import React, { useState } from 'react';
import './RegistrationPage.css';


function RegistrationPage() {
  // State hooks for each input
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  // Function to handle the form submission
  const handleSubmit = (event) => {
    event.preventDefault(); // Prevents the default form submit action
    // Here you would handle the registration logic or store the state
    console.log('Submitted:', { username, password, confirmPassword });
  };

  // The component's HTML (JSX)
  return (
    <div className="registration-container">
      <h1>BATTLESHIP</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="confirm-password">Confirm Password:</label>
          <input
            type="password"
            id="confirm-password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">CREATE ACCOUNT</button>
      </form>
    </div>
  );
}

export default RegistrationPage;
