package com.module.cmd.ping.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MainServerResp {
    @JSONField(name = "serverIP")
    private String serverIP;
    @JSONField(name = "serverOnline")
    private boolean serverOnline;
    @JSONField(name = "serverTimeStamp")
    private String serverTimeStamp;
    @JSONField(name = "serverType")
    private String serverType;

    public MainServerResp(String serverType){
        this.serverType = serverType;
    }
}
