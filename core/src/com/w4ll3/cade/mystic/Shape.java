package com.w4ll3.cade.mystic;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Arrays;

public class Shape {

    protected ArrayList<Vertex> mObj = new ArrayList<Vertex>();

    @Override
    public String toString() {
        return "Shape{" +
                "mObj=" + Arrays.toString(mObj.toArray()) +
                '}';
    }

    public void add(float x, float y) {
        mObj.add(new Vertex(x, y));
    }

    public void add(Vertex v1) {
        mObj.add(v1);
    }

    public void translate(float x, float y) {
        Vertex transformation[] = new Vertex[]{
                new Vertex(1, 0, x),
                new Vertex(0, 1, y),
                new Vertex(0, 0, 1),
        };

        for (Vertex ç : mObj) ç.mul(transformation[0], transformation[1], transformation[2]);
    }

    public void scale(float x, float y) throws InvalidArgumentException {
        if (x == 0 || y == 0) throw new InvalidArgumentException(new String[] {"Exta escala exta errada."});

        Vertex transformation[] = new Vertex[]{
                new Vertex(x, 0, 0),
                new Vertex(0, y, 0),
                new Vertex(0, 0, 1),
        };

        for (Vertex ç : mObj) ç.mul(transformation[0], transformation[1], transformation[2]);
    }

    public void rotate(float g) {
        Vertex transformation[] = new Vertex[]{
                new Vertex((float) Math.cos(Math.toRadians(g)), (float) Math.sin(Math.toRadians(g)) * -1, 0),
                new Vertex((float) Math.sin(Math.toRadians(g)), (float) Math.cos(Math.toRadians(g)), 0),
                new Vertex(0, 0, 1),
        };

        for (Vertex ç : mObj) ç.mul(transformation[0], transformation[1], transformation[2]);
    }

}
