import React from "react";

const Navbar = () => {
  const handleRedirect = (url) => {
    window.location.href = url; // Redirect to the external URL
  };

  return (
    <nav>
      <ul>
        <li onClick={() => handleRedirect("https://cumminscollege.org/placements/placement-statistics/")}>
          Placement Statistics
        </li>
      </ul>
    </nav>
  );
};

export default Navbar;