package com.brogrammers.tartaros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brogrammers.tartaros.Tartaros;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class LoadingScreen implements Screen {

    private Tartaros game;

    private Stage stage;

    private float progress;

    private Texture backgroundTexture;
    private Image backgoundImage;

    private Label loadingLabel;

    private Label.LabelStyle loadingLabelStyle;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter loadingLabelParameter;

    private BitmapFont loadingLabelFont;

    public LoadingScreen(Tartaros game) {

        this.game = game;
        this.progress = 0f;

        this.stage = new Stage(new FitViewport(Tartaros.V_WIDTH, Tartaros.V_HEIGHT, game.camera));
        Gdx.input.setInputProcessor(stage);

        this.backgroundTexture = new Texture(Gdx.files.internal("graphics/loading_background21.png"));
        this.backgoundImage = new Image(backgroundTexture);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Fiolex_Mephisto.ttf"));

        loadingLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        loadingLabelParameter.size = 25;

        loadingLabelFont = generator.generateFont(loadingLabelParameter);

        loadingLabelStyle = new Label.LabelStyle(loadingLabelFont, new Color(1, 1, 1, 1));

        loadingLabel = new Label("Loading ...", loadingLabelStyle);

        stage.addActor(backgoundImage);
        stage.addActor(loadingLabel);

        queueAssests();
    }

    @Override
    public void show() {
        System.out.println("LOADING");

        backgoundImage.setPosition(0, 0);
        backgoundImage.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));

        loadingLabel.setPosition((Tartaros.V_WIDTH - loadingLabel.getWidth()) / 2, 200);
        loadingLabel.addAction(sequence(alpha(Tartaros.alphaStart), forever(sequence(fadeIn(1f), fadeOut(1f)))));

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

        progress = game.assets.getProgress()*360;

        stage.act(delta);

        if(game.assets.update()){
            game.setScreen(new PreReleaseScreen(game));
        }
    }

    public void handleInput(){
        if(Tartaros.DEVELOP) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                app.exit();
            }
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
        backgroundTexture.dispose();
        generator.dispose();
        loadingLabelFont.dispose();
    }

    private void queueAssests(){
        game.assets.load("badlogic.jpg", Texture.class);
        game.assets.load("graphics/background_menu_new.png", Texture.class);
        game.assets.load("skin/menu/menu.atlas", TextureAtlas.class);
        game.assets.load("skin/menu/menu.json", Skin.class, new SkinLoader.SkinParameter("skin/menu/menu.atlas"));
    }
}
