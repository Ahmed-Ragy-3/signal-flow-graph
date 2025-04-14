import { Handle } from "reactflow";

function Node({data}) {
    return (
        <div className="node">
  
        <p>{`${data.label}`}</p>
   
        

        {data.label !== "R(s)" && (
        <Handle type="target" position="left" id={`left-${data.id}`} className="handle" />
      )}
      
      {data.label !== "C(s)" && (
        <Handle type="source" position="right" id={`right-${data.id}`} className="handle" />
      )}

      <Handle type="target" position="top" id={`top-target-${data.id}`} className="handle" />
      <Handle type="source" position="top" id={`top-source-${data.id}`} className="handle" />
      <Handle type="target" position="bottom" id={`bottom-target-${data.id}`} className="handle" />
      <Handle type="source" position="bottom" id={`bottom-source-${data.id}`} className="handle" />
        </div>

    );
}
export default Node;