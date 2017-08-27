package com.brogrammers.tartaros;

import com.badlogic.gdx.Gdx;

public class Settings {

    public static void setMusic(boolean status){
        if(status){
            Tartaros.mainSettings.putBoolean(Tartaros.MUSICKEY, true);
            Tartaros.music.play();

        }else{
            Tartaros.mainSettings.putBoolean(Tartaros.MUSICKEY, false);
            Tartaros.music.stop();
        }

        Tartaros.mainSettings.flush();
    }

    public static void setMusicVolume(float volume){
        Tartaros.mainSettings.putFloat(Tartaros.MUSICVOLUMEKEY, volume);
        Tartaros.music.setVolume(volume);

        Tartaros.mainSettings.flush();
    }

    public static void setSound(boolean status){
        if(status){
            Tartaros.mainSettings.putBoolean(Tartaros.SOUNDKEY, true);
        }else{
            Tartaros.mainSettings.putBoolean(Tartaros.SOUNDKEY, false);
        }

        Tartaros.mainSettings.flush();
    }

    public static void setSoundVolume(float volume){
        Tartaros.mainSettings.putFloat(Tartaros.SOUNDVOLUMEKEY, volume);
//        Tartaros.sound.setVolume(volume);

        Tartaros.mainSettings.flush();
    }

    public static void setLanguage(String language){
        if(language.equals("English")){
            Tartaros.mainSettings.putString("language", "English");
            Tartaros.xmlHandler.setLanguageFile();
        }else if(language.equals("German")){
            Tartaros.mainSettings.putString("language", "German");
            Tartaros.xmlHandler.setLanguageFile();
        }else if(language.equals("Bayrisch")){
            Tartaros.mainSettings.putString("language", "Bayrisch");
            Tartaros.xmlHandler.setLanguageFile();
        }

        Tartaros.mainSettings.flush();
    }

    public static void setSavegameName(int savegame, String name){
        Tartaros.mainSettings.putString(Tartaros.SAVEGAMENAMEKEY+savegame, name);

        Tartaros.mainSettings.flush();
    }

    public static String getLanguage(){
        return(Tartaros.mainSettings.getString("language"));
    }

    public static boolean getMusic(){
        return(Tartaros.mainSettings.getBoolean(Tartaros.MUSICKEY));
    }

    public static float getMusicVolume(){
        return(Tartaros.mainSettings.getFloat(Tartaros.MUSICVOLUMEKEY));
    }

    public static boolean getSound(){
        return(Tartaros.mainSettings.getBoolean(Tartaros.SOUNDKEY));
    }

    public static float getSoundVolume(){
        return(Tartaros.mainSettings.getFloat(Tartaros.SOUNDVOLUMEKEY));
    }

    public static String getSavegameName(int savegame){
        return(Tartaros.mainSettings.getString(Tartaros.SAVEGAMENAMEKEY+savegame));
    }
}
