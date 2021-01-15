package com.module.screencmd.pojo.app_enum;

public enum AppOnOffArg {
    START("start"),
    STOP("stop"),
    RESTART("restart");

    String operation;
    AppOnOffArg(String operation){
        this.operation = operation;
    }

    public String getOperation(){
        return operation;
    }

    public static AppOnOffArg getMatchOnOffArgs(String arg) {
        for(AppOnOffArg item : AppOnOffArg.values()){
            if(item.getOperation().equals(arg))
                return item;
        }
        return null;
    }
}
