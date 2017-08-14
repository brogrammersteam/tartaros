package com.brogrammers.tartaros.screens;

import com.brogrammers.tartaros.Tartaros;

public class Settings {

    public static void setAudio(boolean status){
        if(status){
            Tartaros.mainSettings.putBoolean("audio", true);
            Tartaros.music.play();
        }else{
            Tartaros.mainSettings.putBoolean("audio", false);
            Tartaros.music.stop();
        }

        Tartaros.mainSettings.flush();
    }

    public static void setLanguage(String language){
        if(language.equals("English")){
            Tartaros.mainSettings.putString("language", "English");
        }else if(language.equals("German")){
            Tartaros.mainSettings.putString("language", "German");
        }else if(language.equals("Bayrisch")){
            Tartaros.mainSettings.putString("language", "Bayrisch");
        }

        Tartaros.mainSettings.flush();
    }

    public static String getLanguage(){
        return(Tartaros.mainSettings.getString("language"));
    }

    public static boolean getAudio(){
        return(Tartaros.mainSettings.getBoolean("audio"));
    }
}
