package com.w4ll3.cade.mystic;

public class Vertex {

    float x, y, z;

    @Override
    public String toString() {
        return "Vertex{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public Vertex(float x, float y) {
        this.x = x;
        this.y = y;
        this.z = 1;
    }

    public Vertex(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vertex mul(Vertex v1, Vertex v2, Vertex v3) {
        float x = (this.x * v1.x) + (this.y * v1.y) + (this.z * v1.z);
        float y = (this.x * v2.x) + (this.y * v2.y) + (this.z * v2.z);
        float z = (this.x * v3.x) + (this.y * v3.y) + (this.z * v3.z);
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
}
