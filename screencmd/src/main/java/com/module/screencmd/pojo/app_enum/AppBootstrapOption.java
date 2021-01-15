package com.module.screencmd.pojo.app_enum;

public enum AppBootstrapOption {
    /** Chrome浏览器启动参数 **/
    START_FULLSCREEN("--start-fullscreen"),
    NEW_WINDOW("--new-window"),

    /** vlc多媒体播放器启动参数 **/
    NO_VIDEO_TITLE_SHOW("--no-video-title-show"),
    FULLSCREEN("--fullscreen"),
    VIDEO_TITLE("--video-title"),
    REPEAT("--repeat");

    String option;
    AppBootstrapOption(String option){
        this.option = option;
    }

    public String getOption(){
        return option;
    }

    public static AppBootstrapOption getMatchOption(String arg){
        for(AppBootstrapOption item : AppBootstrapOption.values()){
            if(item.getOption().equals(arg))
                return item;
        }
        return null;
    }
}
