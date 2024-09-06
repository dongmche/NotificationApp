import React from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ArrowDownwardIcon from '@mui/icons-material/ArrowDownward';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';

function Address({ address }) {
  // return (
    // <div className="card" style={{ width: '18rem' }}>
    //   <div className="card-body">
    //     <h5 className="card-title">Address</h5>
    //     <p className="card-text">{address.address}</p>
    //     <a href="#" className="btn btn-primary">Go somewhere</a>
    //   </div>
    // </div>
  // );

  return (
    <div>
      <Accordion>
        <AccordionSummary
          expandIcon={<ArrowDownwardIcon />}
          aria-controls="panel1-content"
          id="panel1-header"
        >
          <Typography>Address {address.address} </Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography>
              type: {address.type}
          </Typography>
        </AccordionDetails>
      </Accordion>
    </div>
  );
}

export default Address;
