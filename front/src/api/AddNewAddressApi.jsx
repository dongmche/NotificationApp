// import axios from "axios";

// const AddNewAddressApi = async (customerId, address, type) => {
//     try {
//       const token = localStorage.getItem("jwt"); // Get the token from localStorage
    
//       const response = await axios.post(
//         `${import.meta.env.VITE_BACKEND_URL}/addresses/create`,
//         {
//           customerId: customerId,
//           address,
//           type,
//         },
//         {
//           headers: {
//             Authorization: `Bearer ${token}`, // Include the token in the Authorization header
//             'Content-Type': 'application/json', // Optional: Set the content type
//           },
//         }
//       );
    
//       return response.data;
//     } catch (error) {
//       console.error('Error:', error.response?.data || error.message);
//       throw error;
//     }
//   };

import { apiClient, setAuthToken } from "./ApiClient";
  
const AddNewAddressApi = async (customerId, address, type) => { 
  setAuthToken(localStorage.getItem("jwt"));
  try{
    const response = await apiClient.post('/addresses/create', {
      customerId: customerId,
      address,
      type,
    });
  }catch(error){
    console.error('Error:', error.response?.data || error.message);
    throw error;
  }
}

export default AddNewAddressApi;