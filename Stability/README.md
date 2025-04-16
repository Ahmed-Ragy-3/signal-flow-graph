# **Routh Stability Criterion**

## 📘 Overview  
This is a **program** for checking the **stability of control systems** using the **Routh-Hurwitz Stability Criterion**. You enter a **characteristic equation, and it tells you whether the system is **stable**, **unstable**, or **marginally stable**, along with the number of **unstable poles** (right-half-plane roots).

---

## 🧠 What It Does
- Builds the **Routh array**.
- Applies the **Routh-Hurwitz criterion**.

---

## ⚙️ Tech Requirements
- Python 3.8 or higher  
- sympy library

---

## 🚀 How to Use

### ▶️ Step 1: Run the Program
Make sure you're in the project folder. Then run:

```bash
  pip install sympy
```

```bash
  python main.py
```

---

### 🧾 Step 2: Enter a Polynomial  
When prompted, enter your characteristic equation using `s`, `+`, and `^` or `**`. For example:

```
Enter the Characteristic equation:
s^5 + s^4 + 10s^3 + 72s^2 + 152s + 240
```

✅ You can type `s^3 + 2*s^2 + 5s + 4`, `s**4 + 3*s**2 + 1`, etc.  
✅ Spaces are ignored.  
✅ `*` is optional between number and `s`.

---

### 🧾 Step 3: Exiting  
type
` exit `
---

## ❌ Error Handling

If something goes wrong, the tool will catch the error and show:

```
❌ Error: Invalid polynomial expression.
```
---

## 🧪 ✅ Tests

The project includes a `unittest`-based test suite to validate different characteristic equations.

### ▶️ To Run the Tests

```bash
  python -m unittest test.py
```

---
## 🗂️ File Structure (All In One Folder)

```
routh-stability/
│
├── main.py               # Contains CLI loop, input cleaning, and solver
├── RouthHurwitz.py       # Contains the Routh array and solve logic
├── README.md             # This file
├── test.py               # This file
```
