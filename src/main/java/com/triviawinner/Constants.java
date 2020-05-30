package com.triviawinner;

public final class Constants {

    private Constants(){
    }

    public static final String GOOGLE_API_KEY = PropertiesManager.getProperties().getProperty("GOOGLE_API_KEY");
    public static final String GOOGLE_SEARCH_ENGINE_ID = PropertiesManager.getProperties().getProperty("GOOGLE_SEARCH_ENGINE_ID");
}