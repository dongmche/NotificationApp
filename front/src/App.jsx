import { BrowserRouter, Route, Routes } from 'react-router-dom'

import './App.css'
import { useEffect, useState } from 'react'
import Error from './pages/Error'
import Register from './pages/Register'
import Landing from './pages/Landing'
import Login from './pages/Login'
import Main from "./pages/Main"
import AddCustomer from './pages/AddCustomer';
import CustomerDetails from './components/CustomerDetails';
import Addresses from './components/Addresses';
import Navbar from './components/Navbar';
import Customers from './pages/Customers'

import Layout from './components/Layout'

import ProtectedRoute from './ProtectedRoute'

import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  const [loggedIn, setLoggedIn] = useState(false)
  const [email, setEmail] = useState('')

  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
        <Route path="/" element={<Landing />} />

        <Route path="register" element={<Register />} />


        <Route path="login" element={<Login />} />
 
        {/* <Route path="/main" element={<Main />} /> */}
 
        {/* <Route path="/customers" element={<Layout><Customers /></Layout>}  /> */}
 
        <Route path="/customers/:id" element={<Layout><CustomerDetails /></Layout>}/>
 
        <Route path="/address" element={<Addresses />} />
 

 
        <Route path="*" element={<Error />} />


        <Route path="/addcustomer" 
          element={<ProtectedRoute element={<Layout><AddCustomer /></Layout>} />}
        />

        <Route 
          path="/customers" 
          element={<ProtectedRoute element={<Layout><Customers /></Layout>} />} 
        />
        
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
