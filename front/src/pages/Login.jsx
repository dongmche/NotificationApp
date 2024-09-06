import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();


  const handleSubmit = async (event) => {
    event.preventDefault(); // Prevent the default form submission behavior

    try {
      const response = await axios.post(`${import.meta.env.VITE_BACKEND_URL}/authenticate`, {
        email,
        password
      });

      // Check if the response status is OK
      if (response.status === 200 && response.data.jwt) {
         // Store the token in local storage
        localStorage.setItem("jwt", response.data.jwt);
        localStorage.setItem("email", response.data.email);

        

        console.log(response.data);
        // Redirect to the main page
        navigate('/customers');
      }




      // Handle success response
      setMessage('Success: ' + response.data.message);
    } catch (error) {
      if (error.response) {
        // Server responded with a status other than 2xx
        console.error('Error response:', error.response);
        setMessage('Error: ' + error.response.data.message);
      } else if (error.request) {
        // Request was made but no response was received
        console.error('Error request:', error.request);
        setMessage('Error: No response from server');
      } else {
        // Something happened in setting up the request
        console.error('Error message:', error.message);
        setMessage('Error: ' + error.message);
      }
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email</label>
          <input
            type="email"
            className="form-control"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="password" className="form-label">Password</label>
          <input
            type="password"
            className="form-control"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Submit</button>
      </form>
      {message && <div className="mt-3">{message}</div>}
    </div>
  );
}

export default Login;
