import React, { useState, useEffect } from "react";
import "./App.css";
import ReactFlow, {
  addEdge,
  useEdgesState,
  useNodesState,
  Background,
  Controls,
  useReactFlow,
  ReactFlowProvider,ConnectionMode,
} from "reactflow";
import CustomEdge from "./CustomEdge";
import "reactflow/dist/style.css";
import {
  Snackbar,
  Alert,
} from "@mui/material";

import Node from "./Node";
import nodeicon from "./assets/node.svg";
import newsim from "./assets/play.svg";
import deleteicon from "./assets/trash (1).svg";
import HandleSimulate from "./HandleSimulate";

import Sidebar from "./Sidebar";

const nodeTypes = { node: Node };
const edgeTypes = {
  "custom-edge": CustomEdge,
};

const SimulationFlow = () => {
  const [nodes, setNodes, onNodesChange] = useNodesState([]);
  const [edges, setEdges, onEdgesChange] = useEdgesState([]);
  const [NodeID, setNodeID] = useState(1);
  const [queuemenu, setqueuemenu] = useState(false); // Initialize shape state inside the component
  const [menu, setMenu] = useState(false); // Initialize as boolean
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState("success");


  const deleteall = () => {
    setNodes([]);
    setEdges([]);
    setNodeID(1);
    setAnswerDto(null);
  };

  const onConnect = (params) => {
    const { source, target, sourceHandle, targetHandle } = params;

    const sourceNode = nodes.find((node) => node.id === source);
    const targetNode = nodes.find((node) => node.id === target);

    if (!sourceNode || !targetNode) {
      console.error("Source or target node not found.");
      return;
    }
        // Validate the connection type - ensure source handle is actually a source and target is a target
        const isSourceHandleValid = sourceHandle && sourceHandle.includes('source');
        const isTargetHandleValid = targetHandle && targetHandle.includes('target');
        
        // Check for invalid connections
        if (
            (sourceHandle && sourceHandle.includes('target')) || 
            (targetHandle && targetHandle.includes('source'))
        ) {
            console.error("Invalid connection: Mismatched handle types");
            return;
        }
    const customEdge = {
      ...params,
      markerEnd: { type: "arrowclosed", color: "#808080" },
      type: "custom-edge",
      label:'1'
    };
    setEdges((eds) => addEdge(customEdge, eds));
  };

  const [isSideBarOpen,setSideBarOpen] = useState(false);

  const [AnswerDto, setAnswerDto] = useState(null);

  // State for floating node
  const [floatingNode, setFloatingNode] = useState(null);
  const { screenToFlowPosition } = useReactFlow();

  // Mouse move handler
  const handleMouseMove = (event) => {
    if (floatingNode) {
      const canvasPosition = screenToFlowPosition({
        x: event.clientX,
        y: event.clientY,
      });
      setFloatingNode({
        ...floatingNode,
        position: canvasPosition,
      });
    }
  };

  // Mouse click handler
  const handleMouseClick = () => {
    if (floatingNode) {
      setNodes((nodes) => [...nodes, floatingNode]);
      setFloatingNode(null);
    }
  };

  // creating queue node
  const handleCreateNode = (event, type) => {
    const canvasPosition = screenToFlowPosition({
      x: event.clientX,
      y: event.clientY,
    });
    let node = null;
    if (type === "start") {
      node = {
        id: `R(s)`,
        position: canvasPosition,
        data: { label: `R(s)` },
        type: "node",
      };
      const findNode = nodes.find((x) => x.id === node.id);
      if (findNode) {
        alert(`Cannot have multible start`);
        return;
      } else {
        setFloatingNode(node);
      }
    } else if (type === "end") {
      node = {
        id: `C(s)`,
        position: canvasPosition,
        data: { label: `C(s)` },
        type: "node",
      };
      const findNode2 = nodes.find((x) => x.id === node.id);
      if (findNode2) {
        alert(`Cannot have multible end`);
        return;
      } else {
        setFloatingNode(node);
      }
    } else {
      node = {
        id: `V${NodeID}`,
        position: canvasPosition,
        data: { label: `V${NodeID}` },
        type: "node",
      };
      setFloatingNode(node);
      setNodeID(NodeID + 1);
    }
  };
  return (
    <div
      className="flow"
      onMouseMove={handleMouseMove}
      onClick={handleMouseClick}
    >
           <Snackbar
          open={openSnackbar}
          autoHideDuration={2000}
          onClose={() => setOpenSnackbar(false)}
          anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
        >
          <Alert
            onClose={() => setOpenSnackbar(false)}
            severity={snackbarSeverity}
            variant="filled"
          >
            {snackbarMessage}
          </Alert>
        </Snackbar>
      
     {AnswerDto && <Sidebar formula={AnswerDto.formula} forwardPath={AnswerDto.forwardPaths} loops={AnswerDto.loops} untouchedLoops={AnswerDto.nonTouchingLoops} delta={AnswerDto.delta} isOpen={isSideBarOpen} setIsOpen={setSideBarOpen} />}


      <div className="bar">
        <button
          className="icon"
          onClick={() => {
            setqueuemenu(!queuemenu);
            setMenu(false);
          }}
        >
          <img src={nodeicon} alt="node" />
        </button>
        {queuemenu && (
          <menu className="menudisplay">
            <button
              className="settype"
              onClick={(event) => {
                setqueuemenu(false);
                handleCreateNode(event, "start");
              }}
            >
              Input
            </button>
            <button
              className="settype"
              onClick={(event) => {
                setqueuemenu(false);
                handleCreateNode(event);
              }}
            >
              Normal
            </button>
            <button
              className="settype"
              onClick={(event) => {
                setqueuemenu(false);
                handleCreateNode(event, "end");
              }}
            >
              Output
            </button>
          </menu>
        )}

        {/* Conditional rendering of the menu */}

        <button
          className="icon"
          onClick={() => {
            setMenu(false);
            setqueuemenu(false);
            setSideBarOpen(true);
            HandleSimulate(nodes, edges,setAnswerDto,setSnackbarMessage,setOpenSnackbar,setSnackbarSeverity);
          }}
        >
          <img src={newsim} alt="new" />
        </button>
        <button className="icon" onClick={deleteall}>
          <img src={deleteicon} alt="delete" />
        </button>
      </div>
      <ReactFlow
        nodes={floatingNode ? [...nodes, floatingNode] : nodes}
        edges={edges}
        onNodesChange={onNodesChange}
        onEdgesChange={onEdgesChange}
        onConnect={onConnect}
        nodeTypes={nodeTypes}
        edgeTypes={edgeTypes}
        connectionMode={ConnectionMode.Loose} // Allow overlapping edges
        fitView
      >
        
        <Controls />
      </ReactFlow>
    </div>
  );
};

const WrappedSimulationFlow = () => (
  <ReactFlowProvider>
    <SimulationFlow />
  </ReactFlowProvider>
);

export default WrappedSimulationFlow;
