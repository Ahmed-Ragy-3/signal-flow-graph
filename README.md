# **Signal Flow Graphs & Routh Stability Criterion**  

### **Overview**  
This project is a web-based tool for analyzing **Signal Flow Graphs (SFGs)** and **system stability using Routh’s Stability Criterion**. It provides an interactive **graphical interface** for drawing signal flow graphs and computing system properties like **forward paths, loops, and transfer functions**. Additionally, it determines the **stability of a system** based on its characteristic equation.  

### **Tech Stack**  
- **Frontend:** React.js (with libraries for visualization)  
- **Backend:** Java Spring Boot (REST API)    
- **Visualization Library:** react-flow  

---

## **Features**  

### **🟢 Part 1: Signal Flow Graph Analysis**  
✅ **Graphical Interface** for drawing the signal flow graph.  
✅ **Visualization of Nodes & Branches** with their gains.  
✅ **Automatic computation of:**  
- Forward paths  
- Individual loops  
- Non-touching loop combinations  
- Delta (Δ) and modified delta values (Δ₁, …, Δₘ)  
✅ **Computation of Overall Transfer Function**  

### **🔴 Part 2: Routh Stability Criterion**  
✅ Accepts **characteristic equation** as input (e.g., `s^5 + s^4 + 10s^3 + 72s^2 + 152s + 240`).  
✅ Uses **Routh’s criterion** to determine system stability.  
✅ If unstable, **lists the number & values of unstable poles** (RHS of s-plane).  

---

## **Getting Started**  

### **🛠 Prerequisites**  
Make sure you have the following installed:  
- **Java 17+** (for Spring Boot)  
- **Node.js 18+** (for React frontend)  
- **Maven** (for backend dependencies)  
- **Git** (for version control)  

### **🚀 Installation**  

#### **1️⃣ Clone the Repository**  
```bash
git clone https://github.com/Ahmed-Ragy-3/Signal-Flow-Routh-Stability.git
cd Signal-Flow-Routh-Stability
```

#### **2️⃣ Backend (Spring Boot) Setup**  
```bash
cd backend
mvn clean install
mvn spring-boot:run
```
- Runs on **`http://localhost:8080`**  

#### **3️⃣ Frontend (React) Setup**  
```bash
cd frontend
npm install
npm start
```
- Runs on **`http://localhost:3000`**  

---

## **Usage**  

### **🎯 Signal Flow Graph**  
1️⃣ Enter the number of nodes and branch gains.  
2️⃣ Draw the **graph** dynamically using the UI.  
3️⃣ Click **Analyze** to compute:  
   - Forward paths  
   - Loops & non-touching loops  
   - Transfer function  

### **📉 Stability Analysis**  
1️⃣ Enter the **characteristic equation** in the input field.  
2️⃣ Click **Check Stability** to determine:  
   - System stability  
   - Unstable poles (if any)  


## **📂 Project Structure**  
```
Signal-Flow-Routh-Stability/
│── backend/              # Java Spring Boot API
│   ├── src/main/java/com/example/   # Java source code
│   ├── src/main/resources/          # Configuration files
│   ├── pom.xml                      # Maven dependencies
│── frontend/             # React.js frontend
│   ├── src/components/   # UI Components
│   ├── src/pages/        # Main application pages
│   ├── package.json      # Frontend dependencies
│── README.md             # Project Documentation
```
