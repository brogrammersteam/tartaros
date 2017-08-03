package com.brogrammers.tartaros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brogrammers.tartaros.screens.LoadingScreen;
import com.brogrammers.tartaros.screens.PreReleaseScreen;

public class Tartaros extends Game {

	public static final String TITLE = "Tartaros - Das Spiel der Spiele";

	public static final float VERSION = 0.1f;
	public static final int V_HEIGHT = 1080;
	public static final int V_WIDTH = 1920;

	public OrthographicCamera camera;

    public SpriteBatch batch;
    public AssetManager assets;

    @Override
	public void create () {
        assets = new AssetManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 720);
		batch = new SpriteBatch();

		this.setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
        batch.dispose();
        this.getScreen().dispose();
        assets.dispose();
		super.dispose();
	}
}
