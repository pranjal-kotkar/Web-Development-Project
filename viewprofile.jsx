import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import './viewprofile.css';

function ViewProfile() {
  const location = useLocation();
  const { stuId, student } = location.state || {};

  useEffect(() => {
    if (!student && stuId) {
      fetch(`http://localhost:8080/tnpbackend/viewprofile?stuId=${stuId}`)
        .then((response) => response.json())
        .then((data) => {
          // Handle the fetched data if needed
        })
        .catch((error) => console.error('Error fetching profile:', error));
    }
  }, [stuId, student]);

  if (!student) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container">
      <h2>Student Profile</h2>
      <h2>userhandle: {student.userHandle}</h2>
      <div className="profile-card">
        <div className="profile-details">
          <h3>{JSON.parse(student.profileDetails).name}</h3>
          <p><strong>Email:</strong> {JSON.parse(student.profileDetails).email}</p>
          <p><strong>Contact:</strong> {JSON.parse(student.profileDetails).contact}</p>
          <p><strong>CGPA:</strong> {student.cgpa}</p>
          <p><strong>10th Percentage:</strong> {student.percentage10th}</p>
          <p><strong>12th Percentage:</strong> {student.percentage12th}</p>
          <p><strong>Branch:</strong> {student.branchName}</p>
          <p><strong>Live Backlogs:</strong> {student.liveBacklogs}</p>
          <p><strong>Dead Backlogs:</strong> {student.deadBacklogs}</p>
          <p><strong>Has Internship:</strong> {student.hasInternship ? 'Yes' : 'No'}</p>
          <p><strong>Eligibility Status:</strong> {student.eligibilityStatus ? 'Eligible' : 'Not Eligible'}</p>
        </div>
      </div>
    </div>
  );
}

export default ViewProfile;
