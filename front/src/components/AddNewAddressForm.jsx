import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AddNewAddressApi from '../api/AddNewAddressApi';

const AddNewAddressForm = ({customerId = null, change = null, setChange = null}) => {
  // State to manage form fields
  const [formData, setFormData] = useState({
    address: '',
    type: ''
  });

  const navigate = useNavigate();

  // Handle input changes
  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData({
      ...formData,
      [id]: value // Update the formData state with the new value
    });
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      // Call the API to add a new address
      await AddNewAddressApi(customerId, formData.address, formData.type);
      
      // Optionally update parent component or state
      if (setChange) {
        setChange(!change);
      }
  
      // Navigate to the customer page
      navigate(`/customers/${customerId}`);
    } catch (error) {
      console.error('Error submitting form:', error);
    }
  };

  return (
    <div>
      <h4>add new address</h4>
    <form onSubmit={handleSubmit}>
      <div className="form-group">
        <input
            type="text"
            className="form-control"
            id="address" // Make sure this matches the formData key
            aria-describedby="addressHelp"
            placeholder="Enter address" // Changed placeholder to match field
            value={formData.address} // Ensure this matches the formData key
            onChange={handleChange}
          />
      </div>
      <div className="form-group row">
        <div className="col-md-9">
        <input
              type="text" // Changed type to text for consistency with address
              className="form-control"
              id="type" // Make sure this matches the formData key
              placeholder="Type"
              value={formData.type} // Ensure this matches the formData key
              onChange={handleChange}
            />
        </div>
        <div className="col-md-3 d-flex align-items-center">
          <button type="submit" className="btn btn-primary w-100">
            add
          </button>
        </div>
      </div>
    </form>
    </div>
  );
};

export default AddNewAddressForm;
