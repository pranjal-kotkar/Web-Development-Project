import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './companydetails.css';

function CompanyDetails() {
  const location = useLocation();
  const { company, userId, stuId } = location.state || {};
  const [isApplied, setIsApplied] = useState(false);
  const navigate = useNavigate();
  const handleApplyChange = (e) => {
     setIsApplied(e.target.checked); 
     if (e.target.checked) { 
      fetch('http://localhost:8080/tnpbackend/apply', {
         method: 'POST', 
         headers: { 'Content-Type': 'application/x-www-form-urlencoded', },
          body: new URLSearchParams({
             studentId: stuId, companyId: company.companyId, }), })
              .then((response) => response.json()) .then((data) => {
                 alert('Application submitted:', data); })
                  .catch((error) => {
                     alert('Error submitting application:', error);
                     }); 
                     navigate('/latestcom', { state: { userId: userId ,stuId: stuId} }); }
                   };
  if (!company) {
    return <div>No company details available.</div>;
  }

  return (
    <div className="company-details">
      <h2>{company.companyName}</h2>
      <p><strong>Contact Person:</strong> {company.contactPerson}</p>
      <p><strong>Contact Email:</strong> {company.contactEmail}</p>
      <p><strong>Details:</strong> {company.details}</p>
      <p><strong>Stipend:</strong> {company.stipend}</p>
      <p><strong>Location:</strong> {company.location}</p>
      <p><strong>Schedule Date:</strong> {company.scheduleDate}</p>
      <p><strong>Eligible Branches:</strong> {company.eligibleBranches}</p>
      <div className="mb-3"> 
        <label> <input type="checkbox" checked={isApplied} onChange={handleApplyChange} /> 
        Confirm that you have applied </label>
         </div>

      {company.googleFormLink && (
        <p>
          <strong>Google Form Link:</strong>{' '}
          <a href={company.googleFormLink} target="_blank" rel="noopener noreferrer">
            Click here to open the form
          </a>
        </p>
      )}
    </div>
  );
}

export default CompanyDetails;
