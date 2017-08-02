package com.brogrammers.tartaros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brogrammers.tartaros.screens.MenuScreen;
import com.brogrammers.tartaros.screens.SplashScreen;

public class Tartaros extends Game {

	public static final String TITLE = "Tartaros - Das Spiel der Spiele";

	public static final float VERSION = 0.1f;
	public static final int V_HEIGHT = 1080;
	public static final int V_WIDTH = 1920;

	public OrthographicCamera camera;

    public SpriteBatch batch;

    public BitmapFont font;

    @Override
	public void create () {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 720);
		batch = new SpriteBatch();
		font = new BitmapFont();

		this.setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
        batch.dispose();
        this.getScreen().dispose();
		super.dispose();
	}
}
