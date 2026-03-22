# AI & Machine Learning Algorithms Portfolio

A multi-disciplinary Artificial Intelligence suite featuring core algorithms implemented in **Java** and **MATLAB**. This portfolio covers Search Strategies, Constraint Satisfaction, and Machine Learning.

---

## 🚀 Project Overview

The repository is divided into three specialized modules:

### 1. Tic-Tac-Toe (XO) - Game Theory [Java]
An interactive game implementing search strategies to manage game states and intelligent decision-making.
* **Core Logic:** State-space search and move validation.
* **Features:** Human vs. CPU interaction logic.
* **Language:** Java (Object-Oriented Programming).



### 2. Map Coloring - Constraint Satisfaction (CSP) [Java]
A classic AI problem solved using backtracking to color a map such that no adjacent regions share the same color.
* **Algorithm:** Backtracking search with constraint checking.
* **Logic:** Efficiently assigning values to variables while respecting spatial boundaries.
* **Language:** Java.



### 3. Digit Recognition & Clustering [MATLAB]
A precision-focused module for pattern recognition and unsupervised grouping using a custom-curated dataset.
* **Custom Digit Recognition:** One-vs-All Logistic Regression trained and validated directly on **50 handwritten images** (`0.png` to `49.png`).
* **Dynamic Data Loader:** Features a custom script that transforms raw PNG pixel data into training matrices, achieving high accuracy for specific handwriting styles.
* **K-Means Clustering:** An unsupervised learning model that organizes the 50 images into clusters based on visual similarity, with real-time visualization of centroid convergence.
* **Optimization:** Utilizes the Conjugate Gradient (`fmincg`) algorithm for efficient weight tuning.
* **Language:** MATLAB.
