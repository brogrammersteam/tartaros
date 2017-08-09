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

public class MenuScreen implements Screen {

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

    private TextButton singlePlayerButton;
    private TextButton multiPlayerButton;
    private TextButton settingsButton;
    private TextButton reportButton;

    public MenuScreen(Tartaros game) {
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

        titleLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        titleLabelParameter.size = 200;

        titleLabelFont = generator.generateFont(titleLabelParameter);

        titleLabelStyle = new Label.LabelStyle(titleLabelFont, new Color(1,1,1,1));

        titleLabel = new Label("Tartaros", titleLabelStyle);
        titleLabel.setAlignment(Align.center);

        singlePlayerButton = new TextButton("Singleplayer", skin);
        multiPlayerButton = new TextButton("Multiplayer", skin);
        settingsButton = new TextButton("Settings", skin);
        reportButton = new TextButton("Report to Github", skin);

        stage.addActor(backgroundImage);
        stage.addActor(table);
    }

    @Override
    public void show() {
        System.out.println("MENU");

        backgroundImage.setSize(Tartaros.V_WIDTH, Tartaros.V_HEIGHT);
        backgroundImage.setPosition(0,0);

        singlePlayerButton.getLabel().setAlignment(Align.center);
        multiPlayerButton.getLabel().setAlignment(Align.center);
        settingsButton.getLabel().setAlignment(Align.center);
        reportButton.getLabel().setAlignment(Align.center);

        table.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));
        table.bottom();

        table.row();
        table.add(titleLabel).expandX().width(Tartaros.V_WIDTH - 100).height(300).center().padBottom(175);
        table.row();
        table.add(singlePlayerButton).expandX().width(500).height(100).center().padBottom(50);
        table.row();
        table.add(multiPlayerButton).expandX().width(500).height(100).center().padBottom(50);
        table.row();
        table.add(settingsButton).expandX().width(500).height(100).center().padBottom(50);
        table.row();
        table.add(reportButton).expandX().width(500).height(100).center().padBottom(90);

        singlePlayerButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                singlePlayerButton.setText("Game Started");
//                TODO Let the Game start the SinglePlayer Mode
            }
        });

        multiPlayerButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                multiPlayerButton.setText("Game Started");
//                TODO Let the Game start the MultiPlayer Mode
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                game.setScreen(new SettingsScreen(game));
            }
        });


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

        stage.act(delta);
        stage.draw();
    }

    public void update(float delta){
        handleInput();
    }

    public void handleInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
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
        skin.dispose();
        generator.dispose();
        backgroundTexture.dispose();
        titleLabelFont.dispose();
    }
}
