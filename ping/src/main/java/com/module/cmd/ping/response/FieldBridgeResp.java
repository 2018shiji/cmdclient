package com.module.cmd.ping.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Data
@ToString
public class FieldBridgeResp {
    @JSONField(name = "stationed")
    private String stationed;

    @JSONField(name = "fBridgeIP")
    private String fBridgeIP;
    @JSONField(name = "fBridgeOnline")
    private boolean fBridgeOnline;
    @JSONField(name = "fBridgeTimeStamp")
    private String fBridgeTimeStamp;

    @JSONField(name = "sBridgeIP")
    private String sBridgeIP;
    @JSONField(name = "sBridgeOnline")
    private boolean sBridgeOnline;
    @JSONField(name = "sBridgeTimeStamp")
    private String sBridgeTimeStamp;

    public FieldBridgeResp(String stationed){
        this.stationed = stationed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldBridgeResp that = (FieldBridgeResp) o;
        return Objects.equals(stationed, that.stationed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationed);
    }
}
