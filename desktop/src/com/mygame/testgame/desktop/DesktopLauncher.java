package com.mygame.testgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.mygame.testgame.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;
		config.initialBackgroundColor = Color.WHITE;
		config.foregroundFPS = 60;
		config.resizable = false;
		config.title = "Spidey";
		new LwjglApplication(new Main(), config);
	}
}
