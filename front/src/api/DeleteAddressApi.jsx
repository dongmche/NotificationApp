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

export default DeleteAddressApi;