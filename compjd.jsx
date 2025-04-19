import React, { useState } from 'react';
import './jd.css';
import { useLocation, useNavigate } from 'react-router-dom';
function JobDescriptionForm() {
  const [formData, setFormData] = useState({
    companyName: '',
    contactPerson: '',
    contactEmail: '',
    details: '',
    stipend: '',
    eligibleBranches: '',
    cgpa:'',
    location: '',
    scheduleDate: '',
    googleFormLink: ''
  });
  const navigate = useNavigate();
  const location = useLocation();
  
  const { userId, message } = location.state || {};
  // Update form data when input fields change
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value
    }));
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/tnpbackend//JDServlet', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams(formData),
      });
      if (response.ok) {
        alert('Form submitted successfully');
        // Redirect to Notification.js and pass the companyName and minCgpa
     navigate( '/notify', {state: { userId: userId , message: message,companyName:formData.companyName, cgpa:formData.cgpa } }); 
        //navigate('/latestcom', { state: { userId: userId , message: message} });
      } else {
        alert('Form submission failed');
      }
    } catch (error) {
      alert('Error submitting form:', error);
    }
  };

  return (
    <div className="form-container">
      <h2>Job Description Form</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Company Name:</label>
          <input
            type="text"
            name="companyName"
            value={formData.companyName}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Contact Person:</label>
          <input
            type="text"
            name="contactPerson"
            value={formData.contactPerson}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Contact Email:</label>
          <input
            type="email"
            name="contactEmail"
            value={formData.contactEmail}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Details:</label>
          <textarea
            name="details"
            value={formData.details}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Stipend:</label>
          <input
            type="text"
            name="stipend"
            value={formData.stipend}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Location:</label>
          <input
            type="text"
            name="location"
            value={formData.location}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Schedule Date:</label>
          <input
            type="date"
            name="scheduleDate"
            value={formData.scheduleDate}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Eligible Branches:</label>
          <input
            type="text"
            name="eligibleBranches"
            value={formData.eligibleBranches}
            onChange={handleChange}
          />
        </div>
        <div>
        <label htmlFor="cgpa" className="form-label">
              CGPA</label> 
              <input type="number" step="0.01" className="form-control" id="cgpa" name="cgpa" 
              value={formData.cgpa}
              onChange={handleChange} required /> 

        </div>
        <div>
          <label>Google Form Link:</label>
          <input
            type="url"
            name="googleFormLink"
            value={formData.googleFormLink}
            onChange={handleChange}
          />
        </div>
        <button type="submit">Submit</button>
      </form>

      {/* Displaying the entered information for preview */}
      <div style={{ marginTop: '20px' }}>
        <h3>Preview:</h3>
        <p><strong>Company Name:</strong> {formData.companyName}</p>
        <p><strong>Contact Person:</strong> {formData.contactPerson}</p>
        <p><strong>Contact Email:</strong> {formData.contactEmail}</p>
        <p><strong>Details:</strong> {formData.details}</p>
        <p><strong>Stipend:</strong> {formData.stipend}</p>
        <p><strong>Location:</strong> {formData.location}</p>
        <p><strong>Schedule Date:</strong> {formData.scheduleDate}</p>
        <p><strong>Eligible Branches:</strong> {formData.eligibleBranches}</p>
        <p><strong>CGPA should be greater than: </strong> {formData.cgpa}</p>
        
        {formData.googleFormLink && (
          <p>
            <strong>Google Form Link:</strong>{' '}
            <a href={formData.googleFormLink} target="_blank" rel="noopener noreferrer">
              Click here to open the form
            </a>
          </p>
        )}
      </div>
    </div>
  );
}

export default JobDescriptionForm;
