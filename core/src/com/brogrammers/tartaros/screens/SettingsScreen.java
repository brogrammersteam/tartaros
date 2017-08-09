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
    private TextButton resetAcceptButton;
    private TextButton resetRejectButton;
    private TextButton forceUpdateButton;
    private TextButton backButton;

    private Label titleLabel;
    private Label resetLabel;

    private Label.LabelStyle titleLabelStyle;
    private Label.LabelStyle resetLabelStyle;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter titleLabelParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter resetLabelParameter;

    private BitmapFont titleLabelFont;
    private BitmapFont resetLabelFont;

    public SettingsScreen(Tartaros game) {
        this.game = game;

        stage = new Stage(new FitViewport(Tartaros.V_WIDTH, Tartaros.V_HEIGHT, game.camera));
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.setDebug(Tartaros.DEBUG);

        skin = game.assets.get("skin/menu/menu.json", Skin.class);

        resetDialog = new Dialog("Reset Game", skin);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Fiolex_Mephisto.ttf"));

        titleLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        titleLabelParameter.size = 125;

        resetLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        resetLabelParameter.size = 25;

        titleLabelFont = generator.generateFont(titleLabelParameter);
        resetLabelFont = generator.generateFont(resetLabelParameter);

        titleLabelStyle = new Label.LabelStyle(titleLabelFont, new Color(1,1,1,1));
        titleLabel = new Label("Einstellungen", titleLabelStyle);

        resetLabelStyle = new Label.LabelStyle(resetLabelFont, new Color(1,1,1,1));
        resetLabel = new Label("Bist du dir sicher das du das Spiel zurücksetzten willst ?", resetLabelStyle);

        backgroundTexture = game.assets.get("graphics/background_menu_new.png", Texture.class);
        backgroundImage = new Image(backgroundTexture);

        languages = new String[]{"German", "English", "Bayrisch"};

        languageSelectBox = new SelectBox<String>(skin);
        languageSelectBox.setItems(languages);

        resetButton = new TextButton("Reset Game", skin);
        impressumButton = new TextButton("Created by", skin);
        resetAcceptButton = new TextButton("Ok", skin);
        resetRejectButton = new TextButton("Abbrechen", skin);
        forceUpdateButton = new TextButton("Force Update", skin);
        backButton = new TextButton("Back", skin);


        stage.addActor(backgroundImage);
        stage.addActor(backButton);
        stage.addActor(table);
    }

    @Override
    public void show() {
        System.out.println("SETTINGS");

        backgroundImage.setSize(Tartaros.V_WIDTH, Tartaros.V_HEIGHT);
        backgroundImage.setPosition(0,0);

        backButton.setPosition(25,25);
        backButton.setSize(backButton.getLabel().getWidth() + 20,backButton.getLabel().getHeight() + 20);

        resetButton.getLabel().setAlignment(Align.center);
        impressumButton.getLabel().setAlignment(Align.center);
        backButton.getLabel().setAlignment(Align.center);
        forceUpdateButton.getLabel().setAlignment(Align.center);

        resetDialog.text(resetLabel);
        resetDialog.setResizable(false);
        resetDialog.setMovable(false);
        resetDialog.button(resetAcceptButton);
        resetDialog.button(resetRejectButton);

        titleLabel.setAlignment(Align.center);

        table.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));

        table.bottom();
        table.row();
        table.add(titleLabel).expandX().padBottom(400).width(Tartaros.V_WIDTH - 100).center().colspan(2);
        table.row();
        table.add(languageSelectBox).expandX().padBottom(50).height(100).width(300).align(Align.right).padRight(10);
        table.add(forceUpdateButton).expandX().padBottom(50).height(100).width(300).align(Align.left).padLeft(10);
        table.row();
        table.add(resetButton).expandX().padBottom(75).height(100).width(300).align(Align.right).padRight(10);
        table.add(impressumButton).expandX().padBottom(75).height(100).width(300).align(Align.left).padLeft(10);
    }

    @Override
    public void render(float delta) {
        update(delta);

        stage.draw();
    }

    private void update(float delta){
        handleInput();
        setClickListener();

        stage.act(delta);
    }

    private void handleInput(){
        if(Tartaros.DEVELOP) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                app.exit();
            }
        }
    }

    private void setClickListener(){
        impressumButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                game.setScreen(new ImpressumScreen(game));
            }
        });

        resetButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                resetDialog.show(stage);
            }
        });

        resetAcceptButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                game.setScreen(new MenuScreen(game));
//                TODO Reset the whole Config
            }
        });

        forceUpdateButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                forceUpdateButton.setText("No function");
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                game.setScreen(new MenuScreen(game));
            }
        });
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {
        System.out.println("Settings Pause");
    }

    @Override
    public void resume() {
        System.out.println("Settings Resume");
    }

    @Override
    public void hide() {
        System.out.println("Settings Hide");
    }

    @Override
    public void dispose() {
        System.out.println("Dispose");
        stage.dispose();
        skin.dispose();
        backgroundTexture.dispose();
        titleLabelFont.dispose();
        resetLabelFont.dispose();
    }
}
