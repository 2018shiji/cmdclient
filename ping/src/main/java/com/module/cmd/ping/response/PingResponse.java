package com.module.cmd.ping.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PingResponse {
    @JSONField(name = "ipAddress")
    private String ipAddress;
    @JSONField(name = "online")
    private boolean online;
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "timeStamp")
    private String timeStamp;

}
