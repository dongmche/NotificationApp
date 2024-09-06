import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';
import { FaSearch } from "react-icons/fa";


function SearchComponent() {
  const [searchQuery, setSearchQuery] = useState("");
  const navigate = useNavigate(); 

  function handleSearchClick() {
    // console.log("Search clicked with query:");
    // console.log("search query is an ");
    // console.log(searchQuery);
    
    // You can add further logic here to handle the search action
    // how to navigate to the test with query as an arguments
    // return </Test>
    navigate(`/customers?query=${encodeURIComponent(searchQuery)}`);
  }

  function handleInputChange(event) {
    setSearchQuery(event.target.value);
  }

  return (
    <div className="input-group">
      <div className="form-outline">
        <input
          type="search"
          id="form1"
          className="form-control"
          placeholder="Search"
          value={searchQuery}
          onChange={handleInputChange}
        />
        <label className="form-label" htmlFor="form1"></label>
      </div>
      <button type="button" className="btn btn-primary" onClick={handleSearchClick}>
        <FaSearch />
      </button>
    </div>
  );
}

export default SearchComponent;
