import React, { useState } from 'react';
import './register.css';
import { useLocation , useNavigate} from 'react-router-dom';
function Register() {
  const navigate = useNavigate();
  const [academicNumber, setAcademicNumber] = useState('');
  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [contact, setcontact] = useState('');
  const [cgpa, setCgpa] = useState(''); 
  const [percentage10th, setPercentage10th] = useState(''); 
  const [percentage12th, setPercentage12th] = useState('');
   const [deadBacklogs, setDeadBacklogs] = useState('');
    const [liveBacklogs, setLiveBacklogs] = useState('');
     const [branch, setBranch] = useState(''); 
     const [profilePicture, setProfilePicture] = useState(null);
     const location = useLocation(); 
     const { userId } = location.state || {};
 

     const handleSubmit = async (e) => {
       e.preventDefault(); 
          const formDataToSend = new FormData(); 
          formDataToSend.append('academicNumber', academicNumber); 
          formDataToSend.append('fullName', fullName); 
          formDataToSend.append('email', email);
           formDataToSend.append('cgpa', cgpa); 
           formDataToSend.append('contact', contact); 
           formDataToSend.append('percentage10th', percentage10th); 
           formDataToSend.append('percentage12th', percentage12th); 
           formDataToSend.append('deadBacklogs', deadBacklogs);
            formDataToSend.append('liveBacklogs', liveBacklogs); 
            formDataToSend.append('branch', branch);
            formDataToSend.append('userid', userId);
             if (profilePicture)
               { formDataToSend.append('profilePicture', profilePicture); 

               } try
                { const response = await fetch('http://localhost:8080/tnpbackend/register',
                   { method: 'POST', 
                    body: formDataToSend,
                   });
                    if (response.ok)
                       { console.log('Form data submitted successfully!');
                        navigate('/latestcom');
                        } else 
                        { console.error('Error submitting form data'); } }
                         catch (error) { console.error('Error submitting form data:', error); } };

  return (
    <div className="container form-container">
      <div className="form-header">
        <h2>Create Student profile for T&P Cell</h2>
        <p className="text-muted">Fill in your details to create an account</p>
      </div>

      <form onSubmit={handleSubmit} encType="multipart/form-data">
        {/* Academic Number */}
         <div className="mb-3">
           <label htmlFor="academicNumber" className="form-label">
            C Number
            </label> 
            <input type="text" className="form-control"
             id="academicNumber" name="academicNumber" value={academicNumber}
              onChange={(e) => setAcademicNumber(e.target.value)} 
              required /> </div>
        {/* Name */}
        <div className="mb-3">
          <label htmlFor="name" className="form-label">Name</label>
          <input
            type="text"
            className="form-control"
            id="name"
            name="name"
            value={fullName}
            onChange={(e) =>
              setFullName(e.target.value)}
            required
          />
        </div>

        {/* Email */}
        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email ID</label>
          <input
            type="email"
            className="form-control"
            id="email"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>

        {/* Contact Number */}
        <div className="mb-3">
          <label htmlFor="contact" className="form-label">Contact Number</label>
          <input
            type="tel"
            className="form-control"
            id="contact"
            name="contact"
            pattern="[0-9]{10}"
            value={contact}
            onChange={(e) => setcontact(e.target.value)}
            required
          />
        </div>

         {/* CGPA */}
          <div className="mb-3"> 
            <label htmlFor="cgpa" className="form-label">
              CGPA</label> 
              <input type="number" step="0.01" className="form-control" id="cgpa" name="cgpa" 
              value={cgpa} onChange={(e) => setCgpa(e.target.value)} required /> </div>

              {/* 10th Marks */} 
              <div className="mb-3"> <label htmlFor="percentage_10th" className="form-label">10th Marks (%)</label>
               <input type="number" step="0.01" className="form-control" id="percentage_10th" name="percentage_10th" 
               value={percentage10th} onChange={(e) => setPercentage10th(e.target.value)} required /> 
               </div>
                {/* 12th Marks */} <div className="mb-3"> <label htmlFor="percentage_12th" className="form-label">
                  
                  12th Marks (%)</label> <input type="number" step="0.01" className="form-control" id="percentage_12th"
                   name="percentage_12th" value={percentage12th} onChange={(e) => setPercentage12th(e.target.value)} 
                   required /> </div>
                    {/* Backlogs */} <div className="mb-3"> <label className="form-label">Backlogs</label>
                     <div className="row"> <div className="col"> <input type="number" className="form-control"
                      id="deadBacklogs" name="deadBacklogs" placeholder="Dead Backlogs" value={deadBacklogs}
                       onChange={(e) => setDeadBacklogs(e.target.value)} /> </div> <div className="col"> 
                       <input type="number" className="form-control" id="liveBacklogs" name="liveBacklogs"
                        placeholder="Live Backlogs" value={liveBacklogs} onChange={(e) => setLiveBacklogs(e.target.value)} /> 
                        </div> </div> </div>
                         {/* Branch */} <div className="mb-3"> 
                          <label htmlFor="branch" className="form-label">Branch</label> 
                          <select className="form-select" id="branch" name="branch" value={branch} 
                          onChange={(e) => setBranch(e.target.value)} required > <option value="" disabled>Select Branch</option>
                           <option value="Computer Science">Computer Engineering</option> 
                           <option value="Information Technology">
                            Information Technology</option> <option value="Electronics">Electronics and Telecommunication
                              </option> <option value="Mechanical">Mechanical</option> <option value="Instrumentation">Instrumentation</option> 
                              </select> </div>
                               {/* Profile Picture */} <div className="mb-3"> 
                                <label htmlFor="profilePicture" className="form-label">Profile Picture</label>
                                 <input type="file" className="form-control" id="profilePicture" 
                                 name="profilePicture" accept="image/*" onChange={(e) =>
                                  setProfilePicture(e.target.files[0])}
                                  required />
                                   </div>

        {/* Submit Button */}
        <div className="d-grid gap-2">
          <button type="submit" className="btn btn-primary">Create Profile</button>
        </div>
      </form>
    </div>
  );
}

export default Register;
