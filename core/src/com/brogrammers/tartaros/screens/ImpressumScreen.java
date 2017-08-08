package com.brogrammers.tartaros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brogrammers.tartaros.Tartaros;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class ImpressumScreen implements Screen {

    private Tartaros game;

    private Stage stage;
    private Table table;

    private Texture backgroundTexture;
    private Image backgroundImage;

    private Label headerLabel;
    private Label sebastianLabel;
    private Label adrianLabel;
    private Label moritzLabel;

    private Label.LabelStyle headerLabelStyle;
    private Label.LabelStyle developerLabelStyle;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter headerLabelParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter developerLabelParameter;

    private BitmapFont headerLabelFont;
    private BitmapFont developerLabelFont;

    public ImpressumScreen(final Tartaros game){
        this.game = game;

        stage = new Stage(new FitViewport(Tartaros.V_WIDTH, Tartaros.V_HEIGHT, game.camera));
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.setDebug(Tartaros.DEBUG);

        backgroundTexture = game.assets.get("graphics/background_menu_new.png", Texture.class);
        backgroundImage = new Image(backgroundTexture);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Fiolex_Mephisto.ttf"));

        headerLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        headerLabelParameter.size = 80;

        developerLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        developerLabelParameter.size = 50;

        headerLabelFont = generator.generateFont(headerLabelParameter);
        developerLabelFont = generator.generateFont(developerLabelParameter);

        headerLabelStyle = new Label.LabelStyle(headerLabelFont, new Color(1, 1, 1, 1));
        developerLabelStyle = new Label.LabelStyle(developerLabelFont, new Color(1,1,1,1));

        headerLabel = new Label("Created by:", headerLabelStyle);
        sebastianLabel = new Label("Sebastian Schmailzl", developerLabelStyle);
        adrianLabel = new Label("Adrian Beckmann", developerLabelStyle);
        moritzLabel = new Label("Moritz Wachter", developerLabelStyle);

        stage.addActor(backgroundImage);
        stage.addActor(table);
    }

    @Override
    public void show() {
        System.out.println("IMPRESSUM");

        backgroundImage.setSize(Tartaros.V_WIDTH, Tartaros.V_HEIGHT);
        backgroundImage.setPosition(0,0);

        table.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));

        table.top();
        table.row();
        table.add(headerLabel).expandX().padTop(20).center().colspan(3);
        table.row();
        table.add(sebastianLabel).expandX().padTop(100).center();
        table.add(adrianLabel).expandX().padTop(100).center();
        table.add(moritzLabel).expandX().padTop(100).center();
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
