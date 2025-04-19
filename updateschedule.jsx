import React, { useState, useEffect } from 'react';
import './viewschedule.css';

function UpdateSchedule() {
  const [schedules, setSchedules] = useState([]);
  const [editingSchedule, setEditingSchedule] = useState(null);
  const [companies, setCompanies] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/tnpbackend/viewschedule')
      .then((response) => response.json())
      .then((data) => setSchedules(data))
      .catch((error) => console.error('Error fetching schedules:', error));
  }, []);

  useEffect(() => { 
    fetch('http://localhost:8080/tnpbackend/companies') 
    .then((response) => response.json()) .
    then((data) => setCompanies(data))
     .catch((error) => console.error('Error fetching companies:', error)); 
    }, []);

  const handleEditClick = (schedule) => {
    setEditingSchedule(schedule);
  };

  const handleUpdateSubmit = async (e) => {
    e.preventDefault();
    const formDataToSend = new FormData();
    formDataToSend.append('scheduleId', editingSchedule.scheduleId);
    formDataToSend.append('scheduleDate', editingSchedule.scheduleDate);
    formDataToSend.append('scheduleTime', editingSchedule.scheduleTime);
    formDataToSend.append('venue', editingSchedule.venue);
    formDataToSend.append('companyId', editingSchedule.companyId);
    formDataToSend.append('event', editingSchedule.event);

    try {
      const response = await fetch('http://localhost:8080/tnpbackend/updateschedule', {
        method: 'POST',
        body: formDataToSend,
      });

      if (response.ok) {
        alert('Schedule updated successfully!');
        setEditingSchedule(null);
        // Refresh the schedules list
        fetch('http://localhost:8080/tnpbackend/viewschedule')
          .then((response) => response.json())
          .then((data) => setSchedules(data))
          .catch((error) => console.error('Error fetching schedules:', error));
      } else {
        alert('Error updating schedule');
      }
    } catch (error) {
      console.error('Error updating schedule:', error);
      alert('Error updating schedule');
    }
  };

  return (
    <div className="container">
      <h2>Upcoming Schedules</h2>
      {editingSchedule ? (
        <form onSubmit={handleUpdateSubmit} encType="multipart/form-data" >
          <div className="mb-3">
            <label htmlFor="scheduleDate" className="form-label">Schedule Date</label>
            <input
              type="date"
              className="form-control"
              id="scheduleDate"
              value={editingSchedule.scheduleDate}
              onChange={(e) => setEditingSchedule({ ...editingSchedule, scheduleDate: e.target.value })}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="scheduleTime" className="form-label">Schedule Time</label>
            <input
              type="time"
              className="form-control"
              id="scheduleTime"
              value={editingSchedule.scheduleTime}
              onChange={(e) => setEditingSchedule({ ...editingSchedule, scheduleTime: e.target.value })}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="venue" className="form-label">Venue</label>
            <input
              type="text"
              className="form-control"
              id="venue"
              value={editingSchedule.venue}
              onChange={(e) => setEditingSchedule({ ...editingSchedule, venue: e.target.value })}
              required
            />
          </div>
          <div className="mb-3"> 
            <label htmlFor="companyId" className="form-label">Company Name</label>
             <select className="form-control" id="companyId" value={editingSchedule.companyId} 
             onChange={(e) => setEditingSchedule({ ...editingSchedule, companyId: e.target.value })} required >
               <option value="">Select a company</option>
                {companies.map((company) => ( 
                  <option key={company.companyId}
                   value={company.companyId}> {company.companyName} </option> ))}
                    </select>
                     </div>
          <div className="mb-3">
            <label htmlFor="event" className="form-label">Event</label>
            <input
              type="text"
              className="form-control"
              id="event"
              value={editingSchedule.event}
              onChange={(e) => setEditingSchedule({ ...editingSchedule, event: e.target.value })}
              required
            />
          </div>
          <button type="submit" className="btn btn-primary">Update Schedule</button>
        </form>
      ) : (
        <div className="schedule-list">
          {schedules.map((schedule) => (
            <div key={schedule.scheduleId} className="schedule-item">
              <h3>{schedule.event}</h3>
              <p><strong>Date:</strong> {schedule.scheduleDate || 'TBD'}</p>
              <p><strong>Time:</strong> {schedule.scheduleTime || 'TBD'}</p>
              <p><strong>Venue:</strong> {schedule.venue}</p>
              <p><strong>Company:</strong> {schedule.companyName}</p>
              <button onClick={() => handleEditClick(schedule)} className="btn btn-secondary">Edit</button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default UpdateSchedule;
