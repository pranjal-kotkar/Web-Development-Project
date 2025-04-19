import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';


import Home from './components/root/home/Home';
import Home2 from './components/root/home/Home2';
import Login from './components/root/user/login';
import Signup from './components/root/user/Signup';
import Header from './components/Header';
import  Navbar from './components/Navbar';
import AboutUs from './components/aboutus';
import Register from './components/root/user/sturegister';
import Latestcompany from './components/root/home/latestcompany';
import ViewSchedule from './components/root/admin/viewschedule';
import Schedule from './components/root/admin/schedule';
import UpdateSchedule from './components/root/admin/updateschedule';
import ViewProfile from './components/root/user/viewprofile';
import { AuthProvider } from './components/root/auth/Authprovider';
import JobDescriptionForm from './components/root/admin/compjd';
import CompanyDetails from './components/root/home/companydetails';
import ShortlistStudents from './components/root/admin/shortlist';
import AllStudentsProfile from './components/root/user/allstudentsdata';
import AdminDashboard from './components/root/admin/admindashb';
import Forum from './components/root/home/forum';
import ViewShortlist from './components/root/admin/viewshortlist';
import NotificationComponent from './components/root/admin/notification';
function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
 
  const handleLogin = () => {
    setIsLoggedIn(true);
  };
/*
  const handleLogout = () => {
    fetch('http://localhost:8080/tnpbackend/logout', 
      { method: 'POST', }) .then((response) =>
         response.text()) .then((data) => 
          { if (data === 'Logout successful')
             { setIsLoggedIn(false);
              
              }
              else { alert(data); } }); }; */
//remove above for authprovider
  return (
    <Router> 
     
      <Navbar isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />
    <Routes> 
      <Route path="/login" element={<Login setIsLoggedIn={handleLogin} />} /> 
      <Route path="/signup" element={<Signup />} />
      <Route path="/" element={<Home2 />} /> 
      <Route path="/home" element={<Home />} />
      <Route path="/latestcom" element={<Latestcompany />} />
      <Route path="/aboutus" element={<AboutUs />} />
      <Route path="/stuprofile" element={<Register />} />
      <Route path="/schedule" element={<Schedule />} />
      <Route path="/viewschedule" element={<ViewSchedule />} />
      <Route path="/updateschedule" element={<UpdateSchedule />} />
      <Route path="/viewprofile/:stuId" element={<ViewProfile />} />
      <Route path="/stuprofiles" element={<AllStudentsProfile />} />
      <Route path="/compjd" element={<JobDescriptionForm />} />
      <Route path="/stushortl" element={<ShortlistStudents />} />
      <Route path="/company/:companyId" element={<CompanyDetails />} />
      <Route path="/admindash" element={<AdminDashboard  />} />
      <Route path="/forum" element={<Forum  />} />
      <Route path="/compshortl" element={<ViewShortlist  />} />
      <Route path="/notify" element={<NotificationComponent  />} />



      </Routes>
     
      </Router>
  );
}

export default App;
