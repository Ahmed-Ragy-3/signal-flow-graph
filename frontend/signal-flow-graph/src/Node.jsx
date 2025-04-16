import { Handle } from "reactflow";
import { MathJax, MathJaxContext } from "better-react-mathjax";

function Node({data}) {
    return (
        <div className="node">
  
  <MathJaxContext>  
          <MathJax>
           <p>{`\\( ${data.label} \\)`}</p>
          </MathJax>
          </MathJaxContext>
   
        

        {data.label !== "R(s)" && (
        <Handle type="target" position="left" id={`left-${data.label}`} className="handle" />
      )}
      
      {data.label !== "C(s)" && (
        <Handle type="source" position="right" id={`right-${data.label}`} className="handle" />
      )}

      <Handle type="target" position="top" id={`top-target-${data.label}`} className="handle" />
      <Handle type="source" position="top" id={`top-source-${data.label}`} className="handle" />
      <Handle type="target" position="bottom" id={`bottom-target-${data.label}`} className="handle" />
      <Handle type="source" position="bottom" id={`bottom-source-${data.label}`} className="handle" />
        </div>

    );
}
export default Node;