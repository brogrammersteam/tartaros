package com.brogrammers.tartaros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brogrammers.tartaros.Tartaros;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SettingsScreen implements Screen {

    private Tartaros game;

    private Stage stage;

    private Label infoLabel;

    private Label.LabelStyle infoLabelStyle;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter infoLabelParameter;

    private BitmapFont infoLabelFont;

    public SettingsScreen(Tartaros game) {
        this.game = game;

        stage = new Stage(new FitViewport(Tartaros.V_WIDTH, Tartaros.V_HEIGHT, game.camera));
        Gdx.input.setInputProcessor(stage);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Bold.ttf"));

//        Setting the warningLabelParameter
        infoLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        infoLabelParameter.size = 50;

//        Generating all the Label Fonts
        infoLabelFont = generator.generateFont(infoLabelParameter);

//        Initialising all the Label Sytles
        infoLabelStyle = new Label.LabelStyle(infoLabelFont, new Color(1, 1, 1, 1));

//        Initialising all the Labels
        infoLabel = new Label("The Settings Screen is still under development.", infoLabelStyle);

        stage.addActor(infoLabel);
    }

    @Override
    public void show() {
        System.out.println("SETTINGS");

//        Setting the position of every actor
        infoLabel.setPosition((Tartaros.V_WIDTH - infoLabel.getWidth()) / 2, (Tartaros.V_HEIGHT - infoLabel.getHeight()) / 2);

//        Adding an animation to every actor
        infoLabel.addAction(sequence(alpha(0f), forever(sequence(fadeIn(1.5f), fadeOut(1.5f)))));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f,.25f,.25f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();
    }

    private void update(float delta){
        handleInput();

        stage.act(delta);
    }

    private void handleInput(){

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
//        Disposing all the Stuff
        System.out.println("Dispose");
        stage.dispose();
    }
}
