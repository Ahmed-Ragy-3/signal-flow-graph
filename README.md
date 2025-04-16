# 🚀 Signal Flow Graphs & Routh Stability Criterion

## 📌 Overview
This web-based tool enables the **visual construction and analysis** of Signal Flow Graphs (SFGs) and evaluates system stability using **Routh’s Stability Criterion**.

Built with **React.js** on the frontend and **Spring Boot** on the backend, it supports dynamic interaction, mathematical analysis, and clean API integration for control systems enthusiasts and students.

---

## 🧠 Features

### 🟢 Signal Flow Graph Analysis
- 🎨 Interactive node/edge drawing UI.
- 📌 Gain input for branches.
- 📈 Automatically computes:
  - Forward paths
  - Individual loops
  - Non-touching loops
  - Δ and Δ₁, Δ₂, ..., Δₘ
- 🧮 Calculates overall **transfer function** using Mason's Gain Formula.

### 🔴 Routh Stability Analysis
- 🧾 Accepts characteristic equations like `s^5 + s^4 + 10s^3 + 72s^2 + 152s + 240`.
- 🧠 Applies **Routh's Criterion** to assess system stability.
- ❌ Lists unstable poles (on right-half s-plane) if system is unstable.

---

## 🧰 Tech Stack
- **Frontend:** React.js + `react-flow` for visualization
- **Backend:** Java 17+, Spring Boot (REST API)
- **Package Managers:** Maven (Java), npm (React)

---

## 🛠 Prerequisites
Install the following tools:
- [Java 17+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)
- [Node.js 18+](https://nodejs.org/)
- [Git](https://git-scm.com/)

---

## 🔃 Cloning the Project
```bash
git clone https://github.com/Ahmed-Ragy-3/signal-flow-graph.git
cd signal-flow-graph
```

####  Backend (Spring Boot) Setup
```bash
cd SFG-Back
mvn clean install
mvn spring-boot:run
```
- Runs on **`http://localhost:8080`**  
---



####  Frontend (React) Setup  
```bash
cd SFG-Front
npm install
npm run dev
```
- Runs on **`http://localhost:5173`**  

---


####  Stability (Python) Setup  
```bash
  pip install sympy
  python main.py
```


## 🗂️ Project Structure
```
signal-flow-graph/
├── SFG-Frony/                ← SFG React frontend
├── Stability/                ← Python-based Routh Stability
├── SFG-Back/                 ← SFG Spring backend (Java or logic)?
├── .idea/, .git/, etc.       ← Metadata files
├── README.md                 ← Readme file
```

