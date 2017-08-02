package com.brogrammers.tartaros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brogrammers.tartaros.Tartaros;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SplashScreen implements Screen {

    private final Tartaros game;

    private Stage stage;

    private Image splashImage;

    public SplashScreen(Tartaros game) {
        this.game = game;
        this.stage = new Stage(new FitViewport(Tartaros.V_WIDTH, Tartaros.V_HEIGHT, game.camera));
        Gdx.input.setInputProcessor(stage);

        Texture splashTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
        splashImage = new Image(splashTexture);
        splashImage.setOrigin(splashImage.getWidth()/2, splashImage.getHeight()/2);

        stage.addActor(splashImage);
    }

    @Override
    public void show() {
        System.out.println("Show");
        splashImage.setPosition(400,300);

//        splashImage.addAction(sequence(alpha(0f),
//                parallel(moveBy(30, -20, 2f), fadeIn(5f))));

        splashImage.addAction(sequence(alpha(0f), scaleTo(.1f, .1f),
                parallel(fadeIn(2f, Interpolation.pow2),
                        scaleTo(2f,2f,2.5f, Interpolation.pow5),
                        moveTo(stage.getWidth() / 2 -32, stage.getHeight() / 2 - 32, 2f, Interpolation.swing))));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f,.25f,.25f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();

        game.batch.begin();
        game.font.draw(game.batch, "Splashscreen!", 120,120);
        game.batch.end();
    }

    public void update(float delta){
        handleInput();
        stage.act(delta);
    }

    public void handleInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            app.exit();
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
        stage.dispose();
    }
}