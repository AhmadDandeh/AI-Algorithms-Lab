package com.ahmad.ai.map_coloring;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HelpFun {
    public int countryNum;
    public int[][] adjacencyMatrix;
    public Country[] countries;
    private List<CountryPack> queue = new ArrayList<>();
    public int resultColorCount;
    public static List<Country> finalResult = new ArrayList<>();

    public HelpFun(int n) {
        this.countryNum = n;
        this.adjacencyMatrix = new int[n][n];
        this.countries = new Country[n];
        for (int i = 0; i < n; i++) {
            String name = JOptionPane.showInputDialog("Enter name for country #" + (i + 1));
            countries[i] = new Country(name != null ? name : "C" + i, i);
        }
    }

    public void solve() {
        fillBorders();
        sortCountriesByBorders();

        queue.clear();
        queue.add(new CountryPack(countryNum, adjacencyMatrix, countries[0]));

        int bestIdx = 0;
        while (queue.get(bestIdx).g + 1 != countryNum) {
            CountryPack current = queue.get(bestIdx);
            int nextDepth = current.g + 1;

            queue.add(new CountryPack(countries[nextDepth], current));
            queue.remove(bestIdx);
            bestIdx = findMinFIndex();
        }

        this.resultColorCount = queue.get(bestIdx).colorCount;
        finalResult = queue.get(bestIdx).coloredCountries;
    }

    private void fillBorders() {
        for (int i = 0; i < countryNum; i++) {
            for (int j = i + 1; j < countryNum; j++) {
                int response = JOptionPane.showConfirmDialog(null,
                        "Does " + countries[i].name + " border " + countries[j].name + "?", "Define Borders", JOptionPane.YES_NO_OPTION);
                int val = (response == JOptionPane.YES_OPTION) ? 1 : 0;
                adjacencyMatrix[i][j] = val;
                adjacencyMatrix[j][i] = val;
            }
        }
    }

    private void sortCountriesByBorders() {
        for (Country c : countries) {
            int count = 0;
            for (int j = 0; j < countryNum; j++) if (adjacencyMatrix[c.id][j] == 1) count++;
            c.borderCount = count;
        }
        // Basic sort by border density (Heuristic)
        java.util.Arrays.sort(countries, (a, b) -> b.borderCount - a.borderCount);
    }

    private int findMinFIndex() {
        int minIdx = 0;
        int minF = queue.get(0).f;
        for (int i = 1; i < queue.size(); i++) {
            if (queue.get(i).f < minF) {
                minF = queue.get(i).f;
                minIdx = i;
            }
        }
        return minIdx;
    }
}