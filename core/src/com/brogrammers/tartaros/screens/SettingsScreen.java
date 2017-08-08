package com.brogrammers.tartaros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brogrammers.tartaros.Tartaros;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SettingsScreen implements Screen {

    private Tartaros game;

    private Stage stage;
    private Table table;

    private Skin skin;

    private SelectBox languageSelectBox;

    private String[] languages;

    private TextButton resetButton;
    private TextButton impressumButton;

    public SettingsScreen(Tartaros game) {
        this.game = game;

        stage = new Stage(new FitViewport(Tartaros.V_WIDTH, Tartaros.V_HEIGHT, game.camera));
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);

        skin = new Skin(Gdx.files.internal("skin/menu/menu.json"));

        languages = new String[]{"German", "English", "Bayrisch"};

        languageSelectBox = new SelectBox<String>(skin);
        languageSelectBox.setItems(languages);

        resetButton = new TextButton("Reset Game", skin);
        impressumButton = new TextButton("Impressum", skin);

        stage.addActor(table);
    }

    @Override
    public void show() {
        System.out.println("SETTINGS");

        impressumButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
            game.setScreen(new ImpressumScreen(game));
//                TODO Let the Game start the SinglePlayer Mode
            }
        });

        table.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));

        table.add(languageSelectBox).expandX().padTop(20).height(100).width(300);
        table.row();
        table.add(resetButton).expandX().padTop(20).height(100).width(300);
        table.row();
        table.add(impressumButton).expandX().padTop(20).height(100).width(300);
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            app.exit();
        }
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
