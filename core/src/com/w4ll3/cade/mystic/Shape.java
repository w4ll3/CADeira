package com.w4ll3.cade.mystic;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Arrays;

public class Shape {

    private ArrayList<Vertex> mObj = new ArrayList<>();

    @Override
    public String toString() {
        return "Shape{" +
                "mObj=" + Arrays.toString(mObj.toArray()) +
                '}';
    }

    public void add(float x, float y) {
        mObj.add(new Vertex(x, y));
    }

    void add(Vertex v1) {
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

    public void draw(ShapeRenderer renderer) {
        for (int i = 0; i < mObj.size(); i++) {
            int loop = (i + 1) % mObj.size();
            renderer.line(mObj.get(i).x, mObj.get(i).y, mObj.get(loop).x, mObj.get(loop).y);
        }
    }


    public void update(float x, float y, int pointNumber) {
        mObj.set(pointNumber, new Vertex(x, y));
    }
}
