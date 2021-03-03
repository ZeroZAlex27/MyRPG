package com.zeroz.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screens.MainMenuScreen;

public class Main extends Game {

	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new MainMenuScreen(this));
	}

	public SpriteBatch getBatch()
	{
		return batch;
	}

	@Override
	public void dispose() {
	}
}
