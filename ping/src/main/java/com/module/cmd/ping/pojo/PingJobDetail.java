package com.module.cmd.ping.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class PingJobDetail {
    @JSONField(name = "ip")
    private String ip;
    @JSONField(name = "group")
    private String group;
    @JSONField(name = "command")
    private String command;

    public PingJobDetail(String ip){
        this.ip = ip;
        this.command = "ping " + ip;
    }

    public PingJobDetail(String ip, String group){
        this.ip = ip;
        this.group = group;
        this.command = "ping " + ip;
    }

}
