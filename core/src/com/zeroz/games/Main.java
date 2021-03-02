package com.zeroz.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import world.GameMap;
import world.WorldMap1;

public class Main extends Game {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;

	SpriteBatch batch;
	OrthographicCamera camera;
	GameMap gameMap;


	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 352);
		camera.update();
		gameMap = new WorldMap1();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameMap.render(camera, batch);
	}

	@Override
	public void dispose() {
		batch.dispose();
		gameMap.dispose();
	}
}
