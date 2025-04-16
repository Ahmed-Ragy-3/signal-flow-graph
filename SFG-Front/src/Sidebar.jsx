import React, { useState, useEffect } from "react";
import { MathJax, MathJaxContext } from "better-react-mathjax";

const Sidebar = ({ formula, forwardPath, loops, untouchedLoops,delta,solution,isOpen,setIsOpen }) => {

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
      <p>{`\\( Gain : ${path.gain.replaceAll("*","·")} \\)`}</p>
      <p>{`\\( Δ${index+1} : ${path.delta.replaceAll("[","(").replaceAll("]", ")").replaceAll("*","·")} \\)`}</p>
      {index != forwardPath.length-1 && <br></br>}
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
      <p>{`\\( Gain : ${loop.gain.replaceAll("*","·")} \\)`}</p>
      {index != loop.length-1 && <br></br>}
    </MathJax>
  ))}
</div>
        {/* Untouched Loops Section */}
        <div className="section">
  <h3>Non-Touching Loops</h3>
  {untouchedLoops.length<=2 && <p>No non-touching loops</p>}
  {untouchedLoops.slice(2).map((loopGroup, groupIndex) => (
    <div key={groupIndex} className="loop-group">
      <h5>{`${groupIndex + 2}-Non-Touching Loops`}</h5>
      {loopGroup.map((loop, loopIndex) => (
        <MathJax key={loopIndex}>
         <p>{`\\( \\text{${loop.name}}: ${loop.gain.replaceAll("*","·")} \\)`}</p>
         <br></br>
        </MathJax>
      ))}
    </div>
  ))}
</div>

        {/* delta Section */}
        <div className="section">
  <h3>Delta</h3>
    <MathJax>
      <p>{`\\(${delta.replaceAll("[","(").replaceAll("]", ")").replaceAll("*","·")} \\)`}</p>
    </MathJax>
  
</div>

        {/* formula Section */}
        <div className="section">
  <h3>Formula</h3>
  <MathJax>
  <p style={{display : "inline-block" }}>{`\\( \\displaystyle \\frac{C(s)}{R(s)} = \\frac{${formula.numerator.replaceAll("[","(").replaceAll("]", ")").replaceAll("*","·")}}{${formula.denomenator.replaceAll("[","(").replaceAll("]", ")").replaceAll("*","·")}} \\)`}</p>
  {solution && <p style={{display : "inline-block"}}>{`\\( \\text{ = } ${solution} \\)`}</p>}
    
   
  </MathJax>
</div>
      </div>
    </MathJaxContext>
  );
};

export default Sidebar;