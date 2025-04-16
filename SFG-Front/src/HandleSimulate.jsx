import axios from "axios";
async function HandleSimulate(nodes,edges,setAnswerDto, setSnackbarMessage, setOpenSnackbar,setSnackbarSeverity,setSideBarOpen){
    var normalNodes = nodes.map((node) => node.id)
  
    const input = nodes.find((node) => node.id === "R(s)");
    const output = nodes.find((node) => node.id === "C(s)");

    const triggerSnackbarAndClose = () => {
        setOpenSnackbar(true);
        setTimeout(() => {
          setOpenSnackbar(false);
        }, 1500);
      };

    if(!input || !output){
        setSnackbarMessage("No Input or Output Node");
        setSnackbarSeverity("error");
        triggerSnackbarAndClose();
        return;
    }

 
    
    const graphdto = {inputNode : input.id , outputNode:output.id,nodes:normalNodes,edges:edges};
    console.log(graphdto)


       
        try{
            const response = await axios.post(`http://localhost:8080/graph/solve`,graphdto);
            if(response.status === 200){
                console.log("done");
                console.log(response.data);
                setAnswerDto(response.data) 
                setSideBarOpen(true); 
                setSnackbarMessage("Signal Flow Graph Evaluated Successfully"); 
                setSnackbarSeverity("success");
                triggerSnackbarAndClose();
                
            }

        }catch(error){
            setSnackbarMessage(error.response.data);
            setSnackbarSeverity("error");
            triggerSnackbarAndClose();
            return;
        }  
    
}
export default HandleSimulate;