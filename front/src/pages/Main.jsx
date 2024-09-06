import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


const Main = () => {
  const [customers, setCustomers] = useState([]);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    // Function to fetch customers
    const fetchCustomers = async () => {
      console.log("fetchin data");
      try {
        // Get the token from local storage or however you store it
        const token = localStorage.getItem('token');
        

        // no need for token yet
        // Send GET request to fetch customers
        // const response = await axios.get('http://localhost:8080/customers/getall', {
        //   headers: {
        //     Authorization: `Bearer ${token}`, // Include token in the Authorization header
        //   },
        // });

        const response = await axios.get(`${import.meta.env.VITE_BACKEND_URL}/customers/getall`);
        console.log(response);


        // Update the state with the fetched customers
        setCustomers(response.data);
      } catch (error) {
        console.log("error happened here");
        console.log(error);
        // Handle errors
        setError('Failed to fetch customers: ' + (error.response ? error.response.data.message : error.message));
        navigate('/error');
      }
    };

    // Call the function to fetch customers
    fetchCustomers();
  }, []); // Empty dependency array ensures this runs once on mount

  return (
    <div>
    <h1>Customer List</h1>
    {error && <p style={{ color: 'red' }}>{error}</p>}
    <ul>
      {customers && customers.length > 0 ? (
        customers.map((customer) => (
          <li key={customer.id}>
            {customer.name} - {customer.email}
          </li>
        ))
      ) : (
        <li>No customers available</li>
    )}
  </ul>

  </div>
  
  );
};

export default Main;
