package com.w4ll3.cade;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.w4ll3.cade.mystic.Shape;

public class CADe extends ApplicationAdapter {
    SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Shape line = new Shape();
        line.add(0, 0);
        line.add(3, 3);
        line.add(0, 3);
        line.add(3, 0);
        System.out.println(line);
        line.translate(5, 5);
        System.out.println(line);
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
