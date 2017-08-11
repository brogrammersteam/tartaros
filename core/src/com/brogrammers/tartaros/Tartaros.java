package com.brogrammers.tartaros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.brogrammers.tartaros.screens.LoadingScreen;
import com.sun.xml.internal.fastinfoset.util.StringArray;

public class Tartaros extends Game {

	public static final String TITLE = "Tartaros - Das Spiel der Spiele";

	public static final int V_HEIGHT = 1080;
	public static final int V_WIDTH = 1920;
	public static final int FPS = 60;

	public static final boolean RESIZABLE = false;
    public static final boolean DEBUG = false;
    public static final boolean DEVELOP = true;
    public static final boolean FULLSCREEN = true;
    public static final boolean VSYNC = true;

	public static final float VERSION = 0.2f;
	public static final float alphaStart = 0f;
	public static final float fadeTime = 1.75f;

	public static final Array<String> splashArray = new Array<String>();

	public OrthographicCamera camera;

    public SpriteBatch batch;
    public AssetManager assets;
    public Preferences mainSettings;

    @Override
	public void create () {
//        Creating all the needed things
        assets = new AssetManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 720);
		batch = new SpriteBatch();
		mainSettings = Gdx.app.getPreferences("Settings");

		initializeSettings();
		addSplashs();

//		  Setting the first Screen
		this.setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
//        Dispose from all the Stuff
        batch.dispose();
        this.getScreen().dispose();
        assets.dispose();
		super.dispose();
	}

	public void initializeSettings(){

    	mainSettings.flush();
	}

	private void addSplashs(){
		splashArray.add("Here i am");
		splashArray.add("Gotya");
		splashArray.add("Whats up");
	}
}
