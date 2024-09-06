import * as React from 'react';
import { Link } from 'react-router-dom';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import AdbIcon from '@mui/icons-material/Adb';
import HandleLogout from '../functions/HandleLogout';
import { useNavigate } from 'react-router-dom';
import SearchComponent from './SearchComponent';


import "./Navbar.css"


function UserPresent(){
  const navigate = useNavigate("/");

  const HandleLogout = () => {
    // Clear JWT token from local storage
    localStorage.removeItem('jwt');
    localStorage.removeItem('email');
    
    // Navigate to the home page
    navigate('/');
  };

  const HandleAddCustomer = () => {
    navigate('/addcustomer');
  }

  return (
    <div className="d-flex justify-content-between align-items-center">
      <div className="d-flex gap-2">
        <button className='btn btn-primary' onClick={HandleLogout}>Log out</button>
        <button className='btn btn-primary' onClick={() => navigate("/addcustomer")}>Add customer</button>
        <button className='btn btn-primary' onClick={() => navigate("/customers")}>Customers</button>
        <SearchComponent className="" />
      </div>
    </div>
  );
}


function UserNotPresent(){
  const navigate = useNavigate();
  function HandleLogin(){
    navigate("/login");
  }

  return (
    <div className='button-container'>
      <button className='btn btn-primary' onClick={HandleLogin}> Log in </button>
    </div>
  )
}

function Navbar() {
  const user = localStorage.getItem("user");

  return (
    <AppBar position="fixed" sx={{ width: '100%' }}>
      <Container maxWidth="xl" disableGutters>
        <Toolbar disableGutters>
          {user != null ? <UserNotPresent /> : <UserPresent />}
        </Toolbar>
      </Container>
    </AppBar>
  );
}

export default Navbar;
