import React, { useState } from "react";
import { MathJax, MathJaxContext } from "better-react-mathjax";

const Sidebar = ({ formula, forwardPath, loops, untouchedLoops,delta,isOpen,setIsOpen }) => {
  //const [isOpen, setIsOpen] = useState(false);

  return (
    <MathJaxContext>
      {/* Toggle Button (Positioned Relative to Sidebar) */}
      <button
        className={`menu-btn ${isOpen ? "open" : ""}`}
        onClick={() => setIsOpen(!isOpen)}
      >
        {isOpen ? "✖" : "☰"}
      </button>

      {/* Sidebar (Animated with Transition) */}
      <div className={`sidebar ${isOpen ? "open" : ""}`}>
        {/* Centered Title */}
        <h2 style={{ textAlign: "center" }}>Answer</h2>

        {/* Forward Path Section */}
        <div className="section">
  <h3>Forward Path</h3>
  {forwardPath.map((path, index) => (
    <MathJax key={index}>
      <p>{`\\( P${index+1} \\)`}</p>
      <p>{`\\( Nodes : ${path.nodes} \\)`}</p>
      <p>{`\\( Gain : ${path.gain} \\)`}</p>
      <p>{`\\( delta : ${path.delta} \\)`}</p>
      
    </MathJax>
  ))}
</div>

        {/* Loops Section */}
<div className="section">
  <h3>Loops</h3>
  {loops.map((loop, index) => (
    <MathJax key={index}>
      <p>{`\\( ${loop.name} \\)`}</p>
      <p>{`\\( Nodes : ${loop.nodes} \\)`}</p>
      <p>{`\\( Gain : ${loop.gain} \\)`}</p>
    </MathJax>
  ))}
</div>
        {/* Untouched Loops Section */}
        <div className="section">
  <h3>Non-Touching Loops</h3>
  {untouchedLoops.slice(2).map((loopGroup, groupIndex) => (
    <div key={groupIndex} className="loop-group">
      <h5>{`${groupIndex + 2}-Non-Touching Loops`}</h5>
      {loopGroup.map((loop, loopIndex) => (
        <MathJax key={loopIndex}>
         <p>{`\\( \\text{${loop.name}}: ${loop.gain} \\)`}</p>
        </MathJax>
      ))}
    </div>
  ))}
</div>

        {/* delta Section */}
        <div className="section">
  <h3>Delta</h3>
    <MathJax>
      <p>{`\\(${delta} \\)`}</p>
    </MathJax>
  
</div>

        {/* formula Section */}
        <div className="section">
  <h3>Formula</h3>
  <MathJax>
    <p>{`\\( \\displaystyle \\frac{${formula.numerator}}{${formula.denomenator}} \\)`}</p>
  </MathJax>
</div>
      </div>
    </MathJaxContext>
  );
};

export default Sidebar;