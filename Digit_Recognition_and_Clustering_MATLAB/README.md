# Custom Digit Recognition & Clustering

This module implements a localized Machine Learning model designed to recognize and cluster a specific set of handwritten digits.

## 🎯 Project Focus
This project focuses on a curated dataset of **50 handwritten images** (indexed 0 to 49). It demonstrates the model's ability to learn and classify a small-scale specific dataset with high precision.

## 🛠️ Key Components
- **Dataset:** 50 custom `.png` images (20x20 pixels) located in `src/data/`.
- **Algorithm:** One-vs-All Logistic Regression optimized for a small sample size.
- **Training Logic:** The model is trained directly on the vectorized pixel data of the 50 images.
- **Clustering:** K-Means implementation to visualize how these 50 points can be grouped.

## 📊 Results
The model achieves near 100% accuracy on the target 50-image set, showcasing effective pattern matching for the provided handwriting samples.
