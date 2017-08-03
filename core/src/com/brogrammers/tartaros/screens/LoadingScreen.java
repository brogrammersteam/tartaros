package com.brogrammers.tartaros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brogrammers.tartaros.Tartaros;

import static com.badlogic.gdx.Gdx.app;

public class LoadingScreen implements Screen {

    private Tartaros game;

    private Stage stage;

    private float progress;

    private ProgressBar progressBar;
    private ProgressBar.ProgressBarStyle progressBarStyle;

    public LoadingScreen(Tartaros game) {

        this.game = game;
        this.progress = 0f;
        this.stage = new Stage(new FitViewport(Tartaros.V_WIDTH, Tartaros.V_HEIGHT, game.camera));

        Pixmap pixmap = new Pixmap(100, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        TextureRegionDrawable backDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        Pixmap pixmap2 = new Pixmap(0, 20, Pixmap.Format.RGBA8888);
        pixmap2.setColor(Color.GREEN);
        pixmap2.fill();
        TextureRegionDrawable knobDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap2)));
        pixmap2.dispose();

        Pixmap pixmap3 = new Pixmap(100, 20, Pixmap.Format.RGBA8888);
        pixmap3.setColor(Color.GREEN);
        pixmap3.fill();
        TextureRegionDrawable knobBeforeDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap3)));
        pixmap3.dispose();

        this.progressBarStyle = new ProgressBar.ProgressBarStyle();
        this.progressBarStyle.background = backDrawable;
        this.progressBarStyle.knob = knobDrawable;
        this.progressBarStyle.knobBefore = knobBeforeDrawable;

        this.progressBar = new ProgressBar(0.0f, 1.0f, 0.01f, false, progressBarStyle);

        stage.addActor(progressBar);

        queueAssests();
    }

    @Override
    public void show() {
        System.out.println("LOADING");

        progressBar.setPosition((Tartaros.V_WIDTH - progressBar.getWidth()) / 2, (Tartaros.V_HEIGHT - progressBar.getHeight()) / 2 + 200);
        progressBar.setValue(0f);
        progressBar.setAnimateDuration(0.25f);
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

        progress = game.assets.getProgress();
        progressBar.setValue(progress);

        stage.act(delta);

        if(game.assets.update() && progressBar.getValue() == 1.0f){
            game.setScreen(new PreReleaseScreen(game));
//            TODO Whole Loading Screen needs a graphical update
        }
    }

    public void handleInput(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {

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

    private void queueAssests(){
        game.assets.load("badlogic.jpg", Texture.class);
    }
}
