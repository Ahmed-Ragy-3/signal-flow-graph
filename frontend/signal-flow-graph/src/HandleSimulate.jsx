import axios from "axios";
async function HandleSimulate(nodes,edges,setAnswerDto){
    var normalNodes = nodes.map((node) => node.id)
  
    const input = nodes.find((node) => node.id === "R(s)");
    const output = nodes.find((node) => node.id === "C(s)");
    
    const graphdto = {inputNode : input.id , outputNode:output.id,nodes:normalNodes,edges:edges};
    // console.log(edges)
    // console.log(normalNodes)
    // console.log(input.id)
    // console.log(output.id)
    console.log(graphdto)


       
        try{
            const response = await axios.post(`http://localhost:8080/graph/solve`,graphdto);
            if(response.status === 200){
                console.log("done");
                console.log(response.data);
                setAnswerDto(response.data)
            }

        }catch(error){
            console.log(error.response.data)
        }  
    
}
export default HandleSimulate;