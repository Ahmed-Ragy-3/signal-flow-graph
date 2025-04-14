import React, { useState } from "react";
import { MathJax, MathJaxContext } from "better-react-mathjax";

const Sidebar = ({ expression, forwardPath, loops, untouchedLoops }) => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <MathJaxContext>
      {/* Toggle Button (Positioned Relative to Sidebar) */}
      <button
        className={`menu-btn ${isOpen ? "open" : ""}`}
        onClick={() => setIsOpen(!isOpen)}
      >
        ☰
      </button>

      {/* Sidebar (Animated with Transition) */}
      <div className={`sidebar ${isOpen ? "open" : ""}`}>
        {/* Centered Title */}
        <h2 style={{ textAlign: "center" }}>Answer</h2>

        {/* Forward Path Section */}
        <div className="section">
          <h3>Forward Path</h3>
          <MathJax>
            <p>{`\\( ${forwardPath} \\)`}</p>
          </MathJax>
        </div>

        {/* Loops Section */}
        <div className="section">
          <h3>Loops</h3>
          <MathJax>
            <p>{`\\( ${loops} \\)`}</p>
          </MathJax>
        </div>

        {/* Untouched Loops Section */}
        <div className="section">
          <h3>Untouched Loops</h3>
          <MathJax>
            <p>{`\\( ${untouchedLoops} \\)`}</p>
          </MathJax>
        </div>

        {/* Expression Section */}
        <div className="section">
          <h3>Expression</h3>
          <MathJax>
            <p>{`\\( \\frac{${expression.num}}{${expression.den}} \\)`}</p>
          </MathJax>
        </div>
      </div>
    </MathJaxContext>
  );
};

export default Sidebar;