import React from 'react';
import Navbar from './Navbar'; // Assuming you have a Navbar component

const Layout = ({ children }) => {
  return (
    <div>
      <Navbar />
      <div className="container mt-4">
        {children} {/* This is where other components will be injected */}
      </div>
    </div>
  );
};

export default Layout;
