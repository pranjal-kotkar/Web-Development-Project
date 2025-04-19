import React from 'react';
import './Home2.css';
import Carousel from 'react-multi-carousel';
import 'react-multi-carousel/lib/styles.css';

function Home2() {
  const categories = [
    {
      title: 'IT Product Companies',
      companies: ['Microsoft', 'Cisco', 'Amazon', 'Walmart', 'Shell', 'Avaya...']
    },
    {
      title: 'Fincorp Companies',
      companies: ['Goldman Sachs', 'Citi', 'Deutsche Bank', 'Wells Fargo', 'Bank of America', 'Nomura...']
    },
    {
      title: 'Service Based Companies',
      companies: ['Accenture', 'Infosys', 'Wipro', 'TCS', 'Cognizant', 'L&T...']
    },
    {
      title: 'Automation Companies',
      companies: ['Alstom','Boeing','Tata Motors', 'Hero','Honeywell', 'Mahindra...']
    }
  ];

  return (
    <div>
      <div className="marquee-container">
        <marquee className="marquee-text">Cummins College T&P Cell</marquee>
      </div>
      <div className="homepage">
        <div className="content">
          <div className="content-box">
            <h1>Welcome to Our Placement Portal</h1>
            <p>Your future starts here</p>
          </div>
        </div>
      </div>
      <div className="content-container">
        <div className="description">
          <p>
            The training & placement cell is the nodal center and an integral part of the institution. 
            Started in 1995, it has scaled to great heights and touched a mark of more than 90% placements since its inception.
            The cell has very active collaborations with industry.
            The cell helps the students in pursuing their career interests and also, grooms and shapes the students to make 
            them industry-ready by imparting necessary skills and training.
            More than 150 reputed National and Multinational companies visit our institution for campus recruitments annually.
            The cell also imparts value-added services like Hackathons, Resume Writing, Personal Interviews, Mock tests, 
            Team building activities as a part of the pre-placement training.
            The cell has a state-of-the-art infrastructure for its effective functioning and makes the best of arrangements 
            required by the company officials during placement visits.
            Our skilled and dedicated staff always tries to deliver precisely planned services to the student community and our recruiters.
          </p>
        </div>
        <div className="above-homepage">
          <img src="\assets\Placement-process-chart.jpg" alt="Description of image" className="above-homepage-image" />
        </div>
      </div>
      <div className="content">
      <div className="content-box">
      <h1>Companies Visited </h1>
      </div>
      </div>
     
      
      <div className="carousel-container">
        <Carousel
          additionalTransfrom={0}
          arrows
          autoPlay
          autoPlaySpeed={1000}
          centerMode={false}
          className=""
          containerClass="carousel-container"
          dotListClass=""
          draggable
          focusOnSelect={false}
          infinite
          itemClass=""
          keyBoardControl
          minimumTouchDrag={80}
          renderButtonGroupOutside={false}
          renderDotsOutside={false}
          responsive={{
            desktop: {
              breakpoint: { max: 3000, min: 1024 },
              items: 3,
              partialVisibilityGutter: 40
            },
            tablet: {
              breakpoint: { max: 1024, min: 464 },
              items: 2,
              partialVisibilityGutter: 30
            },
            mobile: {
              breakpoint: { max: 464, min: 0 },
              items: 1,
              partialVisibilityGutter: 30
            }
          }}
          showDots={false}
          sliderClass=""
          slidesToSlide={1}
          swipeable
        >
          {categories.map((category, index) => (
            <div key={index} className="category-block">
              <h2>{category.title}</h2>
              <ul>
                {category.companies.map((company, idx) => (
                  <li key={idx}>{company}</li>
                ))}
              </ul>
            </div>
          ))}
        </Carousel>
      </div>
      
    </div>
  );
}

export default Home2;
