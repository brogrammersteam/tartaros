package com.brogrammers.tartaros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brogrammers.tartaros.Tartaros;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SplashScreen implements Screen {

    private final Tartaros game;

    private Stage stage;

    private Label warningLabel;
    private Label tapLabel;
    private Label versionLabel;

    private Label.LabelStyle warningLabelStyle;
    private Label.LabelStyle tapLabelStyle;
    private Label.LabelStyle versionLabelStyle;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter warningLabelParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter tapLabelParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter versionLabelParameter;

    private BitmapFont warningLabelFont;
    private BitmapFont tapLabelFont;
    private BitmapFont versionLabelFont;

    public SplashScreen(Tartaros game) {
        this.game = game;
        this.stage = new Stage(new FitViewport(Tartaros.V_WIDTH, Tartaros.V_HEIGHT, game.camera));
        Gdx.input.setInputProcessor(stage);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Bold.ttf"));

//        Setting the warningLabelParameter
        warningLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        warningLabelParameter.size = 50;

//        Setting the tapLabelParameters
        tapLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        tapLabelParameter.size = 30;

//        Setting the versionLabelParameters
        versionLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        versionLabelParameter.size = 20;

//        Generating all the Label Fonts
        warningLabelFont = generator.generateFont(warningLabelParameter);
        tapLabelFont = generator.generateFont(tapLabelParameter);
        versionLabelFont = generator.generateFont(versionLabelParameter);

//        Initialising all the Label Sytles
        warningLabelStyle = new Label.LabelStyle(warningLabelFont, new Color(1, 1, 1, 1));
        tapLabelStyle = new Label.LabelStyle(tapLabelFont, new Color(1, 1, 1, 1));
        versionLabelStyle = new Label.LabelStyle(versionLabelFont, new Color(1, 1, 1, 1));

//        Initialising all the Labels
        warningLabel = new Label("Please consider that this is a Pre-Release Version of the Game.", warningLabelStyle);
        tapLabel = new Label("Tap the Screen or press Enter to get to the main screen", tapLabelStyle);
        versionLabel = new Label("Current Version: " + Tartaros.VERSION, versionLabelStyle);

//        Adding everything to the Stage
        stage.addActor(warningLabel);
        stage.addActor(tapLabel);
        stage.addActor(versionLabel);
    }

    @Override
    public void show() {
        System.out.println("Show");

//        Setting the position of every actor
        warningLabel.setPosition((Tartaros.V_WIDTH - warningLabel.getWidth()) / 2, (Tartaros.V_HEIGHT - warningLabel.getHeight()) / 2 + 200);
        tapLabel.setPosition((Tartaros.V_WIDTH - tapLabel.getWidth()) / 2, (Tartaros.V_HEIGHT - tapLabel.getHeight()) / 2);
        versionLabel.setPosition((Tartaros.V_WIDTH - versionLabel.getWidth()) / 2, (Tartaros.V_HEIGHT - versionLabel.getHeight()) / 2 - 350);

        warningLabel.addAction(sequence(alpha(0f), fadeIn(1.5f)));
        versionLabel.addAction(sequence(alpha(0f), fadeIn(1.5f)));
        tapLabel.addAction(sequence(alpha(0f), forever(sequence(fadeIn(1.5f), fadeOut(1.5f)))));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f,.25f,.25f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();
    }

    public void update(float delta){
        handleInput();
        stage.act(delta);
    }

    public void handleInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            app.exit();
        }
        if(Gdx.input.justTouched() || Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {
        System.out.println("Pause");
    }

    @Override
    public void resume() {
        System.out.println("Resume");
    }

    @Override
    public void hide() {
        System.out.println("Hide");
    }

    @Override
    public void dispose() {
        System.out.println("Dispose");
        tapLabelFont.dispose();
        warningLabelFont.dispose();
        stage.dispose();
        generator.dispose();
    }
}