package com.w4ll3.cade;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.w4ll3.cade.mystic.*;

import java.util.ArrayList;

public class CADe extends ApplicationAdapter implements ApplicationListener, InputProcessor {
	private Rectangle rect;
	private State function;
	private int pointNumber;
	private SpriteBatch batch;
	private ShapeRenderer renderer;
	private OrthographicCamera cam;
	private boolean ctrl, progress;
	private ArrayList<Shape> objects;
	private Stage stage;

	@Override
	public void create() {
		ctrl = false;
		progress = false;
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(false);
		cam.update();
		stage = new Stage(new ScreenViewport(cam));
		objects = new ArrayList<>();
		pointNumber = 0;
		function = State.NONE;
		rect = new Rectangle(new Vertex(0, 0), new Vertex(100, 0), new Vertex(0, 100), new Vertex(100, 100));
		objects.add(rect);
		Gdx.input.setInputProcessor(new InputMultiplexer(this, stage));
		stage.addListener(new ClickListener(Input.Keys.LEFT) {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (pointNumber == 0) {
					switch (function) {
						case LINE: {
							objects.add(new Line(new Vertex(x, y), new Vertex(x, y)));
							progress = true;
							pointNumber++;
							break;
						}

						case RECT: {
							objects.add(new Rectangle(new Vertex(x, y), new Vertex(x, y), new Vertex(x, y), new Vertex(x, y)));
							progress = true;
							pointNumber++;
							break;
						}

						case CIRCLE: {
							objects.add(new Line(new Vertex(x, y), new Vertex(x, y)));
							progress = true;
							pointNumber++;
							break;
						}

						case TRIANGLE: {
							objects.add(new Triangle(new Vertex(x, y), new Vertex(x, y), new Vertex(x, y)));
							progress = true;
							pointNumber++;
							break;
						}
					}
				} else if (progress) {
					switch (function) {
						case LINE: {
							if (pointNumber == 1) {
								objects.get(objects.size() - 1).update(x, y, pointNumber - 1);
								progress = false;
							} else
								pointNumber++;
							break;
						}

						case RECT: {
							if (pointNumber == 3) {
								objects.get(objects.size() - 1).update(x, y, pointNumber - 1);
								progress = false;
							} else
								pointNumber++;
							break;
						}

						case CIRCLE: {
							if (pointNumber == 1) {
								objects.get(objects.size() - 1).update(x, y, pointNumber - 1);
								progress = false;
							} else
								pointNumber++;
							break;
						}

						case TRIANGLE: {
							if (pointNumber == 2) {
								objects.get(objects.size() - 1).update(x, y, pointNumber - 1);
								progress = false;
							} else
								pointNumber++;
							break;
						}

						default: {
							break;
						}
					}
				}
				return false;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			}

			@Override
			public boolean mouseMoved(InputEvent event, float x, float y) {
				if (pointNumber != 0) {
					switch (function) {
						case LINE: {
							if (pointNumber == 2)
								return false;
							else
								objects.get(objects.size() - 1).update(x, y, pointNumber - 1);
							break;
						}

						case RECT: {
							if (pointNumber == 4)
								return false;
							else
								objects.get(objects.size() - 1).update(x, y, pointNumber - 1);
							break;
						}

						case CIRCLE: {
							if (pointNumber == 1)
								return false;
							else
								objects.get(objects.size() - 1).update(x, y, pointNumber - 1);
							break;
						}

						case TRIANGLE: {
							if (pointNumber == 3)
								return false;
							else
								objects.get(objects.size() - 1).update(x, y, pointNumber - 1);
							break;
						}

						default: {
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
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.end();
		if (!progress)
			pointNumber = 0;
		renderer.setProjectionMatrix(cam.combined);
		renderer.begin(ShapeRenderer.ShapeType.Line);
		for (Shape s : objects) s.draw(renderer);
		renderer.end();
	}

	@Override
	public void resize(int width, int height) {
		cam.position.set(new Vector3(width / 2, height / 2, 0));
		stage.getViewport().update(width, height);
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
