import React, { useState, useEffect } from 'react';
import './shortl.css';
import { useNavigate } from 'react-router-dom';

function ShortlistStudents() {
  const [formData, setFormData] = useState({
    companyId: '',
    studentId: '',
    jobStatus: 'applied',
    selected: false,
  });
  const [companies, setCompanies] = useState([]);
  const [students, setStudents] = useState([]);
  const navigate = useNavigate();
 
  useEffect(() => {
    // Fetch companies data
    fetch('http://localhost:8080/tnpbackend/companies')
      .then((response) => response.json())
      .then((data) => setCompanies(data))
      .catch((error) => console.error('Error fetching companies:', error));
  }, []);

  useEffect(() => {
    if (formData.companyId) {
      // Fetch students who have applied for the selected company
      fetch(`http://localhost:8080/tnpbackend/appliedstudents?companyId=${formData.companyId}`)
        .then((response) => response.json())
        .then((data) => setStudents(data))
        .catch((error) => console.error('Error fetching students:', error));
    }
  }, [formData.companyId]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === 'checkbox' ? checked : value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:8080/tnpbackend/shortlist', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: new URLSearchParams(formData),
    })
      .then((response) => response.json())
      .then((data) => {
        alert('Shortlist submitted:', data);
        navigate('/admindash', { state: { userId: data.userId , message: data.message} }); // Redirect to Admin page after successful login
              
      })
      .catch((error) => alert('Error submitting shortlist:', error));
  };

  return (
    <div className="shortlist-form-container">
      <h2>Shortlist Students for Job</h2>
      <form onSubmit={handleSubmit} className="shortlist-form">
        <div className="form-group">
          <label>Company Name:</label>
          <select
            name="companyId"
            value={formData.companyId}
            onChange={handleChange}
            required
          >
            <option value="">Select a company</option>
            {companies.map((company) => (
              <option key={company.companyId} value={company.companyId}>
                {company.companyName}
              </option>
            ))}
          </select>
        </div>
        <div className="form-group">
          <label>Student Name:</label>
          <select
            name="studentId"
            value={formData.studentId}
            onChange={handleChange}
            required
          >
            <option value="">Select a student</option>
            {students.map((student) => (
              <option key={student.studentId} value={student.studentId}>
                {JSON.parse(student.profileDetails).name}
              </option>
            ))}
          </select>
        </div>
        <div className="form-group">
          <label>Job Status:</label>
          <select
            name="jobStatus"
            value={formData.jobStatus}
            onChange={handleChange}
          >
            <option value="applied">Applied</option>
            <option value="interview_scheduled">Interview Scheduled</option>
            <option value="accepted">Accepted</option>
            <option value="rejected">Rejected</option>
          </select>
        </div>
        <div className="form-group">
          <label>
            <input
              type="checkbox"
              name="selected"
              checked={formData.selected}
              onChange={handleChange}
            />
            Selected
          </label>
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default ShortlistStudents;
