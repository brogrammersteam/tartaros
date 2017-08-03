package com.brogrammers.tartaros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.brogrammers.tartaros.Tartaros;

import static com.badlogic.gdx.Gdx.app;

public class MenuScreen implements Screen {

    private Tartaros game;

    private Stage stage;
    private Skin skin;
    private TextButton startButton;
    private TextButton settingButton;

    public MenuScreen(Tartaros game) {
        this.game = game;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        startButton = new TextButton("Start", skin);
        settingButton = new TextButton("Settings", skin);
        resetButton = new TextButton("Reset all", skin);

        startButton.setSize(900,150);
        startButton.setPosition(Tartaros.V_WIDTH /2 - 450,Tartaros.V_HEIGHT /2);
        startButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                startButton.setText("Game Started");
//                TODO Let the Game start by the press of this Button
            }
        });

        settingButton.setSize(900,150);
        settingButton.setPosition(Tartaros.V_WIDTH /2 - 450, Tartaros.V_HEIGHT /2 - 160);
        settingButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button) {
                settingButton.setText("Settings Started");
//                TODO Change to the Settings Screen by the push of the Button
            }
        });
        resetButton.setSize(900,150);
        resetButton.setPosition(Tartaros.V_WIDTH /2 - 450, Tartaros.V_HEIGHT /2 - 195);
        resetButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button) {
                resetButton.setText("Reset all");
//                TODO Reset the whole game
            }
        });

        stage.addActor(startButton);
        stage.addActor(settingButton);
        stage.addActor(resetButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0.25f,0.25f,0.25f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    public void update(float delta){
        handleInput();
    }

    public void handleInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            app.exit();
            //closes app use to exit from fullscreen mode while developing
        }

    }

    @Override
    public void resize(int width, int height) {

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

    }
}
