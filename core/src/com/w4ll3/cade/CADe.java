package com.w4ll3.cade;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.w4ll3.cade.mystic.Line;
import com.w4ll3.cade.mystic.Triangle;
import com.w4ll3.cade.mystic.Vertex;

public class CADe extends ApplicationAdapter {
    SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Line line = new Line(new Vertex(0, 3), new Vertex(4, 8));
        Triangle triangle = new Triangle(new Vertex(0, 3), new Vertex(3, 5), new Vertex(0, 5));
        System.out.println(line + "\n" + triangle);
        line.translate(5, 5);
        triangle.translate(5, 5);
        System.out.println(line + "\n" + triangle);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
