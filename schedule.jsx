import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function Schedule() {
  const [scheduleDateOption, setScheduleDateOption] = useState('');
  const [scheduleDate, setScheduleDate] = useState('');
  const [scheduleTimeOption, setScheduleTimeOption] = useState('');
  const [scheduleTime, setScheduleTime] = useState('');
  const [venue, setVenue] = useState('');
  const [companyId, setCompanyId] = useState('');
  const [event, setEvent] = useState('');
  const [companies, setCompanies] = useState([]);
  const [otherEvent, setOtherEvent] = useState('');
  const navigate = useNavigate();
  useEffect(() => {
    // Fetch company names from the companies table
    fetch('http://localhost:8080/tnpbackend/companies')
      .then((response) => response.json())
      .then((data) => setCompanies(data))
      .catch((error) => console.error('Error fetching companies:', error));
  }, []);

  const handleSubmit = async (e) => { 
    e.preventDefault(); 
    const formDataToSend = new FormData();
     formDataToSend.append('scheduleDate', scheduleDateOption === 'setDate' ? scheduleDate : scheduleDateOption);
      formDataToSend.append('scheduleTime', scheduleTimeOption === 'setTime' ? scheduleTime : scheduleTimeOption);
       formDataToSend.append('venue', venue); 
       formDataToSend.append('companyId', companyId); 
       formDataToSend.append('event', event === 'other' ? otherEvent : event);
        try {
           const response = await fetch('http://localhost:8080/tnpbackend/schedule', 
            { method: 'POST', body: formDataToSend,

             });
              if (response.ok)
                 { alert('Schedule created successfully!'); 
                  navigate('/latestcom');


                 } else 
                 { alert('Error creating schedule'); } } 
                 catch (error) {
                   console.error('Error creating schedule:', error);
                    alert('Error creating schedule'); 
                  } 
                
                };

  return (
    <div className="container">
      <h2>Create Schedule for Upcoming Events</h2>
      <form onSubmit={handleSubmit} encType="multipart/form-data">
        {/* Schedule Date */}
        <div className="mb-3">
          <label htmlFor="scheduleDateOption" className="form-label">Schedule Date</label>
          <select
            className="form-select"
            id="scheduleDateOption"
            value={scheduleDateOption}
            onChange={(e) => setScheduleDateOption(e.target.value)}
            required
          >
            <option value="" disabled>Select Date</option>
            <option value="TBD">TBD</option>
            <option value="setDate">Set Date</option>
          </select>
          {scheduleDateOption === 'setDate' && (
            <input
              type="date"
              className="form-control mt-2"
              value={scheduleDate}
              onChange={(e) => setScheduleDate(e.target.value)}
              required
            />
          )}
        </div>

        {/* Schedule Time */}
        <div className="mb-3">
          <label htmlFor="scheduleTimeOption" className="form-label">Schedule Time</label>
          <select
            className="form-select"
            id="scheduleTimeOption"
            value={scheduleTimeOption}
            onChange={(e) => setScheduleTimeOption(e.target.value)}
            required
          >
            <option value="" disabled>Select Time</option>
            <option value="TBD">TBD</option>
            <option value="setTime">Set Time</option>
          </select>
          {scheduleTimeOption === 'setTime' && (
            <input
              type="time"
              className="form-control mt-2"
              value={scheduleTime}
              onChange={(e) => setScheduleTime(e.target.value)}
              required
            />
          )}
        </div>

        {/* Venue */}
        <div className="mb-3">
          <label htmlFor="venue" className="form-label">Venue</label>
          <select
            className="form-select"
            id="venue"
            value={venue}
            onChange={(e) => setVenue(e.target.value)}
            required
          >
            <option value="" disabled>Select Venue</option>
            <option value="Virtual">Virtual</option>
            <option value="On Campus">On Campus</option>
            <option value="TBD">TBD</option>
          </select>
        </div>

        {/* Company */}
        <div className="mb-3">
          <label htmlFor="companyId" className="form-label">Select Company</label>
          <select
            className="form-select"
            id="companyId"
            value={companyId}
            onChange={(e) => setCompanyId(e.target.value)}
            required
          >
            <option value="" disabled>Select Company</option>
            {companies.map((company) => (
              <option key={company.companyId} value={company.companyId}>
                {company.companyName}
              </option>
            ))}
          </select>
        </div>

        {/* Event */}
        <div className="mb-3">
          <label htmlFor="event" className="form-label">Event</label>
          <select
            className="form-select"
            id="event"
            value={event}
            onChange={(e) => setEvent(e.target.value)}
            required
          >
            <option value="" disabled>Select Event</option>
            <option value="Online Test">Online Test</option>
            <option value="Interview">Interview</option>
            <option value="TBD">TBD</option>
            <option value="other">Other: Specify</option>
          </select>
          {event === 'other' && (
            <input
              type="text"
              className="form-control mt-2"
              placeholder="Specify Event"
              value={otherEvent}
              onChange={(e) => setOtherEvent(e.target.value)}
              required
            />
          )}
        </div>

        {/* Submit Button */}
        <div className="d-grid gap-2">
          <button type="submit" className="btn btn-primary">Create Schedule</button>
        </div>
      </form>
    </div>
  );
}

export default Schedule;
