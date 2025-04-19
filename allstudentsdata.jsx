import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './allstuprofiles.css';

function AllStudentsProfile() {
  const [students, setStudents] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetch('http://localhost:8080/tnpbackend/viewprofile')
      .then((response) => response.json())
      .then((data) => setStudents(data))
      .catch((error) => console.error('Error fetching students:', error));
  }, []);

  const handleViewProfile = (student,stuId) => {
    navigate(`/viewprofile/${stuId}`, { state: { student,stuId } });
  };

  return (
    <div className="container">
      <h2>All Students Profile</h2>
      <div className="students-list">
        {students.map((student) => (
          <div key={student.studentId} className="student-card">
            <h3>{JSON.parse(student.profileDetails).name}</h3>
            <p><strong>Email:</strong> {JSON.parse(student.profileDetails).email}</p>
            <p><strong>Contact:</strong> {JSON.parse(student.profileDetails).contact}</p>
            <button onClick={() => handleViewProfile(student,student.studentId)}>View Profile</button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default AllStudentsProfile;
