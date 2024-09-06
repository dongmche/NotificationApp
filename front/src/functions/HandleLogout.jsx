import {useNavigate } from 'react-router-dom';
const HandleLogout = () => {
    const navigate = useNavigate("/");
    // Clear JWT token from local storage
    localStorage.removeItem('jwt');
    localStorage.removeItem('user');
    
    // Navigate to the home page
    navigate('/');
    console.log('Logged out');
  };

export default HandleLogout;