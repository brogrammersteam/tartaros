package com.brogrammers.tartaros.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.brogrammers.tartaros.Tartaros;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Tartaros.TITLE;
		config.width = Tartaros.V_WIDTH;
		config.height = Tartaros.V_HEIGHT;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		config.fullscreen = true;
//		TODO Make Fullscreen Possible in all Resolutions
		config.resizable = false;
//		config.addIcon();
//		TODO Create Icon @Designer-Team
		new LwjglApplication(new Tartaros(), config);
	}
}
