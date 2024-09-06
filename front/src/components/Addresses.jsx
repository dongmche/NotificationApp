import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import Address from './Address'; // Import the Address component

function Addresses() {
  const { id } = useParams(); // Extract id from URL parameters
  const [customer, setCustomer] = useState(null); // State to hold customer data
  const [loading, setLoading] = useState(true); // State to handle loading state
  const [error, setError] = useState(null); // State to handle errors

  useEffect(() => {
    // Async function to fetch customer data
    const fetchCustomer = async () => {
      console.log('inside some')
      try {
        setLoading(true); // Set loading to true before fetching
        const response = await axios.get(`http://localhost:8080/customers/4`); // Fetch customer data
        console.log(response)
        setCustomer(response.data); // Set customer data to state
      } catch (err) {
        setError(err); // Set error if any
      } finally {
        setLoading(false); // Set loading to false after fetching
      }
    };

    fetchCustomer(); // Call fetchCustomer when component mounts
  }, [id]); // Dependency array to refetch if id changes

  // Display loading spinner or error message
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  // Display customer details and addresses
  return (
    <div className="container">
      <h1 className="my-4">Customer Details</h1>
      {customer ? (
        <div>
          <div className="card mb-4">
            <div className="card-body">
              <h5 className="card-title">{customer.name}</h5>
              <p className="card-text">Email: {customer.email}</p>
              <p className="card-text">Phone: {customer.phone}</p>
            </div>
          </div>

          <h2>Addresses</h2>
          {customer.addresses && customer.addresses.length > 0 ? (
            <div>
              {customer.addresses.map((address, index) => (
                <Address key={index} address={address} />
              ))}
            </div>
          ) : (
            <p>No addresses available.</p>
          )}
        </div>
      ) : (
        <div>No customer data found.</div>
      )}
    </div>
  );
}

export default Addresses;
