import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import Address from './Address';
import { useNavigate } from 'react-router-dom';
import AddNewAddressForm from './AddNewAddressForm';
import Navbar from './Navbar';
import FetchCustomersApi from '../api/FetchCustomersApi';
import FetchSingleCustomerAPi from "../api/FetchSingleCustomerApi"
import DeleteAddressApi from '../api/DeleteAddressApi';


function CustomerDetails() {
  const { id } = useParams(); // Extract id from URL parameters
  const [customer, setCustomer] = useState(null); // State to hold customer data
  const [loading, setLoading] = useState(true); // State to handle loading state
  const [error, setError] = useState(null); // State to handle errors
  const [change, setChange] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    // Async function to fetch customer data
    const fetchCustomer = async () => {
      try {
        setLoading(true); // Set loading to true before fetching
        // const response = await axios.get(`http://localhost:8080/customers/${id}`); // Fetch customer data
        const response = await FetchSingleCustomerAPi({id})


        setCustomer(response.data); // Set customer data to state
        // console.log(response.data);
        // console.log(response.data.addresses.length)
      } catch (err) {
        navigate("/error");
        setError(err); // Set error if any
      } finally {
        setLoading(false); // Set loading to false after fetching
      }
    };

    fetchCustomer(); // Call fetchCustomer when component mounts
  }, [id, change]); // Dependency array to refetch if id changes

  // Display loading spinner or error message
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;


  async function handleAddressRemove(id) {
    try {
      // Send a DELETE request to the server
      // const response = await axios.delete(`${import.meta.env.VITE_BACKEND_URL}/addresses/${id}`);
      const response = await DeleteAddressApi({id});

      navigate(0);
      setChange((prev) => {return !prev});
      
      // Optionally: Perform additional actions like updating the UI or state
    } catch (error) {
      // Handle errors, such as network issues or server errors
      console.error('Error removing address:', error);
    }
  }


  // Display customer details
  return (
    <div>
      <Navbar/>
      {customer ? (     
        <div>
          <h1> {customer.name}  {customer.email} Phone: {customer.phone}</h1>

          <h2>Addresses</h2>
          {customer.addresses && customer.addresses.length > 0 ? (
            <div>
              <ul className="list-group list-group-flush">
                
                {customer.addresses.map((address, index) => (
                  <li key= {address.id} className="list-group-item d-flex justify-content-between align-items-center">
                   {address.address} {address.type} 
                    <button className='btn btn-primary' onClick={() => handleAddressRemove(address.id)}>
                      remove
                    </button>
                  <span className="badge badge-primary badge-pill"></span>
                </li>
                ))}
              </ul>
              

              {/* add new address */}
              <AddNewAddressForm customerId={customer.id} change={change} setChange={setChange} />
              
            </div>
          ) : (
            <div>
            <p>No addresses available.</p>
            <AddNewAddressForm customerId={customer.id} change={change} setChange={setChange} />
            </div>
            
          )}
        </div>
      ) : (
        <div>No customer data found.</div>
      )}
    </div>
  );
}

export default CustomerDetails;
