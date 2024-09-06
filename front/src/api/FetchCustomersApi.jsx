import axios from 'axios'; // Import axios or use fetch for HTTP requests
import { apiClient, setAuthToken } from './ApiClient';

// Fetch customer data from API
// import axios from 'axios';

// const FetchCustomersApi = async ({query = null}) => {
//     const token = localStorage.getItem("jwt"); 


//     try {
        
//         if(query == null){
//         const response = await axios.get(`${import.meta.env.VITE_BACKEND_URL}/customers/getall`); // Replace with your API URL        
//         return response.data;
//         }else{
//             const response = await axios.get(`${import.meta.env.VITE_BACKEND_URL}/customers/query`, {
//                 params: { query: query },
//               }
              
//             );
//             return response.data;
//         }

//     } catch (error) {
//         console.error("Error fetching customer data", error);
//         return [];
//     }
// };

const FetchCustomersApi = async ({query = null}) => { 
    setAuthToken(localStorage.getItem("jwt"));
    try{
        if(query == null){
            const response = await apiClient.get("/customers/getall")
            return response.data;
        }
    }catch(error){
      console.error('Error:', error.response?.data || error.message);
      throw error;
    }
  }
  




export default FetchCustomersApi