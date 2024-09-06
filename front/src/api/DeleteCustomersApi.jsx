import { apiClient, setAuthToken } from "./ApiClient";

const DeleteAddressApi = async ({id}) => { 
  setAuthToken(localStorage.getItem("jwt"));
  try{
    const response = await apiClient.delete(`/addresses/${id}`);
  }catch(error){
    console.error('Error:', error.response?.data || error.message);
    throw error;
  }
}


const DeleteCustomersApi = async ({selected }) => {
  setAuthToken(localStorage.getItem("jwt"));

  console.log("in a delete customers api");

  try {
    const response = apiClient.post(url, selected, {selected})
    

    return response.data;
  } catch (error) {
    console.error("Error deleting customers:", error.response?.data || error.message);
    throw error; // Rethrow if you want to handle the error elsewhere
  }
};



export default DeleteCustomersApi;


