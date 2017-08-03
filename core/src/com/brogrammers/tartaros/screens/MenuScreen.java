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
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MenuScreen implements Screen {

    private Tartaros game;

    private Stage stage;
    private Skin skin;

    private TextButton singlePlayerButton;
    private TextButton multiPlayerButton;
    private TextButton settingsButton;
    private TextButton reportButton;

    public MenuScreen(Tartaros game) {
        this.game = game;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        singlePlayerButton = new TextButton("Singleplayer", skin);
        multiPlayerButton = new TextButton("Multiplayer", skin);
        settingsButton = new TextButton("Settings", skin);
        reportButton = new TextButton("Report to our Github Repository", skin);

        stage.addActor(singlePlayerButton);
        stage.addActor(multiPlayerButton);
        stage.addActor(settingsButton);
        stage.addActor(reportButton);
    }

    @Override
    public void show() {
        System.out.println("MENU");

        singlePlayerButton.setSize(500,100);
        singlePlayerButton.setPosition((Tartaros.V_WIDTH - singlePlayerButton.getWidth()) /2 ,Tartaros.V_HEIGHT /2 + 200);
        singlePlayerButton.addAction(sequence(alpha(0f), fadeIn(2f)));
        singlePlayerButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                singlePlayerButton.setText("Game Started");
//                TODO Let the Game start the SinglePlayer Mode
            }
        });

        multiPlayerButton.setSize(500,100);
        multiPlayerButton.setPosition((Tartaros.V_WIDTH - multiPlayerButton.getWidth()) / 2, Tartaros.V_HEIGHT / 2 + 75);
        multiPlayerButton.addAction(sequence(alpha(0f), fadeIn(2f)));
        multiPlayerButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                multiPlayerButton.setText("Game Started");
//                TODO Let the Game start the MultiPlayer Mode
            }
        });

        settingsButton.setSize(500, 100);
        settingsButton.setPosition((Tartaros.V_WIDTH - multiPlayerButton.getWidth()) / 2, Tartaros.V_HEIGHT / 2 - 50);
        settingsButton.addAction(sequence(alpha(0f), fadeIn(2f)));
        settingsButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                settingsButton.setText("Settings Started");
//                TODO Let the Game start the Settings Mode
            }
        });

        reportButton.setSize(500, 100);
        reportButton.setPosition((Tartaros.V_WIDTH - multiPlayerButton.getWidth()) / 2, Tartaros.V_HEIGHT / 2 - 175);
        reportButton.addAction(sequence(alpha(0f), fadeIn(2f)));
        reportButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                Gdx.net.openURI("https://github.com/brogrammersteam/tartaros/issues");
            }
        });
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
