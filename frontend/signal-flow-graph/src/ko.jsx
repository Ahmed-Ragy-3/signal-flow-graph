import React, { useState, useEffect, StrictMode } from "react";

import "./App.css";
import ReactFlow, {
  addEdge,
  useEdgesState,
  useNodesState,
  Background,
  Controls,
  useReactFlow,
  ReactFlowProvider,
} from "reactflow";
import { useCallback,useMemo } from "react";

import "reactflow/dist/style.css";

import Node from "./Node";
import queueicon from "./assets/queue.svg";
import counter from "./assets/counter.svg";
import redoicon from "./assets/redo.svg";
import newsim from "./assets/play.svg";
import machineicon from "./assets/machine.svg";
import deleteicon from "./assets/trash (1).svg";
import HandleSimulate from "./HandleSimulate";
import stopicon from './assets/stop.svg';
import Replay from "./Replay";
import Simulate from "./StopSimulate";
import PositionableEdge from "./PositionableEdge";
const nodeTypes = { node: Node };

const SimulationFlow = () => {
  const [nodes, setNodes, onNodesChange] = useNodesState([]);
  const [edges, setEdges, onEdgesChange] = useEdgesState([]);
  const [NodeID, setNodeID] = useState(1);
  const [queuemenu, setqueuemenu] = useState(false); // Initialize shape state inside the component
  const [menu, setMenu] = useState(false); // Initialize as boolean
  const [numberOfProducts, setNumberOfProducts] = useState(0); // Correct state name

  const deleteall = () => {
    setNodes([]);
    setEdges([]);
    setNodeID(1); 
    Simulate();
  };

  const handleNumberOfProductsChange = (e) => {
    setNumberOfProducts(e.target.value);
  };

  const edgeTypes = useMemo(() => ({
    "custom-edge": PositionableEdge,
  }), []);

const onConnect = useCallback(
  (params) => {
    const { source, target } = params;
    const { screenToFlowPosition } = useReactFlow();

    // Create a custom edge with proper initialization
    const customEdge = {
      ...params,
      id: String(Math.random()),
      markerEnd: { type: "arrowclosed", color: "#808080" },
      type: "custom-edge",
      style: { stroke: "#777", strokeWidth: 2 },
      data: {
        type: "straight", // or "bezier" or "smoothstep"
        positionHandlers: [],
        screenToFlowPosition, // Pass the transformation function
      },
    };

    setEdges((eds) => addEdge(customEdge, eds));
  },
  [setEdges]
);
  // const onConnect = useCallback((params) => {
  //   const { source, target } = params;

  //   const sourceNode = nodes.find((node) => node.id === source);
  //   const targetNode = nodes.find((node) => node.id === target);

  //   if (!sourceNode || !targetNode) {
  //     console.error("Source or target node not found.");
  //     return;
  //   }

    
  //   const customEdge = {
  //     ...params,
  //     markerEnd: { type: "arrowclosed", color: "#808080" },
  //     type: "custom-edge",
  //     data: {
  //       positionHandlers: [
  //         // {
  //         //   x: (sourceNode.position.x + targetNode.position.x) / 2,
  //         //   y: (sourceNode.position.y + targetNode.position.y) / 2,
  //         //   active: -1,
  //         // },
  //       ],
  //     },
  //   };

  //   setEdges((eds) => {
  //     return addEdge(customEdge, eds);
  //   });
  // }, [nodes, setEdges]);
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
        id: `Input`,
        position: canvasPosition,
        data: { label: `Input` },
        type: "node",
      };
      const findNode = nodes.find((x)=> x.id === node.id)
      if(findNode){
        alert(`Cannot have multible start`);
        return
      }
      else{
        setFloatingNode(node)
      }
    } else if (type === "end") {
      node = {
        id: `Output`,
        position: canvasPosition,
        data: { label: `Output` },
        type: "node",
      };
      const findNode2 = nodes.find((x)=> x.id === node.id)
      if(findNode2){
        alert(`Cannot have multible end`);
        return
      }
      else{
        setFloatingNode(node)
      }
    } else {
       node = {
        id: `v${NodeID}`,
        position: canvasPosition,
        data: { label: `v${NodeID}`},
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
      <div className="bar">
        
        <button
          className="icon"
          onClick={() => {
            setqueuemenu(!queuemenu);
            setMenu(false);
          }}
        >
          <img src={queueicon} alt="queue" />
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
              Start
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
              End
            </button>
          </menu>
        )}

       
        {/* Conditional rendering of the menu */}
       
        <button
          className="icon"
          onClick={() => {
            setMenu(false);
            setqueuemenu(false);
            HandleSimulate(nodes, edges, numberOfProducts,setNodes);
          }}
        >
          <img src={newsim} alt="new" />
        </button>
        <button
          className="icon"
          onClick={() => {
            setMenu(false);
            setqueuemenu(false);
            Replay(setNodes);
          }}
        >
          <img src={redoicon} alt="redo" />
        </button>
        <button className="icon"onClick={Simulate}>
        <img src={stopicon} alt="stop" />
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
        fitView
      >
        <Background />
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
