package com.brogrammers.tartaros;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;

import java.io.IOException;

public class XmlHandler {

    private XmlReader languageXml;
    private XmlReader.Element languageXmlRoot;
    private XmlReader.Element languageXmlResources;

    private Array<String> splashArray;

    public XmlHandler(){

        languageXml = new XmlReader();
        splashArray = new Array<String>();

    }

    public void setLanguageFile(){

        FileHandle file = null;

        if (Settings.getLanguage().equals("German")) {
            file = Gdx.files.internal("languages/strings_de.xml");
        }else if(Settings.getLanguage().equals("English")){
            file = Gdx.files.internal("languages/strings_en.xml");
        }else if(Settings.getLanguage().equals("Bayrisch")){
            file = Gdx.files.internal("languages/strings_bay.xml");
        }

        try {
            languageXmlRoot = languageXml.parse(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLanguageStrings(String screenName, String stringName){

        languageXmlResources = languageXmlRoot.getChildByName(screenName);

        Array<XmlReader.Element> groups = languageXmlResources.getChildrenByName("string");

        for(XmlReader.Element groupsBuffer : groups){
            if(groupsBuffer.getAttribute("name").equals(stringName)) {
                System.out.println(groupsBuffer.get("value"));
                return groupsBuffer.get("value");
            }
        }

        return ("String not found");

    }

    public Array<String> getSplashArray(){

        languageXmlResources = languageXmlRoot.getChildByName("splashes");

        Array<XmlReader.Element> splashes = languageXmlResources.getChildrenByName("splash");

        for(XmlReader.Element splashesBuffer : splashes){
            splashArray.add(splashesBuffer.get("value"));
        }

        return splashArray;
    }
}
