package com.brogrammers.tartaros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brogrammers.tartaros.Tartaros;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MenuScreen implements Screen {

    private Tartaros game;

    private Stage stage;
    private Table table;

    private static final String singlePlayerText_de = "Einzelspieler";
    private static final String singlePlayerText_en = "Singleplayer";
    private static final String singlePlayerText_bay = "Alloa";

    private String multiPlayerText_de = "Mehrspieler";
    private String multiPlayerText_en = "Multiplayer";
    private String multiPlayerText_bay = "Mitanand";

    private String settingsText_de = "Einstellungen";
    private String settingsText_en = "Settings";
    private String settingsText_bay = "Einstellungen";

    private String reportText_de = "Probleme melden";
    private String reportText_en = "Report to Github";
    private String reportText_bay = "Was meckern";

    private String exitText_de = "Beenden";
    private String exitText_en = "Exit";
    private String exitText_bay = "Raus da";

    private Skin skin;

    private Container container;

    private Label titleLabel;
    private Label versionLabel;
    private Label splashLabel;

    private Label.LabelStyle titleLabelStyle;
    private Label.LabelStyle versionLabelStyle;
    private Label.LabelStyle splashLabelStyle;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter titleLabelParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter versionLabelParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter splashLabelParameter;

    private BitmapFont titleLabelFont;
    private BitmapFont versionLabelFont;
    private BitmapFont splashLabelFont;

    private Texture backgroundTexture;
    private Image backgroundImage;

    private TextButton singlePlayerButton;
    private TextButton multiPlayerButton;
    private TextButton settingsButton;
    private TextButton reportButton;
    private TextButton quitButton;

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

        splashLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        splashLabelParameter.size = 40;

        versionLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        versionLabelParameter.size = 30;

        titleLabelFont = generator.generateFont(titleLabelParameter);
        versionLabelFont = generator.generateFont(versionLabelParameter);
        splashLabelFont = generator.generateFont(splashLabelParameter);

        titleLabelStyle = new Label.LabelStyle(titleLabelFont, new Color(1,1,1,1));
        versionLabelStyle = new Label.LabelStyle(versionLabelFont, new Color(1,1,1,1));
        splashLabelStyle = new Label.LabelStyle(splashLabelFont, new Color(1,1,1,1));

        titleLabel = new Label("Tartaros", titleLabelStyle);
        titleLabel.setAlignment(Align.center);

        versionLabel = new Label("Version: " + Tartaros.VERSION, versionLabelStyle);
        versionLabel.setAlignment(Align.center);

        splashLabel = new Label("", splashLabelStyle);
        splashLabel.setAlignment(Align.center);

        singlePlayerButton = new TextButton("", skin);
        multiPlayerButton = new TextButton("", skin);
        settingsButton = new TextButton("", skin);
        reportButton = new TextButton("", skin);
        quitButton = new TextButton("", skin);

        container = new Container(splashLabel);

        stage.addActor(backgroundImage);
        stage.addActor(versionLabel);
        stage.addActor(container);
        stage.addActor(table);
    }

    @Override
    public void show() {
        System.out.println("MENU");

        setLanguage();

        backgroundImage.setSize(Tartaros.V_WIDTH, Tartaros.V_HEIGHT);
        backgroundImage.setPosition(0,0);

        versionLabel.setPosition((Tartaros.V_WIDTH - versionLabel.getWidth()) / 2, 800);
        versionLabel.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));

        splashLabel.setText(Tartaros.splashArray.random());

        container.setPosition(250, Tartaros.V_HEIGHT - 490 );
        container.setDebug(true);
        container.setTransform(true);
        container.setRotation(22);
        container.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));

        singlePlayerButton.getLabel().setAlignment(Align.center);
        multiPlayerButton.getLabel().setAlignment(Align.center);
        settingsButton.getLabel().setAlignment(Align.center);
        reportButton.getLabel().setAlignment(Align.center);
        quitButton.getLabel().setAlignment(Align.center);

        table.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));
        table.bottom();

        table.row();
        table.add(titleLabel).expandX().width(Tartaros.V_WIDTH - 100).height(300).center().padBottom(150);
        table.row();
        table.add(singlePlayerButton).expandX().width(500).height(100).center().padBottom(50);
        table.row();
        table.add(multiPlayerButton).expandX().width(500).height(100).center().padBottom(50);
        table.row();
        table.add(settingsButton).expandX().width(500).height(100).center().padBottom(50);
        table.row();
        table.add(reportButton).expandX().width(500).height(100).center().padBottom(50);
        table.row();
        table.add(quitButton).expandX().width(500).height(100).center().padBottom(30);

        setChangeListener();
    }

    @Override
    public void render(float delta) {
        update(delta);

        stage.draw();
    }

    private void update(float delta){
        stage.act(delta);
        handleInput();
    }

    private void handleInput(){
        if(Tartaros.DEVELOP) {
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                app.exit();
            }
        }

    }

    private void setLanguage(){

        System.out.println("Set Languages");

        if(Tartaros.mainSettings.getString("language").equals("German")){
            System.out.println("German");
            singlePlayerButton.setText(singlePlayerText_de);
            multiPlayerButton.setText(multiPlayerText_de);
            settingsButton.setText(settingsText_de);
            reportButton.setText(reportText_de);
            quitButton.setText(exitText_de);
        }else if (Tartaros.mainSettings.getString("language").equals("Bayrisch")){
            System.out.println("Bayrisch");
            singlePlayerButton.setText(singlePlayerText_bay);
            multiPlayerButton.setText(multiPlayerText_bay);
            settingsButton.setText(settingsText_bay);
            reportButton.setText(reportText_bay);
            quitButton.setText(exitText_bay);
        }else if (Tartaros.mainSettings.getString("language").equals("English")){
            System.out.println("English");
            singlePlayerButton.setText(singlePlayerText_en);
            multiPlayerButton.setText(multiPlayerText_en);
            settingsButton.setText(settingsText_en);
            reportButton.setText(reportText_en);
            quitButton.setText(exitText_en);
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
        splashLabelFont.dispose();
    }

    public void setChangeListener(){

        singlePlayerButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                game.setScreen(new SingleplayerMenuScreen(game));
            }
        });

        multiPlayerButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                multiPlayerButton.setText("Game Started");
//                TODO Let the Game start the MultiPlayer Mode
            }
        });

        settingsButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                game.setScreen(new SettingsScreen(game));
            }
        });


        reportButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                Gdx.net.openURI("https://github.com/brogrammersteam/tartaros/issues");
            }
        });

        quitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                Gdx.app.exit();
            }
        });
    }
}
