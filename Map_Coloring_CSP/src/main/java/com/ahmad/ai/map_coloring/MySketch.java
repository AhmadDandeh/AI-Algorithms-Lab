package com.ahmad.ai.map_coloring;

import processing.core.PApplet;

public class MySketch extends PApplet {
    private int[][] palette = {
            {255, 85, 85}, {85, 255, 85}, {85, 85, 255},
            {255, 255, 85}, {255, 85, 255}, {85, 255, 255}
    };

    public static void launchSketch() {
        PApplet.main("com.ahmad.ai.map_coloring.MySketch");
    }

    public void settings() { size(600, 600); }
    public void setup() { background(30); }

    public void draw() {
        if (HelpFun.finalResult.isEmpty()) return;

        int n = HelpFun.finalResult.size();
        float centerX = width / 2f;
        float centerY = height / 2f;
        float radius = 200;

        // Draw connections
        stroke(100);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (CountryPack.adjacencyMatrix[HelpFun.finalResult.get(i).id][HelpFun.finalResult.get(j).id] == 1) {
                    float x1 = centerX + radius * cos(TWO_PI * i / n);
                    float y1 = centerY + radius * sin(TWO_PI * i / n);
                    float x2 = centerX + radius * cos(TWO_PI * j / n);
                    float y2 = centerY + radius * sin(TWO_PI * j / n);
                    line(x1, y1, x2, y2);
                }
            }
        }

        // Draw Countries
        for (int i = 0; i < n; i++) {
            Country c = HelpFun.finalResult.get(i);
            float x = centerX + radius * cos(TWO_PI * i / n);
            float y = centerY + radius * sin(TWO_PI * i / n);

            fill(palette[c.color % palette.length][0], palette[c.color % palette.length][1], palette[c.color % palette.length][2]);
            ellipse(x, y, 60, 60);

            fill(255);
            textAlign(CENTER, CENTER);
            text(c.name, x, y);
        }
        noLoop();
    }
}