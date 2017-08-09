package com.brogrammers.tartaros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brogrammers.tartaros.Tartaros;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class ImpressumScreen implements Screen {

    private Tartaros game;

    private Stage stage;
    private Table table;

    private Skin skin;

    private String impressumText = "Dies ist ein Projekt, welches uns sehr am Herzen liegt. Es entsteht, dank der Code+Design Initiative! Wir möchten ein Spiel entwickeln, welches so ergreifend ist, dass man es nicht mehr loslassen möchte. Man soll darin so gefangen sein wie im Tartaros, der mystischen Unterwelt der Götterwelt des antiken Griechenlands. Die Kernfrage ist: Beobachtet dich Tartaros oder beobachtest du Ihn?";
    private String thanksText = "Ein riesen Dankeschön geht hierbei an die Code+Design Initiative.";

    private Texture backgroundTexture;
    private Image backgroundImage;

    private Label headerLabel;
    private Label sebastianLabel;
    private Label adrianLabel;
    private Label moritzLabel;
    private Label sebastianJobLabel;
    private Label adrianJobLabel;
    private Label moritzJobLabel;
    private Label textLabel;
    private Label thanksLabel;

    private Label.LabelStyle headerLabelStyle;
    private Label.LabelStyle developerLabelStyle;
    private Label.LabelStyle jobLabelStyle;
    private Label.LabelStyle textLabelStyle;
    private Label.LabelStyle thanksLabelStyle;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter headerLabelParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter developerLabelParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter jobLabelParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter textLabelParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter thanksLabelParameter;

    private BitmapFont headerLabelFont;
    private BitmapFont developerLabelFont;
    private BitmapFont jobLabelFont;
    private BitmapFont textLabelFont;
    private BitmapFont thanksLabelFont;

    private TextButton backButton;

    public ImpressumScreen(final Tartaros game){
        this.game = game;

        stage = new Stage(new FitViewport(Tartaros.V_WIDTH, Tartaros.V_HEIGHT, game.camera));
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.setDebug(Tartaros.DEBUG);

        skin = game.assets.get("skin/menu/menu.json", Skin.class);

        backgroundTexture = game.assets.get("graphics/background_menu_new.png", Texture.class);
        backgroundImage = new Image(backgroundTexture);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Fiolex_Mephisto.ttf"));

        headerLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        headerLabelParameter.size = 80;

        developerLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        developerLabelParameter.size = 50;

        jobLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        jobLabelParameter.size = 30;

        textLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        textLabelParameter.size = 30;

        thanksLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        thanksLabelParameter.size = 35;

        headerLabelFont = generator.generateFont(headerLabelParameter);
        developerLabelFont = generator.generateFont(developerLabelParameter);
        jobLabelFont = generator.generateFont(jobLabelParameter);
        textLabelFont = generator.generateFont(textLabelParameter);
        thanksLabelFont = generator.generateFont(thanksLabelParameter);

        headerLabelStyle = new Label.LabelStyle(headerLabelFont, new Color(1, 1, 1, 1));
        developerLabelStyle = new Label.LabelStyle(developerLabelFont, new Color(1,1,1,1));
        jobLabelStyle = new Label.LabelStyle(jobLabelFont, new Color(1,1,1,1));
        textLabelStyle = new Label.LabelStyle(textLabelFont, new Color(1,1,1,1));
        thanksLabelStyle = new Label.LabelStyle(thanksLabelFont, new Color(1,1,1,1));

        headerLabel = new Label("Created by:", headerLabelStyle);

        sebastianLabel = new Label("Sebastian Schmailzl", developerLabelStyle);
        adrianLabel = new Label("Adrian Beckmann", developerLabelStyle);
        moritzLabel = new Label("Moritz Wachter", developerLabelStyle);

        sebastianJobLabel = new Label("Master of Code", jobLabelStyle);
        adrianJobLabel = new Label("Master of Design", jobLabelStyle);
        moritzJobLabel = new Label("Master of Desaster", jobLabelStyle);

        textLabel = new Label(impressumText, textLabelStyle);
        thanksLabel = new Label(thanksText, thanksLabelStyle);

        backButton = new TextButton("Back", skin);

        stage.addActor(backgroundImage);
        stage.addActor(backButton);
        stage.addActor(table);
    }

    @Override
    public void show() {
        System.out.println("IMPRESSUM");

        backgroundImage.setSize(Tartaros.V_WIDTH, Tartaros.V_HEIGHT);
        backgroundImage.setPosition(0,0);

        backButton.setPosition(25,25);
        backButton.setSize(backButton.getLabel().getWidth() + 20,backButton.getLabel().getHeight() + 20);
        backButton.getLabel().setAlignment(Align.center);

        textLabel.setWrap(true);
        textLabel.setAlignment(Align.center);
        textLabel.setWidth(Tartaros.V_WIDTH - 100);

        thanksLabel.setWrap(true);
        thanksLabel.setAlignment(Align.center);
        thanksLabel.setWidth(Tartaros.V_WIDTH - 100);

        backButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                game.setScreen(new SettingsScreen(game));
            }
        });

        table.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));

        table.top();
        table.row();
        table.add(headerLabel).expandX().padTop(20).center().colspan(3);
        table.row();
        table.add(sebastianLabel).expandX().padTop(100).center();
        table.add(adrianLabel).expandX().padTop(100).center();
        table.add(moritzLabel).expandX().padTop(100).center();
        table.row();
        table.add(sebastianJobLabel).expandX().padTop(50).center();
        table.add(adrianJobLabel).expandX().padTop(50).center();
        table.add(moritzJobLabel).expandX().padTop(50).center();
        table.row();
        table.add(textLabel).width(Tartaros.V_WIDTH).expandX().padTop(75).center().colspan(3);
        table.row();
        table.add(thanksLabel).width(Tartaros.V_WIDTH).expandX().padTop(50).center().colspan(3);
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
        stage.dispose();
        backgroundTexture.dispose();
        generator.dispose();
        thanksLabelFont.dispose();
        textLabelFont.dispose();
        jobLabelFont.dispose();
        developerLabelFont.dispose();
        headerLabelFont.dispose();
    }
}
