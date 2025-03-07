import {
    BaseEdge,
    EdgeLabelRenderer,
    getStraightPath,
    useReactFlow,
  } from "reactflow";
  import { useCallback, useState } from "react";
  
  export default function CustomEdge({ id, sourceX, sourceY, targetX, targetY, markerEnd ,label}) {
    const {setEdges } = useReactFlow();
    const [isMenuOpen, setIsMenuOpen] = useState(false);
    const [inputName, setInputName] = useState(false);
    const [tempLabel, setTempLabel] = useState(label); 
    const updateEdgeLabel = useCallback(() => {
        setEdges((edges) =>
          edges.map((edge) =>
            edge.id === id ? { ...edge, label: tempLabel } : edge
          )
        );
      }, [setEdges, tempLabel, id]); 
      
    const [edgePath, labelX, labelY] = getStraightPath({
      sourceX,
      sourceY,
      targetX,
      targetY,
    });
    return (
      <>
     
        <BaseEdge id={id} path={edgePath} markerEnd={markerEnd} style={{ stroke: "#808080", strokeWidth: 2 }} />

        <EdgeLabelRenderer>
            <label  style={{
                position: "absolute",
                transform: `translate(-50%, -50%) translate(${labelX}px, ${labelY}px)`,
                pointerEvents: "all",
                display: "flex",
                fontSize:"13px",
                alignItems: "center",
                width: "fit-content",
                color: "black",
                borderRadius:"4px",
                background:"white",

              }}> {label}</label>
           
          <div

            style={{
              position: "absolute",
              top: 0,
              transform: `translate(${sourceX}px, ${labelY}px)`,
              pointerEvents: "all",
              display: "flex",
              alignItems: "center",
              width: "fit-content",
            }}
            className="nodrag nopan"
          >
           
            <button
              onClick={() => setIsMenuOpen(!isMenuOpen)}
              style={{
                background: "gray",
                color: "white",
                border: "none",
                padding: "2px 5px",
                fontSize: "8px",
                cursor: "pointer",
                borderRadius: "3px",
              }}
            >
              ⋮
            </button>
  
           
            {inputName ? (
              <div
                style={{
                  position: "absolute",
                  top: "20px",
                  left: "0",
                  background: "white",
                  border: "1px solid gray",
                  borderRadius: "5px",
                  padding: "5px",
                  boxShadow: "0px 2px 5px rgba(0, 0, 0, 0.2)",
                  fontSize: "8px",
                  display: "flex",
                  flexDirection: "column",
                  gap: "5px",
                  zIndex: 10,
                  width: "80px",
                }}
              >
                <input
                  type="text"
                  value={tempLabel}
                  onChange={(e) => setTempLabel(e.target.value)}
                  style={{
                    fontSize: "10px",
                    padding: "2px",
                    border: "1px solid gray",
                    borderRadius: "3px",
                    width: "90%",
                  }}
                />
                <div style={{ display: "flex", justifyContent: "space-between" }}>
                  <button
                    onClick={() => {
                        updateEdgeLabel();
                      setInputName(false);
                      setIsMenuOpen(false);
                    }}
                    style={{
                      background: "lightgreen",
                      border: "none",
                      padding: "2px",
                      cursor: "pointer",
                      fontSize: "8px",
                    }}
                  >
                    ✅save
                  </button>
                  <button
                    onClick={() => {
                      setInputName(false);
                      setIsMenuOpen(false);
                    }}
                    style={{
                      background: "lightcoral",
                      border: "none",
                      padding: "2px",
                      cursor: "pointer",
                      fontSize: "8px",
                    }}
                  >
                    ❌cancel
                  </button>
                </div>
              </div>
            ) : (
              isMenuOpen && (
                <div
                  style={{
                    position: "absolute",
                    top: "20px",
                    left: "0",
                    background: "white",
                    border: "1px solid gray",
                    borderRadius: "5px",
                    padding: "5px",
                    boxShadow: "0px 2px 5px rgba(0, 0, 0, 0.2)",
                    fontSize: "8px",
                    display: "flex",
                    flexDirection: "column",
                    gap: "2px",
                    zIndex: 10,
                    width: "100px",
                  }}
                >
                  <button
                    onClick={() => {
                      setInputName(true);
                      setIsMenuOpen(false);
                    }}
                    style={{
                      background: "lightblue",
                      border: "none",
                      padding: "2px",
                      cursor: "pointer",
                      fontSize: "8px",
                      textAlign: "left",
                      width: "100%",
                    }}
                  >
                    ✏ Change Name
                  </button>
                  <button
                    onClick={() => setEdges((edges) => edges.filter((edge) => edge.id !== id))}
                    style={{
                      background: "lightcoral",
                      border: "none",
                      padding: "2px",
                      cursor: "pointer",
                      fontSize: "8px",
                      textAlign: "left",
                      width: "100%",
                    }}
                  >
                    ❌ Delete
                  </button>
                </div>
              )
            )}
          </div>
        </EdgeLabelRenderer>
      </>
    );
  }
  