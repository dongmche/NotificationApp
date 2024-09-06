import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ element, ...rest }) => {
  const isLoggedIn = localStorage.getItem('email') !== null;
  
  return isLoggedIn ? element : <Navigate to="/login" />;
};

export default ProtectedRoute;
