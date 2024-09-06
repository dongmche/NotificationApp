import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; // For React Router v6

const Register = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate(); // For React Router v6

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    switch (name) {
      case 'name':
        setName(value);
        break;
      case 'email':
        setEmail(value);
        break;
      case 'password':
        setPassword(value);
        break;
      default:
        break;
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    
    try {
      const response = await axios.post(`${import.meta.env.VITE_BACKEND_URL}/register`, {
        name,
        email,
        password
      });

      if (response.status === 200) {
        console.log('Registration successful:', response.data);
        navigate('/login'); // Redirect to success page
      } else {
        console.log('Error:', response.data);
        setError('Registration failed.');
        setName('');
        setEmail('');
        setPassword('');
      }
    } catch (error) {
      console.error('There was an error registering:', error);
      setError('Registration failed.');
      setName('');
      setEmail('');
      setPassword('');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h3>Sign Up</h3>
      <div className="mb-3">
        <label>Name</label>
        <input
          type="text"
          className="form-control"
          placeholder="name"
          name="name"
          value={name}
          required
          onChange={handleInputChange}
        />
      </div>
      <div className="mb-3">
        <label>Email address</label>
        <input
          type="email"
          className="form-control"
          placeholder="Enter email"
          name="email"
          value={email}
          required
          onChange={handleInputChange}
        />
      </div>
      <div className="mb-3">
        <label>Password</label>
        <input
          type="password"
          className="form-control"
          placeholder="Enter password"
          name="password"
          value={password}
          required
          onChange={handleInputChange}
        />
      </div>
      {error && <div className="text-danger">{error}</div>}
      <div className="d-grid">
        <button type="submit" className="btn btn-primary">
          Sign Up
        </button>
      </div>
      <p className="forgot-password text-right">
        Already registered <a href="/login">sign in?</a>
      </p>
    </form>
  );
};

export default Register;
