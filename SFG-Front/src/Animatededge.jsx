import CustomEdge from "./CustomEdge";
import { AnimatedSvgEdge } from "@/components/animated-svg-edge";
const defaultNodes = [
    {
      id: "M1",
      position: { x: 200, y: 200 },
      data: { label: "A" },
    },
    {
      id: "StartQ1",
      position: { x: 400, y: 400 },
      data: { label: "B" },
    },
  ];
const defaultEdges = [
    {
      id: "1->2",
      source: "1",
      target: "2",
      type: 'custom-edge',
      label: "1",
    } 
  ];
 
  const edgeTypes = {
    'custom-edge': CustomEdge,
  };
   