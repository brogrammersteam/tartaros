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
import com.brogrammers.tartaros.Settings;
import com.brogrammers.tartaros.Tartaros;

import static com.badlogic.gdx.Gdx.app;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SettingsScreen implements Screen {

    private Tartaros game;

    private Stage stage;
    private Table table;

    private Skin skin;

    private String screenName = "settings";

    private SelectBox languageSelectBox;

    private String[] languages;

    private Dialog resetDialog;
    private Dialog audioDialog;
    private Dialog languageDialog;

    private Texture backgroundTexture;
    private Image backgroundImage;

    private TextButton resetButton;
    private TextButton impressumButton;
    private TextButton resetAcceptButton;
    private TextButton resetRejectButton;
    private TextButton forceUpdateButton;
    private TextButton backButton;

    private TextButton audioButton;
    private TextButton audioAcceptButton;
    private TextButton languageButton;
    private TextButton languageAcceptButton;

    private CheckBox musicCheckBox;
    private CheckBox soundCheckBox;

    private Slider musicVolumeSlider;
    private Slider soundVolumeSlider;

    private Label titleLabel;
    private Label resetLabel;
    private Label audioLabel;
    private Label languageLabel;

    private Label.LabelStyle titleLabelStyle;
    private Label.LabelStyle dialogLabelStyle;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter titleLabelParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter dialogLabelParameter;

    private BitmapFont titleLabelFont;
    private BitmapFont dialogLabelFont;

    public SettingsScreen(Tartaros game) {
        this.game = game;

        stage = new Stage(new FitViewport(Tartaros.V_WIDTH, Tartaros.V_HEIGHT, game.camera));
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.setDebug(Tartaros.DEBUG);

        skin = game.assets.get("skin/menu/menu.json", Skin.class);

        resetDialog = new Dialog("Reset Game", skin);
        audioDialog = new Dialog("Audio", skin);
        languageDialog = new Dialog("Language", skin);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Fiolex_Mephisto.ttf"));

        titleLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        titleLabelParameter.size = 125;

        dialogLabelParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        dialogLabelParameter.size = 25;

        titleLabelFont = generator.generateFont(titleLabelParameter);
        dialogLabelFont = generator.generateFont(dialogLabelParameter);

        titleLabelStyle = new Label.LabelStyle(titleLabelFont, new Color(1,1,1,1));
        titleLabel = new Label("", titleLabelStyle);

        dialogLabelStyle = new Label.LabelStyle(dialogLabelFont, new Color(1,1,1,1));

        resetLabel = new Label("", dialogLabelStyle);
        audioLabel = new Label("", dialogLabelStyle);
        languageLabel = new Label("", dialogLabelStyle);

        backgroundTexture = game.assets.get("graphics/background_menu_new.png", Texture.class);
        backgroundImage = new Image(backgroundTexture);

        languages = new String[]{"German", "English", "Bayrisch"};

        languageSelectBox = new SelectBox<String>(skin);
        languageSelectBox.setItems(languages);

        resetButton = new TextButton("", skin);
        impressumButton = new TextButton("", skin);
        resetAcceptButton = new TextButton("", skin);
        resetRejectButton = new TextButton("", skin);
        forceUpdateButton = new TextButton("", skin);
        backButton = new TextButton("", skin);
        audioButton = new TextButton("", skin);
        audioAcceptButton = new TextButton("", skin);
        languageButton = new TextButton("", skin);
        languageAcceptButton = new TextButton("", skin);

        musicCheckBox = new CheckBox("", skin);
        soundCheckBox = new CheckBox("", skin);

        musicVolumeSlider = new Slider(0f, 1f, 0.1f, false, skin);
        musicVolumeSlider.setValue(Settings.getMusicVolume());

        soundVolumeSlider = new Slider(0f, 1f, 0.1f, false, skin);
        soundVolumeSlider.setValue(Settings.getSoundVolume());

        stage.addActor(backgroundImage);
        stage.addActor(backButton);
        stage.addActor(table);
    }

    @Override
    public void show() {
        System.out.println("SETTINGS");

        setLanguage();

        if (Settings.getMusic()){
            musicCheckBox.setChecked(true);
        }else {
            musicCheckBox.setChecked(false);
        }

        if(Settings.getSound()){
            soundCheckBox.setChecked(true);
        }else {
            soundCheckBox.setChecked(false);
        }

        backgroundImage.setSize(Tartaros.V_WIDTH, Tartaros.V_HEIGHT);
        backgroundImage.setPosition(0,0);

        backButton.setPosition(25,25);
        backButton.setSize(150,75);
        backButton.getLabel().setAlignment(Align.center);

        resetButton.getLabel().setAlignment(Align.center);
        impressumButton.getLabel().setAlignment(Align.center);
        backButton.getLabel().setAlignment(Align.center);
        forceUpdateButton.getLabel().setAlignment(Align.center);

        resetDialog.setResizable(false);
        resetDialog.setMovable(false);
        resetDialog.setDebug(Tartaros.DEBUG);
        resetDialog.text(resetLabel);
        resetDialog.button(resetAcceptButton);
        resetDialog.button(resetRejectButton);

        audioDialog.setResizable(false);
        audioDialog.setMovable(false);
        audioDialog.setDebug(Tartaros.DEBUG);

        audioDialog.getContentTable().top();
        audioDialog.getContentTable().add(audioLabel);
        audioDialog.getContentTable().pad(50);

        audioDialog.getButtonTable().add(musicCheckBox).expandX().align(Align.center);
        audioDialog.getButtonTable().add(soundCheckBox).expandX().align(Align.center);
        audioDialog.getButtonTable().row();
        audioDialog.getButtonTable().add(musicVolumeSlider).expandX().align(Align.center);
        audioDialog.getButtonTable().add(soundVolumeSlider).expandX().align(Align.center);
        audioDialog.getButtonTable().row();
        audioDialog.getButtonTable().add(audioAcceptButton).colspan(2).align(Align.center);
        audioDialog.getButtonTable().pad(50);

        languageDialog.setPosition((Tartaros.V_WIDTH - languageDialog.getWidth()) / 2,(Tartaros.V_HEIGHT - languageDialog.getHeight()) / 2);
        languageDialog.setResizable(false);
        languageDialog.setMovable(false);
        languageDialog.setDebug(Tartaros.DEBUG);

        languageDialog.getContentTable().add(languageLabel);
        languageDialog.getContentTable().row();
        languageDialog.getContentTable().pad(50);

        languageDialog.getButtonTable().add(languageSelectBox).expandX().align(Align.center).pad(20).width(200);
        languageDialog.getButtonTable().row();
        languageDialog.getButtonTable().add(languageAcceptButton).expandX().align(Align.center).pad(20).width(200);
        languageDialog.getButtonTable().pad(50);

        titleLabel.setAlignment(Align.center);

        table.addAction(sequence(alpha(Tartaros.alphaStart), fadeIn(Tartaros.fadeTime)));

        table.bottom();
        table.row();
        table.add(titleLabel).expandX().padBottom(400).width(Tartaros.V_WIDTH - 100).center().colspan(2);
        table.row();
        table.add(audioButton).expandX().padBottom(50).height(100).width(300).align(Align.right).padRight(10);
        table.add(languageButton).expandX().padBottom(50).height(100).width(300).align(Align.left).padLeft(10);
        table.row();
        table.add(forceUpdateButton).expandX().padBottom(50).height(100).width(300).align(Align.right).padRight(10);
        table.add(resetButton).expandX().padBottom(50).height(100).width(300).align(Align.left).padLeft(10);
        table.row();
        table.add(impressumButton).expandX().padBottom(75).height(100).width(300).colspan(2);

        languageSelectBox.setSelected(Tartaros.mainSettings.getString("language"));

        setChangeListener();
    }

    @Override
    public void render(float delta) {
        update(delta);

        stage.draw();

        if(musicCheckBox.isChecked()){
            Settings.setMusic(true);
        }
        else{
            Settings.setMusic(false);
        }

        if(soundCheckBox.isChecked()){
            Settings.setSound(true);
        }else{
            Settings.setSound(false);
        }

    }

    private void update(float delta){
        handleInput();

        stage.act(delta);
    }

    private void handleInput(){
        if(Tartaros.DEVELOP) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                app.exit();
            }
        }
    }

    private void setLanguage(){

        System.out.println("Set Languages");

//        Default view
        titleLabel.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "titleText"));
        resetButton.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "resetButtonText"));
        languageButton.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "languageButtonText"));
        forceUpdateButton.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "forceUpdateButtonText"));
        audioButton.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "audioButtonText"));
        impressumButton.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "impressumButtonText"));
        backButton.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "backButtonText"));

//        Reset Dialog view
        resetLabel.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "resetText"));
        resetRejectButton.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "resetRejectButtonText"));
        resetAcceptButton.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "resetAcceptButtonText"));

//        Audio Dialog view
        audioLabel.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "audioText"));
        audioAcceptButton.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "audioAcceptButtonText"));
        musicCheckBox.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "musicCheckBoxText"));
        soundCheckBox.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "soundCheckBoxText"));

//        Language Dialog view
        languageLabel.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "languageText"));
        languageAcceptButton.setText(Tartaros.xmlHandler.getLanguageStrings(screenName, "languageAcceptButton"));

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
        dialogLabelFont.dispose();

    }

    private void setChangeListener(){

        languageButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                languageDialog.show(stage);
            }
        });

        audioAcceptButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                audioDialog.hide();
            }
        });

        languageAcceptButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                languageDialog.hide();
            }
        });

        impressumButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                game.setScreen(new ImpressumScreen(game));
            }
        });

        resetButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                resetDialog.show(stage);
            }
        });

        forceUpdateButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                forceUpdateButton.setText("No function");
            }
        });

        audioButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                audioDialog.show(stage);
            }
        });

        musicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.setMusicVolume(musicVolumeSlider.getValue());
            }
        });

        soundVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.setSoundVolume(musicVolumeSlider.getValue());
            }
        });

        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                game.setScreen(new MenuScreen(game));
                Tartaros.mainSettings.flush();
            }
        });

        resetAcceptButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor){
                game.setScreen(new MenuScreen(game));
                Tartaros.initializeSettings();
            }
        });

        languageSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.setLanguage(languages[languageSelectBox.getSelectedIndex()]);
                setLanguage();
            }
        });
    }
}
