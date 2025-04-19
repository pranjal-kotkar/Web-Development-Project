import React, { useState,useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import emailjs from 'emailjs-com';

const NotificationComponent = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { userId, message ,companyName, cgpa } = location.state;
  const [students, setStudents] = useState([]);
  
  useEffect(() => {
    /*
    const fetchEligibleStudents = async () => {
        fetch('http://localhost:8080/tnpbackend/viewprofile')
        .then((response) => response.json())
        .then((data) => setStudents(data))
        .catch((error) => console.error('Error fetching students:', error));


      students.forEach(student => {
        if (student.cgpa >= cgpa) {
          sendEmailNotification(student.userHandle, student.userId,companyName);
        }
      });
    };

  fetchEligibleStudents();
    */
    sendEmailNotification("pranjal.s.kotkar@cumminscollege.in", 1,companyName);
});

  const sendEmailNotification = (recipientEmail,userid, companyName) => {
    const templateParams = {
      to_email: recipientEmail,
      to_name:userid,
      message: companyName,
    };

    emailjs.send('service_xi5i8q9', 'template_4x6nbzx', templateParams, 'fVEoytJX55bPuN5dZ')
      .then((response) => {
        alert('Email sent successfully!', response.status, response.text);
        navigate('/latestcom', { state: { userId: userId , message: message} });

      }, (error) => {
        alert('Failed to send email:', error);
      });
  };

  return (
    <div>
      <h2>Sending Notifications...</h2>
    </div>
  );
};

export default NotificationComponent;
