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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brogrammers.tartaros.Tartaros;
import sun.security.util.BitArray;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class ImpressumScreen implements Screen {

    private Tartaros game;

    private Stage stage;
    private Table table;

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

        stage.addActor(table);
    }

    @Override
    public void show() {
        System.out.println("IMPRESSUM");

        table.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));

        table.top();
        table.add(headerLabel).expandX().padTop(20).height(100).width(300);
        table.row();
        table.add(sebastianLabel).expandX().padTop(100).height(100).width(200);
        table.add(adrianLabel).expandX().padTop(100).height(100).width(200);
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
