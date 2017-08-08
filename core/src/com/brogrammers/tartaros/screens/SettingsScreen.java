package com.brogrammers.tartaros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
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

    private Dialog resetDialog;

    private Texture backgroundTexture;
    private Image backgroundImage;

    private TextButton resetButton;
    private TextButton impressumButton;

    public SettingsScreen(Tartaros game) {
        this.game = game;

        stage = new Stage(new FitViewport(Tartaros.V_WIDTH, Tartaros.V_HEIGHT, game.camera));
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.setDebug(Tartaros.DEBUG);

        skin = game.assets.get("skin/menu/menu.json", Skin.class);

        resetDialog = new Dialog("Reset Game", skin);

        backgroundTexture = game.assets.get("graphics/background_menu_new.png", Texture.class);
        backgroundImage = new Image(backgroundTexture);

        languages = new String[]{"German", "English", "Bayrisch"};

        languageSelectBox = new SelectBox<String>(skin);
        languageSelectBox.setItems(languages);

        resetButton = new TextButton("Reset Game", skin);
        impressumButton = new TextButton("Created by", skin);


        stage.addActor(backgroundImage);
        stage.addActor(table);
    }

    @Override
    public void show() {
        System.out.println("SETTINGS");

        backgroundImage.setSize(Tartaros.V_WIDTH, Tartaros.V_HEIGHT);
        backgroundImage.setPosition(0,0);

        resetButton.getLabel().setAlignment(Align.center);
        impressumButton.getLabel().setAlignment(Align.center);

        impressumButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
            game.setScreen(new ImpressumScreen(game));
//                TODO Let the Game start the SinglePlayer Mode
            }
        });

        resetButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                resetDialog.show(stage);
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
