package com.w4ll3.cade.mystic;

public class Line extends Shape {

    public Line(Vertex v1, Vertex v2) {
        super.add(v2);
        super.add(v1);
    }

}
