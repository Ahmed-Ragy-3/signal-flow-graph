import React, { useState, useCallback, useEffect } from "react";
import {
  BaseEdge,
  EdgeLabelRenderer,
  useReactFlow,
} from "reactflow";
import {
  Box,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
} from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import InboxIcon from "@mui/icons-material/Inbox";
import ArrowRightAltIcon from "@mui/icons-material/ArrowRightAlt";
import NextPlanIcon from "@mui/icons-material/NextPlan";
import FlipCameraAndroidIcon from '@mui/icons-material/FlipCameraAndroid';
import { MathJax, MathJaxContext } from "better-react-mathjax";

export default function CustomEdge({
  id,
  sourceX,
  sourceY,
  targetX,
  targetY,
  markerEnd,
  label,
  data
}) {
  const { setEdges } = useReactFlow();

  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [inputName, setInputName] = useState(false);
  const [tempLabel, setTempLabel] = useState(label);
  const [edgeLabel, setEdgeLabel] = useState(label);
  const [flipArc, setFlipArc] = useState(data?.flipArc || false);
  const [isArc, setIsArc] = useState(false);

  useEffect(() => {
    setIsArc(data?.isArc || false);
    setFlipArc(data?.flipArc || false);
    setEdgeLabel(label);
    setTempLabel(label);
  }, [data?.isArc, data?.flipArc, label]);

  const updateEdgeLabel = useCallback(() => {
    setEdges((edges) =>
      edges.map((edge) =>
        edge.id === id
          ? {
              ...edge,
              label: tempLabel,
              data: { ...edge.data, isArc },
            }
          : edge
      )
    );
    setEdgeLabel(tempLabel);
  }, [setEdges, tempLabel, id, isArc]);

  const handleChangeShape = () => {
    const newArc = !isArc;
    setIsArc(newArc);
    setEdges((edges) =>
      edges.map((edge) =>
        edge.id === id
          ? {
              ...edge,
              data: {
                ...edge.data,
                isArc: newArc,
              },
            }
          : edge
      )
    );
    setIsMenuOpen(false);
  };

  const handleFlipArc = () => {
    const newFlip = !flipArc;
    setFlipArc(newFlip);
    setEdges((edges) =>
      edges.map((edge) =>
        edge.id === id
          ? {
              ...edge,
              data: {
                ...edge.data,
                isArc,
                flipArc: newFlip,
              },
            }
          : edge
      )
    );
    setIsMenuOpen(false);
  };

  const handleEdgeClick = (e) => {
    e.stopPropagation();
    setIsMenuOpen(true);
    setInputName(false);
  };

  const handleCloseMenus = useCallback(() => {
    setIsMenuOpen(false);
    setInputName(false);
  }, []);

  useEffect(() => {
    const handleOutsideClick = () => {
      handleCloseMenus();
    };
    document.addEventListener("click", handleOutsideClick);
    return () => document.removeEventListener("click", handleOutsideClick);
  }, [handleCloseMenus]);

  const radius = Math.abs(sourceX - targetX) / 2 || 1;
  const edgePath = isArc
    ? `M ${sourceX} ${sourceY} A ${radius} ${radius} 0 0 ${flipArc ? 0 : 1} ${targetX} ${targetY}`
    : `M ${sourceX} ${sourceY} L ${targetX} ${targetY}`;

  let labelX = (sourceX + targetX) / 2;
  let labelY = (sourceY + targetY) / 2;
  if (isArc) {
    const arcOffset = radius ; 
    if (sourceX<targetX){
   
      labelY += flipArc ? arcOffset : -arcOffset;
    }
    else {
      labelY -= flipArc ? arcOffset : -arcOffset;
    }
 
  }

  return (
    <>
      <BaseEdge
        id={id}
        path={edgePath}
        markerEnd={markerEnd}
        style={{ stroke: "#808080", strokeWidth: 2 }}
      />

      <EdgeLabelRenderer>
        <div
          onClick={handleEdgeClick}
          style={{
            position: "absolute",
            transform: `translate(-50%, -80%) translate(${labelX}px, ${labelY}px)`,
            pointerEvents: "all",
            fontSize: "13px",
            color: "white",
            background: "transparent",
            padding: "2px 5px",
            zIndex: 1000,
            cursor: "pointer",
            borderRadius: "4px",
            
          }}
        >
          <MathJaxContext>  
          <MathJax>
           <p>{`\\( ${edgeLabel} \\)`}</p>
          </MathJax>
          </MathJaxContext>
        </div>

        {/* Menu */}
        {isMenuOpen && !inputName && (
          <div
            className="nodrag nopan"
            style={{
              position: "absolute",
              transform: `translate(${sourceX}px, ${labelY}px)`,
              pointerEvents: "all",
              zIndex: 1000,
            }}
            onClick={(e) => e.stopPropagation()}
          >
            <Box
              sx={{
                bgcolor: "#ffffff",
                borderRadius: "4px",
                boxShadow: 3,
                p: 1,
                position: "absolute",
                top: "10px",
                left: "0",
                zIndex: 1000,
              }}
            >
              <List dense disablePadding>
                <ListItem disablePadding>
                  <ListItemButton
                    onClick={() => {
                      setInputName(true);
                      setIsMenuOpen(false);
                    }}
                  >
                    <ListItemIcon sx={{ minWidth: "auto", mr: 0.5 }}>
                      <InboxIcon style={{ fontSize: 12 }} />
                    </ListItemIcon>
                    <ListItemText
                      primary="Rename"
                      primaryTypographyProps={{ fontSize: "8px", color: "black" }}
                    />
                  </ListItemButton>
                </ListItem>

                <ListItem disablePadding>
                  <ListItemButton
                    onClick={() =>
                      setEdges((edges) => edges.filter((edge) => edge.id !== id))
                    }
                  >
                    <ListItemIcon sx={{ minWidth: "auto", mr: 0.5 }}>
                      <DeleteIcon style={{ fontSize: 12 }} />
                    </ListItemIcon>
                    <ListItemText
                      primary="Delete"
                      primaryTypographyProps={{ fontSize: "8px", color: "black" }}
                    />
                  </ListItemButton>
                </ListItem>

                <ListItem disablePadding>
                  <ListItemButton onClick={handleChangeShape}>
                    <ListItemIcon sx={{ minWidth: "auto", mr: 0.5 }}>
                      {!isArc ? (
                        <NextPlanIcon style={{ fontSize: 12 }} />
                      ) : (
                        <ArrowRightAltIcon style={{ fontSize: 12 }} />
                      )}
                    </ListItemIcon>
                    <ListItemText
                      primary={isArc ? "Straight" : "Curve"}
                      primaryTypographyProps={{ fontSize: "8px", color: "black" }}
                    />
                  </ListItemButton>
                </ListItem>

                <ListItem disablePadding>
                  <ListItemButton onClick={handleFlipArc}>
                    <ListItemIcon sx={{ minWidth: "auto", mr: 0.5 }}>
                      <FlipCameraAndroidIcon style={{ fontSize: 12 }} />
                    </ListItemIcon>
                    <ListItemText
                      primary="Flip"
                      primaryTypographyProps={{ fontSize: "8px", color: "black" }}
                    />
                  </ListItemButton>
                </ListItem>
              </List>
            </Box>
          </div>
        )}

        {/* Rename input */}
        {inputName && (
          <div
            style={{
              position: "absolute",
              transform: `translate(${sourceX}px, ${labelY}px)`,
              background: "white",
              border: "1px solid gray",
              borderRadius: "5px",
              padding: "5px",
              boxShadow: "0px 2px 5px rgba(0, 0, 0, 0.2)",
              fontSize: "8px",
              zIndex: 2000,
              width: "110px",
              pointerEvents: "all",
            }}
            onClick={(e) => e.stopPropagation()}
          >
            <input
              type="text"
              value={tempLabel}
              onChange={(e) => setTempLabel(e.target.value)}
              autoFocus
              style={{
                fontSize: "10px",
                padding: "2px",
                border: "1px solid gray",
                borderRadius: "3px",
                width: "100%",
              }}
            />
            <div style={{ display: "flex", justifyContent: "space-between", marginTop: "4px" }}>
              <button
                onClick={() => {
                  updateEdgeLabel();
                  handleCloseMenus();
                }}
                style={{
                  background: "lightgreen",
                  border: "none",
                  padding: "2px 4px",
                  fontSize: "8px",
                  cursor: "pointer",
                }}
              >
                ✅
              </button>
              <button
                onClick={handleCloseMenus}
                style={{
                  background: "lightcoral",
                  border: "none",
                  padding: "2px 4px",
                  fontSize: "8px",
                  cursor: "pointer",
                }}
              >
                ❌
              </button>
            </div>
          </div>
        )}
      </EdgeLabelRenderer>
    </>
  );
}
