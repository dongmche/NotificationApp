import { apiClient, setAuthToken } from "./ApiClient";
const AddNewCustomerApi = async ({name, email, phone}) => {
    const url = `${import.meta.env.VITE_BACKEND_URL}/customers/create`;

    setAuthToken(localStorage.getItem("jwt"));
    


    try {

      const response = await apiClient.post(`/customers/create`, {
        name,
        email,
        phone
      });

      if(response.status == 201){
        return true;
      }

      return false; // Return the server's response
    } catch (error) {
      console.log(error);
      console.error('Error:', error.response?.data || error.message);
      // Handle the error as needed (could show an error message or re-throw the error)
      return false;
    }
  }
  
  export default AddNewCustomerApi;
  