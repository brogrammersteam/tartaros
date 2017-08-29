package com.brogrammers.tartaros.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.brogrammers.tartaros.Tartaros;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Tartaros.TITLE;
		config.width = Tartaros.V_WIDTH;
		config.height = Tartaros.V_HEIGHT;
		config.foregroundFPS = Tartaros.FPS;
		config.backgroundFPS = Tartaros.FPS;
		config.fullscreen = Tartaros.FULLSCREEN;
		config.resizable = Tartaros.RESIZABLE;
		config.vSyncEnabled = Tartaros.VSYNC;
		new LwjglApplication(new Tartaros(), config);
	}
}
