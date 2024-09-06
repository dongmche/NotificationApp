import React, { useState } from 'react';
import AddNewCustomerApi from '../api/AddNewCustomerApi';

const AddCustomer = () => {
  const  [message, setMessage] = useState(null);
  const [error, setError] = useState(null);
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
  });


  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };


  function handleAddCustomer(e) {
    e.preventDefault();
    const response = AddNewCustomerApi({name:formData.name, email:formData.email, phone:formData.phone});
    if(response) {
      setError(null);
      setMessage("succesfully added new customer");
    }else{
      setMessage(null);
      console.log("setting error");
      setError("can not create user, mayebe email is not unique");
    }
  }

  return (
    <div className="container mt-5">
      <h2>Add Customer</h2>
      {error && <div className="alert alert-danger">{error}</div>}
      {message && (
        <div className="alert alert-info" role="alert">
          {message}
        </div>
      )}


      <form onSubmit={handleAddCustomer}>
        <div className="mb-3">
          <label htmlFor="name" className="form-label">Name</label>
          <input
            type="text"
            id="name"
            name="name"
            className="form-control"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            className="form-control"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="phone" className="form-label">Phone</label>
          <input
            type="tel"
            id="phone"
            name="phone"
            className="form-control"
            value={formData.phone}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Add Customer</button>
      </form>
    </div>
  );
};

export default AddCustomer;
