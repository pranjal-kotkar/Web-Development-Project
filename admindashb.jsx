import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './admindash.css';

function AdminDashboard() {
  const navigate = useNavigate();
  const [isLoggedIn,setIsLoggedIn]= useState(true);
  const handleLogoff = () => {
     setIsLoggedIn(false); 
     // Perform any additional logout operations here
      console.log('Logged out');
      navigate('/login');
     };

  return (
    <div className="admin-dashboard">
      <h1 className="centered-heading">Admin Dashboard</h1>

      <div className="dashboard-sections">
        <div className="section" onClick={() => navigate('/schedule')}>
          <h2>Create Schedule</h2>
        </div>
        <div className="section" onClick={() => navigate('/updateschedule')}>
          <h2>Update Schedules</h2>
        </div>
        <div className="section" onClick={() => navigate('/compjd')}>
          <h2>Add Company</h2>
        </div>
        <div className="section" onClick={() => navigate('/latestcom')}>
          <h2>Latest Companies</h2>
        </div>
        <div className="section" onClick={() => navigate('/stuprofiles')}>
          <h2>View All Students</h2>
        </div>
        <div className="section" onClick={() => navigate('/stushortl')}>
          <h2>Shortlist Students</h2>
        </div>
      </div>
    </div>
  );
}

export default AdminDashboard;
