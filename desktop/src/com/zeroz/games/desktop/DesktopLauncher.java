package com.zeroz.games.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zeroz.games.Main;
import utils.GameData;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "DreamOfRPG";
		config.width = GameData.SCREEN_WIDTH;
		config.height = GameData.SCREEN_HEIGHT;
		config.resizable = false;
		config.backgroundFPS = 30;
		new LwjglApplication(new Main(), config);
	}
}
