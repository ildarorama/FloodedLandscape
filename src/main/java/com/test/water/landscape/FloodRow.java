package com.test.water.landscape;

public class FloodRow {
    int x;
    int y;
    int size;

    public FloodRow(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    boolean contain(int x, int y) {
        return this.y == y && x > this.x - size && x <= this.x;
    }
}
