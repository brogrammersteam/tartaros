package com.brogrammers.tartaros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brogrammers.tartaros.Tartaros;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SplashScreen implements Screen {

    private Tartaros game;

    private Stage stage;

    private String infoString = "Please consider that this game was developed by a young group of Programmers called BROgrammers. We want you to become one with the game and the game to become one with you. If you find any Problems or Bugs go to our Github Repository and tell us about them. Thank you for helping us finding Bugs and Problems.";

    private Texture splashTexture;
    private Image splashImage;

    private Label infoLabel;
    private Label tapLabel;

    private Label.LabelStyle infoLabelStyle;
    private Label.LabelStyle tapLabelStyle;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter infoLabelParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter tapLabelParameter;

    private BitmapFont infoLabelFont;
    private BitmapFont tapLabelFont;

    public SplashScreen(Tartaros game) {
        this.game = game;
        stage = new Stage(new FitViewport(Tartaros.V_WIDTH, Tartaros.V_HEIGHT, game.camera));
        Gdx.input.setInputProcessor(stage);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Fiolex_Mephisto.ttf"));

        splashTexture = game.assets.get("badlogic.jpg", Texture.class);
//        TODO Change out the Logo by our own
        splashImage = new Image(splashTexture);

//        Setting the infoLabelParameter
        infoLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        infoLabelParameter.size = 35;

//        Setting the tapLabelParameters
        tapLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        tapLabelParameter.size = 30;

//        Generating all the Label Fonts
        infoLabelFont = generator.generateFont(infoLabelParameter);
        tapLabelFont = generator.generateFont(tapLabelParameter);

//        Initialising all the Label Sytles
        infoLabelStyle = new Label.LabelStyle(infoLabelFont, new Color(1, 1, 1, 1));
        tapLabelStyle = new Label.LabelStyle(tapLabelFont, new Color(1, 1, 1, 1));

//        Initialising all the Labels
        infoLabel = new Label(infoString, infoLabelStyle);
        tapLabel = new Label("Tap the Screen or press Enter to get to the main screen", tapLabelStyle);

//        Adding everything to the Stage
        stage.addActor(splashImage);
        stage.addActor(infoLabel);
        stage.addActor(tapLabel);
    }

    @Override
    public void show() {
        System.out.println("SHOW");

//        Making the infoLabel wrapping the Text
        infoLabel.setWrap(true);
        infoLabel.setAlignment(Align.center);
        infoLabel.setWidth(Tartaros.V_WIDTH - 200);

//        Setting the Position of every Actor
        splashImage.setPosition((Tartaros.V_WIDTH - splashImage.getWidth()) / 2, (Tartaros.V_HEIGHT - splashImage.getHeight()) / 2);
        infoLabel.setPosition((Tartaros.V_WIDTH - infoLabel.getWidth()) / 2, (Tartaros.V_HEIGHT - infoLabel.getHeight()) / 2 + 300);
        tapLabel.setPosition((Tartaros.V_WIDTH - tapLabel.getWidth()) / 2, (Tartaros.V_HEIGHT - tapLabel.getHeight()) / 2 - 300);

//        Adding Actions to every Actor
        splashImage.addAction(sequence(alpha(Tartaros.alphaStart), forever(sequence(fadeIn(Tartaros.fadeTime), fadeOut(Tartaros.fadeTime)))));
        infoLabel.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));
        tapLabel.addAction(sequence(alpha(Tartaros.alphaStart), forever(sequence(fadeIn(Tartaros.fadeTime), fadeOut(Tartaros.fadeTime)))));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.25f,0.25f,0.25f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);

        stage.draw();
    }

    private void update(float delta){
        handleInput();
        stage.act(delta);
    }

    public void handleInput(){
//        Chancel the App
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            app.exit();
        }
//        Get to the next Screen
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
//        Disposing all the Stuff
        System.out.println("Dispose");
        splashTexture.dispose();
        stage.dispose();
        tapLabelFont.dispose();
        infoLabelFont.dispose();
        generator.dispose();
    }
}
