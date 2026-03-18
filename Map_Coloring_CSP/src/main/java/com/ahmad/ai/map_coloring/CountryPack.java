package com.ahmad.ai.map_coloring;

import java.util.ArrayList;
import java.util.List;

public class CountryPack {
    public static int totalCountries;
    public static int[][] adjacencyMatrix;
    public static int totalBorders;

    public int colorCount;
    public int currentBorders;
    public int g = -1; // Depth in search tree
    public int f;      // Heuristic value
    public List<Country> coloredCountries = new ArrayList<>();

    // Constructor for initial state
    public CountryPack(int num, int[][] matrix, Country firstCountry) {
        totalCountries = num;
        adjacencyMatrix = new int[num][num];
        for (int i = 0; i < num; i++) System.arraycopy(matrix[i], 0, adjacencyMatrix[i], 0, num);

        totalBorders = countTotalBorders();
        this.g = 0;
        firstCountry.color = 0;
        this.coloredCountries.add(firstCountry);
        this.colorCount = 1;
        this.f = calculateF();
    }

    // Constructor for next state
    public CountryPack(Country nextCountry, CountryPack parent) {
        this.g = parent.g + 1;
        this.coloredCountries.addAll(parent.coloredCountries);
        this.currentBorders = parent.currentBorders + countBordersWithColored(nextCountry);

        // Determine valid color
        nextCountry.color = findValidColor(nextCountry.id);
        this.coloredCountries.add(nextCountry);

        this.colorCount = getMaxColor() + 1;
        this.f = calculateF();
    }

    private int calculateF() {
        return totalCountries + totalBorders + colorCount - currentBorders - 1;
    }

    private int countTotalBorders() {
        int count = 0;
        for (int i = 0; i < totalCountries; i++) {
            for (int j = i + 1; j < totalCountries; j++) {
                if (adjacencyMatrix[i][j] == 1) count++;
            }
        }
        return count;
    }

    public int findValidColor(int countryId) {
        int color = 0;
        while (!isValidColor(countryId, color)) {
            color++;
        }
        return color;
    }

    private boolean isValidColor(int id, int color) {
        for (Country c : coloredCountries) {
            if (adjacencyMatrix[c.id][id] == 1 && c.color == color) return false;
        }
        return true;
    }

    private int getMaxColor() {
        int max = 0;
        for (Country c : coloredCountries) {
            if (c.color > max) max = c.color;
        }
        return max;
    }

    private int countBordersWithColored(Country target) {
        int count = 0;
        for (Country c : coloredCountries) {
            if (adjacencyMatrix[c.id][target.id] == 1) count++;
        }
        return count;
    }
}