package com.brogrammers.tartaros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brogrammers.tartaros.Settings;
import com.brogrammers.tartaros.Tartaros;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SingleplayerMenuScreen implements Screen {

    private Tartaros game;

    private Stage stage;
    private Table table;

    private Skin skin;

    private Label titleLabel;

    private Label.LabelStyle titleLabelStyle;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter titleLabelParameter;

    private BitmapFont titleLabelFont;

    private Texture backgroundTexture;
    private Image backgroundImage;

    private TextButton backButton;
    private TextButton savegame1;
    private TextButton savegame2;
    private TextButton savegame3;

    public SingleplayerMenuScreen(Tartaros game) {
        this.game = game;

        stage = new Stage(new FitViewport(Tartaros.V_WIDTH, Tartaros.V_HEIGHT, game.camera));
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.setDebug(Tartaros.DEBUG);

        skin = game.assets.get("skin/menu/menu.json", Skin.class);

        backgroundTexture = game.assets.get("graphics/background_menu_new.png", Texture.class);
        backgroundImage = new Image(backgroundTexture);

        backButton = new TextButton("Back", skin);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Fiolex_Mephisto.ttf"));

        titleLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        titleLabelParameter.size = 160;

        titleLabelFont = generator.generateFont(titleLabelParameter);

        titleLabelStyle = new Label.LabelStyle(titleLabelFont, new Color(1,1,1,1));

        titleLabel = new Label("Singleplayer", titleLabelStyle);
        titleLabel.setAlignment(Align.center);

        savegame1 = new TextButton(Settings.getSavegameName(1), skin);
        savegame2 = new TextButton(Settings.getSavegameName(2), skin);
        savegame3 = new TextButton(Settings.getSavegameName(3), skin);

        stage.addActor(backgroundImage);
        stage.addActor(backButton);
        stage.addActor(titleLabel);
        stage.addActor(table);
    }

    @Override
    public void show() {
        System.out.println("MENU");

        backgroundImage.setSize(Tartaros.V_WIDTH, Tartaros.V_HEIGHT);
        backgroundImage.setPosition(0,0);

        backButton.setPosition(25,25);
        backButton.setSize(150,75);
        backButton.getLabel().setAlignment(Align.center);

        titleLabel.setPosition((Tartaros.V_WIDTH - titleLabel.getWidth()) / 2, Tartaros.V_HEIGHT - titleLabel.getHeight() + 125);
        titleLabel.setSize(titleLabel.getWidth(), titleLabel.getHeight());
        titleLabel.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));

        titleLabel.setAlignment(Align.center);
        backButton.getLabel().setAlignment(Align.center);
        savegame1.getLabel().setAlignment(Align.center);
        savegame2.getLabel().setAlignment(Align.center);
        savegame3.getLabel().setAlignment(Align.center);

        table.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));
        table.top();

        table.row().padTop(300);
        table.add(savegame1).expandX();
        table.add(savegame2).expandX();
        table.add(savegame3).expandX();

        setChangeListener();
    }

    @Override
    public void render(float delta) {
        update(delta);

        stage.draw();
    }

    public void update(float delta){
        stage.act(delta);
        handleInput();
    }

    public void handleInput(){
        if(Tartaros.DEVELOP) {
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                app.exit();
            }
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
        stage.dispose();
        skin.dispose();
        backgroundTexture.dispose();
        generator.dispose();
        titleLabelFont.dispose();
    }

    public void setChangeListener(){

        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                game.setScreen(new MenuScreen(game));
            }
        });
    }
}
