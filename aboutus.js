import React from 'react';
import './aboutus.css';

function AboutUs() {
  return (
    <div className="about-us">
      {/* Image Section */}
      <div className="image-section">
        <img src="/assets/tnp.jpg" alt="About Us" className="about-us-image" />
      </div>

      {/* Hero Section */}
      <div className="hero-section">
        <h1>About Us</h1>
        <p>
          The Training & Placement Cell plays an integral role in creating the illustrious placement record of Cummins College of Engineering for Women, Pune. 
          It ensures smooth functioning of the placement activities in the campus and virtually. The cell further facilitates training activities of the students and makes sure they get placements and internships in the best of the companies.
        </p>
      </div>

      {/* Mission and Vision Section */}
      <div className="mission-vision">
        <div className="mission">
          <h2>Our Mission</h2>
          <p>
            To develop women professionals who are academically and technically competent with strong professional ethics. We believe in nurturing talents that contribute positively to society.
          </p>
        </div>
        <div className="vision">
          <h2>Our Vision</h2>
          <p>
            To be a globally renowned institute for imparting quality education and to develop women leaders in engineering and technology.
          </p>
        </div>
      </div>

      <div className="company-info">
        <h3>TNP Officer</h3>
        <h4>Prof. Amit Rajurkar</h4>
        <p>
          Contact: <br />
          Landline Direct: 020-25311131/25311132 <br />
          Mobile: 9822493920 <br />
          Email: placements@cumminscollege.in
        </p>
      </div>
    </div>
  );
}

export default AboutUs;