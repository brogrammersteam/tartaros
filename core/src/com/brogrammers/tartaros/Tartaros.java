package com.brogrammers.tartaros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.brogrammers.tartaros.screens.LoadingScreen;

public class Tartaros extends Game {

	public static final String TITLE = "Tartaros - Das Spiel der Spiele";
	public static final String VERSION = "Prerelease 0.2";

	static final String MUSICKEY = "music";
	static final String MUSICVOLUMEKEY = "musicvolume";
	static final String SOUNDKEY = "sound";
	static final String SOUNDVOLUMEKEY = "soundvolume";

	static final String SAVEGAMENAMEKEY = "savegamename";

	public static final int V_HEIGHT = 1080;
	public static final int V_WIDTH = 1920;
	public static final int FPS = 60;

	public static final boolean RESIZABLE = false;
    public static final boolean DEBUG = false;
    public static final boolean DEVELOP = true;
    public static final boolean FULLSCREEN = true;
    public static final boolean VSYNC = true;

	public static final float alphaStart = 0f;
	public static final float fadeTime = 1.75f;

	public OrthographicCamera camera;

    public SpriteBatch batch;
    public AssetManager assets;
    public static Preferences mainSettings;
    public static Music music;
    public static XmlHandler xmlHandler;

    @Override
	public void create () {
//        Creating all the needed things
        assets = new AssetManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 720);
		batch = new SpriteBatch();
		xmlHandler = new XmlHandler();
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));

		mainSettings = Gdx.app.getPreferences("com.brogrammers.tartaros.mainSettings");

		if(mainSettings.getBoolean("initialised")){
			System.out.println("Die Einstellungen wurden bereits initialisiert");
		}else {
			initializeSettings();
		}

		xmlHandler.setLanguageFile();

		music.setLooping(true);
		if(Settings.getMusic()) {
			music.play();
		}
		music.setVolume(Settings.getMusicVolume());

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
        music.dispose();
		super.dispose();
	}

	public static void initializeSettings() {
		System.out.println("Initialise");

		mainSettings.putBoolean("initialised", true);

		Settings.setLanguage("English");

		Settings.setSavegameName(1, "Savegame1");
		Settings.setSavegameName(2, "Savegame2");
		Settings.setSavegameName(3, "Savegame3");

		Settings.setMusic(true);
		Settings.setMusicVolume(1f);
		Settings.setSound(true);
		Settings.setSoundVolume(1f);

		mainSettings.putBoolean("savegame1initialised", false);

		mainSettings.putBoolean("savegame2initialised", false);

		mainSettings.putBoolean("savegame3initialised", false);

		mainSettings.flush();
	}
}
