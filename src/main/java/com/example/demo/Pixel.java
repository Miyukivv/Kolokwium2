package com.example.demo;

import java.awt.*;

public class Pixel {
    private int x;
    private int y;
    private String color;

    public Pixel(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

}
