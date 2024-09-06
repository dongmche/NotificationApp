import { apiClient, setAuthToken } from "./ApiClient";

const FetchSingleCustomerAPi = async ({id}) => {

    setAuthToken(localStorage.getItem("jwt"));
  try{
    const response = await apiClient.get(`/customers/${id}`);

    return response;
  }catch(error){
    console.error('Error:', error.response?.data || error.message);
    throw error;
  }
}

export default FetchSingleCustomerAPi;