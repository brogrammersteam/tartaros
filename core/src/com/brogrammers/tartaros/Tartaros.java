package com.brogrammers.tartaros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brogrammers.tartaros.screens.MenuScreen;

public class Tartaros extends Game {

	SpriteBatch batch;

	public static final String TITLE = "Tartaros - Das Spiel der Spiele";
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
