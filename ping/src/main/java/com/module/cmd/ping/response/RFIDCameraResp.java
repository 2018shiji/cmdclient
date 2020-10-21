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
public class RFIDCameraResp {
    @JSONField(name = "stationed")
    private String stationed;

    @JSONField(name = "RFIDIP")
    private String RFIDIP;
    @JSONField(name = "RFIDOnline")
    private boolean RFIDOnline;
//    @JSONField(name = "message")
//    private String message;
    @JSONField(name = "RFIDTimeStamp")
    private String RFIDTimeStamp;

    @JSONField(name = "cameraIP")
    private String cameraIP;
    @JSONField(name = "cameraOnline")
    private boolean cameraOnline;
    @JSONField(name = "cameraTimeStamp")
    private String cameraTimeStamp;

    public RFIDCameraResp(String stationed){
        this.stationed = stationed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RFIDCameraResp that = (RFIDCameraResp) o;
        return Objects.equals(stationed, that.stationed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationed);
    }

}
