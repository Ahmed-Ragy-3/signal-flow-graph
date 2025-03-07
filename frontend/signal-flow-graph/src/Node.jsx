import { Handle } from "reactflow";
function Node({data}) {
    return (
        <div className="node">
        <p>{data.label}</p>
       {data.label!="Input"&& <Handle type="target" position="left" id="left" className="handle" />}
       {data.label!="Input"&& <Handle type="source" position="left" id="left" className="handle" />}
        {data.label!="Output"&&  <Handle type="source" position="right" id="right" className="handle" />}
        {data.label!="Input" && data.label!="Output" && <Handle type="target" position="top" id="top" className="handle" />}
        {data.label!="Input" && data.label!="Output" && <Handle type="source" position="top" id="top" className="handle" />}
        {data.label!="Input" && data.label!="Output" && <Handle type="target" position="bottom" id="bottom" className="handle" />}
        {data.label!="Input" && data.label!="Output" && <Handle type="source" position="bottom" id="bottom" className="handle" />} </div>

    );
}
export default Node;