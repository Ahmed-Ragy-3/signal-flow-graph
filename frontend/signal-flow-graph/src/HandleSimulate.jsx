import axios from "axios";
async function HandleSimulate(nodes,edges,numberOfProducts){
    const normalNodes = nodes.filter((node)=> (node.type === "node" && node.id!=="Input" && node.id!=="Output"));
    const input = nodes.find((node) => node.id === "Input");
    const output = nodes.find((node) => node.id === "Output");
    
    const graphdto = {inputNode : input , outputNode:output,nodes:normalNodes,edges:edges};
    console.log(edges)
    console.log(normalNodes)
    console.log(input)
    console.log(output)
    console.log(numberOfProducts)


       
        // try{
        //     const response = await axios.post(`http://localhost:8080/simulate`,simulationDto);
        //     if(response.status === 200){
        //         console.log("done");
        //     }

        // }catch(error){
        //     alert(error.response.data);
        // }  
    
}
export default HandleSimulate;