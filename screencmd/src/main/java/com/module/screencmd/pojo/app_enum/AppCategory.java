package com.module.screencmd.pojo.app_enum;

public enum AppCategory {
    CHROME("chrome"),
    VLC_MEDIA("vlc");

    String appName;

    AppCategory(String appName){
        this.appName = appName;
    }

    public String getAppName(){
        return appName;
    }

    public static AppCategory getMatchCategory(String arg){
        for(AppCategory item : AppCategory.values()){
            if(item.getAppName().equals(arg))
                return item;
        }
        return null;
    }
}
