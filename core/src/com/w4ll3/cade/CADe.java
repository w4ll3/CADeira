package com.w4ll3.cade;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.w4ll3.cade.mystic.Line;
import com.w4ll3.cade.mystic.Rectangle;
import com.w4ll3.cade.mystic.Shape;
import com.w4ll3.cade.mystic.Vertex;

import java.util.ArrayList;

public class CADe extends ApplicationAdapter implements ApplicationListener, InputProcessor {
    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private Rectangle rect;
    private State function;
    private ArrayList<Shape> objects;
    private boolean ctrl, progress;
    private int pointNumber;

    @Override
    public void create() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        Stage stage = new Stage();
        objects = new ArrayList<>();
        pointNumber = 0;
        function = State.NONE;
        ctrl = false;
        progress = true;
        rect = new Rectangle(new Vertex(10, 20), new Vertex(20, 20), new Vertex(20, 40), new Vertex(10, 40));
        objects.add(rect);
        Gdx.input.setInputProcessor(new InputMultiplexer(this, stage));
        stage.addListener(new ClickListener(Input.Keys.LEFT) {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (progress) {
                    switch (function) {
                        case LINE: {
                            objects.add(new Line(new Vertex(x, y), new Vertex(x, y)));
                            System.out.println("Line mode");
                            progress = false;
                        }

                        case RECT: {

                        }

                        case CIRCLE: {

                        }

                        case TRIANGLE: {

                        }
                    }
                } else {
                    pointNumber++;
                }
                super.touchDown(event, x, y, pointer, button);
                return false;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                if (!progress) {
                    objects.get(objects.size() - 1).update(x, y, pointNumber);
                    switch (function) {
                        case LINE: {
                            if (pointNumber == 1) progress = true;
                            break;
                        }

                        case RECT: {
                            if (pointNumber == 3) progress = true;
                            break;
                        }

                        case CIRCLE: {
                            if (pointNumber == 1) progress = true;
                            break;
                        }

                        case TRIANGLE: {
                            if (pointNumber == 2) progress = true;
                            break;
                        }
                    }
                }
                return false;
            }
        });

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();
        for (Shape s: objects) s.draw(renderer);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.L: {
                function = State.LINE;
                break;
            }

            case Input.Keys.R: {
                function = State.RECT;
                break;
            }

            case Input.Keys.C: {
                function = State.CIRCLE;
                break;
            }

            case Input.Keys.T: {
                function = State.TRIANGLE;
                break;
            }

            case Input.Keys.CONTROL_LEFT: {
                ctrl = true;
                break;
            }

            case Input.Keys.N: {
                if (ctrl) function = State.CLEAR;
                break;
            }

            case Input.Keys.S: {
                function = State.SELECTION;
                break;
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
